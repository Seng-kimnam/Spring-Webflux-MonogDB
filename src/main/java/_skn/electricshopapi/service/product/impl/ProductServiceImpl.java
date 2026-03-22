package _skn.electricshopapi.service.product.impl;

import _skn.electricshopapi.exception.NotFoundException;
import _skn.electricshopapi.model.DTO.Product.request.ProductRequest;
import _skn.electricshopapi.model.DTO.Product.response.ProductResponse;
import _skn.electricshopapi.model.Entity.Product;
import _skn.electricshopapi.repository.CountryRepository;
import _skn.electricshopapi.repository.ProductRepository;
import _skn.electricshopapi.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;
    private final CountryRepository countryRepo;

    @Override
    public Mono<Product> fetchProductById(String id) {
        Mono<Product> productMono = productRepo.findById(id);
        if( productMono == null){
            throw new NotFoundException("Product with id " + id + " not found.");
        }
        return productMono ;
    }

    @Override
    public Flux<ProductResponse> fetchAllProducts() {
        return productRepo.findAll()
                .flatMap(product ->
                    countryRepo.findAllById(product.getCountryIds())
                            .collectList()
                            .map(
                                    countries ->
                                        new ProductResponse(
                                                product.getProductId(),
                                                product.getProductName(),
                                                product.getPrice(),
                                                countries
                                        )
                            )

                );
    }

    @Override
    public Mono<Product> createNewProduct(Product productRequest) {

        Mono<Product> newProduct = countryRepo.findAllById(productRequest.getCountryIds())
                .collectList()
                .flatMap(
                        countries -> {
                            if (countries.size() != productRequest.getCountryIds().size()) {
                                return Mono.error(new RuntimeException("Invalid country IDs"));
                            }
                            return productRepo.save(productRequest);

                        }
                );
        return newProduct ;
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
