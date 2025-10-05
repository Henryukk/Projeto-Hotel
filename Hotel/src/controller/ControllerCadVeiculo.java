/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.ControllerCadHospede.codigo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Modelo;
import model.Veiculo;
import service.ModeloService;
import view.TelaBuscaVeiculo;
import view.TelaCadastroVeiculo;

public class ControllerCadVeiculo implements ActionListener {

   TelaCadastroVeiculo telaCadastroVeiculo;

    public static int codigo;

    public ControllerCadVeiculo(TelaCadastroVeiculo telaCadastroVeiculo) {
        this.telaCadastroVeiculo = telaCadastroVeiculo;
        this.telaCadastroVeiculo.getjButtonNovo().addActionListener(this);
        this.telaCadastroVeiculo.getjButtonSair().addActionListener(this);
        this.telaCadastroVeiculo.getjButtonGravar().addActionListener(this);
        this.telaCadastroVeiculo.getjButtonCancelar().addActionListener(this);
        this.telaCadastroVeiculo.getjButtonBuscar().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroVeiculo.getjPanel1(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroVeiculo.getjPanel2(), false);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastroVeiculo.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroVeiculo.getjPanel1(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroVeiculo.getjPanel2(), true);
            this.telaCadastroVeiculo.getjTextFieldIDVeiculo().setEnabled(false);
            
            

            this.telaCadastroVeiculo.getjFormattedTextFieldPlaca().requestFocus();
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroVeiculo.getjButtonGravar()) {

            if (this.telaCadastroVeiculo.getjFormattedTextFieldPlaca().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "O atributo Descricao é obrigatório...");
                this.telaCadastroVeiculo.getjFormattedTextFieldPlaca().requestFocus();
            } else {
                Veiculo veiculo = new Veiculo();
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                veiculo.setPlaca(this.telaCadastroVeiculo.getjFormattedTextFieldPlaca().getText());
                veiculo.setCor(this.telaCadastroVeiculo.getjComboBoxCorDoVeiculo().getSelectedItem().toString());
                veiculo.setStatus(this.telaCadastroVeiculo.getjTextFieldStatus().getText().trim().charAt(0));
                
                

                if (this.telaCadastroVeiculo.getjTextFieldIDVeiculo().getText().trim().equalsIgnoreCase("")) {
                    //se for igual a nada incluir
                    veiculo.setStatus('A');
                    service.VeiculoService.Criar(veiculo);

                } else {
                    //atualiza
                    veiculo.setId(Integer.parseInt(this.telaCadastroVeiculo.getjTextFieldIDVeiculo().getText()));
                    service.VeiculoService.Atualizar(veiculo);
                }

                utilities.Utilities.ativaDesativa(this.telaCadastroVeiculo.getjPanel1(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroVeiculo.getjPanel2(), false);

            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroVeiculo.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroVeiculo.getjPanel1(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroVeiculo.getjPanel2(), false);
            
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroVeiculo.getjButtonBuscar()) {

            codigo = 0;

            TelaBuscaVeiculo telaBuscasVeiculo = new TelaBuscaVeiculo(null, true);
            ControllerBuscaVeiculo controllerBuscaVeiculo = new ControllerBuscaVeiculo(telaBuscasVeiculo);
            telaBuscasVeiculo.setVisible(true);

            if (codigo != 0) {
                utilities.Utilities.ativaDesativa(this.telaCadastroVeiculo.getjPanel1(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroVeiculo.getjPanel2(), true);

                this.telaCadastroVeiculo.getjTextFieldIDVeiculo().setText(codigo + "");
                this.telaCadastroVeiculo.getjTextFieldIDVeiculo().setEnabled(false);

                Veiculo veiculo = new Veiculo();
                veiculo = service.VeiculoService.Carregar(codigo);
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                
                this.telaCadastroVeiculo.getjTextFieldIDVeiculo().setText(String.valueOf(veiculo.getId()));
                this.telaCadastroVeiculo.getjFormattedTextFieldPlaca().setText(veiculo.getPlaca());
                for (int i = 0; i < this.telaCadastroVeiculo.getjComboBoxCorDoVeiculo().getItemCount(); i++) {
                    if(veiculo.getCor().equalsIgnoreCase(this.telaCadastroVeiculo.getjComboBoxCorDoVeiculo().getItemAt(i))){
                        this.telaCadastroVeiculo.getjComboBoxCorDoVeiculo().setSelectedIndex(i);
                    }
                }
                this.telaCadastroVeiculo.getjTextFieldStatus().setText(String.valueOf(veiculo.getStatus()));

                this.telaCadastroVeiculo.getjFormattedTextFieldPlaca().requestFocus();
            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroVeiculo.getjButtonSair()) {
            this.telaCadastroVeiculo.dispose();

        }
    }
}
