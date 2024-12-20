package weiz.code.Universidad.api.controllers;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weiz.code.Universidad.api.models.requests.NotaRequest;
import weiz.code.Universidad.api.models.responses.EstudianteResponse;
import weiz.code.Universidad.api.models.responses.NotaResponse;
import weiz.code.Universidad.infraestructure.services.INotaService;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@RestController
@RequestMapping("/notas")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotaController {

    private final INotaService notaService;

    @GetMapping
    public ResponseEntity<Set<NotaResponse>> readAll() {
        Set<NotaResponse> response = notaService.readAll();
        return response.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaResponse> get(@PathVariable Integer id) {
        return ResponseEntity.ok(notaService.read(id));
    }

    @PostMapping
    public ResponseEntity<NotaResponse> post(@Valid @RequestBody NotaRequest request) {
        return ResponseEntity.ok(notaService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaResponse> put(@Valid @RequestBody NotaRequest request, @PathVariable Integer id) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.ok(notaService.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        notaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

