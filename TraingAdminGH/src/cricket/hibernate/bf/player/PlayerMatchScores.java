package cricket.hibernate.bf.player;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import webservices.jaxb.WSPlayerTO;

import common.util.Constants;
import common.util.DateUtil;

import cricket.hibernate.bf.team.TeamShedule;
import cricket.struts.actionforms.team.PlayerMatchScoresForm;

public class PlayerMatchScores implements Serializable {

	private Long id;

	private Date date;

	private Player player;

	private String playerName;

	private TeamShedule match;

	private Long order;

	private Long playerId;

	private Long matchId;

	private Long runs;

	private Long balls;

	private Long wickets;

	private Long catches;

	private Long score;

	private Long seriesId;

	private Long fours;

	private Long sixers;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Long getBalls() {
		return balls;
	}

	public void setBalls(Long balls) {
		this.balls = balls;
	}

	public Long getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Long seriesId) {
		this.seriesId = seriesId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		if (player != null)
			this.playerName = player.getName();
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Long getRuns() {
		return runs;
	}

	public void setRuns(Long runs) {
		this.runs = runs;
	}

	public Long getWickets() {
		return wickets;
	}

	public void setWickets(Long wickets) {
		this.wickets = wickets;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public Long getCatches() {
		return catches;
	}

	public void setCatches(Long catches) {
		this.catches = catches;
	}

	public TeamShedule getMatch() {
		return match;
	}

	public void setMatch(TeamShedule match) {
		this.match = match;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public PlayerMatchScoresForm getActionForm() {
		PlayerMatchScoresForm form = new PlayerMatchScoresForm();
		form.setId(id);
		if (match != null) {
			form.setMatchName(match.getMatchName());
			if (match.getSeries() != null)
				form.setSeriesName(match.getSeries().getName());
		}
		form.setMatchId(matchId);
		form.setPlayerId(playerId);
		if (player != null) {
			form.setPlayerName(player.getName());
		} else {
			form.setPlayerName(playerName);
		}
		if (runs != null)
			form.setRuns(runs.toString());

		if (balls != null)
			form.setBalls(balls.toString());

		if (catches != null)
			form.setCatches(catches.toString());

		if (wickets != null)
			form.setWickets(wickets.toString());

		if (score != null)
			form.setScore(score.toString());

		if (fours != null) {
			form.setFours(fours.toString());
		}
		if (sixers != null) {
			form.setSixers(sixers.toString());
		}
		if (order != null) {
			form.setOrder(order.toString());
		}
		form.setSeriesId(seriesId);
		form.setDate(date);
		if (runs != null && runs.intValue() != 0 && balls != null && balls.intValue() != 0) {
			DecimalFormat twoPlaces = new DecimalFormat("00.0");
			double sr = (runs.doubleValue() / balls.doubleValue()) * 100;
			form.setStrikRate("" + twoPlaces.format(sr));
		}
		if (match != null) {
			form.setMatchName(match.getFirstTeamName() + " Vs " + match.getSecondTeamName());
			form.setDateString(DateUtil.getStringFromDate(match.getDate(), Constants.DATE_TEME_AM__PM_SHORT));
		}
		return form;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Long getFours() {
		return fours;
	}

	public void setFours(Long fours) {
		this.fours = fours;
	}

	public Long getSixers() {
		return sixers;
	}

	public void setSixers(Long sixers) {
		this.sixers = sixers;
	}

	public Long generateScore() {
		Long score = 0L;
		if (runs != null) {
			score = score + runs * 1;
			if (runs.intValue() >= 100) {
				score = score + 10;
			} else if (runs.intValue() >= 50) {
				score = score + 5;
			}
		}
		if (catches != null) {
			score = score + catches * 10;
		}
		if (wickets != null) {
			score = score + wickets * 25;
		}
		if (fours != null) {
			score = score + fours * 1;
		}
		if (sixers != null) {
			score = score + sixers * 2;
		}
		return score;
	}

	// this is used to in WS
	public WSPlayerTO getPlayerScore4WS(String country) {
		WSPlayerTO form = new WSPlayerTO();

		if (player != null) {
			form.setName(player.getName());
		} else {
			form.setName(playerName);
		}
		if (runs != null)
			form.setRuns(runs.toString());

		if (balls != null)
			form.setBalls(balls.toString());

		if (catches != null)
			form.setCatches(catches.toString());

		if (wickets != null)
			form.setWickets(wickets.toString());

		if (score != null)
			form.setScore(score.toString());

		if (fours != null) {
			form.setFours(fours.toString());
		}
		if (sixers != null) {
			form.setSixers(sixers.toString());
		}
		// form.setDate(date);
		if (runs != null && runs.intValue() != 0 && balls != null && balls.intValue() != 0) {
			DecimalFormat twoPlaces = new DecimalFormat("00.0");
			double sr = (runs.doubleValue() / balls.doubleValue()) * 100;
			form.setStrikeRate("" + twoPlaces.format(sr));
		}
		if (match != null) {
			
			if (match.getFirstTeam().getCountry() != null && !match.getFirstTeamName().matches(country)) {
				form.setMatch(match.getFirstTeam().getCode());
			} else {
				form.setMatch(match.getSecondTeam().getCode());
			}

			form.setDate(DateUtil.getStringFromDate(match.getDate(), Constants.DATE_TEME_AM__PM_SHORT));
		}
		return form;
	}
}
