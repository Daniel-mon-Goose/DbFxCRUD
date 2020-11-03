package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.Outlay;
import ru.nsu.protasov.repository.OutlayRepository;

@Service
public class OutlayServiceImpl extends AbstractService<Outlay, OutlayRepository> {
    public OutlayServiceImpl(OutlayRepository repository) {
        super(repository);
    }
}
