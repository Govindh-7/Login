package servlets.com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
/* */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
   public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String username=request.getParameter("username");
        String password = request.getParameter("password");
        String rep_password = request.getParameter("rep_password");

        String jdbcURL = "jdbc:mysql://localhost:3306/govi?useSSL=false&serverTimezone=UTC";
        String jdbcUsername = "root";
        String jdbcPassword = "Govindh@7";
        if(!password.equals(rep_password))
        {
        	response.getWriter().println("password not matched");
        	return;
        }
   
       
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

           
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            
         
            String sql = "INSERT INTO users( firstname, lastname, email, username, password) VALUES(?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, firstname);
            st.setString(2, lastname);
            st.setString(3, email);
            st.setString(4, username);
            st.setString(5, password);
            int rows=st.executeUpdate();
            
            if (rows>0) {
            	
            	try {
            		response.getWriter().print("Registered successful!");
            		Thread.sleep(3000);
            	} catch(InterruptedException e)
            	{
            		e.printStackTrace();
            	}
            		
            	response.sendRedirect("shop.html");
            	
             
            } else {
                response.getWriter().print("Registeration not successful!");
            }

           
            
            st.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("Error occurred: " + e.getMessage());
        };
        
	}

}
