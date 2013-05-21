package common.ehcache.elements;

import java.io.Serializable;
import java.util.List;

import cricket.struts.actionforms.common.SunSignForm;

public class SunSignsElement implements Serializable
{
  public static final String SUNSIGNS = "SUNSIGNS";

  public List<SunSignForm> sunSignForms;

  public SunSignsElement(List<SunSignForm> sunSignStaticData)
  {
  this.sunSignForms = sunSignStaticData;
  }

  public List<SunSignForm> getSunSignForms()
  {
  return sunSignForms;
  }

  public void setSunSignForms(List<SunSignForm> sunSignForms)
  {
  this.sunSignForms = sunSignForms;
  }

}
