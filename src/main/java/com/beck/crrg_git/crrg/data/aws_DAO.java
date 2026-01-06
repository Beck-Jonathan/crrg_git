package com.beck.crrg_git.crrg.data;

import com.beck.crrg_git.crrg.data_interfaces.Iaws_DAO;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.io.IOUtils;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class aws_DAO implements Iaws_DAO {
  @Override
  public List<File> saveUploadedFiles(HttpServletRequest req, String applicationPath, List<DiskFileItem> items, String UPLOAD_DIR) throws Exception {
    List<File> files = new ArrayList<>();
    for (DiskFileItem x : items) {
      if (x.getName() != null) {
        InputStream inputStream = x.getInputStream();
        File file = new File(applicationPath + File.separator + UPLOAD_DIR + x.getName());
        boolean created = file.createNewFile();
        if (!created) {
          file.delete();
          file.createNewFile();
        }
        try (FileOutputStream outstream = new FileOutputStream(file)) {
          IOUtils.copy(inputStream, outstream);
        }
        files.add(file);
      }
    }
    return files;
  }
  @Override
  public Map<File, String> uploadToS3(List<File> files, String bucketName) throws Exception {
    Map<File, String> fileUrlMap = new HashMap<>();
    try (S3Client client = S3Client.builder().build()) {
      for (File file : files) {
        String filename = file.getName();
        PutObjectRequest request = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(filename)
            .build();
        client.putObject(request, RequestBody.fromFile(file));

        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
            .bucket(bucketName)
            .key(filename)
            .build();
        String url = client.utilities().getUrl(getUrlRequest).toExternalForm();
        fileUrlMap.put(file, url);
      }
    }
    return fileUrlMap;
  }
}
