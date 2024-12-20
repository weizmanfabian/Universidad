package weiz.code.Universidad.infraestructure.services.imp;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import weiz.code.Universidad.api.models.requests.ProfesorRequest;
import weiz.code.Universidad.api.models.responses.ProfesorResponse;
import weiz.code.Universidad.domain.entities.ProfesorEntity;
import weiz.code.Universidad.domain.repositories.ProfesorRepository;
import weiz.code.Universidad.infraestructure.services.IProfesorService;
import weiz.code.Universidad.util.enums.TableEnum;
import weiz.code.Universidad.util.exceptions.IdNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class ProfesorService implements IProfesorService {

    private final ProfesorRepository profesorRepository;

    @Override
    public Set<ProfesorResponse> readAll() {
        Set<ProfesorResponse> responseSet = new HashSet<>();
        profesorRepository.findAll().forEach(profesor -> responseSet.add(entityToResponse(profesor)));
        return responseSet;
    }

    @Override
    public ProfesorResponse create(ProfesorRequest request) {
        var profesorIn = requestToEntity(request);
        var profesor = profesorRepository.save(profesorIn);
        return entityToResponse(profesor);
    }

    @Override
    public ProfesorResponse read(Integer id) {
        var profesor = profesorRepository.findById(id).orElseThrow(() -> new IdNotFoundException(TableEnum.Profesor.name()));
        return entityToResponse(profesor);
    }

    @Override
    public ProfesorResponse update(ProfesorRequest request, Integer id) throws InvocationTargetException, IllegalAccessException {
        var profesorPrePersist = profesorRepository.findById(id).orElseThrow(() -> new IdNotFoundException(TableEnum.Profesor.name()));
        profesorPrePersist.merge(requestToEntity(request));
        var profesor = profesorRepository.save(profesorPrePersist);
        return entityToResponse(profesor);
    }

    @Override
    public void delete(Integer id) {
        var profesor = profesorRepository.findById(id).orElseThrow(() -> new IdNotFoundException(TableEnum.Profesor.name()));
        profesorRepository.delete(profesor);
    }

    public static ProfesorEntity requestToEntity(ProfesorRequest request) {
        ProfesorEntity profesor = new ProfesorEntity();
        BeanUtils.copyProperties(request, profesor);
        return profesor;
    }

    public static ProfesorResponse entityToResponse(ProfesorEntity entity) {
        ProfesorResponse res = new ProfesorResponse();
        BeanUtils.copyProperties(entity, res);
        return res;
    }
}
