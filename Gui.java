/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ass3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.util.Map;
import java.util.stream.IntStream;

public class Gui implements ActionListener {

    private final NimPlayer Player1 = new PlayerAI("A");
    private final NimPlayer Player2 = new PlayerAI("B");
    private NimGame game = new NimGame(Player1, Player2);
    private boolean TwoPlayersMode = false;
    private boolean isFirstPlayerTurn = true;
    private boolean humanPlayerMoveSuccess = false;
    private boolean gameStart = false;

    private final JPanel playTypePanel;
    private final JButton playType1;
    private final JButton playType2;

    private final JPanel stonesRows[];
    // private final JPanel stoneRow[];
    private JButton stone[][];
    private JButton button[];
    private JButton NextButton;
    JButton[][] buttonArray;

    private final JPanel binaryRows[];
    private JLabel binaryLabel[];
    private JLabel binarynimsum;

    private final JPanel gameMessagesPanel;
    private JLabel messageBox; //can be final lets see

    public Gui() {

        playTypePanel = new JPanel();
        playTypePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        playTypePanel.setLayout(new FlowLayout());
        playType1 = new JButton("Human vs Human");
        playType2 = new JButton("PC vs Human");
        playType1.setBackground(Color.ORANGE);
        playType1.setForeground(Color.WHITE);
        playType2.setBackground(Color.ORANGE);
        playType2.setForeground(Color.WHITE);
        playTypePanel.add(playType1);
        playTypePanel.add(playType2);
        playType1.addActionListener(this);
        playType2.addActionListener(this);

        JPanel stonesPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        buttonArray = new JButton[game.piles.length][7];
        stonesRows = new JPanel[game.piles.length];
        for (int row = 0; row < game.piles.length; row++) {
            stonesRows[row] = new JPanel(new FlowLayout());
            for (int col = 0; col < game.piles[row]; col++) {
                JButton button2 = new JButton();
                button2.setBorder(new LineBorder(Color.GREEN, 3, true));
                button2.setPreferredSize(new Dimension(50, 50));
                button2.addActionListener(this);
                stonesRows[row].add(button2);
                buttonArray[row][col] = button2;
            }
            stonesPanel.add(stonesRows[row]);
        }
        JPanel NButton = new JPanel();
        stonesPanel.add(NButton);
        NextButton = new JButton("NEXT");
        NextButton.setBackground(Color.ORANGE);
        NextButton.setForeground(Color.WHITE);
        NextButton.setPreferredSize(new Dimension(100, 50));
        NButton.add(NextButton);
        NextButton.addActionListener(this);

        Font myFont = new Font(Font.SANS_SERIF, Font.BOLD, 13);
        JPanel binaryPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        binaryRows = new JPanel[game.piles.length];
        binaryLabel = new JLabel[game.piles.length];
        for (int i = 0; i < game.piles.length; i++) {
            binaryRows[i] = new JPanel(new FlowLayout());
            binaryLabel[i] = new JLabel(toBinaryString(game.piles[i], 3));
            binaryLabel[i].setFont(myFont);
            binaryLabel[i].setPreferredSize(new Dimension(50, 50));
            binaryRows[i].add(binaryLabel[i]);
            binaryPanel.add(binaryRows[i]);
        }
        binarynimsum = new JLabel("NIM SUM: " + Integer.toBinaryString(calculateNimSum(game.piles)));
        binarynimsum.setFont(myFont);
        binarynimsum.setPreferredSize(new Dimension(100, 50));
        binaryPanel.add(binarynimsum);

        gameMessagesPanel = new JPanel();
        LineBorder msgBorder = new LineBorder(Color.lightGray, 2);
        messageBox = new JLabel(" To start the game, choose your play type and click NEXT: Human vs Human / PC vs Human...");
        messageBox.setPreferredSize(new Dimension(600, 50));
        messageBox.setFont(myFont);
        messageBox.setBorder(msgBorder);
        gameMessagesPanel.add(messageBox);

        //main frame
        JFrame frame = new JFrame();
        frame.add(playTypePanel, BorderLayout.NORTH);
        frame.add(stonesPanel, BorderLayout.WEST);
        frame.add(binaryPanel, BorderLayout.EAST);
        frame.add(gameMessagesPanel, BorderLayout.SOUTH);
        frame.setTitle("MISERE NIMGAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private static String toBinaryString(int n, int length) {
        String binary = Integer.toBinaryString(n);
        int padding = length - binary.length();
        if (padding > 0) {
            return "0".repeat(padding) + binary;
        } else {
            return binary;
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        new Gui();

    }

    public void updatePiles(int pile, int stones) {
        //game.piles[pile] = game.piles[pile] - stones;
        //i have to hide 3 stones from pile 2
        for (int i = 0; i < stones; i++) {
            buttonArray[pile][i].setVisible(false);
        }
        binaryLabel[pile].setText(toBinaryString(game.piles[pile], 3));
        binarynimsum.setText("NIM SUM: " + Integer.toBinaryString(calculateNimSum(game.piles)));
    }

    public void handlePCMove() {
        Map.Entry<Integer, Integer> move = Player1.nextMove(game.piles.clone());
        game.piles[move.getKey()] -= move.getValue();
        messageBox.setText(Player1.getPlayerName() + " removed " + move.getValue() + " stones from pile " + (move.getKey() + 1));
        updatePiles(move.getKey(), move.getValue());
        binaryLabel[move.getKey()].setText(toBinaryString(game.piles[move.getKey()], 3));
        binarynimsum.setText("NIM SUM: " + Integer.toBinaryString(calculateNimSum(game.piles)));
    }

    public static int calculateNimSum(int piles[]) {
        int nimsum = 0;
        for (int i = 0; i < piles.length; i++) {
            nimsum = piles[i] ^ nimsum;
        }
        return nimsum;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (IntStream.of(game.piles).sum() > 0) {
            if (e.getSource() == playType1) {

                messageBox.setText("You are now in two players mode. Player A's turn...");
                TwoPlayersMode = true;

            }

            if (e.getSource() == playType2) {
                //game = new NimGame(new PlayerAI("PC"), new HumanPlayer("B"));
                messageBox.setText("You are playing vs PC...");
                TwoPlayersMode = false;
            }
            if (e.getSource() == NextButton) {
                
                isFirstPlayerTurn = !isFirstPlayerTurn;

                if (!isFirstPlayerTurn) {
                    messageBox.setText("Player A's turn");
                } else {
                    messageBox.setText("Player B's turn");
                }
                gameStart = true;
                playType1.setEnabled(false);
                playType2.setEnabled(false);
                if (!TwoPlayersMode) {
                    if (isFirstPlayerTurn) {
                        handlePCMove();
                        humanPlayerMoveSuccess = false;
                    } else {
                        if (humanPlayerMoveSuccess) {
                            isFirstPlayerTurn = !isFirstPlayerTurn;
                            //handleHumanMove();
                            actionPerformed(e);
                        }
                    }
                }
            }

            for (int i = 0; i < game.piles.length; i++) {

                for (int j = 0; j < 7; j++) {

                    if (e.getSource() == buttonArray[i][j]) {

                        if (gameStart) {
                            buttonArray[i][j].setVisible(false);
                            humanPlayerMoveSuccess = true;
                            game.piles[i] -= 1;
                            binaryLabel[i].setText(toBinaryString(game.piles[i], 3));
                            binarynimsum.setText("NIM SUM: " + Integer.toBinaryString(calculateNimSum(game.piles)));
                            
                        }
                    }
                }
            }

        } else {
            messageBox.setText((isFirstPlayerTurn ? Player1.getPlayerName() : Player2.getPlayerName()) + " won!");
        }

    }

}

//PC vs human
/*     
problems:
pile 3 stone problem
some stones cannot be accessed
can remove stones from multiple rows

 */
