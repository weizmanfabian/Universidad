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
import weiz.code.Universidad.api.models.requests.ProfesorRequest;
import weiz.code.Universidad.api.models.responses.ProfesorResponse;
import weiz.code.Universidad.api.models.responses.errors.ErrorsResponse;
import weiz.code.Universidad.infraestructure.services.IProfesorService;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@RestController
@RequestMapping("/profesores")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Profesor", description = "Controlador de Profesores")
public class ProfesorController {
    private final IProfesorService profesorService;


    @Operation(summary = "Obtiene todos los profesores")
    @GetMapping
    public ResponseEntity<Set<ProfesorResponse>> readAll() {
        ResponseEntity.ok();
        Set<ProfesorResponse> response = profesorService.readAll();
        return response.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtiene un profesor con el id pasado como parámetro")
    @GetMapping(path = "{id}")
    public ResponseEntity<ProfesorResponse> get(@PathVariable Integer id) {
        return ResponseEntity.ok(profesorService.read(id));
    }

    @ApiResponse(responseCode = "404",
            description = "Si el body de la request no cumple con las restricciones de validación, "
                    + "responde con un estado HTTP 404 y un json con los detalles de los errores.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class))
            }
    )
    @Operation(summary = "Crea un profesor")
    @PostMapping
    public ResponseEntity<ProfesorResponse> post(@Valid @RequestBody ProfesorRequest request) {
        return ResponseEntity.ok(profesorService.create(request));
    }

    @ApiResponse(responseCode = "404",
            description = "Si el body de la request no cumple con las restricciones de validación, "
                    + "responde con un estado HTTP 404 y un json con los detalles de los errores.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class))
            }
    )
    @Operation(summary = "Actualiza un profesor con el id pasado como parámetro")
    @PutMapping(path = "{id}")
    public ResponseEntity<ProfesorResponse> put(@Valid @RequestBody ProfesorRequest request, @PathVariable Integer id) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.ok(profesorService.update(request, id));
    }

    @Operation(summary = "Elimina un profesor con el id pasado como parámetro")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        profesorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
