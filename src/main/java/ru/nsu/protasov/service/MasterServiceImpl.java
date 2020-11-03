package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.Master;
import ru.nsu.protasov.repository.MasterRepository;

@Service
public class MasterServiceImpl extends AbstractService<Master, MasterRepository> {
    public MasterServiceImpl(MasterRepository repository) {
        super(repository);
    }
}
