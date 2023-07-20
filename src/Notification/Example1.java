package Notification;

import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber; 
 
import java.net.URI; 
import java.math.BigDecimal; 
 
public class Example1 { 
    // Find your Account Sid and Token at twilio.com/console 
    public static final String ACCOUNT_SID = "AC6493efac5e4b83f85dafbf9ea27ae6bc"; 
    public static final String AUTH_TOKEN = "17a2ff91c1546ac94a23cccf601938d2"; 
 
    public static void main(String[] args) { 
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        Message message = Message.creator( 
                new com.twilio.type.PhoneNumber("+916205373032"),  
                "MGb1dc45ac76887a254289e32725d7eaf7", 
                "Hello")      
            .create(); 
 
        System.out.println("helo"+message.getSid()); 
    } 
}