package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import Model.Registration;
import Util.JDBCUtil;

public class RegistrationDAO {
	public static boolean IsExist(int course_id,int student_id, Connection c) {
		boolean kq=false;
			String sql="SELECT reg_id FROM dkhpDB.registration WHERE student_id="+student_id+" AND course_id="+course_id;
			try {
				Statement st=c.createStatement();
				ResultSet rs=st.executeQuery(sql);
				kq=rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return kq;
	}
	public static String insert(int course_id,int student_id) {
		Connection c=JDBCUtil.getConnection();
		String kq="error";
		if(c != null) {
			if(IsExist(course_id, student_id,c)) kq="added";
			else {
				String sql1 = "INSERT INTO dkhpDB.registration(course_id,student_id) VALUES(" + course_id + ","
						+ student_id + ")";
				String sql2="UPDATE dkhpDB.course SET DaDK=DaDK+1 WHERE course_id="+course_id+" AND DaDK<SiSo";
				try {
					c.setAutoCommit(false);
					Statement st = c.createStatement();
					if(st.executeUpdate(sql1)>0) {
						if(st.executeUpdate(sql2)>0) {
							kq ="success";
							c.commit();
						}
						else kq="full";
					}
				} catch (SQLException e) {
					try {
						c.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}
		}
		JDBCUtil.closeConnection(c);
		return kq;
	}
	public static boolean delete(int student_id, int course_id) {
		Connection c=JDBCUtil.getConnection();
		boolean kq=false;
		if(c!=null) {
			String sql1="DELETE FROM dkhpDB.registration WHERE student_id="+student_id+" AND course_id="+course_id;
			String sql2="UPDATE dkhpDB.course SET DaDK=DaDK-1 WHERE course_id="+course_id;
			try {
				c.setAutoCommit(false);
				Statement st=c.createStatement();
				st.executeUpdate(sql1);
				st.executeUpdate(sql2);
				c.commit();
				kq=true;
			} catch (SQLException e) {
				try {
					c.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
		JDBCUtil.closeConnection(c);
		return kq;
	}
}
