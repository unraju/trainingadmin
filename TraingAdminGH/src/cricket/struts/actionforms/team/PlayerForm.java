package cricket.struts.actionforms.team;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import webservices.jaxb.WSPlayerTO;

import common.util.Constants;

import cricket.hibernate.bf.player.Player;
import cricket.struts.actionforms.common.SeriesTypeForm;
import cricket.struts.actionforms.player.PlayerScoresForm;

public class PlayerForm extends ActionForm
{

	private Long id;

	private String name;

	private String country;

	private String countryName;

	private String countryShortName;

	private boolean active;

	private String skill;

	private String score = "";

	private String startDate;

	private String endDate;

	private boolean captain;

	private String captainString;

	private boolean selected;

	private String runs = "";

	private String balls = "";

	List<PlayerScoresForm> playerScores = new ArrayList<PlayerScoresForm>();

	private String wickets = "";

	private String catches = "";

	private long coreTeamId;

	private String coreTeamName = "";

	private String activeString = "Y";

	private Long changedPlayerId;

	private Long tmpPlayerId;

	private Long coreTeamPlayerId;

	private String matches = "";

	private String centuries = "";

	private String halfCenturies = "";

	private String strikeRate = "";

	private String fours = "";

	private String sixers = "";

	private String order = "";

	public PlayerForm()
	{
		super();
		// TODO Auto-generated constructor stub
		// skill = Constants.SKILL_BM;
		active = true;
	}

	public String getBalls()
	{
		return balls;
	}

	public void setBalls(String balls)
	{
		this.balls = balls;
	}

	public String getCountryName()
	{
		return countryName;
	}

	public String getCountryShortName()
	{
		return countryShortName;
	}

	public void setCountryShortName(String countryShortName)
	{
		this.countryShortName = countryShortName;
	}

	public void setCountryName(String countryName)
	{
		this.countryName = countryName;
	}

	public Long getCoreTeamPlayerId()
	{
		return coreTeamPlayerId;
	}

