package universite_paris8.iut.nchaieb.sae_jeux.modele;

import universite_paris8.iut.nchaieb.sae_jeux.Main;
import java.io.InputStream;
import java.util.Scanner;

public class LecteurVague {
    private Vague[] vagues;
    private int nbVague;

    public LecteurVague() {
        lire();
    }

    public int getNbVague() {
        return nbVague;
    }

    public Vague[] getVagues() {
        return vagues;
    }

    public void lire() {
        try {
            InputStream is = Main.class.getResourceAsStream("vagues.txt");
            if (is == null) {
                System.out.println("Fichier vagues.txt introuvable !");
                return;
            }
            Scanner sc = new Scanner(is).useDelimiter("\n");

            nbVague = sc.nextInt();
            vagues = new Vague[nbVague];
            sc.next(); // Passe le premier #

            for (int indVague = 0; indVague < nbVague; indVague++) {
                vagues[indVague] = new Vague();
                String ligne = sc.next().trim();

                while (!ligne.equals("#") && sc.hasNext()) {
                    if (!ligne.isEmpty()) {
                        String[] parts = ligne.split(" ");
                        int quantite = Integer.parseInt(parts[0]);
                        int codeMonstre = Integer.parseInt(parts[1]);
                        int delaiTick = (int) (Double.parseDouble(parts[2]) * 60);
                        for (int i = 0; i < quantite; i++) {
                            vagues[indVague].getListeApparition().ajouter(codeMonstre, delaiTick);
                        }
                    }
                    ligne = sc.next().trim();
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}