package org.pastebin.application.message_db_server.service.minio;

import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.pastebin.application.message_db_server.configuration.minio.MinIOProperties;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MinIOService {
    private final MinioClient minioClient;
    private final MinIOProperties minIOProperties;

    public void createBucket() throws Exception {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(minIOProperties.getBUCKET()).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minIOProperties.getBUCKET()).build());
        }
    }

    public void saveToBucket(String hash, InputStream message) throws Exception {
        createBucket();
        minioClient
                .putObject(PutObjectArgs
                        .builder()
                        .stream(message, message.available(), -1)
                        .object(hash)
                        .bucket(minIOProperties.getBUCKET())
                        .build());
    }

    public String getFromBucket(String hash) throws Exception{
        return new String(minioClient
                .getObject(GetObjectArgs
                        .builder()
                        .bucket(minIOProperties.getBUCKET())
                        .object(hash)
                        .build())
                .readAllBytes());
    }

    public void deleteFromBucket(String hash) throws Exception{
        minioClient
                .removeObject(RemoveObjectArgs
                        .builder()
                        .bucket(minIOProperties.getBUCKET())
                        .object(hash)
                        .build());
    }
}
