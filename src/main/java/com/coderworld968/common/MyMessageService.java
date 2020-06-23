package com.coderworld968.common;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyMessageService {

    @Autowired
    Repo repo;

    @Transactional
    public void delete4Rollback() {
        repo.execute();
        int i = 1 / 0;
    }


    @Transactional
    public void delete() {
        repo.execute();
        repo.execute();
    }

    public List<Message> findAll() {
        List<Message> messages = repo.queryAll();
        return messages;
    }

}
