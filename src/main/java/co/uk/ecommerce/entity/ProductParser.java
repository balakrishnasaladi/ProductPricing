package co.uk.ecommerce.entity;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;

import co.uk.ecommerce.entity.factory.ProductTypeFactory;
import co.uk.ecommerce.util.DateUtil;


public class ProductParser
{
	private static final Logger LOG = Logger.getLogger(ProductParser.class);

	public Product parse(final String data) throws ParseException
	{
		final String values[] = data.split(",");
		final Product product = new Product();
		product.setName(values[0]);
		final Date fromDate = DateUtil.convertDate(values[2]);
		final Date toDate = DateUtil.convertDate(values[3]);
		if (DateUtil.isCurrent(fromDate, toDate))
		{
			product.setType(ProductTypeFactory.getType(values[1]));
			product.setOnlineFromDate(fromDate);
			product.setOnlineToDate(toDate);
			product.setPrice(new Double(values[4]));
			LOG.info("Product:" + values[1] + " Is Active");
			return product;
		}
		LOG.info("Product:" + values[1] + " Is Offline");
		return null;
	}
}
