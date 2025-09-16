package br.edu.progiitrabalho.controller;

import br.edu.progiitrabalho.model.Cliente;
import br.edu.progiitrabalho.model.Endereco;
import br.edu.progiitrabalho.service.Buscador;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class CadastroController {

    @FXML private TextField txtNome;
    @FXML private TextField txtTelefone;
    @FXML private TextField txtCep;
    @FXML private TextField txtRua;
    @FXML private TextField txtNumero;
    @FXML private TextField txtCidade;
    @FXML private TextField txtEstado;

    @FXML private TableView<Cliente> tabelaClientes;
    @FXML private TableColumn<Cliente, Integer> colCodigo;
    @FXML private TableColumn<Cliente, String> colNome;
    @FXML private TableColumn<Cliente, String> colTelefone;
    @FXML private TableColumn<Cliente, String> colCidade;
    @FXML private TableColumn<Cliente, String> colEstado;

    private Buscador buscador;
    private List<Cliente> listaClientes;

    @FXML
    private void initialize() {
        buscador = new Buscador();
        listaClientes = new ArrayList<>();

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        colCidade.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getEndereco() == null ? "" : c.getValue().getEndereco().getCidade()));
        colEstado.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getEndereco() == null ? "" : c.getValue().getEndereco().getEstado()));
    }

    @FXML
    private void onBuscarCep() {
        try {
            Endereco e = buscador.buscar(txtCep.getText().trim());
            txtRua.setText(e.getRua());
            txtCidade.setText(e.getCidade());
            txtEstado.setText(e.getEstado());
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Erro ao buscar CEP: " + ex.getMessage()).showAndWait();
        }
    }

    @FXML
    private void onSalvar() {
        if (txtNome.getText().isBlank()) { warn("Informe o nome"); return; }
        if (txtTelefone.getText().isBlank()) { warn("Informe o telefone"); return; }
        if (txtCep.getText().isBlank()) { warn("Informe o CEP"); return; }
        if (txtRua.getText().isBlank() || txtCidade.getText().isBlank() || txtEstado.getText().isBlank()) {
            warn("Busque o CEP antes de salvar"); return;
        }

        Endereco end = new Endereco(
                txtCep.getText().trim(),
                txtRua.getText().trim(),
                txtNumero.getText().trim(),
                txtCidade.getText().trim(),
                txtEstado.getText().trim()
        );

        Cliente c = new Cliente(txtNome.getText().trim(), end, txtTelefone.getText().trim());
        listaClientes.add(c);
        tabelaClientes.setItems(FXCollections.observableArrayList(listaClientes));
        limpar();
    }

    private void warn(String m) { new Alert(Alert.AlertType.WARNING, m).showAndWait(); }

    private void limpar() {
        txtNome.clear();
        txtTelefone.clear();
        txtCep.clear();
        txtRua.clear();
        txtNumero.clear();
        txtCidade.clear();
        txtEstado.clear();
    }
}
