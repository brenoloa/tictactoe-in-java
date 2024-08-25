import  java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class jogodavelha {
    int janela_largura = 600;
    int janela_altura = 650; // espaçamento de 50x pra colocar um painel


    JFrame frame = new JFrame("jogo da velha");
    JLabel textlabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String jogadorX = "X";
    String jogadorO = "O";
    String jogador_atual = jogadorX;

    boolean gameOver = false;

    int jogadas = 0;

    jogodavelha() {
        frame.setVisible(true);
        frame.setSize(janela_largura, janela_altura);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        textlabel.setBackground(Color.darkGray);
        textlabel.setForeground(Color.white);
        textlabel.setFont(new Font("Arial", Font.BOLD, 50));
        textlabel.setHorizontalAlignment(JLabel.CENTER);
        textlabel.setText("Jogo da Velha");
        textlabel.setOpaque(true);
        
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textlabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                board[i][j] = tile;
                boardPanel.add(tile);
                
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));    
                tile.setFocusable(false);
                
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == ""){                           
                            tile.setText(jogador_atual);                      
                            jogadas++;
                            checkWinner();
                            if (!gameOver){

                                jogador_atual = jogador_atual == jogadorX ? jogadorO : jogadorX;
                                textlabel.setText("Jogador =>: " + jogador_atual); 
                            
                            }

                        }

                    
                    }
                    
                });
            
            }

        }
    }

    void checkWinner() {
        // horizontal
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText() == "") continue;

            if (board[i][0].getText() == board[i][1].getText() && 
                board[i][1].getText() == board[i][2].getText()) {
                for (int j = 0; j < 3; j++) {
                    setWinner(board[i][j]);

                }
                gameOver = true;
                return;
            }
        }
    
        // vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") continue;

            if (board[0][c].getText() == board[1][c].getText() && 
                board[1][c].getText() == board[2][c].getText()) {
                for (int j = 0; j < 3; j++) {
                    setWinner(board[j][c]);
                }
                gameOver = true;
                return;
                }

        }
    
        // diagonal
        if (board[0][0].getText() == board[1][1].getText() && 
            board[1][1].getText() == board[2][2].getText() && 
            board[0][0].getText() != "") {
            for (int i = 0; i < 10; i++) {
                setWinner(board[i][i]);
                
            }
            gameOver = true;
            return;
        }
        // anti diago

        if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "") {
                setWinner(board[0][2]);
                setWinner(board[1][1]);
                setWinner(board[2][0]);
                gameOver = true;
                return;

            }
        //empate
        if (jogadas == 9){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    setTie(board[i][j]);
                }           
            }
            gameOver = true;
            //return;
        }
    
    }
    
    

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textlabel.setText("Vencedor: " + jogador_atual);
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textlabel.setText("empate.");
    }
}



