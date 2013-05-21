package expenditure.actionform;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class AddExpActionForm extends ActionForm
{
  
  
  public AddExpActionForm()
  {
    super();
    // TODO Auto-generated constructor stub
    
    this.cashFlowType="1";
  }

  private int id;

  private int itemNo;

  private String itemName;

  private String itemId;

  private String date;

  private String paymentType;

  private String cardType;

  private String amount;

  private String createDate;

  private String itemType;

  private String cashFlowType;

  public int getItemNo()
  {
    return itemNo;
  }

  public void setItemNo(int itemNo)
  {
    this.itemNo = itemNo;
  }

  public String getItemName()
  {
    return itemName;
  }

  public void setItemName(String itemName)
  {
    this.itemName = itemName;
  }

  public String getDate()
  {
    return date;
  }

  public void setDate(String date)
  {
    this.date = date;
  }

  public String getPaymentType()
  {
    return paymentType;
  }

  public void setPaymentType(String paymentType)
  {
    this.paymentType = paymentType;
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

  public String getCreateDate()
  {
    return createDate;
  }

  public void setCreateDate(String createDate)
  {
    this.createDate = createDate;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getItemId()
  {
    return itemId;
  }

  public void setItemId(String itemId)
  {
    this.itemId = itemId;
  }

  public String getItemType()
  {
    return itemType;
  }

  public void setItemType(String itemType)
  {
    this.itemType = itemType;
  }

  public String getCashFlowType()
  {
    return cashFlowType;
  }

  public void setCashFlowType(String cashFlowType)
  {
    this.cashFlowType = cashFlowType;
  }

  @Override
  public String toString()
  {
    // TODO Auto-generated method stub
    StringBuffer sb = new StringBuffer();
    sb.append(" Item No ").append(itemNo).append(" date ").append(date);
    return sb.toString();
  }

  @Override
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
  {
    // Requestor validations
    ActionErrors errors = new ActionErrors();
    String[] runtimeValues = new String[6];
    if (itemName == null || itemName.trim().equals(""))
    {
      runtimeValues[0] = "Item Name";
      errors.add("itemName", new ActionMessage("errors.required", runtimeValues[0]));
      this.itemName = "";
    }

    if (amount == null || amount.trim().equals(""))
    {
      runtimeValues[1] = "Amount";
      errors.add("amount", new ActionMessage("errors.required", runtimeValues[1]));
      this.amount = "";
    } else
    {
      try
      {
        Double.parseDouble(amount);
      }
      catch (Exception e)
      {
        runtimeValues[1] = "Amount";
        errors.add("amount", new ActionMessage("errors.invalid", runtimeValues[1]));
        this.amount = "";
      }
    }

    if (date == null || date.trim().equals(""))
    {
      runtimeValues[2] = "Date";
      errors.add("date", new ActionMessage("errors.required", runtimeValues[2]));
      this.date = "";

    }

    if (paymentType == null || paymentType.trim().equals(""))
    {
      runtimeValues[3] = "PaymentType";
      errors.add("paymentType", new ActionMessage("errors.required", runtimeValues[3]));
      this.paymentType = "";

    }

    if (paymentType.equalsIgnoreCase("CARD"))
    {
      if (cardType == null || cardType.trim().equals("") || cardType.equals("PleaseSelect"))
      {
        runtimeValues[4] = "CardType";
        errors.add("cardType", new ActionMessage("errors.required", runtimeValues[4]));
        this.cardType = "";

      }
    }

    return errors;
  }

  public void copy(AddExpActionForm addExpActionForm)
  {
    this.amount = addExpActionForm.getAmount();
    if (!addExpActionForm.getCardType().startsWith("Please"))
    {
      this.cardType = addExpActionForm.getCardType();
    }
    this.date = addExpActionForm.getDate();
    this.itemName = addExpActionForm.getItemName();
  }

}
