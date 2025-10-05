/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
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
            JOptionPane.showMessageDialog(null, "Botão Carregar Presionado...");
            if (this.telaBuscaModelo.getjTableColunas().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Errou. \nNão Existe Dados Selecionados");
                
            }else {
                JOptionPane.showMessageDialog(null, "Carregando Dados para Edição...");
            }

        
            
        } else if (evento.getSource() == this.telaBuscaModelo.getjButtonFiltrar()) {
            JOptionPane.showMessageDialog(null, "Botão Filtrar Presionado...");
            if(this.telaBuscaModelo.getjTextFieldValor().getText().trim().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
                
            }else{
              JOptionPane.showMessageDialog(null,"Filtrando informações...");
              if(this.telaBuscaModelo.getjComboBoxFiltrarPor().getSelectedIndex()==0){
                  JOptionPane.showMessageDialog(null, "Filtrando por ID");
              }else if (this.telaBuscaModelo.getjComboBoxFiltrarPor().getSelectedIndex()==1){
                  JOptionPane.showMessageDialog(null, "Filtrando por Descrição");
              }else if (this.telaBuscaModelo.getjComboBoxFiltrarPor().getSelectedIndex()==2){
                  JOptionPane.showMessageDialog(null,"Filtrando por Status");
              }
            }
        } else if (evento.getSource() == this.telaBuscaModelo.getjButtonSair()) {
            this.telaBuscaModelo.dispose();

        }
    }

}
