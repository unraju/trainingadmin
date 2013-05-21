package expenditure.helpers;

import org.hibernate.Session;

import common.util.HibernateUtil;

import expenditure.actionform.StaticDataForm;
import expenditure.businessfunction.Itemtype;

public class StaticDBHelper
{

	public static void addItemDetails(StaticDataForm sform)
	{
		Session session = HibernateUtil.getSession();
		Itemtype itemType = new Itemtype();

		itemType.setCode(sform.getCode());
		itemType.setDiscription(sform.getDescription());

		// itemType.setCashFlowType(cashFlowType);
		session.save(itemType);

	}

	public static void main(String[] arg)
	{

	}
}
