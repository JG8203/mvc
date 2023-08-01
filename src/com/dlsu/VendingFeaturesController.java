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
                
            }
        });
        /*while (true) {
            view.displayMenu();
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                machineController.displayItems();
            } else if (choice.equals("2")) {
                view.displayInsertMoneyPrompt();
                List<Integer> bills = Arrays.stream(scanner.nextLine().split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                machineController.insertMoney(bills);
            } else if (choice.equals("3")) {
                view.displayBuyItemPrompt();
                int slotNumber = Integer.parseInt(scanner.nextLine());
                machineController.buyItem(slotNumber);
            } else if (choice.equals("4")) {
                if (machineController instanceof SpecialVendingMachineController) {
                    view.displayCustomizeProductPrompt();
                    int slotNumber = Integer.parseInt(scanner.nextLine());
                    ((SpecialVendingMachineController) machineController).customizeProduct(slotNumber, scanner);
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else if (choice.equals("5")) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }*/
    }
}