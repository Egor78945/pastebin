package org.pastebin.application.message_db_server.services.minio;

import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.pastebin.application.message_db_server.configuration.MinIOProperties;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MinIOService {
    private final MinioClient minioClient;
    private final MinIOProperties minIOProperties;

    @SneakyThrows
    private void createBucket() {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(minIOProperties.getBUCKET()).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minIOProperties.getBUCKET()).build());
        }
    }

    @SneakyThrows
    private void save(String hash, InputStream message) {
        minioClient
                .putObject(PutObjectArgs
                        .builder()
                        .stream(message, message.available(), -1)
                        .object(hash)
                        .bucket(minIOProperties.getBUCKET())
                        .build());
    }
}
