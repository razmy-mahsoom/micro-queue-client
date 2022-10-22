package org.microq.client.auditor.template;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.Socket;

public class SimpleMQTemplate extends MQTemplate{

    public SimpleMQTemplate(Socket socket, ObjectMapper mapper) {
        super(socket, mapper);
    }
}
