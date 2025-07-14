package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Course;
import Util.JDBCUtil;

public class CourseDAO {
	public static boolean insert(Course course) {
		Connection c=JDBCUtil.getConnection();
		boolean kq=false;
		
		if(c!=null) {
			String sql="INSERT INTO dkhpDB.course(MaLop,courseName,SoTC, SiSo) "
				+"VALUES('"+course.getMaLop()+"','"+course.getCourseName()+"',"
				+course.getSoTC()+","+course.getSiSo()+")";
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

	public static ArrayList<Course> SelectByStudentId(int student_id){
		Connection c=JDBCUtil.getConnection();
		ArrayList<Course> kq=null;
		if(c!=null) {
			String sql="SELECT * FROM dkhpDB.course WHERE course_id IN (SELECT course_id FROM dkhpDB.registration WHERE student_id="+student_id+")";
			try {
				Statement st=c.createStatement();
				ResultSet rs=st.executeQuery(sql);
				kq=new ArrayList<>();
				while(rs.next()) {
					kq.add(new Course(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JDBCUtil.closeConnection(c);
		return kq;
	}

	public static ArrayList<Course> SelectAll(){
		Connection c=JDBCUtil.getConnection();
		ArrayList<Course> kq=null;
		if(c!=null) {
			String sql="SELECT * FROM dkhpDB.course";
			try {
				Statement st=c.createStatement();
				ResultSet rs=st.executeQuery(sql);
				kq=new ArrayList<>();
				while(rs.next()) {
					kq.add(new Course(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JDBCUtil.closeConnection(c);
		return kq;
	}
}
