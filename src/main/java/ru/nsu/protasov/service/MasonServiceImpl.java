package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.Mason;
import ru.nsu.protasov.repository.MasonRepository;

@Service
public class MasonServiceImpl extends AbstractService<Mason, MasonRepository> {
    public MasonServiceImpl(MasonRepository repository) {
        super(repository);
    }
}
