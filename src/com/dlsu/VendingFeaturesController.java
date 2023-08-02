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

    public void start() {
        view.displayMenu();
        
        view.setDisplayItemsAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                machineController.displayItems();
            }
        });

        view.setInsertAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                view.displayInsertMoneyPrompt();
                view.setSubmitInsertAction(new ActionListener(){
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
            @Override
            public void actionPerformed(ActionEvent e){
                view.displayBuyItemPrompt();
                view.setSubmitBuyAction(new ActionListener(){
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
            @Override
            public void actionPerformed(ActionEvent e){
                if (machineController instanceof SpecialVendingMachineController) {
                    view.displayCustomizeProductPrompt();
                    view.setSubmitCustomizeAction(new ActionListener(){
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
            @Override
            public void actionPerformed(ActionEvent e){
                view.disposeFeatures();
            }
        });
    }
}