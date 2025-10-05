/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ProdutoCopa;
import view.TelaBuscaProdutoCopa;
import view.TelaCadastroProdutoCopa;

public class ControllerCadProdutoCopa implements ActionListener {

    TelaCadastroProdutoCopa telaCadastroProdutoCopa;

    public static int codigo;

    public ControllerCadProdutoCopa(TelaCadastroProdutoCopa telaCadastroProdutoCopa) {
        this.telaCadastroProdutoCopa = telaCadastroProdutoCopa;
        this.telaCadastroProdutoCopa.getjButtonNovo().addActionListener(this);
        this.telaCadastroProdutoCopa.getjButtonSair().addActionListener(this);
        this.telaCadastroProdutoCopa.getjButtonGravar().addActionListener(this);
        this.telaCadastroProdutoCopa.getjButtonCancelar().addActionListener(this);
        this.telaCadastroProdutoCopa.getjButtonBuscar().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroProdutoCopa.getjPanel1(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroProdutoCopa.getjPanel2(), false);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastroProdutoCopa.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroProdutoCopa.getjPanel1(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroProdutoCopa.getjPanel2(), true);
            this.telaCadastroProdutoCopa.getjTextFieldID().setEnabled(false);
            
            

            this.telaCadastroProdutoCopa.getjTextFieldDescricao().requestFocus();
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroProdutoCopa.getjButtonGravar()) {

            if (this.telaCadastroProdutoCopa.getjTextFieldDescricao().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "O atributo Descricao é obrigatório...");
                this.telaCadastroProdutoCopa.getjTextFieldDescricao().requestFocus();
            } else {
                ProdutoCopa produtoCopa = new ProdutoCopa();
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                produtoCopa.setDescricao(this.telaCadastroProdutoCopa.getjTextFieldDescricao().getText());
                produtoCopa.setObs(this.telaCadastroProdutoCopa.getjTextFieldObservacao().getText());
                produtoCopa.setStatus(this.telaCadastroProdutoCopa.getjTextFieldStatus().getText().trim().charAt(0));
                produtoCopa.setValor(Float.parseFloat(this.telaCadastroProdutoCopa.getjTextFieldValor().getText()));
                

                if (this.telaCadastroProdutoCopa.getjTextFieldID().getText().trim().equalsIgnoreCase("")) {
                    //se for igual a nada incluir
                    produtoCopa.setStatus('A');
                    service.ProdutoCopaService.Criar(produtoCopa);

                } else {
                    //atualiza
                    produtoCopa.setId(Integer.parseInt(this.telaCadastroProdutoCopa.getjTextFieldID().getText()));
                    service.ProdutoCopaService.Atualizar(produtoCopa);
                }

                utilities.Utilities.ativaDesativa(this.telaCadastroProdutoCopa.getjPanel1(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroProdutoCopa.getjPanel2(), false);

            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroProdutoCopa.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroProdutoCopa.getjPanel1(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroProdutoCopa.getjPanel2(), false);
            
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroProdutoCopa.getjButtonBuscar()) {

            codigo = 0;

            TelaBuscaProdutoCopa telaBuscasProdutoCopa = new TelaBuscaProdutoCopa(null, true);
            ControllerBuscaProdutoCopa controllerBuscaProdutoCopa = new ControllerBuscaProdutoCopa(telaBuscasProdutoCopa);
            telaBuscasProdutoCopa.setVisible(true);

            if (codigo != 0) {
                utilities.Utilities.ativaDesativa(this.telaCadastroProdutoCopa.getjPanel1(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroProdutoCopa.getjPanel2(), true);

                this.telaCadastroProdutoCopa.getjTextFieldID().setText(codigo + "");
                this.telaCadastroProdutoCopa.getjTextFieldID().setEnabled(false);

                ProdutoCopa produtoCopa = new ProdutoCopa();
                produtoCopa = service.ProdutoCopaService.Carregar(codigo);
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                
                this.telaCadastroProdutoCopa.getjTextFieldID().setText(String.valueOf(produtoCopa.getId()));
                this.telaCadastroProdutoCopa.getjTextFieldDescricao().setText(produtoCopa.getDescricao());
                this.telaCadastroProdutoCopa.getjTextFieldObservacao().setText(produtoCopa.getObs());
                this.telaCadastroProdutoCopa.getjTextFieldStatus().setText(String.valueOf(produtoCopa.getStatus()));
                this.telaCadastroProdutoCopa.getjTextFieldValor().setText(String.valueOf(produtoCopa.getValor()));
                this.telaCadastroProdutoCopa.getjTextFieldDescricao().requestFocus();
            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroProdutoCopa.getjButtonSair()) {
            this.telaCadastroProdutoCopa.dispose();

        }
    }
}
