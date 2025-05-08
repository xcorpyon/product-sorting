package net.retail.productsorting.domain;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

import lombok.RequiredArgsConstructor;
import net.retail.productsorting.domain.model.sorting.AssignedWeight;
import net.retail.productsorting.domain.model.Product;
import net.retail.productsorting.domain.model.sorting.SortableRecord;
import net.retail.productsorting.domain.model.sorting.SortingCriteria;
import net.retail.productsorting.domain.port.in.SortingService;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DomainProductSortingService implements SortingService<Product> {

	private static final Comparator<SortableRecord<?>> ASSIGNED_WEIGHTS_SUM_COMPARATOR =
			comparingInt(record ->
					record.assignedWeights().stream()
							.mapToInt(AssignedWeight::value)
							.sum());

	private final List<SortingCriteria<Product>> sortingCriteria;

	@Override
	public List<SortableRecord<Product>> sort(List<Product> products) {

		return products.stream()
				.map(product -> new SortableRecord<Product>(
						product,
						buildAssignedWeightsOf(product)))
				.sorted(ASSIGNED_WEIGHTS_SUM_COMPARATOR)
				.collect(toList());
	}

	private List<AssignedWeight> buildAssignedWeightsOf(Product product) {
		return sortingCriteria.stream()
				.map(sortingCriteria -> new AssignedWeight(
						sortingCriteria.weigh(product),
						sortingCriteria.getName()
				))
				.toList();
	}
}
