package co.uk.ecommerce;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import co.uk.ecommerce.entity.Product;


public class CartTest
{


	@Test
	public void testCartWithSingleProduct()
	{
		final Cart cart = new Cart();
		final List<CartEntity> entries = new ArrayList<CartEntity>();
		final CartEntity cartEntity = new CartEntity();
		final Product product = new Product();
		product.setPrice(12.8);
		cartEntity.setEntity(product);
		entries.add(cartEntity);
		cart.setCartEntries(entries);
		assertTrue(cart.getSubTotal() == 12.8);
		assertTrue(cart.getOfferPrice() == 12.8);
	}

	@Test
	public void testCartWithTwoProducts()
	{
		final Cart cart = new Cart();
		final List<CartEntity> entries = new ArrayList<CartEntity>();

		final CartEntity cartEntity = new CartEntity();
		final Product pant = new Product();
		pant.setPrice(12.8);
		cartEntity.setEntity(pant);
		entries.add(cartEntity);

		final CartEntity trouserCartEntity = new CartEntity();
		final Product trouser = new Product();
		trouser.setPrice(34.8);
		trouserCartEntity.setEntity(trouser);
		entries.add(trouserCartEntity);

		cart.setCartEntries(entries);

		final double val = BigDecimal.valueOf(12.8 + 34.8).setScale(2, RoundingMode.HALF_UP).doubleValue();
		assertTrue(cart.getSubTotal() == val);

	}

	@Test
	public void testCartClearOFfer()
	{
		final Cart cart = new Cart();
		final List<CartEntity> entries = new ArrayList<CartEntity>();

		final CartEntity cartEntity = new CartEntity();
		final Product pant = new Product();
		cartEntity.setEntity(pant);
		cartEntity.setOfferapplied(true);
		entries.add(cartEntity);

		final CartEntity trouserCartEntity = new CartEntity();
		final Product trouser = new Product();
		trouser.setPrice(34.8);
		trouserCartEntity.setEntity(trouser);
		trouserCartEntity.setOfferapplied(false);
		entries.add(trouserCartEntity);

		cart.setCartEntries(entries);
		cart.clearOffer();

		assertFalse(cart.getCartEntries().get(0).isOfferapplied());
		assertFalse(cart.getCartEntries().get(1).isOfferapplied());

	}

}
