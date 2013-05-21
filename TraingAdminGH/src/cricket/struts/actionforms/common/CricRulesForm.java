package cricket.struts.actionforms.common;

import org.apache.struts.action.ActionForm;

public class CricRulesForm extends ActionForm
{

  private String id;

  private String rule;

  private String shortName;

  public boolean isScoreRule;

  public String getId()
  {
  return id;
  }

  public void setId(String id)
  {
  this.id = id;
  }

  public String getRule()
  {
  return rule;
  }

  public void setRule(String rule)
  {
  this.rule = rule;
  }

  public String getShortName()
  {
  return shortName;
  }

  public void setShortName(String shortName)
  {
  this.shortName = shortName;
  }

  public boolean isScoreRule()
  {
  return isScoreRule;
  }

  public void setScoreRule(boolean isScoreRule)
  {
  this.isScoreRule = isScoreRule;
  }

}
