package com.fire.D02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YourClass {
    private static final Logger logger = LoggerFactory.getLogger(YourClass.class);

    public void someMethod() {
        logger.info("这是一个info级别的日志");
        logger.debug("这是一个debug级别的日志");
        logger.error("这是一个error级别的日志");
    }

    public static void main(String[] args) {
        new YourClass().someMethod();
    }
}