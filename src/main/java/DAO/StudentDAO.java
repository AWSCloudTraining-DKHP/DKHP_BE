package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Student;
import Util.JDBCUtil;

public class StudentDAO {
	public static boolean insert(Student s) {
		Connection c=JDBCUtil.getConnection();
		boolean kq=false;
		if(c!=null) {
			String sql="INSERT INTO dkhpDB.student(name,mssv) "
			+"VALUES ('"+s.getName()+"','"+s.getMssv()+"')";
			try {
				Statement st=c.createStatement();
				st.executeUpdate(sql);
				kq=true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JDBCUtil.closeConnection(c);
		return kq;
	}
	public static String SelectMssv(Integer student_id) {
		Connection c=JDBCUtil.getConnection();
		String kq=null;
		if(c!=null) {
			String sql="SELECT mssv FROM student WHERE student_id="+student_id;
			try {
				Statement st=c.createStatement();
				ResultSet rs=st.executeQuery(sql);
				if(rs.next()) kq=rs.getString(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JDBCUtil.closeConnection(c);
		return kq;
	}
	public static Student SelectByStudentId(int student_id) {
		Connection c=JDBCUtil.getConnection();
		Student kq=null;
		if(c!=null) {
			String sql="SELECT * FROM student WHERE student_id="+student_id;
			try {
				Statement st=c.createStatement();
				ResultSet rs=st.executeQuery(sql);
				if(rs.next()) kq=new Student(rs.getString(2), rs.getString(3));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JDBCUtil.closeConnection(c);
		return kq;
	}
}
