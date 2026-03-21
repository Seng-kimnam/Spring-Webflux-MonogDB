package _skn.electricshopapi.service.impl;

import _skn.electricshopapi.exception.NotFoundException;
import _skn.electricshopapi.model.Product;
import _skn.electricshopapi.repository.ProductRepository;
import _skn.electricshopapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;

    @Override
    public Mono<Product> fetchProductById(String id) {
        Mono<Product> productMono = productRepo.findById(id);
        if( productMono == null){
            throw new NotFoundException("Product with id " + id + " not found.");
        }
        return productMono ;
    }

    @Override
    public Flux<Product> fetchAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Mono<Product> createNewProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Mono<Product> modifiedExistingProductById(String id, Product product) {
        Mono<Product> newProduct = productRepo.findById(id).flatMap(
                exist -> {
                    exist.setProductName(product.getProductName());
                    exist.setPrice(product.getPrice());
                    return productRepo.save(exist);
                }
        );
        return newProduct;
    }

    @Override
    public Mono<Void> removeProductById(String id) {

        if(id == null){
            throw new NotFoundException("Product with id " + id + " not found.");
        }
        productRepo.deleteById(id);
        return null;
    }

    @Override
    public Flux<Product> fetchProductsByName(String name) {
        return productRepo.findByProductNameContaining(name);
    }
}
