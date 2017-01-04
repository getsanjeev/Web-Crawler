import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;

/**
 * Created by sanjeev on 4/1/17.
 */
public class basic_jsoup
{
    public static void main(String [] args)
    {
        get_title_from_url();

    }

    static  void get_title_from_url()
    {
        try
        {
            File f;
            Document document = Jsoup.connect("http://github.com").get();
            String alpha = document.text();
            Jsoup.parse(f,"cic");
            System.out.println(alpha);
            String title = document.title();
            System.out.println(title);
        }
        catch (IOException e)
        {
            e.getCause();
        }

    }
}
