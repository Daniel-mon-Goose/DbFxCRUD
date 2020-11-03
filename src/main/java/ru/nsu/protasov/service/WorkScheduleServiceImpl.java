package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.WorkSchedule;
import ru.nsu.protasov.repository.WorkScheduleRepository;

@Service
public class WorkScheduleServiceImpl extends AbstractService<WorkSchedule, WorkScheduleRepository> {
    public WorkScheduleServiceImpl(WorkScheduleRepository repository) {
        super(repository);
    }
}
