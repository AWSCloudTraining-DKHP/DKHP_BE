package Model;

public class Registration {
	private Integer reg_id;
	private Integer course_id;
	private Integer student_id;

	public Registration(Integer course_id, Integer student_id) {
		this.course_id = course_id;
		this.student_id = student_id;
	}

	public Integer getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}

	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}

	public Integer getReg_id() {
		return reg_id;
	}
	
	public void setReg_id(Integer reg_id) {
		this.reg_id = reg_id;
	}
	
}
