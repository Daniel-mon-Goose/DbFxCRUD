package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.BrigadeObjectWork;
import ru.nsu.protasov.repository.BrigadeObjectWorkRepository;

@Service
public class BrigadeObjectWorkServiceImpl extends AbstractService<BrigadeObjectWork, BrigadeObjectWorkRepository> {
    public BrigadeObjectWorkServiceImpl(BrigadeObjectWorkRepository repository) {
        super(repository);
    }
}