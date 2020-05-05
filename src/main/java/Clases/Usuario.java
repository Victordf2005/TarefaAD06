/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/** Clase que conterá os datos dun usuario rexistrado
 *
 * @author Víctor Díaz
 */
public class Usuario {
    
    private String nome;
    private String usuario;
    private String contrasinal;
    private ArrayList<String> segueA;

    public Usuario() {
    }

    public Usuario(String nome, String usuario, String contrasinal, ArrayList<String> segue) {
        this.nome = nome;
        this.usuario = usuario;
        this.contrasinal = contrasinal;
        this.segueA = segue;
    }

    public String getNome() {
        return nome;
    }

    public String getUsuario() {
        return usuario;
    }
    
    public String getContrasinal() {
        return contrasinal;
    }

    public ArrayList<String> getSegueA() {
        return segueA;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasinal(String contrasinal) {
        this.contrasinal = contrasinal;
    }

    public void setSegueA(ArrayList<String> segueA) {
        this.segueA = segueA;
    }
    
    
    
}
