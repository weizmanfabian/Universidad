package weiz.code.Universidad.api.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorRequest {
    @NotNull(message = "El nombre es obligatorio")
    private String nombre;
}
