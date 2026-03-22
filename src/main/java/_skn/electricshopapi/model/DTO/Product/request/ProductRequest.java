package _skn.electricshopapi.model.DTO.Product.request;

import _skn.electricshopapi.model.Entity.Country;
import _skn.electricshopapi.model.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String name;
    private Double price;

    private List<String> countryIds;

    public Product toDocument(){ // toDoucment mean for MongoDb same as Entity
        return new Product(
                null,
                this.name,
                this.price,
                this.countryIds
        );
    }

}
