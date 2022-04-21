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
public class WarBird extends Force implements Serializable {
    private boolean cloaking;
    
    public WarBird()
    {
        super();
        this.cloaking = false;
    }
    
    public WarBird(String id, String name, ForceType type, int actFee, 
                int bStrength, ForceState state, boolean cloaking)
    {
        super(id, name, type, actFee, bStrength, state);
        this.cloaking = cloaking;
    }
    
    public String toString()
    {
        return super.toString() + "\n" + "Cloaking: " + cloaking + "\n";
    }

    public boolean isCloaking()
    {
        return cloaking;
    }

    public void setCloaking(boolean cloaking)
    {
        this.cloaking = cloaking;
    }
    
    
}
