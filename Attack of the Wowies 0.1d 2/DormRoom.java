import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DormRoom extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public DormRoom()
    {    
        super(1600, 900, 1);
        GreenfootImage bg = new GreenfootImage("dormroomlevel.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        prepare();
    }
    private Character main;
    private Door exit;
    

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    public void act(){
        DoorTest();
    }
    private void prepare()
    {
        Floor dormfloor = new Floor();
        addObject(dormfloor,752,898);;
        Character character = new Character();
        main = character;
        addObject(character,296,673);
        character.setLocation(296,673);
        Door door = new Door();
        exit = door;
        addObject(door,1235,731);
    }
    public Character getPlayer(){
        return main;
    }
    public Door getDoor(){
        return exit;
    }
    public void DoorTest(){
        int doorX = this.getDoor().getX();
        int doorY = this.getDoor().getY();
        int playerX = this.getPlayer().getX();
        int playerY = this.getPlayer().getY();
        int rangeX1 = doorX - 150;
        int rangeX2 = doorX + 150;
        int rangeY1 = doorY - 150;
        int rangeY2 = doorY + 150;
        if((playerX > rangeX1 && playerX < rangeX2) && (playerY > rangeY1 && playerY < rangeY2)){
            DoorMessage pressX = new DoorMessage();
            addObject(pressX,this.getWidth()/2, this.getWidth()/10);
            if(Greenfoot.isKeyDown("x")){
                this.removeObjects(this.getObjects(Actor.class));
                getDoor().toWorld();
            }
        }
        else{
            this.removeObjects(this.getObjects(DoorMessage.class));
        }
    }
}
