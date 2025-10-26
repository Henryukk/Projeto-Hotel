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
public class CheckHospede {
    private int id;
    private String tipoHospede;
    private String obs;
    private String status;
    private Check check;
    private Hospede hospede;

    public CheckHospede() {
    }

    public CheckHospede(int id, String tipoHospede, String obs, String status, Hospede hospede, Check check) {
        this.id = id;
        this.tipoHospede = tipoHospede;
        this.obs = obs;
        this.check= check;
        this.hospede= hospede;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoHospede() {
        return tipoHospede;
    }

    public void setTipoHospede(String tipoHospede) {
        this.tipoHospede = tipoHospede;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }
    

    @Override
    public String toString() {
        return "id = " + id + 
                "\ntipoHospede = " + tipoHospede + 
                "\nobs = " + obs + 
                "\nstatus = " + status;
    }
    
    
}
