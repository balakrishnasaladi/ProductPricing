package co.uk.ecommerce.entity;

/*
 * Offers must be defined unique, if more than one offer exists for same product
 * system will apply any one offer
 *
 */
public interface Offer
{
	public double calculatePrice(Product product);

	public boolean offerExist(Product product);

	default public ProductType getIncludedOnProducts()
	{
		return getIncludedOnProducts();
	}

	default public int getMustContainNumber()
	{
		return getMustContainNumber();
	}

	default public ProductType getMustContain()
	{
		return getMustContain();
	}
}

