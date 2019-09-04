package com.aykj.loglink.example;

import com.aykj.loglink.LogLinker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * Create On 2019-08-30 15:39
 *
 * @author mo
 */
@Slf4j
@Component
public class FooService implements SmartLifecycle {

    @Override
    public void start() {
        LogLinker.setRequestId("log_id_xxx");
        log.info("this log with log id {log_id_xxx}");
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }


    public String echo(String s){
        log.info("echo {}", s);
        return s;
    }
}
