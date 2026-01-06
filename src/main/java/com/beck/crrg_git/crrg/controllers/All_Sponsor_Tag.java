package com.beck.crrg_git.crrg.controllers; /******************
 Create the Servlet  For Viewing all of the  Sponsor_Tag table
 Created By Jonathan Beck 11/14/2024
 ***************/

import com.beck.crrg_git.crrg.data.Sponsor_Tag_DAO;
import com.beck.crrg_git.crrg.models.Sponsor_Tag_VM;
import com.beck.crrg_git.crrg.models.User;
import com.beck.crrg_git.crrg.data_interfaces.ISponsor_Tag_DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@WebServlet("/all-Sponsor_Tags")
public class All_Sponsor_Tag extends HttpServlet {
  private ISponsor_Tag_DAO sponsor_tagDAO;
  @Override
  public void init() throws ServletException{
    sponsor_tagDAO = new Sponsor_Tag_DAO();
  }
  public void init(ISponsor_Tag_DAO sponsor_tagDAO){
    this.sponsor_tagDAO = sponsor_tagDAO;
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
      session.invalidate();
      resp.sendRedirect("crrgLogin");
      return;
    }
    String sponsor_ID = req.getParameter("SponsorID");
    if (sponsor_ID==null){
      sponsor_ID = "";
    }

    session.setAttribute("currentPage",req.getRequestURL());
    List<Sponsor_Tag_VM> sponsor_tags = null;

    try{
      sponsor_tags =sponsor_tagDAO.getAllSponsor_Tag(sponsor_ID);
    }catch (Exception e){
      sponsor_tags = new ArrayList<>();
    }


    req.setAttribute("Sponsor_Tags", sponsor_tags);
    req.setAttribute("pageTitle", "All Sponsor_Tags");
    req.getRequestDispatcher("WEB-INF/crrg/all-Sponsor_Images.jsp").forward(req,resp);

  }
}
