package ru.nsu.protasov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.protasov.entity.ConstructionManagement;

@Repository
public interface ConstructionManagementRepository extends CommonRepository<ConstructionManagement> {
}
