package weiz.code.Universidad.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weiz.code.Universidad.api.models.requests.ProfesorRequest;
import weiz.code.Universidad.api.models.responses.ProfesorResponse;
import weiz.code.Universidad.infraestructure.services.IProfesorService;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@RestController
@RequestMapping("/profesores")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfesorController {
    private final IProfesorService profesorService;

    @GetMapping
    public ResponseEntity<Set<ProfesorResponse>> readAll() {
        ResponseEntity.ok();
        Set<ProfesorResponse> response = profesorService.readAll();
        return response.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(response);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ProfesorResponse> get(@PathVariable Integer id) {
        return ResponseEntity.ok(profesorService.read(id));
    }

    @PostMapping
    public ResponseEntity<ProfesorResponse> post(@Valid @RequestBody ProfesorRequest request) {
        return ResponseEntity.ok(profesorService.create(request));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ProfesorResponse> put(@Valid @RequestBody ProfesorRequest request, @PathVariable Integer id) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.ok(profesorService.update(request, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        profesorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
