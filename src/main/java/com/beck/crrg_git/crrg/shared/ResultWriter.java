package com.beck.crrg_git.crrg.shared;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

 public class ResultWriter {
  public static void writeResult(HttpServletResponse resp, int result) throws IOException {
    PrintWriter out = resp.getWriter();
    resp.setHeader("Content-Type", "application/json;charset=UTF-8");
    resp.setCharacterEncoding("UTF-8");
    out.print(result);
    out.flush();
  }
}
