package net.retail.productsorting.domain.model.sorting;

import net.retail.productsorting.domain.model.Product;

public interface SortingCriteria {

	String getName();

	int weigh(Product product);
}
