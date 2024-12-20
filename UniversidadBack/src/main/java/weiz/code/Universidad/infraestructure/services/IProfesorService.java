package weiz.code.Universidad.infraestructure.services;

import weiz.code.Universidad.api.models.requests.ProfesorRequest;
import weiz.code.Universidad.api.models.responses.ProfesorResponse;
import weiz.code.Universidad.infraestructure.abstractService.CatalogService;
import weiz.code.Universidad.infraestructure.abstractService.CrudService;

public interface IProfesorService extends CrudService<ProfesorRequest, ProfesorResponse, Integer>, CatalogService<ProfesorResponse> {
}
