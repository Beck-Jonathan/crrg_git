package com.beck.crrg_git.crrg.controllers;

import com.beck.crrg_git.crrg.data.Album_DAO;
import com.beck.crrg_git.crrg.models.Album;
import com.beck.crrg_git.crrg.models.Album_VM;
import com.beck.crrg_git.crrg.models.User;
import com.beck.crrg_git.crrg.data_interfaces.iAlbum_DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/all-Albums")
public class All_Album extends HttpServlet {
  private iAlbum_DAO albumDAO;
  @Override
  public void init() throws ServletException{
    albumDAO = new Album_DAO();
  }
  public void init(iAlbum_DAO _albumDAO) throws ServletException{
    albumDAO = _albumDAO;
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
    int errors = 0;
    HashMap<String,String> results = new HashMap<>();
    int albumcount=0;
    int page_number=1;
    int page_size = 20;
    try {
      page_number = Integer.parseInt(req.getParameter("page"));
    } catch (Exception e){
      page_number=1;
    }
    int offset=(page_number-1)*(page_size);
    if (user==null||!user.isInRole(ROLES_NEEDED)){
      session.invalidate();
      resp.sendRedirect("crrgLogin");
      return;
    }
    String search_term = req.getParameter("search");
    if (search_term==null){
      search_term ="";
    }
    if (!search_term.equals("") && (search_term.length()<2||search_term.length()>100)){
      errors++;
      results.put("searchError","Invalid search term");
    }

//    session.setAttribute("currentPage",req.getRequestURL());
    List<Album_VM> albums = null;

    try {
      albumcount = albumDAO.getAlbumCount(search_term);
      albums =albumDAO.getAllAlbum(page_size,offset,search_term);
    } catch (SQLException e) {
      albums = new ArrayList<>();
    }

    int total_pages = (albumcount/page_size)+1;
    req.setAttribute("noOfPages", total_pages);
    req.setAttribute("currentPage", page_number);
    req.setAttribute("Albums", albums);
    req.setAttribute("pageTitle", "All Albums");


    req.getRequestDispatcher("WEB-INF/crrg/all-Albums.jsp").forward(req,resp);

  }
}