package net.retail.productsorting.infraestructure;

import net.retail.productsorting.domain.DomainSortingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

	@Bean
	DomainSortingService<?> domainSortingService() {
		return new DomainSortingService<>();
	}
}
