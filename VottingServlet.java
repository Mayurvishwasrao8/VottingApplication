import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@WebServlet("/votepage")
public class VottingServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String candidate = request.getParameter("candidate");
      
        
        PrintWriter pw=response.getWriter();
     
    	PreparedStatement pstmt=null;
		String query="insert into votetable values(?)";
		try {
			pstmt=con.prepareStatement(query);
			
			pstmt.setString(1,candidate);
			
			int count=pstmt.executeUpdate();
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
			pw.print("<h1>"+ "Vote sucessfully");
			pw.print("<br>");
			 RequestDispatcher rd=request.getRequestDispatcher("Logout.html");
			 rd.include(request, response);
			 pw.print("</div>");
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

