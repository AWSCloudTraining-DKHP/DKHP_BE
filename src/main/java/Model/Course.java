package Model;

import java.time.LocalDate;

public class Course {
	private Integer course_id;
	private String CourseName;
	private String MaLop;
	private Integer SiSo;
	private Integer DaDK;
	private Integer SoTC;
	public Course(Integer course_id,String maLop, String courseName, Integer soTC, Integer siSo, Integer daDK) {
		this.course_id = course_id;
		CourseName = courseName;
		MaLop = maLop;
		SiSo = siSo;
		DaDK = daDK;
		SoTC = soTC;
	}
	public Course(String courseName, String maLop,Integer soTC, Integer siSo) {
		CourseName = courseName;
		MaLop = maLop;
		SiSo = siSo;
		SoTC = soTC;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	public String getMaLop() {
		return MaLop;
	}
	public void setMaLop(String maLop) {
		MaLop = maLop;
	}
	public Integer getSiSo() {
		return SiSo;
	}
	public void setSiSo(Integer siSo) {
		SiSo = siSo;
	}
	public Integer getDaDK() {
		return DaDK;
	}
	public void setDaDK(Integer daDK) {
		DaDK = daDK;
	}
	public Integer getSoTC() {
		return SoTC;
	}
	public void setSoTC(Integer soTC) {
		SoTC = soTC;
	}
	
}
