package org.microq.client.auditor.template;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.microq.support.annotations.MessageType;
import org.microq.support.auditor.MQMessage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
public abstract class MQTemplate {

    private Socket socket;
    private ObjectMapper mapper;

    public MQTemplate(Socket socket, ObjectMapper mapper) {
        this.socket = socket;
        this.mapper = mapper;
    }

    public void convertAndSend(String interchange, String path, Object object){
        //TODO:: Get the converter and convert the object to json payload
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MQMessage message = MQMessage.builder()
                .messageType(MessageType.PAYLOAD)
                .payload(object.toString())
                .messageUUID(UUID.randomUUID().toString())
                .timestamp(LocalDateTime.now())
                .build();
        log.info("MQMessage: {}", message);
        BufferedWriter bufferedWriter = null;
        try {
             bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             String payload = mapper.writeValueAsString(message);
             log.info("Payload: {}", payload);
                bufferedWriter.write(payload);
                bufferedWriter.newLine();
                bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("Message has been sent to Micro-Queue");

    }
}
