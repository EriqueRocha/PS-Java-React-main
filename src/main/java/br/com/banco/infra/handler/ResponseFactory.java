package br.com.banco.infra.handler;

import br.com.banco.infra.handler.exception.BusinessException;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ResponseFactory {

    public static Response delete(Object campo, String suggestion) {
        return ok(campo,"deletado com sucesso", suggestion);
    }
    public static Response ok(Object body) {
        return ok(body,"Consulta realizada com sucesso");
    }
    public static Response ok(Object body, String message) {
        return response(HttpStatus.OK.value(), body,message, "");
    }
    public static Response ok(Object body, String message, String suggestion) {
        return response(HttpStatus.OK.value(), body,message, suggestion);
    }
    public static Response create(Object body, String message, String suggestion) {
        return response(HttpStatus.CREATED.value(), body,message, suggestion);
    }
    private static Response response(Serializable code, Object body, String message, String suggestion) {
        Response response = new Response();

        response.body = body;
        response.code =code;
        response.message =message;
        response.success = true;
        response.suggestion = suggestion;

        return response;
    }

    public static Response error() {
        return error("Error","Entre em contato com o suporte");
    }
    public static Response exception(BusinessException be) {
        return error(be.getErrorCode(), be.getMessage(),be.getSuggestion());
    }
    public static Response error(String message, String suggestion) {
        return error(500,message,suggestion);
    }
    public static Response error(Serializable code, String message, String suggestion) {
        Response response = new Response();
        response.code =code;
        response.message =message;
        response.success = false;
        response.suggestion = suggestion;
        return response;
    }

}
