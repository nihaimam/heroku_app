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

import javax.servlet.annotation.WebServlet;
@WebServlet( name = "sort", urlPatterns = {"/sort"} )

// Final Fall 2020 - Sort Strings
// CONSTRUCTOR: no constructor specified (default)
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

public class Sort extends HttpServlet
{

// Location of servlet.
// David: (5) adds the path of your form submit action
static String Domain  = "";
static String Path    = "";
static String Servlet = "sort";

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
   String all = request.getParameter("str");
   String op = request.getParameter("op");

   // convert one big string to a String[]
   String[] arr = all.split("\\W+");
   // convert the String[] to List<String>
   List<String> inArr = Arrays.asList(arr);
   // try to remove duplicates
   List<String> input = new ArrayList<String>();
   for (String element : input) {
      if (!input.contains(element)) {
         input.add(element);
      }
   }


   if (op.equals("A -> Z"))
   {
      input.sort(Comparator.comparing(String::toString));
   }
   else if (op.equals("Z -> A")) 
   {
      input.sort(Comparator.comparing(String::toString ).reversed());
   }

   else if (op.equals("numeric")) 
   {
      //do this
      //In order to simply check the string that it contains only NUMBER use the following code :
      //if (text.matches("[0-9]+"){
      // your operations

   }

   else if (op.equals("length")) 
   {
      //do this
   }

   // we take the sorted arraylist and convert it back to array
   String[] sortedArr = new String[input.size()];
   sortedArr = input.toArray(sortedArr);
    
   // combine all together into one big string
   StringBuffer sb = new StringBuffer();
   for(int i = 0; i < sortedArr.length; i++) {
      sb.append(arr[i]);
      sb.append(", ");
   }
   String final_str = sb.toString();


   response.setContentType("text/html");
   PrintWriter out = response.getWriter();
   PrintHead(out);
   PrintBody(out, final_str);
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
   out.println("<title>Sort them Strings</title>");
   out.println("<style>");
   out.println("  body { background-color: #FFB6C1; font-family:Courier New; ");
   out.println("  h1   { text-align: center; color: #97704F; font-size: 250%}");
   out.println("</style>");
   out.println("</head>");
   out.println("");
} // End PrintHead

/** *****************************************************
 *  Prints the <BODY> of the HTML page with the form data
 *  values from the parameters.
********************************************************* */
private void PrintBody (PrintWriter out, String arr)
{
   out.println("<body>");
   out.println("<h1> <strong>String Sorter </h1>");
   out.println("   <p>a simple program to sort multiple multiple strings !<br>");
   out.println("      <br><br> please enter the strings seperated by space or comma or newline<br>");
   out.println("   </p>");
   out.println("   <form method=\"post\" action=\"/" + Servlet + "\">");
   out.println("      <textarea id=\"str\" name=\"str\" rows='20' cols='79'></textarea>");
   out.println("      <br><br>");
   out.println("      <input type=\"submit\" value=\"A -> Z\" name=\"op\">");
   out.println("      <input type=\"submit\" value=\"Z -> A\" name=\"op\">");
   out.println("      <input type=\"submit\" value=\"numeric\" name=\"op\">");
   out.println("      <input type=\"submit\" value=\"random\" name=\"op\">");
   out.println("   </form>");
   out.println("   <br><p>your sorted strings are:</p>");
   out.println("   <p style=\"font-size: 150%;\"><br><br>" + arr + "</p>");
   out.println("</div>");
   out.println("</body>");
} // End PrintBody

/** *****************************************************
 *  Overloads PrintBody (out,lhs,rhs,rslt) to print a page
 *  with blanks in the form fields.
********************************************************* */
private void PrintBody (PrintWriter out)
{
   PrintBody(out, "");
}

/** *****************************************************
 *  Prints the bottom of the HTML page.
********************************************************* */
private void PrintTail (PrintWriter out)
{
   out.println("");
   out.println("</html>");
} // End PrintTail

}
