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
import model.Modelo;
import view.TelaBuscaModelo;

/**
 *   
 * @author aluno
 */
public class ControllerBuscaModelo implements ActionListener {

    TelaBuscaModelo telaBuscaModelo;

    public ControllerBuscaModelo(TelaBuscaModelo telaBuscaModelo) {
        this.telaBuscaModelo = telaBuscaModelo;

        this.telaBuscaModelo.getjButtonCarregar().addActionListener(this);
        this.telaBuscaModelo.getjButtonFiltrar().addActionListener(this);
        this.telaBuscaModelo.getjButtonSair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBuscaModelo.getjButtonCarregar()) {

            if (this.telaBuscaModelo.getjTableColunas().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Errou. \nNão Existe Dados Selecionados");
            } else {
                
                ControllerCadModelo.codigo = (int) this.telaBuscaModelo.getjTableColunas().getValueAt(this.telaBuscaModelo.getjTableColunas().getSelectedRow(), 0);
                
                this.telaBuscaModelo.dispose();
                
                
            }

        } else if (evento.getSource() == this.telaBuscaModelo.getjButtonFiltrar()) {

            if (this.telaBuscaModelo.getjTextFieldValor().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                if (this.telaBuscaModelo.getjComboBoxFiltrarPor().getSelectedIndex() == 0) {

                    //criando objeto para receber o dado que vira do banco de dados
                    Modelo modelo = new Modelo();

                    //carregando o registro do modelo na entidade para o objeto modelo
                    modelo = service.ModeloService.Carregar(Integer.parseInt(this.telaBuscaModelo.getjTextFieldValor().getText()));

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaModelo.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    tabela.addRow(new Object[]{modelo.getId(), modelo.getDescricao(),modelo.getMarca().getDescricao(), modelo.getStatus()});

                } else if (this.telaBuscaModelo.getjComboBoxFiltrarPor().getSelectedIndex() == 1) {

                    List<Modelo> listaModelos = new ArrayList<>();


                    //carregando o registro do modelo na entidade para o objeto modelo
                    listaModelos = service.ModeloService.Carregar("modelo.descricao", this.telaBuscaModelo.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaModelo.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Modelo listaModelo : listaModelos) {
                        tabela.addRow(new Object[]{listaModelo.getId(),
                            listaModelo.getDescricao(),
                            listaModelo.getMarca().getDescricao(),
                            listaModelo.getStatus()});

                    }

                } 
                else if (this.telaBuscaModelo.getjComboBoxFiltrarPor().getSelectedIndex() == 2) {

                    List<Modelo> listaModelos = new ArrayList<>();

                    //carregando o registro do modelo na entidade para o objeto modelo
                    listaModelos = service.ModeloService.Carregar("marca.descricao", this.telaBuscaModelo.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaModelo.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Modelo listaModelo : listaModelos) {
                        tabela.addRow(new Object[]{listaModelo.getId(),
                            listaModelo.getDescricao(),
                            listaModelo.getMarca().getDescricao(),
                            listaModelo.getStatus()});

                    }
                }
            }
        } else if (evento.getSource() == this.telaBuscaModelo.getjButtonSair()) {
            this.telaBuscaModelo.dispose();

        }
    }

}
