package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.Material;
import ru.nsu.protasov.repository.MaterialRepository;

@Service
public class MaterialServiceImpl extends AbstractService<Material, MaterialRepository> {
    public MaterialServiceImpl(MaterialRepository repository) {
        super(repository);
    }
}
