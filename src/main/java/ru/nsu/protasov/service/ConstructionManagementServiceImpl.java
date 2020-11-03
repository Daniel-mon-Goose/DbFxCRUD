package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.ConstructionManagement;
import ru.nsu.protasov.repository.ConstructionManagementRepository;

@Service
public class ConstructionManagementServiceImpl extends AbstractService<ConstructionManagement,
        ConstructionManagementRepository> {
    public ConstructionManagementServiceImpl(ConstructionManagementRepository repository) {
        super(repository);
    }
}
