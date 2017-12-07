package uninove;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

public class Tela02_Controller implements Initializable {

    public static ObservableList <ElementosTabela> obl;
    
    @FXML
    private AnchorPane bd_frame;
    @FXML
    private TableView<ElementosTabela> tvbd;
    @FXML
    private TableColumn<ElementosTabela, String> tcra;
    @FXML
    private TableColumn<ElementosTabela, String> tcnome;
    @FXML
    private TableColumn<ElementosTabela, String> tccurso;
    @FXML
    private Button cmdalterar;
    @FXML
    private Button cmdsair;
    @FXML
    private Button cmdapagar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obl = observableArrayList();
        
        tcra.setCellValueFactory(new PropertyValueFactory<>("ra"));
        tcnome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tccurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        
        refresh();
        
        tcra.setCellFactory(TextFieldTableCell.forTableColumn());
        tcra.setOnEditCommit((TableColumn.CellEditEvent<ElementosTabela, String> r) ->{
            ((ElementosTabela) r.getTableView().getItems().get(
            r.getTablePosition().getRow())).setRa(r.getNewValue());
        });
        
        tcnome.setCellFactory(TextFieldTableCell.forTableColumn());
        tcnome.setOnEditCommit((TableColumn.CellEditEvent<ElementosTabela, String> n) ->{
            ((ElementosTabela) n.getTableView().getItems().get(
            n.getTablePosition().getRow())).setNome(n.getNewValue());
        });
        
        tccurso.setCellFactory(TextFieldTableCell.forTableColumn());
        tccurso.setOnEditCommit((TableColumn.CellEditEvent<ElementosTabela, String> c) ->{
            ((ElementosTabela) c.getTableView().getItems().get(
            c.getTablePosition().getRow())).setCurso(c.getNewValue());
        });
    }    

    @FXML
    private void clicaralterar(ActionEvent event) {
        alterar();
    }

    @FXML
    private void clicarsair(ActionEvent event) throws IOException {
        AnchorPane main_frame = FXMLLoader.load(getClass().getResource("tela01.fxml"));
        bd_frame.getChildren().setAll(main_frame);
    }
    
    @FXML
    private void clicarapagar(ActionEvent event) {
        Bd.apagar(tvbd.getSelectionModel().getSelectedItem().getRa());
        refresh();
    }
    
    private void refresh(){
        obl.clear();
        Bd.pesquisar();
        tvbd.setItems(obl);
    }
    
    void alterar(){
        Bd.atualizar(
        tvbd.getSelectionModel().getSelectedItem().getNome(),
        tvbd.getSelectionModel().getSelectedItem().getCurso(),
        tvbd.getSelectionModel().getSelectedItem().getRa()
        );
    }
}
