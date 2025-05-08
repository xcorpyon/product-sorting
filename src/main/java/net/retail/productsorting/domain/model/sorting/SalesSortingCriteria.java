package net.retail.productsorting.domain.model.sorting;

import net.retail.productsorting.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class SalesSortingCriteria implements SortingCriteria<Product> {

	@Override
	public String getName() {
		return "unit sales";
	}

	@Override
	public int weigh(Product product) {
		return product.sales();
	}
}
