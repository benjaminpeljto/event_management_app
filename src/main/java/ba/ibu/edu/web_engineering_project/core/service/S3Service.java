package ba.ibu.edu.web_engineering_project.core.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String getAccessKey;

    private final String getBucketName;
    private final String getRegion;

    public S3Service(S3Client s3Client, String getAccessKey, String getBucketName, String getRegion) {
        this.s3Client = s3Client;
        this.getAccessKey = getAccessKey;
        this.getBucketName = getBucketName;
        this.getRegion = getRegion;
    }

    public String putObject(MultipartFile image){
        File file = convertMultipartToFile(image);
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(getBucketName)
                .key(fileName)
                .build();
        s3Client.putObject(objectRequest, RequestBody.fromFile(file));

        return "https://" + getBucketName + ".s3." + Region.of(getRegion) + ".amazonaws.com/" + fileName;
    }

    private File convertMultipartToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try {
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return convertedFile;
    }


}
