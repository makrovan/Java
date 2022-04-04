import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;
        final int NUMBER_OF_DATA_ELEMENTS = 4;

        String[] components = data.split("\\s+");
        if (components.length != NUMBER_OF_DATA_ELEMENTS) {
            throw new IllegalArgumentException("Wrong format in add command!");
        }
        if (!components[INDEX_PHONE].matches("[+7][0-9]{11}")) {
            throw new IllegalArgumentException("Wrong format in phone number!");
        }
        if (!components[INDEX_EMAIL].matches("[a-zA-Z.]+[@][a-zA-Z]+[.][a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("Wrong format in e-mail!");
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}