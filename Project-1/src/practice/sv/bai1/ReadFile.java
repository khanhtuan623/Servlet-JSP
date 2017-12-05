package practice.sv.bai1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadFile {
	public static ArrayList<Student>listStudent (String filename){
	try {
		ArrayList<Student> listSt = new ArrayList<Student>();
		FileInputStream fis = new FileInputStream(filename); //đọc dữ liệu theo định dạng byte
		InputStreamReader isr = new InputStreamReader(fis); //chuyển byte sang kí tự
		BufferedReader br = new BufferedReader(isr); //đọc văn bản dựa trên kí tự
		
		//bỏ dòng đầu tiên - header
		String line = br.readLine();//đọc theo dòng
		String [] st;
		
		while ((line = br.readLine()) != null) {
			st = line.split(",");//cắt chuỗi
			listSt.add(new Student(st[0], st[1], st[2], st[3]));
		}	
		//đóng luồng dữ liệu
		br.close();
		isr.close();
		fis.close();
		
		//xuất dữ liệu
		listSt.forEach(st1 -> System.out.println(st1.toString()));
		return listSt;
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	return null;
	}
}
