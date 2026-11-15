import java.util.Scanner;
import java.util.Random;

public class Game {
    private Scanner input;

    private Player player;

    private Room supplyCloset;
    private Room mathRoom;
    private Room cafeteria;
    private Room fOffice;
    private Room pOffice;

    private Enemy broom;
    private Enemy mathTeach;
    private Enemy burger;      
    private Enemy principle;
    
    private String[] mathQuestions = {"3 x 4", "6 x 7", "8 x 9", "5 x 6", "7 x 8","9 x 9", "4 x 12", "11 x 11", "6 x 8", "7 x 7","12 x 12", "9 x 6", "8 x 4", "11 x 7", "12 x 9"};
    private String[] mathAnswers = {"12", "42", "72", "30", "56","81", "48", "121", "48", "49","144", "54", "32", "77", "108"};

    private String[] triviaQuestions = {"What planet is known as the Red Planet?", "How many continents are there?", "What is the largest ocean on Earth?", "How many sides does a hexagon have?", "What is the freezing point of water in Fahrenheit?", "What is the capital of France?", "How many legs does a spider have?", "What is the largest planet in our solar system?", "What color do you get when you mix blue and yellow?", "How many days are in a leap year?"};
    private String[] triviaAnswers = {"mars", "7", "pacific", "6", "32", "paris", "8", "jupiter", "green", "366"};

    public Game() {
        this.input = new Scanner(System.in);
    }

