import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
 * CLASSE qui s'occupe de la lecture d'un fichier contenant une liste de noms et tailles de protéines sous la forme : nom, taille
 */
public class ListeProteines {
	private String nomFicProteines;
	private String nomDossierResultat;
	
	public ListeProteines(String nomFicProteines, String nomDossierResultat){
		this.nomFicProteines = nomFicProteines;
		this.nomDossierResultat = nomDossierResultat;
	}
	
	/*Lire la liste des proteines dans le fichier nomFicProteines et construire
	 *  les donnees de sortie correspondantes, en les stockant dans un fichier pour chaque proteine
	 *  dans le sous dossier nomDossierResultat
	 */
	public void ecrireDonneesProteines() throws IOException{
		Scanner sc = new Scanner(new File(nomFicProteines));
		EcrireProteineFinal e = null;
		String nomp;
		int longueur;
		
		int erreur= 0;
		
		while(sc.hasNextLine()){
			nomp = sc.next();  			 /*lire le nom de la proteine*/
			longueur = sc.nextInt();	 /*lire la longueur de la proteine*/
			e = new EcrireProteineFinal(nomp, longueur);
			try{
				e.ecrireFichierProteineFinal(nomDossierResultat);
			}catch(FileNotFoundException f){
				System.out.println("Pas de fichier ."+f.getMessage()+" correspondant pour "+nomp);
				erreur++;
			}
			sc.nextLine();				/*sauter le reste de la ligne pour passer a la suivante*/
		}
		
		System.out.println("Nombre de fichier sans correspondance =  "+erreur);
		sc.close();
	}

}
