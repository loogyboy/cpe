import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpeedUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpeedUp extends Powerups
{
    /**
     * Act - do whatever the SpeedUp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public SpeedUp(int x, int y)
    {
        super(x, y);
    }
    
    public SpeedUp()
    {
        super();
    }
    
    protected void checkHitCharacter()
    {
        Character a = (Character) getOneIntersectingObject(Character.class);
        
        if (isTouching(Character.class))
        {
            a.setSpeedTimer();
            getWorld().removeObject(this);
        }
    }
}
