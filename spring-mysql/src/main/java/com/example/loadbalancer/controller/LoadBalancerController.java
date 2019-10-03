package com.example.loadbalancer.controller;

import java.io.PrintWriter;
//import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class LoadBalancerController {
	public static String dbURL = "jdbc:mysql://localhost:3306/transactions";
	public static String username = "root";
	public static String password = "harshad";
	int lastIndexFOrTransaction = 0;
	String insertQuery = "insert into transaction_history( transaction_number,query,parameter1,parameter2,result,transactime)"
            + "values(?,?,?,?,?,?)";
	Connection myConnection;
	/**
	 * This function adds two inputs. 
	 */
	@RequestMapping(value="/add")
	public void addService(HttpServletRequest req, HttpServletResponse res)  throws Exception  {
		int numberOne =  Integer.parseInt(req.getParameter("number1"));
		int numberTwo =  Integer.parseInt(req.getParameter("number2"));
		int result = numberOne + numberTwo;
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		res.addHeader("Access-Control-Max-Age", "1728000");		
		PrintWriter out = res.getWriter(); 
		out.println( result );
	    connectToDatabase();
	    storeResult(result,"Addition",numberOne,numberTwo);
	 }
	/**
	 * This function subtracts two inputs. 
	 */
	@RequestMapping(value="/subtract")
	public void subtractService(HttpServletRequest req, HttpServletResponse res)  throws Exception  {
		int numberOne =  Integer.parseInt(req.getParameter("number1"));
		int numberTwo =  Integer.parseInt(req.getParameter("number2"));
		int result = numberOne - numberTwo;
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		res.addHeader("Access-Control-Max-Age", "1728000");		
		PrintWriter out = res.getWriter(); 
		out.println( result );
		connectToDatabase();
		storeResult(result,"Subtraction",numberOne,numberTwo);
	}
	/**
	 * This function will multiply two inputs. 
	 */
	@RequestMapping(value="/multiply")
	public void multiplyService(HttpServletRequest req, HttpServletResponse res)  throws Exception  {
		int numberOne =  Integer.parseInt(req.getParameter("number1"));
		int numberTwo =  Integer.parseInt(req.getParameter("number2"));
		int result = numberOne * numberTwo;
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		res.addHeader("Access-Control-Max-Age", "1728000");		
		PrintWriter out = res.getWriter(); 
		out.println( result );
		connectToDatabase();
		storeResult(result,"Multiplication",numberOne,numberTwo);
	}
	/**
	 * This function divide two inputs. 
	 */
	@RequestMapping(value="/divide")
	public void divideService(HttpServletRequest req, HttpServletResponse res)  throws Exception  {
		int numberOne =  Integer.parseInt(req.getParameter("number1"));
		int numberTwo =  Integer.parseInt(req.getParameter("number2"));
		int result = numberOne / numberTwo;
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		res.addHeader("Access-Control-Max-Age", "1728000");		
		PrintWriter out = res.getWriter(); 
		out.println( result );
		connectToDatabase();
		storeResult(result,"Division",numberOne,numberTwo);
	}
	/**
	 * This function will calculate sine of an input. 
	 */
	@RequestMapping(value="/sine")
	public void sineService(HttpServletRequest req, HttpServletResponse res)  throws Exception  {
		int numberOne =  Integer.parseInt(req.getParameter("number1"));
		int numberTwo =  Integer.parseInt(req.getParameter("number2"));
		double result =  Math.sin(numberOne);
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		res.addHeader("Access-Control-Max-Age", "1728000");		
		PrintWriter out = res.getWriter(); 
		out.println( result );
		connectToDatabase();
		storeTrigonometricResult(result,"Sine",numberOne,numberTwo);
	}
	/**
	 * This function will calculate cosine of an input. 
	 */
	@RequestMapping(value="/cosine")
	public void cosineService(HttpServletRequest req, HttpServletResponse res)  throws Exception  {
		int numberOne =  Integer.parseInt(req.getParameter("number1"));
		int numberTwo =  Integer.parseInt(req.getParameter("number2"));
		double result =  Math.cos(numberOne);
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		res.addHeader("Access-Control-Max-Age", "1728000");		
		PrintWriter out = res.getWriter(); 
		out.println( result );
		connectToDatabase();
		storeTrigonometricResult(result,"Cosine",numberOne,numberTwo);
	}
	/**
	 * This function will calculate tangent of an input. 
	 */
	@RequestMapping(value="/tan")
	public void tanService(HttpServletRequest req, HttpServletResponse res)  throws Exception  {
		int numberOne =  Integer.parseInt(req.getParameter("number1"));
		int numberTwo =  Integer.parseInt(req.getParameter("number2"));
		double result =  Math.tan(numberOne);
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		res.addHeader("Access-Control-Max-Age", "1728000");		
		PrintWriter out = res.getWriter(); 
		out.println( result );
		connectToDatabase();
		storeTrigonometricResult(result,"Tan",numberOne,numberTwo);
	}
	/**
	 * This function will calculate modulus of an input. 
	 */
	@RequestMapping(value="/mod")
	public void modService(HttpServletRequest req, HttpServletResponse res)  throws Exception  {
		int numberOne =  Integer.parseInt(req.getParameter("number1"));
		int numberTwo =  Integer.parseInt(req.getParameter("number2"));
		int result = numberOne % numberTwo;
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		res.addHeader("Access-Control-Max-Age", "1728000");		
		PrintWriter out = res.getWriter(); 
		out.println( result );
		connectToDatabase();
		storeResult(result,"Modulo",numberOne,numberTwo);
	}
	/**
	 * This function will calculate squareroot of an input. 
	 */
	@RequestMapping(value="/root")
	public void rootService(HttpServletRequest req, HttpServletResponse res)  throws Exception  {
		int numberOne =  Integer.parseInt(req.getParameter("number1"));
		int numberTwo =  Integer.parseInt(req.getParameter("number2"));
		double result = Math.sqrt(numberOne);
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		res.addHeader("Access-Control-Max-Age", "1728000");		
		PrintWriter out = res.getWriter(); 
		out.println( result );
		connectToDatabase();
		storeTrigonometricResult(result,"Squareroot",numberOne,numberTwo);
	}
	/**
	 * This function will calculate square of an input. 
	 */
	@RequestMapping(value="/square")
	public void squareService(HttpServletRequest req, HttpServletResponse res)  throws Exception  {
		int numberOne =  Integer.parseInt(req.getParameter("number1"));
		int numberTwo =  Integer.parseInt(req.getParameter("number2"));
		int result = numberOne * numberOne;
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		res.addHeader("Access-Control-Max-Age", "1728000");		
		PrintWriter out = res.getWriter(); 
		out.println( result );
		connectToDatabase();
		storeResult(result,"Square",numberOne,numberTwo);
	}
	/**
	 * This function will calculate log to base 10 of an input. 
	 */
	@RequestMapping(value="/log10")
	public void log10Service(HttpServletRequest req, HttpServletResponse res)  throws Exception  {
		int numberOne =  Integer.parseInt(req.getParameter("number1"));
		int numberTwo =  Integer.parseInt(req.getParameter("number2"));
		double result = Math.log10(numberOne);
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		res.addHeader("Access-Control-Max-Age", "1728000");		
		PrintWriter out = res.getWriter(); 
		out.println( result );
		connectToDatabase();
		storeTrigonometricResult(result,"Log 10",numberOne,numberTwo);
	}
	
	/**
	 * This function will store the trigonometric operation result in the database.
	 * @param result : result of computation.
	 * @param queryAPI : operation performed.
	 * @param numberOne : first input.
	 * @param numberTwo : second input.
	 */
	private void storeTrigonometricResult(double result, String queryAPI, int numberOne, int numberTwo) throws SQLException {
		PreparedStatement preparedStmt = myConnection.prepareStatement(insertQuery);
		preparedStmt.setInt(1, lastIndexFOrTransaction + 1 );
		preparedStmt.setString(2, queryAPI);
		preparedStmt.setInt(3, numberOne);
		preparedStmt.setInt(4, numberTwo);
		preparedStmt.setDouble(5, result);
		preparedStmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
		preparedStmt.executeUpdate();
	}
	/**
	 * This function will store the arithmetic operation result in the database.
	 * @param result : result of computation.
	 * @param queryAPI : operation performed.
	 * @param numberOne : first input.
	 * @param numberTwo : second input.
	 */
	private void storeResult(int result, String queryAPI,int numberOne, int numberTwo) throws Exception {
		PreparedStatement preparedStmt = myConnection.prepareStatement(insertQuery);
		preparedStmt.setInt(1, lastIndexFOrTransaction + 1 );
		preparedStmt.setString(2, queryAPI);
		preparedStmt.setInt(3, numberOne);
		preparedStmt.setInt(4, numberTwo);
		preparedStmt.setInt(5, result);
		preparedStmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
		preparedStmt.executeUpdate();
	}
	/**
	 * This function will connect to the database and return the last value
	 * of primary key to maintain serial order.
	 */
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
