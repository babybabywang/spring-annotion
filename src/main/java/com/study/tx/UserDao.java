package com.study.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveCustomer(int id, int code) {
        String sql = "INSERT INTO sys_user(user_id,user_code) VALUES(?,?)";
        jdbcTemplate.update(sql, id, code);
    }
}
