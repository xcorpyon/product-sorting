package net.retail.productsorting.domain.model.sorting;

import net.retail.productsorting.domain.model.Product;

import java.util.stream.IntStream;

public class StockSortingCriteria implements SortingCriteria<Product> {

	private final int weight;

	public StockSortingCriteria(int weight) {
		this.weight = weight;
	}

	@Override
	public String getName() {
		return "stock ratio";
	}

	@Override
	public int weigh(Product product) {
		var stock = product.stock();
		var availableSizes = IntStream.of(stock.s(), stock.m(), stock.l())
				.reduce(0, (total, items) -> (items > 0) ? total + 1 : total);
		return availableSizes * weight;
	}
}
