//Interactive TicTacToe GameBoard – Winter Break 2021 Project 1

//* imports the entire library instead of specifics. 
import java.util.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeBoard implements ActionListener {

	Random random = new Random();
	JFrame frame  =  new JFrame();
	JPanel panelTitle = new JPanel();
	JPanel panelButton = new JPanel();
	JLabel label = new JLabel();
	JButton [] buttons = new JButton[9];
	boolean oTurn;
	TicTacToeGame game;
	
	
	boolean active;
	
	public TicTacToeBoard(){
		//Lines ______ – _____ are setting the GUI frame. Using ORACLE Library to find correct methods. 
		//https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html
		//https://docs.oracle.com/javase/7/docs/api/javax/swing/JLabel.html

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 900);
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		label.setBackground(new Color(25,25,25));
		label.setForeground(new Color(255, 128, 0));
		label.setFont(new Font("ink free", Font.BOLD, 100));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setText("TicTacToe Game");
		label.setOpaque(true);
		
		panelTitle.setLayout(new BorderLayout());
		panelTitle.setBounds(0, 0, 750, 100);
		
		panelButton.setLayout(new GridLayout(3, 3));
		panelButton.setBackground(new Color(100, 100, 100));
		
		
		//adding the buttons:
		for(int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			panelButton.add(buttons[i]);
			buttons[i].setFont(new Font("Serif", Font.BOLD, 90));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this); 
			buttons[i].setName("" + (i+1));
		}
		
		panelTitle.add(label);
		frame.add(panelTitle, BorderLayout.NORTH);
		frame.add(panelButton);
		
		initBoard();
		playerTurns();
		showTitle(false);
	}
	
	
//	try {
//		Thread.sleep(3000);//allows me to add a delay to whose turn is shown
//	} catch (InterruptedException e) {
//		e.printStackTrace();
//	}
	
	
	public void playerTurns() {
		if(random.nextInt(2) == 0) {
			oTurn = true;
		}else {
			oTurn = false;
		}
		toggleTurn();
	}
	
	public void toggleTurn() { //this is defaulting to only O or X based off boolean. Not random
		//This will also stop after 1 turn. 
		if(oTurn) {
			oTurn = false;
			label.setText("X turn");
		}else {
			oTurn = true;
			label.setText("O turn");
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(!active) {
			return;
		}
		
		JButton button = (JButton) e.getSource();
		button.setEnabled(false);
		
		int index = Integer.parseInt(button.getName());
		Square square = this.game.getSquare(index);
		if(square.getValue() != SquareVal.BLANK) {
			return;
		}
		
		if(oTurn) {
			square.setValue(SquareVal.O);
		}else {
			square.setValue(SquareVal.X);
		}
		
		button.setText(square.getValue() == SquareVal.O ? "O" : "X");
		
		String winner = this.game.checkWinner();
		if(winner != null) {
			label.setText(winner);
			active = false;
			playAgain();
		}else {
			toggleTurn();
		}
	}
	
	private void playAgain() {
		int choice = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Game Over",
				JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			initBoard();
			playerTurns();
		} else {
			showTitle(true);
		}
	}
	
	private void showTitle(boolean exit) {
		final JFrame parent = new JFrame();
		JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
		JLabel label = new JLabel();
		JButton button = new JButton();

        button.setText(exit ? "Close" : "Play");
        label.setText("Welcome to TicTacToe");
        
        parent.setUndecorated(true);
        panel.add(label, BorderLayout.CENTER);
        panel.add(button,  BorderLayout.SOUTH);
        parent.add(panel);
        parent.pack(); 
        parent.setSize(400, 400);
        parent.setLocationRelativeTo(null);
        parent.setVisible(true);

        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	parent.setVisible(false);
            	if(exit) {
            		System.exit(0);
            	}
            }
        });
	}


	private void initBoard() {
		game = new TicTacToeGame();
		active = true;
		for(JButton b : buttons) {
			b.setEnabled(true);
			b.setText("");
		}
	}
}
