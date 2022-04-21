/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OODCwk;

import java.io.Serializable;

/**
 *
 * @author folke
 */
public abstract class Force implements Serializable {
    private String fleetID;
    private String name;
    private ForceType type;
    private int activationFee;
    private int battleStrength;
    private ForceState state;

    public Force()
    {
        this.fleetID = "";
        this.name = "";
        this.type = ForceType.WING;
        this.activationFee = 0;
        this.battleStrength = 0;
        this.state = state.ACTIVE;    
    }
    
    public Force(String id, String name, ForceType type, int actFee, 
                    int bStrength, ForceState state)
    {
        this.fleetID = id;
        this.name = name;
        this.type = type;
        this.activationFee = actFee;
        this.battleStrength = bStrength;
        this.state = state;
    }
    
    public String toString()
    {
        return "fleet ID: " + fleetID + "\n" + "Name: " + name + "\n" + 
                "Force type: " + type + "\n" + "Activation fee: " + 
                activationFee + "\n" + "Battle strength: " + battleStrength + 
                "\n" + "Force state:" + state;
    }

    public String getFleetID() 
    {
        return fleetID;
    }

    public void setFleetID(String fleetID)
    {
        this.fleetID = fleetID;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ForceType getType()
    {
        return type;
    }

    public void setType(ForceType type)
    {
        this.type = type;
    }
    
    public int getActivationFee() 
    {
        return activationFee;
    }

    public void setActivationFee(int activationFee) 
    {
        this.activationFee = activationFee;
    }

    public int getBattleStrength() 
    {
        return battleStrength;
    }

    public void setBattleStrength(int battleStrength)
    {
        this.battleStrength = battleStrength;
    }

    public ForceState getState() 
    {
        return state;
    }

    public void setState(ForceState state) 
    {
        this.state = state;
    }
    
    
}
