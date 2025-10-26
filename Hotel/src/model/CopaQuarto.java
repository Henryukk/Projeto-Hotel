/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author aluno
 */
public class CopaQuarto {
    private int id;
    private float quantidade;
    private String dataHoraPedido;
    private String obs;
    private char status;
    private CheckQuarto checkQuarto;
    
    public CopaQuarto() {
    }

    public CopaQuarto(int id, float quantidade, String dataHoraPedido, String obs, char status, CheckQuarto checkQuarto) {
        this.id = id;
        this.quantidade = quantidade;
        this.dataHoraPedido = dataHoraPedido;
        this.obs = obs;
        this.checkQuarto= checkQuarto;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public String getDataHoraPedido() {
        return dataHoraPedido;
    }

    public void setDataHoraPedido(String dataHoraPedido) {
        this.dataHoraPedido = dataHoraPedido;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public CheckQuarto getCheckQuarto() {
        return checkQuarto;
    }

    public void setCheckQuarto(CheckQuarto checkQuarto) {
        this.checkQuarto = checkQuarto;
    }
    

    @Override
    public String toString() {
        return "id = " + id + 
                "\nquantidade = " + quantidade + 
                "\ndataHoraPedido = " + dataHoraPedido + 
                "\nobs = " + obs + 
                "\nstatus = " + status;
    }
    
    
}
