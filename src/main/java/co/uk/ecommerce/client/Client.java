package co.uk.ecommerce.client;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.uk.ecommerce.Cart;
import co.uk.ecommerce.CartEvaluation;
import co.uk.ecommerce.entity.Product;
import co.uk.ecommerce.entity.ProductParser;
import co.uk.ecommerce.entity.ProductType;
import co.uk.ecommerce.entity.factory.ProductTypeFactory;
import co.uk.ecommerce.loader.InputFeeds;
import co.uk.ecommerce.loader.Loader;


public class Client
{
	private final static String OFFER_FEED = "resource/infeed/offerfeed.txt";
	private final static String PRODUCT_FEED = "resource/infeed/productfeed.txt";
	private final static String BASKET = "AnatwineBasket";
	private static final Logger LOG = Logger.getLogger(ProductParser.class);

	public static void main(final String[] args)
	{
		final ApplicationContext context = new ClassPathXmlApplicationContext("spring-report.xml");
		/*
		 * Load offers and products
		 */
		final InputFeeds inputfeed = (InputFeeds) context.getBean("inputFeeds");
		final CartEvaluation evaluation = (CartEvaluation) context.getBean("cartEvaluation");
		final Cart cart = (Cart) context.getBean("cart");

		inputfeed.setInputFile(OFFER_FEED);
		inputfeed.loader(Loader.Offer);
		inputfeed.setInputFile(PRODUCT_FEED);
		inputfeed.loader(Loader.Product);
		/*
		 * Collect input from command prompt and add to basket
		 */

		System.out.println("Please enter the values:[trousers,jacket,tie,shirt]");
		//final Scanner scanner = new Scanner(System.in);
		//final String data = scanner.nextLine();
		final String data = "jacket tie shirt ";
		final String values[] = data.split(" ");

		for (final String val : values)
		{
			if (!BASKET.equals(val))
			{
				final ProductType type = ProductTypeFactory.getType(val);
				final Optional<Product> product = inputfeed.getProductList().stream().filter(p -> p.getType().equals(type))
						.findFirst();
				cart.add(product.get());

			}
		}

		/*
		 * call calculation
		 */
		evaluation.evaluate(inputfeed.getOfferList(), cart);
		final double subtotal = cart.getSubTotal();
		final double total = cart.getOfferPrice();
		LOG.info("Subtotal:" + subtotal);
		cart.offerDetails();
		if (total == subtotal)
		{
			System.out.println("(No offers available)");
		}
		LOG.info("Total:" + total);

	}

}
