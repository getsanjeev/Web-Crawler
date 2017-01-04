import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class crawler {

    public static db_connect mydatabase = new db_connect();

    public static void main(String[] args) throws SQLException, IOException
    {
        mydatabase.runSql2("TRUNCATE Record;");     // empty the table.
        processPage("http://www.mit.edu");   //main function to crawl.
    }

    public static void processPage(String URL) throws SQLException, IOException
    {
        //check if the given URL is already in database.
        String sql_result = "select * from Record where URL = '"+URL+"'";
        ResultSet rs = mydatabase.runSql(sql_result);   //returns the result set.
        if(rs.next())
        {
            // if has non zero elements in the table.
           // System.out.println("Has few elements");
        }
        else
        {
            //store the URL to database to avoid parsing again
            sql_result = "INSERT INTO Record (URL) VALUES(?);";
            PreparedStatement statement = mydatabase.conn.prepareStatement(sql_result, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, URL);    // binds values to the statement.
            statement.execute();
            //get useful information
            Document doc = Jsoup.connect("http://www.mit.edu/").get();

            if(doc.text().contains("research")){
                System.out.println(URL);
            }

            //get all links and recursively call the processPage method
            Elements questions = doc.select("a[href]");
            for(Element link: questions){
                if(link.attr("href").contains("mit.edu"))
                    processPage(link.attr("abs:href"));
            }
        }
    }
}