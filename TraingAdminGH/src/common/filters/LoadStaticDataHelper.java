package common.filters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.ehcache.CricCache;
import common.ehcache.elements.BestPlayersElement;
import common.ehcache.elements.CoreTeamsElement;
import common.ehcache.elements.CountriesElement;
import common.ehcache.elements.CricRulesElement;

import common.ehcache.elements.FrontPageSeriesDataElement;
import common.ehcache.elements.SeriesElement;
import common.ehcache.elements.SunSignsElement;
import common.ehcache.elements.TeamSheduleElement;
import common.ehcache.elements.UserRolesElement;
import common.ehcache.elements.UserTeamsElement;
import common.ehcache.xmlcachemanager.ActionXmlCacheManager;
import common.hibernate.bf.user.UserRole;
import common.struts.actionforms.user.UserRoleForm;
import common.util.Constants;
import common.util.HibernateUtil;
import common.util.UserUtil;

import cricket.hibernate.bf.common.Country;
import cricket.hibernate.bf.common.CricRules;
import cricket.hibernate.bf.common.SCTNews;
import cricket.hibernate.bf.common.Series;
import cricket.hibernate.bf.common.SeriesType;
import cricket.hibernate.bf.common.SunSigns;
import cricket.hibernate.bf.team.CoreTeam;
import cricket.hibernate.bf.team.CoreTeamPlayerRunComparator;
import cricket.hibernate.bf.team.CoreTeamPlayerScoreComparator;
import cricket.hibernate.bf.team.CoreTeamPlayerWicketsComparator;
import cricket.hibernate.bf.team.CoreTeamPlayers;
import cricket.hibernate.bf.team.TeamShedule;
import cricket.hibernate.bf.team.UserTeam;
import cricket.struts.actionforms.common.CountryForm;
import cricket.struts.actionforms.common.CricRulesForm;
import cricket.struts.actionforms.common.SCTNewsForm;
import cricket.struts.actionforms.common.SeriesForm;
import cricket.struts.actionforms.common.SeriesTypeForm;
import cricket.struts.actionforms.common.SunSignForm;
import cricket.struts.actionforms.team.CoreTeamForm;
import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.actionforms.team.TeamSheduleForm;
import cricket.struts.actionforms.team.UserTeamForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;


public class LoadStaticDataHelper {
	Session session = null;

	public LoadStaticDataHelper() {
		super();
	}

	public void loadstaticData(ServletContext context) throws ServletException {

		System.out.println("# loadstaticData -  Start #");
		this.session = HibernateUtil.getSession();
		session.beginTransaction();
		try {
			loadStaticDataDetails(context);
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			session.close();
		}
		System.out.println("# loadstaticData -  Ends #");
	}

	public void loadStaticDataDetails(ServletContext context) {

		List<SeriesForm> serieses = getSeriesStaticData();
		if (context != null) {
			context.setAttribute(Constants.DEFAULT_USER_ROLES, getDefaultUserRoleForms());
		}
		for (SeriesForm seriesForm : serieses) {
			ActionXmlCacheManager.getInstance(seriesForm.getId().toString());
		}
		ActionXmlCacheManager.getInstance(Constants.COMMON_CACHE);
		ActionXmlCacheManager.getInstance(Constants.EXPENDITURE_CACHE);
		/*
		 * else if(context != null) {
		 * context.setAttribute(seriesForm.getId().toString(),
		 * setSeriedSpecificCoreData(seriesForm.getId()));
		 * context.setAttribute(Constants.SERIESES, serieses);
		 * context.setAttribute(Constants.SERIESES_TYPES,
		 * getSeriesTypeStaticData());
		 * context.setAttribute(Constants.USER_ROLES, getActiveUserRoleForms());
		 * context.setAttribute(Constants.COUNTRIES, getCountryStaticData()); }
		 */
	}

