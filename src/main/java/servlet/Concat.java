import java.lang.*;
import java.io.*;
import java.util.*;

import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//David: (1) adds servlet mapping annotation
import javax.servlet.annotation.WebServlet;
@WebServlet( name = "concat", urlPatterns = {"/concat"} )

// twoButtons class
// CONSTRUCTOR: no constructor specified (default)
//
// ***************  PUBLIC OPERATIONS  **********************************
// public void doPost ()  --> prints a blank HTML page
// public void doGet ()  --> prints a blank HTML page
// private void PrintHead (PrintWriter out) --> Prints the HTML head section
// private void PrintBody (PrintWriter out) --> Prints the HTML body with
//              the form. Fields are blank.
// private void PrintBody (PrintWriter out, String lhs, String rhs, String rslt)
//              Prints the HTML body with the form.
//              Fields are filled from the parameters.
// private void PrintTail (PrintWriter out) --> Prints the HTML bottom
//***********************************************************************

public class Concat extends HttpServlet
{

// Location of servlet.
// David: (5) adds the path of your form submit action
static String Domain  = "";
static String Path    = "";
static String Servlet = "concat";

// Button labels
static String ABC = "String  A B C";
static String ACB = "String  A C B";
static String BAC = "String  B A C";
static String BCA = "String  B C A";
static String CAB = "String  C A B";
static String CBA = "String  C B A";

// Other strings.
static String Style ="https://www.cs.gmu.edu/~offutt/classes/432/432-style.css";

/** *****************************************************
 *  Overrides HttpServlet's doPost().
 *  Converts the values in the form, performs the operation
 *  indicated by the submit button, and sends the results
 *  back to the client.
********************************************************* */
@Override
public void doPost (HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException
{
   String rslt = "";
   String sep = ",";
   String operation = request.getParameter("Operation");
   String strA = request.getParameter("strA");
   String strB = request.getParameter("strB");
   String strC = request.getParameter("strC");
   
   // reverse every string
   if ("Reverse".equals(request.getParameter("reverse")))
   {
      StringBuilder a = new StringBuilder(strA).reverse();
      strA = a.toString();

      StringBuilder b = new StringBuilder(strB).reverse();
      strB = b.toString();

      StringBuilder c = new StringBuilder(strC).reverse();
      strC = c.toString();      
   }
   
   if (operation.equals(ABC))
   {
      rslt = strA + sep + strB + sep + strC;
   }
   else if (operation.equals(ACB))
   {
      rslt = strA + sep + strC + sep + strB;
   }
   else if (operation.equals(BAC))
   {  
      rslt = strB + sep + strA + sep + strC;
   }
   else if (operation.equals(BCA))
   {  
      rslt = strB + sep + strC + sep + strA;
   }
   else if (operation.equals(CAB))
   {  
      rslt = strC + sep + strA + sep + strB;
   }
   else if (operation.equals(CBA))
   {  
      rslt = strC + sep + strB + sep + strA;
   }

   response.setContentType("text/html");
   PrintWriter out = response.getWriter();
   PrintHead(out);
   PrintBody(out, strA, strB, strC, rslt.toString());
   PrintTail(out);
}  // End doPost

/** *****************************************************
 *  Overrides HttpServlet's doGet().
 *  Prints an HTML page with a blank form.
********************************************************* */
@Override
public void doGet (HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException
{
   response.setContentType("text/html");
   PrintWriter out = response.getWriter();
   PrintHead(out);
   PrintBody(out);
   PrintTail(out);
} // End doGet

/** *****************************************************
 *  Prints the <head> of the HTML page, no <body>.
********************************************************* */
private void PrintHead (PrintWriter out)
{
   out.println("<html>");
   out.println("");

   out.println("<head>");
   out.println("<title>Concatenate Strings</title>");
   out.println("<style>");
   out.println("  body { background-color: #D1F2EB; font-family: sans-serif; }");
   out.println("  div { margin: 0 auto; width: 400px; padding: 2em; border: 2px solid #000; border-radius: 5em; background-color: #FFFFFF; }");
   out.println("</style>");
   //out.println(" <link rel=\"stylesheet\" type=\"text/css\" href=\"" + Style + "\">");
   out.println("</head>");
   out.println("");
} // End PrintHead

/** *****************************************************
 *  Prints the <BODY> of the HTML page with the form data
 *  values from the parameters.
********************************************************* */
private void PrintBody (PrintWriter out, String strA, String strB, String strC, String rslt)
{
   out.println("<body>");
   out.println("<p>");
   out.println("<strong>A simple program to concatenate multiple Strings ! :)</strong>");
   out.println("<br>");
   out.println("you can concatenate the strings in multiple orders, click on any given button");
   out.println("</p>");
   out.print  ("<form method=\"post\"");
   out.println(" action=\"/" + Servlet + "\">");
   out.println("");
   out.println(" <table>");
   out.println("  <tr>");
   out.println("   <td>String A:");
   out.println("   <td><input type=\"text\" name=\"strA\" value=\"" + strA + "\" size=10>");
   out.println("  </tr>");
   out.println("  <tr>");
   out.println("   <td>String B:");
   out.println("   <td><input type=\"text\" name=\"strB\" value=\"" + strB + "\" size=10>");
   out.println("  </tr>");
   out.println("  <tr>");
   out.println("   <td>String C:");
   out.println("   <td><input type=\"text\" name=\"strC\" value=\"" + strC + "\" size=10>");
   out.println("  </tr>");
   out.println("<br>");
   out.println("  <tr>");
   out.println("   <td>Result:");
   out.println("   <td><input type=\"text\" name=\"result\" value=\"" + rslt + "\" size=30 readonly>");
   out.println("  </tr>");
   out.println(" </table>");
   out.println(" <br>");
   out.println(" <br>");
   out.println(" <input type=\"radio\" value='Reverse' name=\"reverse\">");
   out.println(" <label for=\"reverse\">Reverse the Strings !</label>");
   out.println(" <br><br>");
   out.println(" <input type=\"submit\" value=\"" + ABC + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + ACB + "\" name=\"Operation\">");
   out.println(" <br>");
   out.println(" <input type=\"submit\" value=\"" + BAC + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + BCA + "\" name=\"Operation\">");
   out.println(" <br>");
   out.println(" <input type=\"submit\" value=\"" + CAB + "\" name=\"Operation\">");
   out.println(" <input type=\"submit\" value=\"" + CBA + "\" name=\"Operation\">");
   out.println(" <br>");
   out.println(" <input type=\"reset\" value=\"Reset\" name=\"reset\">");
   out.println("</form>");
   out.println("");
   out.println("</body>");
} // End PrintBody

/** *****************************************************
 *  Overloads PrintBody (out,lhs,rhs,rslt) to print a page
 *  with blanks in the form fields.
********************************************************* */
private void PrintBody (PrintWriter out)
{
   PrintBody(out, "", "", "", "");
}

/** *****************************************************
 *  Prints the bottom of the HTML page.
********************************************************* */
private void PrintTail (PrintWriter out)
{
   out.println("");
   out.println("</html>");
} // End PrintTail

}  // End twoButtons
