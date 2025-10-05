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
import model.Quarto;
import view.TelaBuscaQuarto;

/**
 *   
 * @author aluno
 */
public class ControllerBuscaQuarto implements ActionListener {

    TelaBuscaQuarto telaBuscaQuarto;

    public ControllerBuscaQuarto(TelaBuscaQuarto telaBuscaQuarto) {
        this.telaBuscaQuarto = telaBuscaQuarto;

        this.telaBuscaQuarto.getjButtonCarregar().addActionListener(this);
        this.telaBuscaQuarto.getjButtonFiltrar().addActionListener(this);
        this.telaBuscaQuarto.getjButtonSair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBuscaQuarto.getjButtonCarregar()) {

            if (this.telaBuscaQuarto.getjTableColunas().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Errou. \nNão Existe Dados Selecionados");
            } else {
                
                ControllerCadQuarto.codigo = (int) this.telaBuscaQuarto.getjTableColunas().getValueAt(this.telaBuscaQuarto.getjTableColunas().getSelectedRow(), 0);
                
                this.telaBuscaQuarto.dispose();
                
                
            }

        } else if (evento.getSource() == this.telaBuscaQuarto.getjButtonFiltrar()) {

            if (this.telaBuscaQuarto.getjTextFieldValor().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                if (this.telaBuscaQuarto.getjComboBoxFiltrarPor().getSelectedIndex() == 0) {

                    //criando objeto para receber o dado que vira do banco de dados
                    Quarto quarto = new Quarto();

                    //carregando o registro do quarto na entidade para o objeto quarto
                    quarto = service.QuartoService.Carregar(Integer.parseInt(this.telaBuscaQuarto.getjTextFieldValor().getText()));

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaQuarto.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    tabela.addRow(new Object[]{quarto.getId(), quarto.getIdentificacao(), quarto.getAndar(), quarto.getStatus()});

                } else if (this.telaBuscaQuarto.getjComboBoxFiltrarPor().getSelectedIndex() == 1) {

                    List<Quarto> listaQuartos = new ArrayList<>();


                    //carregando o registro do quarto na entidade para o objeto quarto
                    listaQuartos = service.QuartoService.Carregar("identificacao", this.telaBuscaQuarto.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaQuarto.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Quarto listaQuarto : listaQuartos) {
                        tabela.addRow(new Object[]{listaQuarto.getId(),
                            listaQuarto.getIdentificacao(),
                            listaQuarto.getAndar(),
                            listaQuarto.getStatus()});

                    }

                } else if (this.telaBuscaQuarto.getjComboBoxFiltrarPor().getSelectedIndex() == 2) {

                    List<Quarto> listaQuartos = new ArrayList<>();

                    //carregando o registro do quarto na entidade para o objeto quarto
                    listaQuartos = service.QuartoService.Carregar("andar", this.telaBuscaQuarto.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaQuarto.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Quarto listaQuarto : listaQuartos) {
                        tabela.addRow(new Object[]{listaQuarto.getId(),
                            listaQuarto.getIdentificacao(),
                            listaQuarto.getAndar(),
                            listaQuarto.getStatus()});

                    }
                } else if (this.telaBuscaQuarto.getjComboBoxFiltrarPor().getSelectedIndex() == 3) {

                    List<Quarto> listaQuartos = new ArrayList<>();

                    //carregando o registro do quarto na entidade para o objeto quarto
                    listaQuartos = service.QuartoService.Carregar("status", this.telaBuscaQuarto.getjTextFieldValor().getText());

                    //criando um objeto tabela do tipo DefaultTableModel e atribuindo o modelo de tabela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaQuarto.getjTableColunas().getModel();
                    tabela.setRowCount(0);

                    for (Quarto listaQuarto : listaQuartos) {
                        tabela.addRow(new Object[]{listaQuarto.getId(),
                            listaQuarto.getIdentificacao(),
                            listaQuarto.getAndar(),
                            listaQuarto.getStatus()});

                    }
                }
            }
        } else if (evento.getSource() == this.telaBuscaQuarto.getjButtonSair()) {
            this.telaBuscaQuarto.dispose();

        }
    }

}
