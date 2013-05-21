package common.ehcache.elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cricket.hibernate.bf.team.CoreTeamPlayerRunComparator;
import cricket.hibernate.bf.team.CoreTeamPlayerScoreComparator;
import cricket.hibernate.bf.team.CoreTeamPlayerWicketsComparator;
import cricket.struts.actionforms.common.SCTNewsForm;
import cricket.struts.actionforms.team.CurrentMatchSheduleForm;
import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.actionforms.team.UserTeamForm;

public class FrontPageSeriesDataElement implements Serializable
{
  public static final String FP_SERIES_DATA = "FP_SERIES_DATA";

  public List<UserTeamForm> userTeamForms;

  public List<PlayerForm> sctBestPlayers;

  public List<PlayerForm> bestBatsmen;

  public List<PlayerForm> bestBowlers;

  public List<CurrentMatchSheduleForm> currentMatchSheduleForms;

  public List<SCTNewsForm> sctNewsForms;

  int size = 0;

  public FrontPageSeriesDataElement()
  {
  super();
  }

  public FrontPageSeriesDataElement(List<UserTeamForm> userTeamForms, List<PlayerForm> seriesBestPlayers, List<CurrentMatchSheduleForm> liveMatchDetails, List<SCTNewsForm> sctNews)
  {
  size = 8;
  this.currentMatchSheduleForms = liveMatchDetails;
  this.sctNewsForms = sctNews;
  setSeriesBestBatsman(seriesBestPlayers);
  setSeriesBestBowlers(seriesBestPlayers);
  setSeriesSCTBestPlayer(seriesBestPlayers);
  setTopUserTeams(userTeamForms);
  }

  public List<UserTeamForm> getUserTeamForms()
  {
  return userTeamForms;
  }

  public List<PlayerForm> getSctBestPlayers()
  {
  return sctBestPlayers;
  }

  public List<PlayerForm> getBestBatsmen()
  {
  return bestBatsmen;
  }

  public List<CurrentMatchSheduleForm> getCurrentMatchSheduleForms()
  {
  return currentMatchSheduleForms;
  }

  public List<SCTNewsForm> getSctNewsForms()
  {
  return sctNewsForms;
  }

  public List<PlayerForm> getBestBowlers()
  {
  return bestBowlers;
  }

  public void setSeriesBestBatsman(List<PlayerForm> seriesBestPlayers)
  {
  List<PlayerForm> temp = new ArrayList<PlayerForm>();
  if (seriesBestPlayers != null && seriesBestPlayers.size() > 0)
  {
    for (PlayerForm playerForm : seriesBestPlayers)
    {
      if (playerForm != null && playerForm.getSkill() != null && (playerForm.getRuns() != null && !playerForm.getRuns().trim().equals("") && Integer.parseInt(playerForm.getRuns()) > 0))
      {
        temp.add(playerForm);
      }

    }
  }
  Collections.sort(temp, new CoreTeamPlayerRunComparator());
  bestBatsmen = new ArrayList<PlayerForm>();
  for (PlayerForm playerForm : temp)
  {
    bestBatsmen.add(playerForm);
    if (bestBatsmen.size() == size)
    {
      break;
    }
  }
  }

  public void setSeriesBestBowlers(List<PlayerForm> seriesBestPlayers)
  {
  List<PlayerForm> temp = new ArrayList<PlayerForm>();
  if (seriesBestPlayers != null && seriesBestPlayers.size() > 0)
  {
    for (PlayerForm playerForm : seriesBestPlayers)
    {

      if (playerForm.getWickets() != null && !playerForm.getWickets().trim().equals("") && Integer.parseInt(playerForm.getWickets()) > 0)
      {
        temp.add(playerForm);
      }
    }
  }

  Collections.sort(temp, new CoreTeamPlayerWicketsComparator());
  bestBowlers = new ArrayList<PlayerForm>();
  for (PlayerForm playerForm : temp)
  {
    bestBowlers.add(playerForm);
    if (bestBowlers.size() == size)
    {
      break;
    }
  }

  }

  public void setSeriesSCTBestPlayer(List<PlayerForm> seriesBestPlayers)
  {
  List<PlayerForm> temp = new ArrayList<PlayerForm>();

  if (seriesBestPlayers != null && seriesBestPlayers.size() > 0)
  {
    for (PlayerForm playerForm : seriesBestPlayers)
    {
      temp.add(playerForm);
    }
  }
  Collections.sort(temp, new CoreTeamPlayerScoreComparator());
  sctBestPlayers = new ArrayList<PlayerForm>();
  for (PlayerForm playerForm : temp)
  {
    sctBestPlayers.add(playerForm);
    if (sctBestPlayers.size() == size)
    {
      break;
    }
  }
  }

  private void setTopUserTeams(List<UserTeamForm> userTeamForms2)
  {
  userTeamForms = new ArrayList<UserTeamForm>();
  for (UserTeamForm userTeamForm : userTeamForms2)
  {
    userTeamForms.add(userTeamForm);
    if (userTeamForms.size() == 18)
    {
      break;
    }
  }
  }

}
