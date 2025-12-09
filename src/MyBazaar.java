import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MyBazaar {
    public static List<Person> users = new ArrayList<>();
    public static List<Item> items = new ArrayList<>();
    public static List<Campaign> campaigns = new ArrayList<>();

    public static void initializeData(String userFile, String itemFile) {
        loadUsers(userFile);
        loadItems(itemFile);
    }

    private static void loadUsers(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\t");
                if (parts.length < 6) {
                    // input düzgün olduğu için bu hatalar zaten çıkmayacak
                    continue;
                }
                String role = parts[0].trim().toUpperCase();

                // TÜM string alanları trim’le
                String name  = parts[1].trim();
                String mail  = parts[2].trim();
                String dob   = parts[3].trim();
                String s4    = parts[4].trim();
                String s5    = parts[5].trim();

                try {
                    if (role.equals("ADMIN")) {
                        users.add(new Admin(name, mail, dob,
                                Double.parseDouble(s4), s5));
                    } else if (role.equals("TECH")) {
                        boolean isSenior = Integer.parseInt(s5) == 1;
                        users.add(new Technician(name, mail, dob,
                                Double.parseDouble(s4), isSenior));
                    } else if (role.equals("CUSTOMER")) {
                        users.add(new Customer(name, mail, dob,
                                Double.parseDouble(s4), s5));
                    }
                } catch (NumberFormatException nfe) {
                    // parse hatası olursa satırı atla
                }
            }
            // ÖDEVDE İSTENMEDİĞİ İÇİN:
            // System.out.println("Users loaded: " + users.size());
        } catch (IOException e) {
            // okunamazsa zaten program bozulur, ekstra çıktı istemiyoruz
        }
    }

    private static void loadItems(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int defaultStock = 1;   // senin tasarımın
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\t");
                if (parts.length < 2) continue;
                Item created = createItemFromTokens(parts, 0, defaultStock);
                if (created != null) items.add(created);
            }
            // System.out.println("Items loaded: " + items.size());  // kaldırıldı
        } catch (IOException e) {
        }
    }

    private static Item createItemFromTokens(String[] tokens, int startIndex, int defaultStock) {
        try {
            String type = tokens[startIndex].trim().toUpperCase();
            switch (type) {
                case "DESKTOP": {
                    if (tokens.length < startIndex + 11) return null;
                    double cost = Double.parseDouble(tokens[startIndex+1]);
                    String manuf = tokens[startIndex+2];
                    String brand = tokens[startIndex+3];
                    int volt = Integer.parseInt(tokens[startIndex+4]);
                    int watt = Integer.parseInt(tokens[startIndex+5]);
                    String os = tokens[startIndex+6];
                    String cpu = tokens[startIndex+7];
                    int ram = Integer.parseInt(tokens[startIndex+8]);
                    int hdd = Integer.parseInt(tokens[startIndex+9]);
                    String boxColor = tokens[startIndex+10];
                    return new Desktop(cost, defaultStock, manuf, brand, volt, watt, os, cpu, ram, hdd, boxColor);
                }
                case "LAPTOP": {
                    if (tokens.length < startIndex + 11) return null;
                    double cost = Double.parseDouble(tokens[startIndex+1]);
                    String manuf = tokens[startIndex+2];
                    String brand = tokens[startIndex+3];
                    int volt = Integer.parseInt(tokens[startIndex+4]);
                    int watt = Integer.parseInt(tokens[startIndex+5]);
                    String os = tokens[startIndex+6];
                    String cpu = tokens[startIndex+7];
                    int ram = Integer.parseInt(tokens[startIndex+8]);
                    int hdd = Integer.parseInt(tokens[startIndex+9]);
                    boolean hasHdmi = Integer.parseInt(tokens[startIndex+10]) == 1;
                    return new Laptop(cost, defaultStock, manuf, brand, volt, watt, os, cpu, ram, hdd, hasHdmi);
                }
                case "TABLET": {
                    if (tokens.length < startIndex + 11) return null;
                    double cost = Double.parseDouble(tokens[startIndex+1]);
                    String manuf = tokens[startIndex+2];
                    String brand = tokens[startIndex+3];
                    int volt = Integer.parseInt(tokens[startIndex+4]);
                    int watt = Integer.parseInt(tokens[startIndex+5]);
                    String os = tokens[startIndex+6];
                    String cpu = tokens[startIndex+7];
                    int ram = Integer.parseInt(tokens[startIndex+8]);
                    int hdd = Integer.parseInt(tokens[startIndex+9]);
                    String dim = tokens[startIndex+10];
                    return new Tablet(cost, defaultStock, manuf, brand, volt, watt, os, cpu, ram, hdd, dim);
                }
                case "TV": {
                    if (tokens.length < startIndex + 7) return null;
                    double cost = Double.parseDouble(tokens[startIndex+1]);
                    String manuf = tokens[startIndex+2];
                    String brand = tokens[startIndex+3];
                    int volt = Integer.parseInt(tokens[startIndex+4]);
                    int watt = Integer.parseInt(tokens[startIndex+5]);
                    int screenSize = Integer.parseInt(tokens[startIndex+6]);
                    return new TV(cost, defaultStock, manuf, brand, volt, watt, screenSize);
                }
                case "SMARTPHONE": {
                    if (tokens.length < startIndex + 7) return null;
                    double cost = Double.parseDouble(tokens[startIndex+1]);
                    String manuf = tokens[startIndex+2];
                    String brand = tokens[startIndex+3];
                    int volt = Integer.parseInt(tokens[startIndex+4]);
                    int watt = Integer.parseInt(tokens[startIndex+5]);
                    String screenType = tokens[startIndex+6];
                    return new SmartPhone(cost, defaultStock, manuf, brand, volt, watt, screenType);
                }
                case "BOOK": {
                    if (tokens.length < startIndex + 7) return null;
                    double cost = Double.parseDouble(tokens[startIndex+1]);
                    String release = tokens[startIndex+2];
                    String title = tokens[startIndex+3];
                    String publisher = tokens[startIndex+4];
                    String authors = tokens[startIndex+5];
                    int page = Integer.parseInt(tokens[startIndex+6]);
                    return new Book(cost, defaultStock, release, title, publisher, authors, page);
                }
                case "CDDVD": {
                    if (tokens.length < startIndex + 6) return null;
                    double cost = Double.parseDouble(tokens[startIndex+1]);
                    String release = tokens[startIndex+2];
                    String title = tokens[startIndex+3];
                    String composer = tokens[startIndex+4];
                    String songs = tokens[startIndex+5];
                    return new CDDVD(cost, defaultStock, release, title, composer, songs);
                }
                case "HAIRCARE": {
                    if (tokens.length < startIndex + 8) return null;
                    double cost = Double.parseDouble(tokens[startIndex+1]);
                    String manuf = tokens[startIndex+2];
                    String brand = tokens[startIndex+3];
                    boolean isOrganic = Integer.parseInt(tokens[startIndex+4]) == 1;
                    int expiration = Integer.parseInt(tokens[startIndex+5]);
                    int weight = Integer.parseInt(tokens[startIndex+6]);
                    boolean isMedicated = Integer.parseInt(tokens[startIndex+7]) == 1;
                    return new HairCare(cost, defaultStock, manuf, brand, isOrganic, expiration, weight, isMedicated);
                }
                case "SKINCARE": {
                    if (tokens.length < startIndex + 8) return null;
                    double cost = Double.parseDouble(tokens[startIndex+1]);
                    String manuf = tokens[startIndex+2];
                    String brand = tokens[startIndex+3];
                    boolean isOrganic = Integer.parseInt(tokens[startIndex+4]) == 1;
                    int expiration = Integer.parseInt(tokens[startIndex+5]);
                    int weight = Integer.parseInt(tokens[startIndex+6]);
                    boolean isSensitive = Integer.parseInt(tokens[startIndex+7]) == 1;
                    return new SkinCare(cost, defaultStock, manuf, brand, isOrganic, expiration, weight, isSensitive);
                }
                case "PERFUME": {
                    if (tokens.length < startIndex + 8) return null;
                    double cost = Double.parseDouble(tokens[startIndex+1]);
                    String manuf = tokens[startIndex+2];
                    String brand = tokens[startIndex+3];
                    boolean isOrganic = Integer.parseInt(tokens[startIndex+4]) == 1;
                    int expiration = Integer.parseInt(tokens[startIndex+5]);
                    int weight = Integer.parseInt(tokens[startIndex+6]);
                    int lasting = Integer.parseInt(tokens[startIndex+7]);
                    return new Perfume(cost, defaultStock, manuf, brand, isOrganic, expiration, weight, lasting);
                }
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static void processCommands(String commandFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(commandFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\t");
                String command = parts[0].trim().toUpperCase();

                try {
                    if (command.equals("ADDCUSTOMER")) {
                        if (parts.length < 7) continue;
                        Person admin = findPersonByName(parts[1].trim());
                        if (admin == null || !(admin instanceof Admin)) {
                            System.out.println("No admin person named " + parts[1].trim() + " exists!");
                            continue;
                        }
                        Customer c = new Customer(
                                parts[2].trim(), parts[3].trim(), parts[4].trim(),
                                Double.parseDouble(parts[5].trim()), parts[6].trim());
                        users.add(c);
                    }
                    else if (command.equals("SHOWCUSTOMER")) {
                        if (parts.length < 3) continue;
                        Person admin = findPersonByName(parts[1].trim());
                        if (admin == null || !(admin instanceof Admin)) {
                            System.out.println("No admin person named " + parts[1].trim() + " exists!");
                            continue;
                        }
                        Customer c = findCustomerById(Integer.parseInt(parts[2].trim()));
                        if (c == null) {
                            System.out.println("No customer with ID number " + parts[2].trim() + " exists!");
                        } else {
                            System.out.println(c.toString());
                        }
                    }
                    else if (command.equals("SHOWCUSTOMERS")) {
                        if (parts.length < 2) continue;
                        Person admin = findPersonByName(parts[1].trim());
                        if (admin == null || !(admin instanceof Admin)) {
                            System.out.println("No admin person named " + parts[1].trim() + " exists!");
                            continue;
                        }
                        for (Person p : users) {
                            if (p instanceof Customer) System.out.println(p.toString());
                        }
                    }
                    else if (command.equals("SHOWADMININFO")) {
                        if (parts.length < 2) continue;
                        Person admin = findPersonByName(parts[1].trim());
                        if (admin == null || !(admin instanceof Admin)) {
                            System.out.println("No admin person named " + parts[1].trim() + " exists!");
                        } else {
                            System.out.println(admin.toString());
                        }
                    }
                    else if (command.equals("CREATECAMPAIGN")) {
                        if (parts.length < 6) continue;
                        Person admin = findPersonByName(parts[1].trim());
                        if (admin == null || !(admin instanceof Admin)) {
                            System.out.println("No admin person named " + parts[1].trim() + " exists!");
                            continue;
                        }
                        String start = parts[2].trim();
                        String end   = parts[3].trim();
                        String type  = parts[4].trim().toUpperCase();
                        int rate     = Integer.parseInt(parts[5].trim());

                        if (rate > Campaign.MAX_DISCOUNT_RATE) {
                            System.out.println("Campaign was not created. Discount rate exceeds maximum rate of 50%.");
                        } else if (getActiveCampaign(type) != null) {
                            System.out.println("Campaign was not created. A campaign for this item type already exists.");
                        } else {
                            campaigns.add(new Campaign(start, end, type, rate));
                        }
                    }
                    else if (command.equals("ADDTOCART")) {
                        if (parts.length < 3) continue;
                        int cId = Integer.parseInt(parts[1].trim());
                        int iId = Integer.parseInt(parts[2].trim());
                        Customer c = findCustomerById(cId);
                        if (c == null) {
                            System.out.println("No customer with ID number " + parts[1].trim() + " exists!");
                            continue;
                        }
                        Item item = findItemById(iId);
                        if (item == null) {
                            System.out.println("Invalid item ID");
                        } else if (item.getStock() <= 0) {
                            System.out.println("We are sorry. The item is temporarily unavailable.");
                        } else {
                            c.getCart().addItem(item);
                            System.out.println("The item " + item.getClass().getSimpleName() + " has been successfully added to your cart.");
                        }
                    }
                    else if (command.equals("EMPTYCART")) {
                        if (parts.length < 2) continue;
                        Customer c = findCustomerById(Integer.parseInt(parts[1].trim()));
                        if (c == null) {
                            System.out.println("No customer with ID number " + parts[1].trim() + " exists!");
                        } else {
                            c.getCart().emptyCart();
                            System.out.println("The cart has been emptied.");
                        }
                    }
                    else if (command.equals("ORDER")) {
                        if (parts.length < 3) continue;
                        int cId = Integer.parseInt(parts[1].trim());
                        String pass = parts[2].trim();
                        Customer c = findCustomerById(cId);
                        if (c == null) {
                            System.out.println("No customer with ID number " + parts[1].trim() + " exists!");
                            continue;
                        }
                        if (c.getCart().isEmpty()) {
                            System.out.println("You should add some items to your cart before order request!");
                            continue;
                        }
                        if (!c.getPassword().equals(pass)) {
                            System.out.println("Order could not be placed. Invalid password.");
                            continue;
                        }

                        double totalCost = 0;
                        for (Item i : c.getCart().getItems()) {
                            double itemPrice = i.getCost();
                            String type = i.getClass().getSimpleName().toUpperCase();
                            Campaign camp = getActiveCampaign(type);
                            if (camp != null && camp.isActive(LocalDate.now())) {
                                itemPrice -= (itemPrice * camp.getDiscountRate() / 100.0);
                            }
                            totalCost += itemPrice;
                        }

                        if (c.getStatus().equals(Customer.SILVER)) {
                            totalCost -= (totalCost * Customer.SILVER_DISCOUNT);
                        } else if (c.getStatus().equals(Customer.GOLDEN)) {
                            totalCost -= (totalCost * Customer.GOLDEN_DISCOUNT);
                        }

                        if (c.getBalance() < totalCost) {
                            System.out.println("Order could not be placed. Insufficient funds.");
                        } else {
                            for (Item i : c.getCart().getItems()) {
                                i.decreaseStock(1);
                            }
                            c.setBalance(c.getBalance() - totalCost);
                            Order newOrder = new Order(cId, totalCost, c.getCart().getItems());
                            c.getOrderHistory().add(newOrder);
                            c.getCart().emptyCart();
                            System.out.println("Done! Your order will be delivered as soon as possible. Thank you!");

                            c.addToSpent(totalCost);
                            
                            if (c.getStatus().equals(Customer.CLASSIC)) {
                                double needed = Customer.SILVER_LIMIT - c.getTotalSpent();
                                if (needed > 0) {
                                    System.out.println("You need to spend " + needed +
                                                    " more TL to become a SILVER MEMBER.");
                                }
                            } else if (c.getStatus().equals(Customer.SILVER)) {
                                double needed = Customer.GOLDEN_LIMIT - c.getTotalSpent();
                                if (needed > 0) {
                                    System.out.println("You need to spend " + needed +
                                                    " more TL to become a GOLDEN MEMBER.");
                                }
                            }
                        }
                    }
                    else if (command.equals("DEPOSITMONEY")) {
                        if (parts.length < 3) continue;
                        Customer c = findCustomerById(Integer.parseInt(parts[1].trim()));
                        if (c != null) {
                            double amount = Double.parseDouble(parts[2].trim());
                            c.setBalance(c.getBalance() + amount);
                        } else {
                            System.out.println("No customer with ID number " + parts[1].trim() + " exists!");
                        }
                    }
                    else if (command.equals("SHOWCAMPAIGNS")) {
                        if (parts.length < 2) continue;
                        Customer c = findCustomerById(Integer.parseInt(parts[1].trim()));
                        if (c == null) {
                            System.out.println("No customer with ID number " + parts[1].trim() + " exists!");
                        } else {
                            if (campaigns.isEmpty()) {
                                System.out.println("No campaign has been created so far!");
                            } else {
                                System.out.println("Active campaigns:");
                                for (Campaign camp : campaigns) {
                                    System.out.println(camp.toString());
                                }
                            }
                        }
                    }

                    else if (command.equals("LISTITEM")) {
                        if (parts.length < 2) continue;
                        Person p = findPersonByName(parts[1].trim());
                        if (p == null || (!(p instanceof Admin) && !(p instanceof Technician))) {
                            System.out.println("No admin or technician person named " + parts[1].trim() + " exists!");
                        } else {
                            for (Item i : items) {
                                System.out.println(i.toString());
                                System.out.println("-----------------------");
                            }
                        }
                    }
                    else if (command.equals("SHOWITEMSLOWONSTOCK")) {
                        if (parts.length < 2) continue;
                        Person p = findPersonByName(parts[1].trim());
                        if (p == null || (!(p instanceof Admin) && !(p instanceof Technician))) {
                            System.out.println("No admin or technician person named " + parts[1].trim() + " exists!");
                            continue;
                        }

                        // Tip bazında stok topla (Book, Desktop, Laptop... tek satır olacak)
                        Map<String, Integer> stockByType = new LinkedHashMap<>();
                        for (Item i : items) {
                            String typeName = i.getClass().getSimpleName();
                            int current = stockByType.getOrDefault(typeName, 0);
                            stockByType.put(typeName, current + i.getStock());
                        }

                        int limit = (parts.length > 2) ? Integer.parseInt(parts[2].trim()) : Integer.MAX_VALUE;

                        for (Map.Entry<String, Integer> entry : stockByType.entrySet()) {
                            String typeName = entry.getKey();
                            int stock = entry.getValue();
                            if (parts.length > 2) {
                                if (stock < limit) {
                                    System.out.println(typeName + " : " + stock);
                                }
                            } else {
                                // limit yoksa hepsini göster
                                System.out.println(typeName + " : " + stock);
                            }
                        }
                    }
                    else if (command.equals("SHOWVIP")) {
                        if (parts.length < 2) continue;
                        Person p = findPersonByName(parts[1].trim());
                        if (p == null || (!(p instanceof Admin) && !(p instanceof Technician))) {
                            System.out.println("No admin or technician person named " + parts[1].trim() + " exists!");
                        } else {
                            for (Person u : users) {
                                if (u instanceof Customer) {
                                    Customer cust = (Customer) u;
                                    if (cust.getStatus().equals(Customer.GOLDEN)) {
                                        System.out.println(cust.toString());
                                    }
                                }
                            }
                        }
                    }
                    else if (command.equals("ADDADMIN")) {
                        if (parts.length < 7) continue;
                        Person admin = findPersonByName(parts[1].trim());
                        if (admin == null || !(admin instanceof Admin)) {
                            System.out.println("No admin person named " + parts[1].trim() + " exists!");
                            continue;
                        }
                        users.add(new Admin(
                                parts[2].trim(), parts[3].trim(), parts[4].trim(),
                                Double.parseDouble(parts[5].trim()), parts[6].trim()));
                    }
                    else if (command.equals("ADDTECH")) {
                        if (parts.length < 7) continue;
                        Person admin = findPersonByName(parts[1].trim());
                        if (admin == null || !(admin instanceof Admin)) {
                            System.out.println("No admin person named " + parts[1].trim() + " exists!");
                            continue;
                        }
                        boolean isSenior = Integer.parseInt(parts[6].trim()) == 1;
                        users.add(new Technician(
                                parts[2].trim(), parts[3].trim(), parts[4].trim(),
                                Double.parseDouble(parts[5].trim()), isSenior));
                    }
                    else if (command.equals("CHPASS")) {
                        if (parts.length < 4) continue;
                        Customer c = findCustomerById(Integer.parseInt(parts[1].trim()));
                        if (c == null) {
                            System.out.println("No customer with ID number " + parts[1].trim() + " exists!");
                        } else if (!c.getPassword().equals(parts[2].trim())) {
                            System.out.println("The given password does not match the current password. Please try again.");
                        } else {
                            c.setPassword(parts[3].trim());
                            System.out.println("The password has been successfully changed.");
                        }
                    }
                    else if (command.equals("SHOWORDERS")) {
                        if (parts.length < 2) continue;
                        Person p = findPersonByName(parts[1].trim());
                        if (p == null || !(p instanceof Technician)) {
                            System.out.println("No technician person named " + parts[1].trim() + " exists!");
                        } else {
                            Technician t = (Technician) p;
                            if (!t.isSenior()) {
                                System.out.println(t.getName() + " is not authorized to display orders!");
                            } else {
                                System.out.println("Order History:");
                                for (Person u : users) {
                                    if (u instanceof Customer) {
                                        for (Order o : ((Customer) u).getOrderHistory()) {
                                            System.out.println(o.toString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else if (command.equals("ADDITEM")) {
                        if (parts.length < 3) continue;
                        Person p = findPersonByName(parts[1].trim());
                        if (p == null || !(p instanceof Technician)) {
                            System.out.println("No technician person named " + parts[1].trim() + " exists!");
                            continue;
                        }
                        // "LAPTOP:1250:..." → direk token dizisi olarak kullan
                        String spec = parts[2].trim();
                        String[] tokens = spec.split(":");
                        for (int i = 0; i < tokens.length; i++) {
                            tokens[i] = tokens[i].trim();
                        }
                        Item created = createItemFromTokens(tokens, 0, 1);
                        if (created == null) {
                            System.out.println("No item type " + tokens[0] + " found");
                        } else {
                            items.add(created);
                        }
                    }
                    else if (command.equals("DISPITEMSOF")) {
                        if (parts.length < 3) continue;
                        Person p = findPersonByName(parts[1].trim());
                        if (p == null || !(p instanceof Technician)) {
                            System.out.println("No technician person named " + parts[1].trim() + " exists!");
                        } else {
                            String[] types = parts[2].split(":");
                            for (String t : types) {
                                String targetType = t.trim().toUpperCase();
                                for (Item i : items) {
                                    if (i.getClass().getSimpleName().toUpperCase().equals(targetType)) {
                                        System.out.println(i.toString());
                                        System.out.println("-----------------------");
                                    }
                                }
                            }
                        }
                    }
                    else {
                        // geçersiz komut sessizce yok sayılabilir
                    }
                } catch (NumberFormatException nfe) {
                    // satırı atla
                } catch (Exception ex) {
                    // satırı atla
                }
            }
        } catch (IOException e) {
        }
    }

    private static Person findPersonByName(String name) {
        if (name == null) return null;
        String target = name.trim();
        for (Person p : users) {
            if (p.getName() != null && p.getName().trim().equals(target)) {
                return p;
            }
        }
        return null;
    }

    private static Customer findCustomerById(int id) {
        for (Person p : users) {
            if (p instanceof Customer) {
                Customer c = (Customer) p;
                if (c.getCustomerID() == id) {
                    return c;
                }
            }
        }
        return null;
    }

    private static Item findItemById(int id) {
        for (Item i : items) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    private static Campaign getActiveCampaign(String itemType) {
        if (itemType == null) return null;
        String normalized = itemType.trim().toUpperCase();
        for (Campaign c : campaigns) {
            if (c.getItemType() != null &&
                c.getItemType().equals(normalized)) {
                return c;
            }
        }
        return null;
    }

}
