package br.com.banco.infra.handler.exception;

public class RecordNotFoundException extends  BusinessException{

    public RecordNotFoundException(String entidade, String campo) {
        super("Não existe um(a) %s com o %s informado", "100", "O registro deve existir previamente",entidade,campo);
    }

}
