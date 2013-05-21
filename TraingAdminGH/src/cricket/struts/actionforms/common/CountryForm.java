package cricket.struts.actionforms.common;

import org.apache.struts.action.ActionForm;

public class CountryForm extends ActionForm
{

  private String id;

  private String country;

  private String shortName;

  public String getId()
  {
  return id;
  }

  public void setId(String id)
  {
  this.id = id;
  }

  public String getCountry()
  {
  return country;
  }

  public void setCountry(String country)
  {
  this.country = country;
  }

  public String getShortName()
  {
  return shortName;
  }

  public void setShortName(String shortName)
  {
  this.shortName = shortName;
  }

}
