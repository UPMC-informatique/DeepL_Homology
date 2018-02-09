import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/*
 * CLASSE qui s'occupe de la lecture du fichier .acc20 correspondant a la proteine, et remplir la matrice correspondante de taille [2][longueur_proteine]
 */

public class LectureACC extends LectureDonneesProteine{
	
	public LectureACC(String nom, int longueur) {
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
				
			br = new BufferedReader(new FileReader(new File("./ssAcc/"+nomProteine+".acc20")));
				
		}catch(FileNotFoundException f){
				
			try{
				br = new BufferedReader(new FileReader(new File("./ssAcc/"+nomProteine.replace('_','.')+".acc20")));
			}
			catch(FileNotFoundException fn){
				throw new FileNotFoundException("acc");
			}
		}
			
		/*on saute la premiere ligne contenant le nom de la proteine*/	
		line=br.readLine();
		
		
		line=br.readLine();
			
		String buffer = "";
		int seq[] = new int[longueurProteine];
		int n=0;
		
		for(int i=0; i<line.length(); i++) {
			if (i==line.length()-1) {
				buffer=buffer+line.charAt(i);
				seq[n]=Integer.parseInt(buffer);
			}
			if(line.charAt(i)==' ') {
				seq[n]=Integer.parseInt(buffer);
				n++;
				buffer="";
			}
			else {
				buffer=buffer+line.charAt(i);
			}
		}

			
		matrice = new double[2][longueurProteine];
		for(int i=0; i<longueurProteine; i++) {
			for(int j=0; j<2; j++) {
				matrice[j][i]=0;
			}
			int k = seq[i];
			if(k<20) {
				matrice[0][i]=1;
			}
			else if(k>=20) {
				matrice[1][i]=1;
			}
		}
		br.close();
		
	}
	
}