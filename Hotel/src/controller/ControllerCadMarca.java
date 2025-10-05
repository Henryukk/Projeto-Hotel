/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Marca;
import view.TelaBuscaMarca;
import view.TelaCadastroMarca;

public class ControllerCadMarca implements ActionListener {

    TelaCadastroMarca telaCadastroMarca;

    public static int codigo;

    public ControllerCadMarca(TelaCadastroMarca telaCadastroMarca) {
        this.telaCadastroMarca = telaCadastroMarca;
        this.telaCadastroMarca.getjButtonNovo().addActionListener(this);
        this.telaCadastroMarca.getjButtonSair().addActionListener(this);
        this.telaCadastroMarca.getjButtonGravar().addActionListener(this);
        this.telaCadastroMarca.getjButtonCancelar().addActionListener(this);
        this.telaCadastroMarca.getjButtonBuscar().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroMarca.getjPanel1(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroMarca.getjPanel2(), false);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastroMarca.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroMarca.getjPanel1(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroMarca.getjPanel2(), true);
            this.telaCadastroMarca.getjTextFieldID().setEnabled(false);
            
            

            this.telaCadastroMarca.getjTextFieldDescricao().requestFocus();
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroMarca.getjButtonGravar()) {

            if (this.telaCadastroMarca.getjTextFieldDescricao().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "O atributo Descricao é obrigatório...");
                this.telaCadastroMarca.getjTextFieldDescricao().requestFocus();
            } else {
                Marca marca = new Marca();
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                marca.setDescricao(this.telaCadastroMarca.getjTextFieldDescricao().getText());
                marca.setStatus(this.telaCadastroMarca.getjTextFieldStatus().getText().trim().charAt(0));
                

                if (this.telaCadastroMarca.getjTextFieldID().getText().trim().equalsIgnoreCase("")) {
                    //se for igual a nada incluir
                    marca.setStatus('A');
                    service.MarcaService.Criar(marca);

                } else {
                    //atualiza
                    marca.setId(Integer.parseInt(this.telaCadastroMarca.getjTextFieldID().getText()));
                    service.MarcaService.Atualizar(marca);
                }

                utilities.Utilities.ativaDesativa(this.telaCadastroMarca.getjPanel1(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroMarca.getjPanel2(), false);

            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroMarca.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroMarca.getjPanel1(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroMarca.getjPanel2(), false);
            
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroMarca.getjButtonBuscar()) {

            codigo = 0;

            TelaBuscaMarca telaBuscasMarca = new TelaBuscaMarca(null, true);
            ControllerBuscaMarca controllerBuscaMarca = new ControllerBuscaMarca(telaBuscasMarca);
            telaBuscasMarca.setVisible(true);

            if (codigo != 0) {
                utilities.Utilities.ativaDesativa(this.telaCadastroMarca.getjPanel1(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroMarca.getjPanel2(), true);

                this.telaCadastroMarca.getjTextFieldID().setText(codigo + "");
                this.telaCadastroMarca.getjTextFieldID().setEnabled(false);

                Marca marca = new Marca();
                marca = service.MarcaService.Carregar(codigo);
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                
                this.telaCadastroMarca.getjTextFieldID().setText(String.valueOf(marca.getId()));
                this.telaCadastroMarca.getjTextFieldDescricao().setText(marca.getDescricao());
                this.telaCadastroMarca.getjTextFieldStatus().setText(String.valueOf(marca.getStatus()));

                this.telaCadastroMarca.getjTextFieldDescricao().requestFocus();
            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroMarca.getjButtonSair()) {
            this.telaCadastroMarca.dispose();

        }
    }
}
