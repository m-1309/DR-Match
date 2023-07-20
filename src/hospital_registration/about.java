package hospital_registration;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

public class about extends JFrame implements ActionListener {
JLabel l1,l2,l3,l4;
JButton b1,b2,b3;
JTextField t1,t2,t3;   
JTextArea area1;
String A;
int h;
String msg;//hospital id
public about(String a)
{
	// creates instance of JLabel for "Name"and "Location" labels
A=a;

JLabel l1=new JLabel("ABOUT US... ");
l1.setBounds(400, 30, 300, 40);
Font font = new Font("", Font.BOLD,25);
l1.setFont(font);

JTextArea area=new JTextArea("When a person has a heart transplant, their damaged or failing heart is immediately replaced with a healthy heart that has been donated.\nOrgan transportation must be done on time for heart transplants."+" \nHeart donation is declining, but demand for heart transplants is rising. \nHeart transportation services will aid in preserving patients' lives in these circumstances.\r\n"
		+ "Organs that have been donated must go quickly to their destination. \nTransporting hearts for transplantation must be done quickly. \nIt requires transportation services that are flexible, quick to respond, and secure. "+"\nAir ambulance services are the most efficient emergency organ transfer services for delicate organs like kidney, liver, heart, etc.,\n when compared to all other medical transportation services.\r\n"
		+ "Consequently, this application seeks to assist with heart transportation by locating the best donor match.");  
area.setBounds(50,150, 800,200);  

JLabel l2=new JLabel("Continue as:");
l2.setBounds(20, 100, 100, 20);
b1 = new JButton("DONOR");
b2 = new JButton("RECIPIENT");
b3 = new JButton("Your Information");

//x axis, y axis, width, height
b1.setBounds(200,400, 120, 20);
b2.setBounds(500,400, 120, 20);
b3.setBounds(700,30, 150, 20);


//adds labels in Frame1
add(l1);
add(area);
//adds buttons to Frame1
add(b1);
add(b2);
add(b3);

b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
setSize(900, 600) ;
Color clr = new Color(153,204,153);
getContentPane().setBackground(clr);
//uses no layout managers
setLayout(null);
 
//makes the frame visible
setVisible(true);

}
public void actionPerformed(ActionEvent ae) {
	try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
    String query = "SELECT * FROM login1"+" WHERE Username ='"+A+"'";
    Statement sta = connection.createStatement();
    ResultSet rs    = sta.executeQuery(query);
    while (rs.next()) {
    	h=rs.getInt("Hospital_Id");
    	msg="Hospital_Id: "+rs.getString("Hospital_Id") + "\n"+ "Username: "+rs.getString("Username")+"\n"+"Hospital Name: "+rs.getString("Hospital_Name") + "\n" +"Location: "+ 
                rs.getString("Location");
// 	   JOptionPane.showMessageDialog(this,"Hospital_Id: "+rs.getString("Hospital_Id") + "\n"+ "Username: "+rs.getString("Username")+"\n"+"Hospital Name: "+rs.getString("Hospital_Name") + "\n" +"Location: "+ 
//                rs.getString("Location"),"User Details", JOptionPane.PLAIN_MESSAGE);
         /*System.out.println(rs.getString("Username")+"\t"+rs.getString("Hospital_Name") + "\t" + 
                            rs.getString("Location"));*/
      
             }
 connection.close();
 } 
	catch (Exception exception) 
	{
        exception.printStackTrace();
    } 
    
        
    if (ae.getSource() == b1) {
    	setVisible(false);
       d_r_Registration.donor d=new d_r_Registration.donor(h);
    }

    if (ae.getSource() == b2) {
    	setVisible(false);
       d_r_Registration.recipient r=new d_r_Registration.recipient(h);
    }
    if (ae.getSource() == b3) {
                JOptionPane.showMessageDialog(this,msg,"User Details",JOptionPane.PLAIN_MESSAGE);           
    	
     }
   }
	
    
	
    public static void main(String[] args)
    {
    	 // creates instance of JFrame
    	about ab = new about("admin3@drmatch");
    	 ab.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}



