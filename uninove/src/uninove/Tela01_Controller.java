package uninove;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Tela01_Controller implements Initializable {
    
    @FXML
    private AnchorPane main_frame;
    @FXML
    private Button cmdsalvar;
    @FXML
    private Label lblra;
    @FXML
    private TextField txtra;
    @FXML
    private Label lblnome;
    @FXML
    private TextField txtnome;
    @FXML
    private Label lblcurso;
    @FXML
    private TextField txtcurso;
    @FXML
    private Button cmdpesquisar;
    @FXML
    private Label lblrodape;
    @FXML
    private Label lblmsg;
    @FXML
    private Label lblmsgbd;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Tela de Cadastro");
        Main.stage.setResizable(false);
    }    

    @FXML
    private void clicarsalvar(ActionEvent event) {
        if (validar_ra(txtra.getText()) == true && 
            validar_nome(txtnome.getText()) == true && 
            validar_curso(txtcurso.getText()) == true){
            Bd.inserir(txtra.getText(), txtnome.getText(), txtcurso.getText());
            limpar_campos();
        }else{
        System.out.println("As Informações estão incorretas");
        }
    }
    
    @FXML
    private void clicarpesquisar(ActionEvent event) throws IOException {
        AnchorPane bd_frame = FXMLLoader.load(getClass().getResource("tela02.fxml"));
        main_frame.getChildren().setAll(bd_frame);
    }
    
    private void limpar_campos(){
        txtra.clear();
        txtnome.clear();
        txtcurso.clear();
        lblmsg.setText("");
    }
    
    private boolean validar_ra(String ra){
        if(ra.length() != 9){
            lblmsg.setText("O campo do RA deve possuir 9 números");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validar_nome(String nome){
        if(nome.length() == 0){
            lblmsg.setText("É obrigatório o preenchimento do campo NOME");
            return false;
        }else if(nome.length()> 100){
            lblmsg.setText("O campo suporta no máximo 100 caracteres");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validar_curso(String curso){
        if(curso.length() > 100){
            lblmsg.setText("O campo suporta no máximo 100 caracteres");
            return false;
        }else{
            return true;
        }
    }

    @FXML
    private void lblra(MouseEvent event){
    }
    @FXML
    private void txtra(ActionEvent event) {
    }
    @FXML
    private void lblnome(MouseEvent event) {
    }
    @FXML
    private void txtnome(ActionEvent event) {
    }
    @FXML
    private void lblcurso(MouseEvent event) {
    }
    @FXML
    private void txtcurso(ActionEvent event) {
    }
    @FXML
    private void lblrodape(MouseEvent event) {
    }
}
