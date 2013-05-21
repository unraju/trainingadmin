package expenditure.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.util.DateUtil;
import common.util.SQLConnectionUtil;

import expenditure.actionform.AddExpActionForm;
import expenditure.actionform.ViewExpActionForm;

public class SQLDBHelper
{

  public static void addExpDetails(AddExpActionForm expTo) throws SQLException
  {
    int itemNo = expTo.getItemNo();
    String itemName = expTo.getItemName();
    java.util.Date purchaseDate = new java.util.Date();// expTo.getPurchaseDate().toString();
    String paymentType = expTo.getPaymentType();
    String cardType = expTo.getCardType();
    Double amount = Double.parseDouble(expTo.getAmount());

    /*
     * String insertQuery = "insert into expenditure
     * (item_no,item_name,payment_mode,card_type,amount) values" +
     * "("+itemNo+","+itemName+","+paymentType+","+cardType+"," +amount+");";
     */

    String insertQuery = "insert into expenditure (" + "item_name," + "payment_mode," + "card_type," + "amount," + "purchase_date," + "created_date)"
        + "values(?,?,?,?,?,?)";

    Connection con = null;
    try
    {
      con = SQLConnectionUtil.getMySQLConnection();
      PreparedStatement stmt = con.prepareStatement(insertQuery);

      stmt.setString(1, itemName);
      stmt.setString(2, paymentType);
      stmt.setString(3, cardType);
      stmt.setDouble(4, amount);
      stmt.setTimestamp(5, DateUtil.getCurrentDateAsTimestamp());
      stmt.setTimestamp(6, DateUtil.getCurrentDateAsTimestamp());

      int c = stmt.executeUpdate();

      if (c > 0)
        System.out.println(c);
      else
        System.out.println("else" + c);

      // con.commit();
    }
    finally
    {
      closeConnection(con);
    }
  }

  private static void closeConnection(Connection con)
  {

    try
    {
      con.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

  }

  public static void updateExpDetails(AddExpActionForm expTo)
  {

    int itemNo = expTo.getItemNo();
    String itemName = expTo.getItemName();
    String purchaseDate = expTo.getDate().toString();
    String paymentType = expTo.getPaymentType();
    String cardType = expTo.getCardType();
    Double amount = Double.parseDouble(expTo.getAmount());

    Connection con = null;
    try
    {
      con = SQLConnectionUtil.getMySQLConnection();
      Statement stmt = con.createStatement();

      String updateQuery = "update expenditure set( )" + "(" + itemName + "," + paymentType + "," + cardType + "," + purchaseDate + "," + amount
          + ")where item_no=" + itemNo + ";";

      boolean b = stmt.execute(updateQuery);

    }
    catch (SQLException sqle)
    {
      sqle.printStackTrace();
    }
    finally
    {
      closeConnection(con);
    }
  }

  public static void deleteExpDetails(AddExpActionForm expTo)
  {

    Connection con = null;
    try
    {
      con = SQLConnectionUtil.getMySQLConnection();
      Statement stmt = con.createStatement();

    }
    catch (SQLException sqle)
    {
      sqle.printStackTrace();
    }
    finally
    {
      closeConnection(con);
    }
  }

  public static List<AddExpActionForm> viewExpDetails(ViewExpActionForm searchTo)
  {

    String fromDate = searchTo.getDate();
    String toDate = searchTo.getToDate();
    String pType = searchTo.getPaymentMode();
    String cType = searchTo.getCardType();

    List<AddExpActionForm> expArray = new ArrayList<AddExpActionForm>();

    Connection con = null;
    try
    {
      con = SQLConnectionUtil.getMySQLConnection();
      Statement stmt = con.createStatement();

      String selectQuery = "select * from expenditure where payment_Mode=" + pType + "and card_type=" + cType + ";";
      // where purchase_Date between"+fromDate+"and"+toDate+
      // "and payment_Mode="+pType+"and card_type="+cType+";";

      ResultSet rs = stmt.executeQuery(selectQuery);

      while (rs.next())
      {
        AddExpActionForm expTo = new AddExpActionForm();
        expTo.setItemNo(rs.getInt("item_no"));
        expTo.setItemName(rs.getString("item_name"));
        expTo.setPaymentType(rs.getString("payment_mode"));
        expTo.setCardType(rs.getString("card_type"));
        expTo.setAmount((rs.getDouble("amount") + ""));
        expTo.setDate(DateUtil.getStringFromTimestamp(rs.getTimestamp("purchase_date")));
        expTo.setCreateDate(DateUtil.getStringFromTimestamp(rs.getTimestamp("purchase_date")));

        expArray.add(expTo);

      }

    }
    catch (SQLException sqle)
    {
      sqle.printStackTrace();
    }
    finally
    {
      closeConnection(con);
    }

    return expArray;
  }
}
