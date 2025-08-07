package KarelJRobot.kareltherobot;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Vector;
import kareltherobot.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class AnyRemote extends Frame implements Directions {
   private static int id = 0;
   private static int delta = 10;
   private UrRobot karel;
   Class[] argTypes;

   public AnyRemote() {
      this("kareltherobot.UrRobot", 1, 1, North, 0, (Color)null);
   }

   public AnyRemote(String className) {
      this(className, 1, 1, North, 0, (Color)null);
   }

   public AnyRemote(String className, int street, int avenue, Directions.Direction direction, int beepers) {
      this(className, street, avenue, direction, beepers, (Color)null);
   }

   public AnyRemote(String className, int street, int avenue, Directions.Direction direction, int beepers, Color color) {
      super(className + " " + getID() + " control");
      this.karel = null;
      this.argTypes = new Class[]{Integer.TYPE, Integer.TYPE, Directions.Direction.class, Integer.TYPE, Color.class};

      try {
         this.addWindowListener(new Hider(this));
         this.setSize(340, 150);
         this.setLocation(560 + delta * (id - 1), 100 + delta * (id - 1));
         if (color != null) {
            this.setBackground(color);
         }

         Class robotClass = Class.forName(className);
         if (!UrRobot.class.isAssignableFrom(robotClass)) {
            this.add(new Label(className + " is not a robot class."));
            return;
         }

         Constructor ctor = robotClass.getDeclaredConstructor(this.argTypes);
         Object[] values = new Object[]{new Integer(street), new Integer(avenue), direction, new Integer(beepers), color};
         this.karel = (UrRobot)ctor.newInstance(values);
         Method[] allMethods = robotClass.getMethods();
         Vector usefulMethods = new Vector();

         int i;
         for(i = 0; i < allMethods.length; ++i) {
            if (isValid(allMethods[i])) {
               usefulMethods.addElement(allMethods[i]);
            }
         }

         i = usefulMethods.size();
         this.setSize(340, 30 * i);
         this.setLayout(new GridLayout(i, 2));
         Enumeration enumerate = usefulMethods.elements();

         while(enumerate.hasMoreElements()) {
            Method aMethod = (Method)enumerate.nextElement();
            Button aButton = new Button(aMethod.getName());
            this.add(aButton);
            if (isProc(aMethod)) {
               aButton.addActionListener(new ProcListener(this, aMethod));
               this.add(new Label("void"));
            } else {
               TextField aField = new TextField(20);
               aButton.addActionListener(new FuncListener(this, aMethod, aField));
               this.add(aField);
            }
         }

         this.setVisible(true);
      } catch (Exception var17) {
         this.add(new Label(className + " does not seem to be the name of a robot class." + var17.toString()));
         this.setVisible(true);
      }

   }

   private static boolean isValid(Method m) {
      Class declaring = m.getDeclaringClass();
      if (declaring.equals(Object.class)) {
         return false;
      } else if (declaring.equals(Observable.class)) {
         return false;
      } else if (Modifier.isStatic(m.getModifiers())) {
         return false;
      } else {
         if (declaring.equals(UrRobot.class)) {
            String name = m.getName();
            if (!name.equals("move") && !name.equals("turnLeft") && !name.equals("turnOff") && !name.equals("putBeeper") && !name.equals("pickBeeper") && !name.equals("toString")) {
               return false;
            }
         }

         return m.getParameterTypes().length == 0;
      }
   }

   private static boolean isProc(Method m) {
      return m.getReturnType().equals(Void.TYPE);
   }

   private static int getID() {
      return id++;
   }

   class FuncListener implements ActionListener {
      Method method;
      TextField field;
      AnyRemote this$0;

      public FuncListener(AnyRemote var1, Method method, TextField field) {
         this.this$0 = var1;
         this.method = null;
         this.field = null;
         this.field = field;
         this.method = method;
      }
   
      public void actionPerformed(ActionEvent e) {
         try {
            Object result = this.method.invoke(AnyRemote.access$0(this.this$0), (Object[])null);
            this.field.setText(result.toString());
         } catch (Exception var3) {
         }
   
      }
   }
   

   
   class Hider extends WindowAdapter {
      AnyRemote this$0;
      private Hider(AnyRemote var1) {
         this.this$0 = var1;
      }
   
      public void windowClosing(WindowEvent e) {
         this.this$0.setVisible(false);
      }
   }

   class ProcListener implements ActionListener {
      Method method;
      AnyRemote this$0;
      public ProcListener(AnyRemote var1, Method method) {
         this.this$0 = var1;
         this.method = null;
         this.method = method;
      }
   
      public void actionPerformed(ActionEvent e) {
         try {
            this.method.invoke(AnyRemote.access$0(this.this$0), (Object[])null);
         } catch (Exception var3) {
         }
   
      }
   }   
}
