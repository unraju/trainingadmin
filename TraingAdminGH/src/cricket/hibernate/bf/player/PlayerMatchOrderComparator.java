package cricket.hibernate.bf.player;

import java.util.Comparator;

import cricket.struts.actionforms.team.PlayerMatchScoresForm;

public class PlayerMatchOrderComparator implements Comparator<PlayerMatchScoresForm>
{

  public int compare(PlayerMatchScoresForm player1, PlayerMatchScoresForm player2)
  {
  int runs1 = 0;
  if(player1.getOrder() != null && !player1.getOrder().trim().equals(""))
  {
   runs1 = Integer.parseInt(player1.getOrder());
  }

  int runs2 = 0;
  if(player2.getOrder() != null && !player2.getOrder().trim().equals(""))
  {
   runs2 = Integer.parseInt(player2.getOrder());
  }

  if (runs1 > runs2)

    return 1;

  else if (runs1 < runs2)

    return -1;

  else

    return 0;
  }

}
