package personnages;

public class Gaulois {
	private String nom;
	private int force;
	private int attaque = 2;
	private int effetPotion;
	private Equipement [] trophees;
	private int nbTrophees = 0;
	private int nbEquipementMax = 100;
	
	public Gaulois(String nom, int force, int nbEquipementMax) {
		super();
		if (force <= 0) {
			throw new IllegalArgumentException( "Erreur force : " + force);
			}
		this.nom = nom;
		this.force = force;
		this.nbEquipementMax = nbEquipementMax;
		this.trophees = new Equipement[nbTrophees];
		System.out.println(nom + " rejoind la partie !!\n");
	}

	public String getNom() {
		return nom;
	}
	
	public int getAttaque() {
		return attaque*effetPotion;
	}
	
	public void setAttaque(int attaque) {
		this.attaque = attaque;
		System.out.println("La force de " + nom + " passe � : " + attaque + "\n");
	}
	
	
	public void parler(String texte) {
		System.out.println(prendreParole() + " � " + texte + " � \n");

	}
	
	private String prendreParole() {
		return("Le gaulois " + nom + " : ");
	}
	
//	public void frapper(Romain romain) {
//		System.out.println("Le Gaulois " + nom + " chatouille le romain " + romain.getNom() + " et lui infflige " + getAttaque() 
//				+ " points de dommages (RIP l'amitie)\n");
//		romain.recevoirCoup(getAttaque());
//	}
	
	public void frapper(Romain romain) {
        System.out.println(nom + " envoie un grand coup dans la mâchoire de " + romain.getNom());
        Equipement[] nouveauTrophees = romain.recevoirCoup((force / 3) * effetPotion);
        for (int i = 0; nouveauTrophees != null && i < nouveauTrophees.length; i++,nbTrophees++) {
            trophees[nbTrophees] = nouveauTrophees[i];
        }
    }
	public void boirePotion(int forcePotion) {
		effetPotion = forcePotion;
		parler("Merci Druide, je sens que mon attaque est "+ effetPotion + " fois d�cupl�e.");

	}
	
//	public void sEquiper(Equipement equipement) {
//		if (nbEquipement == nbEquipementMax) {
//			System.out.println("Le soldat " + nom + " est deja bien protege !");
//		}else {
//			int i = 0;
//			while (i < nbEquipement && i != -1) {
//				if (equipements[i] == equipement) { i = -1;} else {i++;}
//			}
//			if (i == -1) {
//				System.out.println("Le soldat " + nom + " possede deja un " + equipement.toString() + " !");
//				} else {
//					equipements[nbEquipement] = equipement;
//					nbEquipement++;
//					System.out.println("Le soldat " + nom + " s equipe avec un " + equipement.toString());
//			}
//		}
//	}
	
	
	public void faireUneDonnation(Musee musee) {
        if (nbTrophees == 0) {
            System.out.println(this.nom + "n'a pas d'équipement à donner ");
        } else {
            System.out.println("Le gaulois " + this.nom +" : « Je donne au musee tous mes trophees :");
            while(nbTrophees > 0) {
                System.out.println("  - " + trophees[nbTrophees - 1].getNom());
                Trophee trophee = new Trophee(this, trophees[nbTrophees - 1]);
                musee.donnerTrophees(this, trophee);
                nbTrophees--;
            }
        }
    }

	@Override
	public String toString() {
		return "Gaulois [nom=" + nom + ", force=" + force + "]";
	}
	
	
	
	public static void main(String[] args) {
		Gaulois asterix = new Gaulois("Asterix", 8, 3);
		System.out.println(asterix);
		Romain jules = new Romain("JulesDu31", 2);
		Druide panoramix = new Druide("Panoramix", 5, 10);
		Musee louvre = new Musee("Louvre");
		
		asterix.parler("tabernacle mon brave");
		asterix.frapper(jules);
		asterix.boirePotion(panoramix.preparerPotion());
		
		Equipement casque = Equipement.CASQUE;
		Equipement bouclier = Equipement.BOUCLIER;
		Equipement epee = Equipement.EPEE;
		
		asterix.faireUneDonnation(louvre);
		System.out.println(louvre.extraireInstructionsCaml());
		
	}
	

}
