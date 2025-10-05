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
import model.Funcionario;
import view.TelaBuscaFuncionario;
import view.TelaCadastroFuncionario;

public class ControllerCadFuncionario implements ActionListener {

     TelaCadastroFuncionario telaCadastroFuncionario;
    public static int codigo;

    public ControllerCadFuncionario(TelaCadastroFuncionario telaCadastroFuncionario) {
        this.telaCadastroFuncionario = telaCadastroFuncionario;
        this.telaCadastroFuncionario.getjButtonNovo().addActionListener(this);
        this.telaCadastroFuncionario.getjButtonSair().addActionListener(this);
        this.telaCadastroFuncionario.getjButtonGravar().addActionListener(this);
        this.telaCadastroFuncionario.getjButtonCancelar().addActionListener(this);
        this.telaCadastroFuncionario.getjButtonBuscar().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroFuncionario.getjPanel1(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroFuncionario.getjPanel2(), false);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastroFuncionario.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroFuncionario.getjPanel1(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroFuncionario.getjPanel2(), true);
            this.telaCadastroFuncionario.getjTextFieldID().setEnabled(false);
            
            
            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("MM/dd/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroFuncionario.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroFuncionario.getjFormattedTextFieldDataCadastro().setEnabled(false);
            this.telaCadastroFuncionario.getjFormattedTextFieldNome().requestFocus();
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroFuncionario.getjButtonGravar()) {

            if (this.telaCadastroFuncionario.getjFormattedTextFieldNome().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "O atributo Nome é obrigatório...");
                this.telaCadastroFuncionario.getjFormattedTextFieldNome().requestFocus();
            } else {
                Funcionario funcionario = new Funcionario();
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                funcionario.setNome(this.telaCadastroFuncionario.getjFormattedTextFieldNome().getText());
                funcionario.setFone1(this.telaCadastroFuncionario.getjFormattedTextFieldFone1().getText());
                funcionario.setFone2(this.telaCadastroFuncionario.getjFormattedTextFieldFone2().getText());
                funcionario.setEmail(this.telaCadastroFuncionario.getjTextFieldEmail().getText());
                funcionario.setCep(this.telaCadastroFuncionario.getjFormattedTextFieldCEP().getText());
                funcionario.setLogradouro(this.telaCadastroFuncionario.getjTextFieldLogradouro().getText());
                funcionario.setBairro(this.telaCadastroFuncionario.getjTextFieldBairro().getText());
                funcionario.setCidade(this.telaCadastroFuncionario.getjTextFieldCidade().getText());
                funcionario.setComplemento(this.telaCadastroFuncionario.getjTextFieldComplemento().getText());
                funcionario.setDataCadastro(this.telaCadastroFuncionario.getjFormattedTextFieldDataCadastro().getText());
                funcionario.setCpf(this.telaCadastroFuncionario.getjFormattedTextFieldCPF().getText());
                funcionario.setRg(this.telaCadastroFuncionario.getjTextFieldRG().getText());
                funcionario.setObs(this.telaCadastroFuncionario.getjTextFieldObservacao().getText());
                funcionario.setUsuario(this.telaCadastroFuncionario.getjTextFieldUsuario().getText());
                funcionario.setSenha(this.telaCadastroFuncionario.getjPasswordFieldSenha().getText());
                funcionario.setSexo(this.telaCadastroFuncionario.getjComboBoxSexo().getSelectedItem().toString());
                funcionario.setStatus(this.telaCadastroFuncionario.getjTextFieldStatus().getText().trim().charAt(0));
                

                if (this.telaCadastroFuncionario.getjTextFieldID().getText().trim().equalsIgnoreCase("")) {
                    //se for igual a nada incluir
                    funcionario.setStatus('A');
                    service.FuncionarioService.Criar(funcionario);

                } else {
                    //atualiza
                    funcionario.setId(Integer.parseInt(this.telaCadastroFuncionario.getjTextFieldID().getText()));
                    service.FuncionarioService.Atualizar(funcionario);
                }

                utilities.Utilities.ativaDesativa(this.telaCadastroFuncionario.getjPanel1(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroFuncionario.getjPanel2(), false);

            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroFuncionario.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroFuncionario.getjPanel1(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroFuncionario.getjPanel2(), false);
            
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroFuncionario.getjButtonBuscar()) {

            codigo = 0;

            TelaBuscaFuncionario telaBuscasFuncionario = new TelaBuscaFuncionario(null, true);
            ControllerBuscaFuncionario controllerBuscaFuncionario = new ControllerBuscaFuncionario(telaBuscasFuncionario);
            telaBuscasFuncionario.setVisible(true);

            if (codigo != 0) {
                utilities.Utilities.ativaDesativa(this.telaCadastroFuncionario.getjPanel1(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroFuncionario.getjPanel2(), true);

                this.telaCadastroFuncionario.getjTextFieldID().setText(codigo + "");
                this.telaCadastroFuncionario.getjTextFieldID().setEnabled(false);

                Funcionario funcionario = new Funcionario();
                funcionario = service.FuncionarioService.Carregar(codigo);
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                
                this.telaCadastroFuncionario.getjTextFieldID().setText(String.valueOf(funcionario.getId()));
                this.telaCadastroFuncionario.getjFormattedTextFieldNome().setText(funcionario.getNome());
                this.telaCadastroFuncionario.getjFormattedTextFieldFone1().setText(funcionario.getFone1());
                this.telaCadastroFuncionario.getjFormattedTextFieldFone2().setText(funcionario.getFone2());
                this.telaCadastroFuncionario.getjTextFieldEmail().setText(funcionario.getEmail());
                this.telaCadastroFuncionario.getjFormattedTextFieldCEP().setText(funcionario.getCep());
                this.telaCadastroFuncionario.getjTextFieldLogradouro().setText(funcionario.getLogradouro());
                this.telaCadastroFuncionario.getjTextFieldBairro().setText(funcionario.getBairro());
                this.telaCadastroFuncionario.getjTextFieldCidade().setText(funcionario.getCidade());
                this.telaCadastroFuncionario.getjTextFieldComplemento().setText(funcionario.getComplemento());
                this.telaCadastroFuncionario.getjFormattedTextFieldDataCadastro().setText(funcionario.getDataCadastro());
                this.telaCadastroFuncionario.getjFormattedTextFieldCPF().setText(funcionario.getCpf());
                this.telaCadastroFuncionario.getjTextFieldRG().setText(funcionario.getRg());
                this.telaCadastroFuncionario.getjTextFieldObservacao().setText(funcionario.getObs());
                this.telaCadastroFuncionario.getjTextFieldUsuario().setText(funcionario.getUsuario());
                this.telaCadastroFuncionario.getjPasswordFieldSenha().setText(funcionario.getSenha());

                for (int i = 0; i < this.telaCadastroFuncionario.getjComboBoxSexo().getItemCount(); i++) {
                    if(funcionario.getSexo().equalsIgnoreCase(this.telaCadastroFuncionario.getjComboBoxSexo().getItemAt(i))){
                        this.telaCadastroFuncionario.getjComboBoxSexo().setSelectedIndex(i);
                    }
                }
                this.telaCadastroFuncionario.getjTextFieldStatus().setText(String.valueOf(funcionario.getStatus()));

                this.telaCadastroFuncionario.getjFormattedTextFieldNome().requestFocus();
            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroFuncionario.getjButtonSair()) {
            this.telaCadastroFuncionario.dispose();

        }
    }
}
