/*

===== OBJECT HEIRARCHY =======

SCENE

            1) Object
            2) Obstacle (Rocks, trees)
            3) Creature
            4) Enemy, Player, Friend (Dogs, Orcs, You, Bosses)
            
 */

        /* ========THIS FRAGMENT OF CODE DEMONSTRATES HOW TO BUILD A PROPER COORD PLANE ================
           ========THE 'i' in the for loop corresponds to y values, 'j' to x.===========================
        int yrange = 5;
        int xdomain = 10;
        String[][] board = new String[xdomain][yrange];
        //populate the grid
        for(int i = yrange-1; i >= 0; i--){
            for(int j = 0; j<xdomain; j++){
                board[j][i] = "|" + j + ", " + i + "|";
            }
        }
        //print grid
        for(int i = yrange-1; i >= 0; i--){
            for(int j = 0; j<xdomain; j++){
                System.out.print(board[j][i]);
            }
            System.out.println("");
        }*/

package venture2;
import java.util.*;

class Scene{
    static int ybound, xbound;
    static Object[][] board;
    Player thisPlayer;
    // Below needs to be updated whenever a creature is created.
    public static Creature[] creatures;
    
    //Constructors populate a grid, each space filled with a six-character blank string.
    //Can specify bounds of the grid with params, or 10x10 by default.
    Scene(int xbound, int ybound){
        Scene.ybound = ybound;
        Scene.xbound = xbound;
        board = new Object[xbound][ybound];
        for(int i = ybound-1; i >=0; i--){
            for(int j = 0; j < xbound; j++){
                board[j][i] = "      ";
            }
        }
    }
    Scene(){
        ybound = 10;
        xbound = 10;
        board = new Object[xbound][ybound];
        for(int i = ybound-1; i >=0; i--){
            for(int j = 0; j < xbound; j++){
                board[j][i] = "     ";
            }
        }
    }
    @Override
    public String toString(){
        String op = "";
        // i represents y line, j represents x line. 
        for(int i = ybound-1; i >= 0; i--){
            for(int j = 0; j < xbound; j++){
                op += "|" + board[j][i] + "|";
            }
            op += "\n";
        }
        
        return op;
    }
    boolean isClear(){
        for(Object e: creatures){
            if(e != null){
                return false;
            }
        }
        return true;
    }
    void generateScene(int numOfObstacles, int numOfEnemies){
        for(int i = 0; i < numOfObstacles; i++){
            int randx = (int) (Math.random() * xbound);
            int randy = (int) (Math.random() * ybound);
            int rockOrTree = (int)(Math.random() * 2);
            if(rockOrTree == 0){
                board[randx][randy] = new Rock(randx, randy);
            } else {
                board[randx][randy] = new Tree(randx, randy);
            }
        }
        creatures = new Creature[numOfEnemies + 1]; // Set the length of creature[] to numOfEnemies (for each orc)
        for(int i = 0; i < numOfEnemies; i++){
            int randx = (int) (Math.random() * xbound);
            int randy = (int) (Math.random() * ybound);
            if(i <= 9){
                board[randx][randy] = new Orc(randx, randy, "ORC " + i + " "); //Create a new orc and put him on the board, then...
            } else if(i <= 99){
                board[randx][randy] = new Orc(randx, randy, "ORC " + i); // (same as above, just minor adjustment to name)
            } else {
                board[randx][randy] = new Orc(randx, randy, "ORC" + i);
            }
            
            creatures[i] = (Creature) board[randx][randy]; // ...Add the created orc into creatures[]. 
        }
        int randx = (int) (Math.random() * xbound);
        int randy = (int) (Math.random() * ybound);
        board[randx][randy] = new Orc(randx, randy, " BOSS ", 300, 30, 200);
        creatures[numOfEnemies] = (Creature) board[randx][randy]; //place the new boss creature at this board location into the creature[].
        thisPlayer = new Player(0, 0, " BENJ "); //create the scene's player
        board[0][0] = thisPlayer; //place the scene's player on the board.
        
    }
    void generateScene(int numOfObstacles, int numOfEnemies, Player savedPlayer){ //Create a scene with a player that already exists
        for(int i = 0; i < numOfObstacles; i++){
            int randx = (int) (Math.random() * xbound);
            int randy = (int) (Math.random() * ybound);
            int rockOrTree = (int)(Math.random() * 2);
            if(rockOrTree == 0){
                board[randx][randy] = new Rock(randx, randy);
            } else {
                board[randx][randy] = new Tree(randx, randy);
            }
        }
        creatures = new Creature[numOfEnemies + 1]; // Set the length of creature[] to numOfEnemies (for each orc)
        for(int i = 0; i < numOfEnemies; i++){
            int randx = (int) (Math.random() * xbound);
            int randy = (int) (Math.random() * ybound);
            if(i <= 9){
                board[randx][randy] = new Orc(randx, randy, "ORC " + i + " "); //Create a new orc and put him on the board, then...
            } else if(i <= 99){
                board[randx][randy] = new Orc(randx, randy, "ORC " + i); // (same as above, just minor adjustment to name)
            } else {
                board[randx][randy] = new Orc(randx, randy, "ORC" + i);
            }
            
            creatures[i] = (Creature) board[randx][randy]; // ...Add the created orc into creatures[]. 
        }
        int randx = (int) (Math.random() * xbound);
        int randy = (int) (Math.random() * ybound);
        board[randx][randy] = new Orc(randx, randy, " BOSS ", 300, 30, 200);
        creatures[numOfEnemies] = (Creature) board[randx][randy]; //place the new boss creature at this board location into the creature[].
        thisPlayer = savedPlayer; //create the scene's player out of an existing Player.
        board[0][0] = thisPlayer; //place the scene's player on the board.        
    }
    
