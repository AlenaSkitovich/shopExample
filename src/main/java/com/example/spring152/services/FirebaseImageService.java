package com.example.spring152.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.storage.v2.Bucket;
import com.google.storage.v2.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class FirebaseImageService {
    private Storage storage = StorageOptions.getDefaultInstance().getService();

    public String save(MultipartFile multipartFile) {
        try {
            String imageName = String.valueOf(System.currentTimeMillis());
            BlobId blobId = BlobId.of("spring152-a4782.appspot.com", imageName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(multipartFile.getContentType())
                    .build();
            storage = StorageOptions.newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("spring152-a4782-firebase-adminsdk-683of-d00a75b6d4.json")))
                    .build()
                    .getService();
            storage.create(blobInfo, multipartFile.getInputStream());
            return imageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //https://firebasestorage.googleapis.com/v0/b/spring152-a4782.appspot.com/o/1667133640877?alt=media&token=9f09c7dc-8c36-4a95-856e-139fe1a1114a
    public String getImageUrl(String fileName) {
        return "https://firebasestorage.googleapis.com/v0/b/spring152-a4782.appspot.com/o/" + fileName + "?alt=media&token=9f09c7dc-8c36-4a95-856e-139fe1a1114a";
    }
}
