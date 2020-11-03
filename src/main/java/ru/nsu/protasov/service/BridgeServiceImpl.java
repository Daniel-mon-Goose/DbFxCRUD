package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.Bridge;
import ru.nsu.protasov.repository.BridgeRepository;

@Service
public class BridgeServiceImpl extends AbstractService<Bridge, BridgeRepository> {
    public BridgeServiceImpl(BridgeRepository repository) {
        super(repository);
    }
}