package weiz.code.Universidad.infraestructure.services;


import weiz.code.Universidad.api.models.requests.EstudianteRequest;
import weiz.code.Universidad.api.models.responses.EstudianteResponse;
import weiz.code.Universidad.infraestructure.abstractService.CatalogService;
import weiz.code.Universidad.infraestructure.abstractService.CrudService;

public interface IEstudianteService extends CrudService<EstudianteRequest, EstudianteResponse, Integer>, CatalogService<EstudianteResponse> {
}
