package _skn.electricshopapi.service;

import _skn.electricshopapi.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

     Mono<Product> fetchProductById(String id);
    Flux<Product> fetchAllProducts();
    Mono<Product> createNewProduct(Product product);
    Mono<Product> modifiedExistingProductById(String id , Product product);
    Mono<Void> removeProductById(String id);
     Flux<Product> fetchProductsByName(String name);
}
