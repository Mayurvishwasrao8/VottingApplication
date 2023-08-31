import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@WebServlet("/Registerlink")
public class RegisterSevlet extends HttpServlet {
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email    = request.getParameter("email");
        String phone    = request.getParameter("phone");
        
        PrintWriter pw=response.getWriter();
     
    	PreparedStatement pstmt=null;
		String query="insert into jforce values(?,?,?,?)";
		try {
			pstmt=con.prepareStatement(query);
			
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			pstmt.setString(3,email);
			pstmt.setString(4,phone);
			int count=pstmt.executeUpdate();
			pw.print("<h1>"+ count +" Registration done sucessfully");
			pw.print("<h1>Login your Account");
		    RequestDispatcher rd=request.getRequestDispatcher("UserLogin.html");
		    rd.include(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

