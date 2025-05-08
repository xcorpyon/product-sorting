package net.retail.productsorting.application;

import lombok.extern.slf4j.Slf4j;
import net.retail.productsorting.domain.model.Product;
import net.retail.productsorting.domain.model.Stock;

import java.util.List;

@Slf4j
public class ProductsProvider {

	public static List<Product> get() {
		var products = List.of(
				Product.builder()
						.id(1)
						.name("V-NECH BASIC SHIRT")
						.sales(100)
						.stock(new Stock(4, 9, 0))
						.build(),
				Product.builder()
						.id(2)
						.name("CONTRASTING FABRIC T-SHIRT")
						.sales(50)
						.stock(new Stock(35, 9, 9))
						.build(),
				Product.builder()
						.id(3)
						.name("RAISED PRINT T-SHIRT")
						.sales(80)
						.stock(new Stock(20, 2, 20))
						.build(),
				Product.builder()
						.id(4)
						.name("PLEATED T-SHIRT")
						.sales(3)
						.stock(new Stock(25, 30, 10))
						.build(),
				Product.builder()
						.id(5)
						.name("CONTRASTING LACE T-SHIRT")
						.sales(650)
						.stock(new Stock(0, 1, 0))
						.build(),
				Product.builder()
						.id(6)
						.name("SLOGAN T-SHIRT")
						.sales(20)
						.stock(new Stock(9, 2, 5))
						.build()
		);

		log.debug("products provided: {}", products);

		return products;
	}
}
