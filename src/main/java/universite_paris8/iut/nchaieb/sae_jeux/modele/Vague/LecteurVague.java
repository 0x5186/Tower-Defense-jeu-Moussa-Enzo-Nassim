package universite_paris8.iut.nchaieb.sae_jeux.modele.Vague;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LecteurVague {
    private Vague[] vagues;
    private int nbVague;

    public LecteurVague() {
        lire();
    }

    public int getNbVague() { return nbVague; }
    public Vague[] getVagues() { return vagues; }

    public void lire() {
        try {
            InputStream is = LecteurVague.class.getResourceAsStream("/universite_paris8/iut/nchaieb/sae_jeux/vagues.txt");
            if (is == null) {
                System.err.println(" vagues.txt introuvable !");
                return;
            }

            Scanner sc = new Scanner(is);
            nbVague = 0;
            List<Vague> vaguesLues = new ArrayList<>();
            Vague vagueEnCours = null;

            while (sc.hasNextLine()) {
                String ligne = sc.nextLine().trim();

                // Ligne vide ou commentaire = séparateur de vague
                if (ligne.isEmpty() || ligne.startsWith("#")) {
                    if (vagueEnCours != null) {
                        vaguesLues.add(vagueEnCours);
                        vagueEnCours = null;
                    }
                    continue;
                }

                if (nbVague == 0 && vagueEnCours == null && vaguesLues.isEmpty()) {
                    nbVague = Integer.parseInt(ligne);
                    continue;
                }

                // Ligne de données : quantite code delai
                if (vagueEnCours == null) vagueEnCours = new Vague();

                String[] parts = ligne.split("\\s+");
                int quantite = Integer.parseInt(parts[0]);
                int code     = Integer.parseInt(parts[1]);
                int delai    = (int)(Double.parseDouble(parts[2]) * 60);

                for (int j = 0; j < quantite; j++) {
                    vagueEnCours.getListeApparition().ajouter(code, delai);
                }
            }

            // Dernière vague sans # final
            if (vagueEnCours != null) vaguesLues.add(vagueEnCours);

            vagues = vaguesLues.toArray(new Vague[0]);
            nbVague = vagues.length;
            sc.close();

            System.out.println( nbVague + " vagues chargées");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}