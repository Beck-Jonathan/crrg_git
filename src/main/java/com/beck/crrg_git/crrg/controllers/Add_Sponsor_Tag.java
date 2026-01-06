package com.beck.crrg_git.crrg.controllers;

import com.beck.crrg_git.crrg.data.Sponsor_DAO;
import com.beck.crrg_git.crrg.data.Sponsor_Tag_DAO;
import com.beck.crrg_git.crrg.data.aws_DAO;
import com.beck.crrg_git.crrg.data_interfaces.Iaws_DAO;
import com.beck.crrg_git.crrg.models.*;
import com.beck.crrg_git.crrg.data_interfaces.iSponsor_DAO;
import com.beck.crrg_git.crrg.data_interfaces.ISponsor_Tag_DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletDiskFileUpload;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
/******************
 Create the Servlet  For adding to The  Sponsor_Tag table
 Created By Jonathan Beck 11/14/2024
 ***************/

@WebServlet("/addSponsor_Tag")
@MultipartConfig(

    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class Add_Sponsor_Tag extends HttpServlet{
  private  iSponsor_DAO sponsor_DAO;
  private  ISponsor_Tag_DAO sponsor_tag_DAO;
  private Iaws_DAO aws_DAO;

  private static final String bucketName = "derbypictures";
  private static final String UPLOAD_DIR = "images\\crrg\\";


  @Override
  public void init() throws ServletException{
    sponsor_DAO = new Sponsor_DAO();
    sponsor_tag_DAO = new Sponsor_Tag_DAO();
    aws_DAO = new aws_DAO();
  }
  public void init(ISponsor_Tag_DAO sponsor_tag_DAO, iSponsor_DAO sponsor_DAO, Iaws_DAO aws_dao) throws ServletException{
    this.sponsor_tag_DAO = sponsor_tag_DAO;
    this.sponsor_DAO = sponsor_DAO;
    this.aws_DAO = aws_dao;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    ROLES_NEEDED.add("Jonathan");
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||!user.isInRole(ROLES_NEEDED)){
      session.invalidate();
      resp.sendRedirect("crrgLogin");
      return;
    }
    List<String> allSponsors = new ArrayList<>();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Sponsor_Tag");
    try {
      allSponsors = sponsor_DAO.getDistinctSponsorForDropdown();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("Sponsors", allSponsors);

    req.getRequestDispatcher("WEB-INF/crrg/AddSponsorImage.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    int errorsCount=0;
    List<String> ROLES_NEEDED = new ArrayList<>();
    ROLES_NEEDED.add("Jonathan");
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||!user.isInRole(ROLES_NEEDED)){
      session.invalidate();
      resp.sendRedirect("crrgLogin");
      return;
    }
    List<String> allSponsors = new ArrayList<>();
    String applicationPath = req.getServletContext().getRealPath("");

    String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
    File fileSaveDir = new File(uploadFilePath);
    if (!fileSaveDir.exists()) {
      fileSaveDir.mkdirs();
    }
    Map<String, String> results = new HashMap<>();
    try {
      allSponsors = sponsor_DAO.getDistinctSponsorForDropdown();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("Sponsors", allSponsors);

    List<File> files = new ArrayList<>();
    JakartaServletDiskFileUpload upload = new JakartaServletDiskFileUpload();
    List<DiskFileItem> items;
    try {
      items = upload.parseRequest(req);
    } catch (Exception e) {
      errorsCount++;
      results.put("pictureUploadError", e.getMessage());
      items = new ArrayList<>();
    }


    if ( errorsCount > 0) {
      req.setAttribute("results", results);
      req.setAttribute("sponsors", allSponsors);
      req.getRequestDispatcher("WEB-INF/crrg/AddSponsorTag.jsp").forward(req, resp);
      return;
    }
    String _Sponsor_ID = "";
    String _Picture_ID = "";
    String _Description = "";
    String isPrimary = "";

    try {
      files = aws_DAO.saveUploadedFiles(req, applicationPath, items,UPLOAD_DIR);
    } catch (Exception e) {
      results.put("fileSaveError", e.getMessage());
    }
    for (DiskFileItem x : items) {

      String variable = x.getFieldName();
      String value = x.getString();
      switch (variable) {
        case "inputsponsor_tagSponsor_ID":
          _Sponsor_ID = value;
          break;
        case "inputsponsor_tagPicture_ID":
          _Picture_ID = value;
          break;
        case "inputsponsor_tagDescription":
          _Description = value;
          break;
        case "inputsponsor_tagIs_Primary":
          isPrimary = value;
          break;

      }
    }

    results.put("Sponsor_ID", _Sponsor_ID);
    results.put("Picture_ID", _Picture_ID);
    results.put("description", _Description);
    results.put("Is_Primary", isPrimary);
    List<Sponsor_Tag_VM> Sponsor_Tags = new ArrayList<>();


    if (errorsCount==0) {
      Map<File, String> uploadedUrls;
      try {
        uploadedUrls = aws_DAO.uploadToS3(files, bucketName);
      } catch (Exception ex) {
        results.put("dbError", ex.getMessage());
        req.setAttribute("results", results);
        req.setAttribute("pageTitle", "Add Picture");
        req.getRequestDispatcher("WEB-INF/crrg/AddPicture.jsp").forward(req, resp);
        return;
      }
      int result = 0;
      int added = 0;
      int notadded = 0;

      errorsCount = 0;

      for (File file : files) {
        Sponsor_Tag_VM sponsor_tag = new Sponsor_Tag_VM();
        try {
          sponsor_tag.setSponsor_ID(_Sponsor_ID);
        } catch(Exception e) {results.put("sponsor_tagSponsor_IDerror", e.getMessage());
          errorsCount++;
        }
        try {
          sponsor_tag.setPicture_ID(Integer.valueOf(_Picture_ID));
        } catch(Exception e) {results.put("sponsor_tagPicture_IDerror", e.getMessage());
          errorsCount++;
        }
        try {
          sponsor_tag.setDescription(_Description);
        } catch(Exception e) {results.put("sponsor_tagDescriptionerror", e.getMessage());
          errorsCount++;
        }
        try {
          sponsor_tag.setis_Primary(Boolean.parseBoolean(isPrimary));
        } catch(Exception e) {results.put("sponsor_tagIs_Primaryerror", e.getMessage());
          errorsCount++;
        }
        Sponsor_Tags.add(sponsor_tag);
        file.delete();
      }

      if (errorsCount == 0) {
        for (Sponsor_Tag_VM sponsor_tag : Sponsor_Tags) {
          try {
            result = sponsor_tag_DAO.addSponsor_Tag(sponsor_tag);
          } catch (Exception ex) {
            results.put("dbError", "Database Error");
          }
          if (result > 0) {
            added++;
          } else {
            notadded++;
          }
          if (added == Sponsor_Tags.size()) {
            results.put("dbStatus", added + " Pictures Added. " + notadded + " Pictures Not Added.");
            req.setAttribute("results", results);
            resp.sendRedirect("all-Pictures");
            return;
          }
        }
      }

      results.put("dbStatus", added + " Pictures Added. " + notadded + " Pictures Not Added.");
    }
    req.setAttribute("results", results);

    req.setAttribute("pageTitle", "Add Picture");
    req.getRequestDispatcher("WEB-INF/crrg/AddPicture.jsp").forward(req, resp);

  }
}

