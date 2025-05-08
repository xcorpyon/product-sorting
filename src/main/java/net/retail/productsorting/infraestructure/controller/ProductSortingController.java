package net.retail.productsorting.infraestructure.controller;

import lombok.RequiredArgsConstructor;
import net.retail.productsorting.application.SortingAlgorithm;
import net.retail.productsorting.domain.model.WeighedProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductSortingController {

	private final SortingAlgorithm sortingAlgorithm;

	@GetMapping
	public ResponseEntity<List<WeighedProduct>> get() {
		var result = sortingAlgorithm.sortProducts();
		return ResponseEntity.ok().body(result);
	}
}
