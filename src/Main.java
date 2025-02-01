import java.util.*;

public class ShoppingCart {

    public static void main(String[] args) {
        // Set up store
        Item[] store = setupStore();

        // Create cart based on user input
        ArrayList<Item> cart = createCart(store);

        // If cart is empty, print an appropriate message and exit
        if (cart.isEmpty()) {
            System.out.println("No valid input to cart");
            return;
        }

        // Print the receipt with the items in the cart
        printReceiptInOrder(cart);

        // Empty the cart in reverse order (LIFO)
        emptyCartReverseOrder(cart);
    }

    // Setup the store with predefined items
    public static Item[] setupStore() {
        Item[] store = new Item[5];
        store[0] = new Item("Bananas", 1.5);
        store[1] = new Item("Apple", 0.5);
        store[2] = new Item("Bread", 2.0);
        store[3] = new Item("Milk", 3.0);
        store[4] = new Item("Eggs", 2.5);
        return store;
    }

    // Create a cart from command line input
    public static ArrayList<Item> createCart(Item[] store) {
        ArrayList<Item> cart = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the item numbers to add to the cart (e.g., 0 1 3). Type 'done' to finish:");

        // Read user input
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("done")) break;
            try {
                int itemIndex = Integer.parseInt(input);
                if (itemIndex >= 0 && itemIndex < store.length) {
                    cart.add(store[itemIndex]);
                } else {
                    System.out.println("The store does not have an item of index " + itemIndex + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println(input + " is not a valid integer.");
            }
        }
        return cart;
    }

    // Print receipt with items in the cart, subtotal, sales tax, and total
    public static void printReceiptInOrder(ArrayList<Item> cart) {
        System.out.println("\nReceipt");
        System.out.println("=========================");
        double subtotal = 0;
        for (Item item : cart) {
            System.out.println(item.getItemName() + " " + item.getItemPrice());
            subtotal += item.getItemPrice();
        }

        double tax = subtotal + 0.05;
        double total = subtotal + tax;

        System.out.println("=========================");
        System.out.println("(a) Subtotal: " + String.format("%.2f", subtotal));
        System.out.println("(b) Sales Tax: 5%");
        System.out.println("(c) Total: " + String.format("%.2f", total));
    }

    // Empty the cart in reverse order (LIFO)
    public static void emptyCartReverseOrder(ArrayList<Item> cart) {
        System.out.println("\nRemoving all items from the cart in 'Last In First Out' order...");
        while (!cart.isEmpty()) {
            Item lastItem = cart.remove(cart.size() - 1);
            System.out.println("Removing: " + lastItem.getItemName());
        }
        System.out.println("Cart has been emptied");
    }
}
