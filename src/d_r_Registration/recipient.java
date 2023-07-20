package d_r_Registration;
import Notification.ConfirmRecipient;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class recipient extends JFrame implements ActionListener {
JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l0;
JButton b1,b2;
JTextField t1,t2,t3,t4,t5,t6,t7,t8; 
JRadioButton r1,r2;
String em,RName,age,Hname,bloodG,medhist,status,bmi,nm,survival;
int id,h;
public recipient(int hid)
{
	h=hid;
	
l1=new JLabel("WELCOME ,Enter your details. ");
l1.setBounds(50, 20, 300, 40);
Font font = new Font("", Font.BOLD,15);
l1.setFont(font);
l2=new JLabel("Name of recipient:");
l2.setBounds(20, 100, 150, 20);
l3=new JLabel("Age:");
l3.setBounds(20, 140, 150, 20);
l4=new JLabel("Hospital Name:");
l4.setBounds(20, 180, 150, 20);
l5=new JLabel("Blood Group:");
l5.setBounds(20, 220, 150, 20);
l6=new JLabel("Medical History");
l9=new JLabel("(Character limit:1000)");
l6.setBounds(20,300, 250, 20);
l9.setBounds(30,315,150,20);
l7=new JLabel("Patient Status(1-6)");
l7.setBounds(20,360,150,20);
l0=new JLabel("Survival Chances");
l0.setBounds(20,390,150,20);
l8=new JLabel("BMI");
l8.setBounds(20,260,150,20);
t1=new JTextField();  
t1.setBounds(260,100, 200,20); 
t2=new JTextField();  
t2.setBounds(260,140, 200,20);
t3=new JTextField();  
t3.setBounds(260,180, 200,20);
t4=new JTextField();  
t4.setBounds(260,220, 200,20);
t5= new JTextField();
t5.setBounds(260, 300, 200, 50);
t6=new JTextField();
t6.setBounds(260,360,100,20);
t7=new JTextField();
t7.setBounds(260,260,100,20);
t8=new JTextField();
t8.setBounds(260,390,200,20);
b1 = new JButton("SUBMIT");
b2 = new JButton("RESET");

b1.setBounds(50, 430,120,20);
b2.setBounds(200,430,120,20);


add(l1);
add(l2);
add(l3);
add(l4);
add(l5);
add(l6);
add(l7);
add(l8);
add(l9);
add(l0);
add(b1);
add(b2);

add(t1);
add(t2);
add(t3);
add(t4);
add(t5);
add(t6);
add(t7);
add(t8);
b1.addActionListener(this);
b2.addActionListener(this);


setSize(500, 500) ;
 

setLayout(null);
Color clr = new Color(153,204,153);
getContentPane().setBackground(clr);
 setTitle("Recipient Details");

setVisible(true);

}

public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == b1) {
    	 RName = t1.getText();
         age = t2.getText();
         Hname = t3.getText(); 
         bloodG = t4.getText();
         medhist = t5.getText();
         status=t6.getText();
         bmi = t7.getText();
         survival=t8.getText();
        

        try {
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
               Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
               PreparedStatement pst4=connection.prepareStatement("select Hospital_Name from login1 where Hospital_Id='"+h+"'");
               ResultSet rs3=pst4.executeQuery();
               while(rs3.next()) {
            	   nm=rs3.getString(1);
               }
                        if (RName.equals("")) {
                JOptionPane.showMessageDialog(this, "Recipient Name Required","Warning",JOptionPane.WARNING_MESSAGE);
            } else if (age.equals("")) {
                JOptionPane.showMessageDialog(this, "Age Required","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if (Hname.equals("")) {
                JOptionPane.showMessageDialog(this, "Hospital Name Required","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if (!Hname.equals(nm)) {
                JOptionPane.showMessageDialog(this, "Wrong Hospital Name","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if (bloodG.equals("")) {
                JOptionPane.showMessageDialog(this, "Blood Group Required","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if (bmi.equals("")) {
                JOptionPane.showMessageDialog(this, "BMI Required","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if (medhist.equals("")) {
                JOptionPane.showMessageDialog(this, "Medical History Required!","Warning",JOptionPane.WARNING_MESSAGE);
            }
                      
            else if (survival.equals("")) {
                JOptionPane.showMessageDialog(this, "Survival Chances Required!","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if (status.equals("")) {
                JOptionPane.showMessageDialog(this, "Patient Status Required!","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else {
            	PreparedStatement pst = connection.prepareStatement("insert into recipient (Recipient_Name,Age,Hospital_Name,Blood_Group,BMI,Patient_Status,Survival_Chances,Medical_History)"+"values (?,?, ?, ?,?,?,?,?)"); 
                pst.setString(1, RName);
                pst.setString(2, age);
                pst.setString(3, Hname);
                pst.setString(4, bloodG);
                pst.setString(5, bmi);
                pst.setString(8,medhist);
                pst.setString(6, status);
                pst.setString(7, survival);               
                int x= pst.executeUpdate();
                PreparedStatement pst1= connection.prepareStatement("select Email from login1 where login1.Hospital_Name='"+Hname+"'");
                ResultSet rs = pst1.executeQuery();
                while(rs.next()) {
               	 em=rs.getString(1);
               	 System.out.println("Email:"+em);
               	 break;
               }
                PreparedStatement pst2= connection.prepareStatement("select Recipient_Id from recipient where recipient.Recipient_Name='"+RName+"'"+"AND Hospital_Name='"+Hname+"'");
                ResultSet rs1 = pst2.executeQuery();
                while(rs1.next()) {
               	 id=rs1.getInt(1);
               	 System.out.println("Recipient id: "+id);
               	 break;
                }
            if (x == 0) { 
                JOptionPane.showMessageDialog(b1, "This already exists. Try again.","Message",JOptionPane.PLAIN_MESSAGE);
                t1.setText("");                         
    	        t2.setText("");
    	        t3.setText("");
    	        t4.setText("");
    	        t5.setText("");
    	        t6.setText(" ");
    	        t7.setText("");
    	        
    	    
            } else {
                JOptionPane.showMessageDialog(b1,
                    "Dear , " + RName + " Your request has been received.","Message",JOptionPane.PLAIN_MESSAGE);
                setVisible(false);
                ConfirmRecipient mr = new ConfirmRecipient(em,id,RName,Hname);
            }
            connection.close();
        } }
                        catch (Exception excep) {
            System.out.println(excep);
        }
    }

    if (ae.getSource() == b2) {  //reset
        t1.setText("");                         
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        t7.setText("");
    }
}
public static void main(String args[]) {
recipient r = new recipient(0);


}
}
