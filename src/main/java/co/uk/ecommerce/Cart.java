package co.uk.ecommerce;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import co.uk.ecommerce.entity.Product;


public class Cart
{
	public double getOfferPrice()
	{
		final double price = cartEntries.stream().mapToDouble(p -> p.getEntity().getPrice() - p.getOfferPrice()).sum();
		return BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	/*
	 * public boolean isOfferApplied(){ cartEntries.stream().anyMatch(p->p.isOfferapplied()== true) }
	 */

	public List<CartEntity> getCartEntries()
	{
		return cartEntries;
	}

	public void setCartEntries(final List<CartEntity> cartEntries)
	{
		this.cartEntries = cartEntries;
	}

	public double getSubTotal()
	{
		//return cartEntries.stream().collect(Collectors.summingDouble(p -> p.entity.getPrice()));
		final double price = cartEntries.stream().mapToDouble(p -> p.entity.getPrice()).sum();
		return BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	public void clearOffer()
	{
		cartEntries.stream().forEach(p -> p.setOfferapplied(false));
	}

	public void add(final Product product)
	{
		final CartEntity entry = new CartEntity();
		entry.setEntity(product);
		cartEntries.add(entry);
	}

	public void offerDetails()
	{
		cartEntries.forEach(p -> {
			if (p.getOfferPrice() > 0)
			{
				System.out.println(p.getEntity().getType() + " % OFF: -£" + p.getOfferPrice());
			}
		});
	}

	private List<CartEntity> cartEntries = new ArrayList<CartEntity>();


}
