package expenditure.businessfunction;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import common.util.HibernateUtil;

import expenditure.actionform.CashFlowTypeTO;
import expenditure.actionform.ItemTypeTO;

public class ItemTypeHome
{

  public static List<Itemtype> getItemTypeStaticData()
  {
    Session session =HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(Itemtype.class);
    List<Itemtype> list = criteria.list();

    return list;
  }

  public static Itemtype findItemTypeById(Long id)
  {
    Itemtype itemType = null;
    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(Itemtype.class);
    criteria.add(Restrictions.eq("id", id));
    List<Itemtype> list = criteria.list();
    if (list.size() > 0)
    {
      itemType = list.get(0);
    }
    return itemType;
  }

  public static String findDescById(Long id)
  {
    Itemtype itemType = null;
    String desc = null;
    Session session =HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(Itemtype.class);
    criteria.add(Restrictions.eq("id", id));
    List<Itemtype> list = criteria.list();
    if (list.size() > 0)
    {
      itemType = list.get(0);
      desc = itemType.getDiscription();
    }
    return desc;
  }

  public static List<ItemTypeTO> getExpItemTypes()
  {
    Session session = HibernateUtil.getSession();

    Criteria criteria = null;

    criteria = session.createCriteria(Itemtype.class);
    //criteria.add(Restrictions.eq("active", true));
    List<Itemtype> lookupTypeList = (List<Itemtype>) criteria.list();
    List<ItemTypeTO> list = new ArrayList<ItemTypeTO>();
    for (Itemtype itemType : lookupTypeList)
    {
      ItemTypeTO lookupTypeTo = new ItemTypeTO();
      lookupTypeTo.setId(itemType.getId());
      lookupTypeTo.setCode(itemType.getCode());
      lookupTypeTo.setDescription(itemType.getDiscription());
      if(itemType.getCashFlowType() != null)
      lookupTypeTo.setCashFlowType(itemType.getCashFlowType().getCashFlowType());
      list.add(lookupTypeTo);
    }

    return list;
  }

  public static List<CashFlowTypeTO> getCashFlowTypes()
  {
    Session session = HibernateUtil.getSession();

    Criteria criteria = null;

    criteria = session.createCriteria(CashFlowType.class);
    criteria.add(Restrictions.eq("active", true));
    List<CashFlowType> lookupTypeList = (List<CashFlowType>) criteria.list();
    List<CashFlowTypeTO> list = new ArrayList<CashFlowTypeTO>();
    for (CashFlowType cashFlowType : lookupTypeList)
    {
      CashFlowTypeTO cashFlowTypeTO = new CashFlowTypeTO();
      cashFlowTypeTO.setId(cashFlowType.getId());
      cashFlowTypeTO.setCashFlowType(cashFlowType.getCashFlowType());

      list.add(cashFlowTypeTO);
    }

    return list;
  }

}
