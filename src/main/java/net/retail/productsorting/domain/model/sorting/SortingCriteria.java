package net.retail.productsorting.domain.model.sorting;

public interface SortingCriteria<T> {

	String getName();

	int weigh(T data);
}
