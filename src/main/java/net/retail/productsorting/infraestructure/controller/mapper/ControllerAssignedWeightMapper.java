package net.retail.productsorting.infraestructure.controller.mapper;

import net.retail.productsorting.domain.model.sorting.AssignedWeight;
import net.retail.productsorting.infraestructure.controller.dto.AssignedWeightResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ControllerAssignedWeightMapper {

	List<AssignedWeightResponse> toAssignedWeightResponse(List<AssignedWeight> assignedWeightList);

	AssignedWeightResponse toAssignedWeightResponse(AssignedWeight assignedWeight);
}
