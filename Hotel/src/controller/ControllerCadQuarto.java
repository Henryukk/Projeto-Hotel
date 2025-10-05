/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Quarto;
import view.TelaBuscaQuarto;
import view.TelaCadastroQuarto;

public class ControllerCadQuarto implements ActionListener {

    TelaCadastroQuarto telaCadastroQuarto;
    public static int codigo;

    public ControllerCadQuarto(TelaCadastroQuarto telaCadastroQuarto) {
        this.telaCadastroQuarto = telaCadastroQuarto;
        this.telaCadastroQuarto.getjButtonNovo().addActionListener(this);
        this.telaCadastroQuarto.getjButtonSair().addActionListener(this);
        this.telaCadastroQuarto.getjButtonGravar().addActionListener(this);
        this.telaCadastroQuarto.getjButtonCancelar().addActionListener(this);
        this.telaCadastroQuarto.getjButtonBuscar().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroQuarto.getjPanel1(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroQuarto.getjPanel2(), false);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastroQuarto.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroQuarto.getjPanel1(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroQuarto.getjPanel2(), true);
            this.telaCadastroQuarto.getjTextFieldID().setEnabled(false);
            
            this.telaCadastroQuarto.getjTextFieldDescricao().requestFocus();
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroQuarto.getjButtonGravar()) {

            if (this.telaCadastroQuarto.getjTextFieldDescricao().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "O atributo Descricao é obrigatório...");
                this.telaCadastroQuarto.getjTextFieldDescricao().requestFocus();
            } else {
                Quarto quarto = new Quarto();
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                quarto.setDescricao(this.telaCadastroQuarto.getjTextFieldDescricao().getText());
                quarto.setCapacidadeHospedes(Integer.parseInt(this.telaCadastroQuarto.getjTextFieldCapacidadeDoQuarto().getText()));
                quarto.setMetragem(Float.parseFloat(this.telaCadastroQuarto.getjTextFieldMetragem().getText()));
                quarto.setIdentificacao(this.telaCadastroQuarto.getjTextFieldIdentificacao().getText());
                quarto.setAndar(Integer.parseInt(this.telaCadastroQuarto.getjTextFieldAndar().getText()));
                //quarto.setFlagAnimais(Boolean.parseBoolean(this.telaCadastroQuarto.getjComboBoxFlagAnimais().getSelectedItem().toString()));
                String opcao = this.telaCadastroQuarto.getjComboBoxFlagAnimais().getSelectedItem().toString();
                quarto.setFlagAnimais(opcao.equalsIgnoreCase("Sim"));

                quarto.setObs(this.telaCadastroQuarto.getjTextFieldObservacao().getText());
                quarto.setStatus(this.telaCadastroQuarto.getjTextFieldStatus().getText().trim().charAt(0));
                

                if (this.telaCadastroQuarto.getjTextFieldID().getText().trim().equalsIgnoreCase("")) {
                    //se for igual a nada incluir
                    quarto.setStatus('A');
                    service.QuartoService.Criar(quarto);

                } else {
                    //atualiza
                    quarto.setId(Integer.parseInt(this.telaCadastroQuarto.getjTextFieldID().getText()));
                    service.QuartoService.Atualizar(quarto);
                }

                utilities.Utilities.ativaDesativa(this.telaCadastroQuarto.getjPanel1(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroQuarto.getjPanel2(), false);

            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroQuarto.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroQuarto.getjPanel1(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroQuarto.getjPanel2(), false);
            
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroQuarto.getjButtonBuscar()) {

            codigo = 0;

            TelaBuscaQuarto telaBuscasQuarto = new TelaBuscaQuarto(null, true);
            ControllerBuscaQuarto controllerBuscaQuarto = new ControllerBuscaQuarto(telaBuscasQuarto);
            telaBuscasQuarto.setVisible(true);

            if (codigo != 0) {
                utilities.Utilities.ativaDesativa(this.telaCadastroQuarto.getjPanel1(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroQuarto.getjPanel2(), true);

                this.telaCadastroQuarto.getjTextFieldID().setText(codigo + "");
                this.telaCadastroQuarto.getjTextFieldID().setEnabled(false);

                Quarto quarto = new Quarto();
                quarto = service.QuartoService.Carregar(codigo);
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                
                this.telaCadastroQuarto.getjTextFieldID().setText(String.valueOf(quarto.getId()));
                this.telaCadastroQuarto.getjTextFieldDescricao().setText(quarto.getDescricao());
                this.telaCadastroQuarto.getjTextFieldCapacidadeDoQuarto().setText(String.valueOf(quarto.getId()));
                this.telaCadastroQuarto.getjTextFieldMetragem().setText(String.valueOf(quarto.getMetragem()));
                this.telaCadastroQuarto.getjTextFieldIdentificacao().setText(quarto.getIdentificacao());        
                this.telaCadastroQuarto.getjTextFieldAndar().setText(String.valueOf(quarto.getAndar())); 
                 
                if (quarto.getFlagAnimais()) {
                this.telaCadastroQuarto.getjComboBoxFlagAnimais().setSelectedItem("Sim");
                } else {
                    this.telaCadastroQuarto.getjComboBoxFlagAnimais().setSelectedItem("Não");
                    
                    }
                
                this.telaCadastroQuarto.getjTextFieldObservacao().setText(quarto.getObs());

                this.telaCadastroQuarto.getjTextFieldStatus().setText(String.valueOf(quarto.getStatus()));

            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroQuarto.getjButtonSair()) {
            this.telaCadastroQuarto.dispose();

        }
    }
}
