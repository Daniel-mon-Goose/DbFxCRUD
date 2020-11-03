package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.Machinery;
import ru.nsu.protasov.repository.MachineryRepository;

@Service
public class MachineryServiceImpl extends AbstractService<Machinery, MachineryRepository> {
    public MachineryServiceImpl(MachineryRepository repository) {
        super(repository);
    }
}
