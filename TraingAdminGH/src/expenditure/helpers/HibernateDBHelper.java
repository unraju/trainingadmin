package expenditure.helpers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.struts.actionforms.user.UserForm;
import common.util.DateUtil;
import common.util.HibernateUtil;

import expenditure.actionform.AddExpActionForm;
import expenditure.actionform.MonthlyExpActionForm;
import expenditure.actionform.ViewExpActionForm;
import expenditure.businessfunction.Expenditure;
import expenditure.businessfunction.ItemTypeHome;
import expenditure.businessfunction.Itemtype;

public class HibernateDBHelper
{

  public HibernateDBHelper()
  {
  }

  public static void addExpDetails(AddExpActionForm actionForm, UserForm user) throws Exception
  {

    Expenditure expenditure = new Expenditure();
    expenditure.setAmount(Double.parseDouble(actionForm.getAmount()));
    expenditure.setItem_name(actionForm.getItemName());
    String cardType = actionForm.getCardType();
    if (cardType != null && cardType != "" && !cardType.startsWith("Please"))
    {
      expenditure.setCard_type(cardType);
    }
    expenditure.setPayment_mode(actionForm.getPaymentType());
    Date date = null;
    try
    {
      date = DateUtil.getFormattedDateFromString(actionForm.getDate());
    }
    catch (ParseException e)
    {
      e.printStackTrace();
      throw e;
    }
    expenditure.setPurchase_date(date);
    expenditure.setCreated_date(DateUtil.getCurrentDateAsTimestamp());
    expenditure.setUserId(user.getId());
    Itemtype itemtype = ItemTypeHome.findItemTypeById(Long.valueOf(actionForm.getItemId()));
    expenditure.setItemtype(itemtype);
    Session session = HibernateUtil.getSession();
    session.save(expenditure);
    session.flush();
    // tx.commit();
    // session.close();
  }

  public static void main(String[] arg)
  {
    /*
     * //setSession(); try { viewExpDetailsUsingHibernate(new
     * ViewExpActionForm()); } catch (Exception e) { e.printStackTrace(); }
     */
  }

  public static List<AddExpActionForm> viewExpDetailsUsingHibernate(ViewExpActionForm searchTo, UserForm loginUser)
  {
    String fromDate = searchTo.getDate();
    String toDate = searchTo.getToDate();
    String PaymentMode = searchTo.getPaymentMode();
    String cardType = searchTo.getCardType();
    String itemName = searchTo.getItemName();
    String cashFlowType = searchTo.getCashFlowTypeId();

    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(Expenditure.class);
    if (itemName != null && !itemName.trim().equals(""))
    {
      criteria.add(Restrictions.like("item_name", itemName, MatchMode.ANYWHERE));
    }
    criteria.addOrder(Order.asc("purchase_date"));

    /* Itemtype itemtype = ItemTypeHome.findItemTypeById(itemid); */
    Criteria itemCriteria = criteria.createCriteria("itemtype");
    if (searchTo.getItemId() != null && !searchTo.getItemId().trim().equalsIgnoreCase("ALL"))
    {
      Long itemid = Long.parseLong(searchTo.getItemId());
      itemCriteria.add(Restrictions.eq("id", itemid));
    }
    if (cashFlowType != null && !cashFlowType.trim().startsWith("A"))
    {
      Long cashFlowId = Long.parseLong(cashFlowType);
      Criteria cashFlowCriteria = itemCriteria.createCriteria("cashFlowType");
      cashFlowCriteria.add(Restrictions.eq("id", cashFlowId));
    }

    if (PaymentMode != null)
    {
      criteria.add(Restrictions.eq("payment_mode", PaymentMode));
    }
    if (cardType != null && cardType != "" && !cardType.startsWith("Please"))
    {
      criteria.add(Restrictions.eq("card_type", cardType));
    }
    criteria.add(Restrictions.eq("userId", loginUser.getId()));
    if (fromDate != null && fromDate != "")
    {
      Date fd = null;
      Date td = null;
      if (toDate != null && !toDate.trim().equals(""))
      {
        toDate = toDate + " 23:59:59";
      }
      else
      // if (!(toDate != null && !"".equals(toDate.trim())))
      {
        toDate = DateUtil.getCurrentDateAsString();
        toDate = toDate + " 23:59:59";
      }
      /*
       * if (fromDate != null && !fromDate.trim().equals("")) { fromDate =
       * fromDate + " 23:59:59"; }
       */
      try
      {
        fd = DateUtil.getFormattedDateFromString(fromDate);
        td = DateUtil.getFormattedDateWithHH_MM_SS_FromString(toDate);
      }
      catch (Exception pe)
      {
        pe.printStackTrace();
      }
      criteria.add(Restrictions.between("purchase_date", fd, td));
    }

    List<Expenditure> expArray = (List<Expenditure>) criteria.list();
    List<AddExpActionForm> list = new ArrayList<AddExpActionForm>();
    for (Expenditure expenditure : expArray)
    {
      AddExpActionForm addExpActionForm = new AddExpActionForm();
      addExpActionForm.setId(expenditure.getId());
      addExpActionForm.setAmount(expenditure.getAmount().toString());
      if (expenditure.getCard_type() != null)
      {
        addExpActionForm.setCardType(expenditure.getCard_type());
      }
      else
      {
        addExpActionForm.setCardType(" ");
      }
      // addExpActionForm.setCreateDate(DateUtil.getStringFromDate(actionForm.getCreated_date()));
      addExpActionForm.setItemName(expenditure.getItem_name());
      addExpActionForm.setPaymentType(expenditure.getPayment_mode());
      addExpActionForm.setItemNo(expenditure.getItem_no());
      addExpActionForm.setItemType(expenditure.getItemtype().getDiscription());
      addExpActionForm.setDate(DateUtil.getStringFromDate(expenditure.getPurchase_date(), DateUtil.DD_MMM_YYYY_FORMAT));

      list.add(addExpActionForm);
    }
    return list;
  }

