package common.ehcache.elements;

import java.io.Serializable;
import java.util.List;

import expenditure.actionform.CashFlowTypeTO;
import expenditure.actionform.ItemTypeTO;

public class ExpensiveElement implements Serializable
{
  public static final String EXPENSIVE_DATA = "EXPENSIVE_DATA";

  public List<ItemTypeTO> itemTypes;

  public List<CashFlowTypeTO> cashFlowTypes;

  public ExpensiveElement(List<ItemTypeTO> itemTypes, List<CashFlowTypeTO> cashFlowTypes)
  {
    super();
    this.itemTypes = itemTypes;
    this.cashFlowTypes = cashFlowTypes;
  }

  public List<ItemTypeTO> getItemTypes()
  {
  return itemTypes;
  }

  public List<CashFlowTypeTO> getCashFlowTypes()
  {
  return cashFlowTypes;
  }



}
