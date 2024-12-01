package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import ru.antoshkaxxr.JavaNaumenProject.Services.BaseProductServiceImpl;
import ru.antoshkaxxr.JavaNaumenProject.Services.ProductServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/products")
public class ProductSearchController {

    private final ProductServiceImpl productServiceImpl;
    private final BaseProductServiceImpl baseProductServiceImpl;

    @Autowired
    public ProductSearchController(ProductServiceImpl productServiceImpl, BaseProductServiceImpl baseProductServiceImpl) {
        this.productServiceImpl = productServiceImpl;
        this.baseProductServiceImpl = baseProductServiceImpl;
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String query, Principal principal) {
        List<Product> userProducts = productServiceImpl.findAllProducts(principal.getName())
                .stream()
                .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                .toList();

        List<Product> baseProducts = baseProductServiceImpl.getAllBaseProducts()
                .stream()
                .filter(baseProduct -> baseProduct.getName().toLowerCase().contains(query.toLowerCase()))
                .toList();

        return Stream.concat(userProducts.stream(), baseProducts.stream())
                .collect(Collectors.toList());
    }
}
