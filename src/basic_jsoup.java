import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class basic_jsoup
{
    public static void main(String [] args)
    {
        get_title_from_url("github.com");
        get_title_from_html("/home/sanjeev/IdeaProjects/Web_crawler/src/git_crawler.html");
        get_total_links();
        get_all_images("www.facebook.com");

    }

    static String get_title_from_url(String url)
    {
        String title = "";
        url =  "https://"+url;
        System.out.println(url);
        try
        {
            Document document = Jsoup.connect(url).get();
            title = document.title();

        }
        catch (IOException e)
        {
            e.getCause();
        }
        return title;
    }

    static void get_title_from_html(String file_location)
    {
        System.out.println(file_location);
        try
        {
            Document doc = Jsoup.parse(new File(file_location),"utf-8");
            String title = doc.title();
            System.out.println(title);
        }
        catch (IOException e)
        {
            e.getCause();
        }
    }

    static void get_total_links()
    {
        Document doc;
        try
        {
            doc = Jsoup.connect("http://www.javatpoint.com").get();
            System.out.println(doc.text());
            Elements links = doc.select("a[href]");
            for (Element link : links)
            {
                System.out.println("link : " + link.attr("href"));
                System.out.println("text : " + link.text());
            }
        }
        catch (IOException e)
        {
            e.getCause();
        }
    }

    static void get_all_images(String url)
    {
        url =  "https://"+url;
        System.out.println(url);
        try
        {
            Document document = Jsoup.connect(url).get();
            Elements image_list = document.select("img[src~=(jpg|png|jpeg|gif|tif)]");
            for(Element image:image_list)
            {
                System.out.println("image" + image.attr("src"));
            }

        }
        catch (IOException e)
        {
            e.getCause();
        }


    }
}
