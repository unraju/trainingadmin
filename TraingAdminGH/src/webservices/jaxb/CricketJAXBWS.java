package webservices.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import webservices.restful.cricket.BestPlayers;

import common.ehcache.elements.FrontPageSeriesDataElement;
import common.ehcache.exception.CacheElementNotFound;
import common.util.RetriveContextData;
import common.util.UserUtil;

import cricket.struts.actionforms.team.PlayerForm;

@WebService
public class CricketJAXBWS implements ICricketWS {

	public BestPlayers getBestPlayers(@WebParam(name = "seriesId") String seriesId) {

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

		// List<PlayerForm> bestBatsMan =
		// frontPageSeriesDataElement.getBestBatsmen();
		// List<PlayerForm> bestBowlers =
		// frontPageSeriesDataElement.getBestBowlers();
		List<PlayerForm> bestPlayers = frontPageSeriesDataElement.getSctBestPlayers();
		int i = 0;

		List<WSPlayerTO> playerTOs = new ArrayList<WSPlayerTO>();
		if (bestPlayers != null) {
			for (PlayerForm playerForm : bestPlayers) {
				i++;
				if (i > 5)
					break;

				playerTOs.add(playerForm.getWSPlayerTO());
			}
		} else {
			return errorResponseTO;
		}
		responseTO.setBestPlayers(playerTOs);
		return responseTO;// bestPlayers;
	}

	
	public String getHelloMsg(@WebParam(name = "name")String name) {
		return "Welcome to SCT "+name;
	}

}
