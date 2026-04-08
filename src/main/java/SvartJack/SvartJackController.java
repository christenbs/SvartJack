package SvartJack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.nio.file.Paths;

public class SvartJackController {

    private SvartJack svartJack = new SvartJack();

    private final CSVManager CSVManager = new CSVManager(
        Paths.get("/Users/christenstaib/Desktop/Objektprosjekt/src/main/java/SvartJack/data.csv")
    );

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
    private Button placeBetsButton;
    @FXML
    private Button resetButton;
    
    @FXML
    private ImageView dealerPointer;
    
    @FXML
    private Button betButton;
    
    @FXML
    private TextField depositNameTextField;
    @FXML
    private TextField depositAmountTextField;
    
    @FXML
    private TextField betAmountTextField;
    
    @FXML
    private Button leaveTable1;
    @FXML
    private Button leaveTable;
    @FXML
    private TextField leaveTableName;
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

    private ArrayList<Label> nameLabels;
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
    
    private ArrayList<Label> balanceLabels;
    private int setBalanceIndex = 0;  

    /*Sette bets */
    @FXML
    private Label player1ActiveBet;
    @FXML
    private Label player2ActiveBet;
    @FXML
    private Label player3ActiveBet;
    @FXML
    private Label player4ActiveBet;
    @FXML
    private Label player5ActiveBet;
    
    private ArrayList<Label> betLabels;
    private int betLabelIndex;  
    
    /* Scores */
    
    @FXML
    private Label player1Score;
    @FXML
    private Label player2Score;
    @FXML
    private Label player3Score;
    @FXML
    private Label player4Score;
    @FXML
    private Label player5Score;
    @FXML
    private Label dealerScore;
    
    private ArrayList<Label> scoreLabels;

    /* Variables for hit functionality */

    private int playerHitIndex = 0;

    /* Variables for card view*/

    @FXML
    private Pane player1Cards;
    @FXML
    private Pane player2Cards;
    @FXML
    private Pane player3Cards;
    @FXML
    private Pane player4Cards;
    @FXML
    private Pane player5Cards;
    @FXML
    private Pane dealerCards;

    private ArrayList<Pane> playerCardsPane;

    @FXML
    public void initialize() {
        this.nameLabels = new ArrayList<>(Arrays.asList(player1Label, player2Label, player3Label, player4Label, player5Label));
        this.balanceLabels = new ArrayList<>(Arrays.asList(player1Balance, player2Balance, player3Balance, player4Balance, player5Balance));
        this.betLabels = new ArrayList<>(Arrays.asList(player1ActiveBet, player2ActiveBet, player3ActiveBet, player4ActiveBet, player5ActiveBet));
        this.scoreLabels = new ArrayList<>(Arrays.asList(player1Score, player2Score, player3Score, player4Score, player5Score));
        this.playerCardsPane = new ArrayList<>(Arrays.asList(player1Cards, player2Cards, player3Cards, player4Cards, player5Cards));

        addPlayerButton2.setVisible(false);
        addPlayerButton2.setDisable(true);
        addPlayerTextField.setVisible(false);
        addPlayerTextField.setDisable(true);

        depositButton1.setVisible(false);
        depositButton1.setDisable(true);
        depositButton2.setVisible(false);
        depositButton2.setDisable(true);
        depositNameTextField.setVisible(false);
        depositNameTextField.setDisable(true);
        depositAmountTextField.setVisible(false);
        depositAmountTextField.setDisable(true);
        
        betButton.setVisible(false);
        betButton.setDisable(true);
        betAmountTextField.setVisible(false);
        betAmountTextField.setDisable(true);

        dealerScore.setVisible(false);
        dealerPointer.setVisible(false);

        resetButton.setVisible(false);
        resetButton.setDisable(true);

        leaveTable1.setVisible(false);
        leaveTable1.setDisable(true);
        leaveTable.setVisible(false);
        leaveTable.setDisable(true);
        leaveTableName.setVisible(false);
        leaveTableName.setDisable(true);

        if (svartJack.getPlayers().size() > 0) {
            depositButton1.setVisible(true);
            depositButton1.setDisable(false);

            leaveTable1.setVisible(true);
            leaveTable1.setDisable(false);
        }
    }

    @FXML
    public void addPlayer1() {
        addPlayerButton1.setVisible(false);
        addPlayerButton1.setDisable(true);
        addPlayerButton2.setVisible(true);
        addPlayerButton2.setDisable(false);
        addPlayerTextField.setVisible(true);
        addPlayerTextField.setDisable(false);
    }

