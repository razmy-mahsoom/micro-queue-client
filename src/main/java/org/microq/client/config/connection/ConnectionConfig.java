package org.microq.client.config.connection;

import lombok.extern.slf4j.Slf4j;
import org.microq.support.config.connection.MQConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.IOException;
import java.net.Socket;

@Configuration
@Slf4j
public class ConnectionConfig {

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
}

