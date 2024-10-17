package ru.antoshkaxxr.JavaNaumenProject;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.EatenProductRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.ProductRepository;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class EatenProductTest {
	private final EatenProductRepository eatenProductRepository;
	private final ProductRepository productRepository;

	@Autowired
    EatenProductTest(EatenProductRepository eatenProductRepository, ProductRepository productRepository) {
        this.eatenProductRepository = eatenProductRepository;
        this.productRepository = productRepository;
    }

	/**
	 * Тестирование поиска всех продуктов, съеденных в определенный день
	 */
	@Test
	@Transactional
	@Rollback
	void testFindByEatingDate() {
		Product product1 = new Product("soup", 250.0);
		Product product2 = new Product("apple", 50.0);
		Product product3 = new Product("banana", 100.0);
		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);

		EatenProduct eatenProduct1 = new EatenProduct(product1, LocalDate.of(2024, 9, 12), 1.0);
		EatenProduct eatenProduct2 = new EatenProduct(product2, LocalDate.of(2024, 10, 12), 1.0);
		EatenProduct eatenProduct3 = new EatenProduct(product3, LocalDate.of(2024, 10, 13), 1.0);
		eatenProductRepository.save(eatenProduct1);
		eatenProductRepository.save(eatenProduct2);
		eatenProductRepository.save(eatenProduct3);

		List<EatenProduct> eatenProducts = eatenProductRepository
				.findByEatingDate(LocalDate.of(2024, 10, 13));

		String firstElement = eatenProducts.getFirst().getProduct().getName();
		Assertions.assertEquals(1, eatenProducts.size());
		Assertions.assertEquals("banana", firstElement);
	}

	/**
	 * Тестирование поиска всех продуктов, съеденных за определенный промежуток дат
	 */
	@Test
	@Transactional
	@Rollback
	void testFindByEatingDateBetween() {
		Product product1 = new Product("soup", 250.0);
		Product product2 = new Product("apple", 50.0);
		Product product3 = new Product("banana", 100.0);
		Product product4 = new Product("bread", 70.0);
		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);
		productRepository.save(product4);

		EatenProduct eatenProduct1 = new EatenProduct(product1, LocalDate.of(2024, 10, 12), 1.0);
		EatenProduct eatenProduct2 = new EatenProduct(product2, LocalDate.of(2024, 10, 13), 1.0);
		EatenProduct eatenProduct3 = new EatenProduct(product3, LocalDate.of(2024, 10, 14), 1.0);
		EatenProduct eatenProduct4 = new EatenProduct(product4, LocalDate.of(2024, 10, 15), 1.0);
		eatenProductRepository.save(eatenProduct1);
		eatenProductRepository.save(eatenProduct2);
		eatenProductRepository.save(eatenProduct3);
		eatenProductRepository.save(eatenProduct4);

		List<EatenProduct> eatenProducts = eatenProductRepository
				.findByEatingDateBetween(LocalDate.of(2024, 10, 13),
						LocalDate.of(2024, 10, 14));

		String firstElement = eatenProducts.getFirst().getProduct().getName();
		String secondElement = eatenProducts.get(1).getProduct().getName();
		Assertions.assertEquals(2, eatenProducts.size());
		Assertions.assertEquals("apple", firstElement);
		Assertions.assertEquals("banana", secondElement);
	}
}
