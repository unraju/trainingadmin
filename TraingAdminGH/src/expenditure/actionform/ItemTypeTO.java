package expenditure.actionform;

import java.io.Serializable;

public class ItemTypeTO implements Serializable
{

  private Long id;

  private String code;

  private String description;
  
  private String cashFlowType;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
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
