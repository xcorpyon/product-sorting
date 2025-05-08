package net.retail.productsorting.application;

import lombok.RequiredArgsConstructor;
import net.retail.productsorting.domain.model.WeighedProduct;
import net.retail.productsorting.domain.port.in.ProductSortingService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SortingAlgorithm {

	private final ProductSortingService productSortingService;

	public List<WeighedProduct> sortProducts() {
		var products = ProductsProvider.get();
		return productSortingService.sort(products);
	}
}
