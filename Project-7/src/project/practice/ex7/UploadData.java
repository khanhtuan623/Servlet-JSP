package project.practice.ex7;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

/**
 * Servlet implementation class InsertData
 */
public class UploadData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadData() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter(); 
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);//check upload file
		if (!isMultipartContent) { //upload file Unsuccessful
			out.println("You are not trying to upload<br/>"); 
			return;
		}
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
                            System.out.println("Upload Successful!");
                        } catch (Exception e) {
                            System.out.println("Fail!");
                            e.printStackTrace();
                        }

                            List<Student> students = ReadFile.listStudent(dirUrl + File.separator + fileName);
                           RequestDispatcher disP=request.getRequestDispatcher("/jsp/Show.jsp");
                           request.setAttribute("listStudent",students);
                           disP.forward(request, response);
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
