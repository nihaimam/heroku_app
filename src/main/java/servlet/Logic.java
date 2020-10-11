import javax.servlet.*; // servlet library
import javax.servlet.http.*; // servlet library
import java.io.*;
import javax.servlet.annotation.WebServlet;

// The @WebServletannotation is used to declare a servlet
@WebServlet(name = "Logic Predicate Servlet", urlPatterns = {"/Logic"})

public class Logic extends HttpServlet // Inheriting from HttpServlet makes this a servlet
{

	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html"); // Tells the web container what we're sending back
                PrintWriter out = response.getWriter(); // Make it appear as if we're "writing" to the browser window
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Logic Predicate Servlet</title>");
		out.println("</head>");
		
		out.println("<style>");
		out.println("body { background-color: #EFD4CF; font-family: sans-serif; }");
		out.println("hr { top: 10%; border: none; background-color: white; }");
		out.println("h1{ color: black; text-align: center; top: 3%; }");
		out.println("img{ display: block; margin-left: auto; margin-right: auto; }");
		out.println("box { float: left; width: 400px; background-color: rgba(255, 0, 0, 0.2);");
		out.println("overflow:hidden; padding: 50px; margin-left: 30%; margin-top: 3%; color: black;");
		out.println("box-shadow: 0 0 2px 2px white inset; }");
		out.println("</style>");

		out.println("<body>");
		out.println("<h1> SWE 432 Assignment 5 - </h1>");
		out.println("<br><br>");
		out.println("<p><strong>Predicate Logic</strong><p>");
		out.println("<form method='post' action='https://cs.gmu.edu:8443/offutt/servlet/formHandler'>");
		out.println("<table border=3 style='background-color:#EFD4CF'>");
		out.println("<tr><td>");
		out.println("<table align='center' style='padding:10px'>");
		out.println("<tr>");
		out.println("<h2 align='center'><b>Truth Table Generator<b></h2>");
		out.println("<h3 align='center'>Please enter a logical expression</h3>");
		out.println("<p align='center'>Allowed operators are:<br>! (not)<br>&& (and)<br>|| (or)</p>");
		out.println("<tr>");
		out.println("<td>input:");
		out.println("<td><input type='text' name='input'/>");
		out.println("</tr>");
		out.println("</table>");		
		out.println("<table align='center' style='padding:10px'>");
		out.println("<tr>");
		out.println("<td><input type='submit' name='Print Tables' value='submit'>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</table>");
		out.println("</form>");

		out.println("</body>");
		out.println("</html>");

		out.close();
	}  // end doGet()
	
}  // end Hello
