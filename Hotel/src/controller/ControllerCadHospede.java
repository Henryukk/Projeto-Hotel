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
import model.Hospede;
import view.TelaBuscasHospede;
import view.TelaCadastroHospede;

public class ControllerCadHospede implements ActionListener {

    TelaCadastroHospede telaCadastroHospede;
    public static int codigo;

    public ControllerCadHospede(TelaCadastroHospede telaCadastroHospede) {
        this.telaCadastroHospede = telaCadastroHospede;
        this.telaCadastroHospede.getjButtonNovo().addActionListener(this);
        this.telaCadastroHospede.getjButtonSair().addActionListener(this);
        this.telaCadastroHospede.getjButtonGravar().addActionListener(this);
        this.telaCadastroHospede.getjButtonCancelar().addActionListener(this);
        this.telaCadastroHospede.getjButtonBuscar().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroHospede.getjPanel1(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroHospede.getjPanel2(), false);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastroHospede.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroHospede.getjPanel1(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroHospede.getjPanel2(), true);
            this.telaCadastroHospede.getjTextFieldID().setEnabled(false);
            
            
            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("MM/dd/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroHospede.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroHospede.getjFormattedTextFieldDataCadastro().setEnabled(false);
            this.telaCadastroHospede.getjFormattedTextFieldNome().requestFocus();
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroHospede.getjButtonGravar()) {

            if (this.telaCadastroHospede.getjFormattedTextFieldNome().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "O atributo Nome é obrigatório...");
                this.telaCadastroHospede.getjFormattedTextFieldNome().requestFocus();
            } else {
                Hospede hospede = new Hospede();
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                hospede.setNome(this.telaCadastroHospede.getjFormattedTextFieldNome().getText());
                hospede.setFone1(this.telaCadastroHospede.getjFormattedTextFieldFone1().getText());
                hospede.setFone2(this.telaCadastroHospede.getjFormattedTextFieldFone2().getText());
                hospede.setEmail(this.telaCadastroHospede.getjTextFieldEmail().getText());
                hospede.setCep(this.telaCadastroHospede.getjFormattedTextFieldCEP().getText());
                hospede.setLogradouro(this.telaCadastroHospede.getjTextFieldLogradouro().getText());
                hospede.setBairro(this.telaCadastroHospede.getjTextFieldBairro().getText());
                hospede.setCidade(this.telaCadastroHospede.getjTextFieldCidade().getText());
                hospede.setComplemento(this.telaCadastroHospede.getjTextFieldComplemento().getText());
                hospede.setDataCadastro(this.telaCadastroHospede.getjFormattedTextFieldDataCadastro().getText());
                hospede.setCpf(this.telaCadastroHospede.getjFormattedTextFieldCPF().getText());
                hospede.setRg(this.telaCadastroHospede.getjTextFieldRG().getText());
                hospede.setObs(this.telaCadastroHospede.getjTextFieldObservacao().getText());
                hospede.setRazaoSocial(this.telaCadastroHospede.getjTextFieldRazaoSocial().getText());
                hospede.setCnpj(this.telaCadastroHospede.getjTextFieldCNPJ().getText());
                hospede.setInscricaoEstadual(this.telaCadastroHospede.getjTextFieldInscricaoEstadual().getText());
                hospede.setContato(this.telaCadastroHospede.getjTextFieldContato().getText());
                hospede.setSexo(this.telaCadastroHospede.getjComboBoxSexo().getSelectedItem().toString());
                hospede.setStatus(this.telaCadastroHospede.getjTextFieldStatus().getText().trim().charAt(0));
                

                if (this.telaCadastroHospede.getjTextFieldID().getText().trim().equalsIgnoreCase("")) {
                    //se for igual a nada incluir
                    hospede.setStatus('A');
                    service.HospedeService.Criar(hospede);

                } else {
                    //atualiza
                    hospede.setId(Integer.parseInt(this.telaCadastroHospede.getjTextFieldID().getText()));
                    service.HospedeService.Atualizar(hospede);
                }

                utilities.Utilities.ativaDesativa(this.telaCadastroHospede.getjPanel1(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroHospede.getjPanel2(), false);

            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroHospede.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroHospede.getjPanel1(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroHospede.getjPanel2(), false);
            
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroHospede.getjButtonBuscar()) {

            codigo = 0;

            TelaBuscasHospede telaBuscasHospede = new TelaBuscasHospede(null, true);
            ControllerBuscaHospede controllerBuscaHospede = new ControllerBuscaHospede(telaBuscasHospede);
            telaBuscasHospede.setVisible(true);

            if (codigo != 0) {
                utilities.Utilities.ativaDesativa(this.telaCadastroHospede.getjPanel1(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroHospede.getjPanel2(), true);

                this.telaCadastroHospede.getjTextFieldID().setText(codigo + "");
                this.telaCadastroHospede.getjTextFieldID().setEnabled(false);

                Hospede hospede = new Hospede();
                hospede = service.HospedeService.Carregar(codigo);
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                
                this.telaCadastroHospede.getjTextFieldID().setText(String.valueOf(hospede.getId()));
                this.telaCadastroHospede.getjFormattedTextFieldNome().setText(hospede.getNome());
                this.telaCadastroHospede.getjFormattedTextFieldFone1().setText(hospede.getFone1());
                this.telaCadastroHospede.getjFormattedTextFieldFone2().setText(hospede.getFone2());
                this.telaCadastroHospede.getjTextFieldEmail().setText(hospede.getEmail());
                this.telaCadastroHospede.getjFormattedTextFieldCEP().setText(hospede.getCep());
                this.telaCadastroHospede.getjTextFieldLogradouro().setText(hospede.getLogradouro());
                this.telaCadastroHospede.getjTextFieldBairro().setText(hospede.getBairro());
                this.telaCadastroHospede.getjTextFieldCidade().setText(hospede.getCidade());
                this.telaCadastroHospede.getjTextFieldComplemento().setText(hospede.getComplemento());
                this.telaCadastroHospede.getjFormattedTextFieldDataCadastro().setText(hospede.getDataCadastro());
                this.telaCadastroHospede.getjFormattedTextFieldCPF().setText(hospede.getCpf());
                this.telaCadastroHospede.getjTextFieldRG().setText(hospede.getRg());
                this.telaCadastroHospede.getjTextFieldObservacao().setText(hospede.getObs());
                this.telaCadastroHospede.getjTextFieldRazaoSocial().setText(hospede.getRazaoSocial());
                this.telaCadastroHospede.getjTextFieldCNPJ().setText(hospede.getCnpj());
                this.telaCadastroHospede.getjTextFieldInscricaoEstadual().setText(hospede.getInscricaoEstadual());
                this.telaCadastroHospede.getjTextFieldContato().setText(hospede.getContato());
                for (int i = 0; i < this.telaCadastroHospede.getjComboBoxSexo().getItemCount(); i++) {
                    if(hospede.getSexo().equalsIgnoreCase(this.telaCadastroHospede.getjComboBoxSexo().getItemAt(i))){
                        this.telaCadastroHospede.getjComboBoxSexo().setSelectedIndex(i);
                    }
                }
                this.telaCadastroHospede.getjTextFieldStatus().setText(String.valueOf(hospede.getStatus()));

                this.telaCadastroHospede.getjFormattedTextFieldNome().requestFocus();
            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroHospede.getjButtonSair()) {
            this.telaCadastroHospede.dispose();

        }
    }
}
