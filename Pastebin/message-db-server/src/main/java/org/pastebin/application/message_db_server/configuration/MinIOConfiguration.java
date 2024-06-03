package org.pastebin.application.message_db_server.configuration;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIOConfiguration {
    private final MinIOProperties minIOProperties;

    public MinIOConfiguration(MinIOProperties minIOProperties) {
        this.minIOProperties = minIOProperties;
    }

    @Bean
    public MinioClient minioClient(){
        return MinioClient
                .builder()
                .endpoint(minIOProperties.getURL())
                .credentials(minIOProperties.getACCESS_KEY(), minIOProperties.getSECRET_KEY())
                .build();
    }

}
