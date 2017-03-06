package com.epam.movietheater.dao;

import java.time.LocalDateTime;

public interface AirDateDAO {
	
	Long create(LocalDateTime airDate);
	
	void attachToEvent(Long eventId, Long airDateId);
}
