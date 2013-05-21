package expenditure.businessfunction;

import java.io.Serializable;

public class LookupType implements Serializable
{

  private Long id;

  private String code;

  private String discription;


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

  public String getDiscription()
  {
    return discription;
  }

  public void setDiscription(String discription)
  {
    this.discription = discription;
  }




}
