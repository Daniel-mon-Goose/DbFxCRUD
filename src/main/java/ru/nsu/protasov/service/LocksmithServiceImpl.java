package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.Locksmith;
import ru.nsu.protasov.repository.LocksmithRepository;

@Service
public class LocksmithServiceImpl extends AbstractService<Locksmith, LocksmithRepository> {
    public LocksmithServiceImpl(LocksmithRepository repository) {
        super(repository);
    }
}
