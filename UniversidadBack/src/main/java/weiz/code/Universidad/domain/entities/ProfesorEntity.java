package weiz.code.Universidad.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "profesores")
public class ProfesorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id_profesor")
    private Integer id;

    @Column(name = "pro_nombre", nullable = false)
    private String nombre;

    public void merge(ProfesorEntity profesor) {
        if (profesor != null) this.setNombre(profesor.getNombre());
    }

}
