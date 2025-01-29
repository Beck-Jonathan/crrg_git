package com.beck.crrg_git.crrg.controllers;
/******************
 Create the Servlet For Deleteing from the Album table
 Created By Jonathan Beck 10/10/2024
 ***************/

import com.beck.crrg_git.crrg.data.Album_DAO;
import com.beck.crrg_git.crrg.data.Contributor_DAO;
import com.beck.crrg_git.crrg.data.Picture_DAO;
import com.beck.crrg_git.crrg.data_interfaces.iContributor_DAO;
import com.beck.crrg_git.crrg.models.Album;
import com.beck.crrg_git.crrg.models.Picture;
import com.beck.crrg_git.crrg.models.User;
import com.beck.crrg_git.crrg.data_interfaces.iAlbum_DAO;
import com.beck.crrg_git.crrg.data_interfaces.iPicture_DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/updateActivation")
public class Activation_Servlet extends HttpServlet {
  private iAlbum_DAO albumDAO;
  private iPicture_DAO pictureDAO;
  private iContributor_DAO contributorDAO;
  @Override
  public void init() throws ServletException{
    albumDAO = new Album_DAO();
    pictureDAO = new Picture_DAO();
    contributorDAO = new Contributor_DAO();
  }
  public void init ( iPicture_DAO pictureDAO,iAlbum_DAO albumDAO,iContributor_DAO contributorDAO){
    this.albumDAO = albumDAO;
    this.pictureDAO = pictureDAO;
    this.contributorDAO = contributorDAO;
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

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

    String objectID=req.getParameter("objectID");
    String modeID = req.getParameter("mode");

    String object = req.getParameter("object");

    int ObjectID = -1;
    int mode = -1;
    try {
       ObjectID = Integer.parseInt(req.getParameter("objectID"));
       mode = Integer.parseInt(req.getParameter("mode"));
    } catch (Exception ex){
      results.put("dbStatus",ex.getMessage());
    }
    int result = 0;
    if (ObjectID>0&&(mode==0||mode==1)) {
      try {
        if (object.equals("picture")) {
          result = pictureDAO.changeActivation(ObjectID, mode);
        } else if (object.equals("album")) {

          result = albumDAO.changeActivation(ObjectID, mode);
        }
      } catch (Exception ex) {
        results.put("dbStatus", ex.getMessage());
      }
    }
    else {
      result=-1;
    }
    req.setAttribute("result",result);


    resp.setContentType("text/plain");
    resp.getWriter().write(result);
  }
}


