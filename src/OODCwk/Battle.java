/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OODCwk;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author folke
 */
public class Battle implements Serializable {
    private int battleNo;
    private BattleType type;
    private String enemyName;
    private int enemyStrength;
    private int losses;
    private int gains;

    public Battle()
    {
        
    }
    
    public Battle(int no, BattleType type, String name, int strength, 
                    int losses, int gains)
    {
        this.battleNo = no;
        this.type = type;
        this.enemyName = name;
        this.enemyStrength = strength;
        this.losses = losses;
        this.gains = gains;
    }
    
    public String toString()
    {
        return "Battle ID: " + battleNo + "\n" + "Battle type: " + type + "\n" +
                "Enemy name: " + enemyName + "\n" + "Enemy strength: " + 
                enemyStrength + "\n" + "Losses: " + losses + "\n" + 
                "Gains: " + gains;
    }

    public int getBattleNo()
    {
        return battleNo;
    }

    public void setBattleNo(int battleNo)
    {
        this.battleNo = battleNo;
    }

    public BattleType getType()
    {
        return type;
    }

    public void setType(BattleType type)
    {
        this.type = type;
    }

    public int getEnemyStrength() 
    {
        return enemyStrength;
    }

    public void setEnemyStrength(int enemyStrength)
    {
        this.enemyStrength = enemyStrength;
    }

    public int getLosses()
    {
        return losses;
    }

    public void setLosses(int losses)
    {
        this.losses = losses;
    }

    public int getGains()
    {
        return gains;
    }

    public void setGains(int gains)
    {
        this.gains = gains;
    }
    
    
}
