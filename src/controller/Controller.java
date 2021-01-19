/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.User;

/**
 *
 * @author Fabien
 */
public class Controller implements ControllerInterface {

    public User usr = null;

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public Controller() {
    }

    public void checkInt() {

        while (!sc.hasNextInt()) {
            vueAcceuil.errorInput();
            sc.nextLine();
        }
    }

    public void retourMenuAdmin(int input, User user) {

        if (input == 0) {
            controllerAdmin.adminChoices(user);
        }
    }
}
