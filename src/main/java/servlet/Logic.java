// Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

// Import Java Libraries
import java.io.*;
import java.util.*;
import java.lang.*;

import java.util.ArrayList;

// The @WebServletannotation is used to declare a servlet
@WebServlet(name = "Logic Predicate Servlet", urlPatterns = {"/Logic"})

public class Logic extends HttpServlet // Inheriting from HttpServlet makes this a servlet
{

	ArrayList<Equation> variables = new ArrayList<>();
	ArrayList<Object> equation = new ArrayList<>();
	ArrayList<Object> output = new ArrayList<>();

	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html"); // Tells the web container what we're sending back
                PrintWriter out = response.getWriter(); // Make it appear as if we're "writing" to the browser window
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Logic Predicate Servlet</title>");
		out.println("</head>");
		
		out.println("<style>");
		out.println("body { background-color: #D1F2EB; font-family: sans-serif; }");
		out.println("form { margin: 0 auto; width: 400px; padding: 2em; border: 2px solid #000; border-radius: 5em; background-color: #FFFFFF; }");
		out.println("ul { list-style: none; padding: 0; margin: 0; }");
		out.println("li { margin-top: 1em; }");
		out.println("label { display: inline-block; width: 90px; text-align: right; }");
		out.println("input{ font: 1em sans-serif; width: 275px; box-sizing: border-box; border: 2px solid #999; background-color: #D1F2EB; }");
		out.println("h1{ color: black; text-align: center; top: 3%; }");
		out.println(".button { padding-left: 90px; }");
		out.println("button { margin-left: 0.5em; }");
		out.println("</style>");

		out.println("<body>");
		out.println("<h1>** SWE 432 - Assignment 5 **</h1>");
		out.println("<br>");
		out.println("<ul>");
		out.println("<form method=\"post\">");
		out.println("<li>");
		out.println("<p><strong>TRUTH TABLE GENERATOR</strong><br>please enter a variable and an operator <br><br>allowed operators are <br>(and)  ^ / & / && / and<br>(or)   v / | / or<br>(xor)  xor / ⊕<br>(not)	 ! / ~</p><br>");
		out.println("</li>");
		out.println("<li>");
		out.println("<label for='variable_1'>Variable 1:</label>");
		out.println("<input type='text' id='v1' name='v1'/>");
		out.println("</li>");
		out.println("<li>");
		out.println("<label for='operation'>Operator:</label>");
		out.println("<input type='text' id='op' name='op'/>");
		out.println("</li>");
		out.println("<li>");
		out.println("<label for='variable_2'>Variable 2:</label>");
		out.println("<input type='text' id='v2' name='va2'/>");
		out.println("</li>");
		out.println("<li class='button'>");
		out.println("<button type='submit'>Submit</button>");
		out.println("</li");
		out.println("<li>");
		out.println("<br><br>");
		out.println("<p><strong>CONTRIBUTION SUMMARY</strong><br>Beth, Tori, Niha<br><br>we all did stuff</p>");
		out.println("</li>");
		out.println("</ul>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}  // end doGet()


	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// variables equation output
		
		String var1 = request.getParameter("v1");
		String op = request.getParameter("op");
		String var2 = request.getParameter("v2");
		String input = var1 + " " + op + " " + var2;
		String validOp;
		String validVar;		

		if ((op.equals("&")) || (op.equals("&&")) || (op.equals("and")) || (op.equals("And")) || (op.equals("AND"))) {
			validOp = "and";
		}
		else if ((op.equals("V")) || (op.equals("v")) || (op.equals("|")) || (op.equals("or")) || (op.equals("Or")) || (op.equals("OR"))) {
			validOp = "or";
		}
		else if ((op.equals("xor")) || (op.equals("Xor")) || (op.equals("x"))) {
			validOp = "xor";
		}
		else {
			validOp = "invalid";
		}

                Equation temp = new Equation(var1,true, 1);
                variables.add(temp);
                equation.add(temp);
                equation.add(op);
                temp = new Equation(var2,true, 2);
                variables.add(temp);
                equation.add(temp);


                if (variables.size() > 0) {
                        Table table = new Table(variables, equation, "xor");
                        output = table.constructTable();
                }
                else {
			validVar = "invalid";
		}	

		/*// put values in a container for printing
		Properties newvals = new Properties();
		newvals.put("tv1", var1);
		newvals.put("tv2", var2);
		newvals.put("tv3", "output");
		newvals.put("zero", output.get(0));
		newvals.put("one", output.get(1));
		newvals.put("two", output.get(2));
		newvals.put("three", output.get(3));
		newvals.put("four", output.get(4));
		newvals.put("five", output.get(5));
		newvals.put("six", output.get(6));
		newvals.put("seven", output.get(7));
		newvals.put("eight", output.get(8));
		newvals.put("nine", output.get(9));
		newvals.put("ten", output.get(10));
		newvals.put("eleven", output.get(11));*/


		// *************** HTML ***************
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Truth Table Generator</title>");
		
		out.println("<style>");
		out.println("body { background-color: #D1F2EB; font-family: sans-serif; }");
		out.println("table { margin: 0 auto; width: 400px; padding: 2em; border: 2px solid #000; border-radius: 5em; background-color: #FFFFFF; }");
		out.println("td, th { text-align: left; padding: 10px; box-sizing: border-box; border: 2px solid #999; font: 1em sans-serif; }");
		out.println("h1, p { color: black; text-align: center; top: 3%; }");
		out.println("</style>");
		out.println("</head>");
		
		out.println("<body>");
		out.println("<h1>** SWE 432 - Assignment 5 **</h1>");
		out.println("<br>");
		out.println("<p><strong>YOU ENTERED:</strong><br>");
		out.print(input);
		out.print("</p><br>");
		out.println(output);
		out.println("<table>");
		out.println("<tr>");
		out.println("<th id='tv1'> </th>");
		out.println("<th id='tv2'> </th>");
		out.println("<th id='tv3'> </th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th id='zero'> </th>");
		out.println("<th id='one'> </th>");
               	out.println("<th id='two'> </th>");
                out.println("</tr>");
               	out.println("<tr>");
                out.println("<th id='three'> </th>");
                out.println("<th id='four'> </th>");
                out.println("<th id='five'> </th>");
                out.println("</tr>");
               	out.println("<tr>");
                out.println("<th id='six'> </th>");
                out.println("<th id='seven'> </th>");
                out.println("<th id='eight'> </th>");
                out.println("</tr>");
               	out.println("<tr>");
                out.println("<th id='nine'> </th>");
                out.println("<th id='ten'> </th>");
                out.println("<th id='eleven'> </th>");
                out.println("</tr>");
		out.println("</table>");
		out.println("</body");
		out.println("</html>");

	}

}  // end 
