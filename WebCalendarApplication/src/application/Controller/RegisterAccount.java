package application.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.Model.User;

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
		
		/*Benutzerobjekt zusammenstellen und anschließend dem DB Controller übergeben*/
		User newUser = new User();
		
		newUser.email = request.getParameter("email");
		newUser.password_ = request.getParameter("password");
		newUser.first_name = request.getParameter("firstname");
		newUser.last_name = request.getParameter("lastname");
		newUser.adress.street = request.getParameter("street");
		newUser.adress.street_nr = request.getParameter("streetnr");
		newUser.adress.postcode = Integer.parseInt(request.getParameter("postcode"));
		newUser.adress.city = request.getParameter("city");
		
		
		DatabaseController controller = new DatabaseController();
		
		
		if(!controller.isEmailAlreadyinUse(newUser.email))
		{
		if(controller.RegisterUser(newUser))
		{
			response.sendRedirect("Login/login.jsp?reg=true&user="+ newUser.email);
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
