/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Facade;
import java.util.Scanner;
import view.VueAcceuil;
import view.VueAdmin;
import view.VueFormateur;
import view.VueFormation;
import view.VueSession;
import view.VueStagiaire;

public interface ControllerInterface {
    
    
                        //SCANNER
    
    

    public Scanner sc = new Scanner(System.in);
    
    
                       //FACADE
    
    
    public Facade facade = new Facade();
    
    
                        //CONTROLLER
    
    
    public Controller controller = new Controller();
    public ControllerAcceuil controllerAcceuil = new ControllerAcceuil();
    public ControllerStagiaire controllerStagiaire = new ControllerStagiaire();
    public ControllerAdmin controllerAdmin = new ControllerAdmin();
    public controllerFormateur controllerFormateur = new controllerFormateur();
    
                        //VUE
    
    public VueSession vueSession = new VueSession();
    public VueAcceuil vueAcceuil = new VueAcceuil();
    public VueAdmin vueAdmin = new VueAdmin();
    public VueFormation vueFormation = new VueFormation();
    public VueFormateur vueFormateur = new VueFormateur();
    public VueStagiaire vueStagiaire = new VueStagiaire();

}
