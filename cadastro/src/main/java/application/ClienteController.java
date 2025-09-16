/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author filip
 */
package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Buscador;

public class ClienteController {

    @FXML private TextField txtNome, txtCep, txtRua, txtNumero, txtCidade, txtEstado, txtTelefone;
    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente, Integer> colCodigo;
    @FXML private TableColumn<Cliente, String> colNome, colCidade, colEstado, colTelefone;

    private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
    private Buscador buscador = new Buscador();

    @FXML
    private void initialize() {
        colCodigo.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCodigo()).asObject());
        colNome.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNome()));
        colCidade.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEndereco().getCidade()));
        colEstado.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEndereco().getEstado()));
        colTelefone.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTelefone()));

        tblClientes.setItems(listaClientes);
    }

    @FXML
    private void buscarCep() {
        try {
            Endereco e = buscador.buscar(txtCep.getText());
            txtRua.setText(e.getRua());
            txtCidade.setText(e.getCidade());
            txtEstado.setText(e.getEstado());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void gravarCliente() {
        Endereco e = new Endereco(
                txtCep.getText(),
                txtRua.getText(),
                txtNumero.getText(),
                txtCidade.getText(),
                txtEstado.getText()
        );

        Cliente c = new Cliente(txtNome.getText(), e, txtTelefone.getText());
        listaClientes.add(c);

        limparCampos();
    }

    @FXML
    private void limparCampos() {
        txtNome.clear();
        txtCep.clear();
        txtRua.clear();
        txtNumero.clear();
        txtCidade.clear();
        txtEstado.clear();
        txtTelefone.clear();
    }
}