package cricket.hibernate.bf.common;

import java.io.Serializable;

import cricket.struts.actionforms.common.CountryForm;

public class Country implements Serializable
{

  private Long id;

  private String country;

  private String shortName;

  public Long getId()
  {
  return id;
  }

  public void setId(Long id)
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

  public CountryForm getActionForm()
  {
  CountryForm countryForm = new CountryForm();
  countryForm.setId(id.toString());
  countryForm.setCountry(country);
  countryForm.setShortName(shortName);
  return countryForm;
  }
}