  public static List<AddExpActionForm> viewMonthlyExpDetailsUsingHibernate(MonthlyExpActionForm searchTo, UserForm loginUser)
  {

    int month = searchTo.getMonth();
    int year = searchTo.getYear();
    String cashFlowType = searchTo.getCashFlowType();
    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(Expenditure.class);

    Criteria itemCriteria = criteria.createCriteria("itemtype");
    if (searchTo.getItemId() != null && !searchTo.getItemId().trim().equalsIgnoreCase("ALL"))
    {
      Long itemid = Long.parseLong(searchTo.getItemId());
      itemCriteria.add(Restrictions.eq("id", itemid));
    }
    if (cashFlowType != null && !cashFlowType.trim().startsWith("A"))
    {
      Long cashFlowId = Long.parseLong(cashFlowType);
      Criteria cashFlowCriteria = itemCriteria.createCriteria("cashFlowType");
      cashFlowCriteria.add(Restrictions.eq("id", cashFlowId));
    }

    String fromdate = "01/01/2000";
    String todate = "01/01/2000";
    switch (month)
    {
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12:
      fromdate = "01/" + month + "/" + year;
      todate = "31/" + month + "/" + year;

      break;

    case 2:
      fromdate = "01/" + month + "/" + year;
      if (year % 4 == 0 || year % 100 == 0)
      {

        todate = "29/" + month + "/" + year;
      }
      else
      {

        todate = "28/" + month + "/" + year;
      }
      break;
    case 4:
    case 6:
    case 9:
    case 11:
      fromdate = "01/" + month + "/" + year;
      todate = "30/" + month + "/" + year;
      break;

    }
    try
    {
      Date fd = DateUtil.getDDMMYYYYFormattedDateFromString(fromdate);
      Date td = DateUtil.getDDMMYYYYFormattedDateFromString(todate);

      criteria.add(Restrictions.between("purchase_date", fd, td));

    }
    catch (Exception pe)
    {
      pe.printStackTrace();
    }
    criteria.addOrder(Order.asc("purchase_date"));

    criteria.add(Restrictions.eq("userId", loginUser.getId()));

    List<Expenditure> expArray = (List<Expenditure>) criteria.list();
    List<AddExpActionForm> list = new ArrayList<AddExpActionForm>();
    for (Expenditure expenditure : expArray)
    {
      AddExpActionForm addExpActionForm = new AddExpActionForm();
      addExpActionForm.setId(expenditure.getId());
      addExpActionForm.setAmount(expenditure.getAmount().toString());
      if (expenditure.getCard_type() != null)
      {
        addExpActionForm.setCardType(expenditure.getCard_type());
      }
      else
      {
        addExpActionForm.setCardType(" ");
      }
      addExpActionForm.setItemName(expenditure.getItem_name());
      addExpActionForm.setPaymentType(expenditure.getPayment_mode());
      addExpActionForm.setItemNo(expenditure.getItem_no());
      addExpActionForm.setDate(DateUtil.getStringFromDate(expenditure.getPurchase_date(), DateUtil.DD_MMM_YYYY_FORMAT));
      addExpActionForm.setItemType(expenditure.getItemtype().getDiscription());

      list.add(addExpActionForm);
    }
    return list;
  }

