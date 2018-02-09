import java.io.FileNotFoundException;
import java.io.IOException;

/*Classe abstraite qui regroupe les classes qui vont lire les donnees des fichiers*/

public abstract class LectureDonneesProteine {
	protected String nomProteine;
	protected int longueurProteine;
	protected double [][] matrice;
	
	
	/*Lire le fichier correspondant afin de remplir les cases de la matrice*/
	public abstract void lireFichier() throws FileNotFoundException, IOException;
	
	/*renvoyer la matrice*/
	public double [][] getMatrice(){
		return matrice;
	}
	
	
	/*afficher la matrice sur la console*/
	public void afficherMatrice(){
		for(int i=0; i< matrice.length; i++){
			System.out.print(acideAmineAPartirIndice(i) + "\t");
			for(int j = 0; j< matrice[i].length; j++){
				System.out.print(matrice[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	
	/*renvoyer l'indice d'une acide amine (de 0 a  19) en passant son caractere en parametre*/
	public int indiceAcideAmine(char aa){
		switch(aa){
		case 'A': return 0;
		case 'R': return 1;
		case 'N': return 2;
		case 'D': return 3;
		case 'C': return 4;
		case 'Q': return 5;
		case 'E': return 6;
		case 'G': return 7;
		case 'H': return 8;
		case 'I': return 9;
		case 'L': return 10;
		case 'K': return 11;
		case 'M': return 12;
		case 'F': return 13;
		case 'P': return 14;
		case 'S': return 15;
		case 'T': return 16;
		case 'W': return 17;
		case 'Y': return 18;
		case 'V': return 19;
		default : return -1;
		}
	}
	
	
	/*renvoyer le caractere d'une acide amine en passant son indice en parametre*/
	public char acideAmineAPartirIndice(int i){
		switch(i%20){
		case 0: return 'A';
		case 1: return 'R';
		case 2: return 'N';
		case 3: return 'D';
		case 4: return 'C';
		case 5: return 'Q';
		case 6: return 'E';
		case 7: return 'G';
		case 8: return 'H';
		case 9: return 'I';
		case 10: return 'L';
		case 11: return 'K';
		case 12: return 'M';
		case 13: return 'F';
		case 14: return 'P';
		case 15: return 'S';
		case 16: return 'T';
		case 17: return 'W';
		case 18: return 'Y';
		case 19: return 'V';
		default : return '-';
		}
	}

}
