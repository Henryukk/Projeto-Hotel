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
import model.ProdutoCopa;
import view.TelaBuscaProdutoCopa;

/**
 *
 * @author aluno
 */
public class ControllerBuscaProdutoCopa implements ActionListener {

    TelaBuscaProdutoCopa telaBuscaProdutoCopa;

    public ControllerBuscaProdutoCopa(TelaBuscaProdutoCopa telaBuscaProdutoCopa) {
        this.telaBuscaProdutoCopa = telaBuscaProdutoCopa;

        this.telaBuscaProdutoCopa.getjButtonCarregar().addActionListener(this);
        this.telaBuscaProdutoCopa.getjButtonFiltrar().addActionListener(this);
        this.telaBuscaProdutoCopa.getjButtonSair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBuscaProdutoCopa.getjButtonCarregar()) {

            if (this.telaBuscaProdutoCopa.getjTableColunas().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Errou. \nNão Existe Dados Selecionados");
            } else {
                
                ControllerCadProdutoCopa.codigo = (int) this.telaBuscaProdutoCopa.getjTableColunas().getValueAt(this.telaBuscaProdutoCopa.getjTableColunas().getSelectedRow(), 0);
                
                this.telaBuscaProdutoCopa.dispose();
                
                
            }

        } else if (evento.getSource() == this.telaBuscaProdutoCopa.getjButtonFiltrar()) {

            if (this.telaBuscaProdutoCopa.getjTextFieldValor().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                if (this.telaBuscaProdutoCopa.getjComboBoxFiltrarPor().getSelectedIndex() == 0) {

                    //criando objeto para receber o dado que vira do banco de dados
                    ProdutoCopa produtoCopa = new ProdutoCopa();

                    //carregando o registro do produtoCopa na entidade para o objeto produtoCopa
                    produtoCopa = service.ProdutoCopaService.Carregar(Integer.parseInt(this.telaBuscaProdutoCopa.getjTextFieldValor().getText()));

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaProdutoCopa.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    tabela.addRow(new Object[]{produtoCopa.getId(), produtoCopa.getDescricao(), produtoCopa.getValor(), produtoCopa.getStatus()});

                } else if (this.telaBuscaProdutoCopa.getjComboBoxFiltrarPor().getSelectedIndex() == 1) {

                    List<ProdutoCopa> listaProdutoCopas = new ArrayList<>();


                    //carregando o registro do produtoCopa na entidade para o objeto produtoCopa
                    listaProdutoCopas = service.ProdutoCopaService.Carregar("descricao", this.telaBuscaProdutoCopa.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaProdutoCopa.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (ProdutoCopa listaProdutoCopa : listaProdutoCopas) {
                        tabela.addRow(new Object[]{listaProdutoCopa.getId(),
                            listaProdutoCopa.getDescricao(),
                            listaProdutoCopa.getValor(),
                            listaProdutoCopa.getStatus()});

                    }

                } else if (this.telaBuscaProdutoCopa.getjComboBoxFiltrarPor().getSelectedIndex() == 2) {

                    List<ProdutoCopa> listaProdutoCopas = new ArrayList<>();

                    //carregando o registro do produtoCopa na entidade para o objeto produtoCopa
                    listaProdutoCopas = service.ProdutoCopaService.Carregar("valor", this.telaBuscaProdutoCopa.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaProdutoCopa.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (ProdutoCopa listaProdutoCopa : listaProdutoCopas) {
                        tabela.addRow(new Object[]{listaProdutoCopa.getId(),
                            listaProdutoCopa.getDescricao(),
                            listaProdutoCopa.getValor(),
                            listaProdutoCopa.getStatus()});

                    }
                }else if (this.telaBuscaProdutoCopa.getjComboBoxFiltrarPor().getSelectedIndex() == 3) {

                    List<ProdutoCopa> listaProdutoCopas = new ArrayList<>();

                    //carregando o registro do produtoCopa na entidade para o objeto produtoCopa
                    listaProdutoCopas = service.ProdutoCopaService.Carregar("status", this.telaBuscaProdutoCopa.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaProdutoCopa.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (ProdutoCopa listaProdutoCopa : listaProdutoCopas) {
                        tabela.addRow(new Object[]{listaProdutoCopa.getId(),
                            listaProdutoCopa.getDescricao(),
                            listaProdutoCopa.getValor(),
                            listaProdutoCopa.getStatus()});

                    }
                }
            }
        } else if (evento.getSource() == this.telaBuscaProdutoCopa.getjButtonSair()) {
            this.telaBuscaProdutoCopa.dispose();

        }
    }

}
