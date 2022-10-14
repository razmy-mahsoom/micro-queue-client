package org.microq.client.config.connection;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.microq.support.auditor.Chaining;
import org.microq.support.auditor.Interchange;
import org.microq.support.config.connection.MQConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

@Configuration
@Slf4j
public class ConnectionConfig {

    @Autowired
    private ApplicationContext applicationContext;


    @Bean("default-mq-connection")
    public MQConnection defaultMQConnection(){
        return new DefaultConnection();
    }

    @Bean
    @DependsOn("default-mq-connection")
    public Socket socket(){
        log.info("Starting Connection to MQ on host: {}, port: {}",defaultMQConnection().host(),defaultMQConnection().port());
        try {
            Socket socket = new Socket(defaultMQConnection().host(), defaultMQConnection().port());
            log.info("Connected to MQ on {}",socket.getLocalSocketAddress());
            return socket;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    public void run(){
        Map<String, Chaining> beansOfType = applicationContext.getBeansOfType(Chaining.class);
        beansOfType.forEach((s, chaining) -> {
            System.out.println("Bean Name "+s);
            Interchange interchange = chaining.getInterchange();
            System.out.println(interchange.getInterchangeName());
        });

    }

}

