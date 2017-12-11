import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import javax.swing.JFrame;

import practice.sv.bai1.Student;

public class bai3_server extends Thread {

	private JFrame frame;
	private ServerSocket serverSocket;
	private ArrayList<Student> students;
	String line;

	public bai3_server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(100000);
	}

	@SuppressWarnings("unchecked")
	public void run() {
		while (true) {
			try {
				students = new ArrayList<Student>();

				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();
				System.out.println("Just connected to " + server.getRemoteSocketAddress());
				
				ObjectInputStream input = new ObjectInputStream(server.getInputStream());
				students = (ArrayList<Student>) input.readObject();
				System.out.println("Receive!");
				
				for (Student student : students) {
					System.out.println(student);
				}
				
				server.close();
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				bai3_server window = new bai3_server();
				window.frame.setVisible(true);

				int port = 6066;
				Thread t = new bai3_server(port);
				t.start();

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public bai3_server() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
