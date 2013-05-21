package cricket.hibernate.bf.team;

import java.util.Comparator;

import cricket.struts.actionforms.team.PlayerForm;

public class CoreTeamPlayerRunComparator implements Comparator<PlayerForm>
{

  public int compare(PlayerForm player1, PlayerForm player2)
  {

  int runs1 = Integer.parseInt(player1.getRuns());

  int runs2 = Integer.parseInt(player2.getRuns());

  if (runs1 < runs2)

    return 1;

  else if (runs1 > runs2)

    return -1;

  else

    return 0;
  }

}
