package ischemic_filter;


import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;




public class distance extends JFrame{ //ischemicfilter{
	//int arr[]=new int[500];
	//int ob=0;
	
	public static  Object of;
	LinkedList ob = new LinkedList();
public distance(String did,float dlat,float dlong){
	try {
		
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
        //Haversine obj= new Haversine();
       
        String query = "select Recipient_Id,coordinates1.Latitude,coordinates1.Longitude from recipient INNER JOIN coordinates1 ON recipient.Hospital_Name=coordinates1.Hospital_Name";
        //System.out.println("jbchj1");

        Statement sta = connection.createStatement();
        ResultSet rset = sta.executeQuery(query);
        int rowCount = 0;
        while(rset.next()) { 
           String Rec_id = rset.getString("Recipient_Id"); 
           int idr=Integer.parseInt(Rec_id);
           float Rlat = rset.getFloat("Latitude");  
           float Rlong   = rset.getFloat("Longitude");
           System.out.println("Latitude"+Rlat+"   Longitude"+Rlong);
         double dis= Haversine.distance1(dlat,Rlat,dlong,Rlong);
          if(dis<=1200)  //speed=300 kmph, ischemic time =4 hrs distance =1200
          {
        	  //arr[ob]=idr;
        	 ob.st_insert(idr);
        	// System.out.println(arr[ob]);
        	  //ob++;
        	  }
        	  
          
          System.out.println("Ischemic check");
           ++rowCount;
        }
        
      //ob.delete_v(2);
      //ob.display();
      System.out.println("Ichemic check done");
      JFrame frame;
      
      frame=new JFrame("Searching");
      of=frame;
      JLabel l8=new JLabel("Searching Complete.");
      JLabel l9 = new JLabel("Kindly check your email for the result.");
      l8.setBounds(50, 50, 400, 80);
      l9.setBounds(50,70,400,80);
      Font font = new Font("", Font.BOLD,14);
      l8.setFont(font);
      frame.add(l8);
      frame.add(l9);
      
      frame.setSize(450, 250) ;
      frame.setLayout(null);
      
      frame.setLocation(450, 200);
      Color clr = new Color(153,204,153);
      frame.getContentPane().setBackground(clr);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
     
      
      ob.display();
      health_filter.Test1 obj = new health_filter.Test1(ob); 
        
        connection.close();
    } catch (Exception exception) {
        exception.printStackTrace();
    }
}
	
}