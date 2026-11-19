package com.beck.crrg_git.crrg.controllers;

/******************
 Create the Servlet  For Viewing all of the  Skater table
 Created By Jonathan Beck 10/16/2026
 ***************/

import com.beck.crrg_git.crrg.data.Skater_DAO;
import com.beck.crrg_git.crrg.models.Skater;
import com.beck.crrg_git.crrg.models.User;
import com.beck.crrg_git.crrg.data_interfaces.iSkater_DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Servlet implementation class AllSkaterServlet
 *
 * <p>This servlet handles the viewing of all {@link Skater} entries in the application.
 * It is mapped to the URL pattern <code>/all-Skaters</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the table for viewing all Skaters from the database via {@link Skater_DAO}. Allows earching and filtering by foreign keys. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>*
 * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
 * appropriate roles set (specifically the "User" role).</p>
 *
 * <p>Created by Jonathan Beck on 10/16/2026</p>
 *
 * @author Jonathan Beck
 * @version 1.0
 * @see com.beck.crrg_git.crrg.models.Skater
 * @see com.beck.crrg_git.crrg.data.Skater_DAO
 * @see jakarta.servlet.http.HttpServlet
 */
@WebServlet("/all-Skaters")
public class All_Skater extends HttpServlet {
  private iSkater_DAO skaterDAO;
  @Override
  public void init() {
    skaterDAO = new Skater_DAO();
  }
  public void init(iSkater_DAO skaterDAO){
    this.skaterDAO = skaterDAO;
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

    int errors = 0;
    HashMap<String,String> results = new HashMap<>();
    int skater_count=0;
    int page_number=1;
    int page_size = 1000;
//    try {
//      page_number = Integer.parseInt(req.getParameter("page"));
//    } catch (Exception e){
//      page_number=1;
//    }
    session.setAttribute("skater_page_number",page_number);
    //int offset=(page_number-1)*(page_size);
    int offset=0;
    String search_term = req.getParameter("search");
    if (search_term==null){
      search_term ="";
    }
    if (!search_term.equals("") && (search_term.length()<2||search_term.length()>100)){
      errors++;
      results.put("searchError","Invalid search term");
    }
    session.setAttribute("currentPage",req.getRequestURL());
    List<Skater> skaters = null;
    try {
      skater_count = skaterDAO.getSkaterCount(search_term);
      skaters =skaterDAO.getAllSkater(page_size,offset,search_term);
    } catch (Exception e) {
      skaters = new ArrayList<>();
    }



    req.setAttribute("Skaters", skaters);
    req.setAttribute("pageTitle", "All Skaters");
    req.setAttribute("count",skater_count);
    req.getRequestDispatcher("WEB-INF/crrg/all-Skaters.jsp").forward(req,resp);

  }
}
