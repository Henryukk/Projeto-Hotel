/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Veiculo;
import view.TelaBuscaVeiculo;

/**
 *
 * @author aluno
 */
public class ControllerBuscaVeiculo implements ActionListener {

    TelaBuscaVeiculo telaBuscaVeiculo;

    public ControllerBuscaVeiculo(TelaBuscaVeiculo telaBuscaVeiculo) {
        this.telaBuscaVeiculo = telaBuscaVeiculo;

        this.telaBuscaVeiculo.getjButtonCarregar().addActionListener(this);
        this.telaBuscaVeiculo.getjButtonFiltrar().addActionListener(this);
        this.telaBuscaVeiculo.getjButtonSair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        if (evento.getSource() == this.telaBuscaVeiculo.getjButtonCarregar()) {

            if (this.telaBuscaVeiculo.getjTableColunas().getRowCount() == 0
                    || this.telaBuscaVeiculo.getjTableColunas().getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Errou.\nNão Existe Dados Selecionados");
            } else {
                // Pega o ID da linha selecionada (coluna 0) e devolve ao cadastro
                int idSelecionado = (int) this.telaBuscaVeiculo.getjTableColunas()
                        .getValueAt(this.telaBuscaVeiculo.getjTableColunas().getSelectedRow(), 0);
                ControllerCadVeiculo.codigo = idSelecionado;
                this.telaBuscaVeiculo.dispose();
            }

        } else if (evento.getSource() == this.telaBuscaVeiculo.getjButtonFiltrar()) {

            if (this.telaBuscaVeiculo.getjTextFieldValor().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {

                int indice = this.telaBuscaVeiculo.getjComboBoxFiltrarPor().getSelectedIndex();
                String valor = this.telaBuscaVeiculo.getjTextFieldValor().getText().trim();

                DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaVeiculo
                        .getjTableColunas().getModel();
                tabela.setRowCount(0); 
                try {
                    if (indice == 0) {
                        
                        int id = Integer.parseInt(valor);
                        Veiculo veiculo = service.VeiculoService.Carregar(id);
                        if (veiculo != null) {
                            tabela.addRow(new Object[]{
                                veiculo.getId(),
                                veiculo.getPlaca(),
                                veiculo.getCor(),
                                veiculo.getStatus()
                            });
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado para o ID informado.");
                        }

                    } else if (indice == 1) {
                        
                        List<Veiculo> lista = service.VeiculoService.Carregar("veiculo.placa", valor);
                        for (Veiculo veiculo : lista) {
                            tabela.addRow(new Object[]{
                                veiculo.getId(),
                                veiculo.getPlaca(),
                                veiculo.getCor(),
                                veiculo.getStatus()
                            });
                        }
                        if (tabela.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado com essa placa.");
                        }

                    } else if (indice == 2) {
                        
                        List<Veiculo> lista = service.VeiculoService.Carregar("veiculo.cor", valor);
                        for (Veiculo veiculo : lista) {
                            tabela.addRow(new Object[]{
                                veiculo.getId(),
                                veiculo.getPlaca(),
                                veiculo.getCor(),
                                veiculo.getStatus()
                            });
                        }
                        if (tabela.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado com essa Cor.");
                        }

                    } else if (indice == 3) {
                        
                        List<Veiculo> lista = service.VeiculoService.Carregar("veiculo.status", valor);
                        for (Veiculo veiculo : lista) {
                            tabela.addRow(new Object[]{
                                veiculo.getId(),
                                veiculo.getPlaca(),
                                veiculo.getCor(),
                                veiculo.getStatus()
                            });
                        }
                        if (tabela.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado com esse status.");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione um critério em Filtrar por.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido. Informe um número.");
                }
            }

        } else if (evento.getSource() == this.telaBuscaVeiculo.getjButtonSair()) {
            this.telaBuscaVeiculo.dispose();
        }
    }
}
