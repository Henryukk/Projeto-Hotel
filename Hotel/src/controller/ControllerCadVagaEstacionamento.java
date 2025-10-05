/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.VagaEstacionamento;
import view.TelaBuscaVagaEstacionamento;
import view.TelaCadastroVagaEstacionamento;

public class ControllerCadVagaEstacionamento implements ActionListener {

    TelaCadastroVagaEstacionamento telaCadastroVagaEstacionamento;

    public static int codigo;

    public ControllerCadVagaEstacionamento(TelaCadastroVagaEstacionamento telaCadastroVagaEstacionamento) {
        this.telaCadastroVagaEstacionamento = telaCadastroVagaEstacionamento;
        this.telaCadastroVagaEstacionamento.getjButtonNovo().addActionListener(this);
        this.telaCadastroVagaEstacionamento.getjButtonSair().addActionListener(this);
        this.telaCadastroVagaEstacionamento.getjButtonGravar().addActionListener(this);
        this.telaCadastroVagaEstacionamento.getjButtonCancelar().addActionListener(this);
        this.telaCadastroVagaEstacionamento.getjButtonBuscar().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroVagaEstacionamento.getjPanel1(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroVagaEstacionamento.getjPanel2(), false);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastroVagaEstacionamento.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroVagaEstacionamento.getjPanel1(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroVagaEstacionamento.getjPanel2(), true);
            this.telaCadastroVagaEstacionamento.getjTextFieldID().setEnabled(false);
            
            

            this.telaCadastroVagaEstacionamento.getjTextFieldDescricao().requestFocus();
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroVagaEstacionamento.getjButtonGravar()) {

            if (this.telaCadastroVagaEstacionamento.getjTextFieldDescricao().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "O atributo Descricao é obrigatório...");
                this.telaCadastroVagaEstacionamento.getjTextFieldDescricao().requestFocus();
            } else {
                VagaEstacionamento vagaEstacionamento = new VagaEstacionamento();
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                vagaEstacionamento.setDescricao(this.telaCadastroVagaEstacionamento.getjTextFieldDescricao().getText());
                vagaEstacionamento.setObs(this.telaCadastroVagaEstacionamento.getjTextFieldObservacao().getText());
                vagaEstacionamento.setStatus(this.telaCadastroVagaEstacionamento.getjTextFieldStatus().getText().trim().charAt(0));
                vagaEstacionamento.setMetragemVaga(Float.parseFloat(this.telaCadastroVagaEstacionamento.getjTextFieldMetragemVaga().getText()));
                

                if (this.telaCadastroVagaEstacionamento.getjTextFieldID().getText().trim().equalsIgnoreCase("")) {
                    //se for igual a nada incluir
                    vagaEstacionamento.setStatus('A');
                    service.VagaEstacionamentoService.Criar(vagaEstacionamento);

                } else {
                    //atualiza
                    vagaEstacionamento.setId(Integer.parseInt(this.telaCadastroVagaEstacionamento.getjTextFieldID().getText()));
                    service.VagaEstacionamentoService.Atualizar(vagaEstacionamento);
                }

                utilities.Utilities.ativaDesativa(this.telaCadastroVagaEstacionamento.getjPanel1(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroVagaEstacionamento.getjPanel2(), false);

            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroVagaEstacionamento.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroVagaEstacionamento.getjPanel1(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroVagaEstacionamento.getjPanel2(), false);
            
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroVagaEstacionamento.getjButtonBuscar()) {

            codigo = 0;

            TelaBuscaVagaEstacionamento telaBuscasVagaEstacionamento = new TelaBuscaVagaEstacionamento(null, true);
            ControllerBuscaVagaEstacionamento controllerBuscaVagaEstacionamento = new ControllerBuscaVagaEstacionamento(telaBuscasVagaEstacionamento);
            telaBuscasVagaEstacionamento.setVisible(true);

            if (codigo != 0) {
                utilities.Utilities.ativaDesativa(this.telaCadastroVagaEstacionamento.getjPanel1(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroVagaEstacionamento.getjPanel2(), true);

                this.telaCadastroVagaEstacionamento.getjTextFieldID().setText(codigo + "");
                this.telaCadastroVagaEstacionamento.getjTextFieldID().setEnabled(false);

                VagaEstacionamento vagaEstacionamento = new VagaEstacionamento();
                vagaEstacionamento = service.VagaEstacionamentoService.Carregar(codigo);
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                
                this.telaCadastroVagaEstacionamento.getjTextFieldID().setText(String.valueOf(vagaEstacionamento.getId()));
                this.telaCadastroVagaEstacionamento.getjTextFieldDescricao().setText(vagaEstacionamento.getDescricao());
                this.telaCadastroVagaEstacionamento.getjTextFieldObservacao().setText(vagaEstacionamento.getObs());
                this.telaCadastroVagaEstacionamento.getjTextFieldStatus().setText(String.valueOf(vagaEstacionamento.getStatus()));
                this.telaCadastroVagaEstacionamento.getjTextFieldMetragemVaga().setText(String.valueOf(vagaEstacionamento.getMetragemVaga()));
                this.telaCadastroVagaEstacionamento.getjTextFieldDescricao().requestFocus();
            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroVagaEstacionamento.getjButtonSair()) {
            this.telaCadastroVagaEstacionamento.dispose();

        }
    }
}
