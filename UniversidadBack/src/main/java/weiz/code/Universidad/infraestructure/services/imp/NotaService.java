package weiz.code.Universidad.infraestructure.services.imp;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import weiz.code.Universidad.api.models.requests.NotaRequest;
import weiz.code.Universidad.api.models.responses.EstudianteResponse;
import weiz.code.Universidad.api.models.responses.NotaResponse;
import weiz.code.Universidad.domain.entities.NotaEntity;
import weiz.code.Universidad.domain.repositories.EstudianteRepository;
import weiz.code.Universidad.domain.repositories.NotaRepository;
import weiz.code.Universidad.domain.repositories.ProfesorRepository;
import weiz.code.Universidad.infraestructure.services.IEstudianteService;
import weiz.code.Universidad.infraestructure.services.INotaService;
import weiz.code.Universidad.util.enums.TableEnum;
import weiz.code.Universidad.util.exceptions.IdNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class NotaService implements INotaService {
    private final NotaRepository notaRepository;
    private final ProfesorRepository profesorRepository;
    private final EstudianteRepository estudianteRepository;

    @Override
    public Set<NotaResponse> readAll() {
        Set<NotaResponse> responseSet = new HashSet<>();
        notaRepository.findAll().forEach(nota -> responseSet.add(entityToResponse(nota)));
        return responseSet;
    }

    @Override
    public NotaResponse create(NotaRequest request) {
        var notaPrePersist = requestToEntity(request);
        var notaPersisted = notaRepository.save(notaPrePersist);
        return entityToResponse(notaPersisted);
    }

    @Override
    public NotaResponse read(Integer integer) {
        var nota = notaRepository.findById(integer).orElseThrow(() -> new IdNotFoundException(TableEnum.Nota.name()));
        return entityToResponse(nota);
    }

    @Override
    public NotaResponse update(NotaRequest request, Integer integer) throws InvocationTargetException, IllegalAccessException {
        var notaPrePersist = notaRepository.findById(integer).orElseThrow(() -> new IdNotFoundException(TableEnum.Nota.name()));
        notaPrePersist.merge(requestToEntity(request));
        var nota = notaRepository.save(notaPrePersist);
        return entityToResponse(nota);
    }

    @Override
    public void delete(Integer integer) {
        var nota = notaRepository.findById(integer).orElseThrow(() -> new IdNotFoundException(TableEnum.Nota.name()));

        /*se elimina el estudiante y el profesor para evitar el error de la llave foranea.
        también se puede hacer desde base de datos agregando a la llave foranea la notación ON DELETE CASCADE
        De esta manera no se eliminan los estudiantes y profesores si se elimina la nota*/
        nota.setEstudiante(null);
        nota.setProfesor(null);
        notaRepository.save(nota);

        notaRepository.delete(nota);
    }

    public NotaEntity requestToEntity(NotaRequest request) {
        NotaEntity entity = new NotaEntity();
        BeanUtils.copyProperties(request, entity);
        var profesor = profesorRepository.findById(request.getIdProfesor()).orElseThrow(() -> new IdNotFoundException(TableEnum.Profesor.name()));
        var estudiante = estudianteRepository.findById(request.getIdEstudiante()).orElseThrow(() -> new IdNotFoundException(TableEnum.Estudiante.name()));
        entity.setProfesor(profesor);
        entity.setEstudiante(estudiante);
        return entity;
    }

    public NotaResponse entityToResponse(NotaEntity entity) {
        NotaResponse res = new NotaResponse();
        BeanUtils.copyProperties(entity, res);
        res.setEstudiante(EstudianteService.entityToResponse(entity.getEstudiante()));
        res.setProfesor(ProfesorService.entityToResponse(entity.getProfesor()));
        return res;
    }
}
