package com.library.app.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.app.entities.Member;

@Repository
public class MemberDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void addMember(Member member) {
        String sql = "INSERT INTO members (name, email) VALUES (:name, :email)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", member.getName())
                .addValue("email", member.getEmail());
        jdbcTemplate.update(sql, params);
    }

    public Member getMemberById(Long id) {
        String sql = "SELECT * FROM members WHERE id = :id";
        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id),
                new BeanPropertyRowMapper<>(Member.class));
    }

    public void updateMember(Member member) {
        String sql = "UPDATE members SET name = :name, email = :email WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", member.getId())
                .addValue("name", member.getName())
                .addValue("email", member.getEmail());
        jdbcTemplate.update(sql, params);
    }

    public void deleteMember(Long id) {
        String sql = "DELETE FROM members WHERE id = :id";
        jdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    public List<Member> getAllMembers() {
        String sql = "SELECT * FROM members";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class));
    }
}
