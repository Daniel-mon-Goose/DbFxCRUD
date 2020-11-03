package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.Foreman;
import ru.nsu.protasov.repository.ForemanRepository;

@Service
public class ForemanServiceImpl extends AbstractService<Foreman, ForemanRepository> {
    public ForemanServiceImpl(ForemanRepository repository) {
        super(repository);
    }
}
