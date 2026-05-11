import java.util.Scanner;

public class ArenaaPertarungan {
    public static void main(String[] args) {
         Scanner input = new Scanner(System.in);
         Musuhh[] gelombangMonster = new Musuhh[3];
         gelombangMonster[0] = new Slimee();
         gelombangMonster[1] = new Nagaa();
         gelombangMonster[2] = new Zombie();
         

         System.out.println("===========");
         System.out.println(" ARENA RPG: GELOMBANG MONSTER ");
         System.out.println("===========");
         System.out.println("AWAS ! Sekelompok monster menghadang Anda !");

         boolean isBermain = true;
     while (isBermain) {
         System.out.println("\n--- STATUS MONSTER ---");
         for (int i = 0; i < gelombangMonster.length; i++) {
              System.out.println((i+1)+"."
                 +gelombangMonster[i].namaMusuhh + " (HP: "
                 +gelombangMonster[i].health_Point + ")");
         }
         System.out.println("4. kabur dari pertarungan");
         System.out.print("\nPilih target monster yang ingin diserang (1/2/3) atau 4 untuk kabur: ");
         int pilihanTarget = input.nextInt();
         if (pilihanTarget == 4) {
            System.out.println("Anda lari terbirit-birit dari arena...");
            isBermain = false;
            continue;
         }
         if (pilihanTarget < 1 || pilihanTarget >3) {
            System.out.println("Pilihan tidak valid! Anda membuang giliran. ");
         } else {
            System.out.println("Masukkan kekuatan serangan Anda (10-100): ");
            int powerr = input.nextInt();
            System.out.println("\n>>> HASIL SERANGAN ANDA <<<");
            int indeksMonster = pilihanTarget - 1;
            gelombangMonster[indeksMonster].terimaDamage(powerr);
               if (gelombangMonster[indeksMonster].health_Point <= 0) {
                  System.out.println(gelombangMonster[indeksMonster].namaMusuhh + " telah dikalakan !");
                  if (gelombangMonster[indeksMonster] instanceof bisaloot) {
                     bisaloot monsterloot = (bisaloot) gelombangMonster[indeksMonster];
                     monsterloot.jatuhkanItem();
                  }
               }
         }
            System.out.println("\n<<< GILIRAN MONSTER MEMBALAS >>>");
            for (int i = 0; i < gelombangMonster.length; i++) {
                if (gelombangMonster[i].health_Point > 0) {
                    Musuhh monsterAktif = gelombangMonster [i];
                    monsterAktif.suarakhas();
                      if (monsterAktif instanceof bisaterbang) {
                        System.out.println("[PERINGATAN! Serangan udara terdeteksi]");
                        bisaterbang monsterTerbang = (bisaterbang) monsterAktif;
                        monsterTerbang.lepaslandas();
                        monsterTerbang.seranganudara();
                      } else {
                        monsterAktif.serangPemain();
                      }
                     }
                    
                    }
                }
                boolean semuaMati = true;
                for (int i = 0; i < gelombangMonster.length; i++) {
                    if (gelombangMonster[i].health_Point > 0) {
                        semuaMati = false;
                        break;
                    }
                    if (semuaMati) {
                        System.out.println("\nSelamat pertandingan selesai! Anda berhasil mengalahkan semua monster!");
                        isBermain = false;
                    }
                  }
         input.close();
            System.out.println("Permainan Berakhir.");
               }
            }



          