package cricket.hibernate.bf.team;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import common.hibernate.bf.user.User;
import common.util.DateUtil;
import common.util.HibernateUtil;

import cricket.hibernate.bf.common.SunSigns;
import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.actionforms.team.UserTeamForm;

public class UserTeam implements Serializable
{

	private Long id;

	private String name;

	private Date createdDate;

	private Long score;

	private boolean active;

	private List<UserTeamPlayers> userTeamPlayers;

	private User user;

	private Long substitutions;

	private Long paidSubstitutions = 0L;

	private Long usedSubstitutions = 0L;

	private Long deductedSubScore;

	private Long userId;

	private Long seriesId;

	private Long sunSignId;

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

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
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

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public List<UserTeamPlayers> getUserTeamPlayers()
	{
		return userTeamPlayers;
	}

	public void setUserTeamPlayers(List<UserTeamPlayers> userTeamPlayers)
	{
		this.userTeamPlayers = userTeamPlayers;
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

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public void setDeductedSubScore(Long deductedSubScore)
	{
		this.deductedSubScore = deductedSubScore;
	}

	public Long getSeriesId()
	{
		return seriesId;
	}

	public void setSeriesId(Long seriesId)
	{
		this.seriesId = seriesId;
	}

	public Long getSunSignId()
	{
		return sunSignId;
	}

	public void setSunSignId(Long sunSignId)
	{
		this.sunSignId = sunSignId;
	}

	public UserTeamForm getActionForm()
	{
		UserTeamForm userTeamForm = new UserTeamForm();
		userTeamForm.setId(id);
		userTeamForm.setName(name);
		userTeamForm.setActive(active);
		List<PlayerForm> list = new ArrayList<PlayerForm>();
		if (userTeamPlayers != null)
		{
			for (UserTeamPlayers player : userTeamPlayers)
			{
				if (player != null)
				{
					list.add(player.getActionForm());
				}
			}
		}
		userTeamForm.setPlayers(list);
		userTeamForm.setScore(score);
		userTeamForm.setSubstitutions(substitutions);
		userTeamForm.setPaidSubstitutions(paidSubstitutions);
		if (usedSubstitutions != null)
		{
			userTeamForm.setUsedSubstitutions(usedSubstitutions);
		}
		userTeamForm.setDeductedSubScore(deductedSubScore);
		userTeamForm.setAllowedSubstitutions(substitutions + paidSubstitutions);
		return userTeamForm;
	}

	public UserTeamForm getActivePlayersUserTeamActionForm()
	{
		UserTeamForm userTeamForm = new UserTeamForm();
		userTeamForm.setId(id);
		userTeamForm.setName(name);
		userTeamForm.setActive(active);
		List<PlayerForm> list = new ArrayList<PlayerForm>();
		if (userTeamPlayers != null)
		{
			for (UserTeamPlayers player : userTeamPlayers)
			{
				if (player != null && player.isActive())
				{
					list.add(player.getActionForm());
				}
			}
		}
		userTeamForm.setPlayers(list);
		userTeamForm.setSubstitutions(substitutions);
		if (paidSubstitutions != null)
		{
			userTeamForm.setPaidSubstitutions(paidSubstitutions);
		}
		if (usedSubstitutions != null)
		{
			userTeamForm.setUsedSubstitutions(usedSubstitutions);
		}
		userTeamForm.setDeductedSubScore(deductedSubScore);
		userTeamForm.setAllowedSubstitutions(substitutions + paidSubstitutions);
		userTeamForm.setScore(score);
		return userTeamForm;
	}

	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}

	public UserTeamForm getUserTeamScoresForm()
	{
		UserTeamForm userTeamForm = new UserTeamForm();
		userTeamForm.setId(id);
		userTeamForm.setName(name);
		userTeamForm.setUserName(user.getName());
		userTeamForm.setActive(active);
		userTeamForm.setScore(score);
		userTeamForm.setSubstitutions(substitutions);
		userTeamForm.setPaidSubstitutions(paidSubstitutions);
		if (usedSubstitutions != null)
		{
			userTeamForm.setUsedSubstitutions(usedSubstitutions);
		}
		userTeamForm.setDeductedSubScore(deductedSubScore);
		userTeamForm.setAllowedSubstitutions(substitutions + paidSubstitutions);
		userTeamForm.setSunsignId(sunSignId);
		return userTeamForm;
	}

	public void setUserSunSign()
	{
		Date userDOB = user.getDob();
		String date = DateUtil.getStringFromDate(userDOB, "dd-MMM");

		date = date + "-2011 14:30";
		Date tempDate = null;
		try
		{
			tempDate = DateUtil.getDateFromStringFormatDATE_TIME(date);
		}

		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("### tempDate ###" + tempDate);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(SunSigns.class);
		// dd-MMM-yyyy hh:mm
		// String sDate = "01-MAY-2010 15:50";
		// String eDate = "";
		criteria.add(Restrictions.le("startDate", tempDate));
		criteria.add(Restrictions.ge("endDate", tempDate));
		List<SunSigns> sunsigns = criteria.list();
		System.out.println("### sunsigns ###" + sunsigns.size());
		if (sunsigns != null && sunsigns.size() > 0)
		{
			this.sunSignId = sunsigns.get(0).getId();
			System.out.println("### sunSignId ###" + sunsigns.get(0).getId());
		}
		else
		{
			this.sunSignId = 10L;
		}
	}

}
