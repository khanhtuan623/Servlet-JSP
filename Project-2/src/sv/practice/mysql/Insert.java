package sv.practice.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import practice.sv.bai1.Student;

public class Insert {
	public static void insertInfo(ArrayList<Student> students)
	{
		Connection connection=JDBCConectionMysql.getJDBCConnection();
		try {
			students.stream()
			.forEach(st->{
				Statement statement = null;
				try {
					statement = connection.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String sql="Insert into student(last_name,first_name,birth_day,email) values('"+st.getFirstName()+"','"+st.getLastName()+"','"+st.getBirthDay()+"','"+st.getEmail()+"')";
				try {
					statement.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
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
