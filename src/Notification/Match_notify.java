package Notification;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Match_notify
{
	String emd,dnam,emr,rnam,dhosp,rhosp;
	int idd1,idr1;
	int count =0;
    public Match_notify(String em1,String emr1,int did,int rid,String dnm,String rnm,String dho,String rho)
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
    	
    	String msgd="Greetings!"+"\n"
    			+ "We have received the appropriate match for your donor: "+"\n"+ "Donor id:"+idd1+"  Donor Name: "+dnam+"\n"+"The recipient is :"+"\n"
				+"Recipient Id: "+idr1+"  Rceipient Name: "+rnam+" from Hospital: "+rhosp+".\n"+"Regards, "+"\n"+"DR Match System";
    	String subject="Congratulations";
		String tod=emd;
		
		
		String from= "drmatch2022@gmail.com";
		sendEmail(msgd,subject,tod,from);
		
		
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
			Match_Rec_Notify ob1 = new Match_Rec_Notify(emd,emr,idd1,idr1,dnam,rnam,dhosp,rhosp);
		 
		 }
		 catch (Exception e) 
		 {
			 //throw new RuntimeException(e);
			e.printStackTrace();
		}
	}
//	public static void main(String args[]) {
//		Match_notify on = new Match_notify(null,null,0,0,null,null,null,null);
//	}
//		 	
				}
		
	
	



    

