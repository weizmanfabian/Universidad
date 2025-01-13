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
import weiz.code.Universidad.api.models.requests.EstudianteRequest;
import weiz.code.Universidad.api.models.responses.EstudianteResponse;
import weiz.code.Universidad.api.models.responses.errors.ErrorsResponse;
import weiz.code.Universidad.infraestructure.services.IEstudianteService;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@RestController
@RequestMapping("/estudiantes")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Estudiante", description = "Controlador de estudiantes")
public class EstudianteController {

    private final IEstudianteService estudianteService;


    @Operation(summary = "Obtiene todos los estudiantes")
    @GetMapping
    public ResponseEntity<Set<EstudianteResponse>> readAll() {
        Set<EstudianteResponse> response = estudianteService.readAll();
        return response.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtiene un estudiante con el id pasado como parámetro")
    @GetMapping(path = "{id}")
    public ResponseEntity<EstudianteResponse> get(@PathVariable Integer id) {
        return ResponseEntity.ok(estudianteService.read(id));
    }

    @ApiResponse(responseCode = "404",
            description = "Si el body de la request no cumple con las restricciones de validación, "
                    + "responde con un estado HTTP 404 y un json con los detalles de los errores.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class))
            }
    )
    @Operation(summary = "Crea un estudiante")
    @PostMapping
    public ResponseEntity<EstudianteResponse> post(@Valid @RequestBody EstudianteRequest request) {
        return ResponseEntity.ok(estudianteService.create(request));
    }

    @ApiResponse(responseCode = "404",
            description = "Si el body de la request no cumple con las restricciones de validación, "
                    + "responde con un estado HTTP 404 y un json con los detalles de los errores.",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class))
            }
    )
    @Operation(summary = "Actualiza un estudiante con el id pasado como parámetro")
    @PutMapping(path = "{id}")
    public ResponseEntity<EstudianteResponse> put(@Valid @RequestBody EstudianteRequest request, @PathVariable Integer id) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.ok(estudianteService.update(request, id));
    }

    @Operation(summary = "Elimina un estudiante con el id pasado como parámetro")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        estudianteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
