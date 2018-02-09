import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/*
 * CLASSE qui s'occupe de la lecture du fichier .pssm.ascii correspondant a la proteine, et remplir la matrice correspondante de taille [20][longueur_proteine]
 */

public class LecturePSSM extends LectureDonneesProteine{
	
	public LecturePSSM(String nomProteine, int longueurProteine){
		this.nomProteine = nomProteine;
		this.longueurProteine = longueurProteine;
		matrice = new double[20][longueurProteine];     /*la matrice de pssm contiendra 20 lignes (les acides amines) et l colonnes*/
		
		for(int i = 0; i< 20; i++){
			for(int j =0; j< longueurProteine; j++){
				matrice[i][j] = 0;
			}
		}
	}
	
	public void lireFichier() throws FileNotFoundException, IOException{
		
		Scanner sc;
		
		/*On teste si le nom du fichier associé à la proteine contient des _ comme dans le nom de la proteine ou elles etaient 
		 * remplacees par des points . , ce qui etait le cas dans nos fichiers de donnees
		 */
		try{
			
			sc = new Scanner(new File("./pssmData/"+nomProteine+".pssm.ascii"));
			
		}catch(FileNotFoundException f){
			
				try{
					sc = new Scanner(new File("./pssmData/"+nomProteine.replace('_','.')+".pssm.ascii"));
				}catch(FileNotFoundException fn){
					throw new FileNotFoundException("pssm");
				}
		}
		
		/*sauter les 3 premieres lignes ne contenant pas de donnees*/
		sc.nextLine();
		sc.nextLine();
		sc.nextLine();
		
		/*parcourir et remplir la matrice*/
		for(int i=0; i< longueurProteine; i++){
			
			sc.nextInt();	/*sauter le numero de la ligne*/
			sc.next();		/*sauter le caractere de l'acide amine*/
			
			/*sauter la premiere matrice qui contient des entiers relatifs*/
			for(int j=0; j< 20; j++){
				sc.nextInt();
			}
			
			/*recuperer la matrice normalise qui vient apres la matrice des entiers relatifs dans le fichier .pssm*/
			for(int j=0; j<20; j++){
				int k = sc.nextInt();
				matrice[j][i] = k*1.0 / 100;
			}
			
			/*sauter les deux valeurs flottantes qui se trouvent a la fin de la ligne*/
			sc.next();
			sc.next();
		}
		
		sc.close();
		
	}
		

}
