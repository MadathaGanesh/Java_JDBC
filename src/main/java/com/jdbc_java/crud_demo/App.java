package com.jdbc_java.crud_demo;
import java.sql.*;

public class App
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
    	String username="root";
    	String password="root";
    	String url="jdbc:mysql://localhost:3306/jdbc";
    	String Query="select * from Students_Data_Display";
    	
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	Connection conn=DriverManager.getConnection(url, username, password);
    	Statement stmt= conn.createStatement();
    	ResultSet rs=stmt.executeQuery(Query);
    	
    	while(rs.next()) {
    		System.out.println("UserID is : "+rs.getInt("UserID")+", . Student Name is : "+ 
    	rs.getString("StudName") + " . Address is : " +rs.getString("Address"));

    	}
    	
    	// Creating a new table
//    	String createtable="create table NewJDBCTable(EmpId int(10) primary key,EmpName varchar(100) not null,salary int,Company varchar(100))";
//    	Statement st=conn.createStatement();  -- Used for connection
//    	st.executeUpdate(createtable);
    	System.out.println("New Table created ! ");
    	
    	
    	// Inserting values into NewJDBCTable table one by one using Statement
    	Statement st=conn.createStatement();
//    	String insert1="insert into NewJDBCTable(EmpId,EmpName,salary,Company) values(250,'Ramesh',35000,'Rockwell')";
//    	String insert2="insert into NewJDBCTable(EmpId,EmpName,salary,Company) values(251,'Rakesh',45000,'Samsung')";
//    	String insert3="insert into NewJDBCTable(EmpId,EmpName,salary,Company) values(252,'Suresh',40000,'HP')";
//    	st.executeUpdate(insert1);
//    	st.executeUpdate(insert2);
//    	st.executeUpdate(insert3);
    	
    	
    	
//    	// Inserting values into NewJDBCTable table one by one using PreparedStatement
    	String insertMultipleValues="insert into NewJDBCTable(EmpId,EmpName,salary,Company) values (?,?,?,?)";
    	PreparedStatement pstmt=conn.prepareStatement(insertMultipleValues);
//    	// First Row Data
//    		pstmt.setInt(1, 350);
//    		pstmt.setString(2, "Balaram");
//    		pstmt.setInt(3, 43000);
//    		pstmt.setString(4,"Mediatek" );
//    		pstmt.executeUpdate();
//    		
//    		//Second Row Date
//    		pstmt.setInt(1, 351);
//    		pstmt.setString(2, "Faruuk");
//    		pstmt.setInt(3, 53000);
//    		pstmt.setString(4, "Amazon");
//    		pstmt.executeUpdate();
//    		
//    		//Third row data
//    		pstmt.setInt(1, 352);
//    		pstmt.setString(2,"Pooja");
//    		pstmt.setInt(3, 41000);
//    		pstmt.setString(4, "Microsoft");
//    		pstmt.executeUpdate();
//    		
//    		pstmt.addBatch();
//    		System.out.println("Data inserted succesfully at same time !");
    		
    		
    	// Retrieving and Reading only single row at a time using WHERE Condition.	
    		String RetrieveSingleData="select * from NewJDBCTable where EmpId =? ";
    		PreparedStatement Preparedstmt1=conn.prepareStatement(RetrieveSingleData);
    		Preparedstmt1.setInt(1,352);
    		ResultSet rs1= Preparedstmt1.executeQuery();
    		 
    		boolean isFound=false;
    		while(rs1.next()) {
    			isFound=true;
    			System.out.println("EmpID is : "+ rs1.getInt("EmpId") + " . Emp Name is : " +rs1.getString("EmpName")
    			+" . Salary is : "+rs1.getInt("salary") + " .Company is : "+rs1.getString("Company"));
    		}
    		
    		if(!isFound) {
    			System.out.println("No employee Data found with empid ");
    		}
    	
    	
    	// Retrieving all employees data before updating
    	String query4="select * from NewJDBCTable";
    	Statement st4=conn.createStatement();
    	ResultSet rs4=st4.executeQuery(query4);
    	System.out.println("\n \n ****** Values Before Updating ******* ");
    	while(rs4.next()) {
    		System.out.println("Empid is : "+rs4.getInt("EmpID")+ " . Emp Name is : "+ rs4.getString("EmpName")
    		+" .Salary is : "+rs4.getInt("salary")+" . Company is : "+rs4.getString("Company"));		
    		  	}
    	
    	
    	//Deleting empid = 351, from table
    	String deleteQuery="Delete from NewJDBCTable where EmpID=351";
    	Statement deleteStmt=conn.createStatement();
    	int no_of_rows_deleted= deleteStmt.executeUpdate(deleteQuery);
    	if(no_of_rows_deleted >0) {
    		System.out.println("Records Deleted Successfully !");	
    	}else {
    		System.out.println("No matching Rows");
    	}
    		
    		
    	
    	// Updating 352 company from "Mediatek" to "Snapdraggon"
    		String UpdQuery="Update NewJDBCTable set Company=? where EmpID=?";
    		PreparedStatement pstmt5=conn.prepareStatement(UpdQuery);
    		pstmt5.setString(1,"Snapdraggon");
    		pstmt5.setInt(2, 350);
    		int rstmt5=pstmt5.executeUpdate();
    		
        	// Retrieving all employees data after updating
        	System.out.println("\n \n ****** Values After Updating ******* ");
        	Statement stmt5=conn.createStatement();
        	ResultSet rs5=stmt5.executeQuery(query4);
        	while(rs5.next()) {
        		System.out.println("Empid is : "+rs5.getInt("EmpID")+ " . Emp Name is : "+ rs5.getString("EmpName")
        		+" .Salary is : "+rs5.getInt("salary")+" . Company is : "+rs5.getString("Company"));		
        		  	}
        	
        	
        	
        			
    		
    		
        deleteStmt.close();
    	rs.close();
    	rs1.close();
    	st.close();
    	pstmt5.close();
    	Preparedstmt1.close();
    	pstmt.close();
    	conn.close();
    	
    	
    }
}
