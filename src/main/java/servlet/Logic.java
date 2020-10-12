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

	ArrayList<EquationVariables> variables = new ArrayList<>();
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
		out.println("<input type='text' id='v1' name='variable_1'/>");
		out.println("</li>");
		out.println("<li>");
		out.println("<label for='operation'>Operator:</label>");
		out.println("<input type='text' id='operator' name='operator'/>");
		out.println("</li>");
		out.println("<li>");
		out.println("<label for='variable_2'>Variable 2:</label>");
		out.println("<input type='text' id='v2' name='variable_2'/>");
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
		
		String reqinput = request.getParameter("input");
		String input = reqinput.replaceAll(" ", "");
		input = input.toLowerCase();

		int ctr = 1;

		//loop through the input and store everything in the correct place
		for (int i = 0; i < input.length(); i++) {
			// if the char is a variable
			if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z') {
				EquationVariables tmp = new EquationVariables(input.charAt(i), true, ctr);
				// add the variable to equations
				variables.add(tmp);
				equation.add(tmp);
				ctr = ctr * 2;
			}
			else {
				equation.add(input.charAt(i));
			}
		}

		if (variables.size() > 0){
			//Creates an instance of the truth table class with the proper parameters
			TruthTable table = new TruthTable(variables, equation);
			output = table.constructTable();
		}else{
			out.println("No variables found");
		}

		Properties newvals = new Properties();

		out.println("<html>");
                out.println("<head>");
                out.println("<title>Logic Predicate Servlet</title>");
                out.println("</head>");
		
		out.println("<style>");
                out.println("body { background-color: #EFD4CF; font-family: sans-serif; }");
                out.println("hr { top: 10%; border: none; background-color: white; }");
                out.println("h1{ color: black; text-align: center; top: 3%; }");
                out.println("img{ display: block; margin-left: auto; margin-right: auto; }");
                out.println("box {width: 800px; background-color: rgba(255, 0, 0, 0.2); position: absolute; top: 10%; left: 20%;}");
                out.println("</style>");

		out.println("<body>");
                out.println("<box>");
                out.println("<h1>** SWE 432 - Assignment 5 **</h1>");
                out.println("<br>");
		
		out.println("<table align='center' border = 2>");
		out.println("<tr><td>");
		out.println("<table align='center' style='padding:10px'>");
		out.println("<tr>");
		out.println("<h2 align='center'><b>You entered:<b></h2>");
		out.println("<br>");
		out.println(reqinput);
		out.println("<br>");
		out.println("<br>");
		out.println("<table>");

		int newline = 0;
		int a = 0;
		while (a < output.size()){
			out.println("<tr>");
			while (newline <= variables.size()){
				out.println("<th>");
				out.println(output.get(a));
				out.println("</th>");
				a++;
				newline++;
			}
			out.println("</tr>");
			if (newline >= variables.size()){
				newline = 0;
			}
		}
		
		out.println("</table>");
		out.println("</table>");
		out.println("</table>");

                out.println("</box>");
                out.println("</body>");
                out.println("</html>");
		
		

	}

}  // end 
