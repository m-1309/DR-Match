package hospital_registration;

import java.awt.*;
import javax.swing.*;
//import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.Statement;
import java.sql.*;

public class signup extends JFrame implements ActionListener {
JLabel l1,l2,l3,l4,l5,l6;
JPasswordField t3;
JButton b1,b2;
JTextField t1,t2,t4,t5;   
public signup()
{
	
l1=new JLabel("REGISTER YOUR HOSPITAL WITH US! ");
l1.setBounds(50, 20, 300, 40);
Font font = new Font("", Font.BOLD,15);
l1.setFont(font);
l2=new JLabel("NAME:");
l2.setBounds(30, 100, 100, 20);
l3=new JLabel("LOCATION:");
l3.setBounds(30, 150, 100, 20);
l5=new JLabel("USERNAME:");
l5.setBounds(30,250,100,20);
l4=new JLabel("PASSWORD:");
l4.setBounds(30, 300, 100, 20);
l6=new JLabel("Email");
l6.setBounds(30,200,100,20);
t1=new JTextField();  
t1.setBounds(160,100, 200,20);
t2=new JTextField();  
t2.setBounds(160,150, 200,20);
t5=new JTextField();
t5.setBounds(160,200,200,20);
t3=new JPasswordField();  
t3.setBounds(160,300, 200,20);
t4=new JTextField();
t4.setBounds(160,250,200,20);

b1 = new JButton("SUBMIT");
b2 = new JButton("RESET");



b1.setBounds(70, 370, 120, 20);
b2.setBounds(220, 370, 120, 20);


add(l1);
add(l2);
add(l3);
add(l4);
add(l5);
add(l6);

add(b1);
add(b2);

add(t1);
add(t2);
add(t3);
add(t4);
add(t5);
b1.addActionListener(this);
b2.addActionListener(this);


setSize(500, 500) ;


setLayout(null);
 

setVisible(true);
setTitle("SignUp");
setLocation(450, 200);
Color clr = new Color(153,204,153);
getContentPane().setBackground(clr);
}

public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == b1) {
    	String Host_Name = t1.getText();
        String Location = t2.getText();
        String Password = t3.getText();
        String Username = t4.getText();
        String Email = t5.getText();
        

        try {
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
	               Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
	               if (Username.equals("")) {
	                    JOptionPane.showMessageDialog(this, "Username Required");
	                } else if (Password.equals("")) {
	                    JOptionPane.showMessageDialog(this, "Password Required");
	                }
	                else if (Location.equals("")) {
	                    JOptionPane.showMessageDialog(this, "Location Required");
	                }
	                else if (Email.equals("")) {
	                    JOptionPane.showMessageDialog(this, "Email Required");
	                }
	                else if (Host_Name.equals("")) {
	                    JOptionPane.showMessageDialog(this, "Hospital Name Required");
	                }
	                else {
	                	PreparedStatement ps = connection.prepareStatement("Select * from coordinates1 where Location=?");
	                    ps.setString(1,Location);
	                    
	                   ResultSet rs1 = ps.executeQuery();
	                    
	                    if (rs1.next()) {
	                    PreparedStatement pst = connection.prepareStatement("insert into login1 (Username, Password, Hospital_Name,Location,Email)"+"values (?, ?, ?, ?,?)"); 
	                    pst.setString(1, Username);
	                    pst.setString(2, Password);
	                    pst.setString(3, Host_Name);
	                    pst.setString(4, Location);
	                    pst.setString(5, Email);
	                    int x= pst.executeUpdate();

	                    if (x == 0) {
	                        JOptionPane.showMessageDialog(b1, "This already exists. \n Try Again.");
	                    } else {
	                        JOptionPane.showMessageDialog(b1,
	                            "Welcome, " + Host_Name + " Your account is sucessfully created");
	                        login ls = new login();
	                        setVisible(false);
	                    }
	                    connection.close();
	                    }
	                    else {
	                    	{
		                        JOptionPane.showMessageDialog(this, "Location Not Accepted");
		                        t1.setText("");                         
		    	                t2.setText("");
		    	                t3.setText("");
		    	                t4.setText("");
		    	                t5.setText("");
		                    }
	                    }
	                }
	            } catch (SQLIntegrityConstraintViolationException e)  {
	            	JOptionPane.showMessageDialog(b1, "This already exists. \n Try Again.");
	            	System.out.println(e);
	            	t1.setText("");                         
	                t2.setText("");
	                t3.setText("");
	                t4.setText("");
	                t5.setText("");
	            }
        catch(SQLException e)
        {
        	System.out.println(e);
        }
        catch(Exception e1) {
        	System.out.println(e1);
        }
	        }

    if (ae.getSource() == b2) {
        t1.setText("");                         
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
    }
}

    public static void main(String[] args)
    {
    	 
    	signup sp = new signup();
    }
}