    @FXML
    public void addPlayer() {
        String name = addPlayerTextField.getText().toLowerCase();

        OptionalInt optionalIndex = java.util.stream.IntStream.range(0, nameLabels.size())
                .filter(i -> nameLabels.get(i).getText().isEmpty())
                .findFirst();

        if (!optionalIndex.isPresent()) {
            addPlayerTextField.setPromptText("No free slots");
            addPlayerTextField.clear();
            return;
        }

        this.newPlayerIndex = optionalIndex.getAsInt();
        this.setBalanceIndex = newPlayerIndex;

        if (name.length() < 3) {
            addPlayerTextField.setPromptText("At least 3 characters");
            addPlayerTextField.clear();
            return;
        }
        if (svartJack.getPlayers().size() > 4) {
            addPlayerTextField.setPromptText("Maximum 5 players");
            addPlayerTextField.clear();
            return;
        }
        if (svartJack.getPlayerNames().contains(name)) {
            addPlayerTextField.setPromptText("Name already taken");
            addPlayerTextField.clear();
            return;
        }

        Player newPlayer = new Player(name);
        if (CSVManager.getAllNames().contains(name)) {
            newPlayer.deposit(CSVManager.getBalance(name));
        }
        svartJack.add_player(newPlayer);

        nameLabels.get(newPlayerIndex).setText(name);
        balanceLabels.get(setBalanceIndex).setText(Integer.toString(newPlayer.getBalance()));

        if (svartJack.getPlayers().size() == 1) {
            depositButton1.setVisible(true);
            depositButton1.setDisable(false);
            leaveTable1.setVisible(true);
            leaveTable1.setDisable(false);
        }

        addPlayerTextField.clear();
        addPlayerTextField.setPromptText("Player name");
        addPlayerTextField.setVisible(false);
        addPlayerTextField.setDisable(true);
        addPlayerButton2.setVisible(false);
        addPlayerButton2.setDisable(true);
        addPlayerButton1.setVisible(true);
        addPlayerButton1.setDisable(false);
    }

    @FXML
    public void deposit1() {
        depositButton1.setVisible(false);
        depositButton1.setDisable(true);
        depositButton2.setVisible(true);
        depositButton2.setDisable(false);
        depositNameTextField.setVisible(true);
        depositNameTextField.setDisable(false);
        depositAmountTextField.setVisible(true);
        depositAmountTextField.setDisable(false);
    }

    @FXML
    public void deposit() {
        int amount;
        String name = depositNameTextField.getText().toLowerCase();

        try {
            amount = Integer.parseInt(depositAmountTextField.getText());
        } catch (NumberFormatException e) {
            depositAmountTextField.setPromptText("Enter a number");
            depositAmountTextField.clear();
            return;
        }

        if (amount <= 0) {
            depositAmountTextField.setPromptText("Amount must positive");
            depositAmountTextField.clear();
            return;
        }

        Optional<Player> playerOpt = svartJack.getPlayers().stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();

        if (playerOpt.isPresent()) {
            Player player = playerOpt.get();
            player.deposit(amount);

            int index = svartJack.getPlayers().indexOf(player);
            balanceLabels.get(index).setText(Integer.toString(player.getBalance()));

            depositButton1.setVisible(true);
            depositButton1.setDisable(false);
            depositButton2.setVisible(false);
            depositButton2.setDisable(true);
            depositNameTextField.setVisible(false);
            depositNameTextField.setDisable(true);
            depositAmountTextField.setVisible(false);
            depositAmountTextField.setDisable(true);

            depositNameTextField.setPromptText("Player name");
        } else {
            depositNameTextField.setPromptText("Can not find: " + name);
        }

        depositNameTextField.clear();
        depositAmountTextField.clear();
    }

    @FXML
    public void placeBets() {

        if (svartJack.getPlayers().size() > 0) {
            placeBetsButton.setVisible(false);
            placeBetsButton.setDisable(true);
            betAmountTextField.setVisible(true);
            betAmountTextField.setDisable(false);
            betButton.setVisible(true);
            betButton.setDisable(false);
    
            betLabelIndex = 0;
            nextPlayerBet();
    
            betAmountTextField.setPromptText(svartJack.getPlayers().get(betLabelIndex).getName() + " bet amount");  
        }
        else {
            return;
        }
    }

    public void nextPlayerBet() {
        if (betLabelIndex < svartJack.getPlayers().size()) {
            Player player = svartJack.getPlayers().get(betLabelIndex);

            if (player.getBalance() == 0) {
                betLabelIndex++;
                nextPlayerBet();
            } else {
                betAmountTextField.clear();
                betAmountTextField.setPromptText(player.getName() + " bet amount");
            }
        }

        else {
            betAmountTextField.setVisible(false);
            betAmountTextField.setDisable(true);
            betButton.setVisible(false);
            betButton.setDisable(true);
            startDealing();
        }
    }

