package Notification;
 

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
//import java.net.URI;
//import com.twilio.*;
//import java.net.URISyntaxException;
//import com.twilio.rest.api.v2010.account.Call;
//import com.twilio.exception.ApiException;
//import com.twilio.exception.AuthenticationException;

public class Example {
 // Find your Account SID and Auth Token at twilio.com/console
 // and set the environment variables. See http://twil.io/secure
 public static final String ACCOUNT_SID = "AC6493efac5e4b83f85dafbf9ea27ae6bc";
 public static final String AUTH_TOKEN = "17a2ff91c1546ac94a23cccf601938d2";

 public static void main(String[] args) { //throws AuthenticationException {
     Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
     //Example ob=new Example();
    
     try {
     Message message = Message.creator(
             new com.twilio.type.PhoneNumber("+14345954913"), //to
             new com.twilio.type.PhoneNumber("+14345954708"),//from
             "Hi there")
         .create();

     System.out.println(message.getSid());
 
}
 
     catch (final Exception e) {
     System.err.println(e);
}
}
}