package com.beck.crrg_git.crrg.controllers;

import com.azure.json.implementation.jackson.core.JsonProcessingException;
import com.beck.crrg_git.crrg.data.Album_DAO;
import com.beck.crrg_git.crrg.models.Album;
import com.beck.crrg_git.crrg.models.Album_VM;
import com.beck.crrg_git.crrg.models.User;
import com.beck.crrg_git.crrg.data_interfaces.iAlbum_DAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/all-Albums_API")
public class All_Album_API extends HttpServlet {
  private iAlbum_DAO albumDAO;
  @Override
  public void init() throws ServletException{
    albumDAO = new Album_DAO();
  }
  public void init(iAlbum_DAO albumDAO){
    this.albumDAO = albumDAO;
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    List<Album_VM> albums = null;
    String jsonArray="";

    try {
      albums =albumDAO.getAllAlbum(20,0,"");
    } catch (SQLException e) {
      PrintWriter out = resp.getWriter();
      out.println(e.getMessage());
      out.flush();
      return;
    }
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      jsonArray= objectMapper.writeValueAsString(albums);

    }
    catch (Exception e) {
      PrintWriter out = resp.getWriter();
      out.println(e.getMessage());
      out.flush();
      return;
    }

    resp.setContentType("application/json");
// Get the printwriter object from response to write the required json object to the output stream
    PrintWriter out = resp.getWriter();
// Assuming your json object is **jsonObject**, perform the following, it will return your json object
    out.print(jsonArray);
    out.flush();

  }
}