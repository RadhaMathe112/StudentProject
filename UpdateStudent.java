package crudoperation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updatelink")
public class UpdateStudent extends HttpServlet {

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("studentid");
		String name=req.getParameter("studentname");
		String stream=req.getParameter("studentstream");
		//parsing
		int sid=Integer.parseInt(id);
		PreparedStatement pstmt=null;
		
		String query="update student_info set student_name=?, student_stream=? where student_id=?";
		int count=0;
		try {
			pstmt=con.prepareStatement(query);
			
			pstmt.setString(1, name);
			pstmt.setString(2, stream);
			pstmt.setInt(3, sid);
			count=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw=resp.getWriter();
		pw.print("<h1>"+count+"Record Updated Successfully"+"</h1>");
	}
}
