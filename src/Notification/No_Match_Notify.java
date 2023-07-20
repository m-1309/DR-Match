package Notification;


	

	import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

	import javax.mail.Message;
	import javax.mail.PasswordAuthentication;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeMessage;


	public class No_Match_Notify 
	{
		String emm,nam;
		int idd1;
	    public No_Match_Notify(String em1,int id,String nm)
	    {
	        //System.out.println( "Hello World!" );
	    	emm=em1; //donor email
	    	idd1=id;
	    	nam=nm;
	    	String msg="Greetings!"+"\n"
	    			+ "We are sorry to inform you that there is no appropriate recipient available for the donor:"+"\n"
	    			+"Donor id:"+idd1+"  Donor Name: "+nam+".\n"+"Regards, "+"\n"+"DR Match System";
			String subject="Sorry-No match found.";
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
			 finally{
				 try{
					 Class.forName("com.mysql.jdbc.Driver").newInstance();    		
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
					PreparedStatement psy1= connection.prepareStatement("delete from donor where 1");
					int f=psy1.executeUpdate();
					}
				 catch(Exception e) {
					 System.out.println(e);
				 }
		}
		}
//		 public static void main(String args[]) {
//			 No_Match_Notify ab = new No_Match_Notify("aggarwalmehak2002@gmail.com",57,"Neha");			 }
//			
					}
			
		
		



	    


	

