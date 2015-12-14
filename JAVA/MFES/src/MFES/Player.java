package MFES;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Player implements Cloneable  {
    private String name;
    private Number points = 0L;

    public Player(final String n) {
        cg_init_Player_1(n);
    }

    public Player() {
    }

    public void cg_init_Player_1(final String n) {
        name = n;

        return;
    }

    public void addPoints(final Number inc) {
        points = points.longValue() + inc.longValue();
    }

    public Number getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Player{" + "name := " + Utils.toString(name) + ", points := " +
        Utils.toString(points) + "}";
    }
    
    public Object clone() {
        try {
        	
          return super.clone();
        } catch (CloneNotSupportedException e) {
          System.out.println("Cloning not allowed.");
          return this;
        }
      }
}
