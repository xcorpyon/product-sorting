package net.retail.productsorting.domain.model;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
public class Product {

	private final int id;

	private final String name;

	private final int sales;

	private final Stock stock;
}
