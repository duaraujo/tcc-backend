package com.ifam.tccbackend;

import com.ifam.tccbackend.PropertiesFile.FileStorageProperties;
import com.ifam.tccbackend.kafka.OrderProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class TccBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(TccBackendApplication.class, args);

    }

}
