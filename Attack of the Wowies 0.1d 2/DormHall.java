import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DormHall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DormHall extends World
{

    /**
     * Constructor for objects of class DormHall.
     * 
     */
    public DormHall()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(12800, 900, 1); 
        GreenfootImage bg = new GreenfootImage("dormhalllevel.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        //prepare();
        prepare();
    }
    private Character main;
    private Door exit;
    private Door entry;

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Floor floor = new Floor();
        addObject(floor,861,894);
        Floor floor2 = new Floor();
        addObject(floor2,2623,894);
        Floor floor3 = new Floor();
        addObject(floor3,4374,894);
        Floor floor4 = new Floor();
        addObject(floor4,6065,894);
        Floor floor5 = new Floor();
        addObject(floor5,7779,894);
        Floor floor6 = new Floor();
        addObject(floor6,9438,894);
        Floor floor7 = new Floor();
        addObject(floor7,10977,894);
        Floor floor8 = new Floor();
        addObject(floor8,12461,894);
        Character character = new Character();
        addObject(character,198,670);
        main = character;
    }
}
