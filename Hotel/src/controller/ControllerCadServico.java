/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Servico;
import view.TelaBuscaQuarto;
import view.TelaBuscaServico;
import view.TelaCadastroQuarto;
import view.TelaCadastroServico;

public class ControllerCadServico implements ActionListener {

    TelaCadastroServico telaCadastroServico;

    public static int codigo;

    public ControllerCadServico(TelaCadastroServico telaCadastroServico) {
        this.telaCadastroServico = telaCadastroServico;
        this.telaCadastroServico.getjButtonNovo().addActionListener(this);
        this.telaCadastroServico.getjButtonSair().addActionListener(this);
        this.telaCadastroServico.getjButtonGravar().addActionListener(this);
        this.telaCadastroServico.getjButtonCancelar().addActionListener(this);
        this.telaCadastroServico.getjButtonBuscar().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroServico.getjPanel1(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroServico.getjPanel2(), false);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastroServico.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroServico.getjPanel1(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroServico.getjPanel2(), true);
            this.telaCadastroServico.getjTextFieldID().setEnabled(false);
            
            

            this.telaCadastroServico.getjTextFieldDescricao().requestFocus();
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroServico.getjButtonGravar()) {

            if (this.telaCadastroServico.getjTextFieldDescricao().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "O atributo Descricao é obrigatório...");
                this.telaCadastroServico.getjTextFieldDescricao().requestFocus();
            } else {
                Servico servico = new Servico();
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                servico.setDescricao(this.telaCadastroServico.getjTextFieldDescricao().getText());
                servico.setObs(this.telaCadastroServico.getjTextFieldObservacao().getText());
                servico.setStatus(this.telaCadastroServico.getjTextFieldStatus().getText().trim().charAt(0));
                

                if (this.telaCadastroServico.getjTextFieldID().getText().trim().equalsIgnoreCase("")) {
                    //se for igual a nada incluir
                    servico.setStatus('A');
                    service.ServicoService.Criar(servico);

                } else {
                    //atualiza
                    servico.setId(Integer.parseInt(this.telaCadastroServico.getjTextFieldID().getText()));
                    service.ServicoService.Atualizar(servico);
                }

                utilities.Utilities.ativaDesativa(this.telaCadastroServico.getjPanel1(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroServico.getjPanel2(), false);

            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroServico.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroServico.getjPanel1(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroServico.getjPanel2(), false);
            
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroServico.getjButtonBuscar()) {

            codigo = 0;

            TelaBuscaServico telaBuscasServico = new TelaBuscaServico(null, true);
            ControllerBuscaServico controllerBuscaServico = new ControllerBuscaServico(telaBuscasServico);
            telaBuscasServico.setVisible(true);

            if (codigo != 0) {
                utilities.Utilities.ativaDesativa(this.telaCadastroServico.getjPanel1(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroServico.getjPanel2(), true);

                this.telaCadastroServico.getjTextFieldID().setText(codigo + "");
                this.telaCadastroServico.getjTextFieldID().setEnabled(false);

                Servico servico = new Servico();
                servico = service.ServicoService.Carregar(codigo);
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                
                this.telaCadastroServico.getjTextFieldID().setText(String.valueOf(servico.getId()));
                this.telaCadastroServico.getjTextFieldDescricao().setText(servico.getDescricao());
                this.telaCadastroServico.getjTextFieldObservacao().setText(servico.getObs());
                this.telaCadastroServico.getjTextFieldStatus().setText(String.valueOf(servico.getStatus()));

                this.telaCadastroServico.getjTextFieldDescricao().requestFocus();
            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroServico.getjButtonSair()) {
            this.telaCadastroServico.dispose();

        }
    }
}
