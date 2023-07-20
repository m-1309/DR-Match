package Notification;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class ConfirmRecipient 
{
	String emm,nam,hosp;
	int idr1;
    public ConfirmRecipient(String em1,int id,String nm,String hos)
    {
        //System.out.println( "Hello World!" );
    	emm=em1;
    	idr1=id;
    	nam=nm;
    	hosp=hos;
    	String msg="Greetings!"+"\n"
    			+ "We have received the details of recipient from "+hosp+" Hospital. The details are provided below."+"\n"
    			+"Recipient id:"+id+"  Recipient Name: "+nam+"\n"+"We'll notify you as soon as we find the appropriate match."+"\n"+"Regards, "+"\n"+"DR Match System";
		String subject="Confirmation";
		String to=emm;
		String from= "drmatch2022@gmail.com";
		sendEmail(msg,subject,to,from);
		
	}

	public  void sendEmail(String msg, String subject, String to, final String from) 
	{ //Responsible to send email
		
		//variable for gmail host
		String host="smtp.gmail.com";
		//get the system properties
		Properties properties= System.getProperties();
		
		properties.put("mail.smtp.host",host); //key-value pair
		properties.put("mail.smtp.port", "465");
		properties.put("mail.debug", "true");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth","true");
		
		//step1: to get session object
		
		//Session has factory method to get session object
		 Session session= Session.getDefaultInstance(properties, new javax.mail.Authenticator() 
		{

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(from,"njkjdtozgaojcdbm");
			}
			
		});
		 session.setDebug(true);
		 
		 //Step 2: Compose the message [text,multi media]
		 MimeMessage mimmsg = new MimeMessage(session);
		 //MimeMessage extends Message and implements MimePart
		
		 try 
		 {
			 InternetAddress addressFrom = new InternetAddress(from);
			 //from email

			mimmsg.setFrom(addressFrom);
			
			//recipient email
			mimmsg.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			//if multiple addresses, pass an array of internet addresses objects
			//add subject to msg
			mimmsg.setSubject(subject);
			//add text to msg
			mimmsg.setText(msg);
			//send
			//Step 3 : Send msg using Transport class
			Transport transport=session.getTransport("smtp");
			transport.connect();
			transport.send(mimmsg);
			transport.close();
			System.out.println("Sent Success");
			
		} 
		 catch (Exception e) 
		 {
			 //throw new RuntimeException(e);
			e.printStackTrace();
		}
	}
		 public static void main(String args[]) {
			 ConfirmRecipient ab = new ConfirmRecipient("drmatch2022@gmail.com",1,"Abhay","admin3");
		 }
		
				}
		
	
	



    

