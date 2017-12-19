package project.practice.ex5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServerHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServerHome() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		out.println("<html>\n"+
				"  <head>\n" +
				"<title>4SV</title>"+
				"</head>"+
				"<body>"+
				"<form action=\"Upload\" method=\"post\" enctype=\"multipart/form-data\">"+
				"<input type=\"file\" name=\"file\">"+
				"<br>"+
				"<input type=\"submit\" name=\"submit\">"+
			"</form>"+
				
		"</body>"+
		"</html>");
	}

}
