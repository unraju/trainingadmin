package common.ehcache.elements;

import java.io.Serializable;
import java.util.List;

import cricket.struts.actionforms.common.SCTNewsForm;

public class SctNewsElement implements Serializable
{
  public static final String SCT_NEWS = "SCT_NEWS";

  public List<SCTNewsForm> sctNewsForms;

  public SctNewsElement(List<SCTNewsForm> sctNewsForms)
  {
    super();
    this.sctNewsForms = sctNewsForms;
  }

  public List<SCTNewsForm> getSctNewsForms()
  {
    return sctNewsForms;
  }

}
