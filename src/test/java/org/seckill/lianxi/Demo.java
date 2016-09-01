package org.seckill.lianxi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * @author Administrator
 * @title
 * @since 2016/7/18
 */
public class Demo {
    public static void main(String[] args) {
    }

    //Java8的字符串处理库 strman-java
    /*@Test
    public void testStr(){
        String str = append("t","e","s","t");
        System.out.println(str);

    }*/

    @Test
    public void testHtml() throws IOException {
//        File input = new File("/E:/文档/htmlemail/countHtml.html");
        File input = new File("src/test/resources/docs/countHtml.html");
        Document doc = Jsoup.parse(input, "UTF-8", "");

        System.out.println(doc);

//        Element content = (Element) doc.getElementById("content");
//        content.html("");
//        Elements links = content.getElementsByTag("a");
//        for (Element link : links) {
//            String linkHref = link.attr("href");
//            String linkText = link.text();
//        }
        StringBuffer sb = new StringBuffer();
        try {
//            System.out.println(this.getClass().getClassLoader());
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("src/main/docs/countHtml.html");
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);

            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();
            reader.close();
            is.close();

        } catch (Exception e) {
//            LOG.error("get statisticsReport error", e);
        }
        String html = sb.toString();
        System.out.println(html);
    }

    @Test
    public void testweek(){

    }

}
