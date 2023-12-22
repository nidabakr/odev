import java.util.Random;
import java.util.Scanner;

public class Main {
    private int satirSayisi;
    private int sutunSayisi;
    private int[][] mayinTarlasi;
    private boolean[][] acilanNoktalar;

    public Main(int satirSayisi, int sutunSayisi) {
        this.satirSayisi = satirSayisi;
        this.sutunSayisi = sutunSayisi;
        this.mayinTarlasi = new int[satirSayisi][sutunSayisi];
        this.acilanNoktalar = new boolean[satirSayisi][sutunSayisi];
        mayinlariYerlestir();
    }

    private void mayinlariYerlestir() {
        Random rand = new Random();
        int mayinSayisi = satirSayisi * sutunSayisi / 4;

        for (int i = 0; i < mayinSayisi; i++) {
            int row, col;
            do {
                row = rand.nextInt(satirSayisi);
                col = rand.nextInt(sutunSayisi);
            } while (mayinTarlasi[row][col] == -1);

            mayinTarlasi[row][col] = -1;
        }
    }

    private void oyunuBaslat() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Mayınların Konumu");
        mayinlariGoster();
        System.out.println("Mayın Tarlası Oyuna Hoşgeldiniz !");

        while (true) {
            mayinlariGoster();
            System.out.print("Satır Giriniz : ");
            int satir = scanner.nextInt();

            System.out.print("Sütun Giriniz : ");
            int sutun = scanner.nextInt();

            if (!gecerliNokta(satir, sutun)) {
                System.out.println("Geçersiz bir nokta girdiniz. Lütfen tekrar deneyin.");
                continue;
            }

            if (mayinTarlasi[satir][sutun] == -1) {
                oyunuKaybet();
                break;
            }

            acilanNoktalar[satir][sutun] = true;

            if (oyunuKazandiMi()) {
                oyunuKazan();
                break;
            }
        }
        scanner.close();
    }

    private boolean gecerliNokta(int satir, int sutun) {
        return satir >= 0 && satir < satirSayisi && sutun >= 0 && sutun < sutunSayisi && !acilanNoktalar[satir][sutun];
    }

    private void oyunuKaybet() {
        System.out.println("Game Over!!");
        mayinlariGoster();
    }

    private boolean oyunuKazandiMi() {
        for (int i = 0; i < satirSayisi; i++) {
            for (int j = 0; j < sutunSayisi; j++) {
                if (!acilanNoktalar[i][j] && mayinTarlasi[i][j] != -1) {
                    return false;
                }
            }
        }
        return true;
    }

    private void oyunuKazan() {
        System.out.println("Oyunu Kazandınız !");
        mayinlariGoster();
    }

    private void mayinlariGoster() {
        for (int i = 0; i < satirSayisi; i++) {
            for (int j = 0; j < sutunSayisi; j++) {
                if (acilanNoktalar[i][j]) {
                    System.out.print(mayinTarlasi[i][j] + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println("===========================");
    }

    public static void Main(String[] args) {
        Main oyun = new Main(3, 3);
        oyun.oyunuBaslat();
    }
}
