package weiz.code.Universidad.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaResponse {
    private Integer id;
    private Double valor;
    private ProfesorResponse profesor;
    private EstudianteResponse estudiante;
}
