package ru.antoshkaxxr.JavaNaumenProject.ExceptionHandler;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Предоставляет глобальную обработку исключений для всех контроллеров в приложении.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * Обрабатывает все исключения типа java.lang.Exception.
     * Возвращает HTTP-статус 500 (Internal Server Error) и сообщение об ошибке.
     *
     * @param e Исключение типа java.lang.Exception.
     * @return Объект Exception с сообщением об ошибке.
     */
    @ExceptionHandler(java.lang.Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Exception exception(java.lang.Exception e) {
        return Exception.create(e);
    }

    /**
     * Обрабатывает исключения типа ResourceNotFoundException.
     * Возвращает HTTP-статус 404 (Not Found) и сообщение об ошибке.
     *
     * @param e Исключение типа ResourceNotFoundException.
     * @return Объект Exception с сообщением об ошибке.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Exception exception(ResourceNotFoundException e) {
        return Exception.create(e);
    }
}
