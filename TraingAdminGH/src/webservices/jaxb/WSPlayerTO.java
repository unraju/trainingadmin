package webservices.jaxb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BestPlayer")
public class WSPlayerTO implements Serializable {

	private String id;

	private String name;

	private String country;

	private String countryShortName;

	private String skill;

	private String score = "";

	private String captainString;

	private String runs = "";

	private String balls = "";

	private String wickets = "";

	private String catches = "";

	private String coreTeamName = "";

	private String fours = "";

	private String sixers = "";

	private String strikeRate = "";
	
	private String match = "";
	
	private String date = "";

	public WSPlayerTO() {
		super();
	}

	@XmlAttribute
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
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@XmlElement
	public String getCountryShortName() {
		return countryShortName;
	}

	public void setCountryShortName(String countryShortName) {
		this.countryShortName = countryShortName;
	}

	@XmlElement
	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	@XmlElement
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@XmlElement
	public String getCaptainString() {
		return captainString;
	}

	public void setCaptainString(String captainString) {
		this.captainString = captainString;
	}

	@XmlElement
	public String getRuns() {
		return runs;
	}

	public void setRuns(String runs) {
		this.runs = runs;
	}

	@XmlElement
	public String getBalls() {
		return balls;
	}

	public void setBalls(String balls) {
		this.balls = balls;
	}

	@XmlElement
	public String getWickets() {
		return wickets;
	}

	public void setWickets(String wickets) {
		this.wickets = wickets;
	}

	@XmlElement
	public String getCatches() {
		return catches;
	}

	public void setCatches(String catches) {
		this.catches = catches;
	}

	@XmlElement
	public String getCoreTeamName() {
		return coreTeamName;
	}

	public void setCoreTeamName(String coreTeamName) {
		this.coreTeamName = coreTeamName;
	}
	
	@XmlElement
	public String getFours() {
		return fours;
	}

	public void setFours(String fours) {
		this.fours = fours;
	}
	
	@XmlElement
	public String getSixers() {
		return sixers;
	}

	public void setSixers(String sixers) {
		this.sixers = sixers;
	}

	@XmlElement
	public String getStrikeRate() {
		return strikeRate;
	}

	public void setStrikeRate(String strikeRate) {
		this.strikeRate = strikeRate;
	}
	
	
	@XmlElement
	public String getMatch() {
		return match;
	}

	public void setMatch(String match) {
		this.match = match;
	}
	@XmlElement
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "WSPlayerTO [id=" + id + ", name=" + name + ", country=" + country + ", countryShortName=" + countryShortName
				+ ", skill=" + skill + ", score=" + score + ", captainString=" + captainString + ", runs=" + runs
				+ ", balls=" + balls + ", wickets=" + wickets + ", catches=" + catches + ", coreTeamName=" + coreTeamName
				+ "]";
	}

}
