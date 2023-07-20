package d_r_Registration;


import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import ischemic_filter.distance;


public class donor extends JFrame implements ActionListener {
JLabel l1,l2,l3,l4,l5,l6,l7,l8;
JButton b1,b2;
JTextField t1,t2,t3,t4,t5,t6,t7; 
PreparedStatement pst,pst1,pst2,pst3,pst4;
ResultSet rs,rs1,rs2,rs3;
String DName,age,Hname,bloodG,bmi,con,em,nm,inf;
public String loc;
 String did;
 int hid;
float lat,lon;
String C="false";
public donor(int h)
{
	hid=h;
	// creates instance of JLabel for "Name"and "Location" labels
l1=new JLabel("WELCOME ,Enter your details. ");
l1.setBounds(50, 20, 300, 40);
Font font = new Font("", Font.BOLD,15);
l1.setFont(font);
l2=new JLabel("Name of donor:");
l2.setBounds(20, 70, 100, 20);
l3=new JLabel("Age:");
l3.setBounds(20, 110, 100, 20);
l4=new JLabel("Hospital Name:");
l4.setBounds(20, 150, 100, 20);
l5=new JLabel("Blood Group:");
l5.setBounds(20, 190, 100, 20);
l6=new JLabel("Consent of Guardian given?Y/N");
l6.setBounds(20, 230, 200, 20);
l7=new JLabel("BMI");
l7.setBounds(20,270,100,20);
l8=new JLabel("Infection Status Yes/No");
l8.setBounds(20,300,200,20);

t1=new JTextField();  
t1.setBounds(150,70, 200,20); 
t2=new JTextField();  
t2.setBounds(150,110, 200,20);
t3=new JTextField();  
t3.setBounds(150,150, 200,20);
t4=new JTextField();  
t4.setBounds(150,190, 200,20);
t5=new JTextField();  
t5.setBounds(260,230, 40,20);
t6=new JTextField();
t6.setBounds(150,270,200,20);
t7=new JTextField();
t7.setBounds(260,300,70,20);
b1 = new JButton("SUBMIT");
b2 = new JButton("RESET");




b1.setBounds(50, 350, 120, 20);
b2.setBounds(200,350, 120, 20);


add(l1);
add(l2);
add(l3);
add(l4);
add(l5);
add(l6);
add(l7);
add(l8);

add(b1);
add(b2);

add(t1);
add(t2);
add(t3);
add(t4);
add(t5);
add(t6);
add(t7);
b1.addActionListener(this);
b2.addActionListener(this);



setSize(400, 450) ;
 
setLayout(null);
 setLocation(450,200);
setTitle("Donor Details");
setVisible(true);
Color clr = new Color(153,204,153);
getContentPane().setBackground(clr);


}

public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == b1) {
    	DName = t1.getText();
        age = t2.getText();
        Hname = t3.getText();
        bloodG = t4.getText();
        bmi = t6.getText();
        con=t5.getText();
        inf=t7.getText();
        try {
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
           pst4=connection.prepareStatement("select Hospital_Name from login1 where Hospital_Id='"+hid+"'");
           rs3=pst4.executeQuery();
           while(rs3.next()) {
        	   nm=rs3.getString(1);
           }
            if (DName.equals("")) {
                JOptionPane.showMessageDialog(this, "Donor Name Required","Warning",JOptionPane.WARNING_MESSAGE);
            } else if (age.equals("")) {
                JOptionPane.showMessageDialog(this, "Age Required","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if (Hname.equals("")) {
                JOptionPane.showMessageDialog(this, "Hospital Name Required","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if(!Hname.equals(nm)) {
            	JOptionPane.showMessageDialog(this, "Wrong Hospital Name","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if (bloodG.equals("")) {
                JOptionPane.showMessageDialog(this, "Blood Group Required","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if (bmi.equals("")) {
                JOptionPane.showMessageDialog(this, "BMI Required","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if (inf.equals("")) {
                JOptionPane.showMessageDialog(this, "Infection Status Required","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if(inf.equalsIgnoreCase("yes")) {
            	JOptionPane.showMessageDialog(this, "Infected Patient. DR Match is sorry to inform you we can't accept this donor.","Warning",JOptionPane.WARNING_MESSAGE);
            }	
            
            else if (!con.equalsIgnoreCase("Y")) {
                JOptionPane.showMessageDialog(this, "Cannot proceed without guardian's consent.","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else {
            	pst = connection.prepareStatement("insert into donor (Donor_Name,Age,Hospital_Name,Blood_Group,BMI,Infection_Status)"+"values (?,?,?,?,?,?)"); 
                pst.setString(1, DName);
                pst.setString(2, age);
                pst.setString(3, Hname);
                pst.setString(4, bloodG);
                pst.setString(5, bmi);
                pst.setString(6, inf);
               
                int x= pst.executeUpdate();

            if (x == 0) {
                JOptionPane.showMessageDialog(b1, "This already exists.");
                t1.setText("");                         
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("");
            } else {
            	
                JOptionPane.showMessageDialog(b1,
                    "Dear Donor, " + DName + " Your submission has been received.");
                setVisible(false);
                System.out.println("Hospital Name:" + Hname);
                PreparedStatement pst1= connection.prepareStatement("select Email from login1 where login1.Hospital_Name='"+Hname+"'");
                ResultSet rs = pst1.executeQuery();
                while(rs.next()) {
               	 em=rs.getString(1);
               	 System.out.println("Email: "+em);
               	 break;
               }
                
             
                pst2=connection.prepareStatement("select Latitude,Longitude from coordinates1 where Hospital_Name ='"+Hname+"'");
                rs1=pst2.executeQuery();
                System.out.println("select latitude");
                pst3=connection.prepareStatement("select Donor_Id from donor where Donor_Name='"+DName+"'");
                rs2=pst3.executeQuery();
                System.out.println("select donor id");
                while(rs2.next()) {
                	 did=rs2.getString(1);
                 System.out.println("WHILE loop1");
                }
                while(rs1.next()) {
                	//String l0=rs1.getString(1);
                	String l1=rs1.getString(1);
                	lat=Float.parseFloat(l1);
                	String l2=rs1.getString(2);
                	lon=Float.parseFloat(l2);
                	System.out.println("while 2");
                	//System.out.println(lat+" "+lon);
                	distance iscd=new distance(did,lat,lon); //backend trigger
                	
                	               }
            }
            connection.close();
        } }catch (Exception e) {
            System.out.println(e);        }
        
        	
        	
        	
    }

    if (ae.getSource() == b2) {
        t1.setText("");                         
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
    }
}
	
    
	
    public static void main(String[] args)
    {
    	 // creates instance of JFrame
    	donor d = new donor(0);
    	 d.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

