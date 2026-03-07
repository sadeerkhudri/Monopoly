/*
Name: Sadeer Al-Khudri
Description: Main menu, prompts user to quit which quits program, open the wikipedia page for monopoly for help with rules or anything else, or start a new game.
starting a new game closes the window and opens a new one with the actual monopoly game. First user/users are prompted with a customizable playerSelection screen, 
where players can choose their token, player 3name, and player colour, you must choose a token before continuing to the next player however if you don't enter a colour
using the custom rgb color chooser or a player name they will just default to black and Player (whatever numebr you are) respectively. You can't start the game until 
you have filled out at least two players worth of information and you can't click next player once you reach 6 players satisfying monopoly 2-6 player range. Once 
start game is clicked, the first players turn will begin displaying his name, money, and icon next to his money as well as on the actual board. They are then prompted 
to roll the dice. Going forward monopoly just works as normal, I'm not gonna explain the the whole game, rather I will explain each mechanic in monopoly I've added for a 
more organized description. 
Dice Roll - Rolling the dice plays a dice animatioon and then moves the players token the amount shown on the dice added up spaces in a clockwise fasion around the board
BuyProperty - Once you land on a property if it is unowned you can buy it for the price shown on the bord and then it will become yours
Rent - If you land on a property that is owned by someone else you are forced to pay them the rent shown on the card 
Railroad - If you land on a railroad that is owned by someone else, depending on how many railroads they own you pay them the rent shown on the card (for example if
you land on a railroad that is owned by a player who has 2 railraods you pay him 100 rather than 50 if he only had one)
Utility - If you land on a utility owned by someone else, depending if they have the second on are not you have to pay them the amount shown on the dice multiplied by
either 4 or 10 depending if they own in the second one (for example if you land on a utility that is owned by someone else and they also own another utility and you
rolled a 8 to get to that space you must pay them 8*10 = 80)
Doubles - If you roll a double once your done with your turn you must roll and play again, however if you roll 3 doubles in a row you are directed straight to jail
Jail - If you land on the go to jail space or roll 3 doubles in a row you go straight to jail. To escape jail you can either bail yourself out which costs $50 and then 
roll and move as normal or you can try rolling a double, if you roll a double you escape jail and move as normal, however if you don't roll a double you stay in jail and
your turn ends. If by your third try of rolling doubles you don't get a double your forced to pay the 50 dollars bail and you move as shown on the dice like normal. 
Passing Go - If you pass or land on go you get 200 dollars
Tax - If you land on one of the two tax spaces shown on the board you must pay the amount shown on the space.
Bankrupt - If at the end of your turn you have less than $0 meaning negative money you are declared bankrupt and your player is deleted from the game, all of the properties
you owned get returned to the bank and are available to purchase by other players if they land on them 
How to Win - Once everyone goes bankrupt and there is only one player left, that player is declared the winner
Mechanics I haven't added yet (in progress): 
- colour sets/houses/hotels
- mortgaging properties
- chance/community chest
- player trading 
- saving/loading game
Ressources: 
https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable - Invisible Button while still being in the same layer
https://stackoverflow.com/questions/1006611/java-swing-timer - Java Swing Timer
https://stackoverflow.com/questions/1625855/how-to-disable-javax-swing-jbutton-in-java - setEnabled(false)
https://stackoverflow.com/questions/10037644/opening-a-url-from-a-jbutton-in-simple-java-program - Open link using button
https://stackoverflow.com/questions/2979383/how-to-clear-the-console-using-java - clear()
https://www.geeksforgeeks.org/multidimensional-arrays-in-java/ - 2D Arrays 
https://www.geeksforgeeks.org/multidimensional-collections-in-java/ - 2D Lists
https://www.tutorialspoint.com/how-to-implement-the-runnable-interface-using-lambda-expression-in-java - Runnable variable type and Lambda expressions
https://stackoverflow.com/questions/22352930/terminating-a-java-program - System.exit(0)
https://www.digitalocean.com/community/tutorials/convert-char-to-string-java - String.valueOf()
https://stackoverflow.com/questions/5895829/resizing-image-in-java - Image Scaling
https://stackoverflow.com/questions/2966334/how-do-i-set-the-colour-of-a-label-coloured-text-in-java - Changing text colour
https://docs.oracle.com/javase/tutorial/uiswing/components/colorchooser.html - Colour Chooser
https://www.daniweb.com/programming/software-development/threads/243234/how-to-change-the-font-size-and-text-position-of-a-textfield - Customized TextField Text/Font
https://stackoverflow.com/questions/3519151/how-to-limit-the-number-of-characters-in-jtextfield - JTextField character limits (Plain Document method)
https://stackoverflow.com/questions/9119481/how-to-present-a-simple-alert-message-in-java - JOptionPane.showMessageDialog(null, str);
https://www.muradnabizade.com/backgroundmusicjava - how to play music 
*/

