package sv.practice.mysql;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import practice.sv.bai1.ReadFile;
import practice.sv.bai1.Student;

public class Main {

	private JFrame frame;
	private JTextField link;
	private JButton btnBrowse;
	private JButton btnInsert;
	private JButton btnShow;
	private String fileID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		btnBrowse.addActionListener(e -> {

			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null); // hiển thị Open Dialog
			File f = fc.getSelectedFile();
			fileID = f.getAbsolutePath(); // lấy đường dẫn file
			link.setText(fileID); // gán đường dẫn file vào textLink
		});
		btnInsert.addActionListener(e -> {
			List<Student> listStudent = ReadFile.listStudent(fileID);
			for (Student st : listStudent) {
				Student student=new Student(st.getLastName(),st.getFirstName(),st.getBirthDay(),st.getEmail());
				Insert.insertInfo(student);
			}
			
		});

		btnShow.addActionListener(e -> {
			ArrayList<Student>listS=(ArrayList<Student>) JDBCStatement.readData();
			ReadFile.printData(listS);
			
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lbFile = new JLabel("File");
		lbFile.setBounds(41, 72, 61, 16);
		frame.getContentPane().add(lbFile);

		link = new JTextField();
		link.setBounds(120, 67, 130, 26);
		frame.getContentPane().add(link);
		link.setColumns(10);

		btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(262, 67, 117, 29);

		frame.getContentPane().add(btnBrowse);

		btnInsert = new JButton("Insert");

		btnInsert.setBounds(87, 123, 80, 29);
		frame.getContentPane().add(btnInsert);

		btnShow = new JButton("Show");
		btnShow.setBounds(188, 123, 86, 29);
		frame.getContentPane().add(btnShow);
	}

}
