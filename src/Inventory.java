import javafx.fxml.Initializable;

import java.net.URL;
import java.util.*;

public class Inventory {

    TreeMap<String, LinkedList<Lootbox>> lootboxes;

    public LinkedList<String> allowedGameTypes;

    /**
     * Inventory constructor
     */
    public Inventory(){

        lootboxes = new TreeMap<>();

        allowedGameTypes = new LinkedList<String>(Arrays.asList(
                "FPS",
                "Card Strategy Game",
                "MMORPG",
                "Action RPG",
                "Mobile Game",
                "Battle Royale"
        ));



        for (String str:allowedGameTypes){
            lootboxes.put(str,new LinkedList<Lootbox>());
        }

    }

    public LinkedList<String> getAllowedGameTypes(){
        return allowedGameTypes;
    }




    public LinkedList<String> getGameTypes() {
        return allowedGameTypes;
    }

    /**
     * Getter for all lootboxes objects in inventory with a return of strings
     * @return
     */
    public LinkedList<String> getAllLootboxesStrings() {

        LinkedList<String> lbAll = new LinkedList<>();
        for (LinkedList<Lootbox> lbList:lootboxes.values()){
            for (Lootbox lb:lbList) {
                lbAll.add(lb.toString());
            }

        }

        return lbAll;
    }

    /**
     * Getter for all lootboxes objects in inventory
     * @return
     */
    public LinkedList<Lootbox> getAllLootboxes() {

        LinkedList<Lootbox> lbAll = new LinkedList<>();
        for (LinkedList<Lootbox> lbList:lootboxes.values()){
            for (Lootbox lb:lbList) {
                lbAll.add(lb);
            }

        }

        return lbAll;
    }

    /**
     * Getter for lootboxes objects under a category returning as strings
     * @param key
     * @return
     */
    public LinkedList<String> getCategorizedLootboxesStrings (String key) {

        LinkedList<String> lbCats = new LinkedList<>();
        for (Lootbox lb:lootboxes.get(key)){
            lbCats.add(lb.toString());
        }

        return lbCats;
    }

    /**
     * Getter for lootboxes objects under a category
     * @param key
     * @return
     */
    public LinkedList<Lootbox> getCategorizedLootboxes(String key) {

        LinkedList<Lootbox> lbCats = new LinkedList<>();
        for (Lootbox lb:lootboxes.get(key)){
            lbCats.add(lb);
        }

        return lbCats;
    }


    /**
     * Method which adds a given lootbox object to a specified category
     * @param key
     * @param lootbox
     */
    public void addLootbox (String key,Lootbox lootbox){

        System.out.println(lootboxes.toString());
        lootboxes.get(key).add(lootbox);
    }



}