//all imoports 
package Monopoly;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener {    
    JLayeredPane layeredPane = new JLayeredPane();
    private Clip clip;

    //custom invisible buttons
    public void customButton(JButton btn) {
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setForeground(new Color(0, 0, 0, 0));
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Menu() {
        //setup
        super("Monopoly");
        setSize(1280, 748);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layeredPane.setPreferredSize(getSize());
        setContentPane(layeredPane);

        //background image
        ImageIcon menuImage = new ImageIcon(getClass().getResource("backgrounds/menu.png"));
        JLabel bg = new JLabel(menuImage);
        bg.setBounds(0, 0, menuImage.getIconWidth(), menuImage.getIconHeight());
        layeredPane.add(bg, Integer.valueOf(0));

        //all the buttons
        ImageIcon newGame = new ImageIcon(getClass().getResource("ui/new_game.png"));
        JLabel newGameL = new JLabel(new ImageIcon(newGame.getImage().getScaledInstance(150, 44, Image.SCALE_SMOOTH)));
        newGameL.setBounds(810, 340, 150, 44);
        layeredPane.add(newGameL, Integer.valueOf(1));
        JButton newGameB = new JButton("newGame");
        newGameB.addActionListener(this);
        newGameB.setBounds(810, 340, 150, 44);
        layeredPane.add(newGameB, Integer.valueOf(1));
        customButton(newGameB);

        ImageIcon help = new ImageIcon(getClass().getResource("ui/help.png"));
        JLabel helpL = new JLabel(new ImageIcon(help.getImage().getScaledInstance(150, 44, Image.SCALE_SMOOTH)));
        helpL.setBounds(810, 400, 150, 44);
        layeredPane.add(helpL, Integer.valueOf(1));
        JButton helpB = new JButton("help");
        helpB.addActionListener(this);
        helpB.setBounds(810, 400, 150, 44);
        layeredPane.add(helpB, Integer.valueOf(1));
        customButton(helpB);

        ImageIcon quit = new ImageIcon(getClass().getResource("ui/quit.png"));
        JLabel quitL = new JLabel(new ImageIcon(quit.getImage().getScaledInstance(150, 44, Image.SCALE_SMOOTH)));
        quitL.setBounds(810, 460, 150, 44);
        layeredPane.add(quitL, Integer.valueOf(1));
        JButton quitB = new JButton("quit");
        quitB.addActionListener(this);
        quitB.setBounds(810, 460, 150, 44);
        layeredPane.add(quitB, Integer.valueOf(1));
        customButton(quitB);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        //if newGame button is pressed close this window and open the monopoly window 
        if (action.equals("newGame")) {
            Monopoly x = new Monopoly();
            x.setVisible(true);
            dispose();
        }

        //if help button is pressed open the wikipedia page about monopoly on your browser
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

        //if quit button is pressed quit the program
        if (action.equals("quit")) {
            System.exit(0);
        }
    }

    //play music on loop function
    void playMusic(String resourcePath) {
        try {
            URL url = getClass().getResource(resourcePath);
            if (url == null) {
                System.err.println("Music resource not found: " + resourcePath);
                return;
            }
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        //open window and play music
        Menu x = new Menu();
        x.playMusic("audio/monopoly_music.wav");
        x.setVisible(true);
    }
}