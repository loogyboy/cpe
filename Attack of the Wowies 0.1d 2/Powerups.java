import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PowerUps here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Powerups extends SmoothMover
{
    protected double targetX, targetY, expireTime;
    protected int origX, origY;
    protected double duration;
    protected int counter;
    
    public Powerups(int oX, int oY)
    {
        origX = oX;
        origY = oY;
        //counter = 0;
        expireTime = 100;
        duration = expireTime;
    }

    public Powerups()
    {
        //counter = 0;
        expireTime = 1000;
        duration = expireTime;
    }
    
    protected void addedToWorld(World w)
    {
        origX = getX();
        origY = getY();
    }
    
    public void act() 
    {
        stay();
        checkHitCharacter();
        checkExpire();
    }
    
    //protected abstract double curveX(double f);
    //protected abstract double curveY(double f);
    
    protected abstract void checkHitCharacter();
    
    /*protected void easing()
    {
        double fX = ++counter/duration;
        double fY = counter/duration;
        fX = curveX(fX);
        fY = curveY(fY);
        setLocation((targetX * fX) + (origX * (1 - fX)), (targetY * fY) + (origY * (1 - fY)));
    }*/
    
    protected void stay()
    {
        setLocation(origX, origY);
    }
    
    private void checkExpire()
    {
        if (expireTime-- < 0)
        {
           World w = getWorld();
           if (w != null)
           {
              w.removeObject(this); 
           }
        }
    }
}


