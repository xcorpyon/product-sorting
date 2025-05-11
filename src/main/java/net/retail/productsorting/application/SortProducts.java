package net.retail.productsorting.application;

import lombok.RequiredArgsConstructor;
import net.retail.productsorting.domain.model.Product;
import net.retail.productsorting.domain.model.sorting.SortedRecord;
import net.retail.productsorting.domain.model.sorting.SortingCriteria;
import net.retail.productsorting.domain.port.in.SortingService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SortProducts {

	private final SortingService<Product> sortingService;

	public List<SortedRecord<Product>> sort(
			List<Product> products,
			List<SortingCriteria<Product>> sortingCriteria) {

		return sortingService.sort(products, sortingCriteria);
	}
}
