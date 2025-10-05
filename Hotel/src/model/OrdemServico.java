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
public class OrdemServico {
    private int id;
    private String dataHoraCadastro;
    private String dataPrevisaoInicio;
    private String dataHoraPrevisaoTermino;
    private String obs;
    private char status;
    private int checkId;
    private int servicoId;
    private int quartoID;

    public OrdemServico() {
    }

    public OrdemServico(int id, String dataHoraCadastro, String dataPrevisaoInicio, String dataHoraPrevisaoTermino, String obs, char status) {
        this.id = id;
        this.dataHoraCadastro = dataHoraCadastro;
        this.dataPrevisaoInicio = dataPrevisaoInicio;
        this.dataHoraPrevisaoTermino = dataHoraPrevisaoTermino;
        this.obs = obs;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(String dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public String getDataPrevisaoInicio() {
        return dataPrevisaoInicio;
    }

    public void setDataPrevisaoInicio(String dataPrevisaoInicio) {
        this.dataPrevisaoInicio = dataPrevisaoInicio;
    }

    public String getDataHoraPrevisaoTermino() {
        return dataHoraPrevisaoTermino;
    }

    public void setDataHoraPrevisaoTermino(String dataHoraPrevisaoTermino) {
        this.dataHoraPrevisaoTermino = dataHoraPrevisaoTermino;
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

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public int getServicoId() {
        return servicoId;
    }

    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }

    public int getQuartoID() {
        return quartoID;
    }

    public void setQuartoID(int quartoID) {
        this.quartoID = quartoID;
    }
    

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "id = " + id + 
                "\ndataHoraCadastro = " + dataHoraCadastro + 
                "\ndataPrevisaoInicio = " + dataPrevisaoInicio + 
                "\ndataHoraPrevisaoTermino = " + dataHoraPrevisaoTermino + 
                "\nobs = " + obs + 
                "\nstatus = " + status;
    }
    
    
}