  public static List<AddExpActionForm> viewYearlyExpDetailsUsingHibernate(MonthlyExpActionForm searchTo, UserForm loginUser)
  {

    int year = searchTo.getYear();
    String cashFlowType = searchTo.getCashFlowType();
    String itemTypeId = searchTo.getItemId();

    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(Expenditure.class);

    Criteria itemCriteria = criteria.createCriteria("itemtype");
    if (itemTypeId != null && !itemTypeId.trim().equalsIgnoreCase("ALL"))
    {
      Long itemid = Long.parseLong(searchTo.getItemId());
      itemCriteria.add(Restrictions.eq("id", itemid));
    }
    else if (cashFlowType != null && !cashFlowType.trim().startsWith("A"))
    {
      Long cashFlowId = Long.parseLong(cashFlowType);
      Criteria cashFlowCriteria = itemCriteria.createCriteria("cashFlowType");
      cashFlowCriteria.add(Restrictions.eq("id", cashFlowId));
    }
    String fromdate = "01/01/" + year;
    String todate = "31/12/" + year;
    try
    {
      Date fd = DateUtil.getDDMMYYYYFormattedDateFromString(fromdate);
      Date td = DateUtil.getDDMMYYYYFormattedDateFromString(todate);

      criteria.add(Restrictions.between("purchase_date", fd, td));

    }
    catch (Exception pe)
    {
      pe.printStackTrace();
    }

    criteria.addOrder(Order.asc("purchase_date"));

    criteria.add(Restrictions.eq("userId", loginUser.getId()));

    List<Expenditure> expArray = (List<Expenditure>) criteria.list();
    List<AddExpActionForm> list = new ArrayList<AddExpActionForm>();
    for (Expenditure expenditure : expArray)
    {
      AddExpActionForm addExpActionForm = new AddExpActionForm();
      addExpActionForm.setId(expenditure.getId());
      addExpActionForm.setAmount(expenditure.getAmount().toString());
      if (expenditure.getCard_type() != null)
      {
        addExpActionForm.setCardType(expenditure.getCard_type());
      }
      else
      {
        addExpActionForm.setCardType(" ");
      }
      addExpActionForm.setItemName(expenditure.getItem_name());
      addExpActionForm.setPaymentType(expenditure.getPayment_mode());
      addExpActionForm.setItemNo(expenditure.getItem_no());
      addExpActionForm.setDate(DateUtil.getStringFromDate(expenditure.getPurchase_date(), DateUtil.DD_MMM_YYYY_FORMAT));
      addExpActionForm.setItemType(expenditure.getItemtype().getDiscription());

      list.add(addExpActionForm);
    }
    return list;
  }

  public static void deleteExpensive(ActionForm form)
  {

    // setSession();
    AddExpActionForm addExpActionForm = (AddExpActionForm) form;
    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(Expenditure.class);
    criteria.add(Restrictions.like("id", addExpActionForm.getId()));
    List<Expenditure> expArray = (List<Expenditure>) criteria.list();
    session.delete(expArray.get(0));
    session.flush();
    // tx.commit();
  }

  public static void updateExpDetails(ActionForm form) throws Exception
  {

    // setSession();
    AddExpActionForm addExpActionForm = (AddExpActionForm) form;
    Session session = HibernateUtil.getSession();
    Criteria criteria = session.createCriteria(Expenditure.class);
    criteria.add(Restrictions.like("id", addExpActionForm.getId()));
    List<Expenditure> expList = (List<Expenditure>) criteria.list();
    Expenditure expenditure = expList.get(0);
    updateExpenditureBO(expenditure, addExpActionForm);
    session.update(expenditure);
    session.flush();
    // tx.commit();
  }

  /**
   * @param expenditure
   * @param actionForm
   */
  private static void updateExpenditureBO(Expenditure expenditure, AddExpActionForm actionForm) throws Exception
  {
    expenditure.setAmount(Double.parseDouble(actionForm.getAmount()));
    expenditure.setItem_name(actionForm.getItemName());
    String cardType = actionForm.getCardType();
    if (cardType != null && cardType != "" && !cardType.startsWith("Please"))
    {
      expenditure.setCard_type(cardType);
    }
    expenditure.setPayment_mode(actionForm.getPaymentType());
    Date date = null;
    try
    {
      date = DateUtil.getFormattedDateFromString(actionForm.getDate());
    }
    catch (ParseException e)
    {

      e.printStackTrace();
      throw e;
    }
    expenditure.setPurchase_date(date);

  }
}
