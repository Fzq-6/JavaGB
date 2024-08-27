import java.util.*;

class Laptop {
    private String brand;
    private String model;
    private int ram;
    private int storage;
    private String operatingSystem;
    private String color;
    private double price;

    public Laptop(String brand, String model, int ram, int storage, String operatingSystem, String color, double price) {
        this.brand = brand;
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
        this.color = color;
        this.price = price;
    }


public String getBrand() {
    return brand;
}

public String getModel() {
    return model;
}

public int getRam() {
    return ram;
}

public int getStorage() {
    return storage;
}

public String getOperationSystem() {
    return operatingSystem;
}

public String getColor() {
    return color;
}

public double getPrice() {
    return price;
}

@Override
public String toString() {
    return "Laptop{" +
            "brand='" + brand + '\'' +
            ", model='" + model + '\'' +
            ", ram=" + ram +
            ", storage=" + storage +
            ", operatingSystem='" + operatingSystem + '\'' +
            ", color='" + color + '\'' +
            ", price=" + price +
            '}';
    }
}

class LaptopStore {
    private Set<Laptop> laptops = new HashSet<>();

    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }

    public Set<Laptop> filterLaptops(Map<String, Object> criteria) {
        Set<Laptop> result = new HashSet<>(laptops);

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            result.removeIf(laptop -> !matchesCriteria(laptop, key, value));
        }

        return result;    
    }

    private boolean matchesCriteria(Laptop laptop, String key, Object value) {
    switch (key) {
        case "ram":
            return laptop.getRam() >= (int) value;
        case "storage":
            return laptop.getStorage() >= (int) value;
        case "operatingSystem":
            return laptop.getOperationSystem().equalsIgnoreCase((String) value);
        case "color":
            return laptop.getColor().equalsIgnoreCase((String) value);
        case "price":
            return laptop.getPrice() <= (double) value;
        default:
            return false;
            }   
        }
    }

    public class lesson6 {
        public static void main(String[] args) {
            LaptopStore store = new LaptopStore();

            // Покупаем ноутбуки в магазин и перепрадаём задороже ;)
            store.addLaptop(new Laptop("Dell", "XPS 13", 16, 512, "Windows", "Silver", 1200.00));
            store.addLaptop(new Laptop("Apple", "MacBook Pro", 8, 256, "macOs", "Space Gray", 1500.00));
            store.addLaptop(new Laptop("HP", "Pavilion", 16, 1024, "Windows", "Black", 900.00));

            Scanner scanner = new Scanner(System.in);
            Map<String, Object> criteria = new HashMap<>();

            System.out.println("Введите цифру, соответствующую необходимому критерию:\n1 - ОЗУ\n2 - Объем ЖД\n3 - Операционная система\n4 - Цвет\n5 - Максимальная цена");
            int criterion = scanner.nextInt();
            scanner.nextLine();

            switch (criterion) {
                case 1:
                    System.out.println("Введите минимальный объем ОЗУ (ГБ):");
                    int ram = scanner.nextInt();
                    criteria.put("ram", ram);
                    break;
                case 2:
                    System.out.println("Введите минимальный объем накопителя (ГБ):");
                    int storage = scanner.nextInt();
                    criteria.put("storage", storage);
                    break;
                case 3:
                    System.out.println("Введите операционную систему:");
                    String os = scanner.nextLine();
                    criteria.put("operatingSystem", os);
                    break;
                case 4:
                    System.out.println("Введите цвет:");
                    String color = scanner.nextLine();
                    criteria.put("color", color);
                case 5:
                    System.out.println("Введите максимальную цену:");
                    double price = scanner.nextDouble();
                    criteria.put("price", price);
                    break;
                default:
                    System.out.println("Некорректный выбор.");
                    return;
            }

            // Фильтруем ноутбуки и выводим результат
            Set<Laptop> filteredLaptops = store.filterLaptops(criteria);
            if (filteredLaptops.isEmpty()) {
                System.out.println("Ноутбуков, соответствующих заданным критериям, не найдено.");
            } else {
                System.out.println("Найденные ноутбуки:");
                for (Laptop laptop : filteredLaptops) {
                    System.out.println(laptop);
                }
            }  
        }   
    }      
    