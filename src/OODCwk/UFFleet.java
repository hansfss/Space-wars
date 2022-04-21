/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OODCwk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.RowFilter.Entry;

/**
 *
 * @author folke
 */
public class UFFleet implements Serializable{
    private HashMap<String, Force> fleet;
    
    public UFFleet()
    {
        this.fleet = new HashMap<String, Force>();
    }

    public Force remove(String key)
    {
        Force f = fleet.get(key);
        fleet.remove(key);
        return f;
    }
    
    public void addForce(Force f)
    {
        fleet.put(f.getFleetID(), f);
    }
    
    public Force force(String key)
    {
        return fleet.get(key);
    }
    
    public String toString()
    {
        String s = "";
        Collection<Force> _fleet = fleet.values();
        
        for(Force f : _fleet)
        {
            s += "\n" + "fleet ID: " + f.getFleetID() + "\n" + "Name: " + 
                  f.getName() + "\n" + "Force type: " + f.getType() + "\n" + 
                  "Activation fee: " + f.getActivationFee() + "\n" + 
                  "Battle strength: " + f.getBattleStrength() + "\n" +
                  "Force state:" + f.getState() + "\n";
        }

        return s;
    }

    public HashMap<String, Force> getFleet()
    {
        return fleet;
    }

    public void setFleet(HashMap<String, Force> fleet)
    {
        this.fleet = fleet;
    }
    
    //add one method to add individual forces and one 
    //method to extract one force
}
