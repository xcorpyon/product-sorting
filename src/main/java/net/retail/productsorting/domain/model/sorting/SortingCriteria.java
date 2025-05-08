package net.retail.productsorting.domain.model.sorting;

import net.retail.productsorting.domain.model.Product;

public interface SortingCriteria<T> {

	String getName();

	int weigh(T data);
}
