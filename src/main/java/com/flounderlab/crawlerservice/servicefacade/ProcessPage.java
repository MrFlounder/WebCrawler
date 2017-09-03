package com.flounderlab.crawlerservice.servicefacade;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.flounderlab.crawlerservice.dbservice.MongoDB;
import com.google.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by kzang on 2017/8/31.
 * Input should be the base url to craw,
 * save all web content to db
 * return success message
 */
public class ProcessPage {
    private MongoDB mongoDB;

    @Inject
    ProcessPage(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }

    public void processPage(String URL) throws SQLException, IOException {
        /*
        * 1. retrieve all url starting at count from db and put in queue
        * 2. start crawling on first url, save all url seen on this page starting with base url
        * 3. craw the next one in queue
        * 4. keep counting how many url we did - count
        * */
        String sql = "select * from Record where URL = '" + URL + "'";
        mongoDB.database.getCollection("leetcode");
        if (rs.next()) {

        } else {
            //store the URL to database to avoid parsing again
            sql = "INSERT INTO  `Crawler`.`Record` " + "(`URL`) VALUES " + "(?);";
            PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, URL);
            stmt.execute();

            //get useful information
            Document doc = Jsoup.connect("http://www.mit.edu/").get();

            if (doc.text().contains("research")) {
                System.out.println(URL);
            }

            //get all links and recursively call the processPage method
            Elements questions = doc.select("a[href]");
            for (Element link : questions) {
                if (link.attr("href").contains("mit.edu"))
                    processPage(link.attr("abs:href"));
            }
        }
    }
}
