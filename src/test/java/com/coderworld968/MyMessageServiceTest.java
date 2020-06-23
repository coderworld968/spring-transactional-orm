package com.coderworld968;

import com.coderworld968.common.Message;
import com.coderworld968.jdbc.Application;
import com.coderworld968.orm.DSApplication;
import com.coderworld968.common.MyMessageService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DSApplication.class})
//@SpringBootTest(classes = {Application.class})
@ActiveProfiles("local")
@Slf4j
public class MyMessageServiceTest {

    @Autowired
    private MyMessageService messageService;

    @Test
    public void testFind() throws Exception {
        List<Message> all = messageService.findAll();
        all.forEach(m -> {
            log.debug("message:{}", m);
        });
    }

    @Test
    public void testDelete4Rollback() {
        try {
            messageService.delete4Rollback();
        } catch (Exception e) {
            log.error("rollback exception:", e);
        }
        List<Message> all = messageService.findAll();
        Assert.assertEquals(2, all.size());
    }

    @Test
    public void testDelete() {
        List<Message> all = messageService.findAll();
        Assert.assertEquals(2, all.size());
        messageService.delete();
        List<Message> all2 = messageService.findAll();
        Assert.assertEquals(1, all2.size());
    }
}
