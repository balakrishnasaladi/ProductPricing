package co.uk.ecommerce.entity.factory;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;

import co.uk.ecommerce.entity.Offer;
import co.uk.ecommerce.entity.OfferType;
import co.uk.ecommerce.entity.OneOffOffer;
import co.uk.ecommerce.entity.PercentOffer;
import co.uk.ecommerce.util.DateUtil;


public class OfferFactory
{
	private static final Logger LOG = Logger.getLogger(OfferFactory.class);

	public static Offer getOffer(final String data) throws ParseException
	{

		LOG.debug("Loading offers started");

		final String values[] = data.split(",");
		final String type = values[0];
		final Date fromDate = DateUtil.convertDate(values[2]);
		final Date toDate = DateUtil.convertDate(values[3]);
		Offer offer = null;
		if (DateUtil.isCurrent(fromDate, toDate))
		{

			if (OfferType.PERCENTOFF.toString().equalsIgnoreCase(type))
			{
				final PercentOffer percentOffer = new PercentOffer();
				percentOffer.setName(values[1]);
				percentOffer.setFromDate(fromDate);
				percentOffer.setToDate(toDate);
				percentOffer.setIncludedOnProducts(ProductTypeFactory.getType(values[4]));
				percentOffer.setPercentage(new Integer(values[5]));
				percentOffer.setMustContain(ProductTypeFactory.getType(values[6]));

				percentOffer.setMustContainNumber(new Integer(values[7]));
				offer = percentOffer;

			}
			else if (OfferType.ONEOFF.toString().equalsIgnoreCase(type))
			{
				offer = new OneOffOffer();
			}
			LOG.info("Offer:" + values[1] + " Is active");
		}
		else
		{
			LOG.info("Offer:" + values[1] + " Is offline");
		}
		LOG.debug("Loading offers completed");
		return offer;
	}


}
