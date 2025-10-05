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
import model.Funcionario;
import view.TelaBuscaFuncionario;

/**
 *
 * @author aluno
 */
public class ControllerBuscaFuncionario implements ActionListener {

    TelaBuscaFuncionario telaBuscaFuncionario;

    public ControllerBuscaFuncionario(TelaBuscaFuncionario telaBuscaFuncionario) {
        this.telaBuscaFuncionario = telaBuscaFuncionario;

        this.telaBuscaFuncionario.getjButtonCarregar().addActionListener(this);
        this.telaBuscaFuncionario.getjButtonFiltrar().addActionListener(this);
        this.telaBuscaFuncionario.getjButtonSair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBuscaFuncionario.getjButtonCarregar()) {

            if (this.telaBuscaFuncionario.getjTableColunas().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Errou. \nNão Existe Dados Selecionados");
            } else {
                
                ControllerCadFuncionario.codigo = (int) this.telaBuscaFuncionario.getjTableColunas().getValueAt(this.telaBuscaFuncionario.getjTableColunas().getSelectedRow(), 0);
                
                this.telaBuscaFuncionario.dispose();
                
                
            }

        } else if (evento.getSource() == this.telaBuscaFuncionario.getjButtonFiltrar()) {

            if (this.telaBuscaFuncionario.getjTextFieldValor().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                if (this.telaBuscaFuncionario.getjComboBoxFiltrarPor().getSelectedIndex() == 0) {

                    //criando objeto para receber o dado que vira do banco de dados
                    Funcionario funcionario = new Funcionario();

                    //carregando o registro do funcionario na entidade para o objeto funcionario
                    funcionario = service.FuncionarioService.Carregar(Integer.parseInt(this.telaBuscaFuncionario.getjTextFieldValor().getText()));

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFuncionario.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    tabela.addRow(new Object[]{funcionario.getId(), funcionario.getNome(), funcionario.getCpf(), funcionario.getStatus()});

                } else if (this.telaBuscaFuncionario.getjComboBoxFiltrarPor().getSelectedIndex() == 1) {

                    List<Funcionario> listaFuncionarios = new ArrayList<>();


                    //carregando o registro do funcionario na entidade para o objeto funcionario
                    listaFuncionarios = service.FuncionarioService.Carregar("NOME", this.telaBuscaFuncionario.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFuncionario.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Funcionario listaFuncionario : listaFuncionarios) {
                        tabela.addRow(new Object[]{listaFuncionario.getId(),
                            listaFuncionario.getNome(),
                            listaFuncionario.getCpf(),
                            listaFuncionario.getStatus()});

                    }

                } else if (this.telaBuscaFuncionario.getjComboBoxFiltrarPor().getSelectedIndex() == 2) {

                    List<Funcionario> listaFuncionarios = new ArrayList<>();

                    //carregando o registro do funcionario na entidade para o objeto funcionario
                    listaFuncionarios = service.FuncionarioService.Carregar("cpf", this.telaBuscaFuncionario.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFuncionario.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Funcionario listaFuncionario : listaFuncionarios) {
                        tabela.addRow(new Object[]{listaFuncionario.getId(),
                            listaFuncionario.getNome(),
                            listaFuncionario.getCpf(),
                            listaFuncionario.getStatus()});

                    }
                }
            }
        } else if (evento.getSource() == this.telaBuscaFuncionario.getjButtonSair()) {
            this.telaBuscaFuncionario.dispose();

        }
    }

}
