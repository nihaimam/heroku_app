import java.util.List;
import java.util.ArrayList;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "LogicToFile", urlPatterns = {"/logison"})
public class LogicToFile extends HttpServlet {

  static enum Data {AGE, NAME};
  //static String RESOURCE_FILE = "entries.json";
  static String OperationAdd = "Submit";

  @Override
  public void doGet (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    printHead(out);
    printBody(out, "", "", "");
    printTail(out);
  }

  private void printHead (PrintWriter out)
  {
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Truth Table</title>");
    out.println("<style>");
    out.println("body { background-color: #D1F2EB; font-family: sans-serif; }");
    out.println("table { margin: 0 auto; width: 400px; padding: 2em; border: 2px solid #000; border-radius: 5em; background-color: #FFFFFF; }");
    out.println("td, th { text-align: left; padding: 10px; box-sizing: border-box; border: 2px solid #999; font: 1em sans-serif; }");
    out.println("h1, p { color: black; text-align: center; top: 3%; }");
    out.println("</style>");
    out.println("</head>");
  }

  private void printBody (PrintWriter out, String name, String age, String error)
  {
    out.println("<body onLoad=\"setFocus()\">");
    out.println("<h1>** SWE 432 - Assignment 5 **</h1>");
    out.println("<br>");
    out.println("<ul>");

    out.print  ("<form name=\"logic2file\" method=\"post\"");
    //out.println(" action=\""+Domain+Path+Servlet+"\">");
    out.println("<li>");
    out.println("<p><strong>TRUTH TABLE GENERATOR</strong><br>please enter a variable and an operator <br><br>allowed operators are <br>(and)  ^ / & / && / and<br>(or)   v / | / or<br>(xor)  xor / ⊕<br>(not)	 ! / ~</p><br>");
    out.println("</li>");

    out.println(" <table>");  
    out.println("  <tr>");
    out.println("   <td>Name:</td>");  
    out.println("   <td><input type=\"text\" name=\""+Data.NAME.name()  +"\" value=\""+name+"\" size=30 required></td>");
    out.println("  </tr>");
    out.println("  <tr>");
    out.println("   <td>Age:</td>");
    out.println("   <td><input type=\"text\"  name=\""+Data.AGE.name() +"\" oninput=\"this.value=this.value.replace(/[^0-9]/g,'');\" value=\""  +age+"\" size=3 required></td>");
    out.println("  </tr>");
    out.println(" </table>");

    out.println(" <br>");
    out.println(" <br>");
    out.println(" <input type=\"submit\" value=\"" + OperationAdd
      + "\" name=\"Operation\">");
    out.println(" <input type=\"reset\" value=\"Reset\" name=\"reset\">");
    out.println("</form>");
    out.println("</ul>");
    out.println("</body>");
  }

private void printTail (PrintWriter out){
     out.println("");
     out.println("</html>");
  }

}
