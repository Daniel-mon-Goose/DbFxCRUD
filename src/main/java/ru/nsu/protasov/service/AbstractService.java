package ru.nsu.protasov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.nsu.protasov.config.DataConfig;
import ru.nsu.protasov.entity.Identifiable;
import ru.nsu.protasov.repository.CommonRepository;

import java.util.Optional;

public abstract class AbstractService<E extends Identifiable, R extends CommonRepository<E>>
        implements CommonService<E> {
    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public Optional<E> addOrEdit(E e) {
        return Optional.of(repository.saveAndFlush(e));
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Page<E> getAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    @Cacheable(cacheNames = "customers", cacheResolver = DataConfig.CACHE_RESOLVER_NAME)
    public Optional<E> findById(int id) {
        return repository.findById(id);
    }
}
