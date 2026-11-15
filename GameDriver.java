//Trent Hardacre
//APCSA Text Based Video Game
//Mrs. Ramsey
//This is the GameDriver class that contains the main method to start the game.

public class GameDriver {
    public static void main(String[]args){
        Game game = new Game();
        game.setUp();
        game.gameLoop();
    }
    
}
