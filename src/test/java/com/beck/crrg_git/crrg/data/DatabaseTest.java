package com.beck.crrg_git.crrg.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;



class DatabaseTest {

  private Connection connection;
  @BeforeEach
  void setUp() {
    connection = null;
  }

  @AfterEach
  void tearDown() {
    connection = null;
  }

  @Test
  void getConnection() {
    connection =Database.getConnection();
    assertNotNull(connection);
  }
}