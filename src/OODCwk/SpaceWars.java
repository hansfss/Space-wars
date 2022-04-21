package OODCwk; 
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import OODCwk.Battle;
//import OODCwk.BattleType;
//import OODCwk.Force;
/**
 * This class implements the behaviour expected from a SWAT
 * system as required for 6COM1037 - Nov 2021
 * 
 * @author/s 
 * @version 
 */

public class SpaceWars  implements SWAT, Serializable 
{
    // Fields
    Player p;
    Battle b = new Battle();
    Wing w = new Wing();
    WarBird wb = new WarBird();
    Starship ss = new Starship();
    UFFleet ufleet = new UFFleet();
    private HashMap<Integer, Battle> battles = new HashMap<Integer, Battle>();
    private HashMap<String, Force> forces = new HashMap<String, Force>();
    private Collection<Force> UFF;
    private Collection<Force> ASF;
    private HashMap<String, Force> allForces = new HashMap<String, Force>();

//**************** SWAT **************************  
    /** Constructor requires the name of the admiral
     * @param admiral the name of the admiral
     */  
    public SpaceWars(String admiral)
    {
        p = new Player(admiral);
        setupForces(); 
        setupBattles();
    }
    
    public SpaceWars(String admiral, HashMap<Integer, Battle> _battles)
    {
        p = new Player(admiral);
        setupForces(); 
        battles = _battles;
        //setupBattles();
    }
    
    public SpaceWars(String admiral, int warchest, boolean defeated,
                HashMap<String, Force> fleet)
    {
        p = new Player(admiral, warchest, defeated, fleet);
        setupForces(); 
        setupBattles();
    }

    /**Returns a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the 
     * active Star Fleet(ASF),(or, "No forces" if Star Fleet is empty)
     * @return a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the 
     * Star Fleet,(or, "No forces" if Star Fleet is empty)
     **/
    public String toString()
    {
        return p.toString();
    }
    
    /** returns the number of bit coins in the war chest
     * @returns the number of bit coins in the war chest
     */
    public int getWarchest()
    {
        return p.getWarchest();
    }
     
    /** returns true if war chest <=0 and the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     * @returns true if war chest <=0 and the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     */
    public boolean isDefeated()
    {
        if (p.getWarchest() <= 0 && p.getFleet().size() == 0){
            p.setDefeated(true);
            return true;
        }
        return false;
    }
    
    /**Returns true if force is in the United Forces Fleet(UFF), else false
     * @param ref reference of the force
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public boolean isInUFFleet(String ref) 
    {
        if (ufleet.getFleet().containsKey(ref))
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    /**Returns a String representation of all forces in the United Forces Fleet(UFF)
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public String getUFFleet()
    {   
        return ufleet.toString();
    }
        
    /** Returns details of the force in the game with the given reference code 
     * @return details of the force in the game with the given reference code
     **/
    public String getForceDetails(String ref)
    {
        UFF = ufleet.getFleet().values();
        ASF = p.getFleet().values();
        if (ufleet.getFleet().containsKey(ref))
        {
            return ufleet.getFleet().get(ref).toString();
        }
        else if (p.getFleet().containsKey(ref))
        {
            return p.getFleet().get(ref).toString();
        }
        else
        {
            return "No such force";
        }
    }       
    
