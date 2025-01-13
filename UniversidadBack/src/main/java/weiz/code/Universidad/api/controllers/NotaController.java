package weiz.code.Universidad.api.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weiz.code.Universidad.api.models.requests.NotaRequest;
import weiz.code.Universidad.api.models.responses.NotaResponse;
import weiz.code.Universidad.api.models.responses.errors.ErrorsResponse;
import weiz.code.Universidad.infraestructure.services.INotaService;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@RestController
@RequestMapping("/notas")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Nota", description = "Controlador de notas")
public class NotaController {

    private final INotaService notaService;


    @Operation(summary = "Obtiene todas las notas")
    @GetMapping
    public ResponseEntity<Set<NotaResponse>> readAll() {
        Set<NotaResponse> response = notaService.readAll();
        return response.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtiene una nota con el id pasado como parámetro")
    @GetMapping("/{id}")
    public ResponseEntity<NotaResponse> get(@PathVariable Integer id) {
        return ResponseEntity.ok(notaService.read(id));
    }

    @ApiResponse(responseCode = "404",
            description = "Si el body de la request no cumple con las restricciones de validación, "
                    + "responde con un estado HTTP 404 y un json con los detalles de los errores.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class))
            }
    )
    @Operation(summary = "Crea una nueva nota")
    @PostMapping
    public ResponseEntity<NotaResponse> post(@Valid @RequestBody NotaRequest request) {
        return ResponseEntity.ok(notaService.create(request));
    }

    @ApiResponse(responseCode = "404",
            description = "Si el body de la request no cumple con las restricciones de validación, "
                    + "responde con un estado HTTP 404 y un json con los detalles de los errores.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class))
            }
    )
    @Operation(summary = "Actualiza una nota con el id pasado como parámetro")
    @PutMapping("/{id}")
    public ResponseEntity<NotaResponse> put(@Valid @RequestBody NotaRequest request, @PathVariable Integer id) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.ok(notaService.update(request, id));
    }

    @Operation(summary = "Elimina una nota con el id pasado como parámetro")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        notaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

