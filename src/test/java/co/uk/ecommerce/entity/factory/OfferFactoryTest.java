package co.uk.ecommerce.entity.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import co.uk.ecommerce.entity.PercentOffer;
import co.uk.ecommerce.entity.ProductType;
import co.uk.ecommerce.util.DateUtil;


public class OfferFactoryTest
{
	@Test
	public void testForPercentageOffer() throws ParseException
	{
		final Date date = new Date();
		final String fromDate = DateUtil.dateAsString(date);
		String offerString = "PercentOff,10%Offer on Trousers,";
		offerString += DateUtil.dateAsString(date) + "," + DateUtil.dateAsString(DateUtil.incrementDate(date));
		offerString += ",Trousers,10,,0";
		final PercentOffer offer = (PercentOffer) OfferFactory.getOffer(offerString);
		assertTrue(offer instanceof PercentOffer);
		assertEquals("10%Offer on Trousers", offer.getName());
		assertEquals(DateUtil.dateAsString(date), DateUtil.dateAsString(offer.getFromDate()));
		assertEquals(DateUtil.dateAsString(DateUtil.incrementDate(date)), DateUtil.dateAsString(offer.getToDate()));
		assertTrue(offer.getIncludedOnProducts().toString().equals(ProductType.TROUSERS.toString()));
		assertEquals(10, offer.getPercentage());
	}

}