    void takeTurn(Creature thisCreature){
        //========= PLAYER TURN ==============
        if (thisCreature instanceof Player){
            Scanner scanner = new Scanner(System.in);
            System.out.println(thisCreature.name + "'s turn: ");
            String input = scanner.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            switch(input){
                case "quit":
                    thisCreature.health = 0;
                    break;
                case "q":
                    thisCreature.Attack(-1, 1);
                    thisCreature.Move(-1, 1);
                    break;
                case "w":
                    thisCreature.Attack(0, 1);
                    thisCreature.Move(0, 1);
                    break;
                case "e":
                    thisCreature.Attack(1, 1);
                    thisCreature.Move(1, 1);
                    break;
                case "a":
                    thisCreature.Attack(-1, 0);
                    thisCreature.Move(-1, 0);
                    break;                   
                case "s":
                    thisCreature.Attack(0, -1);
                    thisCreature.Move(0, -1);
                    break;                   
                case "d":
                    thisCreature.Attack(1, 0);
                    thisCreature.Move(1, 0);
                    break;
                case "z":
                    thisCreature.Attack(-1, -1);
                    thisCreature.Move(-1, -1);
                    break;                    
                case "x":
                    thisCreature.Attack(0, -1);
                    thisCreature.Move(0, -1);
                    break;                    
                case "c":
                    thisCreature.Attack(1, -1);
                    thisCreature.Move(1, -1);
                    break;                    
                    
            }
        }
        //============== ENEMY AI =================
        if (thisCreature instanceof Enemy){
            int comChoice = (int)(Math.random() * 9);
            switch (comChoice){
                case 1:
                    thisCreature.Attack(-1, 1);
                    thisCreature.Move(-1, 1);
                    break;
                case 2:
                    thisCreature.Attack(0, 1);
                    thisCreature.Move(0, 1);
                    break;
                case 3:
                    thisCreature.Attack(1, 1);
                    thisCreature.Move(1, 1);
                    break;
                case 4:
                    thisCreature.Attack(-1, 0);
                    thisCreature.Move(-1, 0);
                    break;                   
                case 5:
                    thisCreature.Attack(0, -1);
                    thisCreature.Move(0, -1);
                    break;                   
                case 6:
                    thisCreature.Attack(1, 0);
                    thisCreature.Move(1, 0);
                    break;
                case 7:
                    thisCreature.Attack(-1, -1);
                    thisCreature.Move(-1, -1);
                    break;                    
                case 8:
                    break;                    
                case 0:
                    thisCreature.Attack(1, -1);
                    thisCreature.Move(1, -1);
                    break; 
                    
            }
        }
    }
}

