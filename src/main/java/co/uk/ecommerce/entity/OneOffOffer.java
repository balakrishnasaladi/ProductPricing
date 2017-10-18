package co.uk.ecommerce.entity;

import java.util.Date;


public class OneOffOffer implements Offer
{


	@Override
	public boolean offerExist(final Product product)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double calculatePrice(final Product product)
	{
		if (product.getPrice() >= amount)
		{
			return product.getPrice() - amount;
		}
		return 0;
	}

	private String name;
	private final OfferType type = OfferType.ONEOFF;
	private Date toDate;
	private Date fromDate;
	private String description;
	private int amount;

	private int numberOfItems;

	private ProductType includedOnProducts;

}
