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
import model.VagaEstacionamento;
import view.TelaBuscaVagaEstacionamento;

/**
 *   
 * @author aluno
 */
public class ControllerBuscaVagaEstacionamento implements ActionListener {

    TelaBuscaVagaEstacionamento telaBuscaVagaEstacionamento;

    public ControllerBuscaVagaEstacionamento(TelaBuscaVagaEstacionamento telaBuscaVagaEstacionamento) {
        this.telaBuscaVagaEstacionamento = telaBuscaVagaEstacionamento;

        this.telaBuscaVagaEstacionamento.getjButtonCarregar().addActionListener(this);
        this.telaBuscaVagaEstacionamento.getjButtonFiltrar().addActionListener(this);
        this.telaBuscaVagaEstacionamento.getjButtonSair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBuscaVagaEstacionamento.getjButtonCarregar()) {

            if (this.telaBuscaVagaEstacionamento.getjTableColunas().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Errou. \nNão Existe Dados Selecionados");
            } else {
                
                ControllerCadVagaEstacionamento.codigo = (int) this.telaBuscaVagaEstacionamento.getjTableColunas().getValueAt(this.telaBuscaVagaEstacionamento.getjTableColunas().getSelectedRow(), 0);
                
                this.telaBuscaVagaEstacionamento.dispose();
                
                
            }

        } else if (evento.getSource() == this.telaBuscaVagaEstacionamento.getjButtonFiltrar()) {

            if (this.telaBuscaVagaEstacionamento.getjTextFieldValor().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                if (this.telaBuscaVagaEstacionamento.getjComboBoxFiltrarPor().getSelectedIndex() == 0) {

                    //criando objeto para receber o dado que vira do banco de dados
                    VagaEstacionamento vagaEstacionamento = new VagaEstacionamento();

                    //carregando o registro do vagaEstacionamento na entidade para o objeto vagaEstacionamento
                    vagaEstacionamento = service.VagaEstacionamentoService.Carregar(Integer.parseInt(this.telaBuscaVagaEstacionamento.getjTextFieldValor().getText()));

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaVagaEstacionamento.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    tabela.addRow(new Object[]{vagaEstacionamento.getId(), vagaEstacionamento.getDescricao(), vagaEstacionamento.getMetragemVaga(), vagaEstacionamento.getStatus()});

                } else if (this.telaBuscaVagaEstacionamento.getjComboBoxFiltrarPor().getSelectedIndex() == 1) {

                    List<VagaEstacionamento> listaVagaEstacionamentos = new ArrayList<>();


                    //carregando o registro do vagaEstacionamento na entidade para o objeto vagaEstacionamento
                    listaVagaEstacionamentos = service.VagaEstacionamentoService.Carregar("descricao", this.telaBuscaVagaEstacionamento.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaVagaEstacionamento.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (VagaEstacionamento listaVagaEstacionamento : listaVagaEstacionamentos) {
                        tabela.addRow(new Object[]{listaVagaEstacionamento.getId(),
                            listaVagaEstacionamento.getDescricao(),
                            listaVagaEstacionamento.getMetragemVaga(),
                            listaVagaEstacionamento.getStatus()});

                    }

                } else if (this.telaBuscaVagaEstacionamento.getjComboBoxFiltrarPor().getSelectedIndex() == 2) {

                    List<VagaEstacionamento> listaVagaEstacionamentos = new ArrayList<>();

                    //carregando o registro do vagaEstacionamento na entidade para o objeto vagaEstacionamento
                    listaVagaEstacionamentos = service.VagaEstacionamentoService.Carregar("metragem_vaga", this.telaBuscaVagaEstacionamento.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaVagaEstacionamento.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (VagaEstacionamento listaVagaEstacionamento : listaVagaEstacionamentos) {
                        tabela.addRow(new Object[]{listaVagaEstacionamento.getId(),
                            listaVagaEstacionamento.getDescricao(),
                            listaVagaEstacionamento.getMetragemVaga(),
                            listaVagaEstacionamento.getStatus()});

                    }
                }else if (this.telaBuscaVagaEstacionamento.getjComboBoxFiltrarPor().getSelectedIndex() == 3) {

                    List<VagaEstacionamento> listaVagaEstacionamentos = new ArrayList<>();

                    //carregando o registro do vagaEstacionamento na entidade para o objeto vagaEstacionamento
                    listaVagaEstacionamentos = service.VagaEstacionamentoService.Carregar("status", this.telaBuscaVagaEstacionamento.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaVagaEstacionamento.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (VagaEstacionamento listaVagaEstacionamento : listaVagaEstacionamentos) {
                        tabela.addRow(new Object[]{listaVagaEstacionamento.getId(),
                            listaVagaEstacionamento.getDescricao(),
                            listaVagaEstacionamento.getMetragemVaga(),
                            listaVagaEstacionamento.getStatus()});

                    }
                }
            }
        } else if (evento.getSource() == this.telaBuscaVagaEstacionamento.getjButtonSair()) {
            this.telaBuscaVagaEstacionamento.dispose();

        }
    }

}
