package test;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import common.util.HibernateUtil;

import cricket.hibernate.bf.team.UserTeamPlayers;

public class HibernateTest1
{

  public static void main(String[] args)
  {
  try
  {
    test1();
  }
  catch (ServletException e)
  {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  }

  private static void test1() throws ServletException
  {
  System.out.println("### Start ###");
  HibernateUtil.beginTransaction();
  Session session = HibernateUtil.getSession();
  //UserTeam userTeam = (UserTeam) session.get(UserTeam.class, 4L);
  Criteria criteria =  session.createCriteria(UserTeamPlayers.class);
  criteria.add(Restrictions.eq("userTeamId", 4L));
  Collection<UserTeamPlayers> players =criteria.list();
 
  // UserTeam userTeam = (UserTeam) session.load(UserTeam.class, 40000L);
 // System.out.println("### userTeam ###" + userTeam);
  System.out.println("### ------------------- ###");
 // List<UserTeamPlayers> userTeamPlayers = userTeam.getUserTeamPlayers();
  System.out.println("### ------------------- ###" +  players.size());
  HibernateUtil.commitTransaction();
  System.out.println("### End ###");
  }

}
