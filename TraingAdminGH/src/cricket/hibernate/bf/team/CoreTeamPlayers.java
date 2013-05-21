package cricket.hibernate.bf.team;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

import common.util.DateUtil;

import cricket.hibernate.bf.player.Player;
import cricket.struts.actionforms.team.PlayerForm;

public class CoreTeamPlayers implements Serializable {
	private Long id;

	private Date startDate;

	private Date endDate;

	private Long score;

	private boolean active;

	private Player player;

	private Long playerId;

	private CoreTeam coreTeam;

	private Long coreTeamId;

	private Long runs;

	private Long wickets;

	private Long catches;

	private boolean captain;

	private String centuries;

	private String halfCenturies;

	private Long balls;

	private Long matches;

	private Long fours;

	private Long sixers;

	public Long getBalls() {
		return balls;
	}

	public void setBalls(Long balls) {
		this.balls = balls;
	}

	public boolean isCaptain() {
		return captain;
	}

	public void setCaptain(boolean captain) {
		this.captain = captain;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
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

	public Long getCatches() {
		return catches;
	}

	public void setCatches(Long catches) {
		this.catches = catches;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public CoreTeam getCoreTeam() {
		return coreTeam;
	}

	public void setCoreTeam(CoreTeam coreTeam) {
		this.coreTeam = coreTeam;
	}

	public Long getCoreTeamId() {
		return coreTeamId;
	}

	public void setCoreTeamId(Long coreTeamId) {
		this.coreTeamId = coreTeamId;
	}

	public String getCenturies() {
		return centuries;
	}

	public void setCenturies(String centuries) {
		this.centuries = centuries;
	}

	public String getHalfCenturies() {
		return halfCenturies;
	}

	public void setHalfCenturies(String halfCenturies) {
		this.halfCenturies = halfCenturies;
	}

	public Long getMatches() {
		return matches;
	}

	public void setMatches(Long matches) {
		this.matches = matches;
	}

	public PlayerForm getActionForm() {
		PlayerForm form = new PlayerForm();
		if (player != null && id != null) {
			form.setId(id);
			// form.setPlayerId(player.getId());
			// Below attribute is to select the core team players only.
			form.setCoreTeamPlayerId(player.getId());
			form.setName(player.getName());
			form.setSkill(player.getSkill());
			form.setCountry(player.getCountryString());
			/*
			 * if (player.getRuns() != null)
			 * form.setRuns(player.getRuns().toString()); if
			 * (player.getWickets() != null)
			 * form.setWickets(player.getWickets().toString()); if
			 * (player.getCatches() != null)
			 * form.setCatches(player.getCatches().toString()); if
			 * (player.getMatches() != null)
			 * form.setMatches(player.getMatches().toString()); if
			 * (player.getCenturies() != null)
			 * form.setCenturies(player.getCenturies().toString()); if
			 * (player.getHalfCenturies() != null)
			 * form.setHalfCenturies(player.getHalfCenturies().toString());
			 */

			if (score != null) {
				form.setScore(score.toString());
			}
			if (runs != null) {
				form.setRuns(runs.toString());
				if (balls != null && balls.intValue() != 0) {
					DecimalFormat twoPlaces = new DecimalFormat("00.0");
					double sr = (runs.doubleValue() / balls.doubleValue()) * 100;
					form.setStrikeRate("" + twoPlaces.format(sr));
				}
			}
			if (balls != null && balls.intValue() != 0) {
				form.setBalls(balls.toString());
			}
			if (catches != null) {
				form.setCatches(catches.toString());
			}
			if (wickets != null) {
				form.setWickets(wickets.toString());
			}
			if (centuries != null) {
				form.setCenturies(centuries);
			}
			if (halfCenturies != null) {
				form.setHalfCenturies(halfCenturies);
			}
			if (matches != null) {
				form.setMatches(matches.toString());
			}
			if (fours != null) {
				form.setFours(fours.toString());
			}
			if (sixers != null) {
				form.setSixers(sixers.toString());
			}

		}
		form.setActive(active);
		if (active) {
			form.setActiveString("Yes");
		} else {
			form.setActiveString("No");
		}
		if (startDate != null) {
			form.setStartDate(DateUtil.getStringFromDate(this.startDate, "dd/MM/yyyy"));
		}
		if (endDate != null) {
			form.setStartDate(DateUtil.getStringFromDate(this.endDate, "dd/MM/yyyy"));
		}
		/*
		 * if (score != null) { form.setScore(score.toString()); } if (runs !=
		 * null) { form.setRuns(runs.toString()); } if (catches != null) {
		 * form.setCatches(catches.toString()); } if (wickets != null) {
		 * form.setWickets(wickets.toString()); }
		 */

		if (captain) {
			form.setCaptain(captain);
		}

		if (captain) {
			form.setCaptainString("Yes");
		} else {
			form.setCaptainString("No");
		}
		return form;
	}

	public CoreTeamPlayers clone() {
		CoreTeamPlayers teamPlayer = new CoreTeamPlayers();
		teamPlayer.setCaptain(captain);
		teamPlayer.setStartDate(startDate);
		teamPlayer.setPlayerId(playerId);
		teamPlayer.setCatches(catches);
		teamPlayer.setRuns(runs);
		teamPlayer.setWickets(wickets);
		teamPlayer.setScore(score);
		return teamPlayer;
	}

	public PlayerForm getCorePlayerActionForm() {
		PlayerForm form = new PlayerForm();
		if (player != null && id != null) {
			form.setId(player.getId());
			// form.setPlayerId(player.getId());
			// Below attribute is to select the core team players only.
			// form.setCoreTeamPlayerId(player.getId());
			form.setName(player.getName());
			form.setSkill(player.getSkill());
			form.setCountry(player.getCountry().getCountry());
			form.setCoreTeamName(coreTeam.getCode());
		}
		if (score != null) {
			form.setScore(score.toString());
		}
		if (runs != null) {
			form.setRuns(runs.toString());
			if (balls != null && balls.intValue() != 0) {
				DecimalFormat twoPlaces = new DecimalFormat("00.0");
				double sr = (runs.doubleValue() / balls.doubleValue()) * 100;
				form.setStrikeRate("" + twoPlaces.format(sr));
			}
		}
		if (catches != null) {
			form.setCatches(catches.toString());
		}
		if (balls != null && balls.intValue() != 0) {
			form.setBalls(balls.toString());
		}
		if (wickets != null) {
			form.setWickets(wickets.toString());
		}
		if (centuries != null) {
			form.setCenturies(centuries);
		}
		if (halfCenturies != null) {
			form.setHalfCenturies(halfCenturies);
		}
		if (matches != null) {
			form.setMatches(matches.toString());
		}
		if (fours != null) {
			form.setFours(fours.toString());
		}
		if (sixers != null) {
			form.setSixers(sixers.toString());
		}

		return form;
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

}
