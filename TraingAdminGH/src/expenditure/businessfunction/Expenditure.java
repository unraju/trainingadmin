package expenditure.businessfunction;

import java.io.Serializable;
import java.util.Date;

import common.hibernate.bf.user.User;

public class Expenditure implements Serializable
{

	private Integer id;

	private int item_no;

	private String item_name;

	private Date purchase_date;

	private String payment_mode;

	private String card_type;

	private Double amount;

	private Date created_date;

	private User loginUser;

	private Long userId;

	private Itemtype itemtype;

	public Expenditure()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public int getItem_no()
	{
		return item_no;
	}

	public void setItem_no(int item_no)
	{
		this.item_no = item_no;
	}

	public String getItem_name()
	{
		return item_name;
	}

	public void setItem_name(String item_name)
	{
		this.item_name = item_name;
	}

	public Date getPurchase_date()
	{
		return purchase_date;
	}

	public void setPurchase_date(Date purchase_date)
	{
		this.purchase_date = purchase_date;
	}

	public String getPayment_mode()
	{
		return payment_mode;
	}

	public void setPayment_mode(String payment_mode)
	{
		this.payment_mode = payment_mode;
	}

	public String getCard_type()
	{
		return card_type;
	}

	public void setCard_type(String card_type)
	{
		this.card_type = card_type;
	}

	public Double getAmount()
	{
		return amount;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Date getCreated_date()
	{
		return created_date;
	}

	public void setCreated_date(Date created_date)
	{
		this.created_date = created_date;
	}

	public User getLoginUser()
	{
		return loginUser;
	}

	public void setLoginUser(User loginUser)
	{
		this.loginUser = loginUser;
	}

	public Itemtype getItemtype()
	{
		return itemtype;
	}

	public void setItemtype(Itemtype itemtype)
	{
		this.itemtype = itemtype;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

}