// ------------------------ENTITIES----------------------------------------
class Obstacle{
    int xpos, ypos;
    int health;
    int maxHealth;
    String name;
    @Override
    public String toString(){
        return name;
    }

}
class Tree extends Obstacle{
    Tree(int x, int y){
        name = " Tree ";
        health = 12;
        maxHealth = health;
        xpos = x;
        ypos = y;
    }
}
class Rock extends Obstacle{
    Rock(int x, int y){
        name = " Rock ";
        health = 16;
        maxHealth = 16;
        xpos = x;
        ypos = y;
    }
}
class Creature extends Obstacle{
    int damage;
    int exp;
    int level;

    void Move(int xdeg, int ydeg) throws ArrayIndexOutOfBoundsException{
            try{
                if(Scene.board[xpos+xdeg][ypos+ydeg] instanceof Obstacle){
            //do nothing
                } else {
                    Scene.board[xpos][ypos] = "      ";
                    this.xpos += xdeg;
                    this.ypos += ydeg;
                    Scene.board[xpos][ypos] = this;
                }
            } catch(ArrayIndexOutOfBoundsException e){//do nothing
        }
    }
    void levelUp(){
        this.damage = (int) (this.damage * 1.2);
        this.maxHealth = (int) (this.maxHealth * 1.2);
        this.health = maxHealth;
    }
    
    void Attack(int xdeg, int ydeg) throws ArrayIndexOutOfBoundsException{
        try{
            if(Scene.board[this.xpos + xdeg][this.ypos + ydeg] instanceof Obstacle){ // "this._pos + _deg" represents the target of the attack.
                Obstacle otherObstacle = (Obstacle)(Scene.board[this.xpos + xdeg][this.ypos + ydeg]); // Create an obstacle out of the contents of the target.
                otherObstacle.health -= this.damage;
                System.out.println(this.name + " did " + this.damage + " damage to " + otherObstacle.name);
                if(otherObstacle.health <= 0){ //If the target is dead... 
                    System.out.println(otherObstacle.name + " died.");
                    if(Scene.board[this.xpos + xdeg][this.ypos + ydeg] instanceof Creature){ //If the target is a creature...
                        this.exp += ((((Creature)(otherObstacle)).exp) + ((((Creature)(otherObstacle)).level-1) * 100))/this.level; //Add exp of the target to the attacker, div'd by level
                        System.out.println(this + " gained " + (((((Creature)(otherObstacle)).exp) + ((((Creature)(otherObstacle)).level-1) * 100))/this.level) + " EXP.");
                        if(this.exp >= 100){ //If attacker's exp >= 100...
                            this.level++; //Level up attacker
                            this.exp -= 100; //Reset attacker's exp by taking away 100, rolling over leftovers. 
                            this.levelUp();
                            System.out.println(this.name + " leveled up! " + this.name + " is now level " + this.level + ". ");
                        }
                    }
                    if(otherObstacle instanceof Creature){
                        Scene.creatures[java.util.Arrays.asList(Scene.creatures).indexOf((Creature) otherObstacle)] = null;
                    }
                    Scene.board[otherObstacle.xpos][otherObstacle.ypos] = "      "; //Set the board at coord of the target to "     "
                }
            } else {
                //do nothing
            }
        } catch(ArrayIndexOutOfBoundsException e){
            //do nothing
        }
    }
}
class Enemy extends Creature{
    
}
class Orc extends Enemy{
    Orc(int x, int y){
        xpos = x;
        ypos = y;
        name = " ORC  ";
        health = 100;
        maxHealth = health;
        damage = 20;
        exp = 50;
        level = 1;
    }
    Orc(int x, int y, String name){
        xpos = x;
        ypos = y;
        this.name = name;
        health = 100;
        maxHealth = health;
        damage = 20;
        exp = 50;
        level = 1;
    }
    
