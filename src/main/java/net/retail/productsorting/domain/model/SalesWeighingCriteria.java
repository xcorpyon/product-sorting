package net.retail.productsorting.domain.model;

import org.springframework.stereotype.Component;

@Component
public class SalesWeighingCriteria implements WeighingCriteria {

	@Override
	public String getName() {
		return "unit sales";
	}

	@Override
	public int weigh(Product product) {
		return product.getSales();
	}
}
