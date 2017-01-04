import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class db_connect {

    public Connection conn = null;

    public db_connect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");     //Specifying driver for driver manager we are using.
            String url = "jdbc:mysql://localhost:3306/mycrawler";   //localhost address to connect with
            conn = DriverManager.getConnection(url,"root","");  //here my password is NULL username is root
            System.out.println("conn built");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet runSql(String sql) throws SQLException
    {
        Statement statement = conn.createStatement();
        return statement.executeQuery(sql);
    }

    public boolean runSql2(String sql) throws SQLException
    {
        Statement statement = conn.createStatement();   //statement is an interface to represent SQL query and it needs a connection object to do so.
        return statement.execute(sql);
    }

    @Override
    protected void finalize() throws Throwable
    {
        if (conn != null || !conn.isClosed()) {
            conn.close();
        }
    }
}