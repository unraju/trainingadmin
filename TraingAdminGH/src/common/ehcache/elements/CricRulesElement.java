package common.ehcache.elements;

import java.io.Serializable;
import java.util.List;

import cricket.struts.actionforms.common.CricRulesForm;

public class CricRulesElement implements Serializable
{
  public static final String CRIC_RULES = "CRIC_RULES";

  public List<CricRulesForm> cricRulesForms;

  public CricRulesElement(List<CricRulesForm> cricRulesForms)
  {
    super();
    this.cricRulesForms = cricRulesForms;
  }

  public List<CricRulesForm> getCricRulesForms()
  {
    return cricRulesForms;
  }

}
