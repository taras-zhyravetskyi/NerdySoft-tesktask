package org.example;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class User {
    private String name;
    private int age;

    private User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static User createUser(String name, int age) {
        return new User(name, age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

abstract class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

class RealProduct extends Product {
    private double size;
    private double weight;

    public RealProduct(String name, double price, double size, double weight) {
        super(name, price);
        this.size = size;
        this.weight = weight;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RealProduct that = (RealProduct) o;
        return Double.compare(that.size, size) == 0 && Double.compare(that.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), size, weight);
    }

    @Override
    public String toString() {
        return "RealProduct{" +
                "size=" + size +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

class VirtualProduct extends Product {
    private String code;
    private LocalDate expirationDate;

    public VirtualProduct(String name, double price, String code, LocalDate expirationDate) {
        super(name, price);
        this.code = code;
        this.expirationDate = expirationDate;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VirtualProduct that = (VirtualProduct) o;
        return Objects.equals(code, that.code) && Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code, expirationDate);
    }

    @Override
    public String toString() {
        return "VirtualProduct{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", code='" + code + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}

class ProductFactory {
    public static Product createRealProduct(String name, double price, double size, double weight) {
        return new RealProduct(name, price, size, weight);
    }

    public static Product createVirtualProduct(String name, double price, String code, LocalDate expirationDate) {
        return new VirtualProduct(name, price, code, expirationDate);
    }
}

class Order {
    private User user;
    private List<Product> products;

    private Order(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public static Order createOrder(User user, List<Product> products) {
        return new Order(user, products);
    }

    public User getUser() {
        return user;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(user, order.user) && Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, products);
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", products=" + products +
                '}';
    }
}

class VirtualProductCodeManager {
    private static VirtualProductCodeManager instance = null;
    private Set<String> usedCodes;

    private VirtualProductCodeManager() {
        this.usedCodes = new HashSet<>();
    }

    public static VirtualProductCodeManager getInstance() {
        if (instance == null) {
            instance = new VirtualProductCodeManager();
        }
        return instance;
    }

    public void useCode(String code) {
        usedCodes.add(code);
    }

    public boolean isCodeUsed(String code) {
        return usedCodes.contains(code);
    }
}

public class Main {
    public static void main(String[] args) {
        //TODO Create User class with method createUser
        // User class fields: name, age;
        // Notice that we can only create user with createUser method without using constructor or builder
        User user1 = User.createUser("Alice", 32);
        User user2 = User.createUser("Bob", 19);
        User user3 = User.createUser("Charlie", 20);
        User user4 = User.createUser("John", 27);

        //TODO Create factory that can create a product for a specific type: Real or Virtual
        // Product class fields: name, price
        // Product Real class additional fields: size, weight
        // Product Virtual class additional fields: code, expiration date

        Product realProduct1 = ProductFactory.createRealProduct("Product A", 20.50, 10, 25);
        Product realProduct2 = ProductFactory.createRealProduct("Product B", 50, 6, 17);

        Product virtualProduct1 = ProductFactory.createVirtualProduct("Product C", 100, "xxx", LocalDate.of(2023, 5, 12));
        Product virtualProduct2 = ProductFactory.createVirtualProduct("Product D", 81.25, "yyy",  LocalDate.of(2024, 6, 20));


        //TODO Create Order class with method createOrder
        // Order class fields: User, List<Price>
        // Notice that we can only create order with createOrder method without using constructor or builder
        List<Order> orders = new ArrayList<>() {{
            add(Order.createOrder(user1, List.of(realProduct1, virtualProduct1, virtualProduct2)));
            add(Order.createOrder(user2, List.of(realProduct1, realProduct2)));
            add(Order.createOrder(user3, List.of(realProduct1, virtualProduct2)));
            add(Order.createOrder(user4, List.of(virtualProduct1, virtualProduct2, realProduct1, realProduct2)));
        }};

        //TODO 1). Create singleton class which will check the code is used already or not
        // Singleton class should have the possibility to mark code as used and check if code used
        // Example:
        // singletonClass.useCode("xxx")
        // boolean isCodeUsed = virtualProductCodeManager.isCodeUsed("xxx") --> true;
        // boolean isCodeUsed = virtualProductCodeManager.isCodeUsed("yyy") --> false;

        System.out.println("1. Create singleton class VirtualProductCodeManager \n");
        var isUsed = false;
        VirtualProductCodeManager codeManager = VirtualProductCodeManager.getInstance();
        codeManager.useCode("xxx");
        isUsed = codeManager.isCodeUsed("xxx");
        System.out.println("Is code xxx used: " + isUsed + "\n");

        //TODO 2). Create a functionality to get the most expensive ordered product
        Product mostExpensive = getMostExpensiveProduct(orders);
        System.out.println("2. Most expensive product: " + mostExpensive + "\n");

        //TODO 3). Create a functionality to get the most popular product(product bought by most users) among users
        Product mostPopular = getMostPopularProduct(orders);
        System.out.println("3. Most popular product: " + mostPopular + "\n");

        //TODO 4). Create a functionality to get average age of users who bought realProduct2
        double averageAge = calculateAverageAge(realProduct2, orders);
        System.out.println("4. Average age is: " + averageAge + "\n");

        //TODO 5). Create a functionality to return map with products as keys and a list of users
        // who ordered each product as values
        Map<Product, List<User>> productUserMap = getProductUserMap(orders);
        System.out.println("5. Map with products as keys and list of users as value \n");
        productUserMap.forEach((key, value) -> System.out.println("key: " + key + " " + "value: " +  value + "\n"));

        //TODO 6). Create a functionality to sort/group entities:
        // a) Sort Products by price
        // b) Sort Orders by user age in descending order
        List<Product> productsByPrice = sortProductsByPrice(List.of(realProduct1, realProduct2, virtualProduct1, virtualProduct2));
        System.out.println("6. a) List of products sorted by price: " + productsByPrice + "\n");
        List<Order> ordersByUserAgeDesc = sortOrdersByUserAgeDesc(orders);
        System.out.println("6. b) List of orders sorted by user agge in descending order: " + ordersByUserAgeDesc + "\n");

        //TODO 7). Calculate the total weight of each order
        Map<Order, Integer> result = calculateWeightOfEachOrder(orders);
        System.out.println("7. Calculate the total weight of each order \n");
        result.forEach((key, value) -> System.out.println("order: " + key + " " + "total weight: " +  value + "\n"));
    }

    private static Product getMostExpensiveProduct(List<Order> orders) {
        return orders.stream()
                .flatMap(o -> o.getProducts().stream())
                .max(Comparator.comparing(Product::getPrice))
                .orElse(null);
    }

    private static Product getMostPopularProduct(List<Order> orders) {
         return orders.stream()
                .flatMap(o -> o.getProducts().stream())
                 .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                 .entrySet().stream()
                 .max(Map.Entry.comparingByValue())
                 .map(Map.Entry::getKey)
                 .orElseThrow(() -> new RuntimeException("Can't get the most popular product from orders"));
    }

    private static double calculateAverageAge(Product product, List<Order> orders) {
        return orders.stream()
                .filter(o -> o.getProducts().contains(product))
                .mapToDouble(o -> o.getUser().getAge())
                .average()
                .orElseThrow(() -> new RuntimeException("Can't calculate average age"));
    }

    private static Map<Product, List<User>> getProductUserMap(List<Order> orders) {;
        Map<Product, List<User>> productUserMap = new HashMap<>();
        for (Order order : orders) {
            User user = order.getUser();
            for (Product product : order.getProducts()) {
                productUserMap.putIfAbsent(product, new ArrayList<>());
                productUserMap.get(product).add(user);
            }
        }
        return productUserMap;
    }

    private static List<Product> sortProductsByPrice(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(p -> p.price))
                .toList();
    }

    private static List<Order> sortOrdersByUserAgeDesc(List<Order> orders) {
        return orders.stream()
                .sorted(Comparator.comparing(o -> o.getUser().getAge(), Comparator.reverseOrder()))
                .toList();
    }

    private static Map<Order, Integer> calculateWeightOfEachOrder(List<Order> orders) {
         return orders.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        o -> o.getProducts().stream()
                                .filter(p -> p instanceof RealProduct)
                                .mapToDouble(p -> ((RealProduct) p).getWeight())
                                .mapToInt(w -> Math.toIntExact(Math.round(w)))
                                .sum()));
    }
}
