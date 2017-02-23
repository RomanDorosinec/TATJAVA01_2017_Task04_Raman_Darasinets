package com.epam.task3.dao.impl;

import com.epam.task3.bean.News;
import com.epam.task3.dao.utils.db.ConnectionPool;
import com.epam.task3.dao.utils.db.excpetion.ConnectionPoolException;
import org.testng.annotations.*;

import java.sql.Connection;

import static org.testng.Assert.*;

public class DBNewsDAOTest {

    @BeforeMethod
    public void init() throws ConnectionPoolException {
        ConnectionPool.getInstance().initPoolData();
    }

    @AfterMethod
    public void destroy() {

    }

    @DataProvider(name = "New for added")
    public Object[][] newsAdded() throws Exception {
        return new Object[][] {
                {new News("film", "warcraft", "Duncan Jones")}
        };
    }

    @Test//(dataProvider = "News for added")
    public void testAddNews() throws Exception {
        News news = new News("film", "warcraft", "Duncan Jones");
        DBNewsDAO dbNewsDAO = new DBNewsDAO();
        dbNewsDAO.addNews(news);
        String insert = "SELECT * FROM news WHERE category = ? title = ? author = ?";
    }

    /*@Test
    public void testGetNews() throws Exception {

    }*/

}