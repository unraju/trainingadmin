package common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import common.ehcache.ICache;
import common.ehcache.elements.BestPlayersElement;
import common.ehcache.elements.CoreTeamsElement;
import common.ehcache.elements.CountriesElement;
import common.ehcache.elements.CricRulesElement;
import common.ehcache.elements.ExpensiveElement;
import common.ehcache.elements.FrontPageSeriesDataElement;
import common.ehcache.elements.LiveMatchScheduleElement;
import common.ehcache.elements.SctNewsElement;
import common.ehcache.elements.SeriesElement;
import common.ehcache.elements.SunSignsElement;
import common.ehcache.elements.TeamSheduleElement;
import common.ehcache.elements.UserRolesElement;
import common.ehcache.elements.UserTeamsElement;
import common.ehcache.exception.CacheElementNotFound;
import common.ehcache.xmlcachemanager.ActionXmlCacheManager;
import common.struts.actionforms.user.UserRoleForm;

import cricket.hibernate.bf.team.CoreTeamPlayerRunComparator;
import cricket.hibernate.bf.team.CoreTeamPlayerScoreComparator;
import cricket.hibernate.bf.team.CoreTeamPlayerWicketsComparator;
import cricket.struts.actionforms.common.CountryForm;
import cricket.struts.actionforms.common.CricRulesForm;
import cricket.struts.actionforms.common.SCTNewsForm;
import cricket.struts.actionforms.common.SeriesForm;
import cricket.struts.actionforms.common.SeriesTypeForm;
import cricket.struts.actionforms.common.SunSignForm;
import cricket.struts.actionforms.team.CoreTeamForm;
import cricket.struts.actionforms.team.CurrentMatchSheduleForm;
import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.actionforms.team.TeamSheduleForm;
import cricket.struts.actionforms.team.UserTeamForm;
import expenditure.actionform.CashFlowTypeTO;
import expenditure.actionform.ItemTypeTO;

public class RetriveContextData {
	protected ICache cache = null;

	private boolean isCache = true;

	public List<CoreTeamForm> getCoreTeamDetails(ServletContext ctx) throws CacheElementNotFound {
		if (isCache) {
			cache = ActionXmlCacheManager.getInstance(UserUtil.getSeries().toString());
			CoreTeamsElement coreTeamsElement = (CoreTeamsElement) cache.get(CoreTeamsElement.SEARCHED_CORE_TEAMS);
			return coreTeamsElement.getCoreteamForms();
		} else {
			Map<Long, Map<String, ?>> seriesDataMap = (HashMap<Long, Map<String, ?>>) ctx
					.getAttribute(Constants.SERIES_DETAILS);
			Map<String, Object> map = (HashMap<String, Object>) ctx.getAttribute(UserUtil.getSeries().toString());
			return (List<CoreTeamForm>) map.get(Constants.SEARCHED_CORE_TEAMS);
		}

	}

	public List<TeamSheduleForm> getTeamShedule(ServletContext ctx) throws CacheElementNotFound {
		if (isCache) {
			cache = ActionXmlCacheManager.getInstance(UserUtil.getSeries().toString());
			TeamSheduleElement teamSheduleElement = (TeamSheduleElement) cache.get(TeamSheduleElement.TEAM_SHEDULE);
			return teamSheduleElement.getSheduleForms();
		} else {
			Map<String, Object> map = (HashMap<String, Object>) ctx.getAttribute(UserUtil.getSeries().toString());
			return (List<TeamSheduleForm>) map.get(Constants.TEAM_SHEDULE);
		}
	}

	public List<UserTeamForm> getUserTeamScores(ServletContext ctx) throws CacheElementNotFound {
		if (isCache) {
			cache = ActionXmlCacheManager.getInstance(UserUtil.getSeries().toString());
			UserTeamsElement userTeamsElement = (UserTeamsElement) cache.get(UserTeamsElement.USER_TEAMS);
			return userTeamsElement.getUserTeamForms();
		} else {
			Map<String, Object> map = (HashMap<String, Object>) ctx.getAttribute(UserUtil.getSeries().toString());
			return (List<UserTeamForm>) map.get(Constants.USER_TEAMS_SCORES);
		}
	}

	public List<CricRulesForm> getSeriesCricRuless(ServletContext ctx) throws CacheElementNotFound {
		if (isCache) {
			cache = ActionXmlCacheManager.getInstance(UserUtil.getSeries().toString());
			CricRulesElement cricRulesElement = (CricRulesElement) cache.get(CricRulesElement.CRIC_RULES);
			return cricRulesElement.getCricRulesForms();
		} else {
			Map<String, Object> map = (HashMap<String, Object>) ctx.getAttribute(UserUtil.getSeries().toString());
			return (List<CricRulesForm>) map.get(Constants.CRIC_RULES);
		}
	}

