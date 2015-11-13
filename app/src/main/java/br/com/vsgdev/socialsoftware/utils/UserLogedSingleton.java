package br.com.vsgdev.socialsoftware.utils;


import br.com.vsgdev.socialsoftware.models.User;

public class UserLogedSingleton {

    // Variável estática que conterá a instância do método
    private static User instance;

    static {
        // Operações de inicialização da classe
    }

    // Construtor privado. Suprime o construtor público padrão
    private UserLogedSingleton() {
    }

    // Método público estático de acesso único ao objeto
    public static User getInstance() {

        if (instance == null) {
            inicializaInstancia();
            // O valor é retornado para quem está pedindo

        }
        return instance;
        // Retorna o a instância do objeto

    }

    /*
     * Este metodo é sincronizado para evitar que devido a concorrencia sejam criados mais de
     * uma instancia.
     */
    private static synchronized void inicializaInstancia() {
        if (instance == null) {
            instance = new User(0, "", "", "", null, null, null);
        }
    }

}
