package net.retail.productsorting.domain.port.in;

import net.retail.productsorting.domain.model.sorting.SortableRecord;

import java.util.List;

public interface SortingService<T> {

	List<SortableRecord<T>> sort(List<T> data);
}
