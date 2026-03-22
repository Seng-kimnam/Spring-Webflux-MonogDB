package _skn.electricshopapi.service.country.impl;

import _skn.electricshopapi.model.Entity.Country;
import _skn.electricshopapi.repository.CountryRepository;
import _skn.electricshopapi.service.country.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepo;

    @Override
    public Flux<Country> fetchAllCountries() {
        return countryRepo.findAll();
    }

    @Override
    public Mono<Country> createAvailableCountry(Country country) {

        return countryRepo.save(country) ;
    }
}
