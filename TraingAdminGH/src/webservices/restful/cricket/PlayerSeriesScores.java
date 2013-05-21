package webservices.restful.cricket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import webservices.jaxb.WSPlayerTO;
@XmlRootElement
public class PlayerSeriesScores implements Serializable {

	private String id;

	private String name;

	private String countryShortName;

	private List<WSPlayerTO> playerScores =  new ArrayList<WSPlayerTO>();

	private boolean nameMatched;

	private List<String> playerNames = new ArrayList<String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	 @XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 @XmlElement
	public String getCountryShortName() {
		return countryShortName;
	}

	public void setCountryShortName(String countryShortName) {
		this.countryShortName = countryShortName;
	}
	 @XmlElement
	public boolean isNameMatched() {
		return nameMatched;
	}

	public void setNameMatched(boolean nameMatched) {
		this.nameMatched = nameMatched;
	}
	 @XmlElement
	public List<String> getPlayerNames() {
		return playerNames;
	}

	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
	}
	 @XmlElement
	public List<WSPlayerTO> getPlayerScores() {
		return playerScores;
	}

	public void setPlayerScores(List<WSPlayerTO> playerScores) {
		this.playerScores = playerScores;
	}

	

}
