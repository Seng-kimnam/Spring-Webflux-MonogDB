package _skn.electricshopapi.model.DTO.Product.response;

import _skn.electricshopapi.model.Entity.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String id;
    private String name;
    private Double price;

    private List<Country> countries;
}
