package com.beck.crrg_git.crrg.controllers;



import com.beck.crrg_git.crrg.data.Album_DAO;
import com.beck.crrg_git.crrg.data.Picture_DAO;
import com.beck.crrg_git.crrg.data_interfaces.iAlbum_DAO;
import com.beck.crrg_git.crrg.data_interfaces.iPicture_DAO;
import com.beck.crrg_git.crrg.models.Album;
import com.beck.crrg_git.crrg.models.Picture;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "helloServlet" , value = "/")
public class image_demo_servlet extends HttpServlet {
  private iAlbum_DAO album_DAO ;
  private iPicture_DAO picture_DAO;
  @Override
  public void init() throws ServletException{
    album_DAO = new Album_DAO();
    picture_DAO = new Picture_DAO();
  }
  public void init(iAlbum_DAO album_DAO,iPicture_DAO picture_DAO){
    this.album_DAO = album_DAO;
    this.picture_DAO = picture_DAO;
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    List<Album> allAlbums;
    List<Picture> allPictures;

    try {

      allAlbums = album_DAO.getDistinctAlbumForDropdown();
      for (int i = 0; i< allAlbums.size(); i++ ) {
        if (allAlbums.get(i).getAlbum_Name().equalsIgnoreCase("sponsor")) {
          int spot = i;
          Album sponsors = allAlbums.get(i);
          Album temp = allAlbums.get(allAlbums.size() - 1);
          allAlbums.set(allAlbums.size() - 1, sponsors);
          allAlbums.set(spot, temp);
          break;

        }
      }
      for (int i = 0; i< allAlbums.size(); i++ ) {
        if (allAlbums.get(i).getAlbum_Name().equalsIgnoreCase("teammembers")) {
          int spot = i;
          Album roster = allAlbums.get(i);
          Album temp = allAlbums.get(allAlbums.size() - 2);
          allAlbums.set(allAlbums.size() - 2, roster);
          allAlbums.set(spot, temp);
          break;
        }
      }
      allPictures = picture_DAO.getActivePicturebyAlbum(allAlbums.get(0).getAlbum_ID());
    } catch (SQLException e) {
      allAlbums = new ArrayList<>();
      allPictures = new ArrayList<>();
      resp.sendRedirect("//www.crrollerderby.com");
      return;
    }



    req.setAttribute("Albums", allAlbums);
    req.setAttribute("Pictures", allPictures);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Image Gallery of CRRD. Photos: Mark Young");
    req.getRequestDispatcher("WEB-INF/crrg/images.jsp").forward(req, resp);
  }


}
