package com.pg;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@TestPropertySource({"classpath:application.properties"})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(value = {"environmentName=test"}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Ignore

public class BaseTest {
  @Autowired
  public MockMvc mvc;
}
