/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 *
 * @author Lenovo
 */

public class TestSMS {
public static void main(String[] args) throws Exception{
// TODO Auto-generated method stub
String message = "Prueba modem SMS desde Java";      
String phone = "84069608";
String username = "abcd";
String password = "1234";
String address = "http://192.168.1.137";
String port = "8090";

URL url = new URL(
        address+":"+port+"/SendSMS?username="+username+"&password="+password+
        "&phone="+phone+"&message="+URLEncoder.encode(message,"UTF-8"));

URLConnection connection = url.openConnection();
BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
String inputLine;
while((inputLine = bufferedReader.readLine()) !=null){
    System.out.println(inputLine);
}
bufferedReader.close();
}
}

