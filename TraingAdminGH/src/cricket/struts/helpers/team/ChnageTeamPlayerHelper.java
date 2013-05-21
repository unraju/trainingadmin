package cricket.struts.helpers.team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import common.hibernate.bf.user.User;
import common.util.Constants;
import common.util.DateUtil;
import common.util.HibernateUtil;
import common.util.UserUtil;

import cricket.hibernate.bf.player.Player;
import cricket.hibernate.bf.player.PlayerSubstitution;
import cricket.hibernate.bf.team.CoreTeam;
import cricket.hibernate.bf.team.UserTeam;
import cricket.hibernate.bf.team.UserTeamPlayers;
import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.actionforms.team.UserTeamForm;

public class ChnageTeamPlayerHelper
{

 public  Player findPlayerById(Long id)
  {
    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(Player.class);
    criteria.add(Restrictions.eq("id", id));
    List<Player> list = criteria.list();
    if(list != null && list.size()>0)
    {
    return list.get(0);
    }
    else
    {
      return null;
    }
  }

  private  UserTeam findUserTeamById(Long id)
  {
    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(UserTeam.class);
    criteria.add(Restrictions.eq("id", id));
    List<UserTeam> list = criteria.list();
    return list.get(0);
  }

  public  void changeTeamPlayer(UserTeamForm userTeamForm, PlayerForm oldPlayerForm, PlayerForm newPlayerForm, boolean selectedAsCaptain ) throws Exception
  {
    Session session = HibernateUtil.getSession();
    try
    {
      Player player_old = findPlayerById(oldPlayerForm.getId());
      Long deductPoints = 0L;
      Long substituteCount = 0L;
      String SubtType = "";
      List<String> playersIds = new ArrayList<String>();

      for (PlayerForm playerForm1 : userTeamForm.getPlayers())
      {
        playersIds.add(playerForm1.getId().toString());
      }
      
      if(!player_old.isActive() && !selectedAsCaptain)
      {
        deductPoints = 0L;
        substituteCount = 0L;
        SubtType = Constants.CHN_INACT_PLY;
      }
      else if (!player_old.isActive() && selectedAsCaptain)
      {
        deductPoints = 50L;
        substituteCount = 0L;
        SubtType = Constants.CHN_INACT_PLY_AS_CAPTAIN;
      } 
      else if (newPlayerForm != null && !selectedAsCaptain && !oldPlayerForm.isCaptain())
      {
        deductPoints = 25L;
        substituteCount = 1L;
        SubtType = Constants.SUB_PLAYER;
      }
      else if (newPlayerForm != null && selectedAsCaptain)
      {
        deductPoints = 75L;
        substituteCount = 1L;
        SubtType = Constants.SUB_PLAYER_AS_CAPTAIN;
      } 
      else if (newPlayerForm ==  null && selectedAsCaptain)
      {
        deductPoints = 50L;
        substituteCount = 0L;
        SubtType = Constants.CHN_CAPT_IN_TEAM;
      }
      else if (newPlayerForm != null && oldPlayerForm.isCaptain())
      {
        deductPoints = 75L;
        substituteCount = 1L;
        SubtType = Constants.SUB_CAPT_OUT_SIDE;
      }

      if (SubtType.equals(Constants.SUB_CAPT_OUT_SIDE) || SubtType.equals(Constants.SUB_PLAYER) 
            || SubtType.equals(Constants.CHN_INACT_PLY))
      {
        // update old player
        UserTeamPlayers oldUserTeamPlayer = findUserTeamPlayer(userTeamForm.getId(), oldPlayerForm.getId());

        UserTeamPlayers newuserTeamPlayer = oldUserTeamPlayer.clone();

        oldUserTeamPlayer.setPlayerId(newPlayerForm.getCoreTeamPlayerId());
        oldUserTeamPlayer.setRuns(null);
        oldUserTeamPlayer.setWickets(null);
        oldUserTeamPlayer.setCatches(null);
        oldUserTeamPlayer.setScore(null);
        oldUserTeamPlayer.setFours(null);
        oldUserTeamPlayer.setSixers(null);
        oldUserTeamPlayer.setHalfCenturies(null);
        oldUserTeamPlayer.setCenturies(null);
        oldUserTeamPlayer.setStartDate(DateUtil.getCurrentDateAsTimestamp());
        oldUserTeamPlayer.setCoreTeamName(findCoreTeamNameByPlayerId(oldUserTeamPlayer.getPlayerId()));
        session.save(oldUserTeamPlayer);

        newuserTeamPlayer.setActive(false);
        newuserTeamPlayer.setEndDate(DateUtil.getCurrentDateAsTimestamp());
        
        session.save(newuserTeamPlayer);
      }
      else if (SubtType.equals(Constants.CHN_CAPT_IN_TEAM))
      {
        //System.out.println("%%% Constants.CHN_CAPT_IN_TEAM %%%");
        // update old player
        UserTeamPlayers oldPlayer = findTeamCaptain(userTeamForm.getId());
        UserTeamPlayers newPlayer = findUserTeamPlayer(userTeamForm.getId(), oldPlayerForm.getId());
        
        UserTeamPlayers tempOldPlayer = oldPlayer.clone();
        UserTeamPlayers tempNewPlayer = newPlayer.clone();

        // oldPlayer.setPlayerId(newPlayer.getId());
        oldPlayer.setRuns(null);
        oldPlayer.setWickets(null);
        oldPlayer.setCatches(null);
        oldPlayer.setScore(null);
        oldPlayer.setCaptain(false);
        oldPlayer.setFours(null);
        oldPlayer.setSixers(null);
        oldPlayer.setHalfCenturies(null);
        oldPlayer.setCenturies(null);
        oldPlayer.setStartDate(DateUtil.getCurrentDateAsTimestamp());
        session.save(oldPlayer);

        // newPlayer.setPlayerId(oldPlayer.getId());
        newPlayer.setRuns(null);
        newPlayer.setWickets(null);
        newPlayer.setCatches(null);
        newPlayer.setScore(null);
        newPlayer.setCaptain(true);
        newPlayer.setStartDate(DateUtil.getCurrentDateAsTimestamp());
        session.save(newPlayer);

        tempOldPlayer.setEndDate(DateUtil.getCurrentDateAsTimestamp());
        tempNewPlayer.setEndDate(DateUtil.getCurrentDateAsTimestamp());

        tempOldPlayer.setActive(false);
        tempNewPlayer.setActive(false);

        session.save(tempOldPlayer);
        session.save(tempNewPlayer);
        
        newPlayerForm = newPlayer.getActionForm();
        oldPlayerForm = oldPlayer.getActionForm();
      }
      else if(SubtType.equals(Constants.SUB_PLAYER_AS_CAPTAIN) ||  SubtType.equals(Constants.CHN_INACT_PLY_AS_CAPTAIN))
      {
        // update old player
        UserTeamPlayers oldUserTeamPlayer = findUserTeamPlayer(userTeamForm.getId(), oldPlayerForm.getId());

        // Changing the old captain to Normal Player
        UserTeamPlayers oldTeamCaptain = findTeamCaptain(userTeamForm.getId());

        UserTeamPlayers newuserTeamPlayer = oldUserTeamPlayer.clone();

        oldUserTeamPlayer.setPlayerId(newPlayerForm.getCoreTeamPlayerId());
        oldUserTeamPlayer.setRuns(null);
        oldUserTeamPlayer.setWickets(null);
        oldUserTeamPlayer.setCatches(null);
        oldUserTeamPlayer.setScore(null);
        oldUserTeamPlayer.setFours(null);
        oldUserTeamPlayer.setSixers(null);
        oldUserTeamPlayer.setHalfCenturies(null);
        oldUserTeamPlayer.setCenturies(null);
     
        // setting this player as Captain.
        oldUserTeamPlayer.setCaptain(true);
        oldUserTeamPlayer.setStartDate(DateUtil.getCurrentDateAsTimestamp());
        oldUserTeamPlayer.setCoreTeamName(findCoreTeamNameByPlayerId(oldUserTeamPlayer.getPlayerId()));
        
        session.save(oldUserTeamPlayer);

        newuserTeamPlayer.setActive(false);
        newuserTeamPlayer.setEndDate(DateUtil.getCurrentDateAsTimestamp());
        session.save(newuserTeamPlayer);

        // Changing the old captain to Normal Player
        UserTeamPlayers newuserTeamCaptain = oldTeamCaptain.clone();

        //oldTeamCaptain.setPlayerId(oldTeamCaptain.getId());
        oldTeamCaptain.setRuns(null);
        oldTeamCaptain.setWickets(null);
        oldTeamCaptain.setCatches(null);
        oldTeamCaptain.setScore(null);
        // setting this player as Captain.
        oldTeamCaptain.setCaptain(false);
        oldTeamCaptain.setStartDate(DateUtil.getCurrentDateAsTimestamp());

        session.save(oldTeamCaptain);

        newuserTeamCaptain.setActive(false);
        newuserTeamCaptain.setEndDate(DateUtil.getCurrentDateAsTimestamp());
        session.save(newuserTeamCaptain);
      }
        

      PlayerSubstitution playerSubstitution = new PlayerSubstitution();
      playerSubstitution.setDate(DateUtil.getCurrentDateAsTimestamp());
      playerSubstitution.setOldPlayerName(oldPlayerForm.getName());
      playerSubstitution.setNewPlayerName(newPlayerForm.getName());
      playerSubstitution.setStatus("Completed");
      playerSubstitution.setSubstitionType(SubtType);
      playerSubstitution.setPointsDeducted(deductPoints);
      playerSubstitution.setUserTeamId(userTeamForm.getId());
      playerSubstitution.setSeriesId(UserUtil.getSeries());
      session.save(playerSubstitution);

      UserTeam userTeam = findUserTeamById(userTeamForm.getId());
      // userTeam.setSubstitutions(userTeam.getSubstitutions() -
      // substituteCount);
      Long totaldeduction = deductPoints;
      if (userTeam.getDeductedSubScore() != null)
      {
        totaldeduction = totaldeduction + userTeam.getDeductedSubScore().intValue();
      }
      userTeam.setDeductedSubScore(totaldeduction);
      userTeam.setUsedSubstitutions(userTeam.getUsedSubstitutions().intValue() + substituteCount);
      userTeam.setScore(userTeam.getScore() - deductPoints);
      session.save(userTeam);
      session.flush();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw e;
    }
  }

