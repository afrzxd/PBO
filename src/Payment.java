import java.util.ArrayList;
import java.util.Scanner;

public class Payment {

    static ArrayList<Order> orders = new ArrayList();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int pilih;

        do {
            System.out.println("------------------------");
            System.out.println("        BUCKS COFFEE    ");
            System.out.println("------------------------");
            System.out.println("1. Buy Coffe");
            System.out.println("2. Check Out");
            System.out.println("3. Exit");
            System.out.println("------------------------");
            System.out.print("Pilihanmu : ");
            pilih = sc.nextInt();
            sc.nextLine(); // agar tak mendapatkan error setelah input integer

            if (pilih == 1) {
                orders = buyCoffee(orders);
            } else if (pilih == 2) {
                orders = checkOut(orders);
            }
        } while (pilih != 3);

        orders.clear();
    }

    private static ArrayList<Order> buyCoffee(ArrayList<Order> orders) {
        String nama, tipe, gula = null;
        int qyt;

        System.out.print("Input nama Kopi: ");
        nama = sc.nextLine();

        boolean ok;
        do {
            System.out.print("Input nama tipe [ Cappucino, Latte, Americano]:");
            tipe = sc.nextLine();
            ok = cekKopi(tipe);
        } while (ok == false);

        do {
            System.out.print("Tambah Gula [Y / T]:");
            gula = sc.nextLine();
            ok = cekGula(gula);
        } while (ok == false);

        System.out.print("Quantity : ");
        qyt = sc.nextInt();

        // masukkan ke dalam arraylist
        orders.add(new Order(nama, tipe, gula, qyt));

        return orders;
    }

    private static boolean cekKopi(String tipe) {
        boolean ok = false;
        if (tipe.equals("Cappucino")
                || tipe.equals("Latte")
                || tipe.equals("Americano")) {
            ok = true;
        }

        return ok;
    }

    private static boolean cekGula(String gula) {
        boolean ok = false;
        if (gula.equals("Y")
                || gula.equals("y")
                || gula.equals("T")
                || gula.equals("t")) {
            ok = true;
        }

        return ok;
    }

    private static ArrayList<Order> checkOut(ArrayList<Order> orders) {
        int harga, jumlah, total = 0, bayar;
        System.out.println("Jumlah order : " + String.valueOf(orders.size()));
        System.out.println("-----------------------------------------");
        System.out.printf("! %-5s| %-15s| %-13s|%-13s|%-13s|%-13s|%-13s|",
                "No.", "Name", "Type", "Extra", "Qyt", "Price", "Total");
        System.out.println();
        System.out.println("-----------------------------------------");

        int num = 1;
        for (int i = 0; i < orders.size(); i++) {
            harga = (orders.get(i).getQyt() * orders.get(i).getName().length() * 100);

            if (orders.get(i).getSugar().equals("Y")
                    || orders.get(i).getSugar().equals("y")) {
                jumlah = orders.get(i).getQyt() * harga;
            } else {
                jumlah = (int) ((int) (orders.get(i).getQyt() * harga) + (orders.get(i).getQyt() * 0.03));
            }

            System.out.printf("! %-5s| %-15s| %-13s|%-13s|%-13s|%-13s|%-13s|",
                    num++,
                    orders.get(i).getName(),
                    orders.get(i).getType(),
                    orders.get(i).getSugar(),
                    orders.get(i).getQyt(),
                    harga, jumlah);

            System.out.println();

            total += jumlah;

        }
        System.out.println("-----------------------------------------");
        System.out.println("Total: " + total);

        boolean ok;
        do {
            System.out.print("Bayar : ");
            bayar = sc.nextInt();
            ok = cekBayar(total, bayar);
        } while (ok == false);

        orders.clear();
        System.out.println("Kembalian: " + Math.abs(total - bayar));
        System.out.println("Successfully add new order!");
        System.out.println("Press enter to continue...");

        sc.nextLine();
        sc.nextLine();

        return orders;
    }

    private static boolean cekBayar(int total, int bayar) {
        boolean ok = false;
        if (bayar >= total) {
            ok = true;
        }

        return ok;
    }
}
