package net.retail.productsorting.domain.model.sorting;

import net.retail.productsorting.domain.model.Product;

public class SalesSortingCriteria implements SortingCriteria<Product> {

	private final int weight;

	public SalesSortingCriteria(int weight) {
		this.weight = weight;
	}

	@Override
	public String getName() {
		return "units sold";
	}

	@Override
	public int weigh(Product product) {
		return product.sales() * weight;
	}
}
