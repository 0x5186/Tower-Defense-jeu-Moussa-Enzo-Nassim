module universite_paris8.iut.nchaieb.sae_jeux {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires java.naming;


    opens universite_paris8.iut.nchaieb.sae_jeux to javafx.fxml;
    exports universite_paris8.iut.nchaieb.sae_jeux;
    exports universite_paris8.iut.nchaieb.sae_jeux.modele;
    opens universite_paris8.iut.nchaieb.sae_jeux.modele to javafx.fxml;
    exports universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;
    opens universite_paris8.iut.nchaieb.sae_jeux.modele.monstres to javafx.fxml;
    exports universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;
    opens universite_paris8.iut.nchaieb.sae_jeux.modele.Tours to javafx.fxml;
    exports universite_paris8.iut.nchaieb.sae_jeux.modele.AEtoile;
    opens universite_paris8.iut.nchaieb.sae_jeux.modele.AEtoile to javafx.fxml;
    exports universite_paris8.iut.nchaieb.sae_jeux.modele.Controlleur;
    opens universite_paris8.iut.nchaieb.sae_jeux.modele.Controlleur to javafx.fxml;
    exports universite_paris8.iut.nchaieb.sae_jeux.modele.Observateur;
    opens universite_paris8.iut.nchaieb.sae_jeux.modele.Observateur to javafx.fxml;
    exports universite_paris8.iut.nchaieb.sae_jeux.modele.Vague;
    opens universite_paris8.iut.nchaieb.sae_jeux.modele.Vague to javafx.fxml;
    exports universite_paris8.iut.nchaieb.sae_jeux.modele.Symbole;
    opens universite_paris8.iut.nchaieb.sae_jeux.modele.Symbole to javafx.fxml;
    exports universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
    opens universite_paris8.iut.nchaieb.sae_jeux.modele.Entite to javafx.fxml;

}