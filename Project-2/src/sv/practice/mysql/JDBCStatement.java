package sv.practice.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import practice.sv.bai1.Student;

public class JDBCStatement {
	public static List<Student> readData(){
		try {
			ArrayList<Student>listS=new ArrayList<Student>();
			Statement statement=JDBCConectionMysql.getJDBCConnection().createStatement();
			String sql="SELECT * FROM internship.student";
			ResultSet rs=statement.executeQuery(sql);
			while(rs.next())
			{
				String lname=rs.getString("last_name");
				String fname=rs.getString("first_name");
				String bd=rs.getString("birth_day");
				String email=rs.getString("email");
				listS.add(new Student(lname, fname, bd, email));
			}
				return listS;
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
