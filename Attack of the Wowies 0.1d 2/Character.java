import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character extends Actor
{
    /**
     * Act - do whatever the Character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Character(){
        GreenfootImage image = getImage();  
        image.scale(400, 400);
        setImage(image);
    }

    public void act() 
    {
        moveEffects();
        playerModel();
        findDoor();
        CharacterX = getX();
        CharacterY = getY();
        combat();
       
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
    public boolean isTouchingDoor = false;
    public static int CharacterX, CharacterY;
    private int stunDelay = -1;
    private int speedTimer = -1;

    public void playerMovement(){//main player movement
        if(Greenfoot.isKeyDown("d") && Greenfoot.isKeyDown("a") == false){
            if(onBackStep == false && weaponID == 0){
                setImage("mainguy1.png");
                GreenfootImage image = getImage();  
                image.scale(400, 400);
                move(movespeed);
            }
            if (onBackStep == true && weaponID == 0){
                setImage("mainguy2.png");
                GreenfootImage image = getImage();
                image.scale(400, 400);
                move(movespeed);
            }
            if (getOneIntersectingObject(Floor.class) != null && ground() == false && Greenfoot.isKeyDown("d") == false){
                move(movespeed);
            }
            //steal code here
            facing = true;
        }
        if(Greenfoot.isKeyDown("a") && Greenfoot.isKeyDown("d") == false){
            if(onBackStep == false && weaponID == 0){
                setImage("mainguy3.png");
                GreenfootImage image = getImage();  
                image.scale(400, 400);
                move(-movespeed);
            }
            if (onBackStep == true && weaponID == 0){
                setImage("mainguy4.png");
                GreenfootImage image = getImage();
                image.scale(400, 400);
                move(-movespeed);
            }
            if (getOneIntersectingObject(Floor.class) != null && ground() == false && Greenfoot.isKeyDown("d") == false){
                move(movespeed);
            }
            //steal code here
            facing = false;
        }
    }
    
    public void playerMovement2(){//main player movement
        if(Greenfoot.isKeyDown("d") && Greenfoot.isKeyDown("a") == false){
            if(onBackStep == false && weaponID == 0){
                setImage("mainguy1.png");
                GreenfootImage image = getImage();  
                image.scale(400, 400);
                move(movespeed * 2);
            }
            if (onBackStep == true && weaponID == 0){
                setImage("mainguy2.png");
                GreenfootImage image = getImage();
                image.scale(400, 400);
                move(movespeed * 2);
            }
            if (getOneIntersectingObject(Floor.class) != null && ground() == false && Greenfoot.isKeyDown("d") == false){
                move(movespeed * 2);
            }
        }
        if(Greenfoot.isKeyDown("a") && Greenfoot.isKeyDown("d") == false){
            if(onBackStep == false && weaponID == 0){
                setImage("mainguy3.png");
                GreenfootImage image = getImage();  
                image.scale(400, 400);
                move(-movespeed * 2);
            }
            if (onBackStep == true && weaponID == 0){
                setImage("mainguy4.png");
                GreenfootImage image = getImage();
                image.scale(400, 400);
                move(-movespeed * 2);
            }
            if (getOneIntersectingObject(Floor.class) != null && ground() == false && Greenfoot.isKeyDown("d") == false){
                move(movespeed * 2);
            }
        }
    }

    public void playerModel(){ //walking animation
        walkTimer++;
        if(walkTimer < 15){
            onBackStep = false;
        }
        if(walkTimer > 15 && walkTimer < 30){
            onBackStep = true;
        }
        if(walkTimer == 30){
            walkTimer = 0;
        }
    }
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
        if (Greenfoot.isKeyDown("space") && jumping == false && falling == false && jumpres == false){
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
    public void findDoor(){
        if(this.getOneObjectAtOffset(0, 0, Door.class) != null){
            isTouchingDoor = true;
        }
        else{
            isTouchingDoor = false;
        }
    }
    
    public void moveEffects() //movement that is affected by powerups and traps
    {
        if (stunDelay < 0)
        {
            jump();
            if(speedTimer < 0)
            {
                playerMovement();
            }
            else
            {
                playerMovement2();
                speedTimer--;
            }
        }
        else
        {
            stunDelay--;
        }
    }
    
    public void setSpeedTimer()
    {
        speedTimer = 200;
    }
    
    public void stun()
    {
        stunDelay = 50;
    }
    
    //steal allow below
    public void combat(){
    //Punch by peter
    punch();
    }
    int start_length = 300;
    int punch_length;
    boolean facing = true;
    boolean within_range;
    int i;
    int j;
    int k;
    int l;
    int punch_time = 0;
    int height = -100;
    Floor floor;
    Enemy punching;
    public void punch(){
    punch_time--;
    if(facing == true){
        punch_length = start_length;
        }else{
        punch_length = -start_length;
        }
        within_range = false;
    for(i = start_length;i>0;i--){
        punching = (Enemy)getOneObjectAtOffset(i, getImage().getHeight()/2, Enemy.class);
        if(punching != null){
            i=0;
            within_range = true;
        }
    }
    for(j = start_length;j<0;j++){
        punching = (Enemy)getOneObjectAtOffset(j, getImage().getHeight()/2 , Enemy.class);
        if(punching != null){
            j=0;
            within_range = true;
        }            
    }
    for(j = start_length;j<0;j++){
        punching = (Enemy)getObjectsAtOffset(j, getImage().getHeight()/2, Enemy.class);
        if(punching != null){
            j=0;
            within_range = true;
        }            
    }
        
    
    
        
    if(Greenfoot.isKeyDown("j") && punch_time < 0 && within_range == true)
        {
        //System.out.println("Stuff");
        punch_time = 60;
        punching.hit_melee();
        
       }
       
    }
}

