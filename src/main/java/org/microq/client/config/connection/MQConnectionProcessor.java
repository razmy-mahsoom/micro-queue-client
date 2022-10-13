package org.microq.client.config.connection;

import lombok.extern.slf4j.Slf4j;
import org.microq.support.config.connection.MQConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.Socket;

@Configuration
@Slf4j
public class MQConnectionProcessor {

    @Autowired
    private MQConnection connection;

    @PostConstruct
    public void startConnection(){
      log.info("Starting Connection to MQ on host: {}, port: {}",connection.host(),connection.port());
        try {
            Socket socket = new Socket(connection.host(),connection.port());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MQConnection getConnection(){
        return connection;
    }
}
