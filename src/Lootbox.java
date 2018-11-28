import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.scene.image.Image;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Lootbox {

        private String name, rarity, gameType;
        private double price;
        private int numOfItems;
        private int numInStock;

        private Image thumbnail;

        private ArrayList<String> allowedRarity = new ArrayList<String>(Arrays.asList(
                "Common",
                "Uncommon",
                "Rare",
                "Epic",
                "Legendary",
                "Mythic"
        ));
        private ArrayList<String> allowedGameTypes = new ArrayList<String>(Arrays.asList(
                 "FPS",
                 "Card Strategy Game",
                 "MMORPG",
                 "Action RPG",
                 "Mobile Game",
                 "Battle Royale"
        ));


        public Lootbox(String name, String gameType, String rarity, double price, int numOfItems, int numInStock){

            setName(name);
            setGameType(gameType);
            setRarity(rarity);
            setPrice(price);
            setNumOfItems(numOfItems);
            setNumInStock(numInStock);
            setThumbnail(rarity, gameType);

        }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.equals("")){
            this.name = name;
        }else{
            throw new IllegalArgumentException("Name can't be blank!");
        }

    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        if (allowedRarity.contains(rarity)){
            this.rarity = rarity;
        }else{
            throw new IllegalArgumentException("Invalid rarity!");
        }
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {

        if (allowedGameTypes.contains(gameType)){
            this.gameType = gameType;
        }else{
            throw new IllegalArgumentException("Invalid game genre!");
        }
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {
            /**
        try {

        }catch (InvalidArgumentException e){
            System.err.println("String is invalid!");
        }
             */

            if(price >= 0 ){
                this.price = price;
            }else{
                throw new IllegalArgumentException("Price is invalid!");
            }
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        if(numOfItems >= 0 ){
            this.numOfItems = numOfItems;
        }else{
            throw new IllegalArgumentException("Number of items is invalid!");
        }
    }

    public int getNumInStock() {
        return numInStock;
    }

    public void setNumInStock(int numInStock) {
        if(numInStock >= 0 ){
            this.numInStock = numInStock;
        }else{
            throw new IllegalArgumentException("Number in stock is invalid!");
        }
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    /**
     * Sets the correct image according to rarity and game type
     * @param rarity
     * @param gameType
     */
    public void setThumbnail(String rarity, String gameType) {
        this.thumbnail = thumbnail;
    }
}


