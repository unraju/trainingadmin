package cricket.struts.actions.player;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ViewBestPlayersAction extends Action
{
  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
 /*
    if (operation.equals("1"))
    {
      session.setAttribute(Constants.SEARCHED_PLAYERS, helper.searchPlayer(playerForm));
      forward = "populateSearchedPlayers";
      // request.setAttribute("success", "Detals added Sucessfully");
    }
    elseif (operation.equals("1"))
    {
      session.setAttribute(Constants.SEARCHED_PLAYERS, helper.searchPlayer(playerForm));
      forward = "populateSearchedPlayers";
      // request.setAttribute("success", "Detals added Sucessfully");
    }
    if (operation.equals("1"))
    {
      session.setAttribute(Constants.SEARCHED_PLAYERS, helper.searchPlayer(playerForm));
      forward = "populateSearchedPlayers";
      // request.setAttribute("success", "Detals added Sucessfully");
    }

  */


  return mapping.findForward("");

  }
}
