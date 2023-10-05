package finalproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Item {
    private String productId;
    private String name;
    private double unitCost;
    private int quantity;

    public Item(String productId, String name, double unitCost, int quantity) {
        this.productId = productId;
        this.name = name;
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class Inventory {
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void updateItem(String productId, int quantity) {
        for (Item item : items) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(quantity);
                break;
            }
        }
    }

    public void deleteItem(String productId) {
        for (Item item : items) {
            if (item.getProductId().equals(productId)) {
                items.remove(item);
                break;
            }
        }
    }

    public Item searchItem(String query) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(query) || item.getProductId().equalsIgnoreCase(query)) {
                return item;
            }
        }
        return null;
    }

    public List<Item> getItems() {
        return items;
    }
}

class InventoryManagementSystem {
    private Inventory inventory;
    private Scanner scanner;

    public InventoryManagementSystem() {
        inventory = new Inventory();
        scanner = new Scanner(System.in);
    }

    public void run() {
        int choice;
        do {
            System.out.println("Main Menu:");
            System.out.println("1) Admin");
            System.out.println("2) User");
            System.out.println("3) Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    userMenu();
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);
    }

    private void adminMenu() {
        int choice;
        do {
            System.out.println("Admin Menu:");
            System.out.println("1) Add new item");
            System.out.println("2) Search and update item");
            System.out.println("3) Search and delete item");
            System.out.println("4) Back to main menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    searchAndUpdateItem();
                    break;
                case 3:
                    searchAndDeleteItem();
                    break;
                case 4:
                    System.out.println("Returning to the main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
    }

       private void userMenu() {
        int choice;
        do {
            System.out.println("User Menu:");
            System.out.println("1) Search item");
            System.out.println("2) Place order (or purchase item)");
            System.out.println("3) Back to main menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    searchItem();
                    break;
                case 2:
                    placeOrder();
                    break;
                case 3:
                    System.out.println("Returning to the main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);
    }

    private void addItem() {
        System.out.println("Adding a new item to the inventory:");
        System.out.print("Enter the product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the unit cost: ");
        double unitCost = scanner.nextDouble();
        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Item newItem = new Item(productId, name, unitCost, quantity);
        inventory.addItem(newItem);
        System.out.println("Item added successfully!");
    }

    private void searchAndUpdateItem() {
        System.out.print("Enter the name or product ID to search for the item: ");
        String query = scanner.nextLine();
        Item item = inventory.searchItem(query);
        if (item != null) {
            System.out.println("Item found:");
            System.out.println("Product ID: " + item.getProductId());
            System.out.println("Name: " + item.getName());
            System.out.println("Unit Cost: $" + item.getUnitCost());
            System.out.println("Quantity: " + item.getQuantity());

            System.out.print("Enter the new quantity: ");
            int newQuantity = scanner.nextInt();
            scanner.nextLine();

            inventory.updateItem(item.getProductId(), newQuantity);
            System.out.println("Item updated successfully!");
        } else {
            System.out.println("Item not found.");
        }
    }

    private void searchAndDeleteItem() {
        System.out.print("Enter the name or product ID to search for the item: ");
        String query = scanner.nextLine();
        Item item = inventory.searchItem(query);
        if (item != null) {
            System.out.println("Item found:");
            System.out.println("Product ID: " + item.getProductId());
            System.out.println("Name: " + item.getName());
            System.out.println("Unit Cost: $" + item.getUnitCost());
            System.out.println("Quantity: " + item.getQuantity());

            System.out.print("Are you sure you want to delete this item? (Y/N): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("Y")) {
                inventory.deleteItem(item.getProductId());
                System.out.println("Item deleted successfully!");
            } else {
                System.out.println("Deletion canceled.");
            }
        } else {
            System.out.println("Item not found.");
        }
    }

    private void searchItem() {
        System.out.print("Enter the name or product ID to search for the item: ");
        String query = scanner.nextLine();
        Item item = inventory.searchItem(query);
        if (item != null) {
            System.out.println("Item found:");
            System.out.println("Product ID: " + item.getProductId());
            System.out.println("Name: " + item.getName());
            System.out.println("Unit Cost: $" + item.getUnitCost());
                       System.out.println("Quantity: " + item.getQuantity());
        } else {
            System.out.println("Item not found.");
        }
    }

    private void placeOrder() {
        System.out.print("Enter the name or product ID to search for the item: ");
        String query = scanner.nextLine();
        Item item = inventory.searchItem(query);
        if (item != null) {
            System.out.println("Item found:");
            System.out.println("Product ID: " + item.getProductId());
            System.out.println("Name: " + item.getName());
            System.out.println("Unit Cost: $" + item.getUnitCost());
            System.out.println("Quantity: " + item.getQuantity());

            System.out.print("Enter the quantity to purchase: ");
            int quantityToPurchase = scanner.nextInt();
            scanner.nextLine();

            if (quantityToPurchase <= item.getQuantity()) {
                double subtotal = item.getUnitCost() * quantityToPurchase;
                double tax = subtotal * 0.07;
                double total = subtotal + tax;

                System.out.println("Total Cost: $" + total);

                // Update the inventory
                int newQuantity = item.getQuantity() - quantityToPurchase;
                inventory.updateItem(item.getProductId(), newQuantity);
                System.out.println("Purchase completed successfully!");
            } else {
                System.out.println("Insufficient quantity available for purchase.");
            }
        } else {
            System.out.println("Item not found.");
        }
    }

    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();
        ims.run();
    }
}



