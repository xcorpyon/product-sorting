package net.retail.productsorting.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import net.retail.productsorting.domain.model.sorting.SortingCriteria;
import org.junit.jupiter.api.Test;

import java.util.List;

class DomainSortingServiceTest {

	DomainSortingService<Object> underTest = new DomainSortingService<>();

	@Test
	void givenARecordWithMoreWeightThanTheOther_whenSorted_thenLatterIsReturnedFirst() {

		final var originalFirstRecord = new Object();
		final var expectedFirstRecord = new Object();

		final var records = List.of(originalFirstRecord, expectedFirstRecord);

		final SortingCriteria<Object> sortingCriteria = mock(SortingCriteria.class);
		final var higherWeight = 2;
		final var lowerWeight = 1;
		when(sortingCriteria.weigh(originalFirstRecord)).thenReturn(higherWeight);
		when(sortingCriteria.weigh(expectedFirstRecord)).thenReturn(lowerWeight);

		final var actualRecords = underTest.sort(
				records,
				List.of(sortingCriteria));

		assertThat(actualRecords)
				.hasSize(2)
				.first().satisfies(record ->
						assertThat(record.data()).isSameAs(expectedFirstRecord)
				);
	}

	@Test
	void givenSeveralSortingCriteria_whenSorted_thenTheSumOfTheirResultingWeightsIsApplied() {

		final var originalFirstRecord = new Object();
		final var expectedFirstRecord = new Object();

		final var records = List.of(originalFirstRecord, expectedFirstRecord);

		final SortingCriteria<Object> sortingCriteria1 = mock(SortingCriteria.class);
		final var sortingCriteria1HigherWeight = 1;
		final var sortingCriteria1LowerWeight = 0;
		when(sortingCriteria1.weigh(originalFirstRecord)).thenReturn(sortingCriteria1LowerWeight);
		when(sortingCriteria1.weigh(expectedFirstRecord)).thenReturn(sortingCriteria1HigherWeight);
		// originalFirstRecord has the lowest weight in sortingCriteria1

		final SortingCriteria<Object> sortingCriteria2 = mock(SortingCriteria.class);
		final var sortingCriteria2HigherWeight = 5;
		final var sortingCriteria2LowerWeight = 3;
		when(sortingCriteria2.weigh(originalFirstRecord)).thenReturn(sortingCriteria2HigherWeight);
		when(sortingCriteria2.weigh(expectedFirstRecord)).thenReturn(sortingCriteria2LowerWeight);
		// expectedFirstRecord has the lowest weight in sortingCriteria1

		final var actualRecords = underTest.sort(
				records,
				List.of(sortingCriteria1, sortingCriteria2));

		assertThat(actualRecords)
				.hasSize(2)
				.first().satisfies(record ->
						assertThat(record.data()).isSameAs(expectedFirstRecord)
				);
	}
}