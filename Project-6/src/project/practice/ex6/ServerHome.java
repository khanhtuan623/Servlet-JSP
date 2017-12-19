package project.practice.ex6;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServerHome
 */
public class ServerHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServerHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		out.println("<html>\n"+
				"  <head>\n" +
				"<title>4SV</title>"+
				"</head>"+
				"<body>"+
					"<div style=\"margin-right: 500px\">"+
					"<form  style=\"float: left; margin-right: 10px\" action=\"InsertDatabase\" method=\"post\" accept-charset=\"utf-8\" enctype=\"multipart/form-data\">"+
					"<input style=\"float: left;\" type=\"file\" name=\"file\">"+
					"<input type=\"submit\" name=\"upload\" value=\"upload\">"+
					"</form>"+
					"</div>"+
					"<form action=\"ReadData\" method=\"post\" accept-charset=\"utf-8\">"+
					"<input type=\"submit\" name=\"read\" value=\"read\">"+
					"</form>"+
				"</body>"+
				"</html>");
	}

}
