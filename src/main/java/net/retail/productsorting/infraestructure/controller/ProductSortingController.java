package net.retail.productsorting.infraestructure.controller;

import lombok.RequiredArgsConstructor;
import net.retail.productsorting.application.SortProducts;
import net.retail.productsorting.domain.model.Product;
import net.retail.productsorting.domain.model.sorting.SalesSortingCriteria;
import net.retail.productsorting.domain.model.sorting.SortingCriteria;
import net.retail.productsorting.domain.model.sorting.StockSortingCriteria;
import net.retail.productsorting.infraestructure.controller.dto.ProductResponse;
import net.retail.productsorting.infraestructure.controller.mapper.ControllerSortedRecordMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductSortingController {

	private static final String SORTING_CRITERIA_PARAMETER = "sortingCriteriaWeight";
	private static final String UNIT_SALES_SORTING_CRITERIA = "US";
	private static final String STOCK_RATIO_SORTING_CRITERIA = "SR";

	private final SortProducts sortProducts;

	private final ControllerSortedRecordMapper sortedRecordMapper;

	@GetMapping
	public ResponseEntity<List<ProductResponse>> get(
			@RequestParam(
							name = SORTING_CRITERIA_PARAMETER,
							required = false)  // lets the advice do the checking
					List<String> sortingCriteriaParams) {

		var sortingCriteria = buildSortingCriteria(sortingCriteriaParams);
		var products = ProductsQueryParamRetriever.retrieve();

		var sortedRecords = sortProducts.sort(products, sortingCriteria);
		var sortedProducts = sortedRecordMapper.toProductResponse(sortedRecords);
		return ResponseEntity.ok().body(sortedProducts);
	}

	private List<SortingCriteria<Product>> buildSortingCriteria(
			List<String> sortingCriteriaParams) {

		if (sortingCriteriaParams == null || sortingCriteriaParams.size() != 2) {
			throw new InvalidRequestException(ERROR_MESSAGES.CRITERIA_PARAMETERS_TOTAL);
		}

		var sortingCriteria = sortingCriteriaParams.stream()
				.map(convertToSortingCriteria())
				.toList();

		if (sortingCriteria.get(0).getName().equals(
				sortingCriteria.get(1).getName())) {
			throw new InvalidRequestException((ERROR_MESSAGES.CRITERIA_CODES_ARE_EQUAL));
		}

		return sortingCriteria;
	}

	private Function<String, SortingCriteria<Product>> convertToSortingCriteria() {
		return param -> {
				var paramChunk = param.split("-");
				if (paramChunk.length != 2) {
					throw new InvalidRequestException(ERROR_MESSAGES.CRITERIA_PARAMETER_NOT_FORMATTED);
				}
				var criteriaCode = paramChunk[0];
				var criteriaValue = paramChunk[1];
				if (! isNumeric(criteriaValue)) {
					throw new InvalidRequestException(ERROR_MESSAGES.CRITERIA_VALUE_NOT_NUMERIC);
				}
				return switch(criteriaCode) {
					case UNIT_SALES_SORTING_CRITERIA ->
							new SalesSortingCriteria(Integer.parseInt(criteriaValue));
					case STOCK_RATIO_SORTING_CRITERIA ->
							new StockSortingCriteria(Integer.parseInt(criteriaValue));
					default ->
							throw new InvalidRequestException(ERROR_MESSAGES.CRITERIA_CODE_NOT_CORRECT);
				};
		};
	}

	private boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		return Pattern.compile("-?\\d+").matcher(strNum).matches();
	}

	private static final class ERROR_MESSAGES {
		private static final String CRITERIA_PARAMETERS_TOTAL =
				"2 and only 2 '" + SORTING_CRITERIA_PARAMETER + "' parameters are allowed";
		private static final String CRITERIA_PARAMETER_NOT_FORMATTED =
				  "'" + SORTING_CRITERIA_PARAMETER + "' parameter criteria must be specified"
				+ " in 'XX-#' format, where 'XX' is the criteria and '#' its weight";
		private static final String CRITERIA_VALUE_NOT_NUMERIC =
				  "'" + SORTING_CRITERIA_PARAMETER + "' parameter criteria must specify"
				+ " its weight as a positive number";
		private static final String CRITERIA_CODE_NOT_CORRECT =
				  "'" + SORTING_CRITERIA_PARAMETER + "' parameter criteria must be specified"
				+ " in 'XX-#' format, where 'XX' is the criteria and and should be one of "
				+ "'" + UNIT_SALES_SORTING_CRITERIA + "' or '" + STOCK_RATIO_SORTING_CRITERIA + "'";
		private static final String CRITERIA_CODES_ARE_EQUAL =
				"'" + SORTING_CRITERIA_PARAMETER + "' parameters must be different";
	}
}
