import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DoorMessage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DoorMessage extends Actor
{
    /**
     * Act - do whatever the DoorMessage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public DoorMessage(){
        GreenfootImage image = getImage();  
        image.scale(400, 100);
        setImage(image);
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
