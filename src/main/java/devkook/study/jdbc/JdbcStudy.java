package devkook.study.jdbc;


import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JdbcStudy {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://us-cdbr-cb-east-01.cleardb.net:3306/cb_devkook";

    //  Database credentials
    static final String USER = "bf2680dae9d429";
    static final String PASS = "fd7bcc05";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            stmt = getStatement(conn);

            if (args[0].equals("SELECT")) {
                rs = printSelect(stmt);
            } else if (args[0].equals("INSERT")) {

                for(long i=2; 1000 >= i; i++){
                    long id = i;
                    String title = "WHY ? " + i;
                    String creator = "T-" + i;
                    String type = "type=" + i;
                    String date = getCurrentTimeStamp();

                    boolean isSuccess = doInsert(stmt,id,title,creator,type,date);
                    System.out.println("INSERT-" + isSuccess + "-" + i);
                }


            } else {
                //TODO
            }


            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                //TODO empty
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        System.out.println("Goodbye!");
    }//end main


    private static Statement getStatement(Connection conn) throws SQLException {
        Statement stmt = null;
        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        return stmt;
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        //STEP 2: Register JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        //STEP 3: Open a connection
        System.out.println("Connecting to database...");
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        return conn;
    }

    //http://www.mkyong.com/jdbc/jdbc-statement-example-insert-a-record/
    private static boolean doInsert(Statement stmt, long id, String title, String creator, String type, String date) throws SQLException {
        String insertTableSQL = String.format("INSERT INTO book(id, title, creator, type, date) VALUES (%d,'%s','%s','%s', '%s')",
                id,
                title,
                creator,
                type,
                date);

        // execute insert SQL stetement
        boolean isSuccess = stmt.execute(insertTableSQL);
        System.out.println(insertTableSQL);

        return  isSuccess;
    }

    private static ResultSet printSelect(Statement stmt) throws SQLException {
        String sql;
        sql = "SELECT id, title, creator, type, date FROM book";
        ResultSet rs = stmt.executeQuery(sql);

        //STEP 5: Extract data from result set
        while (rs.next()) {
            //Retrieve by column name
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String creator = rs.getString("creator");
            String type = rs.getString("type");
            Date date = rs.getDate("date");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", TITLE: " + title);
            System.out.print(", CREATOR: " + creator);
            System.out.print(", TYPE: " + type);
            System.out.println(", DATE: " + date);
        }
        return rs;
    }

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static String getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return dateFormat.format(today.getTime());
    }
}



