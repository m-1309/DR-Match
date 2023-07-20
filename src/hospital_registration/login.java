package hospital_registration;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class login extends JFrame implements ActionListener {

	    Connection c1;
	    PreparedStatement pst;
	    ResultSet rs;

	    JLabel l1, l2, l3, l4,l5;
	    ImageIcon i1;
	    JTextField t1;
	    JPasswordField pf;
	    JButton bt1, bt2;

	    public login() {
	    	
	    	try{
	    		
	    	
	        getContentPane(); 
	        
	        setContentPane(new JLabel(new ImageIcon(getClass().getResource("bgr.png"))));
	        l1 = new JLabel("Hospital DataBase Access");
	        l1.setBounds(120, 10, 200, 40);
	      
	        add(l1);

	        l2 = new JLabel("Username");
	        l2.setBounds(170, 70, 100, 20);
	        add(l2);

	        t1 = new JTextField();
	        t1.setBounds(250, 70, 150, 20);
	        add(t1);

	        l4 = new JLabel("Password");
	        l4.setBounds(170, 110, 100, 20);
	        add(l4);

	        pf = new JPasswordField();
	        pf.setBounds(250, 110, 150, 20);
	        add(pf);


	        try 
	        {
	        	i1=new ImageIcon(getClass().getResource("drLogo.jpg"));
	        
	        l3 = new JLabel(i1);
	        l3.setBounds(30, 50, 100, 100);
	        add(l3);
	        }
	        catch(Exception e){
	        	System.out.println("Image not found");
	        	
	        };
	        bt1 = new JButton("Login");
	        bt1.setBounds(160, 160, 75, 20);
	        add(bt1);

	        bt2 = new JButton("Reset");
	        bt2.setBounds(250, 160, 75, 20);
	        add(bt2);

	        bt1.addActionListener(this);
	        bt2.addActionListener(this);

	        setSize(450, 250);
	        setTitle("Login");
	        setVisible(true);
	        setResizable(false);
	        setLocation(450, 250);
	        
	    	}
	    	catch(Exception e2) {
	    		System.out.println(e2);
	    	}
	    }

	    public void actionPerformed(ActionEvent ae) {
	        if (ae.getSource() == bt1) {
	            try {

	                String un = t1.getText();
	                String pwd = pf.getText();

	                Class.forName("com.mysql.jdbc.Driver").newInstance();
	                c1 = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
	                if (un.equals("")) {
	                    JOptionPane.showMessageDialog(this, "Username Required");
	                } else if (pwd.equals("")) {
	                    JOptionPane.showMessageDialog(this, "Password Required");
	                } else {
	                    pst = c1.prepareStatement("Select * from login1 where Username=? and Password=?");
	                    pst.setString(1, un);
	                    pst.setString(2, pwd);
	                    rs = pst.executeQuery();
	                    
	                    if (rs.next()) {
	                        JOptionPane.showMessageDialog(this, "Username and Password Accepted","Accepted",JOptionPane.PLAIN_MESSAGE);
	                        setVisible(false);
	                      about ab = new about(un);
	                    } else {
	                        JOptionPane.showMessageDialog(this, "Username and Password Not Accepted");
	                        setVisible(false);
	                    }
	                    
	                }
	            } catch (Exception e) {
	                System.out.println("error is " + e);
	            }
	        }

	        if (ae.getSource() == bt2) {
	            t1.setText("");                         
	            pf.setText("");
	            t1.requestFocus();
	        }
	    }

	    public static void main(String[] args) {
	        login ls = new login();
	        
	        ls.setDefaultCloseOperation(EXIT_ON_CLOSE);

	    }
	}


