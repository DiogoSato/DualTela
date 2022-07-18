
package dualtela;

import dao.FuncionarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


public class DualTelaController implements Initializable {
    
    private ObservableList<String> tipoContrato = FXCollections.observableArrayList("Efetivo","Temporário","Terceirizado");
    
    @FXML ComboBox cbBoxTipo;
    
    @FXML private TextField dgtID;
    @FXML private TextField dgtNome;
    @FXML private TextField dgtCargo;
    @FXML private TextField dgtSalario;
    @FXML private TextField dgtTelefone;
    
    @FXML private Button btEnviar;
    @FXML private Button btSair;
    @FXML private Button btLimpar;
    @FXML private Button btPdf;
    
    @FXML private TextField dgtPesquisa;
    @FXML private Button btExcluir;
    @FXML private Button btProcurar;
    
    @FXML TableView<Funcionario> TabelaID;
    @FXML TableColumn<Funcionario,Integer> colunaID;
    @FXML TableColumn<Funcionario,String> colunaNome;
    @FXML TableColumn<Funcionario,String> colunaCargo;
    @FXML TableColumn<Funcionario,String> colunaTipo;
    @FXML TableColumn<Funcionario,String> colunaData;
    @FXML TableColumn<Funcionario,String> colunaSalario;
    @FXML TableColumn<Funcionario,Integer> colunaTelefone;
    
    @FXML DatePicker nascimento;
    
    private ObservableList<Funcionario> funcionarios;
    private static Funcionario selecionado;
    
    @FXML
    private void deletaFuncionario(ActionEvent event){
        if(DualTelaController.selecionado != null){
            FuncionarioDAO dao = new FuncionarioDAO();
            dao.deletaFuncionario(DualTelaController.selecionado);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Funcionário deletado com sucesso");
            a.showAndWait();
            preencheTabela();
        }else{
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Por favor, selecione um funcionário");
        a.showAndWait();
        }
    }
    @FXML
    private void ClicouCAD(ActionEvent event){
        if(dgtNome.getText().equals(" ") || dgtCargo.getText().equals("") || nascimento.getValue() == null
           || dgtSalario.getText().equals(" ") || dgtTelefone.getText().equals("") || cbBoxTipo.getValue() == null){
                Alert c = new Alert(Alert.AlertType.WARNING);
                c.setTitle("ATENÇÃO");
                c.setHeaderText("Campos vazios");
                c.setContentText("Por favor, preencha os campos");
                c.showAndWait();
        }else{
            try{
                Funcionario fun = new Funcionario();
                fun.setNome(dgtNome.getText());
                fun.setCargo(dgtCargo.getText());
                fun.setSalario(parseDouble(dgtSalario.getText()));
                fun.setData(nascimento.getValue().toString());
                fun.setTipo(cbBoxTipo.getValue().toString());
                fun.setTelefone(parseInt(dgtTelefone.getText()));
                insereFuncionario(fun);
                
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("VERIFICAÇÃO DE CADASTRO");
                a.setHeaderText("Campos digistados corretamente");
                a.setContentText("OK, Cadastro Realizado");
                a.showAndWait();
        }catch(Exception e){
            System.out.println("Erro ao Cadastrar " + e.getMessage());
        }
        
            private void clearForm(){
                dgtID.clear();
                dgtNome.clear();
                dgtCargo.clear();
                dgtSalario.clear();
                dgtTelefone.clear();
                nascimento.setValue(null);
                cbBoxTipo.setValue(null);
            }
            public void preencheTabela(){
                dgtID.setCellValueFactory(new PropertyValueFactory("idFunc"));
                dgtNome.setCellValueFactory(new PropertyValueFactory("nome"));
                dgtCargo.setCellValueFactory(new PropertyValueFactory("cargo"));
                dgtSalario.setCellValueFactory(new PropertyValueFactory("salario"));
                dgtTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
                nascimento.setCellValueFactory(new PropertyValueFactory("data"));
                cbBoxTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
                
                FuncionarioDAO dao = new FuncionarioDAO();
                
            }
    }
    
    @FXML
    private void hundleButtonAction(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencheTabela();
        cbBoxTipo.setItems(tipoContrato);
    }    
    
}
