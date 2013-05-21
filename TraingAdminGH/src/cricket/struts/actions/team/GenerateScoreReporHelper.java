package cricket.struts.actions.team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.util.DateUtil;
import common.util.HibernateUtil;
import common.util.UserUtil;

import cricket.hibernate.bf.player.PlayerMatchScores;
import cricket.hibernate.bf.team.CoreTeam;
import cricket.hibernate.bf.team.CoreTeamPlayers;
import cricket.hibernate.bf.team.ScoreReport;
import cricket.hibernate.bf.team.UserTeam;
import cricket.hibernate.bf.team.UserTeamPlayers;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class GenerateScoreReporHelper
{

	public List<ScoreReport> getGeneratedReportHistory()
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(ScoreReport.class);
		criteria.addOrder(Order.desc("id"));
		criteria.add(Restrictions.eq("seriesId", UserUtil.getSeries()));
		List<ScoreReport> list = criteria.list();
		return list;
	}

	public void generateUserTeamsReport()
	{
		ScoreReport scoreReport = new ScoreReport();
		scoreReport.setStatus("Success");
		scoreReport.setDiscription("Completed Successfully");
		scoreReport.setUserName(UserUtil.getCurrentUser().getName());
		try
		{
			List<UserTeam> userTeams = getAllUserTeams();
			for (UserTeam userTeam : userTeams)
			{
				if (userTeam != null && userTeam.isActive())
				{
					updateUserTeamPlayerScores(userTeam);
					/* } else
					 {
					   updateUserTeamPlayerScoresForOldSeries(userTeam);
					 }*/
				}
			}
		}
		catch (Exception e)
		{

			e.printStackTrace();
			scoreReport.setStatus("Falied");
			scoreReport.setDiscription(e.getLocalizedMessage());
		}
		scoreReport.setDate(DateUtil.getCurrentDateAsTimestamp());
		scoreReport.setSeriesId(UserUtil.getSeries());
		Session session = HibernateUtil.getSession();
		session.save(scoreReport);

	}

	private void updateUserTeamPlayerScoresForOldSeries(UserTeam userTeam)
	{
		Session session = HibernateUtil.getSession();

		if (userTeam.getSunSignId() == null || userTeam.getSunSignId().equals(""))
		{
			userTeam.setUserSunSign();
		}
		// userTeam.setUserSunSign();
		session.save(userTeam);
		// transaction.commit();
	}

	private static void updateUserTeamPlayerScores(UserTeam userTeam)
	{
		Long teamScore = 0L;
		Session session = HibernateUtil.getSession();
		for (UserTeamPlayers userTeamPlayer : userTeam.getUserTeamPlayers())
		{
			if (userTeamPlayer != null)
			{
				Long runs = 0L;
				Long wickets = 0L;
				Long catches = 0L;
				Long fours = 0L;
				Long sixers = 0L;
				Long playerScore = 0L;
				Long halfCenturies = 0L;
				Long centuries = 0L;

				List<PlayerMatchScores> playerMatchScores = getPlayerScores(userTeamPlayer);
				for (PlayerMatchScores playerScores : playerMatchScores)
				{
					if (playerScores != null)
					{
						if (playerScores.getRuns() != null)
						{
							Long tempRuns = playerScores.getRuns();
							runs = runs + tempRuns;

							if (tempRuns.intValue() >= 100)
							{
								centuries = centuries + 1;
							}
							else if (tempRuns.intValue() >= 50)
							{
								halfCenturies = halfCenturies + 1;
							}
						}
						if (playerScores.getWickets() != null)
						{
							wickets = wickets + playerScores.getWickets();
						}
						if (playerScores.getCatches() != null)
						{
							catches = catches + playerScores.getCatches();
						}
						if (playerScores.getFours() != null)
						{
							fours = fours + playerScores.getFours();
						}
						if (playerScores.getSixers() != null)
						{
							sixers = sixers + playerScores.getSixers();
						}
						playerScore = playerScore + playerScores.getScore();
					}
				}
				// Long playerScore = (runs * 1) + (wickets * 25) + (catches *
				// 10);
				if (userTeamPlayer.isCaptain())
				{
					playerScore = 2 * playerScore;
				}
				teamScore = teamScore + playerScore;
				userTeamPlayer.setRuns(runs);
				userTeamPlayer.setWickets(wickets);
				userTeamPlayer.setCatches(catches);
				userTeamPlayer.setScore(playerScore);
				userTeamPlayer.setFours(fours);
				userTeamPlayer.setSixers(sixers);
				userTeamPlayer.setHalfCenturies(halfCenturies);
				userTeamPlayer.setCenturies(centuries);
				if (userTeamPlayer.getCoreTeamName() == null || userTeamPlayer.getCoreTeamName().trim().equals(""))
				{
					userTeamPlayer.setCoreTeamName(findCoreTeamNameByPlayerId(userTeamPlayer.getPlayerId()));
				}
				session.save(userTeamPlayer);
			}
		}
		// for adding points for unused free substitutions.
		// if(userTeam.getUsedSubstitutions() <5)
		// {
		// teamScore = teamScore+(5-userTeam.getUsedSubstitutions())*25;
		// }
		userTeam.setScore(teamScore - userTeam.getDeductedSubScore());
		if (userTeam.getSunSignId() == null || userTeam.getSunSignId().equals(""))
		{
			userTeam.setUserSunSign();
		}
		// userTeam.setUserSunSign();
		session.save(userTeam);
		// transaction.commit();
	}

	private static List<PlayerMatchScores> getPlayerScores(UserTeamPlayers userTeamPlayer)
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(PlayerMatchScores.class);
		criteria.add(Restrictions.eq("playerId", userTeamPlayer.getPlayerId()));
		criteria.add(Restrictions.eq("seriesId", UserUtil.getSeries()));
		Date startDate = userTeamPlayer.getStartDate();
		Date endDate = userTeamPlayer.getEndDate();
		if (endDate == null)
		{
			// criteria.add(Restrictions.gt("date", startDate));
			endDate = DateUtil.getCurrentDateAsTimestamp();
		}
		if (startDate != null && endDate != null)
		{
			// criteria.add(Restrictions.between("date", startDate, endDate));
			criteria.add(Expression.between("date", new Date(startDate.getTime()), new Date(endDate.getTime())));
		}
		// criteria.add(Expression.between("investementDate", new
		// Date(startDate.getTime()),

		// criteria.add(Restrictions.between("date", startDate, endDate));
		List<PlayerMatchScores> list = criteria.list();
		return list;
	}

	public static void main(String args[])
	{
		try
		{
			testClass();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<UserTeam> getAllUserTeams()
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.eq("seriesId", UserUtil.getSeries()));
		criteria.add(Restrictions.eq("active", true));
		criteria.addOrder(Order.desc("score"));
		List<UserTeam> list = (ArrayList<UserTeam>) criteria.list();
		return list;

	}

	private static void testClass() throws Exception
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(PlayerMatchScores.class);
		criteria.add(Restrictions.eq("playerId", ""));
		// dd-MMM-yyyy hh:mm
		String sDate = "01-MAY-2010 15:50";
		String eDate = "";

		Date startDate = DateUtil.getDateFromStringFormatDATE_TIME(sDate);
		Date endDate = DateUtil.getDateFromStringFormatDATE_TIME(eDate);
		if (endDate == null)
		{
			criteria.add(Restrictions.gt("date", startDate));
		}
		if (startDate != null && endDate != null)
		{
			criteria.add(Restrictions.between("date", startDate, endDate));
		}
		// criteria.add(Restrictions.between("date", startDate, endDate));
		List<PlayerMatchScores> list = criteria.list();

	}

	public void generateCoreTeamsReport()
	{
		List<CoreTeam> coreTeams = (ArrayList<CoreTeam>) ManageTeamDBHelper.getAllCoreTeamBySeriesId(UserUtil
				.getSeries());
		for (CoreTeam coreTeam : coreTeams)
		{
			if (coreTeam != null && coreTeam.isActive())
			{
				updateCoreTeamPlayerScores(coreTeam);
			}
		}
	}

	private void updateCoreTeamPlayerScores(CoreTeam coreTeam)
	{
		Session session = HibernateUtil.getSession();
		for (CoreTeamPlayers coreTeamPlayer : coreTeam.getPlayers())
		{
			if (coreTeamPlayer != null)
			{
				Long runs = 0L;
				Long wickets = 0L;
				Long catches = 0L;
				Long playerScore = 0L;
				Long balls = 0L;
				Long halfCenturies = 0L;
				Long centuries = 0L;
				Long matches = 0L;
				Long fours = 0L;
				Long sixers = 0L;

				List<PlayerMatchScores> playerMatchScores = getCoreTeamPlayerScores(coreTeamPlayer, coreTeam
						.getSeries().getId());
				for (PlayerMatchScores playerScores : playerMatchScores)
				{
					if (playerScores != null)
					{
						if (playerScores.getRuns() != null)
						{
							runs = runs + playerScores.getRuns();
							if (playerScores.getRuns().intValue() >= 100)
							{
								centuries = centuries + 1;
							}
							else if (playerScores.getRuns().intValue() >= 50)
							{
								halfCenturies = halfCenturies + 1;
							}
						}
						if (playerScores.getWickets() != null)
						{
							wickets = wickets + playerScores.getWickets();
						}
						if (playerScores.getCatches() != null)
						{
							catches = catches + playerScores.getCatches();
						}
						if (playerScores.getBalls() != null)
						{
							balls = balls + playerScores.getBalls();
						}
						if (playerScores.getScore() != null)
						{
							playerScore = playerScore + playerScores.getScore();
						}
						if (playerScores.getFours() != null)
						{
							fours = fours + playerScores.getFours();
						}
						if (playerScores.getSixers() != null)
						{
							sixers = sixers + playerScores.getSixers();
						}

						matches++;
					}
				}
				if (runs > 0) coreTeamPlayer.setRuns(runs);
				if (balls > 0) coreTeamPlayer.setBalls(balls);
				if (wickets > 0) coreTeamPlayer.setWickets(wickets);
				if (catches > 0) coreTeamPlayer.setCatches(catches);
				coreTeamPlayer.setScore(playerScore);
				if (fours > 0) coreTeamPlayer.setFours(fours);
				if (sixers > 0) coreTeamPlayer.setSixers(sixers);

				if (halfCenturies > 0)
				{
					coreTeamPlayer.setHalfCenturies(halfCenturies.toString());
				}
				if (centuries > 0)
				{
					coreTeamPlayer.setCenturies(centuries.toString());
				}
				if (matches > 0)
				{
					coreTeamPlayer.setMatches(matches);
				}
				session.save(coreTeamPlayer);
			}
		}
	}

	private List<PlayerMatchScores> getCoreTeamPlayerScores(CoreTeamPlayers coreTeamPlayer, Long seriesId)
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(PlayerMatchScores.class);
		criteria.add(Restrictions.eq("playerId", coreTeamPlayer.getPlayerId()));
		criteria.add(Restrictions.eq("seriesId", seriesId));
		List<PlayerMatchScores> list = criteria.list();
		return list;
	}

	public static String findCoreTeamNameByPlayerId(Long playerId)
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(CoreTeam.class);
		Criteria pcriteria = criteria.createCriteria("players");
		pcriteria.add(Restrictions.eq("playerId", playerId));
		Criteria criteria2 = criteria.createCriteria("series");
		criteria2.add(Restrictions.eq("id", UserUtil.getSeries()));

		Collection<CoreTeam> list = criteria.list();
		for (CoreTeam coreTeam : list)
		{
			if (coreTeam != null)
			{
				return coreTeam.getCode();
			}
		}
		return "";
	}

}
