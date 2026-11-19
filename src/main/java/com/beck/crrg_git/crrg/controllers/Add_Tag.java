//package com.beck.crrg_git.crrg.controllers;
//
//
//import com.beck.crrg_git.crrg.data.TierDAO;
//import com.beck.crrg_git.crrg.models.Tier;
//import com.beck.crrg_git.crrg.models.User;
//import com.beck.crrg_git.crrg.iData.iTierDAO;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
///******************
// Create the Servlet  For adding to The  Tier table
// Created By Jonathan Beck 10/20/2026
// ***************/
///**
// * Servlet implementation class AddTierServlet
// *
// * <p>This servlet handles the addition of new {@link Tier} entries in the application.
// * It is mapped to the URL pattern <code>/addTier</code>.</p>
// *
// * <p><strong>HTTP GET</strong>: Renders the form for adding a new Tier. Access is restricted
// * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>
// * <p><strong>HTTP POST</strong>: Processes form submissions for adding a new Tier. Validates input,
// * attempts insertion into the database via {@link TierDAO}, and forwards back to the form view with
// * success or error messages as necessary. Successful insertions redirect to the list of all Tier.</p
// > * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
// * appropriate roles set (specifically the "User" role).</p>
// *
// * <p>Created by Jonathan Beck on 10/20/2026</p>
// *
// * @author Jonathan Beck
// * @version 1.0
// * @see com.beck.crrg_git.crrg.models.Tier
// * @see com.beck.crrg_git.crrg.data.TierDAO
// * @see jakarta.servlet.http.HttpServlet
// */
//
//@WebServlet("/addTier")
//public class Add_Tag extends HttpServlet{
//  private iTierDAO tierDAO;
//  @Override
//  public void init() throws ServletExecption{
//    tierDAO = new Tier_DAO();
//  }
//  public void init(iTier_DAO tierDAO){
//    this.tierDAO = tierDAO;
//  }
//
//  @Override
//  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
////To restrict this page based on privilege level
//    int PRIVILEGE_NEEDED = 0;
//    List<String> ROLES_NEEDED = new ArrayList<>();
////add roles here
//    HttpSession session = req.getSession();
//    User user = (User)session.getAttribute("User");
//    if (user==null||user.getPrivilege_ID()<PRIVILEGE_NEEDED||!user.isInRole(ROLES_NEEDED)){
//      session.invalidate();
//      resp.sendRedirect("crrgLogin");
//      return;
//    }
//
//    session.setAttribute("currentPage",req.getRequestURL());
//    req.setAttribute("pageTitle", "Add Tier");
//    req.getRequestDispatcher("WEB-INF/crrg/AddTier.jsp").forward(req, resp);
//  }
//
//  @Override
//  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
////To restrict this page based on privilege level
//    int PRIVILEGE_NEEDED = 0;
//    List<String> ROLES_NEEDED = new ArrayList<>();
////add roles here
//    HttpSession session = req.getSession();
//    User user = (User)session.getAttribute("User");
//    if (user==null||user.getPrivilege_ID()<PRIVILEGE_NEEDED||!user.isInRole(ROLES_NEEDED)){
//      session.invalidate();
//      resp.sendRedirect("crrgLogin");
//      return;
//    }
//
//    String _Tier_ID = req.getParameter("inputtierTier_ID");
//    if (_Tier_ID!=null) {
//      _Tier_ID=_Tier_ID.trim();
//    }
//    Map<String, String> results = new HashMap<>();
//    results.put("Tier_ID",_Tier_ID);
//    Tier tier = new Tier();
//    int errors =0;
//    try {
//      tier.setTier_ID(_Tier_ID);
//    } catch(Exception e) {results.put("tierTier_IDerror", e.getMessage());
//      errors++;
//    }
//    int result=0;
//    if (errors==0){
//      try{
//        result=tierDAO.add(tier);
//      }catch(Exception ex){
//        results.put("dbError","Database Error");
//      }
//      if (result>0){
//        results.put("dbStatus","Tier Added");
//        req.setAttribute("results",results);
//        resp.sendRedirect("all-Tiers");
//        return;
//      } else {
//        results.put("dbStatus","Tier Not Added");
//
//      }
//    }
//    req.setAttribute("results", results);
//    req.setAttribute("pageTitle", "Add Tier");
//    req.getRequestDispatcher("WEB-INF/crrg/AddTier.jsp").forward(req, resp);
//
//  }
//}
//
//
