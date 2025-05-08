package net.retail.productsorting.domain.model.sorting;

import net.retail.productsorting.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class StockSortingCriteria implements SortingCriteria {

	@Override
	public String getName() {
		return "stock ratio";
	}

	@Override
	public int weigh(Product product) {
		var stock = product.getStock();
		return stock.s() + stock.m() + stock.l();
	}
}
