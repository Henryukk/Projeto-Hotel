package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

import model.Modelo;
import model.Veiculo;
import view.TelaBuscaVeiculo;
import view.TelaCadastroVeiculo;

public class ControllerCadVeiculo implements ActionListener {

    private final TelaCadastroVeiculo telaCadastroVeiculo;
    public static int codigo;

    public ControllerCadVeiculo(TelaCadastroVeiculo telaCadastroVeiculo) {
        this.telaCadastroVeiculo = telaCadastroVeiculo;

        this.telaCadastroVeiculo.getjButtonNovo().addActionListener(this);
        this.telaCadastroVeiculo.getjButtonGravar().addActionListener(this);
        this.telaCadastroVeiculo.getjButtonCancelar().addActionListener(this);
        this.telaCadastroVeiculo.getjButtonBuscar().addActionListener(this);
        this.telaCadastroVeiculo.getjButtonSair().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroVeiculo.getjPanel1(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroVeiculo.getjPanel2(), false);

        // preencher a marca ao escolher o modelo
        
        this.telaCadastroVeiculo.getjComboBoxModelo().addActionListener(e -> {
            int idx = this.telaCadastroVeiculo.getjComboBoxModelo().getSelectedIndex();
            if (idx >= 0) {
                String descModelo = this.telaCadastroVeiculo.getjComboBoxModelo().getItemAt(idx);
                List<Modelo> res = service.ModeloService.Carregar("modelo.descricao", descModelo);
                if (!res.isEmpty() && res.get(0).getMarca() != null) {
                    this.telaCadastroVeiculo.getjTextFieldMarca().setText(res.get(0).getMarca().getDescricao());
                } else {
                    this.telaCadastroVeiculo.getjTextFieldMarca().setText("");
                }
            } else {
                this.telaCadastroVeiculo.getjTextFieldMarca().setText("");
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        if (evento.getSource() == this.telaCadastroVeiculo.getjButtonNovo()) {
            
            utilities.Utilities.ativaDesativa(this.telaCadastroVeiculo.getjPanel1(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroVeiculo.getjPanel2(), true);
            this.telaCadastroVeiculo.getjTextFieldIDVeiculo().setEnabled(false);

            //encher a comboBox de modelo
            
            encherComboBoxModelo();
            this.telaCadastroVeiculo.getjTextFieldMarca().setText("");

            // Foco
            this.telaCadastroVeiculo.getjFormattedTextFieldPlaca().requestFocus();

        //_____________________________________________________________________________________________________________
            
        } else if (evento.getSource() == this.telaCadastroVeiculo.getjButtonGravar()) {
            
            if (this.telaCadastroVeiculo.getjFormattedTextFieldPlaca().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo Placa é obrigatório.");
                this.telaCadastroVeiculo.getjFormattedTextFieldPlaca().requestFocus();
                return;
            }
            if (this.telaCadastroVeiculo.getjComboBoxModelo().getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um Modelo.");
                this.telaCadastroVeiculo.getjComboBoxModelo().requestFocus();
                return;
            }

            Veiculo veiculo = new Veiculo();
            veiculo.setPlaca(this.telaCadastroVeiculo.getjFormattedTextFieldPlaca().getText());
            veiculo.setCor(this.telaCadastroVeiculo.getjComboBoxCorDoVeiculo().getSelectedItem().toString());

            String st = this.telaCadastroVeiculo.getjTextFieldStatus().getText().trim();
            veiculo.setStatus(st.isEmpty() ? 'A' : st.charAt(0));

            // Pega o Modelo pela descrição selecionada
            
            String descModeloSel = this.telaCadastroVeiculo.getjComboBoxModelo()
                    .getItemAt(this.telaCadastroVeiculo.getjComboBoxModelo().getSelectedIndex());
            List<Modelo> modelos = service.ModeloService.Carregar("modelo.descricao", descModeloSel);
            if (modelos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Modelo selecionado não foi encontrado.");
                return;
            }
            veiculo.setModelo(modelos.get(0));

            if (this.telaCadastroVeiculo.getjTextFieldIDVeiculo().getText().trim().isEmpty()) {
                veiculo.setStatus('A'); 
                service.VeiculoService.Criar(veiculo);
            } else {
                veiculo.setId(Integer.parseInt(this.telaCadastroVeiculo.getjTextFieldIDVeiculo().getText()));
                service.VeiculoService.Atualizar(veiculo);
            }

            // Reset
            this.telaCadastroVeiculo.getjComboBoxModelo().removeAllItems();
            utilities.Utilities.ativaDesativa(this.telaCadastroVeiculo.getjPanel1(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroVeiculo.getjPanel2(), false);

        } else if (evento.getSource() == this.telaCadastroVeiculo.getjButtonCancelar()) {
            // Cancelar edição
            this.telaCadastroVeiculo.getjComboBoxModelo().removeAllItems();
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

                this.telaCadastroVeiculo.getjTextFieldIDVeiculo().setText(String.valueOf(codigo));
                this.telaCadastroVeiculo.getjTextFieldIDVeiculo().setEnabled(false);
                
        //_____________________________________________________________________________________________________________
            
                Veiculo veiculo = service.VeiculoService.Carregar(codigo);

                this.telaCadastroVeiculo.getjFormattedTextFieldPlaca().setText(veiculo.getPlaca());
                selecionarCor(veiculo.getCor());
                this.telaCadastroVeiculo.getjTextFieldStatus().setText(String.valueOf(veiculo.getStatus()));

                // enche a comboBox de modelo com as opcoes
                
                encherComboBoxModelo();

                String descDoModelo = (veiculo.getModelo() != null) ? veiculo.getModelo().getDescricao() : "";
                selecionarModeloNaCombo(descDoModelo);

                // marca do modelo selecionado
                
                this.telaCadastroVeiculo.getjTextFieldMarca().setText(
                        (veiculo.getModelo() != null && veiculo.getModelo().getMarca() != null)
                                ? veiculo.getModelo().getMarca().getDescricao()
                                : ""
                );

                this.telaCadastroVeiculo.getjFormattedTextFieldPlaca().requestFocus();
            }
            
        //_____________________________________________________________________________________________________________
            
        } else if (evento.getSource() == this.telaCadastroVeiculo.getjButtonSair()) {
            this.telaCadastroVeiculo.dispose();
        }
    }
    

    private void encherComboBoxModelo() {
        this.telaCadastroVeiculo.getjComboBoxModelo().removeAllItems();
        List<Modelo> lista = service.ModeloService.Carregar("modelo.descricao", "");
        for (Modelo m : lista) {
            this.telaCadastroVeiculo.getjComboBoxModelo().addItem(m.getDescricao());
        }
        this.telaCadastroVeiculo.getjComboBoxModelo().setSelectedIndex(-1);
    }

    private void selecionarModeloNaCombo(String descricaoModelo) {
        if (descricaoModelo == null) descricaoModelo = "";
        for (int i = 0; i < this.telaCadastroVeiculo.getjComboBoxModelo().getItemCount(); i++) {
            if (descricaoModelo.equals(this.telaCadastroVeiculo.getjComboBoxModelo().getItemAt(i))) {
                this.telaCadastroVeiculo.getjComboBoxModelo().setSelectedIndex(i);
                return;
            }
        }
        this.telaCadastroVeiculo.getjComboBoxModelo().setSelectedIndex(-1);
    }

    private void selecionarCor(String cor) {
        if (cor == null) return;
        for (int i = 0; i < this.telaCadastroVeiculo.getjComboBoxCorDoVeiculo().getItemCount(); i++) {
            if (cor.equalsIgnoreCase(this.telaCadastroVeiculo.getjComboBoxCorDoVeiculo().getItemAt(i))) {
                this.telaCadastroVeiculo.getjComboBoxCorDoVeiculo().setSelectedIndex(i);
                break;
            }
        }
    }
}