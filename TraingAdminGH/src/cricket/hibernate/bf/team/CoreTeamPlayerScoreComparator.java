package cricket.hibernate.bf.team;

import java.util.Comparator;

import cricket.struts.actionforms.team.PlayerForm;

public class CoreTeamPlayerScoreComparator implements Comparator<PlayerForm>
{

  public int compare(PlayerForm player1, PlayerForm player2)
  {

  int runs1 = Integer.parseInt(player1.getScore());

  int runs2 = Integer.parseInt(player2.getScore());

  if (runs1 < runs2)

    return 1;

  else if (runs1 > runs2)

    return -1;

  else

    return 0;
  }

}
