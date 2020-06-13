package Server;

import java.sql.*;

public class DataBase {
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String user = "postgres";
    private String password = "trangdepgai";
    private Connection con=null;
    public DataBase()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            this.con= DriverManager.getConnection(url,user,password);
        }
        catch (ClassNotFoundException e){
            System.out.println("Tran Trung Duc");
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
            PreparedStatement st = con.prepareStatement(q);
            int result = st.executeUpdate();
            return result;
        } catch (SQLException e) {
            System.out.println("Error Update: " + e.getMessage());
        }
        return -1;
    }
}
