package net.retail.productsorting.domain.port.in;

import net.retail.productsorting.domain.model.sorting.SortedRecord;

import java.util.List;

public interface SortingService<T> {

	List<SortedRecord<T>> sort(List<T> data);
}
