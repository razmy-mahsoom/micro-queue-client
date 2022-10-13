package org.microq.client.config.connection;

import org.microq.support.config.connection.MQConnection;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionConfig {

    @Bean
    @ConditionalOnMissingBean(value = MQConnection.class)
    public MQConnection defaultMQConnection(){
        return new DefaultConnection();
    }
}
