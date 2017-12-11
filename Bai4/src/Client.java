import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import practice.sv.bai1.*;

public class Client {

	private JFrame frame;
	private JButton btnBrowse;
	private JButton btnUpload;
	private ArrayList<practice.sv.bai1.Student> students;
	private JButton btnRead;
	private String filename;
	private JTextField textLink;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Client() {
		initialize();
		
		// tạo nút Browse
		btnBrowse.addActionListener(e -> {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);	//hiển thị Open Dialog
			File f = fc.getSelectedFile();
			filename = f.getAbsolutePath(); //lấy đường dẫn file
			textLink.setText(filename); //gán đường dẫn file vào textLink
		});

		// nút send
		btnUpload.addActionListener(new ActionListener() {
// SU dung lambda
			@Override
			public void actionPerformed(ActionEvent e) {
				String serverName = "localhost";
				int port = 6066;
				try {
					System.out.println("Connecting to " + serverName + " on port " + port); 
					Socket client = new Socket(serverName, port); //connect vào server

					System.out.println("Just connected to " + client.getRemoteSocketAddress());
					DataOutputStream output = new DataOutputStream(client.getOutputStream());
					output.writeUTF("SendData");
										
					List<practice.sv.bai1.Student> st = new ArrayList<practice.sv.bai1.Student>();
					st = ReadFile.listStudent(filename);
					
					ObjectOutputStream objectOutput = new ObjectOutputStream(client.getOutputStream());
					objectOutput.writeObject(st);
					
					
					client.close(); // đóng kết nối
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		// nút read
		btnRead.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				String serverName = "localhost";
				int port = 6066;
				try {
					System.out.println("Connecting to " + serverName + " on port " + port);
					Socket client = new Socket(serverName, port);

					System.out.println("Just connected to " + client.getRemoteSocketAddress());
					
					DataOutputStream output = new DataOutputStream(client.getOutputStream());
					output.writeUTF("recieveData"); // đưa chuỗi request sang server
					
					ObjectInputStream input = new ObjectInputStream(client.getInputStream()); // lấy dữ liệu từ server
					System.out.println("Reading...");
					try {
						students = (ArrayList<practice.sv.bai1.Student>) input.readObject(); 
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
					
					//in ra màn hình
					ReadFile.printData(students);
					
					client.close(); // đóng kết nối
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblFile = new JLabel("File");
		lblFile.setBounds(61, 85, 34, 14);
		frame.getContentPane().add(lblFile);

		textLink = new JTextField();
		textLink.setBounds(106, 82, 173, 20);
		frame.getContentPane().add(textLink);
		textLink.setColumns(10);

		btnUpload = new JButton("Send");
		btnUpload.setBounds(106, 127, 70, 23);
		frame.getContentPane().add(btnUpload);

		btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(298, 81, 89, 23);
		frame.getContentPane().add(btnBrowse);
		
		btnRead = new JButton("Read");
		btnRead.setBounds(201, 127, 78, 23);
		frame.getContentPane().add(btnRead);

	}
}
