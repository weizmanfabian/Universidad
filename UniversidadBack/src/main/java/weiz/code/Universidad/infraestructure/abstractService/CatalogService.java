package weiz.code.Universidad.infraestructure.abstractService;

import java.util.Set;

public interface CatalogService<Res> {
    Set<Res> readAll();
}
