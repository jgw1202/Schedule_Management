package com.example.schedule_management.repository;

import com.example.schedule_management.dto.SchedulerResponseDto;
import com.example.schedule_management.entity.Scheduler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateSchedulerRepository implements SchedulerRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateSchedulerRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public SchedulerResponseDto saveScheduler(Scheduler scheduler) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("scheduler").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("password", scheduler.getPassword());
        parameters.put("user_name", scheduler.getUserName());
        parameters.put("contents", scheduler.getContents());
        parameters.put("created_at", scheduler.getCreatedAt());
        parameters.put("updated_at", scheduler.getUpdatedAt());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new SchedulerResponseDto(key.longValue(), scheduler.getUserName(), scheduler.getContents(), scheduler.getCreatedAt(), scheduler.getUpdatedAt());
    }

    @Override
    public List<SchedulerResponseDto> findAllSchedulers(int page, int size) {
        int offset = (page - 1) * size;
        String query = "SELECT * FROM scheduler ORDER BY updated_at DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, schedulerRowMapper(), size, offset);
    }

    @Override
    public int countAllSchedulers() {
        String query = "SELECT COUNT(*) FROM scheduler";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    private RowMapper<SchedulerResponseDto> schedulerRowMapper() {
        return (rs, rowNum) -> new SchedulerResponseDto(
                rs.getLong("id"),
                rs.getString("user_name"),
                rs.getString("contents"),
                rs.getDate("created_at"),
                rs.getDate("updated_at")
        );
    }

    @Override
    public Optional<Scheduler> findSchedulerById(Long id) {
        List<Scheduler> result = jdbcTemplate.query("select * from scheduler where id = ?", schedulerRowMapperV2(), id);
        return result.stream().findAny();
    }

    @Override
    public int updateScheduler(Long id, String userName, String contents) {
        return jdbcTemplate.update("update scheduler set user_name = ?, contents = ?, updated_at = NOW() where id = ?", userName, contents, id);
    }

    private RowMapper<Scheduler> schedulerRowMapperV2() {
        return (rs, rowNum) -> new Scheduler(
                rs.getLong("id"),
                rs.getString("password"),
                rs.getString("user_name"),
                rs.getString("contents"),
                rs.getDate("created_at"),
                rs.getDate("updated_at")
        );
    }

    @Override
    public int deleteScheduler(Long id) {
        return jdbcTemplate.update("delete from scheduler where id = ?", id);
    }
}
