package ru.nsu.protasov.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.Worker;
import ru.nsu.protasov.repository.WorkerRepository;

import java.util.List;

@Service
public class WorkerServiceImpl extends AbstractService<Worker, WorkerRepository> {
    public WorkerServiceImpl(WorkerRepository repository) {
        super(repository);
    }
}
