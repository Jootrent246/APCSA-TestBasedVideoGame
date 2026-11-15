public class Player{
    private int health;
    private Room currentRoom;
    private int numKeys;

    public Player(Room r){
        this.currentRoom = r;
        health = 100;
        numKeys = 0;
    }

    public void addKeys(int keys){
        numKeys += keys;
    }


    public void takeDamage(int damage){
        health -= damage;
    }

    public void attack(Enemy e){
        e.takeDamage(10);
    }

    public void changeRooms(Room r){
        this.currentRoom = r;
    }

    public int getKeys(){
        return numKeys;
    }

    public int getHealth(){
        return health;
    }

    public Room getRoom(){
        return currentRoom;
    }
    
}
