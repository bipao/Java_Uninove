package uninove;

import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleFloatProperty;

public class ElementosTabela {
    private final SimpleStringProperty etra = new SimpleStringProperty();
    private final SimpleStringProperty etnome = new SimpleStringProperty();
    private final SimpleStringProperty etcurso = new SimpleStringProperty();
    
    public String getRa(){
        return etra.get();
    }
    
    public String getNome(){
        return etnome.get();
    }
    
    public String getCurso(){
        return etcurso.get();
    }
    
    public void setRa(String ra){
        this.etra.set(ra);
    }
    
    public void setNome(String nome){
        this.etnome.set(nome);
    }
    
    public void setCurso(String curso){
        this.etcurso.set(curso);
    }
}
