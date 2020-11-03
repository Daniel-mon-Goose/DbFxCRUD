package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.ScheduleDelay;
import ru.nsu.protasov.repository.ScheduleDelayRepository;

@Service
public class ScheduleDelayServiceImpl extends AbstractService<ScheduleDelay, ScheduleDelayRepository> {
    public ScheduleDelayServiceImpl(ScheduleDelayRepository repository) {
        super(repository);
    }
}
