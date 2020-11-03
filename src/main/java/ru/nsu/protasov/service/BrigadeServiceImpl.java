package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.Brigade;
import ru.nsu.protasov.repository.BrigadeRepository;

@Service
public class BrigadeServiceImpl extends AbstractService<Brigade, BrigadeRepository> {
    public BrigadeServiceImpl(BrigadeRepository repository) {
        super(repository);
    }
}
