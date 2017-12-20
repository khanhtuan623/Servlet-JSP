package project.practice.ex8;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import practice.sv.bai1.Student;
import sv.practice.mysql.JDBCStatement;

/**
 * Servlet implementation class ReadData
 */
public class ReadData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadData() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Student>listS=(List<Student>) JDBCStatement.readData();
		RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/Show.jsp");
		request.setAttribute("listStudent",listS);
		dispatcher.forward(request, response);
	}

}
