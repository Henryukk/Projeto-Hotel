package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.DAO.ModeloDAO;
import model.Marca;
import model.Modelo;
import model.Modelo;
import view.TelaBuscaModelo;
import view.TelaCadastroModelo;

public class ControllerCadModelo implements ActionListener {

    TelaCadastroModelo telaCadastroModelo;

    public static int codigo;

    public ControllerCadModelo(TelaCadastroModelo telaCadastroModelo) {
        this.telaCadastroModelo = telaCadastroModelo;
        this.telaCadastroModelo.getjButtonNovo().addActionListener(this);
        this.telaCadastroModelo.getjButtonSair().addActionListener(this);
        this.telaCadastroModelo.getjButtonGravar().addActionListener(this);
        this.telaCadastroModelo.getjButtonCancelar().addActionListener(this);
        this.telaCadastroModelo.getjButtonBuscar().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroModelo.getjPanel1(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroModelo.getjPanel2(), false);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.telaCadastroModelo.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroModelo.getjPanel1(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroModelo.getjPanel2(), true);
            this.telaCadastroModelo.getjTextFieldID().setEnabled(false);
            
             ArrayList<Marca> opcoesMarca = (ArrayList<Marca>) service.MarcaService.Carregar("descricao", "%%");
            for(Marca marca : opcoesMarca){
                this.telaCadastroModelo.getjComboBoxMarca().addItem(marca.getDescricao());
            }
            this.telaCadastroModelo.getjComboBoxMarca().setSelectedIndex(-1);


            this.telaCadastroModelo.getjTextFieldDescricao().requestFocus();
        //_____________________________________________________________________________________________________________
        
       } else if (evento.getSource() == this.telaCadastroModelo.getjButtonGravar()) {

    if (this.telaCadastroModelo.getjTextFieldDescricao().getText().trim().equalsIgnoreCase("")) {
        JOptionPane.showMessageDialog(null, "O atributo Descrição é obrigatório...");
        this.telaCadastroModelo.getjTextFieldDescricao().requestFocus();
        return;
    }

    if (this.telaCadastroModelo.getjComboBoxMarca().getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(null, "O atributo Marca é obrigatório...");
        this.telaCadastroModelo.getjComboBoxMarca().requestFocus();
        return;
    }

    int itemSelect = this.telaCadastroModelo.getjComboBoxMarca().getSelectedIndex();

    Modelo modelo = new Modelo();
    modelo.setDescricao(this.telaCadastroModelo.getjTextFieldDescricao().getText());
    modelo.setStatus(this.telaCadastroModelo.getjTextFieldStatus().getText().trim().charAt(0));

    // 🔹 Pega a marca selecionada
    modelo.setMarca(
        service.MarcaService.Carregar(
            "descricao",
            this.telaCadastroModelo.getjComboBoxMarca().getItemAt(itemSelect)
        ).get(0) // ou .getFirst(), dependendo da versão do seu Java
    );

    if (this.telaCadastroModelo.getjTextFieldID().getText().trim().equalsIgnoreCase("")) {
        modelo.setStatus('A');
        service.ModeloService.Criar(modelo);
    } else {
        modelo.setId(Integer.parseInt(this.telaCadastroModelo.getjTextFieldID().getText()));
        service.ModeloService.Atualizar(modelo);
    }

    // 🔹 Limpa a combo e reseta a tela
    this.telaCadastroModelo.getjComboBoxMarca().removeAllItems();
    utilities.Utilities.ativaDesativa(this.telaCadastroModelo.getjPanel1(), true);
    utilities.Utilities.limpaComponentes(this.telaCadastroModelo.getjPanel2(), false);

        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroModelo.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroModelo.getjPanel1(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroModelo.getjPanel2(), false);
            
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroModelo.getjButtonBuscar()) {

            codigo = 0;

            TelaBuscaModelo telaBuscasModelo = new TelaBuscaModelo(null, true);
            ControllerBuscaModelo controllerBuscaModelo = new ControllerBuscaModelo(telaBuscasModelo);
            telaBuscasModelo.setVisible(true);

            if (codigo != 0) {
                utilities.Utilities.ativaDesativa(this.telaCadastroModelo.getjPanel1(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroModelo.getjPanel2(), true);

                this.telaCadastroModelo.getjTextFieldID().setText(codigo + "");
                this.telaCadastroModelo.getjTextFieldID().setEnabled(false);

                Modelo modelo = new Modelo();
                modelo = service.ModeloService.Carregar(codigo);
                //COLOCAR TODOS OS ATRIBUTOS DA CLASSE CADASTRO HOSPEDE
                
                this.telaCadastroModelo.getjTextFieldID().setText(String.valueOf(modelo.getId()));
                this.telaCadastroModelo.getjTextFieldDescricao().setText(modelo.getDescricao());
                this.telaCadastroModelo.getjTextFieldStatus().setText(String.valueOf(modelo.getStatus()));

                this.telaCadastroModelo.getjTextFieldDescricao().requestFocus();
            }
        //_____________________________________________________________________________________________________________
        
        } else if (evento.getSource() == this.telaCadastroModelo.getjButtonSair()) {
            this.telaCadastroModelo.dispose();

        }
    }
}
