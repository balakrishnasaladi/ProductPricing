package co.uk.ecommerce.loader;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import co.uk.ecommerce.entity.Offer;
import co.uk.ecommerce.entity.Product;
import co.uk.ecommerce.entity.ProductParser;
import co.uk.ecommerce.entity.factory.OfferFactory;


public class InputFeeds
{
	private static final Logger LOG = Logger.getLogger(InputFeeds.class);
	private String inputFile;
	private ProductParser parser;
	private final List<Offer> offerList = new ArrayList<Offer>();
	private final List<Product> productList = new ArrayList<Product>();

	public ProductParser getParser()
	{
		return parser;
	}


	public void setParser(final ProductParser parser)
	{
		this.parser = parser;
	}


	public String getInputFile()
	{
		return inputFile;
	}


	public void setInputFile(final String inputFile)
	{
		this.inputFile = inputFile;
	}


	public List<Product> getProductList()
	{
		return productList;
	}

	public List<Offer> getOfferList()
	{
		return offerList;
	}


	public void loader(final Loader loader)
	{
		//read csv file for existing offers
		LOG.debug("Load offers processing started");
		try (final Scanner scanner = new Scanner(new File(inputFile)))
		{
			while (scanner.hasNextLine())
			{
				final String str = scanner.nextLine();
				if (loader.Offer.equals(loader))
				{
					final Offer offer = OfferFactory.getOffer(str);
					if (offer != null)
					{
						offerList.add(offer);
					}
				}
				else if (loader.Product.equals(loader))
				{
					final Product product = parser.parse(str);
					if (product != null)
					{
						productList.add(product);
						LOG.info("Product:" + product.getName() + " Is active");
					}
				}

			}
			scanner.close();

			LOG.debug("Load processed sucessfully");
		}
		catch (final IOException | ParseException ioe)
		{
			LOG.error("Error processing input feed:" + loader.toString(), ioe);
		}

	}

}
