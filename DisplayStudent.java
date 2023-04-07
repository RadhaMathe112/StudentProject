package crudoperation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displaylink")
public class DisplayStudent extends HttpServlet {
	
	Connection con;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/1eaj7","root","sql@123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Statement stmt=null;
		ResultSet rs=null;
		PrintWriter pw=resp.getWriter();
		
		String query="select * from student_info";
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			pw.print("<table border='2'>");
			pw.print("<tr>");
			pw.print("<th>STUDENT ID</th>");
			pw.print("<th>STUDENT NAME</th>");
			pw.print("<th>STUDENT STREAM</th>");
			pw.print("<th>STUDENT DOB</th>");
			pw.print("</tr>");
			while(rs.next())
			{
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String stream=rs.getString(3);
				Date dob=rs.getDate(4);
				pw.print("<tr>");
				pw.print("<td>"+id+"</td>");
				pw.print("<td>"+name+"</td>");
				pw.print("<td>"+stream+"</td>");
				pw.print("<td>"+dob+"</td>");
				pw.print("</tr>");
			}
			pw.print("<table>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	

