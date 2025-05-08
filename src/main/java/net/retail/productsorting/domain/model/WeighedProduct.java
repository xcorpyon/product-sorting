package net.retail.productsorting.domain.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
public class WeighedProduct extends Product {

	private final List<AssignedWeight> assignedWeights;
}
