// From "Professional Java Server Programming", Patzer et al.,

// Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

// Import Java Libraries
import java.io.*;
import java.util.Enumeration;

@WebServlet(name = "attributeServlet", urlPatterns = {"/attribute"})
public class AttributeServlet extends HttpServlet
{
public void doGet (HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException
{
   String action = request.getParameter("action");

   if (action != null && action.equals("invalidate"))
   {  // Called from the invalidate button, kill the session.
      // Get session object
      HttpSession session = request.getSession();
      session.invalidate();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      out.println("<html>");
      out.println("<head>");
      out.println(" <title>Session lifecycle</title>");
      out.println("</head>");
      out.println("");
      out.println("<body>");

      out.println("<p>Your session has been invalidated.</P>");

      // Create a link so the user can create a new session.
      // The link will have a parameter builtin
      String lifeCycleURL = "/attribute";
      out.println("<a href=\"" + lifeCycleURL + "?action=newSession\">");
      out.println("Create new session</A>");

      out.println("</body>");
      out.println("</html>");
      out.close();
   }
   else
   {
      // Get session object
      HttpSession session = request.getSession();

      String name   = request.getParameter("attrib_name");
      String val_1  = request.getParameter("attrib_value_1");
      String food   = request.getParameter("attrib_food");
      String val_2  = request.getParameter("attrib_value_2");
      String remove = request.getParameter("attrib_remove");

      if (remove != null && remove.equals("on"))
      {
         if (name != null)
         {
            session.removeAttribute(name);
         }
         if (food != null)
         {
            session.removeAttribute(food);
         }
      }
      else
      {
         if ((name != null && name.length() > 0) && (val_1 != null && val_1.length() > 0))
         {
            session.setAttribute(name, val_1);
         }
         if ((food != null && food.length() > 0) && (val_2 != null && val_2.length() > 0))
         {
            session.setAttribute(food, val_2);
         } 
      }

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      out.println("<html>");
      // no-cache lets the page reload by clicking on the reload link
      out.println("<meta http-equiv=\"Pragma\" content=\"no-cache\">");
      out.println("<head>");
      out.println(" <title>Session lifecycle</title>");
      out.println("</head>");
      out.println("");

      out.println("<body>");
      out.println("<h1><center>Session attributes</center></h1>");

      out.println("Enter name and value of an attribute");

      String url = response.encodeURL("attribute");
      out.println("<form action=\"" + url + "\" method=\"GET\">");
      out.println(" Name: ");
      out.println(" <input type=\"text\" size=\"10\" name=\"attrib_name\">");

      out.println(" Value: ");
      out.println(" <input type=\"text\" size=\"10\" name=\"attrib_value_1\">");

      out.println("<br>");
      out.println(" Food: ");
      out.println(" <input type=\"text\" size=\"10\" name=\"attrib_food\">");

      out.println(" Value: ");
      out.println(" <input type=\"text\" size=\"10\" name=\"attrib_value_2\">");

      out.println(" <br><input type=\"checkbox\" name=\"attrib_remove\">Remove");
      out.println(" <input type=\"submit\" name=\"update\" value=\"Update\">");

      String lifeCycleURL = "/attribute";
      out.print  ("<br><br><a href=\"" + lifeCycleURL + "?action=invalidate\">");
      out.println("Invalidate the session</a>");

      out.println("</form>");
      out.println("<hr>");

      out.println("Attributes in this session:");
      Enumeration e = session.getAttributeNames();
      while (e.hasMoreElements())
      {
         String att_name    = (String) e.nextElement();
         String att_value_1 = (String) session.getAttribute(att_name);
     
         String att_food    = (String) e.nextElement();
         String att_value_2 = (String) session.getAttribute(att_food);

         out.print  ("<br><b>Name:</b> ");
         out.println(att_name);
         out.print  ("<br><b>Value:</b> ");
         out.println(att_value_1);

         out.print  ("<br><b>Food:</b> ");
         out.println(att_food);
         out.print  ("<br><b>Value:</b> ");
         out.println(att_value_2);
      } //end while

      out.println("</body>");
      out.println("</html>");
      out.close();
   } // End else
} // End doGet
} //End  SessionLifeCycle
