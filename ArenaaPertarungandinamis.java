import java.io. *;
import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class ArenaaPertarungandinamis {
    public static void main(String[] args) {
         Scanner input = new Scanner(System.in);
         ArrayList<Musuhh> gelombangMonster = new ArrayList<>();
         gelombangMonster.add(new Slimee());
         gelombangMonster.add(new Nagaa());
         gelombangMonster.add(new Zombie());

         System.out.println("===========");
         System.out.println(" ARENA RPG: GELOMBANG MONSTER ");
         System.out.println("===========");
         System.out.println("AWAS ! Sekelompok monster menghadang Anda !");

         boolean isBermain = true;
         while (isBermain && !gelombangMonster.isEmpty()) {
             System.out.println("\n--- STATUS MONSTER ---");
             for (int i = 0; i < gelombangMonster.size(); i++) {
                 Musuhh monster = gelombangMonster.get(i);
                 System.out.println((i+1) + "." + monster.namaMusuhh + " (HP:" + monster.health_Point + ")");
             }
             System.out.println("------------------------");
             System.out.println("8. [SAVE GAME] Simpan progres permainan");
             System.out.println("9. [LOAD GAME] Muat progres permainan sebelumnya");
             System.out.println( "10. kabur dari pertarungan");
                System.out.print("\nPilih target monster yang ingin diserang (1/" + gelombangMonster.size() + ") atau 10 untuk kabur: ");


                     System.out.println("0. kabur dari pertarungan");
             System.out.print("\nPilih target monster yang ingin diserang: ");
             try {
                 int pilihanTarget = input.nextInt();
                 if (pilihanTarget == 0) {
                     System.out.println("Anda lari terbirit-birit dari arena...");
                     isBermain = false;
                     continue;
                 }
                 else if (pilihanTarget ==8) {
                        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savegame_rpg.dat"))){
                            oos.writeObject(gelombangMonster);
                            System.out.println(
                                ">>> Progres permainan berhasil disimpan!"
                            );
                        } catch (IOException e) {
                            System.out.println(
                                "Gagal menyimpan progres permainan: " + e.getMessage()
                            );
                        }
                        continue;
                 }
                else if (pilihanTarget ==9) {
                        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savegame_rpg.dat"))){
                             gelombangMonster = (ArrayList<Musuhh>) ois.readObject();
                             System.out.println(
                                ">>> Progres permainan berhasil dimuat!"
                                );
                        } catch (IOException | ClassNotFoundException e) {
                            System.out.println(
                                "Gagal memuat progres permainan.Silahkan save game terlebih dahulu: " + e.getMessage()
                                );
                        
                              
                        }
                            continue;
                    }

                 if (pilihanTarget < 1 || pilihanTarget > gelombangMonster.size()) {
                     System.out.println("Pilihan tidak valid! ");
                     continue;
                 }
                 int indeksMonster = pilihanTarget - 1;
                 Musuhh monster = gelombangMonster.get(indeksMonster);
                 if (monster.health_Point <= 0) {
                     throw new TargetMatiException(
                         "Tindakan ilegal! Anda tidak bisa menyerang monster yang sudah mati"
                     );
                 }

                 System.out.print("Masukkan kekuatan serangan Anda (10-100): ");
                 int powerr = input.nextInt();
                 if (powerr < 10 || powerr > 100) {
                     throw new SeranganTidakValidException(
                         "Kekuatan serangan tidak valid! Harus antara 10-100."
                     );
                 }

                 System.out.println("\n>>> HASIL SERANGAN ANDA <<<");
                 monster.terimaDamage(powerr);
                 if (monster.health_Point <= 0) {
                        System.out.println(monster.namaMusuhh + " telah dikalahkan !");
                        if (monster instanceof bisaloot) {
                            bisaloot loot = (bisaloot) monster;
                            loot.jatuhkanItem();
                        }
                        gelombangMonster.remove(indeksMonster);
                    }
             } catch (Exception e) {
                 System.out.println("Terjadi kesalahan sistem: " + e.getMessage());
                 input.nextLine();
                 continue;
             }

             if (gelombangMonster.isEmpty()) {
                System.out.println("\nSelamat pertandingan selesai! Anda berhasil mengalahkan semua monster!");
                isBermain = false;
             }

             System.out.println("\n<<< GILIRAN MONSTER MEMBALAS >>>");
             for (int i = 0; i < gelombangMonster.size(); i++) {
                 Musuhh monsterAktif = gelombangMonster.get(i);
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

             boolean semuaMati = true;
             for (int i = 0; i < gelombangMonster.size(); i++) {
                 if (gelombangMonster.get(i).health_Point > 0) {
                     semuaMati = false;
                     break;
                 }
             }

             if (semuaMati) {
                 System.out.println
                 ("\nSelamat pertandingan selesai! Anda berhasil mengalahkan semua monster!");
                 isBermain = false;
             }

        input.close();
        }
        
    }