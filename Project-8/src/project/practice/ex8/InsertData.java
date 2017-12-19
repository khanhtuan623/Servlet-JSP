package project.practice.ex8;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import practice.sv.bai1.ReadFile;
import practice.sv.bai1.Student;
import sv.practice.mysql.Insert;

/**
 * Servlet implementation class InsertData
 */
public class InsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter(); 
		out.println("<html>\n"+
				"  <head>\n" +
				"<title>4SV</title>"+
				"</head>"+
				"<body>"+
					"<form action=\"ReadData\" method=\"post\" accept-charset=\"utf-8\">"+
					"<input type=\"submit\" name=\"read\" value=\"read\">"+
					"</form>"+
				"</body>"+
				"</html>");
        FileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
            		
                if (!fileItem.isFormField()) {
                    String fileName = fileItem.getName();
                    if (!fileName.equals("")) {
                        String dirUrl = request.getServletContext().getRealPath("") + "files";
                        File dir = new File(dirUrl);

                        if (!dir.exists()) {
                            dir.mkdir();
                        }

                        String fileImg = dirUrl + File.separator + fileName;
                        File file = new File(fileImg);

                        try {
                            fileItem.write(file);
                            out.println("Insert Database Successful!");
                        } catch (Exception e) {
                            System.out.println("Fail!");
                            e.printStackTrace();
                        }
                            List<Student> students = ReadFile.listStudent(dirUrl + File.separator + fileName);
                            	Insert.insertInfo((ArrayList<Student>) students);
                    }
                    else{
                    	out.println("Upload UnSuccessful!");
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
	}

}
