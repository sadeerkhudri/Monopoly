//all imoports 
package Monopoly;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;   
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class Monopoly extends JFrame implements ActionListener {    
    JLayeredPane layeredPane = new JLayeredPane();
    //all public varaibles
    ImageIcon boot = new ImageIcon(getClass().getResource("tokens/boot.png")), car = new ImageIcon(getClass().getResource("tokens/car.png")), dog = new ImageIcon(getClass().getResource("tokens/dog.png")), hat = new ImageIcon(getClass().getResource("tokens/hat.png")), ship = new ImageIcon(getClass().getResource("tokens/ship.png")), thimble = new ImageIcon(getClass().getResource("tokens/thimble.png")), 
            nextPlayer = new ImageIcon(getClass().getResource("ui/next_player.png")), playerName = new ImageIcon(getClass().getResource("ui/player_name.png")), startGame = new ImageIcon(getClass().getResource("ui/start_game.png")), rollDice = new ImageIcon(getClass().getResource("ui/roll_dice.png")), buyProperty = new ImageIcon(getClass().getResource("ui/buy_property.png")), playerColour = new ImageIcon(getClass().getResource("ui/player_colour.png")), nextCard = new ImageIcon(getClass().getResource("ui/next_card.png")), bail = new ImageIcon(getClass().getResource("ui/bail.png")), endTurn = new ImageIcon(getClass().getResource("ui/end_turn.png"));
    ImageIcon[] dice = {new ImageIcon(getClass().getResource("dice/one.png")), new ImageIcon(getClass().getResource("dice/two.png")), new ImageIcon(getClass().getResource("dice/three.png")), new ImageIcon(getClass().getResource("dice/four.png")), new ImageIcon(getClass().getResource("dice/five.png")), new ImageIcon(getClass().getResource("dice/six.png"))};
    JButton bootB = new JButton("boot"), carB = new JButton("car"), dogB = new JButton("dog"), hatB = new JButton("hat"), shipB = new JButton("ship"), thimbleB = new JButton("thimble"), 
            nextPlayerB = new JButton("nextPlayer"), playerColourB = new JButton("playerColour"), startGameB = new JButton("startGame"), rollDiceB = new JButton("rollDice"), buyPropertyB = new JButton("buyProperty"), endTurnB = new JButton("endTurn"), bailB = new JButton("bail"), nextCardB = new JButton("nextCard");
    JLabel  bootL = new JLabel(new ImageIcon(boot.getImage().getScaledInstance(47, 35, Image.SCALE_SMOOTH))), carL = new JLabel(new ImageIcon(car.getImage().getScaledInstance(47, 35, Image.SCALE_SMOOTH))), dogL = new JLabel(new ImageIcon(dog.getImage().getScaledInstance(47, 35, Image.SCALE_SMOOTH))), hatL = new JLabel(new ImageIcon(hat.getImage().getScaledInstance(47, 35, Image.SCALE_SMOOTH))), shipL = new JLabel(new ImageIcon(ship.getImage().getScaledInstance(47, 35, Image.SCALE_SMOOTH))), thimbleL = new JLabel(new ImageIcon(thimble.getImage().getScaledInstance(47, 35, Image.SCALE_SMOOTH))), 
            bootL1 = new JLabel(new ImageIcon(boot.getImage().getScaledInstance(187, 140, Image.SCALE_SMOOTH))), carL1 = new JLabel(new ImageIcon(car.getImage().getScaledInstance(187, 140, Image.SCALE_SMOOTH))), dogL1 = new JLabel(new ImageIcon(dog.getImage().getScaledInstance(187, 140, Image.SCALE_SMOOTH))), hatL1 = new JLabel(new ImageIcon(hat.getImage().getScaledInstance(187, 140, Image.SCALE_SMOOTH))), shipL1 = new JLabel(new ImageIcon(ship.getImage().getScaledInstance(187, 140, Image.SCALE_SMOOTH))), thimbleL1 = new JLabel(new ImageIcon(thimble.getImage().getScaledInstance(187, 140, Image.SCALE_SMOOTH))), token = new JLabel(boot),         
            nextPlayerL = new JLabel(new ImageIcon(nextPlayer.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH))), startGameL = new JLabel(new ImageIcon(startGame.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH))), rollDiceL = new JLabel(new ImageIcon(rollDice.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH))), buyPropertyL = new JLabel(new ImageIcon(buyProperty.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH))), die1 = new JLabel(dice[0]), die2 = new JLabel(dice[0]), playerNameL = new JLabel(new ImageIcon(playerName.getImage().getScaledInstance(325, 325, Image.SCALE_SMOOTH))), playerColourL = new JLabel(new ImageIcon(playerColour.getImage().getScaledInstance(325, 325, Image.SCALE_SMOOTH))), nextCardL = new JLabel(new ImageIcon(nextCard.getImage().getScaledInstance(150, 44, Image.SCALE_SMOOTH))), bailL = new JLabel(new ImageIcon(bail.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH))), endTurnL = new JLabel(new ImageIcon(endTurn.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH))),
            titleT = new JLabel("Player 1 Customization"), moneyT = new JLabel(""), changeT = new JLabel(""), tokenT = new JLabel("Choose Your Token"), cardL = new JLabel(new ImageIcon(getClass().getResource("cards/1.png")));
    JTextField playerNameT = new JTextField(); 
    //each row represents a player on these lists  
    List<String[]> playerNames = new ArrayList<>();
    List<JLabel[]> playerTokens = new ArrayList<>();
    List<Color[]> playerColours = new ArrayList<>();
    List<Integer[]> playerInformation = new ArrayList<>(); //columns: money, position, jailStatus, doublesStatus, railroadStatus, utilityStatus 
    //2D array database of all of the spaces and their information, each row is a new space, columns; propertyType ,owner, propertyPrice, housePrice, rent, housesAmount, 1 house, 2 house, 3 house, 4 house, 5 house
    //propertyType legend, 0 means blank space, 1 is brown set, 2 is light blue set, 3 is pink set, 4 is orange set, 5 is red set, 6 is yellow, set, 7 is green set, 8 is dark blue set, 9 is railraod, 10 is utility, 11 is tax, 12 is go to jail, 13 is chance, and 14 is community chest
    Integer[][] spaces = {{0, -1}, {1, -1, 60, 50, 2, -1, 10, 30, 90, 160, 250}, {14, -1}, {1, -1, 60, 50, 4, -1, 20, 60, 180, 320, 450}, {11, 200}, {9, -1, 200}, {2, -1, 100, 50, 6, -1, 30, 90, 270, 400, 550}, {13, -1}, {2, -1, 100, 50, 6, -1, 30, 90, 270, 400, 550}, {2, -1, 120, 50, 8, -1, 40, 100, 300, 450, 600},
                          {0, -1}, {3, -1, 140, 100, 10, -1, 50, 150, 450, 625, 750}, {10, -1, 150}, {3, -1, 140, 100, 10, -1, 50, 150, 450, 625, 750}, {3, -1, 160, 100, 12, -1, 60, 180, 500, 700, 900}, {9, -1, 200}, {4, -1, 180, 100, 14, -1, 70, 200, 550, 750, 950}, {14, -1}, {4, -1, 180, 100, 14, -1, 70, 200, 550, 750, 950}, {4, -1, 200, 100, 16, -1, 80, 220, 600, 800, 1000},
                          {0, -1}, {5, -1, 220, 150, 18, -1, 90, 250, 700, 875, 1050}, {13, -1}, {5, -1, 220, 150, 18, -1, 90, 250, 700, 875, 1050}, {5, -1, 240, 150, 20, -1, 100, 300, 750, 925, 1100}, {9, -1, 200}, {6, -1, 260, 150, 22, -1, 110, 330, 800, 975, 1150}, {6, -1, 260, 150, 22, -1, 110, 330, 800, 975, 1150}, {10, -1, 150}, {6, -1, 280, 150, 24, -1, 120, 360, 850, 1025, 1200},
                          {12, -1}, {7, -1, 300, 200, 26, -1, 130, 390, 900, 1175, 1275}, {7, -1, 300, 200, 26, -1, 130, 390, 900, 1175, 1275}, {14, -1}, {7, -1, 320, 200, 28, -1, 150, 450, 1000, 1200, 1400}, {9, -1, 200}, {13, -1}, {8, -1, 350, 200, 35, -1, 175, 500, 1100, 1300, 1500}, {11, 100}, {8, -1, 400, 200, 50, -1, 200, 600, 1400, 1700, 2000}};
    List<Integer> cardsPossessed = new ArrayList<>(); //used to display cards
    Integer playerTurn = 0, rand1, rand2, currentCard = 0;

    //custom invisivle buttons
    public void customButton(JButton btn) {
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setForeground(new Color(0, 0, 0, 0));
    }

    //remove/hide button
    public void removeButton(JButton btn, JLabel label) {
        btn.setVisible(false);
        btn.setEnabled(false);
        label.setVisible(false);
    }

    //readd button that was previously removed 
    public void addButton(JButton btn, JLabel label) {
        btn.setVisible(true);
        btn.setEnabled(true);
        label.setVisible(true);
    }

    //once you select a new token unhighlight the rest of them
    public void unhighlight(String btn) { 
        //if you aren't the new token selected unhighlight/make button invisivle 
        if (!btn.equals("boot")) {
            customButton(bootB);
        }
        if (!btn.equals("car")) {
            customButton(carB);
        }
        if (!btn.equals("dog")) {
            customButton(dogB);
        }
        if (!btn.equals("hat")) {
            customButton(hatB);
        }
        if (!btn.equals("ship")) {
            customButton(shipB);
        }
        if (!btn.equals("thimble")) {
            customButton(thimbleB);
        }
    }

    //delay function 
    public void delay(int ms, Runnable action) {
        Timer t = new Timer(ms, e -> {
            ((Timer)e.getSource()).stop();
            action.run();
        });
        t.setRepeats(false);
        t.start();
    }

    //plays Turn
    public void playTurn() {
        titleT.setText(playerNames.get(playerTurn)[0] + "'s Turn"); //set title to players name's turn 
        titleT.setForeground(playerColours.get(playerTurn)[0]); //set colour to players chosen colour
        token.setIcon(playerTokens.get(playerTurn)[0].getIcon()); //set icon next to money to players icon
        moneyT.setText("Money: " + playerInformation.get(playerTurn)[0]); //display money
        displayCards(); //display cards
        addButton(rollDiceB, rollDiceL); //add rollDice Buttone 
        // if player is in jail then add bail button
        if (playerInformation.get(playerTurn)[2] > 0) {
            addButton(bailB, bailL);
        }
        //if there is only one person remaining that isn't bankrupt then declair them the winner and reutrn to main menu 
        if (playerInformation.size() == 1) {
            JOptionPane.showMessageDialog(null, playerNames.get(playerTurn)[0] + " wins!");
            Menu x = new Menu();
            x.setVisible(true);
            dispose();
        }
    }

    //pay bail
    public void payBail() {
        //reset jailStatus to 0
        playerInformation.get(playerTurn)[2] = 0; //reset jailStatus to 0
        playerInformation.get(playerTurn)[0] -= 50; //decrease moeny by 50
        //output money and change to money 
        moneyT.setText("Money: " + playerInformation.get(playerTurn)[0]); 
        changeT.setText("-50");
        changeT.setForeground(Color.RED);
        // 2 second delay then reset the change to money text
        delay(2000, () -> {
            changeT.setText("");
        });
    }

    //display cards
    public void displayCards() {
        //clear list and make currentCard being displayed 0
        cardsPossessed.clear();
        currentCard = 0;
        //check every space if the player is the owner and if they are then add that card to the list 
        for (int i = 0; i < 40; i++) {
            if (spaces[i][1] == playerTurn) {
                cardsPossessed.add(i);
            }
        }

        //if the player owns a card display it 
        if (cardsPossessed.size() > 0) {
            cardL.setIcon(new ImageIcon(getClass().getResource("cards/" + cardsPossessed.get(currentCard) + ".png")));
            cardL.setVisible(true);
        }  else { //otherwise don't display anything
            cardL.setVisible(false);
        }

        //if the player owns more than one card then add a next card button for the player to switch through their possessed cards
        if (cardsPossessed.size() > 1) {
            addButton(nextCardB, nextCardL);
        } else { //otherwise remove the next card button 
            removeButton(nextCardB, nextCardL);
        }
    }

    //buying procedure
    public void game() {
 //2D array database of all of the spaces and their information, each row is a new space, columns; propertyType ,owner, propertyPrice, housePrice, rent, housesAmount, 1 house, 2 house, 3 house, 4 house, 5 house
    //propertyType legend, 0 means blank space, 1 is brown set, 2 is light blue set, 3 is pink set, 4 is orange set, 5 is red set, 6 is yellow, set, 7 is green set, 8 is dark blue set, 9 is railraod, 10 is utility, 11 is tax, 12 is go to jail, 13 is chance, and 14 is community chest

        if (spaces[playerInformation.get(playerTurn)[1]][0] == 0) { //if blank space, end turn
            endTurn();
        } else if (spaces[playerInformation.get(playerTurn)[1]][0] == 11) { //if you landed on tax make the player pay it and display the change
            playerInformation.get(playerTurn)[0] -= spaces[playerInformation.get(playerTurn)[1]][1];
            moneyT.setText("Money: " + playerInformation.get(playerTurn)[0]);
            changeT.setText("-" + spaces[playerInformation.get(playerTurn)[1]][1]);
            changeT.setForeground(Color.RED);
            //2 second delay then end the turn 
            delay(2000, () -> {
                endTurn();
            });
        } else if (spaces[playerInformation.get(playerTurn)[1]][0] == 12) { //if you landed on jail, go to jail
            jail();
        } else if (spaces[playerInformation.get(playerTurn)[1]][0] == 13) { //chance
            endTurn();
        } else if (spaces[playerInformation.get(playerTurn)[1]][0] == 14) { //community chest
            endTurn();
        } else { //if you landed on a property  
            if (spaces[playerInformation.get(playerTurn)[1]][1] == playerTurn) { //if you own the property, end turn
                endTurn();
            } else if (spaces[playerInformation.get(playerTurn)[1]][1] == -1) { //if bank owns the property, enter buying procedure
                addButton(buyPropertyB, buyPropertyL);
                addButton(endTurnB, endTurnL);
            } else { //if someone else owns the property, pay rent
                if (spaces[playerInformation.get(playerTurn)[1]][0] == 9) { //if railroad then
                    //depending on the owners railroadStatus which is the 5th column of playerInformation multiply that by 50 and thats the amount you have to pay and display etc.
                    playerInformation.get(playerTurn)[0] -= playerInformation.get(spaces[playerInformation.get(playerTurn)[1]][1])[4]*50;
                    moneyT.setText("Money: " + playerInformation.get(playerTurn)[0]);
                    changeT.setText("-" + playerInformation.get(spaces[playerInformation.get(playerTurn)[1]][1])[4]*50);
                    changeT.setForeground(Color.RED);
                    playerInformation.get(spaces[playerInformation.get(playerTurn)[1]][1])[0] += playerInformation.get(spaces[playerInformation.get(playerTurn)[1]][1])[4]*50;
                } else if (spaces[playerInformation.get(playerTurn)[1]][0] == 10) { //if utility then
                    //depending on the owners utilityStatus which is the 6th column of playerInformation either multiple the amount shown on the dice by 4 if they only own only one and multiple by 10 if they own two
                    if (playerInformation.get(spaces[playerInformation.get(playerTurn)[1]][1])[5] == 1) { //if owner only owns one then multiply amount shown on dice by 4
                        playerInformation.get(playerTurn)[0] -= 4*(rand1 + rand2 + 2);
                        moneyT.setText("Money: " + playerInformation.get(playerTurn)[0]);
                        changeT.setText("-" + 4*(rand1 + rand2 + 2));
                        changeT.setForeground(Color.RED);
                        playerInformation.get(spaces[playerInformation.get(playerTurn)[1]][1])[0] += 4*(rand1 + rand2 + 2);
                    } else { //if owner owns both then multiply amount shown on dice by 10
                        playerInformation.get(playerTurn)[0] -= 10*(rand1 + rand2 + 2);
                        moneyT.setText("Money: " + playerInformation.get(playerTurn)[0]);
                        changeT.setText("-" + 10*(rand1 + rand2 + 2));
                        changeT.setForeground(Color.RED);
                        playerInformation.get(spaces[playerInformation.get(playerTurn)[1]][1])[0] += 10*(rand1 + rand2 + 2);
                    }
                } else { //otherwise it is regular property 
                    //pay, recieve, and display rent cost which the third column in spaces
                    playerInformation.get(playerTurn)[0] -= spaces[playerInformation.get(playerTurn)[1]][4];
                    moneyT.setText("Money: " + playerInformation.get(playerTurn)[0]);
                    changeT.setText("-" + spaces[playerInformation.get(playerTurn)[1]][4]);
                    changeT.setForeground(Color.RED);
                    playerInformation.get(spaces[playerInformation.get(playerTurn)[1]][1])[0] += spaces[playerInformation.get(playerTurn)[1]][4];
                }
                //2 second delay and then end turn 
                delay(2000, () -> {
                    endTurn();
                });
            }
        }
    }

    //end turn
    public void endTurn() {
        //set change to money text to nothing 
        changeT.setText("");
        
        //if player went bankrupts (they have less than 0 dollars)
        if (playerInformation.get(playerTurn)[0] < 0) {
            //display that they went bankrupt
            JOptionPane.showMessageDialog(null, playerNames.get(playerTurn)[0] + " has gone bankrupt!");
            for (int i = 0; i < 40; i++) {
                //check all the spaces to see if they owned any and if they did give it back to the bank
                if (spaces[i][1] == playerTurn) {
                    spaces[i][1] = -1;
                //if players above the player that got out own any properties decrease their number by one as we are gonna remove the bankrupt players information row which will decrease the list size which we use to determine how many players are still in the game this means players that were numbered above the player the just bankrupt are gonna get appended down by 1
                } else if (spaces[i][1] > playerTurn && spaces[i][1] < playerInformation.size()) {
                    spaces[i][1] -= 1;
                }
            }
            //remove token from board
            playerTokens.get(playerTurn)[0].setVisible(false);
            //remove all of the player's information from the lists  
            playerInformation.remove((int)playerTurn);
            playerNames.remove((int)playerTurn);
            playerTokens.remove((int)playerTurn);
            playerColours.remove((int)playerTurn);
        } else { //otherwise continue as normal 
            //if player did not roll a double go to next players turn
            if (playerInformation.get(playerTurn)[3] == 0) {
                playerTurn += 1;
            }
        }

        //if the players turn surpasses the amount of player lower it back down accordingly 
        if (playerTurn == playerInformation.size()) {
            playerTurn -= playerInformation.size();
        }

        //play the next turn
        playTurn();
    }

    //jail procedure
    public void jail() {
        //notify player they went to jail, set jail status to 1, set their position to jail and update their tokenPosition
        changeT.setText("JAIL!");
        changeT.setForeground(Color.RED);
        playerInformation.get(playerTurn)[2] = 1;
        playerInformation.get(playerTurn)[1] = 10;
        tokenPosition(playerTurn);
        //delay 2 seconds and then immediately end their turn
        delay(2000, () -> {
            endTurn();
        });
    }

    //move token
    public void move() {
        //add amount shown on dice to current players position 
        playerInformation.get(playerTurn)[1] += rand1  + rand2 + 2;
        //if position is greater than 39 subtract it by 40 and give them 200 dollars as that means they have made a full trip around the board and passed go
        if (playerInformation.get(playerTurn)[1] > 39) {
            playerInformation.get(playerTurn)[1] -= 40;
            playerInformation.get(playerTurn)[0] += 200;
            moneyT.setText("Money: " + playerInformation.get(playerTurn)[0]);
            changeT.setText("+200");
            changeT.setForeground(Color.GREEN);
        }
        tokenPosition(playerTurn); //update tokens position on the board
        //1 second delay and the continue with game 
        delay(1000, () -> {
            game();
        });
    }

    //token position on the board
    public void tokenPosition(Integer player) {
        int x = 0, y = 0, multiplier = 0;
        //depending on which rown/column of spaces the player is on you add or subtract to y or x to move accross that row/column 
        //depending on how far down in the column of spaces the player is multiply it by 59 as each speace is seperated by 59 pixels
        //is this an even or odd player as even players have a specific route and odd ones have a different one to avoid tokens being on top of each other as much as possible
        if (playerInformation.get(player)[1] >= 30) {
            multiplier += (playerInformation.get(playerTurn)[1] - 30); 
            y = 48 + 59*multiplier;
            if (player % 2 == 0) { 
                x = 644;
            } else {
                x = 679;
            }
        } else if (playerInformation.get(player)[1] >= 20) {
            multiplier += (playerInformation.get(player)[1] - 20);
            x = 41 + 59*multiplier;
            if (player % 2 == 0) {
                y = 35;
            } else {
                y = 0;
            }
        } else if (playerInformation.get(player)[1] > 10) {
            multiplier += (playerInformation.get(player)[1] - 11);
            y = 580 - 59*multiplier;
            if (player % 2 == 0) {
                x = 28;
            } else {
                x = -7;
            }
        } else if (playerInformation.get(player)[1] == 10) { //in this space depending on if your visiting jail or if your actually in jail your token position differs
            if (playerInformation.get(player)[2] == 0) {
                if (player % 2 == 0) {
                    x = 38;
                    y = 690;
                } else {
                    x = -8;
                    y = 645;
                }
            } else {
                x = 38;
                y = 643;
            }

        } else {
            multiplier += playerInformation.get(player)[1];
            x = 631 - 59*multiplier;
            if (player % 2 == 0) {
                y = 648;
            } else {
                y = 683;
            }
        }
        playerTokens.get(player)[0].setBounds(x, y, 47, 35); //display token in new position 
    }

    //player customization, once button is pressed to go to next player or star game, record all of the players information
    public void playerCustomization(JButton btn, JLabel label) {
        //if nothing was entered to playerName default it to Player (the number they are) 
        if (playerNameT.getText().equals("")) {
            playerNames.get(playerNames.size() - 1)[0] = "Player " + playerNames.size();
        }  else { //otherwise make whatever they entered their player name
            playerNames.get(playerNames.size() - 1)[0] = playerNameT.getText();
        }
        //reset the textbox to nothing
        playerNameT.setText("");
        //reset custom colour
        playerColourB.setBorderPainted(false);
        //remove token that just selected
        removeButton(btn, label);
        
        //add row of players name,token, colour, and information to their respective lists
        playerNames.add(new String[]{" "});
        playerTokens.add(new JLabel[]{startGameL});
        playerColours.add(new Color[]{new Color(0, 0, 0)});
        playerInformation.add(new Integer[]{1500, 0, 0, 0, 0, 0}); //money, position, jailStatus, doubleStatus, railroadStatus, utilityStatus

        titleT.setText("Player " + playerInformation.size() + " Customization"); //new player customization title

        //if this is the second player add the start game button 
        if (playerInformation.size() == 2) {
            startGameL.setBounds(715, 560, 300, 200);
            layeredPane.add(startGameL,  Integer.valueOf(1));
            startGameB.addActionListener(this);
            startGameB.setBounds(760, 616, 210, 90);
            layeredPane.add(startGameB, Integer.valueOf(1));
            customButton(startGameB);
        }

        //if this is the sixth player remove the next player button
        if (playerInformation.size() == 6) {
            removeButton(nextPlayerB, nextPlayerL);
        }
    }

    public Monopoly() {
        //setup
        super("Monopoly");
        setSize(1280, 748);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layeredPane.setPreferredSize(getSize());
        setContentPane(layeredPane);

        //background image
        ImageIcon menuImage = new ImageIcon(getClass().getResource("backgrounds/game.png"));
        JLabel bg = new JLabel(menuImage);
        bg.setBounds(0, 0, menuImage.getIconWidth(), menuImage.getIconHeight());
        layeredPane.add(bg, Integer.valueOf(0));

        //all the buttons
        ImageIcon mainMenu = new ImageIcon(getClass().getResource("ui/main_menu.png"));
        JLabel mainMenuL = new JLabel(new ImageIcon(mainMenu.getImage().getScaledInstance(150, 44, Image.SCALE_SMOOTH)));
        mainMenuL.setBounds(470, 100, 150, 44);
        layeredPane.add(mainMenuL, Integer.valueOf(1));
        JButton mainMenuB = new JButton("mainMenu");
        mainMenuB.addActionListener(this);
        mainMenuB.setBounds(470, 100, 150, 44);
        layeredPane.add(mainMenuB, Integer.valueOf(1));
        customButton(mainMenuB);

        ImageIcon newGame = new ImageIcon(getClass().getResource("ui/new_game.png"));
        JLabel newGameL = new JLabel(new ImageIcon(newGame.getImage().getScaledInstance(150, 44, Image.SCALE_SMOOTH)));
        newGameL.setBounds(470, 150, 150, 44);
        layeredPane.add(newGameL, Integer.valueOf(1));
        JButton newGameB = new JButton("newGame");
        newGameB.addActionListener(this);
        newGameB.setBounds(470, 150, 150, 44);
        layeredPane.add(newGameB, Integer.valueOf(1));
        customButton(newGameB);

        ImageIcon help = new ImageIcon(getClass().getResource("ui/help.png"));
        JLabel helpL = new JLabel(new ImageIcon(help.getImage().getScaledInstance(150, 44, Image.SCALE_SMOOTH)));
        helpL.setBounds(100, 525, 150, 44);
        layeredPane.add(helpL, Integer.valueOf(1));
        JButton helpB = new JButton("help");
        helpB.addActionListener(this);
        helpB.setBounds(100, 525, 150, 44);
        layeredPane.add(helpB, Integer.valueOf(1));
        customButton(helpB);

        ImageIcon quit = new ImageIcon(getClass().getResource("ui/quit.png"));
        JLabel quitL = new JLabel(new ImageIcon(quit.getImage().getScaledInstance(150, 44, Image.SCALE_SMOOTH)));
        quitL.setBounds(100, 575, 150, 44);
        layeredPane.add(quitL, Integer.valueOf(1));
        JButton quitB = new JButton("quit");
        quitB.addActionListener(this);
        quitB.setBounds(100, 575, 150, 44);
        layeredPane.add(quitB, Integer.valueOf(1));
        customButton(quitB);

        //the 2 dice
        die1.setBounds(285, 323, 75, 75);
        layeredPane.add(die1, Integer.valueOf(1));
        die2.setBounds(360, 323, 75, 75);
        layeredPane.add(die2, Integer.valueOf(1));

        //first players placeholders until they enter their customized stuff
        playerNames.add(new String[]{" "});
        playerTokens.add(new JLabel[]{startGameL});
        playerColours.add(new Color[]{new Color(0, 0, 0)});
        playerInformation.add(new Integer[]{1500, 0, 0, 0, 0, 0}); //money, position, jailStatus, doublesStatus, railroadStatus, utilityStatus

        //all texts
        titleT.setBounds(720, -60, 550, 200);
        titleT.setFont(new Font("Times New Roman", Font.BOLD, 50));
        titleT.setHorizontalAlignment(JTextField.CENTER);
        layeredPane.add(titleT,  Integer.valueOf(1));

        tokenT.setBounds(840, 25, 300, 200);
        tokenT.setFont(new Font("Times New Roman", Font.BOLD, 30));
        tokenT.setHorizontalAlignment(JTextField.CENTER);
        layeredPane.add(tokenT,  Integer.valueOf(1));

        //all token buttons
        bootL1.setBounds(906, 315, 187, 140);
        layeredPane.add(bootL1, Integer.valueOf(1));
        carL1.setBounds(1094, 315, 187, 140);
        layeredPane.add(carL1, Integer.valueOf(1));
        dogL1.setBounds(1094, 175, 187, 140);
        layeredPane.add(dogL1, Integer.valueOf(1));
        hatL1.setBounds(718, 175, 187, 140);
        layeredPane.add(hatL1, Integer.valueOf(1));
        shipL1.setBounds(906, 175, 187, 140);
        layeredPane.add(shipL1, Integer.valueOf(1));
        thimbleL1.setBounds(718, 315, 187, 140);
        layeredPane.add(thimbleL1, Integer.valueOf(1));

       
        bootB.addActionListener(this);
        bootB.setBounds(906, 315, 187, 140);
        layeredPane.add(bootB, Integer.valueOf(1));
        carB.addActionListener(this);
        carB.setBounds(1094, 315, 187, 140);
        layeredPane.add(carB, Integer.valueOf(1));
        dogB.addActionListener(this);
        dogB.setBounds(1094, 175, 187, 140);
        layeredPane.add(dogB, Integer.valueOf(1));
        hatB.addActionListener(this);
        hatB.setBounds(718, 175, 187, 140);
        layeredPane.add(hatB, Integer.valueOf(1));
        shipB.addActionListener(this);
        shipB.setBounds(906, 175, 187, 140);
        layeredPane.add(shipB, Integer.valueOf(1));
        thimbleB.addActionListener(this);
        thimbleB.setBounds(718, 315, 187, 140);
        layeredPane.add(thimbleB, Integer.valueOf(1));
        customButton(bootB);
        customButton(carB);
        customButton(dogB);
        customButton(hatB);
        customButton(shipB);
        customButton(thimbleB);

        //player name textbox setup, limits it to only allow a max of 10 characters to be inputted
        playerNameT.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException {
                if (str == null) return;
                if (getLength() + str.length() <= 10 && !str.contains(" ")) {
                    super.insertString(offs, str, a);
                }
            }
        });
        playerNameT.setBounds(1012, 533, 175, 35);
        layeredPane.add(playerNameT, Integer.valueOf(1));
        playerNameT.setOpaque(false);
        playerNameT.setBackground(new Color(0,0,0,0));
        playerNameT.setBorder(null);
        playerNameT.setFont(new Font("SansSerif", Font.BOLD, 20));
        playerNameT.setHorizontalAlignment(JTextField.CENTER);

        playerNameL.setBounds(965, 373, 325, 325);
        layeredPane.add(playerNameL,  Integer.valueOf(1));
        
        //player colour button
        playerColourL.setBounds(695, 375, 325, 325);
        layeredPane.add(playerColourL,  Integer.valueOf(1));
        playerColourB.addActionListener(this);
        playerColourB.setBounds(728, 491, 259, 92);
        layeredPane.add(playerColourB, Integer.valueOf(1));
        customButton(playerColourB);

        //next player button
        nextPlayerL.setBounds(975, 560, 300, 200);
        layeredPane.add(nextPlayerL,  Integer.valueOf(1));
        nextPlayerB.addActionListener(this);
        nextPlayerB.setBounds(1020, 616, 210, 90);
        layeredPane.add(nextPlayerB, Integer.valueOf(1));
        customButton(nextPlayerB);    
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        //depending on token selected, highlight it and set current players token to that one and unhighlight all the other tokens using the unhighlight() function
        if (action.equals("boot")) {
            bootB.setBorderPainted(true);
            bootB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4, true));
            playerTokens.get(playerTokens.size() - 1)[0] = bootL;
            unhighlight("boot");
        }
        if (action.equals("car")) {
            carB.setBorderPainted(true);
            carB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4, true));
            playerTokens.get(playerTokens.size() - 1)[0] = carL;
            unhighlight("car");
        }
        if (action.equals("dog")) {
            dogB.setBorderPainted(true);
            dogB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4, true));
            playerTokens.get(playerTokens.size() - 1)[0] = dogL;
            unhighlight("dog");
        }
        if (action.equals("hat")) {
            hatB.setBorderPainted(true);
            hatB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4, true));
            playerTokens.get(playerTokens.size() - 1)[0] = hatL;
            unhighlight("hat");
        }
        if (action.equals("ship")) {
            shipB.setBorderPainted(true);
            shipB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4, true));
            playerTokens.get(playerTokens.size() - 1)[0] = shipL;
            unhighlight("ship");
        }
        if (action.equals("thimble")) {
            thimbleB.setBorderPainted(true);
            thimbleB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4, true));
            playerTokens.get(playerTokens.size() - 1)[0] = thimbleL;
            unhighlight("thimble");
        }

        //if player colour button is pressed open window with the rgb colour chooser window to allow them to select what colour they want then same it in the current players colours list and highlight the button with that colour to show it off
        if (action.equals("playerColour")) {
            playerColours.get(playerColours.size() - 1)[0] = JColorChooser.showDialog(this, "Choose Player Colour", new Color(0, 0, 0));
            if (!playerColours.get(playerColours.size() - 1)[0].equals(null)) {
                playerColourB.setBorderPainted(true);
                playerColourB.setBorder(BorderFactory.createLineBorder(playerColours.get(playerColours.size() - 1)[0], 4, true));            
            } else {
                playerColourB.setBorderPainted(false);
            }
        }

        //if next player button is pressed go to player customization to lock in all of the players chosen customizations and move on to the next player
        if (action.equals("nextPlayer")) {
            if (playerTokens.get(playerTokens.size() - 1)[0].equals(bootL)) {
                playerCustomization(bootB, bootL1);
            } else if (playerTokens.get(playerTokens.size() - 1)[0].equals(carL)) {
                playerCustomization(carB, carL1);
            } else if (playerTokens.get(playerTokens.size() - 1)[0].equals(dogL)) {
                playerCustomization(dogB, dogL1);
            } else if (playerTokens.get(playerTokens.size() - 1)[0].equals(hatL)) {
                playerCustomization(hatB, hatL1);
            } else if (playerTokens.get(playerTokens.size() - 1)[0].equals(shipL)) {
                playerCustomization(shipB, shipL1);
            } else if (playerTokens.get(playerTokens.size() - 1)[0].equals(thimbleL)) {
                playerCustomization(thimbleB, thimbleL1);
            } 
        }

        //if start game button is pressed initialize everything and start game
        if (action.equals("startGame")) {
            //only allow player to start game if there are two player or more with chosen tokens
            if (!playerTokens.get(playerTokens.size() - 1)[0].equals(startGameL)) {
                //lock in all of the most recent players customizations like in the playerCustomization() function
                if (playerNameT.getText().equals("")) { 
                    playerNames.get(playerNames.size() - 1)[0] = "Player " + playerNames.size();
                }  else {
                    playerNames.get(playerNames.size() - 1)[0] = playerNameT.getText();
                }

                //remove all the buttons and labels and textboxs used in the player customization 
                removeButton(bootB, bootL1);
                removeButton(carB, carL1);
                removeButton(dogB, dogL1);
                removeButton(hatB, hatL1);
                removeButton(shipB, shipL1);
                removeButton(thimbleB, thimbleL1);
                removeButton(startGameB, startGameL);
                removeButton(playerColourB, playerColourL);
                removeButton(nextPlayerB, nextPlayerL);
                tokenT.setVisible(false);
                playerNameT.setVisible(false);
                playerNameT.setEnabled(false);
                playerNameL.setVisible(false);

                //add the token, money, players name, change, and the roll button
                token.setBounds(870, 100, 47, 35);
                layeredPane.add(token,  Integer.valueOf(1));
                moneyT.setBounds(950, 65, 200, 100);
                moneyT.setFont(new Font("Times New Roman", Font.BOLD, 30));
                moneyT.setHorizontalAlignment(JTextField.CENTER);
                layeredPane.add(moneyT,  Integer.valueOf(1));
                changeT.setBounds(1075, 95, 100, 100);
                changeT.setFont(new Font("Times New Roman", Font.BOLD, 30));
                layeredPane.add(changeT,  Integer.valueOf(1));
                rollDiceL.setBounds(715, 560, 300, 200);
                layeredPane.add(rollDiceL,  Integer.valueOf(1));
                rollDiceB.addActionListener(this);
                rollDiceB.setBounds(760, 616, 210, 90);
                layeredPane.add(rollDiceB, Integer.valueOf(1));
                customButton(rollDiceB);

                //initialize all buttons used in the game but then remove them to be added later. This is to simplify the process so when I want to remove or add the buttons later I can just use the removeButton() or addButton() function. It keeps things organized while also avoiding errors like reinitializing the same variable in a plane which causes errors in layered planes.
                endTurnL.setBounds(715, 560, 300, 200);
                layeredPane.add(endTurnL,  Integer.valueOf(1));
                endTurnB.addActionListener(this);
                endTurnB.setBounds(760, 616, 210, 90);
                layeredPane.add(endTurnB, Integer.valueOf(1));
                customButton(endTurnB);
                removeButton(endTurnB, endTurnL);
                buyPropertyL.setBounds(975, 560, 300, 200);
                layeredPane.add(buyPropertyL,  Integer.valueOf(1));
                buyPropertyB.addActionListener(this);
                buyPropertyB.setBounds(1020, 616, 210, 90);
                layeredPane.add(buyPropertyB, Integer.valueOf(1));
                customButton(buyPropertyB);
                removeButton(buyPropertyB, buyPropertyL);
                bailL.setBounds(975, 560, 300, 200);
                layeredPane.add(bailL,  Integer.valueOf(1));
                bailB.addActionListener(this);
                bailB.setBounds(1020, 616, 210, 90);
                layeredPane.add(bailB, Integer.valueOf(1));
                customButton(bailB);
                removeButton(bailB, bailL);
                cardL.setBounds(870, 180, 276, 315);
                layeredPane.add(cardL,  Integer.valueOf(1));
                cardL.setVisible(false);
                nextCardL.setBounds(933, 500, 150, 44);
                layeredPane.add(nextCardL,  Integer.valueOf(1));
                nextCardB.addActionListener(this);
                nextCardB.setBounds(933, 500, 150, 44);
                layeredPane.add(nextCardB, Integer.valueOf(1));
                customButton(nextCardB);
                removeButton(nextCardB, nextCardL);

                //set the token position on the board for all the players
                for (int i = 0; i < playerTokens.size(); i++) {
                    tokenPosition(i);
                    layeredPane.add(playerTokens.get(i)[0],  Integer.valueOf(1));
                }
                //play first players turn 
                playTurn();
            }
        }

        //if roll dice button is pressed
        if (action.equals("rollDice")) {
            //remove roll dice button and if player is in jail remove bail button too 
            removeButton(rollDiceB, rollDiceL);
            if (playerInformation.get(playerTurn)[2] > 0) {
                removeButton(bailB, bailL);
            }
            //animation of button with timer implementation 
            Timer t = new Timer(125, null);
            t.addActionListener(new ActionListener() {
                int counter = 0;
                @Override
                public void actionPerformed(ActionEvent e) {
                    //roll 10 times for animation every 125 miliseconds
                    if (counter++ < 10) {
                        //generate 2 random digits 1-6 for the two die
                        rand1 = new Random().nextInt(6);
                        rand2 = new Random().nextInt(6);
                        //set the dice icons to the random numbers generated 
                        die1.setIcon(dice[rand1]);
                        die2.setIcon(dice[rand2]);
                    } else { //when animation is over 
                        //if player is in jail
                        if (playerInformation.get(playerTurn)[2] > 0) {
                            //if they roll a double then realease them from jail and move like normal 
                            if (rand1 == rand2) {
                                playerInformation.get(playerTurn)[2] = 0;
                                move();
                            //if it's their third time rolling and they didn't get a double force them to pay bail and continue moving like normal, realeasing them from jail
                            } else if (playerInformation.get(playerTurn)[2] == 3) { 
                                payBail();
                                playerInformation.get(playerTurn)[2] = 0;
                                move();
                            } else { //otheriwse don't release them from jail and end their turn while also adding 1 to the jailStatus to keep track of how many times they have tried to roll a double to get out of jail 
                                playerInformation.get(playerTurn)[2] += 1;
                                endTurn();
                            }
                        } else { //otherwise if player isn't in jail
                            //if they roll a double add 1 to their doubleStatus to keep track of how many they have rolled in a row
                            if (rand1 == rand2) {
                                playerInformation.get(playerTurn)[3] += 1;
                            } else { //otherwise if they didn't roll a double reset it back to 0
                                playerInformation.get(playerTurn)[3] = 0;
                            }

                            //if this is the third time they rolled a double, make them go directly to jail and reset their doubleStatus
                            if (playerInformation.get(playerTurn)[3] == 3) {
                                playerInformation.get(playerTurn)[3] = 0;
                                jail();
                            } else { //otherwise move like normal
                                move();
                            }
                        }
                        t.stop();
                    }
                }
            });
            t.start();
        }

        //if buy property button is pressed
        if (action.equals("buyProperty")) {
            //remove end turn and buy property button 
            removeButton(endTurnB, endTurnL);
            removeButton(buyPropertyB, buyPropertyL);
            //set owner variable in the spaces database (2D array) to the current player
            spaces[playerInformation.get(playerTurn)[1]][1] = playerTurn;
            //make them pay the amount needed to buy the property, grab it from the second column in that space's row, display this transaction
            playerInformation.get(playerTurn)[0] -= spaces[playerInformation.get(playerTurn)[1]][2];
            moneyT.setText("Money: " + playerInformation.get(playerTurn)[0]);
            changeT.setText("-" + spaces[playerInformation.get(playerTurn)[1]][2]);
            changeT.setForeground(Color.RED);

            if (spaces[playerInformation.get(playerTurn)[1]][0] == 9) { //if property that is bought is a railraod
                playerInformation.get(playerTurn)[4] += 1; //increase that player's railroad status by one
            } else if (spaces[playerInformation.get(playerTurn)[1]][0] == 10) { //if property that is bought is a utility
                playerInformation.get(playerTurn)[5] += 1; //increase that player's utility status by one
            }
            //2 second delay then end the turn 
            delay(2000, () -> {
                endTurn();
            });
        }
        
        //if end turn button is pressed remove the button and buy property button and run the end turn procedure
        if (action.equals("endTurn")) {
            removeButton(endTurnB, endTurnL);
            removeButton(buyPropertyB, buyPropertyL);
            endTurn();
        }

        //bail button is pressed, remove the button and run the bail procedure
        if (action.equals("bail")) {
            removeButton(bailB, bailL);
            payBail();
        }

        //if next card button is pressed
        if (action.equals("nextCard")) {
            //add one to the currentCard variable
            currentCard += 1; 
            //if the currentCard variable is greater than the amount of cards possessed adjust it accordingly 
            if (currentCard >= cardsPossessed.size()) {
                currentCard -= cardsPossessed.size(); 
            }
            cardL.setIcon(new ImageIcon(getClass().getResource("cards/" + cardsPossessed.get(currentCard) + ".png"))); //display next card in the cardsPossesed list as we added one to the currentCard variable
        }

        //if main menu is button close this window and open the main menu
        if (action.equals("mainMenu")) {
            Menu x = new Menu();
            x.setVisible(true);
            dispose();
        }

        //if new game button is pressed, close the window and reopen to start a new game
        if (action.equals("newGame")) {
            Monopoly x = new Monopoly();
            x.setVisible(true);
            dispose();
        }

        //if help button is pressed, open monopoly wikipedia page on your browser
        if (action.equals("help")) {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI("https://en.wikipedia.org/wiki/Monopoly_(game)"));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace(); 
                }
            } else {
                JOptionPane.showMessageDialog(this,
                    "Opening a browser is not supported on your system.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }  
        }
        
        //if quit button is pressed, quit the program 
        if (action.equals("quit")) {
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        //open the window
        Monopoly x = new Monopoly();
        x.setVisible(true);
    }
}
