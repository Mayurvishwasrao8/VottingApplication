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

@WebServlet("/votecount")
public class VottingCount extends HttpServlet {

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
	   
	              PrintWriter pw=resp.getWriter();
	             
	              
	             
	              PreparedStatement pstmt=null;
	              ResultSet rs;
	              String query="select vote,count(vote) as count from votetable where vote='candidate1' group by vote";
	              pw.print("<style>");
	  			pw.print("  .con{\r\n"
	  					+ "    background-color: white;\r\n"
	  					+ "    border-radius: 10px;\r\n"
	  					+ "    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);\r\n"
	  					+ "    padding: 20px;\r\n"
	  					+ "    text-align: center;\r\n"
	  					+ "  font-family: Arial, sans-serif;\r\n"
	  					+ "    background-color: #f0f0f0;\r\n"
	  					+ "    display: flex;\r\n"
	  					+ "    justify-content: center;\r\n"
	  					+ "    align-items: center;\r\n"
	  					+ "    height: 100vh;\r\n"
	  					+ "    margin: 0; }");
	  			pw.print("</style>");
	              pw.print("<div class=con>");
	              try {
					pstmt=con.prepareStatement(query);
					rs=pstmt.executeQuery();
				  while(rs.next()) {
					  pw.print("<h6>Candidate 1 Vote Count ="+rs.getInt("count")+"\n" );
				  }
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	              String query1="select vote,count(vote) as count from votetable where vote='candidate2' group by vote";
	              try {
					pstmt=con.prepareStatement(query1);
					rs=pstmt.executeQuery();
				  while(rs.next()) {
					  pw.print("<h6>Candidate 2 Vote Count ="+rs.getInt("count") );
				  }
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	              
	       
	        String query2="select vote,count(vote) as count from votetable where vote='candidate3' group by vote";
            try {
				pstmt=con.prepareStatement(query2);
				rs=pstmt.executeQuery();
			  while(rs.next()) {
				  pw.print("<h6>Candidate 3 Vote Count ="+rs.getInt("count") );
			  }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String query3="select vote,count(vote) as count from votetable where vote='candidate4' group by vote";
            try {
				pstmt=con.prepareStatement(query3);
				rs=pstmt.executeQuery();
			  while(rs.next()) {
				  pw.print("<h6>Candidate 4 Vote Count ="+rs.getInt("count") );
			  }
			  pw.print("<br>");
			 RequestDispatcher rd=req.getRequestDispatcher("Logout.html");
			 rd.include(req, resp);
			 pw.print("</div>");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	}
}

