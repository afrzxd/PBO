public class Order {
    String name, type, sugar;
    int qyt;

    public Order(String name, String type, String sugar, int qyt) {
        this.name = name;
        this.type = type;
        this.sugar = sugar;
        this.qyt = qyt;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSugar() {
        return sugar;
    }

    public int getQyt() {
        return qyt;
    }
}
