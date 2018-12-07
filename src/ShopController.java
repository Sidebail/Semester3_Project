import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;


public class ShopController implements Initializable {



    @FXML
    private ListView<String> lvList;

    @FXML
    private ComboBox<String> cbGametypes;

    @FXML
    private RadioButton rbPriceHigh;

    @FXML
    private RadioButton rbPriceLow;

    @FXML
    private RadioButton rbName;

    @FXML
    private RadioButton rbRarity;

    @FXML
    private ImageView imgImage;

    @FXML
    private Button bSell;

    @FXML
    private Label labInvVal;

    @FXML
    private Label labCatVal;

    /**
     * onClick event for the Sell button
     * @param event
     */
    @FXML
    void onClickSell(ActionEvent event) {

        selectedItemIndex = lvList.getSelectionModel().getSelectedIndex();
        lootboxesList.get(lvList.getSelectionModel().getSelectedIndex()).sellLootbox();



        updateView();
    }

    /**
     * onClick event for radio button
     * @param event
     */
    @FXML
    void onRadioHighToLow(ActionEvent event) {
        rbName.setSelected(false);
        rbPriceHigh.setSelected(true);
        rbPriceLow.setSelected(false);
        rbRarity.setSelected(false);

        updateView();
    }

    /**
     * onClick event for radio button
     * @param event
     */
    @FXML
    void onRadioLowToHigh(ActionEvent event) {
        rbName.setSelected(false);
        rbPriceHigh.setSelected(false);
        rbPriceLow.setSelected(true);
        rbRarity.setSelected(false);

        updateView();
    }

    /**
     * onClick event for radio button
     * @param event
     */
    @FXML
    void onRadioName(ActionEvent event) {
        rbName.setSelected(true);
        rbPriceHigh.setSelected(false);
        rbPriceLow.setSelected(false);
        rbRarity.setSelected(false);

        updateView();
    }

    /**
     * onClick event for radio button
     * @param event
     */
    @FXML
    void onRadioRarity(ActionEvent event) {
        rbName.setSelected(false);
        rbPriceHigh.setSelected(false);
        rbPriceLow.setSelected(false);
        rbRarity.setSelected(true);

        updateView();
    }

    /**
     * Category change listener
     * @param event
     */
    @FXML
    void onCategoryChosen(ActionEvent event) {
        if (!cbGametypes.getValue().equals("All...")){
            categorizedLootboxes(cbGametypes.getValue());
        }else{
            allLootboxes();
        }

    }

    /**
     * onClick listener for the list view
     * @param event
     */
    @FXML
    void onListClick(MouseEvent event) {

        selectedItemIndex = lvList.getSelectionModel().getSelectedIndex();

        updateView();
    }

    private Inventory inventory;
    private LinkedList<Lootbox> lootboxesList;
    private String chosenCategory = "";
    private int selectedItemIndex = 0;


    /**
     * Initialize method for ShopController. Sets lootboxes, populates the inventory
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {



        Lootbox lb1 = new Lootbox("Battlepack","FPS","Legendary",19.99,1,10);
        Lootbox lb2 = new Lootbox("Battlepack","FPS","Rare",9.99,1,10);
        Lootbox lb3 = new Lootbox("Hearthstone Pack","Card Strategy Game","Common",9.99,5,10);
        Lootbox lb4 = new Lootbox("Hearthstone Pack","Card Strategy Game","Uncommon",9.99,5,10);
        Lootbox lb5 = new Lootbox("Overwatch Crate","Action RPG","Common",4.99,4,10);
        Lootbox lb6 = new Lootbox("Overwatch Crate","Action RPG","Legendary",14.99,4,5);
        Lootbox lb7 = new Lootbox("WOW Crate","MMORPG","Rare",14.99,1,10);
        Lootbox lb8 = new Lootbox("WOW Crate","MMORPG","Mythic",54.99,1,10);
        Lootbox lb9 = new Lootbox("PUBG Biker Crate","Battle Royale","Uncommon",2.99,1,10);
        Lootbox lb10 = new Lootbox("PUBG Mobile Erangel Set Crate","Mobile Game","Mythic",49.99,7,10);


        inventory = new Inventory();
        cbGametypes.getItems().add("All...");
        cbGametypes.getItems().addAll(inventory.getAllowedGameTypes());


        inventory.addLootbox(lb1.getGameType(),lb1);
        inventory.addLootbox(lb2.getGameType(),lb2);
        inventory.addLootbox(lb3.getGameType(),lb3);
        inventory.addLootbox(lb4.getGameType(),lb4);
        inventory.addLootbox(lb5.getGameType(),lb5);
        inventory.addLootbox(lb6.getGameType(),lb6);
        inventory.addLootbox(lb7.getGameType(),lb7);
        inventory.addLootbox(lb8.getGameType(),lb8);
        inventory.addLootbox(lb9.getGameType(),lb9);
        inventory.addLootbox(lb10.getGameType(),lb10);

        allLootboxes();


    }

    /**
     * Sets all lootboxes from inventory to the local lootboxes list
     */
    public void allLootboxes(){

        chosenCategory = "";
        lootboxesList = inventory.getAllLootboxes();
        selectedItemIndex = 0;
        updateView();

    }

