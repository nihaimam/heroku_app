import java.io.*;
import java.util.*;
import java.lang.*;

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

import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "LogicToFile", urlPatterns = {"/logison"})
public class LogicToFile extends HttpServlet
{

	public void doGet (HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		response.setContentType("text/html"); // Tells the web container what we're sending back
                PrintWriter out = response.getWriter(); // Make it appear as if we're "writing" to the browser window
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Logic Predicate Persistence</title>");
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
		out.println("<h2 align='center'> Truth Table Generator</h2>");
		out.println("<p align='center'><strong>instructions</strong><br><br>please enter two different variables to compare in variable 1 and variable 2.<br><br>if you want to negate one or more of the variables, please add the following in front of the variable input:<br>! ~<br><br>please enter one of the following operators into the operator field:<br>and ^ & &&<br>or v |<br>xor");
		out.println("</li>");
		out.println("<li>");
		out.println("<label for='variable_1'>Variable 1:</label>");
		out.println("<input type='text' id='v1' name='v1' required>");
		out.println("</li>");
		out.println("<li>");
		out.println("<label for='operation'>Operator:</label>");
		out.println("<input type='text' id='op' name='op' required>");
		out.println("</li>");
		out.println("<li>");
		out.println("<label for='variable_2'>Variable 2:</label>");
		out.println("<input type='text' id='v2' name='v2' required>");
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


	static String RESOURCE_FILE = "entries.json";
	
	public class Entry
	{
		String variable_1;
		String operator;
		String variable_2;
	}

	public class Entries
	{
		List<Entry> entries;
	}
	
	public class EntryManager
	{
		private String filePath = null;
				
		public void setFilePath(String filePath)
		{
			this.filePath = filePath;
		}
		
		public Entries save(String var1, String op, String var2)
		{
			Entries entries = getAll();
			Entry newEntry = new Entry();
			newEntry.variable_1 = var1;
			newEntry.operator = op;
			newEntry.variable_2 = var2;
			entries.entries.add(newEntry);
			try{
				FileWriter fileWriter = new FileWriter(filePath);
				new Gson().toJson(entries, fileWriter);
				fileWriter.flush();
				fileWriter.close();
			}
			catch (IOException ioException)
			{
       				 return null;
      			}
			return entries;
		}
		
		private Entries getAll()
		{
    			Entries entries =  entries = new Entries();
    			entries.entries = new ArrayList();
    			try{
    				File file = new File(filePath);
    				if(!file.exists()){
    					return entries;
    				}
    				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
    				Entries readEntries = new Gson().fromJson(bufferedReader, Entries.class);
    				if(readEntries != null && readEntries.entries != null){
    					entries = readEntries;
    				}
    				bufferedReader.close();
    			}
			catch(IOException ioException){
    			}

    		return entries;
		}
		
		public String getAllAsHTMLTable(Entries entries)
		{
    			StringBuilder htmlOut = new StringBuilder("<table>");
    			htmlOut.append("<tr><th>Variable 1</th><th>Operatio</th><th>Variable 2<th></tr>");
    			if(entries == null || entries.entries == null || entries.entries.size() == 0)
			{
    				htmlOut.append("<tr><td>No entries yet.</td></tr>");
    			}
			else{
    				for(Entry entry: entries.entries){
 					htmlOut.append("<tr><td>"+entry.variable_1+"</td><td>"+entry.operator+"</td><td>"+entry.variable_2+"</td></tr>");
    				}
    			}
    			htmlOut.append("</table>");
    			return htmlOut.toString();
    		}

	}

	

	//---------


/*	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html"); // Tells the web container what we're sending back
                PrintWriter out = response.getWriter(); // Make it appear as if we're "writing" to the browser window

                out.println("<html>");
		out.println("<title>Logic Predicate Persistence</title>");
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
                out.println("<form method=\"post\">");
                out.println("<li>");

out.println("<p> THIS IS POOPY </p>");

                out.println("</li");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");	
	}*/

	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();


		ArrayList<Equation> variables = new ArrayList<>();
		ArrayList<Object> equation = new ArrayList<>();
		ArrayList<Object> output = new ArrayList<>();
		
		
		String var1 = request.getParameter("v1");
		String op = request.getParameter("op");
		op = op.replaceAll(" ","");
		String var2 = request.getParameter("v2");

		String input = var1 + " " + op + " " + var2;

		String inop = op;
		boolean validOp = true;
		boolean validVar = true;
		boolean validity = true;		

		if ((op.equals("&")) || (op.equals("^")) || (op.equals("&&")) || (op.equals("and")) || (op.equals("And")) || (op.equals("AND"))) {
			inop = "and";
		}
		else if ((op.equals("V")) || (op.equals("v")) || (op.equals("|")) || (op.equals("or")) || (op.equals("Or")) || (op.equals("OR"))) {
			inop = "or";
		}
		else if ((op.equals("xor")) || (op.equals("Xor")) || (op.equals("x"))) {
			inop = "xor";
		}
		else {
			validOp = false;
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

		// put values in a container for printing
		Properties newvals = new Properties();
		if (!(output.isEmpty()) && validVar && validOp) {
			newvals.put("tv1", var1);
			newvals.put("tv2", var2);
			newvals.put("tv3", "output");
			newvals.put("zero", output.get(0).toString());
			newvals.put("one", output.get(1).toString());
			newvals.put("two", output.get(2).toString());
			newvals.put("three", output.get(3).toString());
			newvals.put("four", output.get(4).toString());
			newvals.put("five", output.get(5).toString());
                	newvals.put("six", output.get(6).toString());
                	newvals.put("seven", output.get(7).toString());
                	newvals.put("eight", output.get(8).toString());
                	newvals.put("nine", output.get(9).toString());
               		newvals.put("ten", output.get(10).toString());
                	newvals.put("eleven", output.get(11).toString());
		}
		else {
			validity = false;
		}

		
		// *************** JSON ***************
		EntryManager entryManager = new EntryManager();
		entryManager.setFilePath(RESOURCE_FILE);
		Entries newEntries=entryManager.save(var1, op, var2);
		
		

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
		out.println("<p>");
		out.print("<strong>YOU ENTERED:</strong><br><br>");
		if (!validOp) { out.print("invalid operation"); }
		else if (!validVar) { out.print("invalid or missing variables"); }
		else if (!validity) { out.print("invalid logical expression"); }
		else { out.print(input); }
		out.println("<br>");
		out.println("<button onClick=\"location.href='https://swe432servlet.herokuapp.com/Logic'\">Reset</button><br>");
		out.print("</p>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th id='tv1'>");
		if (newvals.getProperty ("tv1") != null) out.print(newvals.getProperty ("tv1"));
		out.print("</th>");
		out.println("<th id='tv2'>");
                if (newvals.getProperty ("tv2") != null) out.print(newvals.getProperty ("tv2"));
                out.print("</th>");
		out.println("<th id='tv3'>");
                if (newvals.getProperty ("tv3") != null) out.print(newvals.getProperty ("tv3"));
                out.print("</th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th id='zero'>");
                if (newvals.getProperty ("zero") != null) out.print(newvals.getProperty ("zero"));
                out.print("</th>");
		out.println("<th id='one'>");
                if (newvals.getProperty ("one") != null) out.print(newvals.getProperty ("one"));
                out.print("</th>");
               	out.println("<th id='two'>");
                if (newvals.getProperty ("two") != null) out.print(newvals.getProperty ("two"));
                out.print("</th>");
                out.println("</tr>");
               	out.println("<tr>");
                out.println("<th id='three'>");
                if (newvals.getProperty ("three") != null) out.print(newvals.getProperty ("three"));
                out.print("</th>");
                out.println("<th id='four'>");
                if (newvals.getProperty ("four") != null) out.print(newvals.getProperty ("four"));
                out.print("</th>");
                out.println("<th id='five'>");
                if (newvals.getProperty ("five") != null) out.print(newvals.getProperty ("five"));
                out.print("</th>");                
		out.println("</tr>");
               	out.println("<tr>");
                out.println("<th id='six'>");
                if (newvals.getProperty ("six") != null) out.print(newvals.getProperty ("six"));
                out.print("</th>");
                out.println("<th id='seven'>");
                if (newvals.getProperty ("seven") != null) out.print(newvals.getProperty ("seven"));
                out.print("</th>");
                out.println("<th id='eight'>");
                if (newvals.getProperty ("eight") != null) out.print(newvals.getProperty ("eight"));
                out.print("</th>");
                out.println("</tr>");
               	out.println("<tr>");
                out.println("<th id='nine'>");
                if (newvals.getProperty ("nine") != null) out.print(newvals.getProperty ("nine"));
                out.print("</th>");
                out.println("<th id='ten'>");
                if (newvals.getProperty ("ten") != null) out.print(newvals.getProperty ("ten"));
                out.print("</th>");
                out.println("<th id='eleven'>");
                if (newvals.getProperty ("eleven") != null) out.print(newvals.getProperty ("eleven"));
                out.print("</th>");
                out.println("</tr>");
		out.println("</table>");
		
		
		out.println("<table>");
		out.println("<tr>");
                out.println("<th>");
		out.println(entryManager.getAllAsHTMLTable(newEntries));
		//out.println("<p> wwwwwwwwwwwwwwwwww </p>");
                out.println("</th>");
                out.println("</tr>");
		out.println("</table>");


		out.println("</body");
		out.println("</html>");

	}	




}




























