import java.util.Scanner;

public class Main {
    static Menu[] menus = {
        new Menu("Nasi Padang", 20000, "makanan"),
        new Menu("Ayam Goreng", 15000, "makanan"),
        new Menu("Es Teh", 5000, "minuman"),
        new Menu("Kopi", 10000, "minuman")
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selamat datang di Resto Kami!");
        tampilkanMenu();

        int[] orderCounts = new int[menus.length];
        for (int i = 0; i < 4; i++) {
            System.out.print("Masukkan nama menu dan jumlah pesanan (format: Nama = jumlah): ");
            String input = scanner.nextLine();
            String[] parts = input.split(" = ");
            for (int j = 0; j < menus.length; j++) {
                if (menus[j].getNama().equalsIgnoreCase(parts[0])) {
                    orderCounts[j] = Integer.parseInt(parts[1]);
                    break;
                }
            }
        }

        int total = hitungTotal(orderCounts);
        cetakStruk(orderCounts, total);
        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("Daftar Menu:");
        for (Menu menu : menus) {
            System.out.println(menu.getNama() + " - Rp" + menu.getHarga() + " (" + menu.getKategori() + ")");
        }
    }

    private static int hitungTotal(int[] orderCounts) {
        int subtotal = 0;
        for (int i = 0; i < menus.length; i++) {
            subtotal += menus[i].getHarga() * orderCounts[i];
        }
        int tax = subtotal * 10 / 100;
        int serviceCharge = 20000;
        int discount = subtotal > 100000 ? subtotal * 10 / 100 : 0;
        return subtotal + tax + serviceCharge - discount;
    }

    private static void cetakStruk(int[] orderCounts, int total) {
        System.out.println("\nStruk Pembayaran:");
        for (int i = 0; i < menus.length; i++) {
            if (orderCounts[i] > 0) {
                int totalPerItem = menus[i].getHarga() * orderCounts[i];
                System.out.println(menus[i].getNama() + " x " + orderCounts[i] + " = Rp" + totalPerItem);
            }
        }
        System.out.println("Total: Rp" + total);
    }
}
