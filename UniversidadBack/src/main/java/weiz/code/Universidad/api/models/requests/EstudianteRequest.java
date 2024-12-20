package weiz.code.Universidad.api.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
}