    /**
     * Sets all lootboxes of a specific category from inventory to the local lootboxes list
     * @param category
     */
    public void categorizedLootboxes(String category){

        lootboxesList = inventory.getCategorizedLootboxes(category);

        chosenCategory = category;
        selectedItemIndex = 0;
        updateView();
    }

    /**
     * A method to update all view components: counters, list view, image and etc.
     */
    public void updateView(){
        lvList.getItems().clear();


        if (cbGametypes.getValue() != null && !cbGametypes.getValue().equals("All...")){
            double value = 0;
            for (Lootbox lb:lootboxesList){
                value += lb.getPrice() * lb.getNumInStock();
            }

            labCatVal.setText("$" + String.format("%1$,.2f",inventory.getLootboxes().get(chosenCategory).stream().mapToDouble(lb -> lb.getPrice() * lb.getNumInStock()).sum()));
        }else {
            labCatVal.setText("N/A");
        }


        double invValue = 0;
        for (Lootbox lb:inventory.getAllLootboxes()){
            invValue += lb.getPrice() * lb.getNumInStock();
        }



        labInvVal.setText("$" + String.format("%1$,.2f",inventory.getLootboxes().values().stream().mapToDouble(lbList -> {
            double i = 0;
            for(Lootbox lb:lbList){
                i += lb.getPrice() * lb.getNumInStock();
            }
            return i;
        }).sum()));


        if (chosenCategory.equals("")){
            lootboxesList = inventory.getAllLootboxes();


        }else{
            lootboxesList = inventory.getCategorizedLootboxes(chosenCategory);
        }



        customSorting();

        for (Lootbox lb:lootboxesList){
            lvList.getItems().add(lb.toString());
        }

        lvList.getSelectionModel().select(selectedItemIndex);

        imgImage.setImage(lootboxesList.get(lvList.getSelectionModel().getSelectedIndex()).getImage());
    }


    /**
     * A method that does sorting, according to the radio buttons values
     */
    public void customSorting(){
        if (rbName.isSelected()){
            lootboxesList.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        }

        if (rbRarity.isSelected()){
            lootboxesList.sort((o1, o2) -> {
                if (o1.allowedRarity.indexOf(o1.getRarity()) > o2.allowedRarity.indexOf(o2.getRarity())){
                    System.out.println(o1.toString());
                    return 1;
                }

                if (o1.allowedRarity.indexOf(o1.getRarity()) < o2.allowedRarity.indexOf(o2.getRarity())){
                    System.out.println(-1);
                    return -1;
                }

                System.out.println(o1.allowedRarity.indexOf(o1.getRarity()));
                return 0;
            });
        }
        if (rbPriceHigh.isSelected()){
            lootboxesList.sort((o1, o2) -> {
                if (o1.getPrice() < o2.getPrice()){
                    System.out.println(o1.toString());
                    return 1;
                }

                if (o1.getPrice() > o2.getPrice()){
                    System.out.println(-1);
                    return -1;
                }


                return 0;
            });
        }
        if (rbPriceLow.isSelected()){
            lootboxesList.sort((o1, o2) -> {
                if (o1.getPrice() > o2.getPrice()){
                    System.out.println(o1.toString());
                    return 1;
                }

                if (o1.getPrice() < o2.getPrice()){
                    System.out.println("SORTED!" + -1);
                    return -1;
                }


                return 0;
            });
        }
    }
}
