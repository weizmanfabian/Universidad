package weiz.code.Universidad.infraestructure.services.imp;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import weiz.code.Universidad.api.models.requests.EstudianteRequest;
import weiz.code.Universidad.api.models.responses.EstudianteResponse;
import weiz.code.Universidad.domain.entities.EstudianteEntity;
import weiz.code.Universidad.domain.repositories.EstudianteRepository;
import weiz.code.Universidad.infraestructure.services.IEstudianteService;
import weiz.code.Universidad.util.enums.TableEnum;
import weiz.code.Universidad.util.exceptions.IdNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class EstudianteService implements IEstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Override
    public Set<EstudianteResponse> readAll() {
        Set<EstudianteResponse> responseSet = new HashSet<>();
        estudianteRepository.findAll().forEach(estudiante -> responseSet.add(entityToResponse(estudiante)));
        return responseSet;
    }

    @Override
    public EstudianteResponse create(EstudianteRequest request) {
        var estudianteIn = requestToEntity(request);
        var estudiante = estudianteRepository.save(estudianteIn);
        return entityToResponse(estudiante);
    }

    @Override
    public EstudianteResponse read(Integer integer) {
        var estudiante = estudianteRepository.findById(integer).orElseThrow(() -> new IdNotFoundException(TableEnum.Estudiante.name()));
        return entityToResponse(estudiante);
    }

    @Override
    public EstudianteResponse update(EstudianteRequest request, Integer integer) throws InvocationTargetException, IllegalAccessException {
        log.info("update " + request.toString());
        var estudiantePrePersist = estudianteRepository.findById(integer).orElseThrow(() -> new IdNotFoundException(TableEnum.Estudiante.name()));
        estudiantePrePersist.merge(requestToEntity(request));
        var estudiante = estudianteRepository.save(estudiantePrePersist);
        return entityToResponse(estudiante);
    }

    @Override
    public void delete(Integer integer) {
        var estudiante = estudianteRepository.findById(integer).orElseThrow(() -> new IdNotFoundException(TableEnum.Estudiante.name()));
        estudianteRepository.delete(estudiante);
    }

    public static EstudianteEntity requestToEntity(EstudianteRequest request) {
        EstudianteEntity estudiante = new EstudianteEntity();
        BeanUtils.copyProperties(request, estudiante);
        return estudiante;
    }

    public static EstudianteResponse entityToResponse(EstudianteEntity entity) {
        EstudianteResponse res = new EstudianteResponse();
        BeanUtils.copyProperties(entity, res);
        return res;
    }
}
