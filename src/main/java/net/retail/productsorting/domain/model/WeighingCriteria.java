package net.retail.productsorting.domain.model;

public interface WeighingCriteria {

	String getName();

	int weigh(Product product);
}
