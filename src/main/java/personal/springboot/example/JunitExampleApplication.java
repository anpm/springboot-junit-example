/*
 * Copyright (c) 2019 Thermo Fisher Scientific
 * All rights reserved.
 */


package personal.springboot.example;

import java.net.InetAddress;

import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;


/**
 * TODO: Class description
 */
@SpringBootApplication
public class JunitExampleApplication
{
    private static final Logger log = LoggerFactory.getLogger(JunitExampleApplication.class);

    /**
     * TODO: Method description
     *
     * @param args
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        SpringApplication application = new SpringApplication(JunitExampleApplication.class);
        Environment env = application.run(args).getEnvironment();
        log
        .info("\n----------------------------------------------------------\n\t" + "Application '{}' is running! Access URLs:\n\t"
              + "External: \thttp://{}:{}\n----------------------------------------------------------\n" + "With Timezone: " + TimeZone.getDefault().getDisplayName() + " ("
              + TimeZone.getDefault().getID() + ")", env
              .getProperty("spring.application.name"), InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));
    }
}

