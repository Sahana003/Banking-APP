package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Bank {

	public static void main(String[] args) throws SQLException {
		Scanner s=new Scanner(System.in);
		try {
   		       Class.forName("oracle.jdbc.driver.OracleDriver");

			   String url = "jdbc:oracle:thin:@//localhost:1521/orcl.iiht.tech";

			   String userName = "scott";

			   String password = "tiger";

			   Connection con= DriverManager.getConnection(url,userName,password);
			   if(con!=null) {
				   System.out.println("connected");
			   }
			  else
			  {
				  System.out.println("connected failed");
			  }
			  System.out.println("how many customer u want to input:");
			  int n=s.nextInt();
              int user_choice=2;
			
			 do
			 {
				System.out.println();
				System.out.println("1) Open a new bank account");
				System.out.println("2) Deposit to a bank account");
				System.out.println("3) Withdraw to bank account");
				System.out.println("4) Print account balance");
				System.out.println("5) Quit");
				System.out.println();
				System.out.println("Enter choice [1-5]");
				user_choice=s.nextInt();
				switch(user_choice) {
				       case 1:
				    	      System.out.println("Enter a customer name");
				    	      String cn=s.next();
				    	      System.out.println("Enetr a opening balance");
				    	      double d=s.nextDouble();
				    	      System.out.println("Enter customer phone number");
				    	      int ph=s.nextInt();
				    	      System.out.println("Enter customer address");
				    	      String ca=s.next();
				    	      System.out.println("Enter customer Email address");
				    	      String ce=s.next();				    	   
				              if(con !=null) {
							    	
							    PreparedStatement ps;
								ps=con.prepareStatement("insert into Bankinfo1 values(Bankinfo1_auto_incr.nextval,?,?,?,?,?)");
							    //ps.setInt(1, accountNum);
								ps.setString(1, cn);
								ps.setDouble(2, d);
								ps.setInt(3, ph);
								ps.setString(4, ca);
								ps.setString(5,ce);
								int i=ps.executeUpdate();
								if (i==1) {
									PreparedStatement pt6=con.prepareStatement("select * from Bankinfo1 where CPhoneNum=?");
									pt6.setInt(1, ph);
					    	        ResultSet rs6=pt6.executeQuery();
					    	        //System.out.println("accNum"+" "+" "+"Balance");
					    	        if(rs6.next()) {
					    	        	
					    	        System.out.println("New account opened successfully and get an account number:"+rs6.getLong(1));
					    	        }
								} 
								else
								  System.out.println("invalid insertion");
								}				    	  
				             			    	  
				    	      break;
				      case 2:
				    	   //Connection con1 = null;
				    	     System.out.println("Enter a account number");
			    	         Long an=s.nextLong();
			    	         System.out.println("Enter amount to deposit");
			    	         int b=s.nextInt();
					   
				             PreparedStatement ps1=con.prepareStatement("update Bankinfo1 set Balance=Balance+? where CaccountNum=?");
			    	         ps1.setInt(1, b);
			    	         ps1.setLong(2, an);
			    	         ps1.executeUpdate();
			    	         System.out.println("Deposit successfully");
			    	         Statement st1=con.createStatement();
			    	         ResultSet rs1=st1.executeQuery("select Balance from Bankinfo1 where CaccountNum="+an);
			    	        
			    	         while(rs1.next()) {
			    	        	System.out.println("Balance:"+rs1.getString(1));
			    	         }
			    	         rs1.close();
			    	         break; 				
			    	      
				    case 3://Connection con2=null;
				    	     System.out.println("Enter a account number");
		    	             Long and=s.nextLong();
		    	             System.out.println("Enter amount to withdraw");
		    	             int bb=s.nextInt();
		    	             PreparedStatement ps2=con.prepareStatement("update Bankinfo1 set Balance=Balance-? where CaccountNum=?");
		    	             ps2.setInt(1, bb);
		    	             ps2.setLong(2, and);
		    	             ps2.executeUpdate();
		    	             System.out.println("Withdraw successfully");
		    	             Statement st2=con.createStatement();
			    	         ResultSet rs2=st2.executeQuery("select Balance from Bankinfo1 where CaccountNum="+and);
			    	        
			    	         while(rs2.next()) {
			    	        	System.out.println("Balance:"+rs2.getString(1));
			    	         }
			    	         rs2.close();
		    	             break; 	
				    	    
				     case 4://Connection con4=null;
				    	     System.out.println("Enter a account number");
			    	         Long anum=s.nextLong();
			    	         Statement st=con.createStatement();
			    	         ResultSet rs=st.executeQuery("select CAccountNum, Balance from Bankinfo1 where CaccountNum="+anum);
			    	         System.out.println("accNum"+" "+" "+" "+" "+" "+" "+" "+" "+"Balance");
			    	         while(rs.next()) {
			    	        	System.out.println(rs.getString(1)+" "+ " "+" "+" "+" "+rs.getString(2));
			    	         }
			    	         rs.close();
			    	         break;				     
			    	 
				     case 5:System.exit(0);
				    	       default: System.out.println("Invalid option");
				    	       break;
				}
			}
		
			while(user_choice!=5);
		}	catch(Exception e) {
				e.printStackTrace();
			}
		
	  }	
}

	
		
			
	


	