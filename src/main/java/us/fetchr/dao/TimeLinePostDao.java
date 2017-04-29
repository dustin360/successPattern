package us.fetchr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import us.fetchr.dbModels.TimeLinePost;

public interface TimeLinePostDao extends JpaRepository<TimeLinePost, Long>{
	TimeLinePost findByTimelinePost(String timelinePost);
}
