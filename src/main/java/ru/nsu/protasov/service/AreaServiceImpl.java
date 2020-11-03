package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.Area;
import ru.nsu.protasov.repository.AreaRepository;

@Service
public class AreaServiceImpl extends AbstractService<Area, AreaRepository> {
    public AreaServiceImpl(AreaRepository repository) {
        super(repository);
    }
}