    public void setUP(){
        System.out.println("===========================================================================================================================");
        System.out.println("Welcome to the game!" + "\n" + "You are a student at a high school. Your goal is to find the test answers by defeating enemies and finding the three keys." + "\n" + "Good luck!");
        System.out.println("===========================================================================================================================");
        System.out.println("Commands: " + "\n" + "north, south, east, west - move in that direction" + "\n" + "search - search the room" + "\n" + "invetory - check your items, power status,and health" + "\n" + "quit - go home" + "\n" + "exit - exit room" + "\n" + "help - get commands");
        
        supplyCloset = new Room("Supply Closet", "You are in the supply closet. Find the Pencils", true, true);
        mathRoom = new Room("Math Room", "You are in the math room. Find the Calculator.", true, true);
        cafeteria = new Room("Cafeteria", "You are in the cafeteria. Find the Pizza.", true, true);
        fOffice = new Room("Front Office", "You are in the front office. Naviagte to other Rooms.", false, false);
        pOffice = new Room("Principal's Office", "You are in the principal's office. There get pass him for the answers!", true, true);


        broom = new Enemy(20, "Broom", 2);
        mathTeach = new Enemy(30, "Math Teacher", 10);
        burger = new Enemy(20, "Burger", 3);
        principle = new Enemy(50, "Principal", 20);

        supplyCloset.setEnemy(broom);
        mathRoom.setEnemy(mathTeach);
        cafeteria.setEnemy(burger);
        pOffice.setEnemy(principle);

        player = new Player(fOffice);

        fOffice.setNeighbors(supplyCloset, cafeteria, mathRoom, pOffice);
    }

    
    public void gameLoop(){
        String command;
        boolean gameOver = false;
        Room lastRoom = null;
        while(!gameOver){
            if(player.getRoom() != lastRoom){
            System.out.println("\n" + player.getRoom().getDescription());
            System.out.println("\n"+"Type search to investigate room"+ "\n");
            lastRoom = player.getRoom();
            }
             
            System.out.println("["+player.getRoom().getName()+"]");
            
            System.out.print("> ");
            command = input.nextLine().toLowerCase();
            
            if (command.equals("quit")){
                System.out.println("Thanks for playing!");
                gameOver = true;
            }else if (command.equals("help")){
                System.out.println("Commands: " + "\n" + "north, south, east, west - move in that direction" + "\n" + "search - search the room" + "\n" + "invetory - check your items, power status, and health" + "\n" + "quit - go home" + "\n" + "exit - exit room" + "\n" + "help - get commands"+ "\n");
            }else if (command.equals("inventory")){
                System.out.println("Health: " + player.getHealth() + "\n" + "Items: " + player.getKeys() + "\n" + "Power Status: " + (fOffice.isPowerOn() ? "On" : "Off") + "\n");
            }else if(command.equals("west")){
                if(player.getRoom() != fOffice){
                    System.out.println("\n"+"***Type exit to return to front office***"+ "\n");
                }else if(player.getKeys() < 3){
                    if(fOffice.isPowerOn() == false){
                        System.out.println("\n"+"The power is out! You can't access other rooms!"+"\n");
                    }else if(fOffice.isPowerOn() == true){ System.out.println("\n"+"You need all three keys to enter the attendance office."+"\n");}
                }else{
                    Room nextRoom = player.getRoom().getNeighbor(command);
                    player.changeRooms(nextRoom);
                }
            }else if (command.equals("north") || command.equals("south") || command.equals("east")){
                if(fOffice.isPowerOn() == false){
                    System.out.println("\n"+"The power is out! You can't access other rooms!"+"\n");
                }else if (fOffice.isPowerOn() == true){
                    Room nextRoom = player.getRoom().getNeighbor(command);
                    if(player.getRoom() == fOffice){
                    player.changeRooms(nextRoom);
                    }else if(player.getRoom() == pOffice || player.getRoom() == cafeteria || player.getRoom() == mathRoom || player.getRoom() == supplyCloset){
                    System.out.println("\n"+"***Type exit to return to front office***"+ "\n");
                    }
                }
            }else if (command.equals("exit")){
                player.changeRooms(fOffice);
            }else if(command.equals("search")){
                Enemy enemy = player.getRoom().getEnemy();
                if(player.getRoom().hasEnemy() && enemy.isAlive()){
                    if (player.getRoom().hasEnemy() && player.getRoom().getEnemy().isAlive()) {
                        System.out.println("\n" + "OH no the " + enemy.getName() + " is here!" + "\n");
                    }
                        if(enemy.getName().equals("Math Teacher")){
                            System.out.println("Defeat him for the Calculator!" + "\n");
                            while(enemy.isAlive() && player.getHealth() > 0){
                                int i = (int)(Math.random() * mathQuestions.length);
                                System.out.println("What is " + mathQuestions[i] + "?");
                                String answer = input.nextLine().trim();
                                if(answer.equals(mathAnswers[i])){
                                    System.out.println("Correct! You hit the " + enemy.getName() + " for 10 damage!" + "\n");
                                    player.attack(enemy);
                                }else{
                                    System.out.println("Incorrect! The " + enemy.getName() + " hits you for " + enemy.getDamage() + " damage!" + "\n");
                                    enemy.attack(player);
                                }
                                 System.out.println("Your health: " + player.getHealth() + " | " + enemy.getName() + " health: " + enemy.getHealth() + "\n");
                            }
                            if(enemy.getHealth() <=0){
                                System.out.println("\n"+"You defeated the Math Teacher and got the Calculator!" + "\n" + "Type exit to leave");
                                player.addKeys(1);
                            }
                        }else if(enemy.getName().equals("Burger")){
                            System.out.println("Defeat it for Pizza!" + "\n");
                            while(enemy.isAlive() && player.getHealth() > 0){
                                 int number = (int) (Math.random() * 2) + 1;
                                 System.out.print( "Pick a number between 1 and 2 to see if you can attack"+"\n");
                                 String guess = input.nextLine().trim();
                                 if(guess.equals(String.valueOf(number))){
                                    System.out.println("Correct! You hit the " + enemy.getName() + "for 10 damage" + "\n");
                                    player.attack(enemy);
                                 }else{
                                    System.out.println("Incorrect! The " + enemy.getName() + " hits you for " + enemy.getDamage() + " damage!" + "\n");
                                    enemy.attack(player);
                                 }
                                  System.out.println("Your health: " + player.getHealth() + " | " + enemy.getName() + " health: " + enemy.getHealth() + "\n");
                            }
                            if(enemy.getHealth() <=0){
                                System.out.println("\n"+"You defeated the Broom and got the Pencils!" + "\n" + "Type exit to leave");
                                player.addKeys(1);
                            }
                        }else if(enemy.getName().equals("Broom")){
                            System.out.println("Defeat it for Pencils!" + "\n");
                            while(enemy.isAlive() && player.getHealth() > 0){
                                 String correctAnswer;
                                 int number = (int) (Math.random() * 2) + 1;
                                 if (number == 1){correctAnswer = "heads";}else{correctAnswer = "tails";}
                                 System.out.print( "It flips a coin pick heads or tails to see if you can attack"+"\n");
                                 String uGuess = input.nextLine().trim();
                                 if(uGuess.equals(String.valueOf(correctAnswer))){
                                    System.out.println("Correct! You hit the " + enemy.getName() + " for 10 damage" + "\n");
                                    player.attack(enemy);
                                 }else{
                                    System.out.println("Incorrect! The " + enemy.getName() + " hits you for " + enemy.getDamage() + " damage!" + "\n");
                                    enemy.attack(player);
                                 }
                                  System.out.println("Your health: " + player.getHealth() + " | " + enemy.getName() + " health: " + enemy.getHealth() + "\n");
                            }
                            if(enemy.getHealth() <=0){
                                System.out.println("\n"+"You defeated the Burger and got the Pizza!" + "\n" + "Type exit to leave");
                                player.addKeys(1);
                            }
                        }else if(enemy.getName().equals("Principal")){
                            System.out.println("Defeat him for the Test Answers!" + "\n");
                            while(enemy.isAlive() && player.getHealth() > 0){
                                 int i = (int) (Math.random() * triviaQuestions.length);
                                 System.out.print( "Answer his questions to defeat him!"+"\n");
                                 String Oguess = input.nextLine().trim();
                                 if(Oguess.equals(triviaAnswers[i])){
                                    System.out.println("Correct! You hit the " + enemy.getName() + " for 10 damage" + "\n");
                                    player.attack(enemy);
                                 }else{
                                    System.out.println("Incorrect! The " + enemy.getName() + " hits you for " + enemy.getDamage() + " damage!" + "\n");
                                    enemy.attack(player);
                                 }
                                  System.out.println("Your health: " + player.getHealth() + " | " + enemy.getName() + " health: " + enemy.getHealth() + "\n");
                            }
                            if(enemy.getHealth() <=0){
                                System.out.println("\n"+"You defeated the Principal and got the Test Answers!" + "\n");
                                player.addKeys(1);
                                System.out.println("\n"+"Congratulations You Win! Your sure to ACE your Test!"+"\n");
                                gameOver = true;
                            }
                        }
                }else if(player.getRoom().hasEnemy()){
                    System.out.println("The " + player.getRoom().getEnemy().getName() + " is defeated." + "\n");
                }else if(player.getRoom().equals(fOffice)){
                    System.out.println("\n" +"You found the power switch, now you can access other rooms!" + "\n");
                    fOffice.setPower(true);
                }else if (player.getRoom().equals(fOffice) && fOffice.isPowerOn() == true){
                    System.out.println("\n" +"The Power is already on" + "\n");
                }
            }
            if (player.getHealth() <= 0){
                System.out.println("\n"+"You've been CAUGHT! Game Over!" + "\n");
                gameOver = true;
            }
        }
    }          
}




