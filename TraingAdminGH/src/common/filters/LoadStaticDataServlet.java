package common.filters;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadStaticDataServlet extends HttpServlet {
	ServletContext context;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("### doGet start ###");
		super.doGet(req, resp);
		loadstaticData();
		System.out.println("### doGet End ###");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("### doPost start ###");
		super.doPost(req, resp);
		loadstaticData();
		System.out.println("### doPost End ###");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		context = config.getServletContext();
		loadstaticData();
	}

	private void loadstaticData() throws ServletException {

		try {
			new LoadStaticDataHelper().loadstaticData(context);
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

}
