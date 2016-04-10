package com.esgi.account.authentication;

import com.esgi.account.model.Account;
import com.esgi.account.service.AccountService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import java.security.MessageDigest;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * Created by Andreï on 07/04/2016.
 */


//ToDo : Exceptions de validation/creation et
@Component
public class TokenProvider {

    @Autowired
    AccountService accountService;
    ShaPasswordEncoder encoder;

    static String TOKEN_SEPARATOR = "!!!";
    private static final String NEW_LINE = "\r\n";
    private static final int ShaStrength = 256;//Todo variable globale ?
    private static final String secretKey = "SpringSecretKeyESGI";

    public TokenProvider( )
    {
        encoder = new ShaPasswordEncoder( ShaStrength );
    }
    public TokenProvider( AccountService accountService )
    {
        encoder = new ShaPasswordEncoder( ShaStrength );
        this.accountService = accountService;
    }

    public String getToken(Account account) {
        return getToken(account, DateTime.now().plusDays(1).getMillis());
    }

    public String getToken( Account account, long expirationDateInMillis )
    {
        StringBuilder tokenBuilder = new StringBuilder();

        byte[] token = tokenBuilder
                .append( account.getLogin() )
                .append( TOKEN_SEPARATOR )
                .append( expirationDateInMillis )
                .append( TOKEN_SEPARATOR )
                .append( buildTokenKey( account, expirationDateInMillis ))
                .toString().getBytes();

        return Base64.encodeBase64String( token ).replace(NEW_LINE, "");
    }

    public boolean isTokenValid( String encodedToken )
    {
        String[] tokenComponents = decodeAndDissectToken( encodedToken );

        if (tokenComponents == null || tokenComponents.length != 3) {
            System.out.println("not a token");
            return false;
        }

        String externalUser = tokenComponents[0];
        Long externalDate = Long.parseLong(tokenComponents[1]);
        String externalKey  = tokenComponents[2];

        Account account = accountService.getAccountByLogin( externalUser );
        if( account == null )
        {
            return false;
        }

        String expectedKey = buildTokenKey( account, externalDate );

        byte[] expectedKeyBytes = expectedKey.getBytes();
        byte[] externalKeyBytes = externalKey.getBytes();

        if (!MessageDigest.isEqual(expectedKeyBytes, externalKeyBytes)) {
            System.out.println("token altered");
            return false;
        }

        if (new DateTime(externalDate).isBeforeNow()) {
            System.out.println("token expired");
            return false;
        }

        return true;
    }

    private String buildTokenKey( Account account, long expirationDateInMillis )
    {
        StringBuilder keyBuilder = new StringBuilder();
        String key = keyBuilder
                .append(account.getLogin())
                .append(TOKEN_SEPARATOR)
                .append(account.getPassword())
                .append(TOKEN_SEPARATOR)
                .append(expirationDateInMillis)
                .append(TOKEN_SEPARATOR)
                .append(secretKey).toString();

        //byte[] keyBytes = key.getBytes();
        return encoder.encodePassword(key, secretKey);
    }

    public Account getAccountFromToken(String token) {
        if (!isTokenValid(token))
        { return null; }

        String[] components = decodeAndDissectToken(token);
        if (components == null || components.length != 3)
        { return null; }
        String login = components[0];

        return accountService.getAccountByLogin( login );
    }

    private String[] decodeAndDissectToken( String encodedToken )
    {
        if(StringUtils.isBlank(encodedToken) || !Base64.isArrayByteBase64(encodedToken.getBytes())) {
            return null;
        }

        // Apache Commons Base64 expects encoded strings to end with a newline, add one
        if(!encodedToken.endsWith(NEW_LINE)) { encodedToken = encodedToken + NEW_LINE; }

        String token = new String(Base64.decodeBase64(encodedToken));

        if(!token.contains( TOKEN_SEPARATOR ) || token.split( TOKEN_SEPARATOR ).length != 3)
        {
            return null;
        }

        return token.split( TOKEN_SEPARATOR );
    }
}
