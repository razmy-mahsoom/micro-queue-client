package org.microq.client.config;

import org.microq.client.exception.MicroQueueGeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

@Configuration
public class WriteConfig {

    @Autowired
    private Socket socket;

    @Bean
    public BufferedWriter writer() throws MicroQueueGeneralException {
        try {
            return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new MicroQueueGeneralException("Problem connecting to MicroQueue-Dealer");
        }
    }
}
