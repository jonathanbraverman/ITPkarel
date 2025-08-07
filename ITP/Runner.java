package ITP;

import kareltherobot.*;
import java.awt.Color;

import KarelJRobot.kareltherobot.AnyRemote;

public class Runner
{
  //static WorldBuilder builder = null;
  static ITPRobot myrobot = null;
  static RemoteControl rc = null;
  static AnyRemote ar = null;
  static AutoRemote ac = null;

  public static void run()
  {
      System.out.println("Starting...");
      World.repaint();
      //World.setSize(10,10);
      World.setDelay(10);
      World.readWorld("worlds", "firstworld.kwld");
      myrobot = new ITPRobot(1,1,Directions.North,0);
      String fullQualifiedName = myrobot.getClass().getName();
      System.out.println(fullQualifiedName);
      ac = new AutoRemote(fullQualifiedName, 8, 8, Directions.South, 5, Color.red);
      ar = new AnyRemote();
      ar = new AnyRemote("ITP.ITPRobot");
      rc = new RemoteControl(5,5,Directions.North,0);
      //builder = new WorldBuilder(true);
      World.setVisible(true);
      
      myrobot.turnLeft();
      myrobot.turnLeft();
      myrobot.turnLeft();
      myrobot.move();
      myrobot.move();
      myrobot.move();
      myrobot.move();
      myrobot.move();
      myrobot.move();
      myrobot.move();
      myrobot.move();
      myrobot.move();
      myrobot.turnLeft();
      myrobot.move();
      myrobot.move();
      myrobot.move();
      myrobot.turnLeft();
      myrobot.turnLeft();
      myrobot.turnLeft();
      myrobot.move();
      myrobot.turnLeft();
      myrobot.turnLeft();
      myrobot.turnLeft();
      myrobot.move();
      myrobot.move();
      myrobot.move();
      myrobot.pickBeeper();
      myrobot.turnLeft();
      myrobot.turnLeft();
      myrobot.move();
      myrobot.move();
      myrobot.move();
      myrobot.putBeeper();
      myrobot.turnLeft();
      myrobot.move();
  }
    
  public static void main(String[] args)
  {
      run();
  }
}
