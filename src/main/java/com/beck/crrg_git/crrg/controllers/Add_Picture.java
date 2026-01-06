package com.beck.crrg_git.crrg.controllers;




import com.beck.crrg_git.crrg.data.Picture_DAO;
import com.beck.crrg_git.crrg.data.Contributor_DAO;
import com.beck.crrg_git.crrg.data.Album_DAO;
import com.beck.crrg_git.crrg.data.aws_DAO;
import com.beck.crrg_git.crrg.data_interfaces.Iaws_DAO;
import com.beck.crrg_git.crrg.models.*;
import com.beck.crrg_git.crrg.data_interfaces.iPicture_DAO;
import com.beck.crrg_git.crrg.data_interfaces.iContributor_DAO;
import com.beck.crrg_git.crrg.data_interfaces.iAlbum_DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletDiskFileUpload;

import java.io.*;
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
public class Add_Picture extends HttpServlet {
  private static final String UPLOAD_DIR = "images\\crrg\\";
  private static final String bucketName = "derbypictures";

  private iPicture_DAO pictureDAO;
  private iContributor_DAO contributorDAO;
  private iAlbum_DAO albumDAO;
  private Iaws_DAO aws_dao;

  @Override
  public void init() throws ServletException {
    pictureDAO = new Picture_DAO();
    contributorDAO = new Contributor_DAO();
    albumDAO = new Album_DAO();
    aws_dao = new aws_DAO() ;


  }

  public void init(iPicture_DAO pictureDAO, iAlbum_DAO albumDAO, iContributor_DAO contributorDAO, Iaws_DAO iaws_dao   ) {
    this.pictureDAO = pictureDAO;
    this.albumDAO = albumDAO;
    this.contributorDAO = contributorDAO;
    this.aws_dao = iaws_dao;
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
    ROLES_NEEDED.add("Jonathan");
//add roles here
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User");
    if (user == null || !user.isInRole(ROLES_NEEDED)) {
      session.invalidate();
      resp.sendRedirect("crrgLogin");
      return;
    }
    List<Album_VM> allAlbums;
    List<Contributor_VM> allContributors;
    session.setAttribute("currentPage", req.getRequestURL());
    req.setAttribute("pageTitle", "Add Picture");

    try {
      allAlbums = albumDAO.getAllAlbum(20, 0, "");
      allContributors = contributorDAO.getAllContributor(20, 0);
    } catch (SQLException e) {
      allAlbums = new ArrayList<>();
      allContributors = new ArrayList<>();
    }
    req.setAttribute("Albums", allAlbums);
    req.setAttribute("Contributors", allContributors);
    req.getRequestDispatcher("WEB-INF/crrg/AddPicture.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<String> ROLES_NEEDED = new ArrayList<>();
    ROLES_NEEDED.add("Jonathan");
    //add roles here
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User");
    Map<String, String> results = new HashMap<>();
    if (user == null || !user.isInRole(ROLES_NEEDED)) {
      session.invalidate();
      resp.sendRedirect("crrgLogin");
      return;
    }
    int errorsCount =0;


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
    String applicationPath = req.getServletContext().getRealPath("");
    String albumID = "";
    String description = "";
    String contributorID = "";
    String isApproved = "";
    String isActive = "";
    List<Album_VM> allAlbums;
    List<Contributor_VM> allContributors;
    if ( errorsCount > 0) {
      req.setAttribute("results", results);
      try {
        allAlbums = albumDAO.getAllAlbum(20, 0, "");
        allContributors = contributorDAO.getAllContributor(20, 0);
      } catch (SQLException e) {
        allAlbums = new ArrayList<>();
        allContributors = new ArrayList<>();
      }
      req.setAttribute("Albums", allAlbums);
      req.setAttribute("Contributors", allContributors);
      req.getRequestDispatcher("WEB-INF/crrg/AddPicture.jsp").forward(req, resp);
      return;
    }

    try {
      files = aws_dao.saveUploadedFiles(req, applicationPath, items,UPLOAD_DIR);
    } catch (Exception e) {
      results.put("fileSaveError", e.getMessage());
    }
    for (DiskFileItem x : items) {

      String variable = x.getFieldName();
      String value = x.getString();
      switch (variable) {
        case "inputpictureAlbum_ID":
          albumID = value;
          break;
        case "inputpictureContributor_ID":
          contributorID = value;
          break;
        case "inputpicturedescription":
          description = value;
          break;
        case "inputpictureis_Approved":
          isApproved = value;
          break;
        case "inputpictureis_Active":
          isActive = value;
          break;
      }
    }

      boolean is_active = false;
      boolean is_approved = false;
      is_active = Boolean.parseBoolean(isActive);
      is_approved = Boolean.parseBoolean(isApproved);

      results.put("Album_ID", albumID);
      results.put("Contributor_ID", contributorID);
      results.put("description", description);
      results.put("Is_Active", isActive);
      results.put("is_Approved", isApproved);

      List<Picture> pictures = new ArrayList<>();
      boolean falseOverride = false;
      if (files.size() > 20) {
        falseOverride = true;
        results.put("falseOverride","False Override activated.");
      }

      if (errorsCount==0) {
        Map<File, String> uploadedUrls;
        try {
          uploadedUrls = aws_dao.uploadToS3(files, bucketName);
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
          Picture picture = new Picture();
          try {
            picture.setAlbum_ID(Integer.valueOf(albumID));
          } catch (IllegalArgumentException e) {
            results.put("pictureAlbum_IDerror", e.getMessage());
            errorsCount++;
          }
          try {
            picture.setContributor_ID(Integer.valueOf(contributorID));
          } catch (IllegalArgumentException e) {
            results.put("pictureContributor_IDerror", e.getMessage());
            errorsCount++;
          }
          try {
            picture.setDescription(description);
          } catch (IllegalArgumentException e) {
            results.put("picturedescriptionerror", e.getMessage());
            errorsCount++;
          }

            picture.setIs_Active(is_active);

          picture.setis_Approved(is_approved);
          if (falseOverride) {
            picture.setIs_Active(false);
            picture.setis_Approved(false);
          }
          try {
            picture.setWeb_Address(uploadedUrls.get(file));
          } catch (IllegalArgumentException e) {
            results.put("pictureWeb_Addresserror", e.getMessage());
            errorsCount++;
          }
          pictures.add(picture);
          file.delete();
        }

        if (errorsCount == 0) {
          for (Picture picture : pictures) {
            try {
              result = pictureDAO.add(picture);
            } catch (Exception ex) {
              results.put("dbError", "Database Error");
            }
            if (result > 0) {
              added++;
            } else {
              notadded++;
            }
            if (added == pictures.size()) {
              results.put("dbStatus", added + " Pictures Added. " + notadded + " Pictures Not Added.");
              req.setAttribute("results", results);
              resp.sendRedirect("all-Pictures");
              return;
            }
          }
        }

        results.put("dbStatus", added + " Pictures Added. " + notadded + " Pictures Not Added.");
      }

    try {
      allAlbums = albumDAO.getAllAlbum(20, 0, "");
      allContributors = contributorDAO.getAllContributor(20, 0);
    } catch (SQLException e) {
      allAlbums = new ArrayList<>();
      allContributors = new ArrayList<>();
    }
    req.setAttribute("Albums", allAlbums);
    req.setAttribute("Contributors", allContributors);
        req.setAttribute("results", results);

      req.setAttribute("pageTitle", "Add Picture");
      req.getRequestDispatcher("WEB-INF/crrg/AddPicture.jsp").forward(req, resp);

  }


}