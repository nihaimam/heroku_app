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
@WebServlet(name = "Logic Predicate Servlet - Asynchronous Version", urlPatterns = {"/AsynchLogic"}) 
public class LogicAsynch extends HttpServlet // Inheriting from HttpServlet makes this a servlet
{
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html"); // Tells the web container what we're sending back
                PrintWriter out = response.getWriter(); // Make it appear as if we're "writing" to the browser window
		
		out.println("<html>");
		out.println("<head>");

		out.println("	<title>Logic Predicate Servlet - Asynchronous Version</title>");
		out.println("	<style>");
		out.println("		body { background-color: #D1F2EB; font-family: sans-serif; }");
		out.println("		form { margin: 0 auto; width: 400px; padding: 2em; border: 2px solid #000;");
		out.println("			   border-radius: 5em; background-color: #FFFFFF;}");
		out.println("		ul { list-style: none; padding: 0; margin: 0; }");
		out.println("		li { margin-top: 1em; }");
		out.println("		label { display: inline-block; width: 90px; text-align: right; }");
		out.println("		input{ font: 1em sans-serif; width: 275px; box-sizing: border-box; border: 2px solid #999;");
		out.println("			   background-color: #D1F2EB; }");
		out.println("		h1{ color: black; text-align: center; top: 3%; }");
		out.println("		.button { padding-left: 90px; }");
		out.println("		button { margin-left: 0.5em; }");
		out.println("	</style>");
		
		out.println("	<script type=\"text/JavaScript\" src=\"predicate.js\">");
		out.println("	</script>");
		out.println("	<script>");
		out.println("		function showV1(){ var v1 = document.getElementById('v1').value; document.getElementById('eq_v1').innerHTML = v1;}");
		out.println("       function showOp(){ var op = document.getElementById('op').value; document.getElementById('eq_op').innerHTML = op;}");
		out.println("       function showV2(){ var v2 = document.getElementById('v2').value; document.getElementById('eq_v2').innerHTML = v2;}");
		out.println("		function setFocus(){ document.truthtable.operator.focus();}");
		out.println("	</script>");
		out.println("</head>");


		out.println("<body onLoad=\"setFocus()\">");
		out.println("	<h1>** SWE 432 - Assignment 8 **</h1>");
		out.println("	<br>");
		out.println("	<ul>");
		out.println("	<form action=\"\" name=\"truthtable\">"); //<form method=\"post\">");
		out.println("		<li>");
		out.println("			<h3 align='center'><strong>TRUTH TABLE GENERATOR</strong></h3>");
		out.println("			<p align='center'><b>Instructions:</b></p>");
		out.println("			<p align='center'>Please enter two different variables to compare in <i>Variable 1</i> and <i>Variable 2</i>.");
		out.println("			<br>If you want to negate one or more of the variables,");
		out.println("			please add the following in front of the variable input:");
		out.println("			<br><li align='center'>! ~</p>");
		out.println("		</li>");
		out.println("		<p align='center'>Please enter one of the following operators into the <i>Operator</i> field:</p>");
		out.println("		<p><li align='center'>and ^ & &&</li></p>");
		out.println("		<p><li align='center'>or v |</li></p>");
		out.println("		<p><li align='center'>xor</li></p>");
		out.println("		<li>");
		out.println("			<label for='variable_1'>Variable 1:</label>");
		out.println("			<input type=\"text\" name=\"v1\" id=\"v1\" onkeyup=\"showV1()\"/>");
		out.println("		</li>");
		out.println("		<li>");
		out.println("			<label for='operation'>Operator:</label>");
		out.println("			<input type=\"text\" name=\"op\" id=\"op\" onkeyup=\"showOp()\"/>");
		out.println("		</li>");
		out.println("		<li>");
		out.println("			<label for='variable_2'>Variable 2:</label>");
		out.println("			<input type=\"text\" name=\"v2\" id=\"v2\" onkeyup=\"showV2()\"/>");
		out.println("		</li>");
		out.println("		<li>");
		out.println("			<button type='button' onclick='loadPost()'>Submit</button>");
		out.println("		</li>");
		out.println("		<li>");
		out.println("			<p>Equation: <span id=\"eq_v1\"></span> <span id=\"eq_op\"></span> <span id=\"eq_v2\"></span></p>");
		out.println("			<p>Truth Table: <span id='table'> </span></p>");
		out.println("			<br><br>");
		out.println("			<p>Visit our Git Hub for this project: <a href='https://github.com/etracie/swe432_Assign5_NTB.git'>GitHub Link</a></p>");
		out.println("			<p><strong>Contribution Summary</strong><br>Tori, Niha, Beth : We all did stuff</p>");
		out.println("		</li>");
		out.println("	</ul>");
		out.println("	</form>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}  // end doGet()


}  // end 
