package net.retail.productsorting.infraestructure.controller.mapper;

import net.retail.productsorting.domain.model.Product;
import net.retail.productsorting.domain.model.sorting.SortedRecord;
import net.retail.productsorting.infraestructure.controller.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ControllerSortedRecordMapper {

	List<ProductResponse> toProductResponse(List<SortedRecord<Product>> sortedRecordList);

	@Mapping(target = "name", source = "data.name")
	@Mapping(target = "assignedWeights", source = "assignedWeights")
	ProductResponse toProductResponse(SortedRecord<Product> sortedRecord);
}
