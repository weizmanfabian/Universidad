package weiz.code.Universidad.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(exclude = "nota")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "estudiantes")
public class EstudianteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "est_id_estudiante")
    private Integer id;

    @Column(name = "est_nombre", nullable = false)
    private String nombre;

    public void merge(EstudianteEntity estudianteEntity) {
        if (estudianteEntity.getNombre() != null) this.setNombre(estudianteEntity.getNombre());
    }
}
