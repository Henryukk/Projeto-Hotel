/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Hospede;
import view.TelaBuscasHospede;

/**
 *
 * @author aluno
 */
public class ControllerBuscaHospede implements ActionListener {

    TelaBuscasHospede telaBuscaHospede;

    public ControllerBuscaHospede(TelaBuscasHospede telaBuscaHospede) {
        this.telaBuscaHospede = telaBuscaHospede;

        this.telaBuscaHospede.getjButtonCarregar().addActionListener(this);
        this.telaBuscaHospede.getjButtonFiltrar().addActionListener(this);
        this.telaBuscaHospede.getjButtonSair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBuscaHospede.getjButtonCarregar()) {

            if (this.telaBuscaHospede.getjTableColunas().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Errou. \nNão Existe Dados Selecionados");
            } else {
                
                ControllerCadHospede.codigo = (int) this.telaBuscaHospede.getjTableColunas().getValueAt(this.telaBuscaHospede.getjTableColunas().getSelectedRow(), 0);
                
                this.telaBuscaHospede.dispose();
                
                
            }

        } else if (evento.getSource() == this.telaBuscaHospede.getjButtonFiltrar()) {

            if (this.telaBuscaHospede.getjTextFieldValor().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                if (this.telaBuscaHospede.getjComboBoxFiltrarPor().getSelectedIndex() == 0) {

                    //criando objeto para receber o dado que vira do banco de dados
                    Hospede hospede = new Hospede();

                    //carregando o registro do hospede na entidade para o objeto hospede
                    hospede = service.HospedeService.Carregar(Integer.parseInt(this.telaBuscaHospede.getjTextFieldValor().getText()));

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaHospede.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    tabela.addRow(new Object[]{hospede.getId(), hospede.getNome(), hospede.getCpf(), hospede.getStatus()});

                } else if (this.telaBuscaHospede.getjComboBoxFiltrarPor().getSelectedIndex() == 1) {

                    List<Hospede> listaHospedes = new ArrayList<>();


                    //carregando o registro do hospede na entidade para o objeto hospede
                    listaHospedes = service.HospedeService.Carregar("NOME", this.telaBuscaHospede.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaHospede.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Hospede listaHospede : listaHospedes) {
                        tabela.addRow(new Object[]{listaHospede.getId(),
                            listaHospede.getNome(),
                            listaHospede.getCpf(),
                            listaHospede.getStatus()});

                    }

                } else if (this.telaBuscaHospede.getjComboBoxFiltrarPor().getSelectedIndex() == 2) {

                    List<Hospede> listaHospedes = new ArrayList<>();

                    //carregando o registro do hospede na entidade para o objeto hospede
                    listaHospedes = service.HospedeService.Carregar("cpf", this.telaBuscaHospede.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaHospede.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Hospede listaHospede : listaHospedes) {
                        tabela.addRow(new Object[]{listaHospede.getId(),
                            listaHospede.getNome(),
                            listaHospede.getCpf(),
                            listaHospede.getStatus()});

                    }
                } else if (this.telaBuscaHospede.getjComboBoxFiltrarPor().getSelectedIndex() == 3) {

                    List<Hospede> listaHospedes = new ArrayList<>();

                    //carregando o registro do hospede na entidade para o objeto hospede
                    listaHospedes = service.HospedeService.Carregar("status", this.telaBuscaHospede.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaHospede.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Hospede listaHospede : listaHospedes) {
                        tabela.addRow(new Object[]{listaHospede.getId(),
                            listaHospede.getNome(),
                            listaHospede.getCpf(),
                            listaHospede.getStatus()});

                    }
                }
            }
        } else if (evento.getSource() == this.telaBuscaHospede.getjButtonSair()) {
            this.telaBuscaHospede.dispose();

        }
    }

}
