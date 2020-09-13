package ru.yastrebova.thebestrest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
//@RunWith(SpringRunner.class)
public class ContextTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void shouldCreateApplicationContext() {
        log.info("Application context successfully created, {}", applicationContext);
    }
}
