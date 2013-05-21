package cricket.struts.actions.player;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.CheckSessionValidate;
import common.util.Constants;
import common.util.RetriveContextData;

import cricket.hibernate.bf.player.Player;
import cricket.struts.actionforms.common.SeriesTypeForm;
import cricket.struts.actionforms.player.PlayerScoresForm;
import cricket.struts.actionforms.team.PlayerForm;
import cricket.struts.helpers.player.ManagePlayerDBHelper;

public class UpdatePlayersAction extends Action
{
  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
  CheckSessionValidate.isSessionValide(request, response);
  PlayerForm playerForm = (PlayerForm) form;
  String operation = request.getParameter("operation");
  String forward = "success";
  HttpSession session = request.getSession();
  //ServletContext ctx = getServlet().getServletContext();
  List<SeriesTypeForm> seriesTypeForms = new RetriveContextData().getSeriesTypeData();
  try
  {
    ManagePlayerDBHelper helper = new ManagePlayerDBHelper();
    // helper.searchPlayer(playerForm);
    if (operation.equals(Constants.SAVE_BTN_VALUE))
    {

      if (playerForm.getName() == null || playerForm.getName().trim().equals("") || playerForm.getCountry().startsWith("Ple"))
      {
        request.setAttribute(Constants.ERROR_MESSAGE, "Please enter all required details");
        forward = "validation";
      }
      else
      {
        if(playerForm.getId().intValue() == 0)
        {
          request.setAttribute(Constants.ERROR_MESSAGE, "Player Added Sucessfully");
          forward = "playeradded";
        }
        for(SeriesTypeForm seriesTypeForm:seriesTypeForms)
        {
          String id = (String) request.getParameter(seriesTypeForm.getId()+"ID");
          
          String matches = (String) request.getParameter(seriesTypeForm.getId()+"M");

          String runs = (String) request.getParameter(seriesTypeForm.getId()+"R");

          String wickets = (String) request.getParameter(seriesTypeForm.getId()+"W");

          String centuries = (String) request.getParameter(seriesTypeForm.getId()+"C");

          String halfCenturies = (String) request.getParameter(seriesTypeForm.getId()+"H");
          
          if((matches != null && !matches.trim().equals("")) || (runs != null && !runs.trim().equals("")) ||
              (wickets != null && !wickets.trim().equals("")) || (centuries != null && !centuries.trim().equals("")) ||
                  (halfCenturies != null && !halfCenturies.trim().equals("")))
                  {
                    PlayerScoresForm forScoresForm = new PlayerScoresForm();
                    if(id != null && !id.trim().equals("0"))
                    {
                      forScoresForm.setId(Long.parseLong(id.trim()));
                    }
                    forScoresForm.setMatches(matches);
                    forScoresForm.setRuns(runs);
                    forScoresForm.setWickets(wickets);
                    forScoresForm.setCenturies(centuries);
                    forScoresForm.setHalfCenturies(halfCenturies);
                    forScoresForm.setSeriesTypeId(seriesTypeForm.getId());
                    playerForm.addPlayerScores(forScoresForm);
                    
                  }
        }
        Player player = helper.updatePlayer(playerForm);
        List<PlayerForm> playerForms = (ArrayList<PlayerForm>) session.getAttribute(Constants.SEARCHED_PLAYERS);
        if (playerForms != null)
        {
          //playerForms.add(player.getActionForm());
          session.setAttribute(Constants.SEARCHED_PLAYERS, playerForms);
        }
      }
    }
    else if (operation.equals(Constants.CANCEL_BTN_VALUE))
    {
      forward = "cancel";
    }
  }
  catch (Exception e)
  {
    e.printStackTrace();
    request.setAttribute(Constants.ERROR_MESSAGE, e.getLocalizedMessage());
    forward = "error";
    request.setAttribute("success", "");
  }
  PlayerForm playerForm2 =  new PlayerForm();
  playerForm2.addDefaultScores(seriesTypeForms);
  request.setAttribute(Constants.PLAYER_FORM, playerForm2);
  return mapping.findForward(forward);

  }

}
