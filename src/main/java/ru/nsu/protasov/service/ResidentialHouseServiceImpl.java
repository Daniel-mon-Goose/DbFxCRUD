package ru.nsu.protasov.service;

import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.ResidentialHouse;
import ru.nsu.protasov.repository.ResidentialHouseRepository;

@Service
public class ResidentialHouseServiceImpl extends AbstractService<ResidentialHouse, ResidentialHouseRepository> {
    public ResidentialHouseServiceImpl(ResidentialHouseRepository repository) {
        super(repository);
    }
}
