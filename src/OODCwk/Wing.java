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
public class Wing extends Force implements Serializable {
    private int strikers;
    
    public Wing()
    {
        super();
        this.strikers = 0;
    }
    
    public Wing(String id, String name, ForceType type, int actFee, 
                int bStrength, ForceState state, int strikers)
    {
        super(id, name, type, actFee, bStrength, state);
        this.strikers = strikers;
    }
    
    public String toString()
    {
        return super.toString() + "\n" + "Number of strikers: " + strikers + "\n";
    }
}
