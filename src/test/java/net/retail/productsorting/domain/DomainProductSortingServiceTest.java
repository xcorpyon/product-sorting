package net.retail.productsorting.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import net.retail.productsorting.domain.model.Product;
import net.retail.productsorting.domain.model.Stock;
import net.retail.productsorting.domain.model.sorting.SortingCriteria;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.List;

class DomainProductSortingServiceTest {

	static final int ORIGINAL_FIRST_PRODUCT_ID = 1;
	static final int EXPECTED_FIRST_PRODUCT_ID = 2;

	static final List<Product> SAMPLE_PRODUCTS = List.of(
			new Product(ORIGINAL_FIRST_PRODUCT_ID, "original first product", 100, new Stock(4, 9, 0)),
			new Product(EXPECTED_FIRST_PRODUCT_ID, "expected first product", 50, new Stock(35, 9, 9)));

	static DomainProductSortingService underTest;

	@BeforeAll
	static void setUp() {

		var salesSortingCriteria = mock(SortingCriteria.class);
		when(salesSortingCriteria.weigh(ArgumentMatchers.any(Product.class)))
				.then(invocation -> {
						Product product = invocation.getArgument(0);
						return (product.id() == EXPECTED_FIRST_PRODUCT_ID) ? 5 : 100;
				});

		var stockSortingCriteria = mock(SortingCriteria.class);
		when(stockSortingCriteria.weigh(any()))
				.thenReturn(1);

		underTest = new DomainProductSortingService(
				List.of(salesSortingCriteria, stockSortingCriteria));
	}

	@Test
	void givenTwoProducts_whenSorted_thenItReturnsTheBestSellingFirst() {

		var actualRecords = underTest.sort(SAMPLE_PRODUCTS);

		assertThat(actualRecords)
				.hasSize(2)
				.first().satisfies(record ->
						assertThat(record.data().id()).isEqualTo(EXPECTED_FIRST_PRODUCT_ID));
	}
}