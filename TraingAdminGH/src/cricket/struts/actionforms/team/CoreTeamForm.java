package cricket.struts.actionforms.team;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import common.util.Utility;

public class CoreTeamForm extends ActionForm
{

  public CoreTeamForm()
  {
  super();
  this.groupName = "";
  }

  private Long id;

  private String name;
  
  private String code;

  private String createdDate;

  private String city;

  private String country;

  private String countryId;

  private String owner;

  private boolean active;

  private String groupName;

  private String groupId;

  private String seriesName;

  private String seriesId;
  
  private String playersCount;

  public String getSeriesName()
  {
  return seriesName;
  }

  public String getGroupId()
  {
  return groupId;
  }

  public void setGroupId(String groupId)
  {
  this.groupId = groupId;
  }

  public void setSeriesName(String seriesName)
  {
  this.seriesName = seriesName;
  }

  public String getSeriesId()
  {
  return seriesId;
  }

  public void setSeriesId(String seriesId)
  {
  this.seriesId = seriesId;
  }

  public String getGroupName()
  {
  return groupName;
  }

  public void setGroupName(String groupName)
  {
  this.groupName = groupName;
  }

  private List<PlayerForm> players;

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

  public String getCity()
  {
  return city;
  }

  public void setCity(String city)
  {
  this.city = city;
  }

  public String getCountry()
  {
  return country;
  }

  public void setCountry(String country)
  {
  this.country = country;
  }

  public String getOwner()
  {
  return owner;
  }

  public void setOwner(String owner)
  {
  this.owner = owner;
  }

  public boolean isActive()
  {
  return active;
  }

  public String getActiveString()
  {
  return Utility.convertBooleanToString(active);
  }

  public void setActive(boolean active)
  {
  this.active = active;
  }

  public String getCreatedDate()
  {
  return createdDate;
  }

  public void setCreatedDate(String createdDate)
  {
  this.createdDate = createdDate;
  }

  public List<PlayerForm> getPlayers()
  {
  return players;
  }

  public void setPlayers(List<PlayerForm> players)
  {
  this.players = players;
  }

  public String getCode()
  {
  return code;
  }

  public void setCode(String code)
  {
  this.code = code;
  }

  public String getPlayersCount()
  {
  return playersCount;
  }

  public void setPlayersCount(String playersCount)
  {
  this.playersCount = playersCount;
  }

  public void createDummyPlayers(int seriesPlayersCount)
  {
  int playerssize = seriesPlayersCount;
  if (playersCount != null && !playersCount.trim().equals("") )
  {
    playerssize =  Integer.parseInt(playersCount);
  }
  if (players == null)
  {
    players = new ArrayList<PlayerForm>();
  }
  int tempPlyersCount = 1000 + playerssize;
  int size = tempPlyersCount - players.size();
  for (Long count = 1000L; count < size; count++)
  {
    PlayerForm form = new PlayerForm();
    form.setName("");
    form.setCountry("");
    form.setId(count);
    form.setActive(true);
    if (active)
    {
      form.setActiveString("Yes");
    }
    else
    {
      form.setActiveString("No");
    }
    /*
     * String skill; if (count < 8) { skill = Constants.SKILL_BM; } else if
     * (count == 8) { skill = Constants.SKILL_WK; } else if (count <= 11) {
     * skill = Constants.SKILL_BL; } else { skill = Constants.SKILL_BM; }
     * form.setSkill(skill);
     */
    players.add(form);
  }

  }

  public String getCountryId()
  {
  return countryId;
  }

  public void setCountryId(String countryId)
  {
  this.countryId = countryId;
  }

  @Override
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
      {
  ActionErrors errors = new ActionErrors();
    String operation = request.getParameter("operation");
    if (operation.equalsIgnoreCase("Cancel"))
    {
      return errors;
    }
      // Requestor validations
      String[] runtimeValues = new String[6];
      if (name == null || name.trim().equals(""))
      {
        runtimeValues[0] = "Name";
        errors.add("name", new ActionMessage("errors.required", runtimeValues[0]));
      }
    
      if (countryId == null || countryId.trim().startsWith("Pl"))
      {
         runtimeValues[1] = "Country";
        errors.add("countryId", new ActionMessage("errors.required",  runtimeValues[1]));
      }
    
      if (seriesId == null || seriesId.trim().startsWith("Pl"))
      {
        runtimeValues[2] = "Series";
        errors.add("seriesId", new ActionMessage("errors.required", runtimeValues[2]));
      }
    
      return errors;
    
      }
  

  public String toString() {
  return ToStringBuilder.reflectionToString(this);
}
}
