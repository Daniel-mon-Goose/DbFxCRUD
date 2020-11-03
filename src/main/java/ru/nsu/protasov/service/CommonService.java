package ru.nsu.protasov.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.nsu.protasov.entity.Identifiable;

import java.util.List;
import java.util.Optional;

public interface CommonService<E extends Identifiable> {
    void delete(int id);
    Optional<E> addOrEdit(E e);
    Optional<E> findById(int id);
    Page<E> getAll(Pageable page);
}
