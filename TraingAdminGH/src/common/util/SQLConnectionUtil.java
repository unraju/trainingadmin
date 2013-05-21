package common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnectionUtil
{

  static public final String driver = "com.mysql.jdbc.Driver";

  static public final String connection = "jdbc:mysql://localhost:3306/expenditure";

  static public final String user = "root";

  static public final String password = "unraju";

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    try
    {
      getMySQLConnection();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static Connection getMySQLConnection() throws SQLException
  {

    Connection con = null;
    try
    {
      Class.forName(driver);
      con = DriverManager.getConnection(connection, user, password);
    /* System.out.println("Jdbc Mysql Connection String :");
      System.out.println(connection);
      System.out.println("User Name :" + user);
      System.out.println("Password  :" + password);
      System.out.println("con  :" + con);
 */
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return con;
  }

}
