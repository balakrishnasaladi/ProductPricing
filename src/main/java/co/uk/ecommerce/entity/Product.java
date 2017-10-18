package co.uk.ecommerce.entity;

import java.util.Date;


/*
 * This is product entity contains properties about products
 */
public class Product
{
	private String name;
	private ProductType type;
	private double price;
	private String description;
	private Date onlineToDate;
	private Date onlineFromDate;

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public ProductType getType()
	{
		return type;
	}

	public void setType(final ProductType type)
	{
		this.type = type;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(final double price)
	{
		this.price = price;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}

	public Date getOnlineToDate()
	{
		return onlineToDate;
	}

	public void setOnlineToDate(final Date onlineToDate)
	{
		this.onlineToDate = onlineToDate;
	}

	public Date getOnlineFromDate()
	{
		return onlineFromDate;
	}

	public void setOnlineFromDate(final Date onlineFromDate)
	{
		this.onlineFromDate = onlineFromDate;
	}


	@Override
	public String toString()
	{
		return "Name:" + this.name + ",Type:" + this.type.toString() + ",Price:" + this.price;
	}
}


