package _skn.electricshopapi.controller;

import _skn.electricshopapi.model.ApiResponse;
import _skn.electricshopapi.model.Entity.Country;
import _skn.electricshopapi.model.Entity.Product;
import _skn.electricshopapi.service.country.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries")
public class CountryController {


    private final CountryService countryService;

    @GetMapping
    public Mono<ApiResponse<List<Country>>> getAllProducts() {
        return countryService.fetchAllCountries()
                .collectList()
                .map(countries -> ApiResponse.success(
                        "Get all countries successfully.",
                        countries
                ));
    }
    @PostMapping
    public Mono<ApiResponse<Country>> createCountry(@RequestBody Country country) {
        return countryService.createAvailableCountry(country)
                .map(saved -> ApiResponse.success(
                        "Create new country successfully.",
                        saved
                ));
    }
}
