package cricket.struts.actions.team;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.util.Constants;
import common.util.RetriveContextData;
import common.util.UserUtil;

import cricket.struts.actionforms.team.CoreTeamForm;
import cricket.struts.helpers.team.ManageTeamDBHelper;

public class ViewCoreTeamDetailsAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.removeAttribute(Constants.VALIDATION_MESSAGE);
		String id = request.getParameter("id");
		ServletContext ctx = getServlet().getServletContext();
		HttpSession httpSession = request.getSession();
		// String seriesId = (String)request.getParameter(Constants.SERIES_ID);
		long coreTeamId = 0;
		if (id != null) {
			coreTeamId = Integer.parseInt(request.getParameter("id"));
		}
		List<CoreTeamForm> searchedCoreTeamList = new RetriveContextData().getCoreTeamDetails(ctx); // null;
		if (searchedCoreTeamList == null || searchedCoreTeamList.size() == 0) {
			searchedCoreTeamList = (ArrayList<CoreTeamForm>) new ManageTeamDBHelper().getAllCoreTeamDetails(UserUtil
					.getSeries());
			if (searchedCoreTeamList == null) {
				searchedCoreTeamList = new ArrayList<CoreTeamForm>();
			}
			// ctx.setAttribute(Constants.SEARCHED_CORE_TEAMS,
			// searchedCoreTeamList);
		}
		String forward = "success";

		/*
		 * if (operation != null && operation.equalsIgnoreCase("View")) {
		 */
		forward = "View";
		CoreTeamForm coreTeamForm = new CoreTeamForm();

		if (coreTeamId == 0) {
			/*
			 * request.setAttribute(Constants.VALIDATION_MESSAGE,
			 * "Select team to view details."); forward = "viewValidation";
			 * return mapping.findForward(forward);
			 */
			if (searchedCoreTeamList.size() > 0) {
				coreTeamForm = searchedCoreTeamList.get(0);
			} else {
				coreTeamForm = new CoreTeamForm();
			}
			coreTeamId = coreTeamForm.getId();
		}

		else {
			for (CoreTeamForm tempForm : searchedCoreTeamList) {
				if (coreTeamId == tempForm.getId().longValue()) {
					coreTeamForm = tempForm;
					break;
				}
			}

		}

		request.setAttribute(Constants.CORE_TEAM_FORM, coreTeamForm);

		httpSession.setAttribute(Constants.CORETEAM_ID, coreTeamId + "");
		return mapping.findForward(forward);

	}

}
