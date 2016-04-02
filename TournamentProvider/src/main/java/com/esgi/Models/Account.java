package com.esgi.Models;

import org.springframework.ui.Model;
import javax.persistance.Entity;

/**
 * Created by user on 02/04/2016.
 */
@Entity
public class Account extends Model {

    private int idAccount;
    private String login;
    private String password;
    private boolean isAdmin;

    public int getIdAccount() {
        return idAccount;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsAdmin(){
        return isAdmin;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsAdming(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


}
