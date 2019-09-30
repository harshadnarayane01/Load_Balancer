package com.example.loadbalancer2.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MySqlApplicationStringManipulation {
	
	public static String dbURL = "jdbc:mysql://localhost:3306/transactions";
	public static String username = "root";
	public static String password = "harshad";
	int lastIndexFOrTransaction = 0;
	String insertQuery = "insert into transaction_history( transaction_number,query,parameter1,parameter2,result,transactime)"
            + "values(?,?,?,?,?,?)";
	Connection myConnection;
	@RequestMapping(value="/concat")
	public void concatService(HttpServletRequest req, HttpServletResponse res)  throws Exception  {
		String stringOne =  req.getParameter("inputOneString");
		String stringTwo =  req.getParameter("inputTwoString");
		String result = stringOne + stringTwo;
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		res.addHeader("Access-Control-Max-Age", "1728000");		
		PrintWriter out = res.getWriter(); 
		out.println( result );
	    connectToDatabase();
	    storeResult(result,"Concatenation",stringOne,stringTwo);
	 }
	
	@RequestMapping(value="/reverse")
	public void reverseService(HttpServletRequest req, HttpServletResponse res)  throws Exception  {
		String stringOne =  req.getParameter("inputOneString");
		String stringTwo =  req.getParameter("inputTwoString");
		// String result = StringBuilder
		StringBuilder sb = new StringBuilder(stringOne).reverse();
		String result = sb.toString();
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		res.addHeader("Access-Control-Max-Age", "1728000");		
		PrintWriter out = res.getWriter(); 
		out.println( result );
	    connectToDatabase();
	    storeResult(result,"Reverse",stringOne,stringTwo);
	 }
	
	@RequestMapping(value="/length")
	public void lengthService(HttpServletRequest req, HttpServletResponse res)  throws Exception  {
		String stringOne =  req.getParameter("inputOneString");
		String stringTwo =  req.getParameter("inputTwoString");
		String result = String.valueOf(stringOne.length());
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		res.addHeader("Access-Control-Max-Age", "1728000");		
		PrintWriter out = res.getWriter(); 
		out.println( result );
	    connectToDatabase();
	    storeResult(result,"Length",stringOne,stringTwo);
	 }
	
	private void storeResult(String result, String queryAPI,String stringOne, String stringTwo) throws Exception {
		PreparedStatement preparedStmt = myConnection.prepareStatement(insertQuery);
		preparedStmt.setInt(1, lastIndexFOrTransaction + 1 );
		preparedStmt.setString(2, queryAPI);
		preparedStmt.setString(3, stringOne);
		preparedStmt.setString(4, stringTwo);
		preparedStmt.setString(5, result);
		preparedStmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
		preparedStmt.executeUpdate();
	}
	
	private void connectToDatabase() {
		try {
			myConnection = DriverManager.getConnection(dbURL, username, password);
			java.sql.Statement myStatement = myConnection.createStatement();
			ResultSet myResultSet = myStatement.executeQuery("Select max(transaction_number) from transaction_history");
			while (myResultSet.next()) {
				lastIndexFOrTransaction = Integer.parseInt(myResultSet.getString(1));
			}
		}
		catch(Exception e) {
		}
	}
}
