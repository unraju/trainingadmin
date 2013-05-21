package cricket.hibernate.bf.common;

import java.io.Serializable;

import cricket.struts.actionforms.common.CricRulesForm;

public class CricRules implements Serializable
{

  private Long id;

  private String rule;

  public boolean scoreRule;

  public boolean active;
  
  public boolean changePlayerAllowed;
  
  public Long  seriesId;
  

  public boolean isChangePlayerAllowed()
  {
  return changePlayerAllowed;
  }

  public void setChangePlayerAllowed(boolean changePlayerAllowed)
  {
  this.changePlayerAllowed = changePlayerAllowed;
  }

  public Long getId()
  {
  return id;
  }

  public boolean isActive()
  {
  return active;
  }

  public void setActive(boolean active)
  {
  this.active = active;
  }

  public void setId(Long id)
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

  
  public boolean isScoreRule()
  {
  return scoreRule;
  }

  public void setScoreRule(boolean scoreRule)
  {
  this.scoreRule = scoreRule;
  }

  public Long getSeriesId()
  {
  return seriesId;
  }

  public void setSeriesId(Long seriesId)
  {
  this.seriesId = seriesId;
  }

  public CricRulesForm getActionForm()
  {
  CricRulesForm rulesForm = new CricRulesForm();
  rulesForm.setId(id.toString());
  rulesForm.setRule(rule);
  rulesForm.setScoreRule(scoreRule);
  return rulesForm;
  }
}
