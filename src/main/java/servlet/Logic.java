import javax.servlet.*; // servlet library
import javax.servlet.http.*; // servlet library
import java.io.*;
import javax.servlet.annotation.WebServlet;

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
		out.println("<form method=\"post\"");
		//out.println("<form method='post' action='https://cs.gmu.edu:8443/offutt/servlet/formHandler'>");
		out.println("<table border=5>");
		out.println("<tr><td>");
		out.println("<table align='center' style='padding:10px'>");
		out.println("<tr>");
		out.println("<h2 align='center'><b>Truth Table Generator<b></h2>");
		out.println("<h3 align='center'>Please enter a logical expression</h3>");
		out.println("<p align='center'>Allowed operators are:<br>! (not)<br>& (and)<br>| (or)</p>");
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
		out.println("</box>");
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
		//out.println("<tr>");
			
		//for (int a = 0; a <= variables.size(); a++){
		//	out.println("<th>");
		//	out.println(output.get(a));
		//	out.println("</th>");
		//}

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
			if (newline == variables.size()){
				newline = 0;
			}
		}

		
		//out.println("</tr>");
		out.println("</table>");
		out.println("</table>");
		out.println("</table>");

                out.println("</box>");
                out.println("</body>");
                out.println("</html>");
		
		

	}

}  // end Hello
