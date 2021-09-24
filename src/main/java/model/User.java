package model;

import java.util.Map;

public class User {

    private String nome;
    private String email;
    private String password;
    private String administrador;

    public User(String nome, String email, String password, String administrador) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.administrador = administrador;
    }

    public User(Map<String, String> rows) {
        this.nome = rows.get("nome");
        this.email = rows.get("email");
        this.password = rows.get("password");
        this.administrador = rows.get("administrador");
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAdministrador() {
        return administrador;
    }

}
