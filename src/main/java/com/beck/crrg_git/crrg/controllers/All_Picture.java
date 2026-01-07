package com.beck.crrg_git.crrg.controllers;

import com.beck.crrg_git.crrg.data.Album_DAO;
import com.beck.crrg_git.crrg.data.Contributor_DAO;
import com.beck.crrg_git.crrg.data.Picture_DAO;
import com.beck.crrg_git.crrg.data_interfaces.iAlbum_DAO;
import com.beck.crrg_git.crrg.data_interfaces.iContributor_DAO;
import com.beck.crrg_git.crrg.models.*;
import com.beck.crrg_git.crrg.data_interfaces.iPicture_DAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/all-Pictures")
public class All_Picture extends HttpServlet {
  private iPicture_DAO pictureDAO;
  private iAlbum_DAO albumDAO;
  private iContributor_DAO contributorDAO;
  private static final int THUMB_MAX_WIDTH = 150;
  private static final int THUMB_MAX_HEIGHT = 150;
  @Override
  public void init() throws ServletException{
    pictureDAO = new Picture_DAO();
    albumDAO = new Album_DAO();
    contributorDAO = new Contributor_DAO();
  }
  public void init(iPicture_DAO pictureDAO,iAlbum_DAO albumDAO,iContributor_DAO contributorDAO){

    this.pictureDAO = pictureDAO;
    this.albumDAO = albumDAO;
    this.contributorDAO = contributorDAO;
  }
  @Override
  public  void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
    Map<String,String> results = new HashMap<>();
    ROLES_NEEDED.add("Jonathan");
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");


    String album = req.getParameter("album");
    String search_term = req.getParameter("search");
    if (search_term==null){
      search_term ="";
    }
    results.put("search",search_term);



    String contributor = req.getParameter("contributor");
    Integer contributorID=0;
    if (contributor!=null){
      contributorID = Integer.valueOf(contributor);
      results.put("contributorID",contributorID.toString());

    }
    Integer AlbumID=0;
    if (album!=null){
      AlbumID = Integer.valueOf(album);

      results.put("AlbumID",AlbumID.toString());
    }

    if (user==null||!user.isInRole(ROLES_NEEDED)){
      session.invalidate();
      resp.sendRedirect("crrgLogin");
      return;
    }
    int page_number = 1;
    int recordsPerPage = 20;
    if(req.getParameter("page") != null) {
      page_number = Integer.parseInt(req.getParameter("page"));
    }
    session.setAttribute("page_number",page_number);

    session.setAttribute("currentPage",req.getRequestURL());
    List<Picture_VM> pictures = null;
    int picture_count=0;
    try {
      List<Album> allAlbums = albumDAO.getDistinctAlbumForDropdown();
      req.setAttribute("Albums", allAlbums);
      List<Contributor> allContributors = contributorDAO.getDistinctContributorForDropdown();
      req.setAttribute("Contributors", allContributors);
        pictures =pictureDAO.getAllPicture((page_number-1)*recordsPerPage,recordsPerPage,search_term,AlbumID,contributorID);
      picture_count = pictureDAO.getPictureCount(search_term,AlbumID,contributorID);
      ServletContext context = getServletContext();
      String thumbDirPath = context.getRealPath("/thumbnails");
      File thumbDir = new File(thumbDirPath);
      thumbDir.mkdirs();
      for (Picture_VM x : pictures){
        File thumbnailFile = new File(thumbDir, x.getPicture_ID() + ".jpg");

        if (thumbnailFile.exists()) {
          x.setWeb_Address(thumbnailFile.getAbsolutePath());
          continue;
        }
        URL url = new URL(x.getWeb_Address());
        BufferedImage image = ImageIO.read(url);
        // Create aspect-ratio-preserving thumbnail
        BufferedImage thumbnail = pictureDAO.createThumbnail(image);

        // Save thumbnail
        ImageIO.write(thumbnail, "jpg", thumbnailFile);
        x.setWeb_Address(thumbnailFile.getAbsolutePath());
      }


    } catch (SQLException e) {
      req.setAttribute("error", e.getMessage());
      resp.sendRedirect("/");
      return;
    }


  int total_pages = 1+(picture_count/recordsPerPage);
    req.setAttribute("results",results);
    req.setAttribute("noOfPages", total_pages);
    req.setAttribute("numObjects",picture_count);
    //fix current page
    req.setAttribute("currentPage", page_number);
    req.setAttribute("Pictures", pictures);
    req.setAttribute("pageTitle", "All Pictures");
    req.getRequestDispatcher("WEB-INF/crrg/all-Pictures.jsp").forward(req,resp);

  }
}
