package common.ehcache.elements;

import java.io.Serializable;
import java.util.List;

import cricket.struts.actionforms.team.PlayerForm;

public class BestPlayersElement implements Serializable
{
  public static final String BEST_PLAYERS = "BEST_PLAYERS";

  public List<PlayerForm> players;

  public List<PlayerForm> getPlayers()
  {
  return players;
  }

  public BestPlayersElement(List<PlayerForm> playerForms)
  {
  super();
  this.players = playerForms;
  }

}
