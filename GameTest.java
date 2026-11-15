//Trent Hardacre
//APCSA Text Based Video Game
//Mrs. Ramsey
//This is the GameTest class that tests the searching and navigation methods.

public class GameTest {
    public static void main(String[] args){
        Room fOffice = new Room("Front Office", "Test front office", false);
        Room supplyCloset = new Room("Supply Closet", "Test supply closet", true);
        Room cafeteria = new Room("Cafeteria", "Test cafeteria", true);
        Room mathRoom = new Room("Math Room", "Test math room", true);
        Room pOffice = new Room("Principal's Office", "Test principal's office", true);
        fOffice.setNeighbors(supplyCloset, cafeteria, mathRoom, pOffice);

        Player player = new Player(fOffice);
        Enemy broom = new Enemy(20, "Broom", 2);
        supplyCloset.setEnemy(broom);

        GameTest.check("North of the front office is the supply closet", fOffice.getNeighbor("north") == supplyCloset);
        GameTest.check("Supply closet has no exit to the north", supplyCloset.getNeighbor("north") == null);
        GameTest.check("Typo 'nroth' returns null", fOffice.getNeighbor("nroth") == null);
        GameTest.check("Power starts off", fOffice.isPowerOn() == false);

        player.changeRooms(fOffice.getNeighbor("north"));
        GameTest.check("Player moves into the supply closet", player.getRoom() == supplyCloset);

        GameTest.check("Player can't enter principal's office with 0 items", player.getKeys() < 3);

        player.addKeys(3);
        GameTest.check("Player can enter principal's office with 3 items", player.getKeys() == 3);

        player.changeRooms(fOffice);
        player.changeRooms(supplyCloset);
        GameTest.check("Re-entering a room doesn't use up items", player.getKeys() == 3);

        fOffice.setPower(true);
        GameTest.check("Searching the front office turns the power on", fOffice.isPowerOn() == true);

        GameTest.check("Searching the supply closet finds the broom", supplyCloset.hasEnemy() && supplyCloset.getEnemy() == broom);

        player.attack(broom);
        player.attack(broom);
        GameTest.check("Broom is defeated and can't be fought again", broom.isAlive() == false);
    }

    public static void check(String testName, boolean passed){
        if(passed == true){
            System.out.println("PASS: " + testName);
        }
        else{
            System.out.println("FAIL: " + testName);
        }
    }
}