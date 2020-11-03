package ru.nsu.protasov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import ru.nsu.protasov.entity.Identifiable;

@NoRepositoryBean
public interface CommonRepository<E extends Identifiable> extends JpaRepository<E, Integer>,
        JpaSpecificationExecutor<E> {
}