 // ***************** active Star Fleet Forces ************************   
    /** Allows a force to be activated into the active Star Fleet(ASF), but 
     * only if there are enough resources for the activation fee.The force's 
     * state is set to "active"
     * @param ref represents the reference code of the force
     * @return 0 if force is activated, 1 if force is not in the UFF
      * 2 if not enough money, -1 if no such force
     **/       
    public int activateForce(String ref)
    {
        if (!ufleet.getFleet().containsKey(ref))
        {
            //return 1;
            return -1;
        }
        else if (p.getWarchest() < ufleet.force(ref).getActivationFee())
        {
            return 2;
        }
        else 
        {
            if (!ufleet.getFleet().get(ref).getState().equals(ForceState.DESTROYED))
            {
                p.addForce(ufleet.remove(ref));
                return 0;
            }
        }
        return -1;
    }
    
        
    /** Returns true if the force with the reference code is in 
     * the active Star Fleet(ASF), false otherwise.
     * @param ref is the reference code of the force
     * @return returns true if the force with the reference code
     * is in the active Star Fleet(ASF), false otherwise.
     **/
    public boolean isInASFleet(String ref)
    {
        if (p.getFleet().containsKey(ref))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    /** Recalls a force from the Star Fleet(ASF) back to the UFF dock, but only  
     * if they are in the active Star Fleet(ASF)
     * pre-condition: isInASFleet(ref)
     * @param ref is the reference code of the force
     **/
    public void recallForce(String ref)
    {
        if (p.getFleet().containsKey(ref))
        {
            ufleet.addForce(p.returnForce(ref));
        }
    }   
        
    /**Returns a String representation of the forces in the active 
     * Star Fleet(ASF), or the message "No forces activated"
     * @return a String representation of the forces in the active
     * Star Fleet, or the message "No forces activated"
     **/
    public String getASFleet()
    {
        return p.fleetString();
    }
       
    
//**********************Battles************************* 
    /** returns true if the number represents a battle
     * @param num is the number of the required battle
     * @returns true if the number represents a battle
     **/
     public boolean isBattle(int num)
     {
         if (battles.containsKey(num))
         {
             return true;
         }
         else
         {
             return false;
         }
     }
    
    
    /** Provides a String representation of a battle given by 
     * the battle number
     * @param num the number of the battle
     * @return returns a String representation of a battle given by 
     * the battle number
     **/
    public String getBattle(int num)
    {
        b = battles.get(num);
        return b.toString();
    }
    
    /** Provides a String representation of all battles 
     * @return returns a String representation of all battles
     **/
    public String getAllBattles()
    {
        return battles.toString();
    }
     
     
    /** Retrieves the battle represented by the battle number.Finds 
      * a force from the active Star Fleet which can engage in the battle.The  
      * results of battle will be one of the following: 
      * 0 - Battle won, battle gains added to the warchest, 
      * 1 - Battle lost as no suitable force available, battle losses 
      * deducted from warchest 
      * 2 - Battle lost on battle strength , battle losses 
      * deducted from warchest and force destroyed
      * 3 - If a battle is lost and admiral completely defeated (no resources and 
      * no forces to recall) 
      * -1 - no such battle
      * @param battleNo is the number of the battle
      * @return an int showing the result of the battle
      */ 
    public int doBattle(int battleNo)
    { 
        b = battles.get(battleNo);
        BattleType bt = b.getType();
        Collection<Force> fleet = p.getFleet().values();
        ArrayList<Force> forcesToRemove = new ArrayList<Force>();
        for (Force f : fleet)
        {
            ForceType ft = f.getType();
            if (bt.equals(BattleType.SKIRMISH))
            {
                if (ft.equals(ForceType.WARBIRD))
                {
                    forcesToRemove.add(f);
                }
            }
            else if (bt.equals(BattleType.AMBUSH))
            {
                if (ft.equals(ForceType.STARSHIP))
                {
                    forcesToRemove.add(f);
                }
                else if (ft.equals(ForceType.WARBIRD))
                {
                    WarBird wb = (WarBird) f;
                    if (!wb.isCloaking())
                    {
                        forcesToRemove.add(f);
                    }
                }
            }
            else if (bt.equals(BattleType.FIGHT))
            {
                if (ft.equals(ForceType.WING))
                {
                    forcesToRemove.add(f);
                }
            }
        }   
        fleet.removeAll(forcesToRemove);
        int result = p.engageInBattle(b, /*battleType,*/ fleet, ufleet);
        return result;
    }
       
    //**********************private methods*****************************
    private void setupForces()
    {       
        forces.put("IW1", w = new Wing("IW1", "Twisters", ForceType.WING, 200, 200, ForceState.DOCKED, 10));
        forces.put("SS2", ss = new Starship("SS2", "Enterprise", ForceType.STARSHIP, 300, 200, ForceState.DOCKED, 10, 20));
        forces.put("WB3", wb = new WarBird("WB3", "Droop", ForceType.WARBIRD, 300, 100, ForceState.DOCKED, false));
        forces.put("IW4", w = new Wing("IW4", "Wingers", ForceType.WING, 200, 400, ForceState.DOCKED, 20));
        forces.put("WB5", wb = new WarBird("WB5", "Hang", ForceType.WARBIRD, 400, 300, ForceState.DOCKED, true));
        forces.put("SS6", ss = new Starship("SS6", "Voyager", ForceType.STARSHIP, 450, 200, ForceState.DOCKED, 15, 10));
        forces.put("SS7", ss = new Starship("SS7", "Explorer", ForceType.STARSHIP, 120, 65, ForceState.DOCKED, 4, 5));
        forces.put("WB9", wb = new WarBird("WB9", "Hover", ForceType.WARBIRD, 300, 400, ForceState.DOCKED, false));
        forces.put("IW10", w = new Wing("IW10", "Flyers", ForceType.WING, 200, 100, ForceState.DOCKED, 5));

        ufleet.setFleet(forces);
    }
    
    private void setupBattles()
    {
        battles.put(1, b = new Battle(1, BattleType.FIGHT, "Borg", 200, 300, 100));
        battles.put(2, b = new Battle(2, BattleType.SKIRMISH, "Kardassians", 700, 200, 120));
        battles.put(3, b = new Battle(3, BattleType.AMBUSH, "Ferengi", 100, 400, 150));
        battles.put(4, b = new Battle(4, BattleType.FIGHT, "Ewoks", 600, 600, 200));
        battles.put(5, b = new Battle(5, BattleType.AMBUSH, "Borg", 500, 400, 90));
        battles.put(6, b = new Battle(6, BattleType.SKIRMISH, "Groaners", 150, 100, 100));
        battles.put(7, b = new Battle(7, BattleType.FIGHT, "Borg", 150, 500, 300));
        battles.put(8, b = new Battle(8, BattleType.AMBUSH, "Wailers", 300, 300, 300));
    }
    
    private void readBattles(String fname)
    {
        try 
        {
            FileInputStream fis = new FileInputStream(fname);
            ObjectInputStream ois = new ObjectInputStream(fis);
            battles = (HashMap<Integer, Battle>) ois.readObject();
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println(ex.toString());
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.toString());
        } 
        catch (ClassNotFoundException ex)
        {
            System.out.println(ex.toString());
        }
        SpaceWars sw = new SpaceWars(p.getName(), battles);
    }
 
    //*******************************************************************************
    //*******************************************************************************
  
   // ***************   file write/read  *********************
 
     /** Writes whole game to the specified file
      * @param fname name of file storing requests
      */
     public void saveGame(String fname)
     { //only save player object and remove from uffleet anything in asfleet
        try 
        {
            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(p);
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println(ex.toString());
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.toString());
        }
        
     }
     
    /** reads all information about the game from the specified file 
     * and returns a SpaceWars object
     * @param fname name of file storing the game
     * @return the game (as a SpaceWars object)
     */
    public SpaceWars restoreGame(String fname)
    {   // uses object serialisation 
        Player pl = new Player();
        try 
        {
            FileInputStream fis = new FileInputStream(fname);
            ObjectInputStream ois = new ObjectInputStream(fis);
            pl = (Player) ois.readObject();
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println(ex.toString());
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.toString());
        } 
        catch (ClassNotFoundException ex)
        {
            System.out.println(ex.toString());
        }
        
        SpaceWars sw = new SpaceWars(pl.getName(), pl.getWarchest(), 
                    pl.isDefeated(), pl.getFleet());
        return sw;
    } 
}



