package cricket.struts.helpers.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.util.HibernateUtil;

import cricket.hibernate.bf.common.CountryHome;
import cricket.hibernate.bf.player.Player;
import cricket.hibernate.bf.player.PlayerScores;
import cricket.struts.actionforms.player.PlayerScoresForm;
import cricket.struts.actionforms.team.PlayerForm;

public class ManagePlayerDBHelper {

	public Collection<PlayerForm> searchPlayer(PlayerForm form) {
		String name = form.getName();
		String skill = form.getSkill();
		String countryId = form.getCountry();
		boolean active = form.isActive();
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Player.class);

		if (name != null && !name.trim().equals("")) {
			criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		}
		if (skill != null && !skill.trim().equals("") && !skill.equalsIgnoreCase("All")) {
			criteria.add(Restrictions.eq("skill", skill));
		}
		if (active) {
			// criteria.add(Restrictions.eq("active", active));
		} else {
			// criteria.add(Restrictions.eq("active", active));
		}
		if (countryId != null && !countryId.trim().equals("") && !countryId.equalsIgnoreCase("ALL")) {
			Criteria criteria2 = criteria.createCriteria("country");
			criteria2.add(Restrictions.like("id", Long.parseLong(countryId)));
		}

		Collection<Player> list = criteria.list();
		List<PlayerForm> playerForms = new ArrayList<PlayerForm>();
		for (Player player : list) {
			playerForms.add(player.getActionForm());
		}
		return playerForms;
	}

	public Player findPlayerById(Long id) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Player.class);
		criteria.add(Restrictions.eq("id", id));
		List<Player> list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public Player updatePlayer(PlayerForm playerForm) throws Exception {
		Session session = HibernateUtil.getSession();
		Player player = null;
		if (playerForm != null && playerForm.getId().intValue() != 0) {
			player = findPlayerById(playerForm.getId());
		} else {
			player = new Player();
		}

		player.setName(playerForm.getName());
		player.setCountry(CountryHome.findCountryById(Long.parseLong(playerForm.getCountry())));
		player.setSkill(playerForm.getSkill());
		player.setName(playerForm.getName());
		player.setActive(playerForm.isActive());
		if (playerForm.getRuns() != null && !playerForm.getRuns().trim().equalsIgnoreCase("")) {
			player.setRuns(Long.parseLong(playerForm.getRuns()));
		}
		if (playerForm.getRuns() != null && !playerForm.getRuns().trim().equalsIgnoreCase("")) {
			player.setRuns(Long.parseLong(playerForm.getRuns()));
		}
		if (playerForm.getMatches() != null && !playerForm.getMatches().trim().equalsIgnoreCase("")) {
			player.setMatches(Long.parseLong(playerForm.getMatches()));
		}
		if (playerForm.getWickets() != null && !playerForm.getWickets().trim().equalsIgnoreCase("")) {
			player.setWickets(Long.parseLong(playerForm.getWickets()));
		}
		if (playerForm.getCenturies() != null && !playerForm.getCenturies().trim().equalsIgnoreCase("")) {
			player.setCenturies(Long.parseLong(playerForm.getCenturies()));
		}
		if (playerForm.getHalfCenturies() != null && !playerForm.getHalfCenturies().trim().equalsIgnoreCase("")) {
			player.setHalfCenturies(Long.parseLong(playerForm.getHalfCenturies()));
		}
		// player.setCoreTeam(coreTeam);
		session.save(player);

		updatePlayerScoreDetails(playerForm, player);

		return player;
	}

	private void updatePlayerScoreDetails(PlayerForm playerForm, Player player) {
		Session session = HibernateUtil.getSession();
		for (PlayerScoresForm playerScoresForm : playerForm.getPlayerScores()) {
			PlayerScores playerScores = null;
			if (playerScoresForm.getId() != null && playerScoresForm.getId().intValue() != 0) {
				playerScores = findPlayerScoreById(playerScoresForm.getId());
			} else {
				playerScores = new PlayerScores();
			}
			if (playerScoresForm.getMatches() != null && !playerScoresForm.getMatches().trim().equals("")) {
				playerScores.setMatches(Long.parseLong(playerScoresForm.getMatches()));
			}
			if (playerScoresForm.getRuns() != null && !playerScoresForm.getRuns().trim().equals("")) {
				playerScores.setRuns(Long.parseLong(playerScoresForm.getRuns()));
			}
			if (playerScoresForm.getWickets() != null && !playerScoresForm.getWickets().trim().equals("")) {
				playerScores.setWickets(Long.parseLong(playerScoresForm.getWickets()));
			}
			if (playerScoresForm.getCenturies() != null && !playerScoresForm.getCenturies().trim().equals("")) {
				playerScores.setCenturies(Long.parseLong(playerScoresForm.getCenturies()));
			}
			if (playerScoresForm.getHalfCenturies() != null && !playerScoresForm.getHalfCenturies().trim().equals("")) {
				playerScores.setHalfCenturies(Long.parseLong(playerScoresForm.getHalfCenturies()));
			}
			playerScores.setSeriesTypeId(playerScoresForm.getSeriesTypeId());
			playerScores.setPlayer(player);
			session.save(playerScores);
		}
	}

	private PlayerScores findPlayerScoreById(Long id) {

		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(PlayerScores.class);
		criteria.add(Restrictions.eq("id", id));
		Collection<PlayerScores> list = criteria.list();
		if (list != null && list.size() > 0) {
			for (PlayerScores playerScores : list) {
				return playerScores;
			}
		}
		return null;
	}

	public static List<PlayerForm> getAllPlayers() {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Player.class);
		// criteria.add(Restrictions.eq("active", true));
		Collection<Player> list = criteria.list();
		List<PlayerForm> playerForms = new ArrayList<PlayerForm>();
		for (Player player : list) {
			playerForms.add(player.getActionForm());
		}
		return playerForms;
	}

	public static Object searchPlayerByCountry(String countryId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Player.class);
		criteria.add(Restrictions.eq("active", true));
		Criteria countryCriteria = criteria.createCriteria("country");
		countryCriteria.addOrder(Order.asc("id"));
		Collection<Player> list = criteria.list();
		List<PlayerForm> playerForms = new ArrayList<PlayerForm>();
		for (Player player : list) {
			playerForms.add(player.getActionForm());
		}
		return playerForms;
	}

}
