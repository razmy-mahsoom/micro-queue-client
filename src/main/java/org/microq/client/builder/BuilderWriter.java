package org.microq.client.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.microq.support.auditor.Chaining;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;

@Configuration
public class BuilderWriter {

    @Autowired
    private Socket socket;

    @Autowired
    private ApplicationContext applicationContext;

    private BufferedWriter bufferedWriter;

    @PostConstruct
    public void sendMessage(){
        Map<String, Chaining> beansOfType = applicationContext.getBeansOfType(Chaining.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,false);

        try {
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        beansOfType.forEach((s, chaining) -> {
                try {
                    String s1 = mapper.writeValueAsString(chaining);
                    bufferedWriter.write(s1);
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
