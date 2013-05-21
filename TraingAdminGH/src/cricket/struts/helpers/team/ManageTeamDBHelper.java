package cricket.struts.helpers.team;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.hibernate.bf.user.User;
import common.struts.actionforms.user.UserForm;
import common.util.DateUtil;
import common.util.HibernateUtil;
import common.util.UserUtil;

import cricket.hibernate.bf.common.CountryHome;
import cricket.hibernate.bf.common.SCTNews;
import cricket.hibernate.bf.common.Series;
import cricket.hibernate.bf.common.SeriesHome;
import cricket.hibernate.bf.common.TeamGroupHome;
import cricket.hibernate.bf.player.Player;
import cricket.hibernate.bf.player.PlayerMatchScores;
import cricket.hibernate.bf.player.PlayerSubstitution;
import cricket.hibernate.bf.team.CoreTeam;
import cricket.hibernate.bf.team.CoreTeamPlayers;
import cricket.hibernate.bf.team.TeamShedule;
import cricket.hibernate.bf.team.UserTeam;
import cricket.hibernate.bf.team.UserTeamPlayers;
import cricket.struts.actionforms.common.SCTNewsForm;
import cricket.struts.actionforms.team.CoreTeamForm;
import cricket.struts.actionforms.team.CurrentMatchSheduleForm;
import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.actionforms.team.PlayerMatchScoresForm;
import cricket.struts.actionforms.team.TeamSheduleForm;
import cricket.struts.actionforms.team.UserTeamForm;

public class ManageTeamDBHelper {

	public static Collection<CoreTeamForm> getAllCoreTeamDetails(Long seriesId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(CoreTeam.class);
		Criteria seriesCriteria = criteria.createCriteria("series");
		seriesCriteria.add(Restrictions.eq("id", seriesId));
		Collection<CoreTeam> list = criteria.list();
		List<CoreTeamForm> coreTeamForms = new ArrayList<CoreTeamForm>();
		for (CoreTeam coreTeam : list) {
			coreTeamForms.add(coreTeam.getActionForm());
		}
		return coreTeamForms;
	}

	public void deleteCoreTeam(CoreTeamForm coreTeamForm) {
		Session session = HibernateUtil.getSession();
		CoreTeam coreTeam = findCoreTeamById(coreTeamForm.getId());
		Collection<CoreTeamPlayers> players = coreTeam.getPlayers();

		for (CoreTeamPlayers player : players) {
			coreTeam.getPlayers().remove(player);
			// player.setCoreTeam(null);
			// session.delete(player);
		}
		for (CoreTeamPlayers player : players) {
			// coreTeam.getPlayers().remove(player);
			session.delete(player);
		}
		// coreTeam.setPlayers(null);
		session.delete(coreTeam);
	}

	public void saveCoreTeamDetails(CoreTeamForm coreTeamForm) throws ServletException {
		Session session = HibernateUtil.getSession();
		CoreTeam coreTeam = null;
		if (coreTeamForm.getId() != null && coreTeamForm.getId().intValue() != 0) {
			coreTeam = findCoreTeamById(coreTeamForm.getId());
		} else {
			coreTeam = new CoreTeam();
		}
		coreTeam.setName(coreTeamForm.getName());
		coreTeam.setCode(coreTeamForm.getCode());
		coreTeam.setCity(coreTeamForm.getCity());
		coreTeam.setCountry(CountryHome.findCountryById(Long.parseLong(coreTeamForm.getCountryId())));
		if (coreTeamForm.getGroupId() != null && !coreTeamForm.getGroupId().equals("None")) {
			coreTeam.setTeamGroup(TeamGroupHome.findTeamGroupById(Long.parseLong(coreTeamForm.getGroupId())));
		}
		coreTeam.setSeries(SeriesHome.findSeriesById(Long.parseLong(coreTeamForm.getSeriesId())));
		coreTeam.setOwner(coreTeamForm.getOwner());
		coreTeam.setCreatedDate(DateUtil.getCurrentDateAsTimestamp());
		coreTeam.setActive(coreTeamForm.isActive());
		if (coreTeamForm.getPlayersCount() != null && !coreTeamForm.getPlayersCount().trim().equals("")) {
			coreTeam.setPlayersCount(Long.parseLong(coreTeamForm.getPlayersCount()));
		}
		session.save(coreTeam);
		updateCoreTeamPlayers(coreTeam, coreTeamForm);

		session.save(coreTeam);
		session.flush();
		coreTeamForm.setId(coreTeam.getId());

	}

