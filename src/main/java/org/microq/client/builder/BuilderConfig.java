package org.microq.client.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.microq.support.annotations.ClientType;
import org.microq.support.annotations.MessageType;
import org.microq.support.auditor.Chaining;
import org.microq.support.auditor.MQMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Configuration
public class BuilderConfig {

    @Autowired
    private Socket socket;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private BufferedWriter bufferedWriter;

    @PostConstruct
    public void sendChainingInfoToMicroQueue(){
        Map<String, Chaining> beansOfType = applicationContext.getBeansOfType(Chaining.class);
        beansOfType.forEach((s, chaining) -> {
                try {
//                    String s1 = mapper.writeValueAsString(chaining);
//                    bufferedWriter.write(s1);
//                    bufferedWriter.newLine();
//                    bufferedWriter.flush();
                    MQMessage message = MQMessage.builder()
                            .messageType(MessageType.CHAIN_CONFIG)
                            .payload(chaining)
                            .messageUUID(UUID.randomUUID().toString())
                            .timestamp(LocalDateTime.now())
                            .build();
                    String s2 = mapper.writeValueAsString(message);
                    bufferedWriter.write(s2);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
    }

}
