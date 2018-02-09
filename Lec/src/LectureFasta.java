import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



/*
 * CLASSE qui s'occupe de la lecture du fichier .fasta correspondant a la proteine, et remplir la matrice correspondante de taille [20][longueur_proteine]
 */

public class LectureFasta extends LectureDonneesProteine {
	
	protected char [] sequence;  /*La sequence d'acides amines de la proteine*/
	
	public LectureFasta(String nomProteine, int longueurProteine){
		this.nomProteine = nomProteine;
		this.longueurProteine = longueurProteine;
		sequence = new char[longueurProteine];
		matrice = new double[20][longueurProteine];  /*la matrice de fasta contiendra 20 lignes (les acides amines) et l colonnes*/
		
		for(int i = 0; i< 20; i++){
			for(int j =0; j< longueurProteine; j++){
				matrice[i][j] = 0;
			}
		}
	}
	
	public void lireFichier() throws FileNotFoundException, IOException{
		
		BufferedReader bf;
		/*On teste si le nom du fichier associé à la proteine contient des _ comme dans le nom de la proteine ou elles etaient 
		 * remplacees par des points . , ce qui etait le cas dans nos fichiers de donnees
		 */
		try{
			
			bf = new BufferedReader(new FileReader(new File("./fastas/"+nomProteine+".fasta")));
			
		}catch(FileNotFoundException f){
			
			try{
				bf = new BufferedReader(new FileReader(new File("./fastas/"+nomProteine.replace('_','.')+".fasta")));
			}
			catch(FileNotFoundException fn){
				throw new FileNotFoundException("fasta");
			}
		}
		
		bf.readLine(); /*sauter la premiere ligne contenant le nom de la proteine*/
		
		String line;
		int i = 0;
		char c;
		
		/*Lecture de la sequence de la proteine du fichier .fasta, ainsi que le remplissage de la matrice*/
		while(i<longueurProteine){
			line = bf.readLine();
			for(int j =0; j< line.length(); j++){
				c = line.charAt(j);
				sequence[i] = c;
				matrice[indiceAcideAmine(c)][i] = 1;
				i++;
			}
		}
		
		bf.close();
	}
	
	

}
