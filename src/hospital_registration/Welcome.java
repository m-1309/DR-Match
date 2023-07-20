package hospital_registration;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class Welcome extends JFrame implements ActionListener {
	
ImageIcon i1;	
JLabel l1,l2;
JButton b1,b2;
	
public Welcome(){
	   
	        i1=new ImageIcon(getClass().getResource("drLogo.jpg"));
	        l2=new JLabel(i1);
	        l2.setBounds(130,20,200,200);
	        l1=new JLabel("WELCOME TO DONOR-RECIPIENT MATCH PORTAL");
	        l1.setBounds(50, 10, 400, 80);
	        Font font = new Font("", Font.BOLD,14);
	        l1.setFont(font);
	        b1 = new JButton("SIGN UP");
	        b2 = new JButton("LOGIN");
	        b1.addActionListener(this); //this means current object
	        b2.addActionListener(this);
	        
	        b1.setBounds(50, 200, 120, 40);
	        b2.setBounds(300, 200, 120, 40);
	        add(l1);
	        add(b1);
	        add(b2);
	        add(l2);
	        
	        setTitle("DR MATCH");
			
	        setSize(470, 300) ;
	        setLayout(null);
	        setVisible(true);
	        setLocation(450, 200);
	        Color clr = new Color(153,204,153);
	        getContentPane().setBackground(clr);
	    }
public void actionPerformed(ActionEvent ae) {
    if (ae.getSource()==b2)
    {
    	 login ls = new login();
    	 setVisible(false);
         }
    if(ae.getSource()== b1)
    {
    	signup sp = new signup();
    	setVisible(false);
    }
}
public static void main(String args[]) {
	Welcome w1 = new Welcome();
	w1.setDefaultCloseOperation(EXIT_ON_CLOSE);
}
	}
