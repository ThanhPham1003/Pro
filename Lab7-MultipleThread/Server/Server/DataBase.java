package Server;

import java.sql.*;

public class DataBase {
    private String url = "jdbc:postgresql://pg:5432/studs";
    private String user = "s273610";
    private String password = "jjp810";
    private Connection con=null;
    public DataBase()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            this.con= DriverManager.getConnection(url,user,password);
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch (SQLException e) {
            System.out.println("Data transfer to DataBase have error.");
        }
    }
    public ResultSet executeQuery(String q)
    {
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(q);
            return rs;
        } catch (SQLException e) {
            System.out.println("Execute Query error: " + e.getMessage());
        }
        return null;
    }
    public int executeUpdate(String q)
    {
        try{
            Statement st = con.createStatement();
            int result = st.executeUpdate(q);
            return result;
        } catch (SQLException e) {
            System.out.println("Error Update: " + e.getMessage());
        }
        return -1;
    }
}