package net.retail.productsorting.infraestructure.controller.dto;

import java.util.List;

public record ProductResponse(
		String name,
		List<AssignedWeightResponse> assignedWeights
) {}
