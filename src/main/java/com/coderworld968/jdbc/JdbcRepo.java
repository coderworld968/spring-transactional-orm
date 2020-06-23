package com.coderworld968.jdbc;

import com.coderworld968.common.Message;
import com.coderworld968.common.Repo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JdbcRepo implements Repo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void execute(){
        jdbcTemplate.execute("delete from message where id = '000001'  ");
    }

    @Override
    public List<Message> queryAll() {
        String sql = "SELECT * FROM MESSAGE";
        RowMapper<Message> rowMapper = new BeanPropertyRowMapper<Message>(Message.class);
        List<Message> messages = jdbcTemplate.query(sql, rowMapper);
        return messages;
    }

}
