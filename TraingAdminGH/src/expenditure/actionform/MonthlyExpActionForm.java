package expenditure.actionform;

import org.apache.struts.action.ActionForm;

public class MonthlyExpActionForm extends ActionForm
{

  private int month;

  private int year;

  private String itemId;

  private String cashFlowType;

  public String getItemId()
  {
    return itemId;
  }

  public void setItemId(String itemId)
  {
    this.itemId = itemId;
  }

  public int getMonth()
  {
    return month;
  }

  public void setMonth(int month)
  {
    this.month = month;
  }

  public int getYear()
  {
    return year;
  }

  public void setYear(int year)
  {
    this.year = year;
  }

  public String getCashFlowType()
  {
    return cashFlowType;
  }

  public void setCashFlowType(String cashFlowType)
  {
    this.cashFlowType = cashFlowType;
  }

}
