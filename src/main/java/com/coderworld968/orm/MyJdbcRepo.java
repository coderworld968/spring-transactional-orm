package com.coderworld968.orm;

import com.coderworld968.common.Message;
import com.coderworld968.common.Repo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyJdbcRepo implements Repo {

    @Autowired
    MyJdbcTemplate jdbcTemplate;

    @Override
    public void execute() {
        jdbcTemplate.execute("delete from message where id = '000001'");
    }

    @Override
    public List<Message> queryAll() {
        String sql = "SELECT * FROM MESSAGE";
        List<Message> messages = jdbcTemplate.query(sql, Message.class);
        return messages;
    }
}
