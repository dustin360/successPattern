package us.fetchr.mappings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StudentToTimelineMapping 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private long studentId;
	private long timelineId;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public long getTimelineId() {
		return timelineId;
	}
	public void setTimelineId(long timelineId) {
		this.timelineId = timelineId;
	}
	
	

	
}