	public List<PlayerForm> getSeriesBestPlayers(ServletContext ctx) throws CacheElementNotFound {
		if (isCache) {
			cache = ActionXmlCacheManager.getInstance(UserUtil.getSeries().toString());
			BestPlayersElement bestPlayersElement = (BestPlayersElement) cache.get(BestPlayersElement.BEST_PLAYERS);
			return bestPlayersElement.getPlayers();
		} else {
			Map<String, Object> map = (HashMap<String, Object>) ctx.getAttribute(UserUtil.getSeries().toString());
			return (List<PlayerForm>) map.get(Constants.SERIES_BEST_PLAYERS);
		}
	}

	public List<SCTNewsForm> getSCTNews(ServletContext ctx) throws CacheElementNotFound {
		if (isCache) {
			cache = ActionXmlCacheManager.getInstance(UserUtil.getSeries().toString());
			SctNewsElement newsElement = (SctNewsElement) cache.get(SctNewsElement.SCT_NEWS);
			return newsElement.getSctNewsForms();
		} else {
			Map<String, Object> map = (HashMap<String, Object>) ctx.getAttribute(UserUtil.getSeries().toString());
			return (List<SCTNewsForm>) map.get(Constants.SCT_NEWS);
		}
	}

	public List<CurrentMatchSheduleForm> getLiveMatchDetails(ServletContext ctx) throws CacheElementNotFound {
		if (isCache) {
			cache = ActionXmlCacheManager.getInstance(UserUtil.getSeries().toString());
			LiveMatchScheduleElement liveMatchScheduleElement = (LiveMatchScheduleElement) cache
					.get(LiveMatchScheduleElement.LIVE_MATCH_SCHEDULE);
			return liveMatchScheduleElement.getCurrentMatchSheduleForms();
		} else {
			Map<String, Object> map = (HashMap<String, Object>) ctx.getAttribute(UserUtil.getSeries().toString());
			return (List<CurrentMatchSheduleForm>) map.get(Constants.LIVE_MATCH_SHEDULES);
		}
	}

	public List<UserRoleForm> getDefaultUserRoles(ServletContext ctx) throws CacheElementNotFound, ServletException {
		List<UserRoleForm> userRoles = getUserRolesData(ctx);// (List<UserRoleForm>)
		// ctx.getAttribute(Constants.USER_ROLES);
		List<UserRoleForm> userRoleForms = new ArrayList<UserRoleForm>();
		for (UserRoleForm roleForm : userRoles) {
			if (roleForm.isDefaultRole()) {
				userRoleForms.add(roleForm);
			}
		}
		return userRoleForms;
	}

	public List<UserRoleForm> getUserNotAssociatedRoles(ServletContext ctx) throws CacheElementNotFound,
			ServletException {
		List<UserRoleForm> userRoles = getUserRolesData(ctx);
		List<UserRoleForm> userRoleForms = new ArrayList<UserRoleForm>();
		for (UserRoleForm roleForm : userRoles) {
			if (!roleForm.isUserAssociated()) {
				userRoleForms.add(roleForm);
			}
		}
		return userRoleForms;
	}

	public SeriesForm getCurrentSeriesForm() throws CacheElementNotFound {
		List<SeriesForm> seriesForms = getSeriesData();// (List<SeriesForm>)
		// ctx.getAttribute(Constants.SERIESES);
		// List<UserRoleForm> userRoleForms = new ArrayList<UserRoleForm>();
		for (SeriesForm seriesForm : seriesForms) {
			if (seriesForm.getId().intValue() == UserUtil.getSeries().intValue()) {
				return seriesForm;
			}
		}
		return null;
	}

	public SeriesForm getSeriesFormById(int seriesId) throws CacheElementNotFound {
		List<SeriesForm> seriesForms = getSeriesData();// (List<SeriesForm>)
		// ctx.getAttribute(Constants.SERIESES);
		// List<UserRoleForm> userRoleForms = new ArrayList<UserRoleForm>();
		for (SeriesForm seriesForm : seriesForms) {
			if (seriesForm.getId().intValue() == seriesId) {
				return seriesForm;
			}
		}
		return null;
	}

	public List<PlayerForm> getSeriesBestBatsman(ServletContext ctx) throws CacheElementNotFound {
		List<PlayerForm> temp = getSeriesBestPlayers(ctx);
		List<PlayerForm> bestBatsmen = new ArrayList<PlayerForm>();
		if (temp != null && temp.size() > 0) {
			for (PlayerForm playerForm : temp) {
				if (playerForm != null
						&& playerForm.getSkill() != null
						&& (playerForm.getRuns() != null && !playerForm.getRuns().trim().equals("") && Integer
								.parseInt(playerForm.getRuns()) > 25)) {
					bestBatsmen.add(playerForm);
				}

			}
		}
		Collections.sort(bestBatsmen, new CoreTeamPlayerRunComparator());
		return bestBatsmen;
	}

