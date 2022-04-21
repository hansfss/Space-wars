/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OODCwk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author folke
 */
public class Player implements Serializable{
    private String name;
    private int warchest;
    private boolean defeated;
    private HashMap<String, Force> fleet;  
    
    public Player()
    {
        
    }
    
    public Player(String name)
    {
        this.name = name;
        this.warchest = 1000;
        this.defeated = false;
        this.fleet = new HashMap<String, Force>();
    }
    
    public Player(String name, int warchest, boolean defeated, 
                HashMap<String, Force> fleet)
    {
        this.name = name;
        this.warchest = warchest;
        this.defeated = defeated;
        this.fleet = fleet;
    }
    
    public void addForce(Force f)
    {
        if (warchest >= f.getActivationFee())
        {
            f.setState(ForceState.ACTIVE);
            warchest -= f.getActivationFee();
            fleet.put(f.getFleetID(), f);
        }
        
    }
    
    public Force returnForce(String key)
    {
        Force f = fleet.get(key);
        warchest += (f.getActivationFee() / 2);
        f.setState(ForceState.DOCKED);
        fleet.remove(key);
        return f;
    }
    
    public int engageInBattle(Battle b, Collection<Force> _fleet, UFFleet ufleet)
    {
        ArrayList<Force> fl = new ArrayList<Force>();
        for (Force f : _fleet)
        {
            fl.add(f);
        }
        
        int max = _fleet.size() - 1;
        int min = 0;
        int forceSelector = (int)Math.floor(Math.random()*(max - min + 1) + min);
        
        if (fleet.size() == 0)
        {
            warchest -= b.getLosses();
            return 1;
        }
        
        Force f = fl.get(forceSelector);
        
        if (f.getBattleStrength() >= b.getEnemyStrength())
        {
            warchest += b.getGains();
            return 0;
        }
        else if (f.getBattleStrength() < b.getEnemyStrength())
        {
            warchest -= b.getLosses();
            f.setState(ForceState.DESTROYED);
            ufleet.addForce(f);
            fleet.remove(f.getFleetID());
            return 2;
        }
        else 
        {
            return -1;
        }
    }
    
    public String toString()
    {
        return "Admiral name: " + name + "\n" + "Warchest: " + warchest + 
                "\n" + "Defeated: " + defeated + "\n" + "Fleet: " +
                fleetString() + "\n";
    }
    
    public String fleetString()//duplicate method from UFFleet, place in SW?
    {
        String s = "";
        Collection<Force> _fleet = fleet.values();
        
        if (_fleet.size() == 0)
        {
            return "No forces";
        }
        else
        {
            for(Force f : _fleet)
            {
            s += "\n" + "fleet ID: " + f.getFleetID() + "\n" + "Name: " + 
                  f.getName() + "\n" + "Force type: " + f.getType() + "\n" + 
                  "Activation fee: " + f.getActivationFee() + "\n" + 
                  "Battle strength: " + f.getBattleStrength() + "\n" + 
                  "Force state:" + f.getState() + "\n";
            }
        }
        return s;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public int getWarchest()
    {
        return warchest;
    }

    public void setWarchest(int warchest)
    {
        this.warchest = warchest;
    }

    public HashMap<String, Force> getFleet()
    {
        return fleet;
    }

    public void setFleet(HashMap<String, Force> fleet) 
    {
        this.fleet = fleet;
    }

    public boolean isDefeated() 
    {
        return defeated;
    }

    public void setDefeated(boolean defeated) 
    {
        this.defeated = defeated;
    }
}
