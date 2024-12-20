package weiz.code.Universidad.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import weiz.code.Universidad.domain.entities.EstudianteEntity;

public interface EstudianteRepository extends CrudRepository<EstudianteEntity, Integer> {
}
