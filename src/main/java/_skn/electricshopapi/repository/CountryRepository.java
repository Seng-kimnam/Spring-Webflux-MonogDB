package _skn.electricshopapi.repository;

import _skn.electricshopapi.model.Entity.Country;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends ReactiveMongoRepository<Country , String> {
}
