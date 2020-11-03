package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.BuildingObject;
import ru.nsu.protasov.repository.BuildingObjectRepository;

@Service
public class BuildingObjectServiceImpl extends AbstractService<BuildingObject, BuildingObjectRepository> {
    public BuildingObjectServiceImpl(BuildingObjectRepository repository) {
        super(repository);
    }
}
