package gamedevelopment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class tictactoe extends JFrame  implements ActionListener{

	public static int BOARD_SIZE=3;
	
	public static enum GameStatus{
		Incomplete,XWins,ZWins,Tie
	}
	
	private JButton[][] buttons=new JButton[BOARD_SIZE][BOARD_SIZE];
	
	boolean crossTurn=true;
	
	public tictactoe() {
		super.setTitle("tictactoe");
		super.setSize(800,800);
		GridLayout grid=new GridLayout(BOARD_SIZE,BOARD_SIZE);
		super.setLayout(grid);
		Font font=new Font("Comic Sans",1,150);
		for(int row=0;row<BOARD_SIZE;row++) {
			for(int col=0;col<BOARD_SIZE;col++) {
				JButton button=new JButton("");
				buttons[row][col]=button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
			}
			
		}
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton clickedButton=(JButton)e.getSource();
		makeMove(clickedButton);
		
		GameStatus gs=this.getGameStatus();
		if(gs==GameStatus.Incomplete) {
			return;
		}
		declareWinner(gs);
		int choice=JOptionPane.showConfirmDialog(this, "Do you want to restart a Game");
	    if(choice==JOptionPane.YES_OPTION) {
	    	for(int row=0;row<BOARD_SIZE;row++) {
				for(int col=0;col<BOARD_SIZE;col++) {
					
					
					buttons[row][col].setText("");
					
				}
				
			}
	    	crossTurn=true;
	    }
	    else {
	    	super.dispose();
	    }
	}
	private void makeMove(JButton clickedButton) {
		String btntext=clickedButton.getText();
		if(btntext.length()>0) {
			JOptionPane.showMessageDialog(this, "Invalid Move");
		}
		else {
			if(crossTurn) {
				clickedButton.setText("X");
			}
			else {
				clickedButton.setText("0");
			}
			crossTurn=!crossTurn;
		}
				
	}
	private GameStatus getGameStatus() {
		String text1="",text2="";
		int row=0,col=0;
		
		//row
		row=0;
		while(row<BOARD_SIZE) {
			col=0;
			while(col<BOARD_SIZE-1) {
				text1=buttons[row][col].getText();
				text2=buttons[row][col+1].getText();
				if(!text1.contentEquals(text2)||text1.length()==0) {
					break;
				}
				col++;
			}
			if(col==BOARD_SIZE-1) {
				if(text1.contentEquals("X")){
					return GameStatus.XWins;				
			}
			else {
				return GameStatus.ZWins;
			}
		}
			row++;
		}
		
		
		//column
		col=0;
		while(col<BOARD_SIZE) {
			row=0;
			while(row<BOARD_SIZE-1) {
				text1=buttons[row][col].getText();
				text2=buttons[row+1][col].getText();
				if(!text1.contentEquals(text2)||text1.length()==0) {
					break;
				}
				row++;
			}
			if(row==BOARD_SIZE-1) {
				if(text1.contentEquals("X")){
					return GameStatus.XWins;				
			}
			else {
				return GameStatus.ZWins;
			}
		}
			col++;
		}
		
		
		//diagonal1
		
		row=0;
		col=0;
		while(row<BOARD_SIZE-1) {
			text1=buttons[row][col].getText();
			text2=buttons[row+1][col+1].getText();
			if(!text1.contentEquals(text2)||text1.length()==0) {
				break;
			}	
			row++;
			col++;
		}
		if(row==BOARD_SIZE-1) {
			if(text1.contentEquals("X")){
				return GameStatus.XWins;				
		}
		else {
			return GameStatus.ZWins;
		}
	}
	
		//diagonal2
		row=BOARD_SIZE-1;
		col=0;
		while(row>0) {
			text1=buttons[row][col].getText();
			text2=buttons[row-1][col+1].getText();
			if(!text1.contentEquals(text2)||text1.length()==0) {
				break;
			}	
			row--;
			col++;
		}
		if(row==0) {
			if(text1.contentEquals("X")){
				return GameStatus.XWins;				
		}  
		else {
			return GameStatus.ZWins;
		}
	}
		
		String txt="";
		for(row=0;row<BOARD_SIZE;row++) {
			for(col=0;col<BOARD_SIZE;col++) {
				txt=buttons[row][col].getText();
				if(txt.length()==0) {
					return GameStatus.Incomplete;
				}
			}
		}
		return GameStatus.Tie;
	}
	private void declareWinner(GameStatus gs) {
		if(gs==GameStatus.XWins) {
			JOptionPane.showMessageDialog(this, "Mayank Wins");
		}
		else if(gs==GameStatus.ZWins) {
			JOptionPane.showMessageDialog(this, "Other person Wins");	
		}
		else {
			JOptionPane.showMessageDialog(this, "It is a tie");
		}
	}
	
	
	
}

