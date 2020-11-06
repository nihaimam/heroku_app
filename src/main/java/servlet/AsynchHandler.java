//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

//Import Java Libraries
import java.io.*;
import java.util.*;
import java.lang.*;

import java.util.ArrayList;

@WebServlet(name = "Predicate Asynch Serv", urlPatterns = {"/AsynchHandler"})
public class AsynchHandler extends HttpServlet
{
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	// response.setContentType("TEXT/HTML"); // Not needed to respond to ajax
		PrintWriter out = response.getWriter();
	
		ArrayList<Equation> variables = new ArrayList<>();
		ArrayList<Object> equation = new ArrayList<>();
		ArrayList<Object> output = new ArrayList<>();
	
		String var1 = request.getParameter("var1");
		String op = request.getParameter("oper");
		op.replaceAll(" ","");
		String var2 = request.getParameter("v2");
	
		String input = var1 + " " + op + " " + var2;
	
		String inop = op;
	
		boolean validOp = true;
		boolean validVar = true;
		boolean validity = true;
	
		if ((op.equals("&")) || (op.equals("&&")) || (op.equals("and")) || (op.equals("^")) || (op.equals("And")) || (op.equals("AND"))) {
			inop = "and";
		}
		else if ((op.equals("V")) || (op.equals("v")) || (op.equals("|")) || (op.equals("||")) || (op.equals("or")) || (op.equals("Or")) || (op.equals("OR"))) {
			inop = "or";
		}	
		else if ((op.equals("xor")) || (op.equals("Xor")) || (op.equals("XOR")) || (op.equals("x"))) {
			inop = "xor";
		}
		else {
			validOp = false;
			out.println("<script type='text/javascript'>");
			out.println("alert('Invalid operator. Please try again')");
			out.println("location='Logic';");
			out.println("</script>");
		}
	
		Equation temp = new Equation(var1,true, 1);
		variables.add(temp);
		equation.add(temp);
		equation.add(op);
        temp = new Equation(var2,true, 2);
        variables.add(temp);
        equation.add(temp);

		//if (variables.size() > 1) {
        if (!(var1.equals("")) && !(var2.equals(""))) {
			Table table = new Table(variables, equation, inop);
            output = table.constructTable();
        }
        else {
			validVar = false;
			out.println("<script type='text/javascript'>");
			out.println("alert('Invalid variable. Please try again')");
			out.println("location='Logic';");
			out.println("</script>");
		}
		
		//print out everything
		out.println("<p>Asynch Equation:");
		out.println(input);
		out.println("</p>");
		
		out.println("<table>");
		out.println("	<thead><tr>");
		out.println("		<th>Variable 1</th> <th>Variable 2</th> <th>Answer</th>");
		out.println("	</tr></thead>");
		out.println("   <tbody><tr>");
		out.println("   	<th>");		out.print(output.get(0).toString()); out.print("</th>");
		out.println("       <th>");     out.print(output.get(1).toString()); out.print("</th>");
		out.println("       <th>");     out.print(output.get(2).toString()); out.print("</th>");
		out.println("   </tr>");
		out.println("   <tr>");
		out.println("       <th>");     out.print(output.get(3).toString()); out.print("</th>");
		out.println("       <th>");     out.print(output.get(4).toString()); out.print("</th>");
		out.println("       <th>");     out.print(output.get(5).toString()); out.print("</th>");
		out.println("   </tr>");
		out.println("   <tr>");
		out.println("       <th>");     out.print(output.get(6).toString()); out.print("</th>");
		out.println("       <th>");     out.print(output.get(7).toString()); out.print("</th>");
		out.println("       <th>");     out.print(output.get(8).toString()); out.print("</th>");
		out.println("   </tr>");
		out.println("   <tr>");
		out.println("       <th>");     out.print(output.get(9).toString()); out.print("</th>");
		out.println("       <th>");     out.print(output.get(10).toString()); out.print("</th>");
		out.println("       <th>");     out.print(output.get(11).toString()); out.print("</th>");
		out.println("   </tr></tbody>");
		out.println("</table>");
			
		
		out.close();
	}

    // Method doPost - just calls processRequest
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
       processRequest(request, response);
    }

    // Method doGet - just calls processRequest
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
       processRequest(request, response);
    }

}
