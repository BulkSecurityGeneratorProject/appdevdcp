package br.com.lasa.dcpdesconformidades.web.rest.vm;

import br.com.lasa.dcpdesconformidades.domain.User;

/**
 * View Model object for storing a User.
 */
public class UserVM {

    private String login;
    private String name;
    private Integer prontuario;

    public UserVM(User user) {
        this.login = user.getLogin();
        this.name = user.getName();
        this.prontuario = user.getProntuario();
    }

    public UserVM() {
        // Empty public constructor used by Jackson.
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProntuario() {
        return prontuario;
    }

    public void setProntuario(Integer prontuario) {
        this.prontuario = prontuario;
    }

    @Override
    public String toString() {
        return "UserVM{" +
            "login='" + login + '\'' +
            ", name='" + name + '\'' +
            ", prontuario='" + prontuario + '\'' +
            '}';
    }
}
