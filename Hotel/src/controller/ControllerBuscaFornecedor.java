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
import model.Fornecedor;
import view.TelaBuscaFornecedor;

/**
 *
 * @author aluno
 */
public class ControllerBuscaFornecedor implements ActionListener {

    TelaBuscaFornecedor telaBuscaFornecedor;

    public ControllerBuscaFornecedor(TelaBuscaFornecedor telaBuscaFornecedor) {
        this.telaBuscaFornecedor = telaBuscaFornecedor;

        this.telaBuscaFornecedor.getjButtonCarregar().addActionListener(this);
        this.telaBuscaFornecedor.getjButtonFiltrar().addActionListener(this);
        this.telaBuscaFornecedor.getjButtonSair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBuscaFornecedor.getjButtonCarregar()) {

            if (this.telaBuscaFornecedor.getjTableColunas().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Errou. \nNão Existe Dados Selecionados");
            } else {
                
                ControllerCadFornecedor.codigo = (int) this.telaBuscaFornecedor.getjTableColunas().getValueAt(this.telaBuscaFornecedor.getjTableColunas().getSelectedRow(), 0);
                
                this.telaBuscaFornecedor.dispose();
                
                
            }

        } else if (evento.getSource() == this.telaBuscaFornecedor.getjButtonFiltrar()) {

            if (this.telaBuscaFornecedor.getjTextFieldValor().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                if (this.telaBuscaFornecedor.getjComboBoxFiltrarPor().getSelectedIndex() == 0) {

                    //criando objeto para receber o dado que vira do banco de dados
                    Fornecedor fornecedor = new Fornecedor();

                    //carregando o registro do fornecedor na entidade para o objeto fornecedor
                    fornecedor = service.FornecedorService.Carregar(Integer.parseInt(this.telaBuscaFornecedor.getjTextFieldValor().getText()));

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFornecedor.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    tabela.addRow(new Object[]{fornecedor.getId(), fornecedor.getNome(), fornecedor.getCpf(), fornecedor.getStatus()});

                } else if (this.telaBuscaFornecedor.getjComboBoxFiltrarPor().getSelectedIndex() == 1) {

                    List<Fornecedor> listaFornecedores = new ArrayList<>();


                    //carregando o registro do fornecedor na entidade para o objeto fornecedor
                    listaFornecedores = service.FornecedorService.Carregar("NOME", this.telaBuscaFornecedor.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFornecedor.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Fornecedor listaFornecedor : listaFornecedores) {
                        tabela.addRow(new Object[]{listaFornecedor.getId(),
                            listaFornecedor.getNome(),
                            listaFornecedor.getCpf(),
                            listaFornecedor.getRazaoSocial(),
                            listaFornecedor.getCnpj(),
                            listaFornecedor.getStatus()});

                    }

                } else if (this.telaBuscaFornecedor.getjComboBoxFiltrarPor().getSelectedIndex() == 2) {

                    List<Fornecedor> listaFornecedores = new ArrayList<>();

                    //carregando o registro do fornecedor na entidade para o objeto fornecedor
                    listaFornecedores = service.FornecedorService.Carregar("cpf", this.telaBuscaFornecedor.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFornecedor.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Fornecedor listaFornecedor : listaFornecedores) {
                        tabela.addRow(new Object[]{listaFornecedor.getId(),
                            listaFornecedor.getNome(),
                            listaFornecedor.getCpf(),
                            listaFornecedor.getRazaoSocial(),
                            listaFornecedor.getCnpj(),
                            listaFornecedor.getStatus()});


                    }
                }else if (this.telaBuscaFornecedor.getjComboBoxFiltrarPor().getSelectedIndex() == 3) {

                    List<Fornecedor> listaFornecedores = new ArrayList<>();

                    //carregando o registro do fornecedor na entidade para o objeto fornecedor
                    listaFornecedores = service.FornecedorService.Carregar("razao_social", this.telaBuscaFornecedor.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFornecedor.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Fornecedor listaFornecedor : listaFornecedores) {
                        tabela.addRow(new Object[]{listaFornecedor.getId(),
                            listaFornecedor.getNome(),
                            listaFornecedor.getCpf(),
                            listaFornecedor.getRazaoSocial(),
                            listaFornecedor.getCnpj(),
                            listaFornecedor.getStatus()});

                    }
                } else if (this.telaBuscaFornecedor.getjComboBoxFiltrarPor().getSelectedIndex() == 4) {

                    List<Fornecedor> listaFornecedores = new ArrayList<>();

                    //carregando o registro do fornecedor na entidade para o objeto fornecedor
                    listaFornecedores = service.FornecedorService.Carregar("cnpj", this.telaBuscaFornecedor.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFornecedor.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Fornecedor listaFornecedor : listaFornecedores) {
                        tabela.addRow(new Object[]{listaFornecedor.getId(),
                            listaFornecedor.getNome(),
                            listaFornecedor.getCpf(),
                            listaFornecedor.getRazaoSocial(),
                            listaFornecedor.getCnpj(),
                            listaFornecedor.getStatus()});

                    }
                }  
                
            }
        } else if (evento.getSource() == this.telaBuscaFornecedor.getjButtonSair()) {
            this.telaBuscaFornecedor.dispose();

        }
    }

}
