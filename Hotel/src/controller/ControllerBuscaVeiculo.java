/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.TelaBuscaVeiculo;

/**
 *
 * @author aluno
 */
public class ControllerBuscaVeiculo implements ActionListener {

    TelaBuscaVeiculo telaBuscaVeiculo;

    public ControllerBuscaVeiculo(TelaBuscaVeiculo telaBuscaHospede) {
        this.telaBuscaVeiculo = telaBuscaHospede;

        this.telaBuscaVeiculo.getjButtonCarregar().addActionListener(this);
        this.telaBuscaVeiculo.getjButtonFiltrar().addActionListener(this);
        this.telaBuscaVeiculo.getjButtonSair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaBuscaVeiculo.getjButtonCarregar()) {
            JOptionPane.showMessageDialog(null, "Botão Carregar Presionado...");
            if (this.telaBuscaVeiculo.getjTableColunas().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Errou. \nNão Existe Dados Selecionados");
                
            }else {
                JOptionPane.showMessageDialog(null, "Carregando Dados para Edição...");
            }

        
            
        } else if (evento.getSource() == this.telaBuscaVeiculo.getjButtonFiltrar()) {
            JOptionPane.showMessageDialog(null, "Botão Filtrar Presionado...");
            if(this.telaBuscaVeiculo.getjTextFieldValor().getText().trim().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
                
            }else{
              JOptionPane.showMessageDialog(null,"Filtrando informações...");
              if(this.telaBuscaVeiculo.getjComboBoxFiltrarPor().getSelectedIndex()==0){
                  JOptionPane.showMessageDialog(null, "Filtrando por ID");
              }else if (this.telaBuscaVeiculo.getjComboBoxFiltrarPor().getSelectedIndex()==1){
                  JOptionPane.showMessageDialog(null, "Filtrando por Placa");
              }else if (this.telaBuscaVeiculo.getjComboBoxFiltrarPor().getSelectedIndex()==2){
                  JOptionPane.showMessageDialog(null,"Filtrando por Cor");
              }else if (this.telaBuscaVeiculo.getjComboBoxFiltrarPor().getSelectedIndex()==3){
                  JOptionPane.showMessageDialog(null, "Filtrando por Status");
              }
            }
        } else if (evento.getSource() == this.telaBuscaVeiculo.getjButtonSair()) {
            this.telaBuscaVeiculo.dispose();

        }
    }

}
