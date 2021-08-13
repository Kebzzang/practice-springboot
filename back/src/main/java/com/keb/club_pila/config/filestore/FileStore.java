package com.keb.club_pila.config.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class FileStore {

    private final AmazonS3 s3;

//    public FileStore(AmazonS3 s3) {
//        System.out.println("Problem comes up here");this.s3 = s3;
//
//    }

    public void save(String path, String fileName, Optional<Map<String, String>> optionalMetaData,
                    InputStream inputStream) //path will be bucketname
   {
       ObjectMetadata metadata=new ObjectMetadata();
       optionalMetaData.ifPresent(map->{
           if(!map.isEmpty()){
               map.forEach(metadata::addUserMetadata);
           }
       });
       try{
           s3.putObject(path, fileName, inputStream, metadata);
       }catch(AmazonServiceException e){
           throw new IllegalStateException("Failed to store file to s3", e);
       }
   }
    public byte[] download(String path, String key) {
        try {
            S3Object object = s3.getObject(path, key);
            return IOUtils.toByteArray(object.getObjectContent());
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download file to s3", e);
        }
    }
}
