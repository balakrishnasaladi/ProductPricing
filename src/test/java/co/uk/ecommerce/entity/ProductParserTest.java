package co.uk.ecommerce.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import co.uk.ecommerce.util.DateUtil;


public class ProductParserTest
{

	@Test
	public void testValidProduct() throws ParseException
	{
		final ProductParser parser = new ProductParser();
		final Date date = new Date();
		final String fromDate = DateUtil.dateAsString(date);
		String productData = "Peter England,Trousers,";
		productData += DateUtil.dateAsString(date) + "," + DateUtil.dateAsString(DateUtil.incrementDate(date));
		productData += ",25.3";
		final Product product = parser.parse(productData);

		assertEquals("Peter England", product.getName());
		assertEquals(DateUtil.dateAsString(date), DateUtil.dateAsString(product.getOnlineFromDate()));
		assertEquals(DateUtil.dateAsString(DateUtil.incrementDate(date)), DateUtil.dateAsString(product.getOnlineToDate()));
		assertTrue(ProductType.TROUSERS.equals(product.getType()));
		final double price = 25.3;
		assertTrue(price == product.getPrice());

	}

	@Test
	public void testExpiredProduct() throws ParseException
	{
		final ProductParser parser = new ProductParser();
		final Date date = new Date();
		final String fromDate = DateUtil.dateAsString(date);
		String productData = "Peter England,Trousers,01-10-2017,05-10-2017,";
		productData += ",25.3";
		final Product product = parser.parse(productData);
		assertTrue(product == null);
	}
}
