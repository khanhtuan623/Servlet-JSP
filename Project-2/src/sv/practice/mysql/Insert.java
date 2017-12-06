package sv.practice.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import practice.sv.bai1.Student;

public class Insert {
	public static void insertInfo(Student student)
	{
		Connection connection=JDBCConectionMysql.getJDBCConnection();
		try {
			Statement statement=connection.createStatement();
			String sql="Insert into student(last_name,first_name,birth_day,email) values('"+student.getFirstName()+"','"+student.getLastName()+"','"+student.getBirthDay()+"','"+student.getEmail()+"')";
			statement.executeUpdate(sql);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
