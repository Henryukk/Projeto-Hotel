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
import model.Marca;
import view.TelaBuscaMarca;

/**
 *
 * @author aluno
 */
public class ControllerBuscaMarca implements ActionListener {

    TelaBuscaMarca telaBuscaMarca;

    public ControllerBuscaMarca(TelaBuscaMarca telaBuscaMarca) {
        this.telaBuscaMarca = telaBuscaMarca;

        this.telaBuscaMarca.getjButtonCarregar().addActionListener(this);
        this.telaBuscaMarca.getjButtonFiltrar().addActionListener(this);
        this.telaBuscaMarca.getjButtonSair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBuscaMarca.getjButtonCarregar()) {

            if (this.telaBuscaMarca.getjTableColunas().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Errou. \nNão Existe Dados Selecionados");
            } else {
                
                ControllerCadMarca.codigo = (int) this.telaBuscaMarca.getjTableColunas().getValueAt(this.telaBuscaMarca.getjTableColunas().getSelectedRow(), 0);
                
                this.telaBuscaMarca.dispose();
                
                
            }

        } else if (evento.getSource() == this.telaBuscaMarca.getjButtonFiltrar()) {

            if (this.telaBuscaMarca.getjTextFieldValor().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                if (this.telaBuscaMarca.getjComboBoxFiltrarPor().getSelectedIndex() == 0) {

                    //criando objeto para receber o dado que vira do banco de dados
                    Marca marca = new Marca();

                    //carregando o registro do marca na entidade para o objeto marca
                    marca = service.MarcaService.Carregar(Integer.parseInt(this.telaBuscaMarca.getjTextFieldValor().getText()));

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaMarca.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    tabela.addRow(new Object[]{marca.getId(), marca.getDescricao(), marca.getStatus()});

                } else if (this.telaBuscaMarca.getjComboBoxFiltrarPor().getSelectedIndex() == 1) {

                    List<Marca> listaMarcas = new ArrayList<>();


                    //carregando o registro do marca na entidade para o objeto marca
                    listaMarcas = service.MarcaService.Carregar("descricao", this.telaBuscaMarca.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaMarca.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Marca listaMarca : listaMarcas) {
                        tabela.addRow(new Object[]{listaMarca.getId(),
                            listaMarca.getDescricao(),
                            listaMarca.getStatus()});

                    }

                } 
                else if (this.telaBuscaMarca.getjComboBoxFiltrarPor().getSelectedIndex() == 2) {

                    List<Marca> listaMarcas = new ArrayList<>();

                    //carregando o registro do marca na entidade para o objeto marca
                    listaMarcas = service.MarcaService.Carregar("status", this.telaBuscaMarca.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaMarca.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Marca listaMarca : listaMarcas) {
                        tabela.addRow(new Object[]{listaMarca.getId(),
                            listaMarca.getDescricao(),
                            listaMarca.getStatus()});

                    }
                }
            }
        } else if (evento.getSource() == this.telaBuscaMarca.getjButtonSair()) {
            this.telaBuscaMarca.dispose();

        }
    }

}
