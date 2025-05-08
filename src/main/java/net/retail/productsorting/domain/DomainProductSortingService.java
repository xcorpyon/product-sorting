package net.retail.productsorting.domain;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

import lombok.RequiredArgsConstructor;
import net.retail.productsorting.domain.model.AssignedWeight;
import net.retail.productsorting.domain.model.Product;
import net.retail.productsorting.domain.model.WeighedProduct;
import net.retail.productsorting.domain.model.WeighingCriteria;
import net.retail.productsorting.domain.port.in.ProductSortingService;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DomainProductSortingService implements ProductSortingService {

	private static final Comparator<WeighedProduct> ASSIGNED_WEIGHTS_SUM_COMPARATOR =
			comparingInt(weighedProduct ->
					weighedProduct.getAssignedWeights().stream()
							.mapToInt(AssignedWeight::value)
							.sum());

	private final List<WeighingCriteria> weighingCriterias;

	@Override
	public List<WeighedProduct> sort(List<Product> products) {

		return products.stream()
				.map(product -> WeighedProduct.builder()
						.id(product.getId())
						.name(product.getName())
						.sales(product.getSales())
						.stock(product.getStock())
						.assignedWeights(buildAssignedWeightsOf(product))
						.build())
				.sorted(ASSIGNED_WEIGHTS_SUM_COMPARATOR)
				.collect(toList());
	}

	private List<AssignedWeight> buildAssignedWeightsOf(Product product) {
		return weighingCriterias.stream()
				.map(weighingCriteria -> new AssignedWeight(
						weighingCriteria.weigh(product),
						weighingCriteria.getName()
				))
				.toList();
	}
}