    @FXML
    public void bet() {
        int amount = Integer.valueOf(betAmountTextField.getText());
        Player player = svartJack.getPlayers().get(betLabelIndex);

        if (amount <= player.getBalance()) {
            System.out.println(player.getName() + " satset " + amount);
    
            Label betLabel = betLabels.get(betLabelIndex);
            betLabel.setText(String.valueOf(amount));
            player.bet(amount);

            balanceLabels.get(betLabelIndex).setText(String.valueOf(player.getBalance()));

            betLabelIndex++;
            nextPlayerBet(); 
        }
        else if (amount < 0) {
            betAmountTextField.setPromptText("Bet mus tbe bigger than 0");
            betAmountTextField.clear();;
            
        }
        else {
            betAmountTextField.setPromptText("Can not bet that much");
            betAmountTextField.clear();;
        }
    }

    private boolean hasValidBets() {
        return betLabels.stream().anyMatch(label -> {
            String text = label.getText();

            if (text.isEmpty()) return false;

            try {
                return Integer.parseInt(text) > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        });
    }

    public void startDealing() {
        if (!hasValidBets()) {
            System.out.println("Ingen spillere har lagt inn bets!");
            reset();
            return;
        }
        svartJack.deal();
    
        System.out.println(svartJack.getDealer().getHand());
    
        svartJack.getPlayers().stream().forEach(c -> {
            System.out.println(c.getHand());
        });
    
        svartJack.getPlayers().forEach(c -> {
            if (c.getHandvalue() > 0) {
                int index = svartJack.getPlayers().indexOf(c);
                scoreLabels.get(index).setText(String.valueOf(c.getHandvalue()));
            }
        });
    
        dealerScore.setText(String.valueOf(svartJack.getDealer().getHandvalue()));
    
        dealerPointer.setVisible(true);
        startHitting();
    
        showCards(); 
    }

    public void startHitting() {
        this.playerHitIndex = 0;
        nextPlayerHit();
    }

    public void nextPlayerHit() {

        if (svartJack.getDeck().get_size() < 20) {
            svartJack.getDeck().add_deck();
        }

        List<Player> players = svartJack.getPlayers();
        
        while (playerHitIndex < players.size() && (players.get(playerHitIndex).getHandvalue() >= 21 || players.get(playerHitIndex).getActiveBet() == 0)) {
        playerHitIndex++;
    }

        if (playerHitIndex < players.size()) {
            dealerPointer.setTranslateX(playerHitIndex * 270);
        }
        else {
            dealerHit();
            dealerPointer.setVisible(false);
            dealerPointer.setTranslateX(0);
        }
        System.out.println("Deck size: " + svartJack.getDeck().get_size());
    }

    @FXML
    public void hit() {
        Player player = svartJack.getPlayers().get(playerHitIndex);
        svartJack.hit(player);

        System.out.println(player.getName() + "'s hand:" + player.getHand());

        Pane pane = playerCardsPane.get(playerHitIndex);
        showCard(player.getHand().getCards().getLast(), pane);

        scoreLabels.get(playerHitIndex).setText(String.valueOf(player.getHandvalue()));

        if (player.isBust()) {
            scoreLabels.get(playerHitIndex).setTextFill(Color.web("#800020"));
            playerHitIndex++;
            nextPlayerHit();
        }
        else if (player.getHandvalue() == 21) {
            playerHitIndex++;
            nextPlayerHit();
        }
    }

    public void stand() {
        playerHitIndex++;
        nextPlayerHit();
    }

    public void dealerHit() {
        Dealer dealer = svartJack.getDealer();

        while (dealer.canHit()) {
            svartJack.hit(dealer);
            dealerScore.setText(String.valueOf(dealer.getHandvalue()));
        }

        if (dealer.isBust()) {
            dealerScore.setTextFill(Color.web("#800020"));
        }

        dealerCards.getChildren().clear();

        dealer.getHand().getCards().forEach(c -> showCard(c, dealerCards));

        dealerScore.setVisible(true);
        resetButton.setVisible(true);
        resetButton.setDisable(false);
    }

    public void results() {
        svartJack.results();
    }

    public void reset() {

        List<Player> players = svartJack.getPlayers();
        Dealer dealer = svartJack.getDealer();

        results();

        int numOfPlayers = svartJack.getPlayers().size();

        for (int index = 0; index < numOfPlayers; index++) {
            balanceLabels.get(index).setText(String.valueOf(players.get(index).getBalance()));
        }

        players.forEach(c -> c.clearHand());
        dealer.clearHand();
        scoreLabels.forEach(c -> {
            c.setText("");
            c.setTextFill(Color.WHITE);
        });

        betLabels.forEach(c -> c.setText(""));
        dealerScore.setText("");
        dealerScore.setTextFill(Color.WHITE);
        
        placeBetsButton.setVisible(true);
        placeBetsButton.setDisable(false);

        playerCardsPane.forEach(c -> c.getChildren().clear());
        dealerCards.getChildren().clear();

        initialize();
    }

    public void showCards() {
        ArrayList<Player> players = svartJack.getPlayers();
        Dealer dealer = svartJack.getDealer();

        players.forEach(p -> {
            int index = players.indexOf(p);
            Pane pane = playerCardsPane.get(index);

            p.getHand().getCards().forEach(c -> showCard(c, pane));
        });

        showCard(dealer.getHand().getCards().get(0), dealerCards);
        showCard(svartJack.getCardBack(), dealerCards);
    }

    public void showCard(Card card, Pane pane) {
        double offset = 20;

        ImageView cardView = new ImageView(card.getImage());
        cardView.setFitWidth(100);
        cardView.setPreserveRatio(true);

        int numCards = pane.getChildren().size();
        cardView.setLayoutX(10 + (numCards * offset)); 
        cardView.setLayoutY(0);

        pane.getChildren().add(cardView);
    }

    public void showCard(Image card, Pane pane) {
        double offset = 20;

        ImageView cardView = new ImageView(card);
        cardView.setFitWidth(103);
        cardView.setPreserveRatio(true);

        int numCards = pane.getChildren().size();
        cardView.setLayoutX(10 + (numCards * offset)); 
        cardView.setLayoutY(0);

        pane.getChildren().add(cardView);
    }

    @FXML
    public void leaveTable1() {
        leaveTable1.setVisible(false);
        leaveTable1.setDisable(true);

        leaveTable.setVisible(true);
        leaveTable.setDisable(false);
        leaveTableName.setVisible(true);
        leaveTableName.setDisable(false);
    }

    @FXML
    public void leaveTable() {
        String playerName = leaveTableName.getText().toLowerCase();

        Optional<Player> optionalPlayer = this.svartJack.getPlayers().stream()
                .filter(p -> p.getName().equals(playerName))
                .findFirst();

        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();

            PlayerData data = new PlayerData(player.getName(), player.getBalance());
            CSVManager.saveOrUpdatePlayer(data);

            // Finn index til spilleren
            int playerIndex = this.svartJack.getPlayers().indexOf(player);

            //Sjekker om bordet er tomt og flytter
            if (playerIndex >= 0) {
                this.svartJack.getPlayers().remove(playerIndex);

                // Flytt resterende spillere mot venstre
                for (int i = playerIndex; i < nameLabels.size() - 1; i++) {
                    nameLabels.get(i).setText(nameLabels.get(i + 1).getText());
                    balanceLabels.get(i).setText(balanceLabels.get(i + 1).getText());
                    betLabels.get(i).setText(betLabels.get(i + 1).getText());
                    scoreLabels.get(i).setText(scoreLabels.get(i + 1).getText());
                    Pane currentPane = playerCardsPane.get(i);
                    Pane nextPane = playerCardsPane.get(i + 1);
                    currentPane.getChildren().clear();
                    nextPane.getChildren().forEach(card -> currentPane.getChildren().add(card));
                    nextPane.getChildren().clear();
                }

                // Nullstill gammel plass
                int lastIndex = nameLabels.size() - 1;
                nameLabels.get(lastIndex).setText("");
                balanceLabels.get(lastIndex).setText("");
                betLabels.get(lastIndex).setText("");
                scoreLabels.get(lastIndex).setText("");
                playerCardsPane.get(lastIndex).getChildren().clear();
            }

            // Skjul input-felt og knapp
            leaveTableName.clear();
            leaveTableName.setVisible(false);
            leaveTableName.setDisable(true);
            leaveTable.setVisible(false);
            leaveTable.setDisable(true);

            // Vis knappen for å forlate bordet hvis ikke bordet er tomt
            if (this.svartJack.getPlayers().size() > 0) {
                leaveTable1.setVisible(true);
                leaveTable1.setDisable(false);
            }

        } else {
            leaveTableName.setPromptText("Invalid name");
            leaveTableName.clear();
        }
    }
}