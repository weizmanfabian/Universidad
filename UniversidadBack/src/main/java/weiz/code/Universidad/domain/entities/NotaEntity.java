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
@Table(name = "notas")
public class NotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "not_id_nota")
    private Integer id;

    @Column(name = "not_valor", nullable = false)
    private Double valor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "not_est_id_estudiante", referencedColumnName = "est_id_estudiante")
    private EstudianteEntity estudiante;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "not_pro_id_profesor", referencedColumnName = "pro_id_profesor")
    private ProfesorEntity profesor;

    /*@OneToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "not_pro_id_profesor", referencedColumnName = "pro_id_profesor")
    private ProfesorEntity profesor;

    @OneToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "not_est_id_estudiante", referencedColumnName = "est_id_estudiante")
    private EstudianteEntity estudiante;*/

    public void merge(NotaEntity entity) {
        if (entity.getValor() != null) this.setValor(entity.getValor());
        if (entity.getProfesor() != null) this.setProfesor(entity.getProfesor());
        if (entity.getEstudiante() != null) this.setEstudiante(entity.getEstudiante());
    }
}
