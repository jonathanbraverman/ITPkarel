package ITP;

import kareltherobot.*;

public class Runner
{
  //static WorldBuilder builder = null;
    static ITPRobot myrobot = null;
  
  public static void run()
  {
      System.out.println("Starting...");
      World.repaint();
      //World.setSize(10,10);
      //World.setDelay(50);
      World.readWorld("worlds", "firstworld.kwld");
      myrobot = new ITPRobot(1,1,Directions.North,1);
      //builder = new WorldBuilder(true);
      World.setVisible(true);
      
      myrobot.turnLeft();
      myrobot.turnLeft();
      myrobot.turnLeft();
      myrobot.move();
      myrobot.move();
      myrobot.move();
      myrobot.move();
      
  }
    
    
}
