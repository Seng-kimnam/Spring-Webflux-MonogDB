package _skn.electricshopapi.model.Entity;

import _skn.electricshopapi.model.DTO.Product.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {

    @Id

    private String productId;

    private String productName;

    private Double price;

    private List<String> countryIds;


    public Product(Object o, String name, Double price, List<String> countryIds) {
    }

//    public ProductResponse toResponse(){
//        return new ProductResponse(
//                this.productId,
//                this.productName,
//                this.price,
//                this.availableCountry
//        );
//
//    }
}
