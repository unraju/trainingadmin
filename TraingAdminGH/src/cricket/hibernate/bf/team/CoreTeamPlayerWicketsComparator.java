package cricket.hibernate.bf.team;

import java.util.Comparator;

import cricket.struts.actionforms.team.PlayerForm;

public class CoreTeamPlayerWicketsComparator implements Comparator<PlayerForm>
{

  public int compare(PlayerForm player1, PlayerForm player2)
  {

  
  int runs1 = 0;
  if(player1.getWickets() != null)
  runs1=Integer.parseInt(player1.getWickets());

  int runs2 = 0;
  if(player2.getWickets() != null)
  runs2=Integer.parseInt(player2.getWickets());

  if (runs1 < runs2)

    return 1;

  else if (runs1 > runs2)

    return -1;

  else

    return 0;
  }

}
