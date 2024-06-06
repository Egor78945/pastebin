package org.pastebin.application.message_db_server.configuration.minio;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MinIOProperties {
    private final String BUCKET;
    private final String URL;
    private final String ACCESS_KEY;
    private final String SECRET_KEY;

    public MinIOProperties(@Value("${minio.bucket}") String bucket,
                           @Value("${minio.url}") String url,
                           @Value("${minio.access-key}") String accessKey,
                           @Value("${minio.secret-key}") String secretKey) {
        this.BUCKET = bucket;
        this.URL = url;
        this.ACCESS_KEY = accessKey;
        this.SECRET_KEY = secretKey;
    }
}
