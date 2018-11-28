import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeMap;

public class Inventory {

    TreeMap<String, LinkedList<Lootbox>> lootboxes;

    private LinkedList<String> allowedGameTypes = new LinkedList<String>(Arrays.asList(
            "FPS",
            "Card Strategy Game",
            "MMORPG",
            "Action RPG",
            "Mobile Game",
            "Battle Royale"
    ));

    public LinkedList<String> getGameTypes() {
        return allowedGameTypes;
    }

    public TreeMap<String, LinkedList<Lootbox>> getAllLootboxes() {
        return lootboxes;
    }

    public LinkedList<Lootbox> getCategorizedLootboxes(String key) {
        return lootboxes.get(key);
    }

    /*
    DOESNT MAKE SENSE YET! Dont have an idea how to write this up

    Could u explain your hint -

    Hint:  use the category name as the key and put a LinkedList
    as the value in the TreeMap.

    Why won't we use TreeMap<String, Product>, where String will be a category name?

    Setting the Linked list as a value doesn't make sense to me.... for now.
     */
    public void addLootbox (String key,Lootbox lootbox){
        lootboxes.put(key,lootbox);
    }
}
