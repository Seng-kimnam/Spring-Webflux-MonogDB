package _skn.electricshopapi.service.country;

import _skn.electricshopapi.model.Entity.Country;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CountryService {

    Flux<Country> fetchAllCountries();
    Mono<Country> createAvailableCountry(Country country);
}
