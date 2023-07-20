package health_filter;
import ischemic_filter.LinkedList;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import Notification.*;
public class Test1 {
 LinkedList o2,o3;
 int did,rid,count=0;
 int i=0,j=0,k=0,l=0,m=0,n=0;
 int arr6[],arr5[],arr4[],arr3[],arr2[],arr1[];
 int match;
 String dhos,rhos,rname,dname,rem,dem;
 public static int percent(int a, int b)
 {
     float result = 0;
     result = ((b - a) * 100) / a;
  
     return (int)result;
 }
	public Test1(LinkedList o1) {
		o2=o1;
		System.out.println("Test1 Constructor/n List display");
		o2.display();  //Recipient list after ischemic filtering
		System.out.println("Calling filters now");
	Age_filter(o2);
	BloodG_filter();
	BMI_filter();
	//call medical history class
	//linkedlist pass
	med_hist ob=new med_hist(o2);
	o2=ob.res();
	System.out.println("o2 after med hist");
	o2.display();
	patient_status();
	match=survival();
	notify(match);
	}
	public void Age_filter(LinkedList la) {
		int d_age=0;
		int r_age = 0;
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();    
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
	PreparedStatement  ps1 = connection.prepareStatement("select Age from donor");
	
	ResultSet rset1 = ps1.executeQuery();
    while(rset1.next()) {
    	d_age= rset1.getInt("Age"); 
        System.out.println("Age_donor"+d_age);
    }
	      
	LinkedList ptr = la.front;
	
		while(ptr!=null)
		{
			
			
			PreparedStatement ps2=connection.prepareStatement("select Age from recipient where Recipient_Id='"+ptr.info+"'");
			
			ResultSet rset = ps2.executeQuery();
		    
		    while(rset.next())
		    {
		    	r_age= rset.getInt("Age"); 
		    	System.out.println("Recipient Age"+r_age);
		    }
		    
		    if((d_age<=40) &&(r_age<=39 )&&(r_age>=18) )
		    {
		    	 //o3.st_insert(ptr.info);
		    }else if((d_age<=59) &&(d_age>=55) &&(r_age<=59 )&&(r_age>=50)) {
		    	//o3.st_insert(ptr.info);
		    }else if((d_age<=54) &&(d_age>=41) &&(r_age<=49 )&&(r_age>=40)) {
		    	//o3.st_insert(ptr.info);
		    }
		    else {
		    	o2.delete_v(ptr.info);
		    }
		    ptr = ptr.link;
		    }
//		o2=o3;
//		o3.display();
	System.out.println("List after Age_filter");
		o2.display();
		connection.close();
	   }//try end 
	catch (Exception exception) {
		exception.printStackTrace();
	     }
		
	}
	//Age_filter ends
	//Blood Group filter
	
	public void BloodG_filter() 
	{
		String d_bg = "" ; 
		String r_bg="";
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();    
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
				PreparedStatement  ps1 = connection.prepareStatement("select Blood_Group from donor");
				ResultSet rset1 = ps1.executeQuery();
	    while(rset1.next()) {
	    	d_bg= rset1.getString("Blood_Group"); 
	        
	    }
		      
		LinkedList ptr = o2.front;
		
			while(ptr!=null)
			{
				
				String query = "select Blood_Group from recipient where Recipient_Id= "+ptr.info;
				Statement sta = connection.createStatement();
				ResultSet rset = sta.executeQuery(query);
				while(rset.next()) {
			    r_bg= rset.getString("Blood_Group"); 
			    if(d_bg.equals("O")) {
			    	if(r_bg.equals("O") ||r_bg.equals("AB")||r_bg.equals("A")||r_bg.equals("B")) {}
			    	else {o2.delete_v(ptr.info);}
			    }
			    if(d_bg.equals("A")) {
			    	if(r_bg.equals("A") ||r_bg.equals("AB")) {}
			    	else {o2.delete_v(ptr.info);}
			    }
			    if(d_bg.equals("B")) {
			    	if(r_bg.equals("B") ||r_bg.equals("AB")) {}
			    	else {o2.delete_v(ptr.info);}
			    }
			    if(d_bg.equals("AB")) {
			    	if(r_bg.equals("AB")) {}
			    	else {o2.delete_v(ptr.info);}
			    }
				
				}
			    ptr = ptr.link; }
			System.out.println("List after Blood Group filter");
			o2.display();
			
			connection.close();
		   }//try end 
		catch (Exception exception) {
			exception.printStackTrace();
		     }
			
		}//BloodG_fliter ends
	//BMI Filter
	public void BMI_filter() 
	{
		int d_BMI=0;
		int r_BMI = 0;
		int dif=0;
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();    
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
	PreparedStatement  ps1 = connection.prepareStatement("select BMI from donor");

	ResultSet rset1 = ps1.executeQuery();
	while(rset1.next()) {
		d_BMI= rset1.getInt("BMI"); 
	    
	}
	      
	LinkedList ptr = o2.front;

		while(ptr!=null)
		{
			
			
			PreparedStatement ps2=connection.prepareStatement("select BMI from recipient where Recipient_Id='"+ptr.info+"'");
			
			ResultSet rset = ps2.executeQuery();
		    
		    while(rset.next())
		    {
		    	r_BMI= rset.getInt("BMI"); 
		    	//System.out.println("Recipient BMI"+r_BMI);
		    }
		    dif=percent(d_BMI,r_BMI);
		    System.out.println("bMI DIF"+dif);
		    double abdif=Math.abs(dif);
		    if(abdif<=20 )
		    {
		    	count++; //o3.st_insert(ptr.info);
		    }
		    else {
		    	o2.delete_v(ptr.info);
		    }
		    ptr = ptr.link;
		    }
//		o2=o3;
//		o3.display();
	System.out.println("List after BMI Filter");
		o2.display();
		System.out.println("No. of values in list:"+ count);
		connection.close();
		
	   }//try end 
	catch (Exception exception) {
		exception.printStackTrace();
	     }
		
				
			}//BMI_fliter ends
	//Survival Chances
	public int survival() {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();    
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
		String st="(";
		      
		if (n>0) {
			
			for(int f=0;f<n;f++) {
				st=st+arr1[f]+",";
			}	
		}
		else if(m>0) {
			for(int f=0;f<m;f++) {
				st=st+arr2[f]+",";
			}	
		}
		else if(l>0) {
			for(int f=0;f<l;f++) {
				st=st+arr3[f]+",";
			}	
		}
		else if(k>0) {
			for(int f=0;f<k;f++) {
				st=st+arr4[f]+",";
			}	
			
		}
		else if(j>0) {
			for(int f=0;f<j;f++) {
				st=st+arr5[f]+",";
			}	
		}
		else if(i>0) {
			for(int f=0;f<i;f++) {
				st=st+arr6[f]+",";
			}	
		}
		else {
			return 0;
		}
		int l=st.length();
  
		
		String st1= st.substring(0,l-1);
	
		String s2=st1+")";
		
		String que="SELECT Recipient_Id,Survival_Chances from recipient where Recipient_Id IN "+s2+" ORDER BY Survival_Chances LIMIT 1";
		System.out.println("Query:"+que);
		PreparedStatement ps1= connection.prepareStatement(que);
		ResultSet rs=ps1.executeQuery();
		while(rs.next()) {
			int r1 = rs.getInt(1);
			
			return r1;
		}
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 0;
		}
		
	public void notify(int x) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();    
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
			PreparedStatement ps1 = connection.prepareStatement("select Recipient_Id,Recipient_Name,recipient.Hospital_Name from recipient where Recipient_Id='"+x+"'");
			ResultSet rs = ps1.executeQuery();
			System.out.println("Notfication method ");
			while(rs.next())
			{
				rid=rs.getInt(1);
				rname=rs.getString(2);
				rhos=rs.getString(3);
				System.out.println("rid,rname,rhos"+rid+rname+rhos);
				}
			PreparedStatement psm = connection.prepareStatement("select Email from login1 where Hospital_Name='"+rhos+"'");
			ResultSet rs1 = psm.executeQuery();
			while(rs1.next())
			{
				rem=rs1.getString(1);
				System.out.println("Recipient Email: "+rem);
			}
			PreparedStatement psn = connection.prepareStatement("select Donor_Id,Donor_Name,Hospital_Name from donor");
			ResultSet rs2 = psn.executeQuery();
			while(rs2.next())
			{
				did=rs2.getInt(1);
				dname=rs2.getString(2);
				dhos=rs2.getString(3);
				System.out.println("donor_id,donor_name,donor_hospital: "+did+" "+dname+" "+dhos);
			}
			PreparedStatement psb = connection.prepareStatement("select Email from login1 where Hospital_Name='"+dhos+"'");
			ResultSet rs3 = psb.executeQuery();
			//System.out.println("mmmm");
			while(rs3.next())
			{
				dem=rs3.getString(1);
				System.out.println("donor_email"+dem);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		if(x==0)
		{
			No_Match_Notify ob = new No_Match_Notify(dem,did,dname);
		}
		if (x>0)
		{
			System.out.println(dem);
			System.out.println(rem);
			System.out.println(did);
			System.out.println(rid);
			System.out.println(dname);
			System.out.println(rname);
			System.out.println(dhos);
			System.out.println(rhos);
			Match_notify ob = new Match_notify(dem,rem,did,rid,dname,rname,dhos,rhos);
		}
	}
	public void patient_status() {
	    
		LinkedList ptr = o2.front;
		int r_status = 0;
		arr6=new int[count];
		arr5=new int[count];
		arr4=new int[count];
		arr3=new int[count];
		arr2=new int[count];
		arr1=new int[count];
		
		try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();    
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
			while(ptr!=null)
			{
				PreparedStatement ps2=connection.prepareStatement("select Patient_Status from recipient where Recipient_Id='"+ptr.info+"'");
				
				ResultSet rset = ps2.executeQuery();
			    
			    while(rset.next())
			    {
			    	r_status= rset.getInt("Patient_Status"); 
			    
			    if(r_status==6) {
			    arr6[i]=ptr.info;
			    
			    System.out.print("arr6"+ arr6[i]);
			    i++;
			    }
			    else if(r_status==5) {
			    	arr5[j]=ptr.info;
			    	System.out.print("arr5"+ arr5[j]);
			    	j++;
			    }
			    else if(r_status==4) {
			    	arr4[k]=ptr.info;
			    	System.out.print("arr4"+ arr4[k]);
			    	k++;
			    }
			    else if(r_status==3) {
			    	arr3[l]=ptr.info;
			    	System.out.print("arr3"+ arr3[l]);
			    	l++;
			    }
			    else if(r_status==2) {
			    	arr2[m]=ptr.info;
			    	System.out.print("arr2"+ arr2[m]);
			    m++;
			    }
			    
			    else if(r_status==1) {
			    	arr1[n]=ptr.info;
			    	System.out.print("arr1"+ arr1[n]);
			    	n++;
			    }
			    else {}
			    }
			    ptr=ptr.link;
			}
			
			//o3.display();
		
		  //
			System.out.println("survival_filter");
		}//try end 
		catch (Exception exception) {
			exception.printStackTrace();
		     }
		
		}
	
	//Sorting as per Patient status
	//Notification starts
	//Match_notify(String em1,String emr1,int did,int rid,String dnm,String rnm,String dho,String rho)
	//No_Match_notify(em,did,dname) ---if not match found
	
	
}
