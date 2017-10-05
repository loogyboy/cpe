import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private int eSpeed = 2;
    private int canChange;
    private boolean facingLeft = false;
     /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
   
    public Enemy()
    {
        GreenfootImage image = getImage();  
        image.scale(400, 400);
        setImage(image);
    }
    public void act() 
    {
        EnemyOrient();
        EnemyMovement();
        //jump();
        checkFall();
        gravityjump();
        followCharacter();
    }    

    private void edgeTurn()
    {
        // I want a method that makes them not run aimlessly into a wall. But I have to use colliders which I havent done yet.
    }

    private void EnemyOrient()
    //turns enemy right 50% of the time and left 50% of the time (Luke Underwood)
    {
        canChange = canChange + 1;
        if (canChange % 60 == 0)
        {
            if (Greenfoot.getRandomNumber (9) < 4)
            { 
                TurnLeft();
            }
            else {
                TurnRight();
            }

        }
    }

    public void EnemyMovement()
    //moves the enemy in the diection it's facing (Luke Underwood)
    {
        if (facingLeft == true)
        {
            move(-eSpeed);
        }
        else
        {
            move(eSpeed);
        }
    }

    public void TurnLeft()
    {
        facingLeft = true;

    }

    public void TurnRight()
    {
        facingLeft = false;
    }

    private boolean onBackStep = false; //switches between images to control walking animation
    private int walkTimer = 0; //Times onStepBack in order to switch images at even intervals
    private int weaponID = 0; //controls weapon type, 0 is bare hands
    private int movespeed = 5;
    private int speed;
    private int gravity = 1;
    private boolean jumping;
    private int timer;
    private boolean falling;
    private boolean infront;
    private boolean inbehind;
    private int direction = 80;
    private int jumpreset;
    private boolean jumpres;
    public void jump(){//main player jump abaility, Peter did this
        ground();
        checkFall();
        gravityjump();
        timerjump();
        actualJump();
    }

    public void timerjump(){ //determines if jump is over and if falling starts
        timer--;
        if (timer<0){
            jumping = false;
        }
        jumpreset--;
        if(jumpreset < 0){
            jumpres = false;
        }else
        {
            jumpres = true;
        }

    }

    public void actualJump(){ //checks if space is activated and not jumping currently as well as not falling
        // edited by Luke to make random base movement for enemy. Just changed key press to be a random number
        if (Greenfoot.getRandomNumber (1000) < 1 && jumping == false && falling == false && jumpres == false){
            jumping = true;
            timer = 8;
            gravity = -timer;
            falling = true;
        }
    }

    public boolean ground(){ //checks if ground is below char
        //FLOOR is the colliding object
        Object under = getOneObjectAtOffset(0, getImage().getHeight()/2 + 2, Floor.class);
        return under != null;
    }

    public void checkFall(){ //checks if ground is below and jump is not active to not trigger false triggers
        //check if ground is below and not jumping
        Object infronts = getOneObjectAtOffset(0+direction, getImage().getHeight()/2 + 2, Floor.class);
        if(infronts != null){
            infront = true;
        }else{
            infront = false;
        }
        Object behinds = getOneObjectAtOffset(0-direction, getImage().getHeight()/2 + 2, Floor.class);
        if(behinds != null){
            inbehind = true;
        }else{
            inbehind = false;
        }
        if((falling == true || jumping == true) && (infront == true || inbehind == true || ground() == true))
        {
            jumpreset = 30;
        }
        if((infront == true || inbehind == true) && jumping ==false){
            speed = 0;
            falling = false;
        }
        if(ground() == true  && jumping == false){
            speed = 0;
            falling = false;
        }
    }

    public void gravityjump(){ //gravity applying force every frame
        if(gravity != 1){
            gravity++;
        }
        setLocation(getX(), getY() + speed);
        speed += gravity;
    }

    public void followCharacter()
    {
        turnTowards(Character.CharacterX, getY());
        move(2);
    }
    int health = 4;
    public void hit_melee()
    {
        World worlds = getWorld();
        health--;
        if(health == 0)
        worlds.removeObject(this);
    }
}
   