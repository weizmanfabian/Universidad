package weiz.code.Universidad.api.controllers.error_handler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import weiz.code.Universidad.api.models.responses.errors.BaseErrorResponse;
import weiz.code.Universidad.api.models.responses.errors.ErrorResponse;
import weiz.code.Universidad.api.models.responses.errors.ErrorsResponse;
import weiz.code.Universidad.util.exceptions.IdNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//status code 400

/**
 * Controlador de asesoramiento para manejar excepciones y devolver respuestas de error personalizadas.
 * Este controlador se centra en las situaciones donde se requiere devolver un estado HTTP 400 BAD_REQUEST.
 * Se utiliza para indicar que la solicitud enviada por el cliente (por ejemplo, un navegador o una aplicación cliente) no pudo ser entendida por el servidor debido a una sintaxis incorrecta.
 */
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {

    @ExceptionHandler(IdNotFoundException.class)
    public BaseErrorResponse handleIdNotFoud(IdNotFoundException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }


    /**
     * Maneja la excepción MethodArgumentNotValidException para errores de validación de campos en solicitudes.
     *
     * @param exception La excepción de argumento de método no válido capturada.
     * @return Una respuesta de error con detalles de campos específicos que fallaron en la validación.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        // Agrega cada error de validación al mapa de errores.
        exception.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return ErrorsResponse.builder()
                .errors(errors)
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }


}