	public List<PlayerForm> getSeriesBestBowlers(ServletContext ctx) throws CacheElementNotFound {
		List<PlayerForm> temp = getSeriesBestPlayers(ctx);
		List<PlayerForm> bestBowlers = new ArrayList<PlayerForm>();
		if (temp != null && temp.size() > 0) {
			for (PlayerForm playerForm : temp) {

				if (playerForm.getWickets() != null && !playerForm.getWickets().trim().equals("")
						&& Integer.parseInt(playerForm.getWickets()) > 1) {
					bestBowlers.add(playerForm);
				}
			}
		}

		Collections.sort(bestBowlers, new CoreTeamPlayerWicketsComparator());

		return bestBowlers;
	}

	public List<PlayerForm> getSeriesSCTBestPlayer(ServletContext ctx) throws CacheElementNotFound {
		List<PlayerForm> temp = getSeriesBestPlayers(ctx);
		List<PlayerForm> bestPlayers = new ArrayList<PlayerForm>();
		if (temp != null && temp.size() > 0) {
			for (PlayerForm playerForm : temp) {
				bestPlayers.add(playerForm);
			}
		}
		Collections.sort(bestPlayers, new CoreTeamPlayerScoreComparator());
		return bestPlayers;
	}

	public FrontPageSeriesDataElement getFronPageSeriesData() throws CacheElementNotFound {
		cache = ActionXmlCacheManager.getInstance(UserUtil.getSeries().toString());
		return (FrontPageSeriesDataElement) cache.get(FrontPageSeriesDataElement.FP_SERIES_DATA);
	}

	public List<UserRoleForm> getUserRolesData(ServletContext context) throws CacheElementNotFound, ServletException {
		List<UserRoleForm> userRoleForms = null;
		cache = ActionXmlCacheManager.getInstance(Constants.COMMON_CACHE);
		UserRolesElement userRolesElement = (UserRolesElement) cache.get(UserRolesElement.USER_ROLES);
		/*
		 * if (userRolesElement == null || userRolesElement.getUserRoleForms()
		 * == null) { //new LoadStaticDataHelper().loadstaticData(context);
		 * cache = ActionXmlCacheManager.getInstance(Constants.COMMON_CACHE);
		 * userRolesElement = (UserRolesElement)
		 * cache.get(UserRolesElement.USER_ROLES); }
		 */
		if (userRolesElement != null) {
			userRoleForms = userRolesElement.getUserRoleForms();
		}
		/*
		 * // If roles are null update the cache if(userRoleForms == null) { new
		 * LoadStaticDataHelper().updateCommonCache(); userRolesElement =
		 * (UserRolesElement) cache.get(UserRolesElement.USER_ROLES); if
		 * (userRolesElement != null) { userRoleForms =
		 * userRolesElement.getUserRoleForms(); } }
		 */
		return userRoleForms;
	}

	public List<SeriesForm> getSeriesData() throws CacheElementNotFound {
		cache = ActionXmlCacheManager.getInstance(Constants.COMMON_CACHE);
		SeriesElement seriesElement = (SeriesElement) cache.get(SeriesElement.SERIES);
		return seriesElement.getSeriesForms();
	}

	public List<SeriesTypeForm> getSeriesTypeData() throws CacheElementNotFound {
		cache = ActionXmlCacheManager.getInstance(Constants.COMMON_CACHE);
		SeriesElement seriesElement = (SeriesElement) cache.get(SeriesElement.SERIES);
		return seriesElement.getSeriesTypeForms();
	}

	public List<CountryForm> getCountriesData() throws CacheElementNotFound {
		cache = ActionXmlCacheManager.getInstance(Constants.COMMON_CACHE);
		CountriesElement countriesElement = (CountriesElement) cache.get(CountriesElement.COUNTRIES);
		return countriesElement.getCountryForms();
	}

	public List<SunSignForm> getSunSignData() throws CacheElementNotFound {
		cache = ActionXmlCacheManager.getInstance(Constants.COMMON_CACHE);
		SunSignsElement sunSignsElement = (SunSignsElement) cache.get(SunSignsElement.SUNSIGNS);
		return sunSignsElement.getSunSignForms();
	}

	public List<ItemTypeTO> getItemTypes() throws CacheElementNotFound {
		cache = ActionXmlCacheManager.getInstance(Constants.EXPENDITURE_CACHE);
		ExpensiveElement expensiveElement = (ExpensiveElement) cache.get(ExpensiveElement.EXPENSIVE_DATA);
		return expensiveElement.getItemTypes();
	}

	public List<CashFlowTypeTO> getCashFlowTypes() throws CacheElementNotFound {
		cache = ActionXmlCacheManager.getInstance(Constants.EXPENDITURE_CACHE);
		ExpensiveElement expensiveElement = (ExpensiveElement) cache.get(ExpensiveElement.EXPENSIVE_DATA);
		return expensiveElement.getCashFlowTypes();
	}
}
