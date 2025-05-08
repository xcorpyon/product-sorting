package net.retail.productsorting.application;

import lombok.extern.slf4j.Slf4j;
import net.retail.productsorting.domain.model.Product;
import net.retail.productsorting.domain.model.Stock;

import java.util.List;

@Slf4j
public class ProductsProvider {

	public static List<Product> get() {
		var products = List.of(
				new Product(1, "V-NECH BASIC SHIRT", 100, new Stock(4, 9, 0)),
				new Product(2, "CONTRASTING FABRIC T-SHIRT", 50, new Stock(35, 9, 9)),
				new Product(3, "RAISED PRINT T-SHIRT", 80, new Stock(20, 2, 20)),
				new Product(4, "PLEATED T-SHIRT", 3, new Stock(25, 30, 10)),
				new Product(5, "CONTRASTING LACE T-SHIRT", 650, new Stock(0, 1, 0)),
				new Product(6, "SLOGAN T-SHIRT", 20, new Stock(9, 2, 5))
		);

		log.debug("products provided: {}", products);

		return products;
	}
}
