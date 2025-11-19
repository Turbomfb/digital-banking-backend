/* (C)2025 */
package com.techservices.digitalbanking.core.service;

import com.techservices.digitalbanking.core.exception.ValidationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileDownloadService {

  private static final String FILES_DIRECTORY = "files/";
  private static final String GUARANTOR_FORM_PATH = FILES_DIRECTORY + "kkuGuarantorForm.pdf";

  @Value("${app.file.storage.location:#{null}}")
  private String fileStorageLocation;

  /** Get the guarantor form PDF */
  public Resource getGuarantorForm() {

    log.info("Fetching guarantor form from: {}", GUARANTOR_FORM_PATH);
    return getResource(GUARANTOR_FORM_PATH);
  }

  /** Get any file from the files directory */
  public Resource getFile(String fileName) {

    log.info("Fetching file: {}", fileName);

    // Security: Prevent directory traversal attacks
    if (fileName.contains("..") || fileName.contains("/") || fileName.contains("\\")) {
      log.error("Invalid file name detected: {}", fileName);
      throw new ValidationException("invalid.file.name", "Invalid file name");
    }

    String filePath = FILES_DIRECTORY + fileName;
    return getResource(filePath);
  }

  /** Get resource from either file system or classpath */
  private Resource getResource(String path) {

    Resource resource = null;

    // Try loading from external file system first (if configured)
    if (fileStorageLocation != null && !fileStorageLocation.isEmpty()) {
      try {
        Path filePath = Paths.get(fileStorageLocation).resolve(path).normalize();
        resource = new UrlResource(filePath.toUri());

        if (resource.exists() && resource.isReadable()) {
          log.info("Successfully loaded file from file system: {}", path);
          return resource;
        }
      } catch (MalformedURLException e) {
        log.warn("Error loading file from file system: {} - {}", path, e.getMessage());
      }
    }

    // Fallback to classpath
    return getResourceFromClasspath(path);
  }

  /** Get resource from classpath */
  private Resource getResourceFromClasspath(String path) {

    try {
      Resource resource = new ClassPathResource(path);

      if (!resource.exists()) {
        log.error("File not found in classpath: {}", path);
        throw new ValidationException("file.not.found", "File not found: " + path);
      }

      if (!resource.isReadable()) {
        log.error("File not readable in classpath: {}", path);
        throw new ValidationException("file.not.readable", "File not readable: " + path);
      }

      log.info("Successfully loaded file from classpath: {}", path);
      return resource;

    } catch (Exception e) {
      log.error("Error loading file from classpath: {} - {}", path, e.getMessage(), e);
      throw new ValidationException("file.loading.error", "Error loading file: " + path);
    }
  }

  /** Determine media type based on file extension */
  public MediaType getMediaType(String fileName) {

    String extension = getFileExtension(fileName).toLowerCase();

    switch (extension) {
      case "pdf":
        return MediaType.APPLICATION_PDF;
      case "doc":
        return MediaType.valueOf("application/msword");
      case "docx":
        return MediaType.valueOf(
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
      case "xls":
        return MediaType.valueOf("application/vnd.ms-excel");
      case "xlsx":
        return MediaType.valueOf(
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
      case "txt":
        return MediaType.TEXT_PLAIN;
      case "csv":
        return MediaType.valueOf("text/csv");
      case "jpg":
      case "jpeg":
        return MediaType.IMAGE_JPEG;
      case "png":
        return MediaType.IMAGE_PNG;
      case "gif":
        return MediaType.IMAGE_GIF;
      case "zip":
        return MediaType.valueOf("application/zip");
      case "json":
        return MediaType.APPLICATION_JSON;
      case "xml":
        return MediaType.APPLICATION_XML;
      default:
        return MediaType.APPLICATION_OCTET_STREAM;
    }
  }

  /** Get file extension from filename */
  private String getFileExtension(String fileName) {

    int lastIndexOf = fileName.lastIndexOf(".");
    if (lastIndexOf == -1) {
      return "";
    }
    return fileName.substring(lastIndexOf + 1);
  }

  /** Get file size in bytes */
  public long getFileSize(Resource resource) {

    try {
      return resource.contentLength();
    } catch (IOException e) {
      log.warn("Unable to determine file size: {}", e.getMessage());
      return -1;
    }
  }
}
