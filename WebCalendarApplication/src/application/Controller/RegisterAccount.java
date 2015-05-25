package application.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterAccount
 */
@WebServlet("/RegisterAccount")
public class RegisterAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterAccount() {
        super();
      
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String password_ = request.getParameter("password");
		String first_name = request.getParameter("firstname");
		String last_name = request.getParameter("lastname");
		String street = request.getParameter("street");
		String street_nr = request.getParameter("streetnr");
		String postcode = request.getParameter("postcode");
		String city = request.getParameter("city");
		
		
		DatabaseController controller = new DatabaseController();
		
		
		if(!controller.isEmailAlreadyinUse(email))
		{
		if(controller.RegisterUser(email, password_, first_name, last_name, street, street_nr,Integer.valueOf(postcode),city))
		{
			response.sendRedirect("Login/login.jsp?reg=true&user="+ email);
		}
		else
		{
			
			response.sendRedirect("Register/createAccount.jsp?reg=false");
		}
		}
		else
		{
			response.sendRedirect("Register/createAccount.jsp?reg=alreadyinuse");
			
		}
		

	}
}
