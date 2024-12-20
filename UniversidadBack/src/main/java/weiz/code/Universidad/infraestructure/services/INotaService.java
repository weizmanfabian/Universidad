package weiz.code.Universidad.infraestructure.services;


import weiz.code.Universidad.api.models.requests.NotaRequest;
import weiz.code.Universidad.api.models.responses.NotaResponse;
import weiz.code.Universidad.infraestructure.abstractService.CatalogService;
import weiz.code.Universidad.infraestructure.abstractService.CrudService;

public interface INotaService extends CrudService<NotaRequest, NotaResponse, Integer>, CatalogService<NotaResponse> {
}
