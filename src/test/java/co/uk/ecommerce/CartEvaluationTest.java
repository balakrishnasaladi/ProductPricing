package co.uk.ecommerce;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import co.uk.ecommerce.entity.Offer;
import co.uk.ecommerce.entity.PercentOffer;
import co.uk.ecommerce.entity.Product;
import co.uk.ecommerce.entity.ProductType;


public class CartEvaluationTest
{
	@Test
	public void testOfferForJacketWithOffer()
	{
		final CartEvaluation evaluation = new CartEvaluation();
		final List<Offer> offers = new ArrayList<Offer>();
		final PercentOffer offer = new PercentOffer();
		offer.setIncludedOnProducts(ProductType.JACKET);
		offer.setPercentage(10);
		offers.add(offer);

		final Product product = new Product();
		product.setType(ProductType.JACKET);
		product.setPrice(40);
		final Cart cart = new Cart();
		cart.add(product);

		evaluation.evaluate(offers, cart);
		assertTrue(cart.getSubTotal() == 40);
		assertTrue(cart.getOfferPrice() == 4);
	}

	@Test
	public void testOfferForJacketWithoutOffer()
	{
		final CartEvaluation evaluation = new CartEvaluation();
		final List<Offer> offers = new ArrayList<Offer>();
		final PercentOffer offer = new PercentOffer();
		offer.setIncludedOnProducts(ProductType.TIE);
		offer.setPercentage(10);
		offers.add(offer);

		final Product product = new Product();
		product.setType(ProductType.JACKET);
		product.setPrice(40);
		final Cart cart = new Cart();
		cart.add(product);

		evaluation.evaluate(offers, cart);
		assertTrue(cart.getSubTotal() == 40);
		assertTrue(cart.getOfferPrice() == 0);
	}

	@Test
	public void testPercentOfferForTieWhenBoughtTwoJackets()
	{
		final CartEvaluation evaluation = new CartEvaluation();
		final List<Offer> offers = new ArrayList<Offer>();
		final PercentOffer offer = new PercentOffer();
		offer.setIncludedOnProducts(ProductType.TIE);
		offer.setPercentage(50);
		offers.add(offer);
		offer.setMustContain(ProductType.JACKET);
		offer.setMustContainNumber(2);

		final Product product = new Product();
		product.setType(ProductType.JACKET);
		product.setPrice(40);

		final Product productTie = new Product();
		productTie.setType(ProductType.TIE);
		productTie.setPrice(20);
		final Cart cart = new Cart();
		cart.add(product);
		cart.add(product);
		cart.add(productTie);

		evaluation.evaluate(offers, cart);
		assertTrue(cart.getSubTotal() == 100);
		assertTrue(cart.getOfferPrice() == 10);
	}

	@Test
	public void testPercentOfferForTieWhenBoughtOneJackets()
	{
		final CartEvaluation evaluation = new CartEvaluation();
		final List<Offer> offers = new ArrayList<Offer>();
		final PercentOffer offer = new PercentOffer();
		offer.setIncludedOnProducts(ProductType.TIE);
		offer.setPercentage(50);
		offers.add(offer);
		offer.setMustContain(ProductType.JACKET);
		offer.setMustContainNumber(2);

		final Product product = new Product();
		product.setType(ProductType.JACKET);
		product.setPrice(40);

		final Product productTie = new Product();
		productTie.setType(ProductType.TIE);
		productTie.setPrice(20);
		final Cart cart = new Cart();
		cart.add(product);
		cart.add(productTie);

		evaluation.evaluate(offers, cart);
		assertTrue(cart.getSubTotal() == 60);
		assertTrue(cart.getOfferPrice() == 0);
	}

}
