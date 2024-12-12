package com.beck.beck_demos.crrg.controllers;




import com.beck.beck_demos.crrg.data.Picture_DAO;
import com.beck.beck_demos.crrg.data.Contributor_DAO;
import com.beck.beck_demos.crrg.data.Album_DAO;
import com.beck.beck_demos.crrg.models.*;
import com.beck.beck_demos.crrg.data_interfaces.iPicture_DAO;
import com.beck.beck_demos.crrg.data_interfaces.iContributor_DAO;
import com.beck.beck_demos.crrg.data_interfaces.iAlbum_DAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.JakartaServletDiskFileUpload;
import org.apache.commons.io.FileUtils;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;

import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

/******************
 Create the Servlet  For adding to The  Picture table
 Created By Jonathan Beck 10/9/2024
 ***************/

@WebServlet("/addPicture")
@MultipartConfig(

    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class Add_Picture extends HttpServlet{
  private static final String UPLOAD_DIR = "images\\crrg\\";
  private static final String bucketName = "derbypictures";
  static List<Album_VM> allAlbums ;
  static List<Contributor_VM> allContributors ;
  private iPicture_DAO pictureDAO;
  private iContributor_DAO contributorDAO;
  private iAlbum_DAO albumDAO;

  @Override
  public void init() throws ServletException{
    pictureDAO = new Picture_DAO();
    contributorDAO = new Contributor_DAO();
    albumDAO = new Album_DAO();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
    ROLES_NEEDED.add("Jonathan");
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||!user.isInRole(ROLES_NEEDED)){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Picture");

    try {
      allAlbums = albumDAO.getAllAlbum(20,0);
      allContributors = contributorDAO.getAllContributor(20,0);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("Albums", allAlbums);
    req.setAttribute("Contributors", allContributors);
    req.getRequestDispatcher("WEB-INF/crrg/AddPicture.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
    ROLES_NEEDED.add("Jonathan");
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||!user.isInRole(ROLES_NEEDED)){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }


    try {
      allAlbums = albumDAO.getAllAlbum(20,0);
      allContributors = contributorDAO.getAllContributor(20,0);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("Albums", allAlbums);
    req.setAttribute("Contributors", allContributors);
    String _Album_ID = req.getParameter("inputpictureAlbum_ID");
    _Album_ID=_Album_ID.trim();
    String _Contributor_ID = req.getParameter("inputpictureContributor_ID");
    _Contributor_ID=_Contributor_ID.trim();
    String approved = req.getParameter("inputpictureis_Approved");
    String active = req.getParameter("inputpictureis_Active");
    boolean is_approved = false;
    boolean is_active = false;
    try {
      is_approved = Boolean.parseBoolean(approved);
    }
    catch (Exception e) {

    }
    try {
       is_active = Boolean.parseBoolean(active);
    }catch (Exception e){

    }




    Part filePart = req.getPart("inputpictureWeb_Address");
    String fileName = filePart.getSubmittedFileName();
    String url="";



    String _description = req.getParameter("inputpicturedescription");
    _description=_description.trim();
    String _Is_Active = req.getParameter("inputpictureIs_Active");
    //_Is_Active=_Is_Active.trim();
    String _is_Approved = req.getParameter("inputpictureis_Approved");
    _is_Approved=_is_Approved.trim();
    Map<String, String> results = new HashMap<>();
    String applicationPath = req.getServletContext().getRealPath("");
    String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR+_Album_ID;
    File fileSaveDir = new File(uploadFilePath);
    if (!fileSaveDir.exists()) {
      fileSaveDir.mkdirs();
    }
    JakartaServletDiskFileUpload upload = new JakartaServletDiskFileUpload();
    ServletContext servletContext = this.getServletConfig().getServletContext();
  // List<DiskFileItem> items = upload.parseRequest(req);
    //for (DiskFileItem x : items) {
      try {
        File file = new File(uploadFilePath + File.separator + fileName);
        //InputStream stream = x.getInputStream();
       // FileUtils.copyInputStreamToFile(stream, file);
        for (Part part : req.getParts()) {
          part.write(uploadFilePath + File.separator + fileName);

          S3Client client = S3Client.builder().build();
          PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(fileName).build();
          PutObjectResponse result = client.putObject(request, RequestBody.fromFile(file));

          GetUrlRequest req2 = GetUrlRequest.builder().bucket(bucketName).key(fileName).build();
          url = client.utilities().getUrl(req2).toExternalForm();
          file.delete();
        }
      } catch (Exception ex) {
        results.put("dbStatus", ex.getMessage());
        req.setAttribute("results", results);
        req.setAttribute("pageTitle", "Add Picture");
        req.getRequestDispatcher("WEB-INF/crrg/AddPicture.jsp").forward(req, resp);
        return;
      }

    results.put("Album_ID",_Album_ID);
    results.put("Contributor_ID",_Contributor_ID);
    results.put("Web_Address",fileName);
    results.put("description",_description);
    results.put("Is_Active",_Is_Active);
    results.put("is_Approved",_is_Approved);
    Picture picture = new Picture();
    int errors =0;
    try {
      picture.setAlbum_ID(Integer.valueOf(_Album_ID));
    } catch(IllegalArgumentException e) {results.put("pictureAlbum_IDerror", e.getMessage());
      errors++;
    }
    try {
      picture.setContributor_ID(Integer.valueOf(_Contributor_ID));
    } catch(IllegalArgumentException e) {results.put("pictureContributor_IDerror", e.getMessage());
      errors++;
    }
    try {
      picture.setWeb_Address(url);
    } catch(IllegalArgumentException e) {results.put("pictureWeb_Addresserror", e.getMessage());
      errors++;
    }
    try {
      picture.setdescription(_description);
    } catch(IllegalArgumentException e) {results.put("picturedescriptionerror", e.getMessage());
      errors++;
    }
    picture.setIs_Active(is_active);
    picture.setis_Approved(is_approved);
    int result=0;
    if (errors==0){
      try{
        result=pictureDAO.add(picture);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Picture Added");
        resp.sendRedirect("all-Pictures");
        return;
      } else {
        results.put("dbStatus","Picture Not Added");

      }
    }
    //}
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Add Picture");
    req.getRequestDispatcher("WEB-INF/crrg/AddPicture.jsp").forward(req, resp);

  }
}

