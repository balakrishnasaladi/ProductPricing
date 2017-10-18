package co.uk.ecommerce.entity.factory;

import org.apache.log4j.Logger;

import co.uk.ecommerce.entity.ProductType;


public class ProductTypeFactory
{
	private static final Logger LOG = Logger.getLogger(OfferFactory.class);

	public static ProductType getType(final String name)
	{
		if (ProductType.JACKET.toString().equalsIgnoreCase(name))
		{
			return ProductType.JACKET;
		}
		else if (ProductType.TROUSERS.toString().equalsIgnoreCase(name))
		{
			return ProductType.TROUSERS;
		}
		else if (ProductType.TIE.toString().equalsIgnoreCase(name))
		{
			return ProductType.TIE;
		}
		else if (ProductType.SHIRT.toString().equalsIgnoreCase(name))
		{
			return ProductType.SHIRT;
		}
		else
		{
			LOG.error("New product type found : " + name);
			return null;

		}
	}
}
