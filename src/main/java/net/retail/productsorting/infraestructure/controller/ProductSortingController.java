package net.retail.productsorting.infraestructure.controller;

import lombok.RequiredArgsConstructor;
import net.retail.productsorting.application.ProductSortingAlgorithm;
import net.retail.productsorting.infraestructure.controller.dto.ProductResponse;
import net.retail.productsorting.infraestructure.controller.mapper.ControllerSortedRecordMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductSortingController {

	private final ProductSortingAlgorithm productSortingAlgorithm;

	private final ControllerSortedRecordMapper sortedRecordMapper;

	@GetMapping
	public ResponseEntity<List<ProductResponse>> get() {
		var sortedRecords = productSortingAlgorithm.sortProducts();
		var sortedProducts = sortedRecordMapper.toProductResponse(sortedRecords);
		return ResponseEntity.ok().body(sortedProducts);
	}
}
