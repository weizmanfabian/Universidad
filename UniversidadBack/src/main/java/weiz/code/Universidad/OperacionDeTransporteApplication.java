package weiz.code.Universidad;

import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import weiz.code.Universidad.domain.repositories.EstudianteRepository;
import weiz.code.Universidad.util.enums.TableEnum;
import weiz.code.Universidad.util.exceptions.IdNotFoundException;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class OperacionDeTransporteApplication implements CommandLineRunner {

    private final EstudianteRepository estudianteRepository;

    public static void main(String[] args) {
        SpringApplication.run(OperacionDeTransporteApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //var estudiante = estudianteRepository.findById(1).orElseThrow(() -> new IdNotFoundException(TableEnum.Estudiante.name()));
        //System.out.println(estudiante);
    }
}
