package org.microq.client.auditor.template;

import java.net.Socket;

public abstract class MQTemplate {

    private Socket socket;

    public MQTemplate(Socket socket) {
        this.socket = socket;
    }

    public void convertAndSend(String interchange, String path, Object object){
        //TODO:: Get the converter and convert the object to json payload
        System.out.println("Message sending to interchange = "+interchange+" path = "+path+ " Object = "+ object.toString());
    }
}
