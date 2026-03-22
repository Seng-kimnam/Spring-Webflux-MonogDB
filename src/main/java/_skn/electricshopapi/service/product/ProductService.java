package _skn.electricshopapi.service.product;

import _skn.electricshopapi.model.DTO.Product.request.ProductRequest;
import _skn.electricshopapi.model.DTO.Product.response.ProductResponse;
import _skn.electricshopapi.model.Entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

     Mono<Product> fetchProductById(String id);
    Flux<ProductResponse> fetchAllProducts();
    Mono<Product> createNewProduct(Product productRequest);
    Mono<Product> modifiedExistingProductById(String id , Product product);
    Mono<Void> removeProductById(String id);
     Flux<Product> fetchProductsByName(String name);
}
