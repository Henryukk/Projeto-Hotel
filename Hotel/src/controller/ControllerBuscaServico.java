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
import model.Servico;
import view.TelaBuscaServico;

/**
 *   
 * @author aluno
 */
public class ControllerBuscaServico implements ActionListener {

    TelaBuscaServico telaBuscaServico;

    public ControllerBuscaServico(TelaBuscaServico telaBuscaServico) {
        this.telaBuscaServico = telaBuscaServico;

        this.telaBuscaServico.getjButtonCarregar().addActionListener(this);
        this.telaBuscaServico.getjButtonFiltrar().addActionListener(this);
        this.telaBuscaServico.getjButtonSair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBuscaServico.getjButtonCarregar()) {

            if (this.telaBuscaServico.getjTableColunas().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Errou. \nNão Existe Dados Selecionados");
            } else {
                
                ControllerCadServico.codigo = (int) this.telaBuscaServico.getjTableColunas().getValueAt(this.telaBuscaServico.getjTableColunas().getSelectedRow(), 0);
                
                this.telaBuscaServico.dispose();
                
                
            }

        } else if (evento.getSource() == this.telaBuscaServico.getjButtonFiltrar()) {

            if (this.telaBuscaServico.getjTextFieldValor().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                if (this.telaBuscaServico.getjComboBoxFiltrarPor().getSelectedIndex() == 0) {

                    //criando objeto para receber o dado que vira do banco de dados
                    Servico servico = new Servico();

                    //carregando o registro do servico na entidade para o objeto servico
                    servico = service.ServicoService.Carregar(Integer.parseInt(this.telaBuscaServico.getjTextFieldValor().getText()));

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaServico.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    tabela.addRow(new Object[]{servico.getId(), servico.getDescricao(), servico.getObs(), servico.getStatus()});

                } else if (this.telaBuscaServico.getjComboBoxFiltrarPor().getSelectedIndex() == 1) {

                    List<Servico> listaServicos = new ArrayList<>();


                    //carregando o registro do servico na entidade para o objeto servico
                    listaServicos = service.ServicoService.Carregar("descricao", this.telaBuscaServico.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaServico.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Servico listaServico : listaServicos) {
                        tabela.addRow(new Object[]{listaServico.getId(),
                            listaServico.getDescricao(),
                            listaServico.getObs(),
                            listaServico.getStatus()});

                    }

                } else if (this.telaBuscaServico.getjComboBoxFiltrarPor().getSelectedIndex() == 2) {

                    List<Servico> listaServicos = new ArrayList<>();

                    //carregando o registro do servico na entidade para o objeto servico
                    listaServicos = service.ServicoService.Carregar("obs", this.telaBuscaServico.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaServico.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Servico listaServico : listaServicos) {
                        tabela.addRow(new Object[]{listaServico.getId(),
                            listaServico.getDescricao(),
                            listaServico.getObs(),
                            listaServico.getStatus()});

                    }
                }else if (this.telaBuscaServico.getjComboBoxFiltrarPor().getSelectedIndex() == 3) {

                    List<Servico> listaServicos = new ArrayList<>();

                    //carregando o registro do servico na entidade para o objeto servico
                    listaServicos = service.ServicoService.Carregar("status", this.telaBuscaServico.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaServico.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Servico listaServico : listaServicos) {
                        tabela.addRow(new Object[]{listaServico.getId(),
                            listaServico.getDescricao(),
                            listaServico.getObs(),
                            listaServico.getStatus()});

                    }
                }
            }
        } else if (evento.getSource() == this.telaBuscaServico.getjButtonSair()) {
            this.telaBuscaServico.dispose();

        }
    }

}
