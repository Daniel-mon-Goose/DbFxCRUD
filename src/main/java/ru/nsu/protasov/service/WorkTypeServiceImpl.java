package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.WorkType;
import ru.nsu.protasov.repository.WorkTypeRepository;

@Service
public class WorkTypeServiceImpl extends AbstractService<WorkType, WorkTypeRepository> {
    public WorkTypeServiceImpl(WorkTypeRepository repository) {
        super(repository);
    }
}
