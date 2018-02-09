import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/*
 * CLASSE qui s'occupe de la lecture du fichier .ss correspondant a la proteine, et remplir la matrice correspondante de taille [3][longueur_proteine]
 */

public class LectureSS extends LectureDonneesProteine{
	
	
	public LectureSS(String nom, int longueur) {
		this.nomProteine = nom;
		this.longueurProteine = longueur;
	}
	
	
	public void lireFichier() throws IOException{
		String line="";
		BufferedReader br = null;
		
		/*On teste si le nom du fichier associé à la proteine contient des _ comme dans le nom de la proteine ou elles etaient 
		 * remplacees par des points . , ce qui etait le cas dans nos fichiers de donnees
		 */
		try{
				
			br = new BufferedReader(new FileReader(new File("./ssAcc/"+nomProteine+".ss")));
				
		}catch(FileNotFoundException f){
				
			try{
				br = new BufferedReader(new FileReader(new File("./ssAcc/"+nomProteine.replace('_','.')+".ss")));
			}
			catch(FileNotFoundException fn){
				throw new FileNotFoundException("ss");
			}
		}
			
		/*sauter la premiere ligne contenant le nom de la proteine*/
		line=br.readLine();
		
		
		line=br.readLine();
		matrice = new double[3][longueurProteine];
		for(int i=0; i<longueurProteine; i++) {
			for(int j=0; j<3; j++) {
				matrice[j][i]=0;
			}
			char c=line.charAt(i);
			if(c=='H') {
				matrice[0][i]=1;
			}
			else if(c=='E') {
				matrice[1][i]=1;
			}
			else if(c=='C') {
				matrice[2][i]=1;
			}
		}
		
		br.close();
		
	}
	
}
