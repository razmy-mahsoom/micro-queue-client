package org.microq.client.config.connection;

import org.microq.support.config.connection.MQConnection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@Qualifier("defaultConnection")
public class DefaultConnection implements MQConnection {
    @Value("${micro-q-config.connection.username}")
    private String username;
    @Value("${micro-q-config.connection.password}")
    private String password;
    @Value("${micro-q-config.connection.host}")
    private String host;
    @Value("${micro-q-config.connection.port}")
    private Integer port;
    @Override
    public String username() {
        return username;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String host() {
        return host;
    }

    @Override
    public Integer port() {
        return port;
    }
}
