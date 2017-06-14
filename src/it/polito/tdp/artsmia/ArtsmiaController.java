/**
 * Sample Skeleton for 'Artsmia.fxml' Controller Class
 */

package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Model;
import it.polito.tdp.artsmia.model.Stat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ChoiceBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtFieldStudenti"
    private TextField txtFieldStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleCreaGrafo(ActionEvent event) {
    	
    	if(this.boxAnno.getValue()==null){
    		txtResult.appendText("ERRORE: Inserire anno !");
    		return;
    	}
    		
    	int anno=this.boxAnno.getValue();
    	
    	boolean connesso=model.creaGrafo(anno);
    	if(connesso){
    		txtResult.appendText("Il grafo è connesso !\n");
    	}
    	else
    		txtResult.appendText("Il grafo non è connesso !\n");
    	
    	txtResult.appendText( "La mostra con più opere è: "+ model.trovaMostraMaggiore().toString()+"\n");

    }

    @FXML
    void handleSimula(ActionEvent event) {

    	if(this.boxAnno.getValue()==null){
    		txtResult.appendText("ERRORE: Inserire anno !");
    		return;
    	}
    		
    	int anno=this.boxAnno.getValue();
    	
    	String sn=this.txtFieldStudenti.getText();
    	 if(sn.equals(" ")){
    		 txtResult.appendText("ERRORE: inserire un valore! ");
    		 return;
    	 }
    	 
    	int n;
    	try{
    	n=Integer.parseInt(sn);
    	}catch(NumberFormatException e){
    		txtResult.appendText("ERRORE: Inserire un numero!");
    		return;
    	}
    	
    	
    	for(Stat stemp: model.getClassifica(anno,n)){
    		txtResult.appendText("Studente "+stemp.getStudente()+" "+stemp.getNrOpere()+"\n");
    	}
    		
    		
    		
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtFieldStudenti != null : "fx:id=\"txtFieldStudenti\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
		this.boxAnno.getItems().addAll(model.getAnniBegin());
		
		
	}
}
