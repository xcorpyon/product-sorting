package net.retail.productsorting.application;

import lombok.RequiredArgsConstructor;
import net.retail.productsorting.domain.model.Product;
import net.retail.productsorting.domain.model.sorting.SortableRecord;
import net.retail.productsorting.domain.port.in.SortingService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSortingAlgorithm {

	private final SortingService<Product> productSortingService;

	public List<SortableRecord<Product>> sortProducts() {
		var products = ProductsProvider.get();
		return productSortingService.sort(products);
	}
}
