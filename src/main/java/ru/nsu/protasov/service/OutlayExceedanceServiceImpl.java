package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.OutlayExceedance;
import ru.nsu.protasov.repository.OutlayExceedanceRepository;

@Service
public class OutlayExceedanceServiceImpl extends AbstractService<OutlayExceedance, OutlayExceedanceRepository> {
    public OutlayExceedanceServiceImpl(OutlayExceedanceRepository repository) {
        super(repository);
    }
}
