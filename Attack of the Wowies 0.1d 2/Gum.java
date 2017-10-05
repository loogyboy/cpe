import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gum here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gum extends Powerups
{
    private boolean isHit = false;
    
    public Gum(int x, int y)
    {
        super(x, y);
    }
    
    public Gum()
    {
        super();
    }
    
    protected void checkHitCharacter()
    {
        Character a = (Character) getOneIntersectingObject(Character.class);
        
        if (a != null)
        {
            a.stun();
            getWorld().removeObject(this);
        }
    }
}