	private void updateCoreTeamPlayers(CoreTeam coreTeam, CoreTeamForm coreTeamForm) {
		Session session = HibernateUtil.getSession();
		for (PlayerForm playerForm : coreTeamForm.getPlayers()) {
			CoreTeamPlayers coreTeamPlayers = findCoreTeamPlayer(playerForm.getId());// ,
																						// playerForm.getId());
			if (coreTeamPlayers == null) {
				coreTeamPlayers = new CoreTeamPlayers();
				coreTeamPlayers.setScore(0L);
			}
			coreTeamPlayers.setPlayerId(playerForm.getCoreTeamPlayerId());
			coreTeamPlayers.setCoreTeamId(coreTeam.getId());
			coreTeamPlayers.setStartDate(DateUtil.getCurrentDateAsTimestamp());
			coreTeamPlayers.setActive(playerForm.isActive());
			coreTeamPlayers.setCaptain(playerForm.isCaptain());
			session.save(coreTeamPlayers);
		}

	}

	private CoreTeamPlayers findCoreTeamPlayer(Long coreTeamId)// , Long
																// playerId)
	{
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(CoreTeamPlayers.class);
		criteria.add(Restrictions.eq("id", coreTeamId));
		// criteria.add(Restrictions.eq("playerId", playerId));
		List<CoreTeamPlayers> list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	private CoreTeam findCoreTeamById(Long id) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(CoreTeam.class);
		criteria.add(Restrictions.eq("id", id));
		List<CoreTeam> list = criteria.list();
		return list.get(0);
	}

