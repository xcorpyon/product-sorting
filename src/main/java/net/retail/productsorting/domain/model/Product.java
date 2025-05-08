package net.retail.productsorting.domain.model;

public record Product (
		int id,
		String name,
		int sales,
		Stock stock
) {}
