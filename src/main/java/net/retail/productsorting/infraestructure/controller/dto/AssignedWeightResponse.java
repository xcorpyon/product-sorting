package net.retail.productsorting.infraestructure.controller.dto;

public record AssignedWeightResponse(
	int value,
	String sortingCriteria
) {}
