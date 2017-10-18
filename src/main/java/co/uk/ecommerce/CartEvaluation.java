package co.uk.ecommerce;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import co.uk.ecommerce.entity.Offer;
import co.uk.ecommerce.entity.ProductType;


public class CartEvaluation
{
	public void evaluate(final List<Offer> offers, final Cart cart)
	{
		final List<CartEntity> entries = cart.getCartEntries();

		final Map<ProductType, Integer> map = getUniqueProducts(entries);
		for (final CartEntity entry : entries)
		{
			final Optional<Offer> existOffer = offers.stream()
					.filter(p -> p.getIncludedOnProducts().equals(entry.getEntity().getType())).findFirst();
			if (existOffer.isPresent())
			{
				/*
				 * do pre-checks
				 */
				if (existOffer.isPresent() && preCheckForMultiOffer(map, existOffer.get()))
				{
					entry.setOfferPrice(existOffer.get().calculatePrice(entry.getEntity()));
				}
				entry.setOfferapplied(true);
			}
		}
	}

	private Map<ProductType, Integer> getUniqueProducts(final List<CartEntity> entries)
	{
		final Map<ProductType, Integer> uniqueProductMap = new ConcurrentHashMap<ProductType, Integer>();
		for (final CartEntity entry : entries)
		{
			final ProductType type = entry.getEntity().getType();
			if (uniqueProductMap.containsKey(type))
			{
				final int value = uniqueProductMap.get(type);
				uniqueProductMap.put(type, (value + 1));
			}
			else
			{
				uniqueProductMap.put(type, 1);
			}
		}
		return uniqueProductMap;
	}

	private boolean preCheckForMultiOffer(final Map<ProductType, Integer> map, final Offer offer)
	{
		final ProductType type = offer.getMustContain();

		if (type == null && offer.getMustContainNumber() == 0)
		{
			return true;
		}
		else if (map.containsKey(type) && map.get(type) >= offer.getMustContainNumber())
		{
			final int value = map.get(type);
			map.put(type, value - 1);
			return true;
		}
		return false;
	}
}
