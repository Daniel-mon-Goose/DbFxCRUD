package ru.nsu.protasov.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.protasov.config.DataConfig;
import ru.nsu.protasov.entity.Customer;
import ru.nsu.protasov.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl extends AbstractService<Customer, CustomerRepository> {
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Customer> addOrEdit(Customer e) {
        return Optional.of(repository.saveAndFlush(e));
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    //@Cacheable(cacheNames = "customers")
    public Page<Customer> getAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    //@Cacheable(cacheNames = "customers")
    public Optional<Customer> findById(int id) {
        return repository.findById(id);
    }

    public Page<Customer> findByName(String name, Pageable page) {
        var spec = new SpecificationImpl<Customer>();
        spec.add(new SearchCriteria("name", name, SearchOperation.EQUAL));
        return repository.findAll(spec, page);
    }
}