  public  List<PlayerSubstitution> getAllSubstitutedetails(User user)
  {
    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(PlayerSubstitution.class);
    UserTeam userTeam = getUserTeam(user);
    if (userTeam == null)
    {
      return null;
    }
    criteria.add(Restrictions.eq("userTeamId", userTeam.getId()));
    List<PlayerSubstitution> list = (ArrayList<PlayerSubstitution>) criteria.list();
    return list;
  }

  // return on all players including active & non active
  private  UserTeam getUserTeam(User user)
  {
    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(UserTeam.class);
    criteria.add(Restrictions.eq("user", user));
    Collection<UserTeam> list = criteria.list();
    for (UserTeam userTeam : list)
    {
      if (userTeam != null)
      {
        return userTeam;
      }
    }
    return null;
  }

  private  UserTeamPlayers findUserTeamPlayer(Long userTeamId, Long playerId)
  {
    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(UserTeamPlayers.class);
    criteria.add(Restrictions.eq("userTeamId", userTeamId));
    criteria.add(Restrictions.eq("playerId", playerId));
    List<UserTeamPlayers> list = criteria.list();
    if (list != null && list.size() > 0)
    {
      return list.get(0);
    }
    else
    {
      return null;
    }
  }
  
  private  UserTeamPlayers findTeamCaptain(Long userTeamId)
  {
    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(UserTeamPlayers.class);
    criteria.add(Restrictions.eq("userTeamId", userTeamId));
    criteria.add(Restrictions.eq("captain", true));
    criteria.add(Restrictions.eq("active", true));
    List<UserTeamPlayers> list = criteria.list();
    if (list != null && list.size() > 0)
    {
      return list.get(0);
    }
    else
    {
      return null;
    }
  }
  

  public static String findCoreTeamNameByPlayerId(Long playerId)
  {
  Session session = HibernateUtil.getSession();
  Criteria criteria = session.createCriteria(CoreTeam.class);
  Criteria pcriteria = criteria.createCriteria("players");
  pcriteria.add(Restrictions.eq("playerId", playerId));
  Criteria criteria2 = criteria.createCriteria("series");
  criteria2.add(Restrictions.eq("id", UserUtil.getSeries()));
  
  Collection<CoreTeam> list = criteria.list();
  for (CoreTeam coreTeam : list)
  {
    if (coreTeam != null)
    {
      return coreTeam.getCode();
    }
  }
  return "";
  }

}
