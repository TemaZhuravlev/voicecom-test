package com.testvoicecom.managerclient.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@Sql(scripts = "classpath:sql/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@SpringBootTest
public abstract class AbstractServiceTest {
}
