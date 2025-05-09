package net.retail.productsorting.domain;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

import lombok.RequiredArgsConstructor;
import net.retail.productsorting.domain.model.sorting.AssignedWeight;
import net.retail.productsorting.domain.model.sorting.SortedRecord;
import net.retail.productsorting.domain.model.sorting.SortingCriteria;
import net.retail.productsorting.domain.port.in.SortingService;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DomainSortingService<T> implements SortingService<T> {

	private static final Comparator<SortedRecord<?>> ASSIGNED_WEIGHTS_SUM_COMPARATOR =
			comparingInt(record ->
					record.assignedWeights().stream()
							.mapToInt(AssignedWeight::value)
							.sum());

	@Override
	public List<SortedRecord<T>> sort(
			List<T> records,
			List<SortingCriteria<T>> sortingCriteria) {

		return records.stream()
				.map(record -> new SortedRecord<T>(
						record,
						buildAssignedWeightsOf(record, sortingCriteria)))
				.sorted(ASSIGNED_WEIGHTS_SUM_COMPARATOR)
				.collect(toList());
	}

	private List<AssignedWeight> buildAssignedWeightsOf(
			T record,
			List<SortingCriteria<T>> sortingCriteria) {

		return sortingCriteria.stream()
				.map(criteria -> new AssignedWeight(
						criteria.weigh(record),
						criteria.getName()
				))
				.toList();
	}
}
