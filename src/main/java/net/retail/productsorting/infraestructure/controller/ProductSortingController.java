package net.retail.productsorting.infraestructure.controller;

import lombok.RequiredArgsConstructor;
import net.retail.productsorting.application.ProductSortingAlgorithm;
import net.retail.productsorting.domain.model.Product;
import net.retail.productsorting.domain.model.sorting.SortableRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductSortingController {

	private final ProductSortingAlgorithm productSortingAlgorithm;

	@GetMapping
	public ResponseEntity<List<SortableRecord<Product>>> get() {
		var result = productSortingAlgorithm.sortProducts();
		return ResponseEntity.ok().body(result);
	}
}
