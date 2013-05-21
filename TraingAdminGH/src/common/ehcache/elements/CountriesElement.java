package common.ehcache.elements;

import java.io.Serializable;
import java.util.List;

import cricket.struts.actionforms.common.CountryForm;

public class CountriesElement implements Serializable
{
  public static final String COUNTRIES = "COUNTRIES";

  public List<CountryForm> countryForms;

  public CountriesElement(List<CountryForm> countryForms)
  {
    super();
    this.countryForms = countryForms;
  }

  public List<CountryForm> getCountryForms()
  {
    return countryForms;
  }

}
