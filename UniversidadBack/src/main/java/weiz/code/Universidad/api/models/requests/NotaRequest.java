package weiz.code.Universidad.api.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaRequest {
    @NotNull(message = "El profesor es obligatorio")
    private Integer idProfesor;
    @NotNull(message = "El estudiante es obligatorio")
    private Integer idEstudiante;
    @NotNull(message = "El valor es obligatorio")
    private Double valor;
}
