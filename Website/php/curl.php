<?php

function my_curl($url, $method, $data, $timeout=2, $error_report=FALSE)
{
    $curl = curl_init();
    $header = [];
    $header[] = "Content-Type: application/json";

    // SET THE CURL OPTIONS - SEE http://php.net/manual/en/function.curl-setopt.php
    curl_setopt($curl, CURLOPT_URL,            $url);
   curl_setopt($curl, CURLOPT_USERAGENT,      'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36');
    curl_setopt($curl, CURLOPT_HTTPHEADER,     $header);
    curl_setopt($curl, CURLOPT_REFERER,        'http://appstree.fr');
    curl_setopt($curl, CURLOPT_ENCODING,       'gzip, deflate, sdch');
    curl_setopt($curl, CURLOPT_AUTOREFERER,    TRUE);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, TRUE);
    curl_setopt($curl, CURLOPT_FOLLOWLOCATION, TRUE);
    curl_setopt($curl, CURLOPT_HEADER, false);
    curl_setopt($curl, CURLOPT_TIMEOUT,        $timeout);

    curl_setopt($curl, CURLOPT_HTTPHEADER,     $header);
    curl_setopt($curl, CURLOPT_CUSTOMREQUEST, $method);
    curl_setopt($curl, CURLOPT_POSTFIELDS, $data);
    // RUN THE CURL REQUEST AND GET THE RESULTS
    $htm = curl_exec($curl);

    $err = curl_errno($curl);
    $inf = curl_getinfo($curl);
    curl_close($curl);

    // ON FAILURE
    if (!$htm)
    {
        // PROCESS ERRORS HERE
        if ($error_report)
        {
            echo "CURL FAIL: $url TIMEOUT=$timeout, CURL_ERRNO=$err";
            var_dump($inf);
        }
        return FALSE;
    }

    // ON SUCCESS
    return $htm;
}

$url = "http://localhost:8080/accounts";
$method = "POST";
$data = '{"body":{"id":null,"login":"Kavlo","password":"myPassword123","isAdmin":false},"token":""}';

$url = $_GET["url"];
$method = $_GET["method"];
$data = $_GET["data"];
$htm = my_curl($url , $method, $data, 5 , true );
echo $htm;

?>