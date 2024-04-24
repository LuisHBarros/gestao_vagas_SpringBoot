package br.com.luishbarros.gestao_vagas.exception;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Usuario ja existe");
    }
}