	public void setCoreTeamPlayerId(Long coreTeamPlayerId)
	{
		this.coreTeamPlayerId = coreTeamPlayerId;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
		if (tmpPlayerId == null)
		{
			this.tmpPlayerId = id;
		}
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCountry()
	{
		return country;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		ActionErrors errors = new ActionErrors();
		String operation = request.getParameter("operation");

		if (operation.equals(Constants.SAVE_BTN_VALUE))
		{
			// Requestor validations
			String[] runtimeValues = new String[6];
			if (name == null || name.trim().equals(""))
			{
				runtimeValues[0] = "Name";
				errors.add("name", new ActionMessage("errors.required", runtimeValues[0]));
			}

			if (country == null || country.trim().startsWith("Please"))
			{
				runtimeValues[1] = "Country";
				errors.add("country", new ActionMessage("errors.required", runtimeValues[1]));
			}

			if (skill == null || skill.trim().equals(""))
			{
				runtimeValues[2] = "Skill";
				errors.add("skill", new ActionMessage("errors.required", runtimeValues[2]));
			}
		}
		return errors;

	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public String getSkill()
	{
		return skill;
	}

	public void setSkill(String skill)
	{
		this.skill = skill;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public boolean isCaptain()
	{
		return captain;
	}

	public void setCaptain(boolean captain)
	{
		this.captain = captain;
		if (captain)
		{
			captainString = "Yes";
		}
		else
		{
			captainString = "No";
		}
	}

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}

	public long getCoreTeamId()
	{
		return coreTeamId;
	}

	public void setCoreTeamId(long coreTeamId)
	{
		this.coreTeamId = coreTeamId;
	}

	public String getCoreTeamName()
	{
		return coreTeamName;
	}

	public void setCoreTeamName(String coreTeamName)
	{
		this.coreTeamName = coreTeamName;
	}

	public String getScore()
	{
		return score;
	}

	public void setScore(String score)
	{
		this.score = score;
	}

	public String getRuns()
	{
		return runs;
	}

	public void setRuns(String runs)
	{
		this.runs = runs;
	}

	public String getWickets()
	{
		return wickets;
	}

	public void setWickets(String wickets)
	{
		this.wickets = wickets;
	}

	public String getCatches()
	{
		return catches;
	}

	public void setCatches(String catches)
	{
		this.catches = catches;
	}

	public String getActiveString()
	{
		return activeString;
	}

	public void setActiveString(String activeString)
	{
		this.activeString = activeString;
	}

	public String getCaptainString()
	{
		return captainString;
	}

	public void setCaptainString(String captainString)
	{
		this.captainString = captainString;
	}

	public Long getChangedPlayerId()
	{
		return changedPlayerId;
	}

	public void setChangedPlayerId(Long changedPlayerId)
	{
		this.changedPlayerId = changedPlayerId;
	}

	public Long getTmpPlayerId()
	{
		return tmpPlayerId;
	}

	public void setTmpPlayerId(Long tmpPlayerId)
	{
		this.tmpPlayerId = tmpPlayerId;
	}

	public String getMatches()
	{
		return matches;
	}

	public void setMatches(String matches)
	{
		this.matches = matches;
	}

	public String getCenturies()
	{
		return centuries;
	}

	public void setCenturies(String centuries)
	{
		this.centuries = centuries;
	}

	public String getHalfCenturies()
	{
		return halfCenturies;
	}

	public void setHalfCenturies(String halfCenturies)
	{
		this.halfCenturies = halfCenturies;
	}

	public String getStrikeRate()
	{
		return strikeRate;
	}

	public void setStrikeRate(String strikeRate)
	{
		this.strikeRate = strikeRate;
	}

	public String getOrder()
	{
		return order;
	}

	public void setOrder(String order)
	{
		this.order = order;
	}

	public Player createBO()
	{
		Player player = null;
		if (name != null && !name.trim().equals(""))
		{
			player = new Player();
			player.setName(name);
			// player.setCountry(country);
			player.setActive(active);
			player.setSkill(skill);
		}
		return player;
	}

	public List<PlayerScoresForm> getPlayerScores()
	{
		return playerScores;
	}

	public void setPlayerScores(List<PlayerScoresForm> playerScores)
	{
		this.playerScores = playerScores;
	}

	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}

	public String getFours()
	{
		return fours;
	}

	public void setFours(String fours)
	{
		this.fours = fours;
	}

	public String getSixers()
	{
		return sixers;
	}

	public void setSixers(String sixers)
	{
		this.sixers = sixers;
	}

	public void addDefaultScores(List<SeriesTypeForm> seriesTypeForms)
	{

		for (SeriesTypeForm seriesTypeForm : seriesTypeForms)
		{
			boolean exites = false;
			for (PlayerScoresForm playerScoresForm : playerScores)
			{
				if (playerScoresForm != null
						&& seriesTypeForm.getId().intValue() == playerScoresForm.getSeriesTypeId().intValue())
				{
					exites = true;
					break;
				}
			}
			if (!exites)
			{
				PlayerScoresForm playerScoresForm = new PlayerScoresForm();
				playerScoresForm.setId(0L);
				playerScoresForm.setSeriesTypeId(seriesTypeForm.getId());
				playerScoresForm.setSeriesTypeName(seriesTypeForm.getName());
				playerScores.add(playerScoresForm);
			}
		}

	}

	public void addPlayerScores(PlayerScoresForm forScoresForm)
	{
		playerScores.add(forScoresForm);

	}

	public WSPlayerTO getWSPlayerTO()
	{
		WSPlayerTO playerTO = new WSPlayerTO();
		playerTO.setName(name);
		playerTO.setCoreTeamName(coreTeamName);
		playerTO.setCountry(country);
		playerTO.setRuns(runs);
		playerTO.setScore(score);
		playerTO.setSkill(skill);
		playerTO.setWickets(wickets);
		playerTO.setCatches(catches);
		playerTO.setBalls(balls);
		playerTO.setCaptainString(captainString);
		playerTO.setStrikeRate(strikeRate);
		return playerTO;
	}
}