    Orc(int x, int y, String name, int health, int damage, int exp){
        xpos = x;
        ypos = y;
        this.level = 1;
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.exp = exp;
    }
}

class Player extends Creature{
    Player(int x, int y, String name){
        xpos = x;
        ypos = y;
        this.name = name;
        level = 1;
        exp = 0;
        damage = 20;
        health = 100;
        maxHealth = health;
        
    }
}
class Friend extends Creature{
                           
}
class Dog extends Friend{
    Dog(int x, int y){
        xpos = x;
        ypos = y;
        level = 1;
        name = " DOG  ";
        damage = 20;
        exp = 20;
        health = 80;
        maxHealth = health;
    }
}

// -----------------------MAIN PROGRAM-----------------------------------------
public class Venture2 {


    public static void main(String[] args) {
        int turn = 1;
        
        Scene scene1 = new Scene(10, 10); // Defines the grid as [first] by [second]
        scene1.generateScene(0, 20); // Populates the grid with [first] obstacles, and [second] enemies, plus one boss. 
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n ======================= TURN 0 =================================== \n\n                        GAME START\n");
        System.out.println(scene1);
        System.out.println("EXP: " + scene1.thisPlayer.exp + "  LEVEL: " + scene1.thisPlayer.level + "  HEALTH: " + scene1.thisPlayer.health + "  DAMAGE: " + scene1.thisPlayer.damage);
        while(scene1.thisPlayer.health > 0 && scene1.isClear() == false){
            System.out.println("\n ======================= TURN " + turn + " =================================== \n");
            scene1.takeTurn(scene1.thisPlayer);
            //takeTurn for each creature.
            for(Creature thisCreature: Scene.creatures){
                if (thisCreature == null){//do nothing
                } else {
                    scene1.takeTurn(thisCreature);
                }
            }
            System.out.println(scene1);
            System.out.println("EXP: " + scene1.thisPlayer.exp + "  LEVEL: " + scene1.thisPlayer.level + "  HEALTH: " + scene1.thisPlayer.health + "/" + scene1.thisPlayer.maxHealth + "  DAMAGE: " + scene1.thisPlayer.damage);
            turn++;
        }
        Scene.board[scene1.thisPlayer.xpos][scene1.thisPlayer.ypos] = "      ";
        scene1.thisPlayer.xpos = 0;
        scene1.thisPlayer.ypos = 0;
        // ============== Start a new scene, because why not? =============
        scene1.generateScene(0, 20, scene1.thisPlayer);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n ======================= TURN 0 =================================== \n\n                        GAME START\n");
        System.out.println(scene1);
        System.out.println("EXP: " + scene1.thisPlayer.exp + "  LEVEL: " + scene1.thisPlayer.level + "  HEALTH: " + scene1.thisPlayer.health + "  DAMAGE: " + scene1.thisPlayer.damage);
        while(scene1.thisPlayer.health > 0){
            System.out.println("\n ======================= TURN " + turn + " =================================== \n");
            scene1.takeTurn(scene1.thisPlayer);
            //takeTurn for each creature.
            for(Creature thisCreature: Scene.creatures){
                if (thisCreature == null){//do nothing
                } else {
                    scene1.takeTurn(thisCreature);
                }
            }
            System.out.println(scene1);
            System.out.println("EXP: " + scene1.thisPlayer.exp + "  LEVEL: " + scene1.thisPlayer.level + "  HEALTH: " + scene1.thisPlayer.health + "/" + scene1.thisPlayer.maxHealth + "  DAMAGE: " + scene1.thisPlayer.damage);
            turn++;
        }
    }
}
