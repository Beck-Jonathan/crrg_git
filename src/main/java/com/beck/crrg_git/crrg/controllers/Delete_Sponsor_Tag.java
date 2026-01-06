package com.beck.crrg_git.crrg.controllers; /******************
 Create the Servlet For Deleteing from the Sponsor_Tag table
 Created By Jonathan Beck 9/11/2025
 ***************/

import com.beck.crrg_git.crrg.data.Picture_DAO;
import com.beck.crrg_git.crrg.data.Sponsor_DAO;
import com.beck.crrg_git.crrg.data.Sponsor_Tag_DAO;
import com.beck.crrg_git.crrg.data_interfaces.ISponsor_Tag_DAO;
import com.beck.crrg_git.crrg.data_interfaces.iPicture_DAO;
import com.beck.crrg_git.crrg.data_interfaces.iSponsor_DAO;
import com.beck.crrg_git.crrg.models.Sponsor_Tag;
import com.beck.crrg_git.crrg.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Servlet implementation class AddSponsor_TagServlet
 *
 * <p>This servlet handles the deletion of  {@link Sponsor_Tag} entries in the application.
 * It is mapped to the URL pattern <code>/deleteSponsor_Tag</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: handles the request for deleting aSponsor_Tag via {@link Sponsor_Tag}. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p> * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
 * appropriate roles set (specifically the "User" role).</p>
 *
 * <p>Created by Jonathan Beck on 9/11/2025</p>
 *
 * @author Jonathan Beck
 * @version 1.0
 * @see com.beck.crrg_git.crrg.models.Sponsor_Tag
 * @see com.beck.crrg_git.crrg.data.Sponsor_Tag_DAO
 * @see jakarta.servlet.http.HttpServlet
 */
@WebServlet("/deletesponsor_tag")
public class Delete_Sponsor_Tag extends HttpServlet {
  private ISponsor_Tag_DAO sponsor_tagDAO;
  private iSponsor_DAO sponsorDAO;
  private iPicture_DAO pictureDAO;
  @Override
  public void init() {
    sponsor_tagDAO = new Sponsor_Tag_DAO();
    sponsorDAO = new Sponsor_DAO();
    pictureDAO = new Picture_DAO();
  }
  public void init(ISponsor_Tag_DAO sponsor_tagDAO,iSponsor_DAO sponsorDAO,iPicture_DAO pictureDAO){
    this.sponsor_tagDAO = sponsor_tagDAO;
    this.sponsorDAO = sponsorDAO;
    this.pictureDAO = pictureDAO;
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
      session.invalidate();
      resp.sendRedirect("crrgLogin");
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete Sponsor_Tag");
    String Sponsor_TagID = req.getParameter("sponsor_tagid");
    String Photo_ID = req.getParameter("Photo_ID");
    int photoID=-1;
    try{
      photoID=Integer.parseInt(Photo_ID);
      if (Sponsor_TagID==null||Sponsor_TagID.isEmpty()){
        throw new Exception("Invalid Sponsor_TagID");
      }
    }
    catch(Exception e){
      resp.setContentType("text/plain");
      PrintWriter writer=resp.getWriter();
      writer.write(e.getMessage());
      writer.flush();
      writer.close();
    }


    Integer result = 0;
    if (photoID!=-1) {
      try {
        result = sponsor_tagDAO.deleteSponsor_Tag(Sponsor_TagID,photoID);
      } catch (Exception ex) {
        PrintWriter writer=resp.getWriter();
        writer.write(ex.getMessage());
        writer.flush();
        writer.close();
      }
    }


      resp.setStatus(200);
      resp.setContentType("text/plain");
      PrintWriter writer=resp.getWriter();
      writer.write(result.toString());
      writer.flush();
      writer.close();
      return;

  }
}

