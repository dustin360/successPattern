package us.fetchr.dbModels;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TimeLine")
public class TimeLinePost 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String timelinePost;
	private Timestamp time;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTimelinePost() {
		return timelinePost;
	}
	public void setTimelinePost(String timelinePost) {
		this.timelinePost = timelinePost;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
