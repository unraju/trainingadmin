package expenditure.businessfunction;

import expenditure.actionform.CashFlowTypeTO;

public class CashFlowType
{
  private long id;

  private String cashFlowType;

  private boolean active;

  public String getCashFlowType()
  {
    return cashFlowType;
  }

  public void setCashFlowType(String cashFlowType)
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

  public long getId()
  {
    return id;
  }

  public void setId(long id)
  {
    this.id = id;
  }

  public CashFlowTypeTO getActionForm()
  {
  CashFlowTypeTO cashFlowTypeTO = new CashFlowTypeTO();
  cashFlowTypeTO.setCashFlowType(cashFlowType);
  cashFlowTypeTO.setId(id);
  return cashFlowTypeTO;
  }

}
