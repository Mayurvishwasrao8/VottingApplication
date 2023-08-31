import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminlink")
public class AdminLogin extends HttpServlet {

		    Connection con;
	        @Override
	        public void init() throws ServletException {
	        	try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookinfo","root","sql123");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
	        @Override
	        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	              String uname=req.getParameter("username");
	              String pass =req.getParameter("password");
	              
	              PrintWriter pw=resp.getWriter();
	            
	              PreparedStatement pstmt=null;
	              ResultSet rs;
	              String query="select username,password from admin where username=?";
	              try {
					pstmt=con.prepareStatement(query);
					pstmt.setString(1, uname);
					rs=pstmt.executeQuery();
				
					String name1=null;
					String pass1=null;
					while(rs.next()) {
						  name1=rs.getString(1);
						  pass1=rs.getString(2);
					
					}
//					 pw.print("<h1>welcome name :"+name1+ " password "+pass1);
					 
						if(uname.equals(name1)&&pass.equals(pass1)) {
							HttpSession hs=req.getSession();
							hs.setAttribute("username",uname);
							pw.print("<h1> Get Votting Record");
							RequestDispatcher rd=req.getRequestDispatcher("votecount");
							rd.include(req, resp);
						  
						}else {
							pw.print("<h1>invalid username and password");
						RequestDispatcher rsd=req.getRequestDispatcher("AdminLogin.html");
						rsd.include(req, resp);
						}	   
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        
	}


