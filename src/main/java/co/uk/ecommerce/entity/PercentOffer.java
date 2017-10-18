package co.uk.ecommerce.entity;

import java.util.Date;


public class PercentOffer implements Offer
{

	public ProductType getMustContain()
	{
		return mustContain;
	}

	public void setMustContain(final ProductType mustContain)
	{
		this.mustContain = mustContain;
	}

	public int getMustContainNumber()
	{
		return mustContainNumber;
	}

	public void setMustContainNumber(final int mustContainNumber)
	{
		this.mustContainNumber = mustContainNumber;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public Date getToDate()
	{
		return toDate;
	}

	public void setToDate(final Date toDate)
	{
		this.toDate = toDate;
	}

	public Date getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(final Date fromDate)
	{
		this.fromDate = fromDate;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}

	public int getPercentage()
	{
		return percentage;
	}

	public void setPercentage(final int percentage)
	{
		this.percentage = percentage;
	}

	public int getNumberOfItems()
	{
		return numberOfItems;
	}

	public void setNumberOfItems(final int numberOfItems)
	{
		this.numberOfItems = numberOfItems;
	}

	public ProductType getIncludedOnProducts()
	{
		return includedOnProducts;
	}

	public void setIncludedOnProducts(final ProductType includedOnProducts)
	{
		this.includedOnProducts = includedOnProducts;
	}

	public OfferType getType()
	{
		return type;
	}

	private String name;
	private final OfferType type = OfferType.PERCENTOFF;
	private Date toDate;
	private Date fromDate;
	private String description;
	private int percentage;
	//default treated as on one item
	private int numberOfItems;
	//default treated as on all items
	private ProductType includedOnProducts;

	private ProductType mustContain;
	private int mustContainNumber;

	@Override
	public double calculatePrice(final Product product)
	{
		//check % offer exist on particular products

		return (product.getPrice() * percentage / 100);
	}

	@Override
	public boolean offerExist(final Product product)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
