package expenditure.actionform;

import java.io.Serializable;

public class CashFlowTypeTO implements Serializable
{

  private Long id;

  private String cashFlowType;

  public CashFlowTypeTO()
  {
    this.cashFlowType="1";
  }
  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
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
