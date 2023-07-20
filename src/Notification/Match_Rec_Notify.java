package Notification;



import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import ischemic_filter.distance;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Match_Rec_Notify{

	String emd,dnam,emr,rnam,dhosp,rhosp;
	int idd1,idr1;
    public Match_Rec_Notify(String em1,String emr1,int did,int rid,String dnm,String rnm,String dho,String rho)
    {
        //System.out.println( "Hello World!" );
    	emd=em1;
    	emr=emr1;
    	idd1=did;
    	idr1=rid;
    	dnam=dnm;
    	rnam=rnm;
    	dhosp=dho;
    	rhosp=rho;
    	String msgr="Greetings!"+"\n"+ "We have received the appropriate match for your recipient: "+"\n"+ "Recipient id:"+idr1+"  Recipient Name: "+rnam+"\n"+"The donor is :"+"\n"
    			+"Donor Id: "+idd1+"  Donor Name: "+dnam+" from Hospital: "+dhosp+".\n"+"Regards, "+"\n"+"DR Match System";
    	String tor=emr;
    	String subject="Congratulations";
    	String from= "drmatch2022@gmail.com";
    	sendEmail(msgr,subject,tor,from);
    
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
		 finally{
			 try{
				 Class.forName("com.mysql.jdbc.Driver").newInstance();    		
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
				PreparedStatement psy= connection.prepareStatement("delete from donor where 1");
				int f1=psy.executeUpdate();
				Object oj=distance.of;
				
				}
			 catch(Exception e) {
				 System.out.println(e);
			 }
	}
//	public static void main(String args[]) {
//		Match_Rec_Notify on = new Match_Rec_Notify(null,null,0,0,null,null,null,null);
//	}
//		 	
				}
}
		
	
	



    



		
	
	
