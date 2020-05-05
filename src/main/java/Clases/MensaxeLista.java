/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/** Clase cos datos deunha mensaxe necesarios para ser listados
 *
 * @author Víctor Díaz
 */
public class MensaxeLista {
    private String nomeUsuario;
    private String userUsuario;
    private String texto;
    private String data;

    public MensaxeLista() {
    }

    public MensaxeLista(String nomeUsuario, String userUsuario, String texto, String data) {
        this.nomeUsuario = nomeUsuario;
        this.userUsuario = userUsuario;
        this.texto = texto;
        this.data = data;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setUserUsuario(String userUsuario) {
        this.userUsuario = userUsuario;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getUserUsuario() {
        return userUsuario;
    }

    public String getTexto() {
        return texto;
    }

    public String getData() {
        return data;
    }
    
        
}
