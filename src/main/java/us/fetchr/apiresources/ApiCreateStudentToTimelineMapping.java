package us.fetchr.apiresources;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ApiCreateStudentToTimelineMapping 
{
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
