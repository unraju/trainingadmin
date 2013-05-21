package expenditure.businessfunction;

import java.io.Serializable;

import expenditure.actionform.ItemTypeTO;

public class Itemtype extends LookupType implements Serializable
{

  private CashFlowType cashFlowType;

  private boolean active;

  public CashFlowType getCashFlowType()
  {
    return cashFlowType;
  }

  public void setCashFlowType(CashFlowType cashFlowType)
  {
    this.cashFlowType = cashFlowType;
  }

  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean active)
  {
    this.active = active;
  }

  public ItemTypeTO getActionForm()
  {
  ItemTypeTO itemTypeTO = new ItemTypeTO();
  itemTypeTO.setCode(getCode());
  itemTypeTO.setId(getId());
  itemTypeTO.setDescription(getDiscription());
  
  return itemTypeTO;
  }

}
