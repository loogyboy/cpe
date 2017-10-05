import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class door here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Door extends Actor
{
    /**
     * Act - do whatever the door wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Door(){
        GreenfootImage image = getImage();  
        image.scale(50, 50);
        setImage(image);
    }
    public int destinationID = 0;
    public void act() 
    {
        if(this.getOneIntersectingObject(Character.class) != null && Greenfoot.isKeyDown("x")){
            toDormHall();
        }
    }
    public void toWorld(){
        toDormHall();
    }
    public void toDormHall(){
        if(destinationID == 0){
            DormHall hall = new DormHall();
            Greenfoot.setWorld(hall);
        }
    }
}
