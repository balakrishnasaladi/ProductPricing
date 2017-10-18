package co.uk.ecommerce;

import co.uk.ecommerce.entity.Product;


public class CartEntity
{
	public double offerPrice;
	public boolean offerapplied = false;
	public Product entity;

	public double getOfferPrice()
	{
		return offerPrice;
	}

	public void setOfferPrice(final double offerPrice)
	{
		this.offerPrice = offerPrice;
	}

	public boolean isOfferapplied()
	{
		return offerapplied;
	}

	public void setOfferapplied(final boolean offerapplied)
	{
		this.offerapplied = offerapplied;
	}

	public Product getEntity()
	{
		return entity;
	}

	public void setEntity(final Product entity)
	{
		this.entity = entity;
	}
}
