package ru.nsu.protasov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.protasov.entity.OutlayExceedance;

@Repository
public interface OutlayExceedanceRepository extends CommonRepository<OutlayExceedance> {
}
