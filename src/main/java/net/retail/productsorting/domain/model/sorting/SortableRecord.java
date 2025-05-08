package net.retail.productsorting.domain.model.sorting;

import java.util.List;

public record SortableRecord<T> (
		T data,
		List<AssignedWeight> assignedWeights
) { }
