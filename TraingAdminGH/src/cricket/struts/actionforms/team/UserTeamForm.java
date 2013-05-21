package cricket.struts.actionforms.team;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.action.ActionForm;

import common.util.Constants;

public class UserTeamForm extends ActionForm
{

  private Long id;

  private String name;

  private String createdDate;

  private Long score;

  private boolean active;

  private List<PlayerForm> players;

  private Long usedId;
  
  private String userName;

  private Long substitutions;

  private Long paidSubstitutions = 0L;

  private Long usedSubstitutions = 0L;

  private Long deductedSubScore;
  
  private Long allowedSubstitutions;
  
  private boolean blockSubstitutions;
  
  private Long avilableSubstitutions;
  
  private Long sunsignId;
  
  public Long getAvilableSubstitutions()
  {
  return (allowedSubstitutions+paidSubstitutions-usedSubstitutions);
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Long getScore()
  {
    return score;
  }

  public void setScore(Long score)
  {
    this.score = score;
  }

  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean active)
  {
    this.active = active;
  }

  public Long getUsedId()
  {
    return usedId;
  }

  public void setUsedId(Long usedId)
  {
    this.usedId = usedId;
  }

  public String getCreatedDate()
  {
    return createdDate;
  }

  public void setCreatedDate(String createdDate)
  {
    this.createdDate = createdDate;
  }

  public void setPlayers(List<PlayerForm> players)
  {
    this.players = players;
  }

  public List<PlayerForm> getPlayers()
  {
    return players;
  }

  public Long getSubstitutions()
  {
    return substitutions;
  }

  public void setSubstitutions(Long substitutions)
  {
    this.substitutions = substitutions;
  }

  public Long getPaidSubstitutions()
  {
    return paidSubstitutions;
  }

  public void setPaidSubstitutions(Long paidSubstitutions)
  {
    this.paidSubstitutions = paidSubstitutions;
  }

  public Long getUsedSubstitutions()
  {
    return usedSubstitutions;
  }

  public void setUsedSubstitutions(Long usedSubstitutions)
  {
    this.usedSubstitutions = usedSubstitutions;
  }

  public Long getDeductedSubScore()
  {
    return deductedSubScore;
  }

  public void setDeductedSubScore(Long deductedSubScore)
  {
    this.deductedSubScore = deductedSubScore;
  }

  public Long getAllowedSubstitutions()
  {
    return allowedSubstitutions;
  }

  public void setAllowedSubstitutions(Long allowedSubstitutions)
  {
    this.allowedSubstitutions = allowedSubstitutions;
  }

  public boolean isBlockSubstitutions()
  {
  return blockSubstitutions;
  }

   public Long getSunsignId()
  {
  return sunsignId;
  }

  public void setSunsignId(Long sunsignId)
  {
  this.sunsignId = sunsignId;
  }

  public void setBlockSubstitutions(boolean blockSubstitutions)
  {
  this.blockSubstitutions = blockSubstitutions;
  }

  public void createDummyPlayers()
  {
    players = new ArrayList<PlayerForm>();
    for (Long count = 1000L; count < 1011; count++)
    {
      PlayerForm form = new PlayerForm();
      form.setName("");
      form.setCountry("");
      form.setId(count);
      form.setActive(true);
      form.setActiveString("Yes");
      if (count < 1006)
        
      {
        form.setSkill(Constants.SKILL_BM);
      }
      else if (count == 1006)
      {
        form.setSkill(Constants.SKILL_WK);
      }
      else
      {
        form.setSkill(Constants.SKILL_BL);
      }
      players.add(form);
    }
  }

  public String getUserName()
  {
  return userName;
  }

  public void setUserName(String userName)
  {
  this.userName = userName;
  }

  public String toString() {
  return ToStringBuilder.reflectionToString(this);
}

}
