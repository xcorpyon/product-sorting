package net.retail.productsorting.domain.model;

import org.springframework.stereotype.Component;

@Component
public class StockWeighingCriteria implements WeighingCriteria {

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
