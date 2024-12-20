package weiz.code.Universidad.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weiz.code.Universidad.api.models.requests.EstudianteRequest;
import weiz.code.Universidad.api.models.requests.ProfesorRequest;
import weiz.code.Universidad.api.models.responses.EstudianteResponse;
import weiz.code.Universidad.api.models.responses.ProfesorResponse;
import weiz.code.Universidad.infraestructure.services.IEstudianteService;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@RestController
@RequestMapping("/estudiantes")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EstudianteController {

    private final IEstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<Set<EstudianteResponse>> readAll() {
        Set<EstudianteResponse> response = estudianteService.readAll();
        return response.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(response);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<EstudianteResponse> get(@PathVariable Integer id) {
        return ResponseEntity.ok(estudianteService.read(id));
    }

    @PostMapping
    public ResponseEntity<EstudianteResponse> post(@Valid @RequestBody EstudianteRequest request) {
        return ResponseEntity.ok(estudianteService.create(request));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EstudianteResponse> put(@Valid @RequestBody EstudianteRequest request, @PathVariable Integer id) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.ok(estudianteService.update(request, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        estudianteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
