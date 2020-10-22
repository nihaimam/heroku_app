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
		out.println("<h2> Truth Table Generator</h2>");
		out.println("<p><strong>instructions</strong><br><br>please enter two different variables to compare in variable 1 and variable 2.<br><br>if you want to negate one or more of the variables, please add the following in front of the variable input: ! ~<br><br>please enter one of the following operators into the operator field:<br>and ^ & &&<br>or v |<br>xor");
		out.println("</li>");
		out.println("<li>");
		out.println("<label for='variable_1'>Variable 1:</label>");
		out.println("<input type='text' id='v1' name='v1'required>>");
		out.println("</li>");
		out.println("<li>");
		out.println("<label for='operation'>Operator:</label>");
		out.println("<input type='text' id='op' name='op'required>>");
		out.println("</li>");
		out.println("<li>");
		out.println("<label for='variable_2'>Variable 2:</label>");
		out.println("<input type='text' id='v2' name='v2'required>");
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


//-------
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

	
	




}




























