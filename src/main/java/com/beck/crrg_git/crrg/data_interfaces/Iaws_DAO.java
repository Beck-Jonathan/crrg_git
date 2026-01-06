package com.beck.crrg_git.crrg.data_interfaces;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload2.core.DiskFileItem;


import java.io.File;

import java.util.List;
import java.util.Map;

public interface Iaws_DAO {

  public Map<File, String> uploadToS3(List<File> files, String bucketName) throws Exception;
  public List<File> saveUploadedFiles(HttpServletRequest req, String applicationPath, List<DiskFileItem> items, String UPLOAD_DIR) throws Exception ;

  }
