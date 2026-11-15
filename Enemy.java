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
        System.out.println("The " + name + " hits you for " + damage + " damage!");
    }

    public String getName(){
        return name;
    }
}
