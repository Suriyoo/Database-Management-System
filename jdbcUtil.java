import java.sql.*;

public class jdbcUtil {
    private Connection c = null;
    private PreparedStatement p = null;
    private ResultSet r = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Connection createConnection(){

        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys2021", "root","elevis123");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Connection Object creation failed");
        }

        return c;
    }


    public PreparedStatement createPreparedStatement(String sql){

        Connection c = createConnection();
        try {
            p = c.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("PreparedStatement Object creation failed");
        }

        return p;
    }



    public void close() {
        if (c != null) {
            try {
                c.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (p != null) {
            try {
                p.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (r != null) {
            try {
                r.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
