package co.uk.ecommerce.entity;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class PercentOfferTest
{
	@Test
	public void testcalculatePrice()
	{
		final Product product = new Product();
		final PercentOffer offer = new PercentOffer();
		offer.setPercentage(10);
		product.setPrice(40);
		assertTrue(offer.calculatePrice(product) == 4);
	}

}
