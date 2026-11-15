//Trent Hardacre
//APCSA Text Based Video Game
//Mrs. Ramsey
//This is the Enemy class that contains the enemy's health, name, damage, and methods to attack the player and take damage from the player.

public class Enemy {
    private int health;
    private String name;
    private int damage;

    public Enemy(int health, String name, int damage){
        this.health = health;
        this.name = name;
        this.damage = damage;
    }

    public int getHealth(){
        return health;
    }

    public int getDamage(){
        return damage;
    }

    public boolean isAlive(){
        if(health > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void takeDamage(int damage){
        health -= damage;
        if(health < 0){
            health = 0;
        }
    }

    public void attack (Player p){
        p.takeDamage(damage);
    }

    public String getName(){
        return name;
    }
}
