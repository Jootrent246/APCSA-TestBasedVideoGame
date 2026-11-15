//Trent Hardacre
//APCSA Text Based Video Game
//Mrs. Ramsey
//This is the Room class that contains the room's description, name, and methods to get the room's neighbors and enemy.

public class Room {
    private String description;
    private boolean hasEnemy;
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private String roomName;
    private Enemy enemy;
    private boolean isPowerOn;

    public Room(String name, String description, boolean hasEnemy){
        this.roomName = name;
        this.description = description;
        this.hasEnemy = hasEnemy;
    }

    public void setPower(boolean power){
        isPowerOn = power;
    }
    public boolean isPowerOn(){
        return isPowerOn;
    }

    public String getName(){
        return roomName;
    }

    public void setNeighbors(Room north, Room south, Room east, Room west){
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;   

    }

    public void setEnemy(Enemy e){
        this.enemy = e;
    }

    public String getDescription(){
        return description;
    }

    public Room getNeighbor(String direction){
        if(direction.equals("north")){
            return north;
        }
        else if(direction.equals("south")){
            return south;
        }
        else if(direction.equals("east")){
            return east;
        }
        else if(direction.equals("west")){
            return west;
        }
        else{
            return null;
        }
    }

    public Enemy getEnemy(){
        return enemy;
    }

    public boolean hasEnemy(){
        if(hasEnemy){
            return true;
        }
        else{
            return false;
        }
    }
}

