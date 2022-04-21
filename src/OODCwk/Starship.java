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
public class Starship extends Force implements Serializable{
    private int laserCannons;
    private int photonTorpedoes;
    
    public Starship()
    {
        super();
        this.laserCannons = 0;
        this.photonTorpedoes = 0;
    }
    
    public Starship(String id, String name, ForceType type, int actFee, 
                int bStrength, ForceState state, int cannons, int torpedoes)
    {
        super(id, name, type, actFee, bStrength, state);
        this.laserCannons = cannons;
        this.photonTorpedoes = torpedoes;
    }

    public String toString()
    {
        return super.toString() + "\n" + "Number of laser cannons: " + 
                laserCannons + "\n" + "Number of photon torpedoes: " + 
                photonTorpedoes + "\n";
    }
}
