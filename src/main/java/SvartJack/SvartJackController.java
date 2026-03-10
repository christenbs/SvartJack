package SvartJack;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SvartJackController {

    private SvartJack svartJack = new SvartJack();

    /*Felter som kan manipuleres */
    @FXML
    private TextField addPlayerTextField;
    @FXML
    private Button addPlayerButton1;
    @FXML
    private Button addPlayerButton2;
    @FXML
    private Button depositButton1;
    @FXML
    private Button depositButton2;

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

        addPlayerButton2.setOpacity(0.0);
        addPlayerTextField.setOpacity(0.0);

        depositButton1.setOpacity(0.0);
        depositButton2.setOpacity(0.0);
        depositNameTextField.setOpacity(0.0);
        depositAmountTextField.setOpacity(0.0);
    }

    /*Funkjsonalitet */
    @FXML
    public void addPlayer1() {
        addPlayerButton1.setOpacity(0.0);
        addPlayerButton2.setOpacity(100.0);
        addPlayerTextField.setOpacity(100.0);
    }

    @FXML
    public void addPlayer() {
        String name = addPlayerTextField.getText();


        if (name.length() < 3) {
            addPlayerTextField.setPromptText("Atleast 3 characters");
            addPlayerTextField.clear();
        }
        else if (svartJack.getPlayers().size() > 4) {
            addPlayerTextField.setPromptText("Maksimum 5 players");
            addPlayerTextField.clear();
        }
        else {

            if (svartJack.getPlayers().size() == 0) {
                depositButton1.setOpacity(100);
            }

            svartJack.add_player(name);
            addPlayerTextField.clear();
            
            labels.get(newPlayerIndex).setText(name);
            balances.get(setBalanceIndex).setText(Integer.toString(svartJack.getPlayers().getLast().getBalance()));
            newPlayerIndex++;
            setBalanceIndex++;
    
            addPlayerTextField.setPromptText("Player name");
            addPlayerTextField.setOpacity(0.0);
            addPlayerButton2.setOpacity(0.0);
            addPlayerButton1.setOpacity(100.0);
        }
    }

    @FXML
    public void deposit1() {
        depositButton1.setOpacity(0.0);
        depositButton2.setOpacity(100.0);
        depositNameTextField.setOpacity(100.0);
        depositAmountTextField.setOpacity(100.0);
    }

    @FXML
    public void deposit() {
        int amount = Integer.valueOf(depositAmountTextField.getText());
        String name = depositNameTextField.getText();

        if (svartJack.getPlayers().stream().noneMatch(c -> c.getName().equals(name))) {
            depositNameTextField.setPromptText("Can not find: " + name);
        }
        else {
            svartJack.getPlayers().stream()
            .forEach(c -> {
                if (c.getName().equals(name)) {
                    c.deposit(amount);
                    int oldIndex = setBalanceIndex;
                    int setBalanceIndex = svartJack.getPlayers().indexOf(c);
                    balances.get(setBalanceIndex).setText(Integer.toString(svartJack.getPlayers().getLast().getBalance()));
                    setBalanceIndex = oldIndex;
    
                    depositButton1.setOpacity(100.0);
                    depositButton2.setOpacity(0.0);
                    depositNameTextField.setOpacity(0.0);
                    depositAmountTextField.setOpacity(0.0);
                    depositNameTextField.setPromptText("Player name");
                }
            });
        }
        depositNameTextField.clear();
        depositAmountTextField.clear();
    }
}