	public Map<String, Object> setSeriedSpecificCoreData(Long seriesId) {
		this.session = HibernateUtil.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.SEARCHED_CORE_TEAMS, getAllCoreTeamDetails(seriesId));
		map.put(Constants.TEAM_SHEDULE, getTeamSheduleDetails(seriesId));
		map.put(Constants.USER_TEAMS_SCORES, getAllUserTeamScores(seriesId));
		map.put(Constants.CRIC_RULES, getCricRules(seriesId));
		map.put(Constants.SERIES_BEST_PLAYERS, getSeriesBestPlayers(seriesId));
		map.put(Constants.SCT_NEWS, getSCTNews(seriesId));
		map.put(Constants.LIVE_MATCH_SHEDULES, ManageTeamDBHelper.setLiveMatchDetails(seriesId));
		return map;
	}

	public void setSeriedSpecificCoreData(ActionXmlCacheManager cache, Long seriesId) {
		this.session = HibernateUtil.getSession();
		// Map<String, Object> map = new HashMap<String, Object>();
		CoreTeamsElement coreTeamsElement = new CoreTeamsElement(getAllCoreTeamDetails(seriesId));
		cache.put(coreTeamsElement.SEARCHED_CORE_TEAMS, coreTeamsElement);

		TeamSheduleElement sheduleElement = new TeamSheduleElement(getTeamSheduleDetails(seriesId));
		cache.put(sheduleElement.TEAM_SHEDULE, sheduleElement);

		List<UserTeamForm> userTeamForms = getAllUserTeamScores(seriesId);
		UserTeamsElement userTeamsElement = new UserTeamsElement(userTeamForms);
		cache.put(userTeamsElement.USER_TEAMS, userTeamsElement);

		CricRulesElement cricRulesElement = new CricRulesElement(getCricRules(seriesId));
		cache.put(cricRulesElement.CRIC_RULES, cricRulesElement);

		List<PlayerForm> bestPlayers = getSeriesBestPlayers(seriesId);
		BestPlayersElement bestPlayersElement = new BestPlayersElement(bestPlayers);
		cache.put(bestPlayersElement.BEST_PLAYERS, bestPlayersElement);

		FrontPageSeriesDataElement frontPageSeriesDataElement = new FrontPageSeriesDataElement(userTeamForms,
				bestPlayers, ManageTeamDBHelper.setLiveMatchDetails(seriesId), getSCTNews(seriesId));

		cache.put(frontPageSeriesDataElement.FP_SERIES_DATA, frontPageSeriesDataElement);
	}

	public void loadCommonCacheDetails(ActionXmlCacheManager cache) {
		this.session = HibernateUtil.getSession();

		CountriesElement countriesElement = new CountriesElement(getCountryStaticData());
		cache.put(countriesElement.COUNTRIES, countriesElement);

		SeriesElement seriesElement = new SeriesElement(getSeriesStaticData(), getSeriesTypeStaticData());
		cache.put(seriesElement.SERIES, seriesElement);

		UserRolesElement userRolesElement = new UserRolesElement(getActiveUserRoleForms());
		cache.put(userRolesElement.USER_ROLES, userRolesElement);

		SunSignsElement sunSignsElement = new SunSignsElement(getSunSignStaticData());
		cache.put(sunSignsElement.SUNSIGNS, sunSignsElement);

	}

	
	public Map<String, Object> publishSeriesDetail(Long seriesId) {
		session = HibernateUtil.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.SEARCHED_CORE_TEAMS, getAllCoreTeamDetails(seriesId));
		map.put(Constants.TEAM_SHEDULE, getTeamSheduleDetails(seriesId));
		map.put(Constants.USER_TEAMS_SCORES, getAllUserTeamScores(seriesId));
		map.put(Constants.CRIC_RULES, getCricRules(seriesId));
		map.put(Constants.SERIES_BEST_PLAYERS, getSeriesBestPlayers(seriesId));
		map.put(Constants.SCT_NEWS, getSCTNews(seriesId));
		map.put(Constants.LIVE_MATCH_SHEDULES, ManageTeamDBHelper.setLiveMatchDetails(seriesId));
		return map;
	}

	public List<SCTNewsForm> getSCTNews(Long seriesId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(SCTNews.class);
		criteria.add(Restrictions.eq("seriesId", seriesId));
		criteria.add(Restrictions.eq("live", true));
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

	public Map<String, List<PlayerForm>> getSeriesBestPlayersMap(Long seriesId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(CoreTeamPlayers.class);
		Criteria criteria1 = criteria.createCriteria("coreTeam");
		Criteria criteria2 = criteria1.createCriteria("series");
		criteria2.add(Restrictions.eq("id", seriesId));
		criteria.add(Restrictions.eq("active", true));
		// Criteria criteria1 = criteria.createCriteria("series");
		// criteria1.add(Restrictions.eq("seriesId", 1));
		List<CoreTeamPlayers> list = criteria.list();

		List<PlayerForm> bestPlayers = new ArrayList<PlayerForm>();
		if (list != null && list.size() > 0) {
			for (CoreTeamPlayers coreTeamPlayers : list) {
				if (coreTeamPlayers.getScore() != null && coreTeamPlayers.getScore().intValue() > 50) {
					bestPlayers.add(coreTeamPlayers.getCorePlayerActionForm());
				}
			}
		}
		List<PlayerForm> bestBatsmens = new ArrayList<PlayerForm>();
		List<PlayerForm> bestBowlers = new ArrayList<PlayerForm>();
		for (PlayerForm playerForm : bestPlayers) {
			if (playerForm != null && playerForm.getSkill() != null
					&& (playerForm.getRuns() != null && Integer.parseInt(playerForm.getRuns()) > 25)) {
				bestBatsmens.add(playerForm);
			}
			if (Integer.parseInt(playerForm.getWickets()) > 1) {
				bestBowlers.add(playerForm);
			}
		}
		Map<String, List<PlayerForm>> corePlayersMap = new HashMap<String, List<PlayerForm>>();
		Collections.sort(bestPlayers, new CoreTeamPlayerScoreComparator());
		Collections.sort(bestBowlers, new CoreTeamPlayerWicketsComparator());
		Collections.sort(bestBatsmens, new CoreTeamPlayerRunComparator());
		corePlayersMap.put(Constants.BEST_BATSMENS, bestBatsmens);
		corePlayersMap.put(Constants.BEST_BOWLERS, bestBowlers);
		corePlayersMap.put(Constants.BEST_PLAYERS, bestPlayers);

		return corePlayersMap;
	}

	public List<PlayerForm> getSeriesBestPlayers(Long seriesId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(CoreTeamPlayers.class);
		Criteria criteria1 = criteria.createCriteria("coreTeam");
		Criteria criteria2 = criteria1.createCriteria("series");
		criteria2.add(Restrictions.eq("id", seriesId));
		criteria.add(Restrictions.eq("active", true));
		// Criteria criteria1 = criteria.createCriteria("series");
		// criteria1.add(Restrictions.eq("seriesId", 1));
		List<CoreTeamPlayers> list = criteria.list();

		List<PlayerForm> bestPlayers = new ArrayList<PlayerForm>();
		if (list != null && list.size() > 0) {
			for (CoreTeamPlayers coreTeamPlayers : list) {
				if (coreTeamPlayers.getScore() != null && coreTeamPlayers.getScore().intValue() > 25) {
					bestPlayers.add(coreTeamPlayers.getCorePlayerActionForm());
				}
			}
		}

		return bestPlayers;
	}

	public List<CountryForm> getCountryStaticData() {

		Criteria criteria = session.createCriteria(Country.class);
		List<Country> list = criteria.list();
		List<CountryForm> countryForms = new ArrayList<CountryForm>();
		for (Country country : list) {
			countryForms.add(country.getActionForm());
		}
		return countryForms;

	}

	public List<SunSignForm> getSunSignStaticData() {

		Criteria criteria = session.createCriteria(SunSigns.class);
		criteria.addOrder(Order.asc("id"));
		List<SunSigns> list = criteria.list();
		List<SunSignForm> sunSignForms = new ArrayList<SunSignForm>();
		for (SunSigns sunSign : list) {
			sunSignForms.add(sunSign.getActionForm());
		}
		return sunSignForms;

	}

	public List<SeriesForm> getSeriesStaticData() {

		Criteria criteria = session.createCriteria(Series.class);
		List<Series> list = criteria.list();
		List<SeriesForm> seriesForms = new ArrayList<SeriesForm>();
		for (Series series : list) {
			seriesForms.add(series.getActionForm());
		}
		return seriesForms;
	}

	public List<SeriesTypeForm> getSeriesTypeStaticData() {
		Criteria criteria = session.createCriteria(SeriesType.class);
		List<SeriesType> list = criteria.list();
		List<SeriesTypeForm> seriesTypeForms = new ArrayList<SeriesTypeForm>();
		for (SeriesType seriesType : list) {
			seriesTypeForms.add(seriesType.getActionForm());
		}
		return seriesTypeForms;
	}

	public List<CoreTeamForm> getAllCoreTeamDetails(Long seriesId) {
		Criteria criteria = session.createCriteria(CoreTeam.class);
		Criteria criteria1 = criteria.createCriteria("series");
		criteria1.add(Restrictions.eq("id", seriesId));
		// Criteria criteria2 = criteria.createCriteria("players");
		// criteria2.addOrder(Order.desc("score"));
		Collection<CoreTeam> list = criteria.list();

		List<CoreTeamForm> coreTeamForms = new ArrayList<CoreTeamForm>();
		for (CoreTeam coreTeam : list) {
			coreTeamForms.add(coreTeam.getActionForm());
		}
		return coreTeamForms;
	}

	public List<TeamSheduleForm> getTeamSheduleDetails(Long seriesId) {
		Criteria criteria = session.createCriteria(TeamShedule.class);
		Criteria criteria1 = criteria.createCriteria("series");
		criteria1.add(Restrictions.eq("id", seriesId));
		criteria.addOrder(Order.asc("id"));
		Collection<TeamShedule> list = criteria.list();
		List<TeamSheduleForm> list2 = new ArrayList<TeamSheduleForm>();
		for (TeamShedule teamShedule : list) {
			if (teamShedule != null && teamShedule.getId() != null) {
				list2.add(teamShedule.getActionFormForView());
			}
		}
		return list2;
	}

	public List<UserRoleForm> getActiveUserRoleForms() {
		Criteria criteria = session.createCriteria(UserRole.class);
		// criteria.add(Restrictions.eq("defaultRole", true));
		criteria.addOrder(Order.asc("priority"));
		List<UserRole> list = criteria.list();
		List<UserRoleForm> userRoleforms = new ArrayList<UserRoleForm>();
		if (list != null) {
			for (UserRole userRole : list) {
				userRoleforms.add(userRole.getUserRoleForm());
			}
		}
		return userRoleforms;
	}

	public List<UserRoleForm> getDefaultUserRoleForms() {
		Criteria criteria = session.createCriteria(UserRole.class);
		criteria.add(Restrictions.eq("defaultRole", true));
		criteria.addOrder(Order.asc("priority"));
		List<UserRole> list = criteria.list();
		List<UserRoleForm> userRoleforms = new ArrayList<UserRoleForm>();
		if (list != null) {
			for (UserRole userRole : list) {
				userRoleforms.add(userRole.getUserRoleForm());
			}
		}
		return userRoleforms;
	}

	public List<UserTeamForm> getAllUserTeamScores(Long seriesId) {
		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.eq("seriesId", seriesId));
		criteria.add(Restrictions.eq("active", true));
		criteria.addOrder(Order.desc("score"));
		List<UserTeam> list = (ArrayList<UserTeam>) criteria.list();
		List<UserTeamForm> userTeamforms = new ArrayList<UserTeamForm>();
		if (list != null) {
			for (UserTeam userTeam : list) {
				userTeamforms.add(userTeam.getUserTeamScoresForm());
			}
		}
		return userTeamforms;

	}

	public List<CricRulesForm> getCricRules(Long seriesId) {
		Criteria criteria = session.createCriteria(CricRules.class);
		criteria.add(Restrictions.eq("seriesId", seriesId));
		criteria.add(Restrictions.eq("active", true));
		criteria.addOrder(Order.asc("id"));
		List<CricRules> list = (ArrayList<CricRules>) criteria.list();
		List<CricRulesForm> rulesforms = new ArrayList<CricRulesForm>();
		if (list != null) {
			for (CricRules cricRules : list) {
				rulesforms.add(cricRules.getActionForm());
			}
		}
		return rulesforms;

	}

	public void updateSeriesDetailsCache(CricCache cache) {
		Long seriesId = UserUtil.getSeries();
		// CricCache cache =
		// ActionXmlCacheManager.getInstance(seriesId.toString());
		this.session = HibernateUtil.getSession();
		// Map<String, Object> map = new HashMap<String, Object>();
		CoreTeamsElement coreTeamsElement = new CoreTeamsElement(getAllCoreTeamDetails(seriesId));
		cache.update(coreTeamsElement.SEARCHED_CORE_TEAMS, coreTeamsElement);

		TeamSheduleElement sheduleElement = new TeamSheduleElement(getTeamSheduleDetails(seriesId));
		cache.update(sheduleElement.TEAM_SHEDULE, sheduleElement);

		List<UserTeamForm> userTeamForms = getAllUserTeamScores(seriesId);
		UserTeamsElement userTeamsElement = new UserTeamsElement(userTeamForms);
		cache.update(userTeamsElement.USER_TEAMS, userTeamsElement);

		CricRulesElement cricRulesElement = new CricRulesElement(getCricRules(seriesId));
		cache.update(cricRulesElement.CRIC_RULES, cricRulesElement);

		List<PlayerForm> bestPlayers = getSeriesBestPlayers(seriesId);
		BestPlayersElement bestPlayersElement = new BestPlayersElement(bestPlayers);
		cache.update(bestPlayersElement.BEST_PLAYERS, bestPlayersElement);

		FrontPageSeriesDataElement frontPageSeriesDataElement = new FrontPageSeriesDataElement(userTeamForms,
				bestPlayers, ManageTeamDBHelper.setLiveMatchDetails(seriesId), getSCTNews(seriesId));

		cache.update(frontPageSeriesDataElement.FP_SERIES_DATA, frontPageSeriesDataElement);
	}

	public void updateCommonCache(CricCache cache) {

		this.session = HibernateUtil.getSession();
		CountriesElement countriesElement = new CountriesElement(getCountryStaticData());
		cache.update(countriesElement.COUNTRIES, countriesElement);

		SeriesElement seriesElement = new SeriesElement(getSeriesStaticData(), getSeriesTypeStaticData());
		cache.update(seriesElement.SERIES, seriesElement);

		UserRolesElement userRolesElement = new UserRolesElement(getActiveUserRoleForms());
		cache.update(userRolesElement.USER_ROLES, userRolesElement);

		SunSignsElement sunSignsElement = new SunSignsElement(getSunSignStaticData());
		cache.update(sunSignsElement.SUNSIGNS, sunSignsElement);

	}

	public void updateSeriesNEWSCache() {
		Long seriesId = UserUtil.getSeries();
		CricCache cache = ActionXmlCacheManager.getInstance(seriesId.toString());
		this.session = HibernateUtil.getSession();

		List<UserTeamForm> userTeamForms = getAllUserTeamScores(seriesId);
		UserTeamsElement userTeamsElement = new UserTeamsElement(userTeamForms);
		cache.update(userTeamsElement.USER_TEAMS, userTeamsElement);

		List<PlayerForm> bestPlayers = getSeriesBestPlayers(seriesId);
		BestPlayersElement bestPlayersElement = new BestPlayersElement(bestPlayers);
		cache.update(bestPlayersElement.BEST_PLAYERS, bestPlayersElement);

		FrontPageSeriesDataElement frontPageSeriesDataElement = new FrontPageSeriesDataElement(userTeamForms,
				bestPlayers, ManageTeamDBHelper.setLiveMatchDetails(seriesId), getSCTNews(seriesId));

		cache.update(frontPageSeriesDataElement.FP_SERIES_DATA, frontPageSeriesDataElement);
	}

	public void loadExpenditureCacheDetails(ActionXmlCacheManager cache) {

		this.session = HibernateUtil.getSession();
		//ExpensiveElement expensiveElement = new ExpensiveElement(getItemTypeData(), getCashFlowData());
		//cache.put(expensiveElement.EXPENSIVE_DATA, expensiveElement);

	}

}
