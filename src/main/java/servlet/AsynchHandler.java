//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

//Import Java Libraries
import java.io.*;
import java.util.*;
import java.lang.*;

@WebServlet(name = "Predicate Asynch Serv", urlPatterns = {"/AsynchHandler"})
public class AsynchHandler extends HttpServlet
{
	public void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
   {
//      response.setContentType("TEXT/HTML"); // Not needed to respond to ajax
      PrintWriter out = response.getWriter();

      String Nm  = "StringSoFar";
      out.print("Sorry, I have no suggestions: <FONT COLOR=green>");
      out.print(Nm);
      out.print("</FONT>\n");

      out.close();
   }

    // Method doPost - just calls processRequest
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
       processRequest(request, response);
    }

    // Method doGet - just calls processRequest
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
       processRequest(request, response);
    }

}
