package SvartJack;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SvartJackController {

    private SvartJack svartJack = new SvartJack();

    /*Felter som kan manipuleres */
    @FXML
    private TextField addPlayerTextField;

    @FXML
    private TextField depositNameTextField;
    @FXML
    private TextField depositAmountTextField;

    /*Sette navn på spillere */
    @FXML
    private Label player1Label;
    @FXML
    private Label player2Label;
    @FXML
    private Label player3Label;
    @FXML
    private Label player4Label;
    @FXML
    private Label player5Label;

    private ArrayList<Label> labels;
    private int newPlayerIndex = 0;  
    
    /*Sette balance på spillere */
    @FXML
    private Label player1Balance;
    @FXML
    private Label player2Balance;
    @FXML
    private Label player3Balance;
    @FXML
    private Label player4Balance;
    @FXML
    private Label player5Balance;
    
    private ArrayList<Label> balances;
    private int setBalanceIndex = 0;  

    /* Deposits */
    
    @FXML
    public void initialize() {
        labels = new ArrayList<>(Arrays.asList(player1Label, player2Label, player3Label, player4Label, player5Label));
        balances = new ArrayList<>(Arrays.asList(player1Balance, player2Balance, player3Balance, player4Balance, player5Balance));
    }

    /*Funkjsonalitet */
    @FXML
    public void addPlayer() {
        String name = addPlayerTextField.getText();


        if (name.length() < 3) {
            addPlayerTextField.setPromptText("Atleast 3 characters");
            addPlayerTextField.clear();
        }
        if (svartJack.getPlayers().size() > 4) {
            addPlayerTextField.setPromptText("Maksimum 5 players");
            addPlayerTextField.clear();
        }
        else {
            svartJack.add_player(name);
            addPlayerTextField.clear();
            
            labels.get(newPlayerIndex).setText(name);
            balances.get(setBalanceIndex).setText(Integer.toString(svartJack.getPlayers().getLast().getBalance()));
            newPlayerIndex++;
            setBalanceIndex++;
    
            addPlayerTextField.setPromptText("Player name");
        }
    }

    @FXML
    public void deposit() {
        int amount = Integer.valueOf(depositAmountTextField.getText());
        String name = depositNameTextField.getText();

        svartJack.getPlayers().stream()
        .forEach(c -> {
            if (c.getName().equals(name)) {
                c.deposit(amount);
                int oldIndex = setBalanceIndex;
                int setBalanceIndex = svartJack.getPlayers().indexOf(c);
                balances.get(setBalanceIndex).setText(Integer.toString(svartJack.getPlayers().getLast().getBalance()));
                setBalanceIndex = oldIndex;
            }
        });
    }
}