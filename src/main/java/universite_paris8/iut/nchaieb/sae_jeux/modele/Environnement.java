package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import universite_paris8.iut.nchaieb.sae_jeux.vue.MonstreVue;

import java.util.ArrayList;

public class  Environnement {

//	private int largeur,hauteur;

	private IntegerProperty nbTours;

	//ce que j'ai rajouté(musa le japonais)
	//t pas japonais heee heee
	private ArrayList<EntiteAllieeDeBase> lesAlliees;
	private ArrayList<MonstreDeBase> lesMonstres;

	public Environnement() {
		this.nbTours = new SimpleIntegerProperty();
		//ce que j'ai rajouté(musa le japonais)
		this.lesAlliees = new ArrayList<>();
		this.lesMonstres = new ArrayList<>();

		//à voir, pour l'instant
		MonstreDeBase.compteurID = 0;
		EntiteAllieeDeBase.compteurID = 0;
	}

	public void ajouterEntite(MonstreDeBase entite){

		lesMonstres.add(entite);
//		if(this.monstreVue!=null){
//			this.monstreVue.animation(entite);
//		}


    }

	public final IntegerProperty nbToursProperty(){
		return this.nbTours;	
	}

	public int getNbTours() {
		return this.nbTours.getValue();
	}




	public void unTour() {
		//faut les supp quand ils sont morts / sinon avance
		if(!lesMonstres.isEmpty()){
			for (int i = lesMonstres.size() - 1; i >= 0; i--){
				if (!lesMonstres.get(i).estVivant()){
					lesMonstres.remove(i);

				} else {
					lesMonstres.get(i).agir();
				}
			}
		}

	}

	public void avancer(MonstreDeBase monstre) {
		monstre.setPosX(monstre.getPosX()+1);
	}

	//ce que j'ai rajouté(musa le japonais)

	public ArrayList<EntiteAllieeDeBase> getLesAlliees() {
		return this.lesAlliees;
	}
	public ArrayList<MonstreDeBase> getLesMonstres() { return this.lesMonstres; }

	public EntiteAllieeDeBase getLesAlliees(String id) {
		for(EntiteAllieeDeBase a: this.lesAlliees){
			if(a.getId().equals(id)){
				return a;
			}
		}
		return null;
	}

	public MonstreDeBase getLesMonstres(String id) {
		for(MonstreDeBase m: this.lesMonstres){
			if(m.getId().equals(id)){
				return m;
			}
		}
		return null;
	}

	public ArrayList<MonstreDeBase> voirLesMonstresElimines(){
		ArrayList<MonstreDeBase> historiqueDeKill = new ArrayList<>();
		MonstreDeBase entiteActuel;
		for (int i = 0; i < this.lesMonstres.size(); i++){
			entiteActuel = lesMonstres.get(i);
			historiqueDeKill.add(entiteActuel);
			this.lesMonstres.remove(i);
		}
		return historiqueDeKill;
	}
}
