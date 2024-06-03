package org.pastebin.application.message_db_server.services;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.pastebin.application.message_db_server.configuration.MinIOProperties;
import org.pastebin.application.message_db_server.services.minio.MinIOService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MinIOService minIOService;

    public void save(Integer hash, String message) {

    }
    private InputStream getInputStream(String message){
        return new ByteArrayInputStream(message.getBytes());
    }
}
