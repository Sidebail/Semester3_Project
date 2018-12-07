import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.scene.image.Image;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Lootbox implements Comparable<Lootbox>{

        private String name, rarity, gameType;
        private double price;
        private int numOfItems;
        private int numInStock;

        private Image thumbnail;

        public ArrayList<String> allowedRarity = new ArrayList<String>(Arrays.asList(
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

    /**
     * Lootbox constructor
     * @param name
     * @param gameType
     * @param rarity
     * @param price
     * @param numOfItems
     * @param numInStock
     */
        public Lootbox(String name, String gameType, String rarity, double price, int numOfItems, int numInStock){

            setName(name);
            setGameType(gameType);
            setRarity(rarity);
            setPrice(price);
            setNumOfItems(numOfItems);
            setNumInStock(numInStock);
            setImage();
        }

    /**
     * All getters and setters with validation (is necessary)
     *
     */


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





    public void sellLootbox(){
        if (numInStock > 0){
            numInStock--;
        }
    }

    @Override
    public String toString() {
        return rarity + " " + name + " (" + numOfItems +" items)     PRICE: " + price + "; In stock: " + numInStock ;
    }


    @Override
    public int compareTo(Lootbox o) {
        if (allowedGameTypes.indexOf(rarity) > allowedGameTypes.indexOf(o.getRarity())){
           return 1;
        }

        if (allowedGameTypes.indexOf(rarity) < allowedGameTypes.indexOf(o.getRarity())){
            return -1;
        }

        return 0;
    }

    public void setImage(){

            try {
                thumbnail = new Image((("images/" + getRarity()+"_"+getName()+".png").replaceAll("\\s","")).toLowerCase());
            }catch (IllegalArgumentException e){
                thumbnail = new Image("images/default.jpg");
            }


        System.out.println((("images/" + getRarity()+"_"+getName()+".png").replaceAll("\\s","")).toLowerCase());
    }

    public Image getImage(){
        return thumbnail;
    }


}


