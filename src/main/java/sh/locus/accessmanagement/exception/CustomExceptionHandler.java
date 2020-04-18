package sh.locus.accessmanagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sh.locus.accessmanagement.response.ResponseDTO;
import util.ResponseCode;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle the Server Exceptions
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleServerExceptions(Exception ex) {
        ResponseDTO<ErrorDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        ErrorDTO errorDTO = null;
        HttpStatus httpStatus = null;

        if (ex instanceof Exception) {
            errorDTO = new ErrorDTO(ex.getClass().getName(), ex.getMessage());
            log.error("Exception {}", ex);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        responseDTO.setPayload(errorDTO);
        return new ResponseEntity<>(responseDTO, httpStatus);

    }
}
