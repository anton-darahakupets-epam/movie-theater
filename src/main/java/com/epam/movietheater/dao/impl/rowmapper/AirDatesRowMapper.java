package com.epam.movietheater.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

public class AirDatesRowMapper implements RowMapper<LocalDateTime>{

	@Override
	public LocalDateTime mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getTimestamp("air_date").toLocalDateTime();
	}

}
