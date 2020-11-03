package ru.nsu.protasov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.protasov.entity.Machinery;

@Repository
public interface MachineryRepository extends CommonRepository<Machinery> {
}