	// return on active players
	public UserTeamForm getUserTeamDetails(Long userId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.eq("userId", userId));
		criteria.add(Restrictions.eq("seriesId", UserUtil.getSeries()));
		Criteria playerCriteria = criteria.createCriteria("userTeamPlayers");
		playerCriteria.add(Restrictions.eq("active", true));
		Collection<UserTeam> list = criteria.list();
		for (UserTeam userTeam : list) {
			if (userTeam != null) {
				return userTeam.getActivePlayersUserTeamActionForm();
			}
		}
		return null;
	}

	// return on all players including active & non active
	public UserTeamForm getAllUserTeamPlayers(Long userId) {

		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.eq("userId", userId));
		criteria.add(Restrictions.eq("seriesId", UserUtil.getSeries()));
		Collection<UserTeam> list = criteria.list();
		for (UserTeam userTeam : list) {
			if (userTeam != null) {
				return userTeam.getActionForm();
			}
		}
		return null;
	}

	public UserTeamForm getAllUserTeamDetails(User user) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.eq("user", user));
		Criteria playerCriteria = criteria.createCriteria("userTeamPlayers");
		// Criteria activePlayerCriteria =
		// playerCriteria.createCriteria("player");
		playerCriteria.add(Restrictions.eq("active", true));
		Collection<UserTeam> list = criteria.list();
		for (UserTeam userTeam : list) {
			if (userTeam != null) {
				return userTeam.getActionForm();
			}
		}
		return null;
	}

	public List<TeamSheduleForm> getTeamSheduleDetails(Long seriesId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(TeamShedule.class);
		Criteria seriesCriteria = criteria.createCriteria("series");
		seriesCriteria.add(Restrictions.eq("id", seriesId));
		criteria.addOrder(Order.asc("id"));
		Collection<TeamShedule> list = criteria.list();
		List<TeamSheduleForm> list2 = new ArrayList<TeamSheduleForm>();
		for (TeamShedule teamShedule : list) {
			if (teamShedule != null && teamShedule.getId() != null) {
				list2.add(teamShedule.getActionForm());
			}
		}
		return list2;
	}

	public void saveTeamSheduleDetails(List<TeamSheduleForm> teamShedules) throws Exception {
		Session session = HibernateUtil.getSession();
		// Transaction transaction = session.beginTransaction();
		for (TeamSheduleForm teamSheduleForm : teamShedules) {
			TeamShedule teamShedule = null;
			if (teamSheduleForm.getId() != null) {

				teamShedule = findTeamSheduleById(teamSheduleForm.getId());
			} else {
				teamShedule = new TeamShedule();
				teamShedule.setSeries(SeriesHome.findSeriesById(UserUtil.getSeries()));
			}
			teamShedule.setMatchName(teamSheduleForm.getMatchName());
			CoreTeam firstCoreTeam = findCoreTeamById(teamSheduleForm.getFirstTeamId());
			CoreTeam secondCoreTeam = findCoreTeamById(teamSheduleForm.getSecondTeamId());
			teamShedule.setFirstTeam(firstCoreTeam);
			teamShedule.setSecondTeam(secondCoreTeam);
			teamShedule.setFirstTeamName(firstCoreTeam.getName());
			teamShedule.setSecondTeamName(secondCoreTeam.getName());
			Date date = null;
			try {
				date = DateUtil
						.getDateFromStringFormatDATE_TIME(teamSheduleForm.getDate() + " " + teamSheduleForm.getTime());
			} catch (ParseException e) {

				e.printStackTrace();
				throw e;
			}
			teamShedule.setDate(date);
			teamShedule.setVenue(teamSheduleForm.getVenue());
			session.save(teamShedule);
			session.flush();
		}
		// transaction.commit();
	}

	public TeamShedule findTeamSheduleById(Long id) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(TeamShedule.class);
		criteria.add(Restrictions.eq("id", id));
		List<TeamShedule> list = criteria.list();
		return list.get(0);
	}

	public Player findPlayerById(Long id) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Player.class);
		criteria.add(Restrictions.eq("id", id));
		List<Player> list = criteria.list();
		return list.get(0);
	}

	public void updateTeamPlayerScores(List<PlayerMatchScoresForm> playerMatchScoresForms, TeamSheduleForm teamSheduleForm)
			throws Exception {
		Session session = HibernateUtil.getSession();
		TeamShedule teamShedule = findTeamSheduleById(playerMatchScoresForms.get(0).getMatchId());
		teamShedule.setFirstTeamScore(teamSheduleForm.getFirstTeamScore());
		teamShedule.setSecondTeamScore(teamSheduleForm.getSecondTeamScore());
		teamShedule.setFirstTeamExtras(Long.parseLong(teamSheduleForm.getFirstTeamExtras()));
		teamShedule.setSecondTeamExtras(Long.parseLong(teamSheduleForm.getSecondTeamExtras()));
		teamShedule.setMatchResult(teamSheduleForm.getMatchResult());
		teamShedule.setManOfMatch(teamSheduleForm.getManOfMatch());
		session.save(teamShedule);
		for (PlayerMatchScoresForm playerMatchScoresForm : playerMatchScoresForms) {
			updatePlayerMatchScore(playerMatchScoresForm);
		}

		// transaction.commit();
	}

	private void updatePlayerMatchScore(PlayerMatchScoresForm playerMatchScoresForm) {
		Session session = HibernateUtil.getSession();
		PlayerMatchScores playerMatchScores = null;
		if (playerMatchScoresForm.getId() != null && playerMatchScoresForm.getId() != 0) {
			playerMatchScores = findPlayerMatchScore(playerMatchScoresForm.getId());
		}
		if (playerMatchScores == null) {
			playerMatchScores = new PlayerMatchScores();
			playerMatchScores.setMatchId(playerMatchScoresForm.getMatchId());
			playerMatchScores.setPlayerId(playerMatchScoresForm.getPlayerId());
			playerMatchScores.setDate(playerMatchScoresForm.getDate());// DateUtil.getCurrentDateAsTimestamp());
			playerMatchScores.setSeriesId(playerMatchScoresForm.getSeriesId());
		}

		if (playerMatchScoresForm.getRuns() != null && !playerMatchScoresForm.getRuns().trim().equals("")) {
			playerMatchScores.setRuns(Long.parseLong(playerMatchScoresForm.getRuns()));
		}
		if (playerMatchScoresForm.getBalls() != null && !playerMatchScoresForm.getBalls().trim().equals("")) {
			playerMatchScores.setBalls(Long.parseLong(playerMatchScoresForm.getBalls()));
		}
		if (playerMatchScoresForm.getWickets() != null && !playerMatchScoresForm.getWickets().trim().equals("")) {
			playerMatchScores.setWickets(Long.parseLong(playerMatchScoresForm.getWickets()));
		}
		if (playerMatchScoresForm.getCatches() != null && !playerMatchScoresForm.getCatches().trim().equals("")) {
			playerMatchScores.setCatches(Long.parseLong(playerMatchScoresForm.getCatches()));
		}
		if (playerMatchScoresForm.getFours() != null && !playerMatchScoresForm.getFours().trim().equals("")) {
			playerMatchScores.setFours(Long.parseLong(playerMatchScoresForm.getFours()));
		}
		if (playerMatchScoresForm.getSixers() != null && !playerMatchScoresForm.getSixers().trim().equals("")) {
			playerMatchScores.setSixers(Long.parseLong(playerMatchScoresForm.getSixers()));
		}
		if (playerMatchScoresForm.getOrder() != null && !playerMatchScoresForm.getOrder().trim().equals("")) {
			playerMatchScores.setOrder(Long.parseLong(playerMatchScoresForm.getOrder()));
		}
		playerMatchScores.setScore(playerMatchScores.generateScore());

		// //
		// System.out.println("### playerMatchScores ###"+playerMatchScores);
		session.save(playerMatchScores);
	}

	public PlayerMatchScores findPlayerMatchScore(Long id) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(PlayerMatchScores.class);
		criteria.add(Restrictions.eq("id", id));
		List<PlayerMatchScores> list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public PlayerMatchScores findPlayerMatchScore(Long matchId, Long playerId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(PlayerMatchScores.class);
		criteria.add(Restrictions.eq("matchId", matchId));
		criteria.add(Restrictions.eq("playerId", playerId));
		List<PlayerMatchScores> list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<PlayerMatchScores> findPlayerSeriesScore(Long seriesId, Long playerId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(PlayerMatchScores.class);
		criteria.add(Restrictions.eq("seriesId", seriesId));
		criteria.add(Restrictions.eq("playerId", playerId));
		List<PlayerMatchScores> list = criteria.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public UserTeam saveUserTeamDetails(UserTeamForm userTeamForm, UserForm user) {
		Session session = HibernateUtil.getSession();
		UserTeam userTeam = null;
		if (userTeamForm.getId() != null && userTeamForm.getId().intValue() != 0) {
			userTeam = findUserTeamById(userTeamForm.getId());
		} else {
			userTeam = new UserTeam();
			//userTeam.setUserSunSign();
			userTeam.setUserId(user.getId());
			userTeam.setSubstitutions(15L);
			userTeam.setActive(true);
			userTeam.setUsedSubstitutions(0L);
			userTeam.setDeductedSubScore(0L);
			userTeam.setPaidSubstitutions(0L);
			userTeam.setScore(0L);
			userTeam.setSeriesId(UserUtil.getSeries());
		}
		userTeam.setName(userTeamForm.getName());
		userTeam.setCreatedDate(DateUtil.getCurrentDateAsTimestampinGMT0530());
		session.save(userTeam);
		updateUserTeamPlayers(userTeam, userTeamForm);
		session.save(userTeam);
		// session.flush();
		return userTeam;
	}

	private void updateUserTeamPlayers(UserTeam userTeam, UserTeamForm userTeamForm) {
		Session session = HibernateUtil.getSession();
		List<UserTeamPlayers> userTeamPlayers = null;
		if (userTeam.getUserTeamPlayers() != null) {
			userTeamPlayers = userTeam.getUserTeamPlayers();
		} else {
			userTeamPlayers = new ArrayList<UserTeamPlayers>();
			userTeam.setUserTeamPlayers(userTeamPlayers);
		}
		for (PlayerForm playerForm : userTeamForm.getPlayers()) {
			UserTeamPlayers userTeamPlayer = findUserTeamPlayer(userTeam.getId(), playerForm.getId());
			if (userTeamPlayer == null) {
				userTeamPlayer = new UserTeamPlayers();
			}
			userTeamPlayer.setPlayerId(playerForm.getChangedPlayerId());
			userTeamPlayer.setUserTeamId(userTeam.getId());
			userTeamPlayer.setStartDate(DateUtil.getCurrentDateAsTimestamp());
			userTeamPlayer.setActive(true);
			userTeamPlayer.setCaptain(playerForm.isCaptain());
			userTeamPlayer.setCoreTeamName(ChnageTeamPlayerHelper.findCoreTeamNameByPlayerId(playerForm.getChangedPlayerId()));
			session.save(userTeamPlayer);
			// userTeamPlayers.add(userTeamPlayer);
		}
	}

	private UserTeamPlayers findUserTeamPlayer(Long userTeamId, Long playerId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(UserTeamPlayers.class);
		criteria.add(Restrictions.eq("userTeamId", userTeamId));
		criteria.add(Restrictions.eq("playerId", playerId));
		List<UserTeamPlayers> list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public UserTeam findUserTeamById(Long id) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.eq("id", id));
		List<UserTeam> list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<UserTeam> getAllUserTeams() {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.addOrder(Order.desc("score"));
		List<UserTeam> list = (ArrayList<UserTeam>) criteria.list();
		return list;

	}

	public List<PlayerSubstitution> getAllSubstitutedetails(Long userId) {
		Session session = HibernateUtil.getSession();
		UserTeam userTeam = getUserTeam(userId);
		if (userTeam == null) {
			return null;
		}
		Criteria criteria = session.createCriteria(PlayerSubstitution.class);
		criteria.add(Restrictions.eq("userTeamId", userTeam.getId()));
		criteria.add(Restrictions.eq("seriesId", UserUtil.getSeries()));
		List<PlayerSubstitution> list = (ArrayList<PlayerSubstitution>) criteria.list();
		return list;
	}

	// return on all players including active & non active
	public UserTeam getUserTeam(Long userId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.eq("userId", userId));
		criteria.add(Restrictions.eq("seriesId", UserUtil.getSeries()));
		Collection<UserTeam> list = criteria.list();
		for (UserTeam userTeam : list) {
			if (userTeam != null) {
				return userTeam;
			}
		}
		return null;
	}

	public static Collection<CoreTeam> getAllCoreTeamBySeriesId(Long seriesId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(CoreTeam.class);
		Criteria criteria2 = criteria.createCriteria("series");
		criteria2.add(Restrictions.eq("id", seriesId));
		criteria.add(Restrictions.eq("active", true));
		Collection<CoreTeam> list = criteria.list();
		return list;
	}

	public static SCTNews findNewsById(long newsId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(SCTNews.class);
		criteria.add(Restrictions.eq("id", newsId));
		List<SCTNews> list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public static void deleteNews(long parseLong) {
		Session session = HibernateUtil.getSession();
		SCTNews sctNews = findNewsById(parseLong);
		session.delete(sctNews);
	}

	public static void addSCTNews(SCTNewsForm sctNewsForm) {
		Session session = HibernateUtil.getSession();
		SCTNews sctNews = null;
		if (sctNewsForm.getId() != null && sctNewsForm.getId().intValue() != 0) {
			sctNews = findNewsById(sctNewsForm.getId());
		} else {
			sctNews = new SCTNews();
			sctNews.setDate(DateUtil.getCurrentDateAsTimestamp());
		}
		sctNews.setNews(sctNewsForm.getNews());
		sctNews.setLive(sctNewsForm.isLive());
		if (sctNewsForm.getPriority() != null && !sctNewsForm.getPriority().trim().equals(""))
			sctNews.setPriority(Long.parseLong(sctNewsForm.getPriority()));
		sctNews.setSeriesId(UserUtil.getSeries());
		session.save(sctNews);
		sctNewsForm.setId(sctNews.getId());
	}

	public static List<SCTNewsForm> getAllSCTNews() {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(SCTNews.class);
		criteria.add(Restrictions.eq("seriesId", UserUtil.getSeries()));
		criteria.addOrder(Order.desc("priority"));
		List<SCTNews> list = criteria.list();
		List<SCTNewsForm> sctNewsForms = new ArrayList<SCTNewsForm>();
		if (list != null && list.size() > 0) {
			for (SCTNews sctNews : list) {
				sctNewsForms.add(sctNews.getActionForm());
			}
		}
		return sctNewsForms;
	}

	public static List<CurrentMatchSheduleForm> setLiveMatchDetails(Long seriesId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(TeamShedule.class);
		Criteria criteria1 = criteria.createCriteria("series");
		criteria1.add(Restrictions.eq("id", seriesId));

		Series series = getCurrentSeriesById(seriesId);

		Date startDate = DateUtil.getCurrentDateAsTimestampinGMT0530();
		startDate.setHours(startDate.getHours() - 36);
		Date endDate = DateUtil.getCurrentDateAsTimestampinGMT0530();
		endDate.setHours(startDate.getHours() + 48);
		Date now = DateUtil.getCurrentDateAsTimestampinGMT0530();
		// before series start
		if (now.before(series.getStartDate())) {
			if (startDate != null && endDate != null) {
				criteria.add(Expression.gt("date", startDate));
			}
		}
		// while running the series
		else if (now.after(series.getStartDate()) && now.before(series.getEndDate())) {
			if (startDate != null)// && endDate != null)
			{
				criteria.add(Restrictions.between("date", startDate, endDate));
			}
		}
		// after series
		else if (now.after(series.getEndDate())) {
			criteria.addOrder(Order.desc("id"));
			// criteria.add(Expression.gt("date", startDate));
		}

		criteria.addOrder(Order.asc("id"));
		List<TeamShedule> list = criteria.list();
		List<CurrentMatchSheduleForm> formsList = new ArrayList<CurrentMatchSheduleForm>();
		if (list != null && list.size() > 0) {
			for (TeamShedule teamShedule : list) {
				formsList.add(teamShedule.getCurrentTeamSheduleForm());
				if (formsList.size() == 5)
					break;
			}
		}
		return formsList;
	}

	public static Series getCurrentSeriesById(Long seriesId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Series.class);
		criteria.add(Restrictions.eq("id", seriesId));
		List<Series> list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<PlayerMatchScores> getUserPlayerMatchScores(long id, StringBuffer playerName) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(UserTeamPlayers.class);
		criteria.add(Restrictions.eq("playerId", id));
		List<UserTeamPlayers> list = criteria.list();
		UserTeamPlayers userTeamPlayer = null;
		if (list != null && list.size() > 0) {
			userTeamPlayer = list.get(0);
		} else {
			return null;
		}
		if (userTeamPlayer != null) {
			playerName.append(userTeamPlayer.getPlayer().getName());
			Criteria criteria1 = session.createCriteria(PlayerMatchScores.class);
			criteria1.add(Restrictions.eq("seriesId", UserUtil.getSeries()));
			criteria1.add(Restrictions.eq("playerId", userTeamPlayer.getPlayerId()));
			Date startDate = userTeamPlayer.getStartDate();
			Date endDate = userTeamPlayer.getEndDate();
			if (endDate == null) {
				criteria1.add(Restrictions.gt("date", startDate));
			}
			if (startDate != null && endDate != null) {
				criteria1.add(Restrictions.between("date", startDate, endDate));
			}
			List<PlayerMatchScores> list1 = criteria1.list();
			if (list1 != null && list1.size() > 0) {
				return list1;
			}
		}
		return null;
	}

}
