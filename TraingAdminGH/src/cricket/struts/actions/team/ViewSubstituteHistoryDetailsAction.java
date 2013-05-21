package cricket.struts.actions.team;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.actionforms.user.UserForm;
import common.util.CheckSessionValidate;
import common.util.Constants;

import cricket.hibernate.bf.player.PlayerSubstitution;
import cricket.struts.actionforms.team.PlayerSubstitutionForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class ViewSubstituteHistoryDetailsAction extends Action
{

  @Override
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
  CheckSessionValidate.isSessionValide(request, response);
  HttpSession session = request.getSession();
  String forward = "success";
  try
  {
    UserForm user = (UserForm) session.getAttribute(Constants.USER);
    List<PlayerSubstitution> substitutions = new ManageTeamDBHelper().getAllSubstitutedetails(user.getId());
    if (substitutions == null)
    {
      substitutions = new ArrayList<PlayerSubstitution>();
    }
    List<PlayerSubstitutionForm> substitutionForms = new ArrayList<PlayerSubstitutionForm>();
    for (PlayerSubstitution playerSubstitution : substitutions)
    {
      if (playerSubstitution != null) substitutionForms.add(playerSubstitution.getActionForm());
    }
    session.setAttribute(Constants.SUBSTITUTIONS_HISTORY, substitutionForms);
  }
  catch (Exception e)
  {
    e.printStackTrace();
    forward = "error";
    request.setAttribute("error", e.getLocalizedMessage());
  }

  return mapping.findForward(forward);
  }

}
