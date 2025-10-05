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
import model.Fornecedor;
import view.TelaBuscaFornecedor;
import view.TelaCadastroFornecedor;

/**
 *
 * @author aluno
 */
public class ControllerCadFornecedor implements ActionListener {

     TelaCadastroFornecedor telaCadastroFornecedor;
    public static int codigo;

    public ControllerCadFornecedor(TelaCadastroFornecedor telaCadastroFornecedor) {
        this.telaCadastroFornecedor = telaCadastroFornecedor;
        this.telaCadastroFornecedor.getjButtonNovo().addActionListener(this);
        this.telaCadastroFornecedor.getjButtonSair().addActionListener(this);
        this.telaCadastroFornecedor.getjButtonGravar().addActionListener(this);
        this.telaCadastroFornecedor.getjButtonCancelar().addActionListener(this);
        this.telaCadastroFornecedor.getjButtonBuscar().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroFornecedor.getjPanel1(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroFornecedor.getjPanel2(), false);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastroFornecedor.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroFornecedor.getjPanel1(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroFornecedor.getjPanel2(), true);
            this.telaCadastroFornecedor.getjTextFieldID().setEnabled(false);
            
            
            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("MM/dd/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroFornecedor.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroFornecedor.getjFormattedTextFieldDataCadastro().setEnabled(false);
            this.telaCadastroFornecedor.getjFormattedTextFieldNome().requestFocus();
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroFornecedor.getjButtonGravar()) {

            if (this.telaCadastroFornecedor.getjFormattedTextFieldNome().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "O atributo Nome é obrigatório...");
                this.telaCadastroFornecedor.getjFormattedTextFieldNome().requestFocus();
            } else {
                Fornecedor fornecedor = new Fornecedor();
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                fornecedor.setNome(this.telaCadastroFornecedor.getjFormattedTextFieldNome().getText());
                fornecedor.setFone1(this.telaCadastroFornecedor.getjFormattedTextFieldFone1().getText());
                fornecedor.setFone2(this.telaCadastroFornecedor.getjFormattedTextFieldFone2().getText());
                fornecedor.setEmail(this.telaCadastroFornecedor.getjTextFieldEmail().getText());
                fornecedor.setCep(this.telaCadastroFornecedor.getjFormattedTextFieldCEP().getText());
                fornecedor.setLogradouro(this.telaCadastroFornecedor.getjTextFieldLogradouro().getText());
                fornecedor.setBairro(this.telaCadastroFornecedor.getjTextFieldBairro().getText());
                fornecedor.setCidade(this.telaCadastroFornecedor.getjTextFieldCidade().getText());
                fornecedor.setComplemento(this.telaCadastroFornecedor.getjTextFieldComplemento().getText());
                fornecedor.setDataCadastro(this.telaCadastroFornecedor.getjFormattedTextFieldDataCadastro().getText());
                fornecedor.setCpf(this.telaCadastroFornecedor.getjFormattedTextFieldCPF().getText());
                fornecedor.setRg(this.telaCadastroFornecedor.getjTextFieldRG().getText());
                fornecedor.setObs(this.telaCadastroFornecedor.getjTextFieldObservacao().getText());
                fornecedor.setRazaoSocial(this.telaCadastroFornecedor.getjTextFieldRazaoSocial().getText());
                fornecedor.setCnpj(this.telaCadastroFornecedor.getjTextFieldCNPJ().getText());
                fornecedor.setInscricaoEstadual(this.telaCadastroFornecedor.getjTextFieldInscricaoEstadual().getText());
                fornecedor.setContato(this.telaCadastroFornecedor.getjTextFieldContato().getText());
                fornecedor.setStatus(this.telaCadastroFornecedor.getjTextFieldStatus().getText().trim().charAt(0));
                

                if (this.telaCadastroFornecedor.getjTextFieldID().getText().trim().equalsIgnoreCase("")) {
                    //se for igual a nada incluir
                    fornecedor.setStatus('A');
                    service.FornecedorService.Criar(fornecedor);

                } else {
                    //atualiza
                    fornecedor.setId(Integer.parseInt(this.telaCadastroFornecedor.getjTextFieldID().getText()));
                    service.FornecedorService.Atualizar(fornecedor);
                }

                utilities.Utilities.ativaDesativa(this.telaCadastroFornecedor.getjPanel1(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroFornecedor.getjPanel2(), false);

            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroFornecedor.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroFornecedor.getjPanel1(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroFornecedor.getjPanel2(), false);
            
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroFornecedor.getjButtonBuscar()) {

            codigo = 0;

            TelaBuscaFornecedor telaBuscasFornecedor = new TelaBuscaFornecedor(null, true);
            ControllerBuscaFornecedor controllerBuscaFornecedor = new ControllerBuscaFornecedor(telaBuscasFornecedor);
            telaBuscasFornecedor.setVisible(true);

            if (codigo != 0) {
                utilities.Utilities.ativaDesativa(this.telaCadastroFornecedor.getjPanel1(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroFornecedor.getjPanel2(), true);

                this.telaCadastroFornecedor.getjTextFieldID().setText(codigo + "");
                this.telaCadastroFornecedor.getjTextFieldID().setEnabled(false);

                Fornecedor fornecedor = new Fornecedor();
                fornecedor = service.FornecedorService.Carregar(codigo);
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                
                this.telaCadastroFornecedor.getjTextFieldID().setText(String.valueOf(fornecedor.getId()));
                this.telaCadastroFornecedor.getjFormattedTextFieldNome().setText(fornecedor.getNome());
                this.telaCadastroFornecedor.getjFormattedTextFieldFone1().setText(fornecedor.getFone1());
                this.telaCadastroFornecedor.getjFormattedTextFieldFone2().setText(fornecedor.getFone2());
                this.telaCadastroFornecedor.getjTextFieldEmail().setText(fornecedor.getEmail());
                this.telaCadastroFornecedor.getjFormattedTextFieldCEP().setText(fornecedor.getCep());
                this.telaCadastroFornecedor.getjTextFieldLogradouro().setText(fornecedor.getLogradouro());
                this.telaCadastroFornecedor.getjTextFieldBairro().setText(fornecedor.getBairro());
                this.telaCadastroFornecedor.getjTextFieldCidade().setText(fornecedor.getCidade());
                this.telaCadastroFornecedor.getjTextFieldComplemento().setText(fornecedor.getComplemento());
                this.telaCadastroFornecedor.getjFormattedTextFieldDataCadastro().setText(fornecedor.getDataCadastro());
                this.telaCadastroFornecedor.getjFormattedTextFieldCPF().setText(fornecedor.getCpf());
                this.telaCadastroFornecedor.getjTextFieldRG().setText(fornecedor.getRg());
                this.telaCadastroFornecedor.getjTextFieldObservacao().setText(fornecedor.getObs());
                this.telaCadastroFornecedor.getjTextFieldRazaoSocial().setText(fornecedor.getRazaoSocial());
                this.telaCadastroFornecedor.getjTextFieldCNPJ().setText(fornecedor.getCnpj());
                this.telaCadastroFornecedor.getjTextFieldInscricaoEstadual().setText(fornecedor.getInscricaoEstadual());
                this.telaCadastroFornecedor.getjTextFieldContato().setText(fornecedor.getContato());
                this.telaCadastroFornecedor.getjTextFieldStatus().setText(String.valueOf(fornecedor.getStatus()));

                this.telaCadastroFornecedor.getjFormattedTextFieldNome().requestFocus();
            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroFornecedor.getjButtonSair()) {
            this.telaCadastroFornecedor.dispose();

        }
    }
}
