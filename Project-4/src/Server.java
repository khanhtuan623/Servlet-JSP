import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import javax.swing.JFrame;

import practice.sv.bai1.*;
import sv.practice.*;
import sv.practice.mysql.Insert;
import sv.practice.mysql.JDBCStatement;

public class Server extends Thread {

	private JFrame frame;
	private ServerSocket serverSocket;
	private ArrayList<practice.sv.bai1.Student> students;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/students";
	static final String TABLE = "class";

	static final String USER = "root";
	static final String PASS = "admin";

	public Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(100000);
	}

	@SuppressWarnings("unchecked")
	public void run() {
		while (true) {
			try {

				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();

				System.out.println("Just connected to " + server.getRemoteSocketAddress());
				DataInputStream input = new DataInputStream(server.getInputStream());
				
				//nhận dữ liêu
				String path = input.readUTF();
				
				// nếu nhận được request, thực hiện gửi dữ liệu
				if(path.equals("RecieveData")){
					students = new ArrayList<>();
					students = (ArrayList<Student>) JDBCStatement.readData();
					
					ObjectOutputStream output = new ObjectOutputStream(server.getOutputStream());
					output.writeObject(students);
					System.out.println("Sent!");
					
					// nếu không thực hiện lưu dữ liệu
				}else{
					ArrayList<Student> st = new ArrayList<Student>();
					
					
					ObjectInputStream objectInput =  new ObjectInputStream(server.getInputStream());
					try {
						st = (ArrayList<Student>) objectInput.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Insert.insertInfo(st);
					System.out.println("Insert done!");
				}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

	public static void main(String[] args) {
		int port = 6066;
		try {
			Thread t = new Server(port);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server window = new Server();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Server() {
		initialize();

	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
