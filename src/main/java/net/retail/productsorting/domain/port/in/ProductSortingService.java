package net.retail.productsorting.domain.port.in;

import net.retail.productsorting.domain.model.Product;
import net.retail.productsorting.domain.model.WeighedProduct;

import java.util.List;

public interface ProductSortingService {

	List<WeighedProduct> sort(List<Product> products);
}
