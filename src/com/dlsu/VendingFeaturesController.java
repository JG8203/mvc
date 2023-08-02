import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class VendingFeaturesController {
    private VendingMachineController machineController;
    private VendingFeaturesView view;
    
    public VendingFeaturesController(VendingMachineController machineController, VendingFeaturesView view) {
        this.machineController = machineController;
        this.view = view;
    }

    /**
    * Called when the application starts. Initializes the UI and displays the list of slots to be bought
    */
    public void start() {
        view.displayMenu(machineController.getModel().getSlots());

        view.setInsertAction(new ActionListener(){
            /**
            * This method is called when the user presses the insert button. It creates an action listener to insert the money into the machine
            * 
            * @param e - The ActionEvent that caused this
            */
            @Override
            public void actionPerformed(ActionEvent e){
                view.displayInsertMoneyPrompt();
                view.setSubmitInsertAction(new ActionListener(){
                    /**
                    * Inserts money into the machine. This method is called when the user presses the insert button. The value of the input field is a comma separated list of integers.
                    * 
                    * @param e - The ActionEvent that caused this method to be called
                    */
                    @Override
                    public void actionPerformed(ActionEvent e){
                        String temp = view.getInsertField();
                        System.out.println(temp);
                        List<Integer> bills = Arrays.stream(temp.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
                        machineController.insertMoney(bills);
                        view.disposeInsertMoney();
                    }
                });
            }
        });

        view.setBuyItemAction(new ActionListener(){
            /**
            * Called when the user clicks on the buy button. This is the place where the user can buy an item.
            * 
            * @param e - The ActionEvent that caused this method to be called
            */
            @Override
            public void actionPerformed(ActionEvent e){
                view.displayBuyItemPrompt();
                view.setSubmitBuyAction(new ActionListener(){
                    /**
                    * Called when the user clicks on the buy button. Buy the item at the selected slot and disposes the UI
                    * 
                    * @param e - The ActionEvent that caused this
                    */
                    @Override
                    public void actionPerformed(ActionEvent e){
                        int slotNumber = view.getBuyField();
                        view.disposeBuyPrompt();
                        machineController.buyItem(slotNumber);
                        
                    }

                });
            }
        });
        
        view.setCustomizeAction(new ActionListener(){
            /**
            * Called when user clicks on button. This is the place to do actions that need to be carried out in order to customize the product.
            * 
            * @param e - The ActionEvent that caused this method to be called
            */
            @Override
            public void actionPerformed(ActionEvent e){
                // This method is called when the user clicks on the product button.
                if (machineController instanceof SpecialVendingMachineController) {
                    view.displayCustomizeProductPrompt();
                    view.setSubmitCustomizeAction(new ActionListener(){
                        /**
                        * Called when the user clicks on the Customize button. This method is responsible for calling the MachineController#customizeProduct ( int ) method to customize the product that was selected.
                        * 
                        * @param e - The ActionEvent that caused this method to be called
                        */
                        @Override
                        public void actionPerformed(ActionEvent e){
                            int slotNumber = view.getCustomizeField();
                            ((SpecialVendingMachineController) machineController).customizeProduct(slotNumber);
                            view.disposeCustomizeProductPrompt();

                        }
                    });
                } else {
                    view.displayInvalidChoice();
                }
            }
        });
        view.setBackAction(new ActionListener(){
            /**
            * Disposes the features when the user clicks the Cancel button. This is called when the user clicks the Cancel button.
            * 
            * @param e - The ActionEvent that caused this method to be called
            */
            @Override
            public void actionPerformed(ActionEvent e){
                view.disposeFeatures();
            }
        });
    }
}