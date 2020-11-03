package ru.nsu.protasov.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.protasov.entity.TechnicalSpecialist;
import ru.nsu.protasov.repository.TechnicalSpecialistRepository;

@Service
public class TechnicalSpecialistServiceImpl extends AbstractService<TechnicalSpecialist, TechnicalSpecialistRepository> {
    public TechnicalSpecialistServiceImpl(TechnicalSpecialistRepository repository) {
        super(repository);
    }

    public Page<TechnicalSpecialist> findMoreExpYears(String years, Pageable page) {
        var spec = new SpecificationImpl<TechnicalSpecialist>();

        int intYears = Integer.parseInt(years);

        spec.add(new SearchCriteria("experienceYears", intYears, SearchOperation.GREATER_THAN_EQUAL));
        return repository.findAll(spec, page);
    }
}
