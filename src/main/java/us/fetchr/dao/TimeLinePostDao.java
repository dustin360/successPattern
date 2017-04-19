package us.fetchr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import us.fetchr.model.TimeLinePost;

public interface TimeLinePostDao extends JpaRepository<TimeLinePost, Long>{
	TimeLinePost findByTimelinePost(String timelinePost);
}
