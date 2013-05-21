package expenditure.actionform;

import org.apache.struts.action.ActionForm;

public class ViewExpActionForm extends ActionForm
{

  private String date;

  private String toDate;

  private String itemName;

  private String paymentMode;

  private String cardType;

  private String amount;

  private String itemId;

  private String cashFlowTypeId;

  public String getItemId()
  {
    return itemId;
  }

  public void setItemId(String itemId)
  {
    this.itemId = itemId;
  }

  public String getDate()
  {
    return date;
  }

  public void setDate(String date)
  {
    this.date = date;
  }

  public String getToDate()
  {
    return toDate;
  }

  public void setToDate(String toDate)
  {
    this.toDate = toDate;
  }

  public String getItemName()
  {
    return itemName;
  }

  public void setItemName(String itemName)
  {
    this.itemName = itemName;
  }

  public String getPaymentMode()
  {
    return paymentMode;
  }

  public void setPaymentMode(String paymentMode)
  {
    this.paymentMode = paymentMode;
  }

  public String getCardType()
  {
    return cardType;
  }

  public void setCardType(String cardType)
  {
    this.cardType = cardType;
  }

  public String getAmount()
  {
    return amount;
  }

  public void setAmount(String amount)
  {
    this.amount = amount;
  }

  public String getCashFlowTypeId()
  {
    return cashFlowTypeId;
  }

  public void setCashFlowTypeId(String cashFlowTypeId)
  {
    this.cashFlowTypeId = cashFlowTypeId;
  }

}
