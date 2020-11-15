package ui;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.CYK;
import model.Grammar;
public class MainController {

    @FXML
    private TextField variable;

    @FXML
    private TextField terminals;

    @FXML
    private TextField textW;

    @FXML
    private TextField initialVariable;
    
    private Grammar grammar;
    
    @FXML
    void initialize() {
    	Map<String, String> gram = new HashMap<String,String>();
    	grammar = new Grammar(gram, initialVariable.getText());
    }
    /**
     * <b>Description:</b>
     * This method allows to build a new instance of a grammar
     * 
     * @param event, the click on the button
     * <b>post:</b>  a new instance of Grammar has been created
     */
  
    @FXML
    void build(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION, "now you can specify the productions of the new Grammar");
    	Map<String, String> gram = new HashMap<String,String>();
    	grammar = new Grammar(gram, initialVariable.getText());
    	alert.show();
    	textW.clear();
    	initialVariable.clear();
    }
    
    /**
     * <b>Description:</b>
     * This method allows to add a production to grammar
     * 
     * <b>pre:</b> grammar must not be null
     * @param event, the click on the button
     * <b>post:</b>  a new production has been added to grammar
     */
    @FXML
    void addProduction(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
    	String var = variable.getText();
    	if(var.isEmpty()) {
    		alert.setContentText("Varibale text field can not be empty");
    		alert.show();
    	}
    	else if(terminals.getText().isEmpty()) {
    		alert.setContentText("varibales/terminals field can not be empty");
    		alert.show();
    	}else {
	    	String[] term = terminals.getText().split(",");
	    	for(String s : term) {
	    		if(grammar.getGram().containsKey(s)) {
	    			grammar.getGram().replace(s,grammar.getGram().get(s) , grammar.getGram().get(s)+","+var);
	    		}
	    		else {
	    			grammar.getGram().put(s, var);
	    		}
	    		alert.setAlertType(alert.getAlertType().CONFIRMATION);
	    		alert.setContentText("production has been added successfully");
	    		alert.show();
	    		variable.clear();
	    		terminals.clear();
	    	}
    	}
    			
    }
    /**
     * <b>Description:</b>
     * This method allows to check if a string W belongs to grammar G created
     * <b>pre:</b> grammar must not be null
     * @param event, the click on the button
     */
    @FXML
    void check(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
    	if(textW.getText().isEmpty()) {
    		alert.setContentText("text W field can not be empty");
    		alert.show();
    		
    	}
    	else if(initialVariable.getText().isEmpty()) {
    		alert.setContentText("initial varibale field can not be empty");
    		alert.show();
    	}else {
    		grammar.setInitial_variable(initialVariable.getText());
	    	CYK cyk = new CYK(textW.getText(),grammar);
	    	boolean check = cyk.check(grammar);
	    	alert.setAlertType(Alert.AlertType.CONFIRMATION);
	    	if(check) {
	    		alert.setContentText("the text string W belongs to the grammar G");
	    		alert.show();
	    	}
	    	else {
	    		alert.setContentText("the text string W DOES NOT belong to the grammar G");
	    		alert.show();
	    	}
    	}
    }

}
