package webservices.restful.cricket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import webservices.jaxb.WSPlayerTO;
import webservices.restful.JAXBMarshellingHelper;

import common.ehcache.elements.FrontPageSeriesDataElement;
import common.ehcache.exception.CacheElementNotFound;
import common.util.HibernateUtil;
import common.util.RetriveContextData;
import common.util.UserUtil;

import cricket.hibernate.bf.player.PlayerMatchScores;
import cricket.hibernate.bf.team.CoreTeamPlayers;
import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.helpers.common.GenerateMailHelper;

@Path("/cric/")
public class CricketRestfulWS {

	@GET
	@Path("getBestPlayers/{seriesId}/{playerType}")
	@Produces(MediaType.TEXT_XML)
	public String getBestPlayers_XML(@PathParam("seriesId") String seriesId, @PathParam("playerType") String playerType) {
		if (seriesId == null || seriesId.trim().equals("")) {
			seriesId = "1";
		}
		if (playerType == null || playerType.trim().equals("")) {
			playerType = "1";
		}
		BestPlayers responseTO = new BestPlayers();
		BestPlayers errorResponseTO = new BestPlayers("Invalid Input Data (1 - WC 2011, 2 - IPL 2011)");
		if (seriesId != null && !"".equals(seriesId.trim()) && (seriesId.equals("1") || seriesId.equals("2"))) {
			try {
				UserUtil.setSelectedSeries(Long.parseLong(seriesId));
			} catch (Exception e) {
				return errorResponseTO.toString();
			}
		} else {
			return errorResponseTO.toString();
		}

		responseTO = getSCTBestPlayers(seriesId, playerType);

		return JAXBMarshellingHelper.marshellObject(responseTO, BestPlayers.class);// bestPlayers;
	}

	private BestPlayers getSCTBestPlayers(String seriesId, String playerType) {

		BestPlayers responseTO = new BestPlayers();
		BestPlayers errorResponseTO = new BestPlayers("Invalid Input Data (1 - WC 2011, 2 - IPL 2011)");
		if (seriesId != null && !"".equals(seriesId.trim()) && (seriesId.equals("1") || seriesId.equals("2"))) {
			try {
				UserUtil.setSelectedSeries(Long.parseLong(seriesId));
			} catch (Exception e) {
				return errorResponseTO;
			}
		} else {
			return errorResponseTO;
		}
		RetriveContextData contextData = new RetriveContextData();
		FrontPageSeriesDataElement frontPageSeriesDataElement = new FrontPageSeriesDataElement();
		try {
			frontPageSeriesDataElement = contextData.getFronPageSeriesData();

		} catch (CacheElementNotFound e) {
			e.printStackTrace();
			return new BestPlayers("System failed to process the request");
		}

		List<PlayerForm> players = new ArrayList<PlayerForm>();
		if (playerType.equals("1")) {
			players = frontPageSeriesDataElement.getSctBestPlayers();
		} else if (playerType.equals("2")) {
			players = frontPageSeriesDataElement.getBestBatsmen();
		} else if (playerType.equals("3")) {
			players = frontPageSeriesDataElement.getBestBowlers();
		}

		int i = 0;

		List<WSPlayerTO> playerTOs = new ArrayList<WSPlayerTO>();
		if (players != null) {
			for (PlayerForm playerForm : players) {
				i++;
				if (i > 13)
					break;

				playerTOs.add(playerForm.getWSPlayerTO());
			}
		} else {
			return errorResponseTO;
		}
		responseTO.setBestPlayers(playerTOs);
		return responseTO;// bestPlayers;

	}

	@GET
	@Path("getPlayerSeriesScores/{seriesId}/{playerName}")
	@Produces(MediaType.TEXT_XML)
	public String getPlayerSeriesScores(@PathParam("seriesId") String seriesId, @PathParam("playerName") String playerName) {
		Long seriesIdL = 1L; 
		if (seriesId != null || !seriesId.trim().equals("")) {
			seriesIdL = Long.parseLong(seriesId);
		}
		
		Session session = HibernateUtil.getSession();
		Criteria criteria =session.createCriteria(CoreTeamPlayers.class);
		criteria.createCriteria("coreTeam").createCriteria("series").add(Restrictions.eq("id", seriesIdL));
		criteria.createCriteria("player").add(Restrictions.like("name", playerName, MatchMode.ANYWHERE));
		
		Collection<CoreTeamPlayers> players = 	criteria.list();
		PlayerSeriesScores playerSeriesScores = new PlayerSeriesScores();
		
		if(players.size() > 1)
		{
			for(CoreTeamPlayers teamPlayers:players)
			{
				playerSeriesScores.getPlayerNames().add(teamPlayers.getPlayer().getName());
				playerSeriesScores.setNameMatched(false);
			}
		} else if(players.size() == 1)
		{
			playerSeriesScores.setNameMatched(true);
			CoreTeamPlayers coreTeamPlayers =  null;
			for(CoreTeamPlayers teamPlayer:players)
			{
				coreTeamPlayers = teamPlayer;
				playerSeriesScores.setCountryShortName(coreTeamPlayers.getCoreTeam().getName());
				playerSeriesScores.setName(coreTeamPlayers.getPlayer().getName());
				break;
			}
			
			playerSeriesScores =findPlayerSeriesScore(seriesIdL,coreTeamPlayers.getPlayerId(),playerSeriesScores);
		} 
	
		return JAXBMarshellingHelper.marshellObject(playerSeriesScores, PlayerSeriesScores.class);// bestPlayers;
	}

	public PlayerSeriesScores findPlayerSeriesScore(Long seriesId, Long playerId, PlayerSeriesScores playerSeriesScores) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(PlayerMatchScores.class);
		criteria.add(Restrictions.eq("seriesId", seriesId));
		criteria.add(Restrictions.eq("playerId", playerId));
		List<PlayerMatchScores> list = criteria.list();
		if (list != null && list.size() > 0) {
			for (PlayerMatchScores playerMatchScores : list) {
				playerSeriesScores.getPlayerScores().add(playerMatchScores.getPlayerScore4WS(playerSeriesScores.getCountryShortName()));
			}
		}
		return playerSeriesScores;
	}
	
	@GET
	@Path("sendMail")
	public void sendMail() {
		
		new GenerateMailHelper().sendSeriesLaunchMail();
	}
}
