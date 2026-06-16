package com.hector.papeleriasaas.auth;

public class Login {

    private static Login instance;

    private Login() {

    }

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }

        return instance;
    }

}
