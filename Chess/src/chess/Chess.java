package chess;
import java.util.*;

//author Benjamin Burnham
//This project produces a chessboard.
//To select a piece, input the coordinates of that piece in the command line, starting with the horizontal coordinate.
//For example, to select the LEFT BLACK ROOK, input 07 when prompted.
//To move that selected piece, type in the coordinates of the desired location.
//There is no enforcement of rules, or AI opponent. 

class Board{
    public static Object[][] board = new Object[8][8];
    Board(){
        //Populate the board with blank spaces
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if((j+i) % 2 == 0){
                    board[j][i] = "::::::";
                } else {
                    board[j][i] = "      ";
                }
            }
        }
        //Set the white pieces
        board[0][0] = new Piece(0,0," Rook ");
        board[7][0] = new Piece(7,0," Rook ");
        board[1][0] = new Piece(1,0," Knyt ");
        board[6][0] = new Piece(6,0," Knyt ");
        board[2][0] = new Piece(2,0," Bshp ");
        board[5][0] = new Piece(5,0," Bshp ");
        board[3][0] = new Piece(3,0," Qeen ");
        board[4][0] = new Piece(4,0," King ");
        for(int j = 0; j < 8; j++){
            board[j][1] = new Piece(j, 1, " Pawn ");
        }
        //Set the black pieces
        board[0][7] = new Piece(0,7,"#Rook#");
        board[7][7] = new Piece(7,7,"#Rook#");
        board[1][7] = new Piece(1,7,"#Knyt#");
        board[6][7] = new Piece(6,7,"#Knyt#");
        board[2][7] = new Piece(2,7,"#Bshp#");
        board[5][7] = new Piece(5,7,"#Bshp#");
        board[3][7] = new Piece(3,7,"#Qeen#");
        board[4][7] = new Piece(4,7,"#King#");
        for(int j = 0; j < 8; j++){
            board[j][6] = new Piece(j, 6, "#Pawn#");
        }
    }
    public String toString(){
        String op = "   ---0-------1-------2-------3-------4-------5-------6-------7----\n";
        
        for(int i = 7; i >= 0; i--){
            op += i + "- ";
            for(int j = 0; j < 8; j++){
                if(board[j][i] instanceof Piece){
                    op += "|" + board[j][i] + "|";
                } else if((j + i) % 2 == 0){
                    op += "|" + "::::::" + "|";
                } else {
                    op += "|" + "      " + "|";
                }
            }
            op += " -" + i + "\n";
        }
        op += "   ---0-------1-------2-------3-------4-------5-------6-------7----";
        return op;
    }
}

class Piece{
    int x;
    int y;
    String name;
    Piece(int x, int y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }
    public void move(int x, int y){
        //Remove the piece from its current location
        Board.board[this.x][this.y] = "      ";
        //Set the piece's new coordinates
        this.x = x;
        this.y = y;
        //Update the board with the new piece
        Board.board[this.x][this.y] = this;
        
        //Check for pawn promotion
        if(this.name.equals("#Pawn#") && this.y == 0){
            this.name = "#Qeen#";
            Board.board[this.x][this.y] = this;
        }
        if(this.name.equals(" Pawn ") && this.y == 7){
            this.name = " Qeen ";
            Board.board[this.x][this.y] = this;
        }
        
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}

public class Chess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Piece selected;
        
        Board game = new Board();
        
        do{
            System.out.println(game);
            System.out.println("Enter the coordinates of the piece you want to move. Enter 'q' to quit: ");
            input = scanner.nextLine();
            if(input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q")){break;} //quit
            if(input.length() > 2){
                switch(input){
                    case " Pawn ":
                        selected = new Piece(0, 0, " Pawn ");
                        break;
                    case " Rook ":
                        selected = new Piece(0, 0, " Rook ");
                        break;
                    case " Bshp ":
                        selected = new Piece(0, 0, " Bshp ");
                        break;
                    case " Knyt ":
                        selected = new Piece(0, 0, " Knyt ");
                        break;                        
                    case " Qeen ":
                        selected = new Piece(0, 0, " Qeen ");
                        break;
                    case " King ":
                        selected = new Piece(0, 0, " King ");
                        break;
                    case "#Pawn#":
                        selected = new Piece(0, 0, "#Pawn#");
                        break;
                    case "#Rook#":
                        selected = new Piece(0, 0, "#Rook#");
                        break;
                    case "#Bshp#":
                        selected = new Piece(0, 0, "#Bshp#");
                        break;
                    case "#Knyt#":
                        selected = new Piece(0, 0, "#Knyt#");
                        break;                        
                    case "#Qeen#":
                        selected = new Piece(0, 0, "#Qeen#");
                        break;
                    case "#King#":
                        selected = new Piece(0, 0, "#King#");
                        break;
                    default:
                        selected = new Piece(0, 0, " Pawn ");
                }
            } else {
                selected = (Piece)Board.board[Integer.parseInt(input.substring(0, 1))][Integer.parseInt(input.substring(1))];
            }
            
            System.out.println("You have selected " + selected + ". Enter coordinates to move the piece. To cancel, leave blank and press enter: ");
            input = scanner.nextLine();
            if(input.equals("")){
                //If user inputs blank, do nothing. 
            } else {
                selected.move(Integer.parseInt(input.substring(0, 1)), Integer.parseInt(input.substring(1)));
            }
        } while(true);
    }
    
}
