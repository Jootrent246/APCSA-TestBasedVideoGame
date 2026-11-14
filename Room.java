public class Room {
    private String description;
    private int keys;
    private boolean isSearched;
    private boolean hasTreasure;
    private boolean isLocked;
    private boolean hasEnemy;
    private Room supplyCloset;
    private Room mathRoom;
    private Room cafeteria;
    private Room fOffice;
    private Room aOffice;
    private Room pOffice;
    private Room testRoom;

    public Room(String description, boolean hasTreasure, boolean hasEnemy){
        this.description = description;
        this.hasTreasure = hasTreasure;
        this.hasEnemy = hasEnemy;
    }

    public void setNeighbors(Room fOffice, Room aOffice, Room pOffice, Room testRoom, Room supplyCloset, Room mathRoom, Room cafeteria){
        
    }

    public void setEnemy(Enemy e){

    }

    public void search(){

    }
    
    public void enterRoom(Plauyer p){

    }

    public String getDescription(){

    }

    public room getNeighbor(String direction){

    }

    public Enemy getEnemy(){

    }

    public boolean hasEnemy(){

    }

}

