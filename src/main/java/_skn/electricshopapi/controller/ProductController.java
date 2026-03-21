package _skn.electricshopapi.controller;

import _skn.electricshopapi.model.ApiResponse;
import _skn.electricshopapi.model.Product;
import _skn.electricshopapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    //  GET ALL PRODUCTS
    @GetMapping
    public Mono<ApiResponse<List<Product>>> getAllProducts() {
        return productService.fetchAllProducts()
                .collectList()
                .map(products -> ApiResponse.success(
                        "Get all products successfully.",
                        products
                ));
    }

    //  GET PRODUCT BY ID
    @GetMapping("/{productId}")
    public Mono<ApiResponse<Product>> getProductById(@PathVariable String productId) {
        return productService.fetchProductById(productId)
                .map(product -> ApiResponse.success(
                        "Get product with id " + productId + " successfully.",
                        product
                ));
    }

    //  SEARCH PRODUCTS BY NAME
    @GetMapping("/search")
    public Mono<ApiResponse<List<Product>>> getProductsByName(@RequestParam String name) {
        return productService.fetchProductsByName(name)
                .collectList()
                .map(products -> ApiResponse.success(
                        "Get products with name '" + name + "' successfully.",
                        products
                ));
    }

    // CREATE PRODUCT
    @PostMapping
    public Mono<ApiResponse<Product>> createProduct(@RequestBody Product product) {
        return productService.createNewProduct(product)
                .map(saved -> ApiResponse.success(
                        "Create new product successfully.",
                        saved
                ));
    }

    //  UPDATE PRODUCT
    @PutMapping("/{productId}")
    public Mono<ApiResponse<Product>> updateProduct(@PathVariable String productId,
                                                    @RequestBody Product product) {
        return productService.modifiedExistingProductById(productId, product)
                .map(updated -> ApiResponse.success(
                        "Update product id " + productId + " successfully.",
                        updated
                ));
    }

    //  DELETE PRODUCT
    @DeleteMapping("/{productId}")
    public Mono<ApiResponse<Void>> deleteProduct(@PathVariable String productId) {
        return productService.removeProductById(productId)
                .then(Mono.just(ApiResponse.success(
                        "Delete product id " + productId + " successfully."
                )));
    }
}