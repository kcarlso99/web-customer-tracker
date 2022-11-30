package com.luv2code.testDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uillinois.aits.passwordManager.EncryptedConnectionPool;

/**
 * Servlet implementation class TestDBServlet
 */
@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected EncryptedConnectionPool m_connectionPool = null;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			PrintWriter out = response.getWriter();
			out.println("Connecting to the DB.....");
			
			// Get DB connection
			Connection con = this.getConnection();
			
			out.println("Connection successful!");
			
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		} 
		
	}
	
	private boolean initDatabasePool() {
		System.out.println("Executing initDatabasePool()");
		boolean success = true;

		try {
			m_connectionPool = new EncryptedConnectionPool("HibernateBanPool");

			if (m_connectionPool != null) {
				m_connectionPool.setDriver("oracle.jdbc.driver.OracleDriver");
				m_connectionPool.setUsername("UILLINOIS");
				m_connectionPool.setURL("jdbc:oracle:thin:@bannerdev.admin.uillinois.edu:1521:BANDEV");
				m_connectionPool.setPoolSize(2);

				System.out.println("[AITSApplication] dbManager.getURL():  " + m_connectionPool.getURL());
				System.out.println("[AITSApplication] dbManager.getDriver():  " + m_connectionPool.getDriver());
				System.out.println("[AITSApplication] dbManager.getUsername():  " + m_connectionPool.getUsername());
				System.out.println("[AITSApplication] dbManager.getPoolSize():  " + m_connectionPool.getPoolSize());

				m_connectionPool.setPoolName("HIBERNATE");
				m_connectionPool.initializePool();
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		
		return success;
	}
	
	private Connection getConnection() {
		
		if (m_connectionPool == null) {
			initDatabasePool();
		}
		
		Connection con = null;
		try {
			con = m_connectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}

}
