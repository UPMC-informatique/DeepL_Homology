import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * CLASSE qui s'occupe construire la matrice de sortie, et la stocker dans un fichier de sortie .resf (matrice de taille [45][longueur_proteine])
 */
public class EcrireProteineFinal {
	
	protected String nomProteine;
	protected int longueurProteine;
	protected double [][] matriceFinale;
	
	public EcrireProteineFinal(String nomProteine, int longueurProteine){
		this.nomProteine = nomProteine;
		this.longueurProteine = longueurProteine;
		matriceFinale = new double[45][longueurProteine];
		for(int i=0; i<45; i++){
			for(int j=0; j< longueurProteine; j++){
				matriceFinale[i][j] = 0;
			}
		}
		
	}
	
	/*Construction de la matrice de sortie en creant des objets LectureDonneesProteines qui construisent les differents briques
	 * de cette matrice finale
	 */
	private void construireMatrice() throws FileNotFoundException, IOException{
		double [][] matrice_fasta, matrice_pssm, matrice_ss, matrice_acc;
		
		
		/*LEcture du fichier .fasta*/
		LectureFasta lf = new LectureFasta(nomProteine, longueurProteine);
		lf.lireFichier();
		matrice_fasta = lf.getMatrice();
		
		/*LEcture du fichier .pssm*/
		LecturePSSM lp = new LecturePSSM(nomProteine, longueurProteine);
		lp.lireFichier();
		matrice_pssm = lp.getMatrice();
		
		/*remplissage de la matrice finale avec les matrices fasta et pssm*/
		for(int i=0; i<20; i++){
			for(int j=0; j<longueurProteine; j++){
				matriceFinale[i][j] = matrice_fasta[i][j];
				matriceFinale[i+20][j] = matrice_pssm[i][j];
			}
		}
		
		
		/*LEcture du fichier .ss*/
		LectureSS ls = new LectureSS(nomProteine, longueurProteine);
		ls.lireFichier();
		matrice_ss =ls.getMatrice();
		
		/*remplissage de la matrice finale avec la matrice ss*/
		for(int i=0; i<3; i++) {
			matriceFinale[i+40]= matrice_ss[i];
		}
		
		/*LEcture du fichier .acc20*/
		LectureACC la = new LectureACC(nomProteine, longueurProteine);
		la.lireFichier();
		matrice_acc = la.getMatrice();
		
		/*remplissage de la matrice finale avec la matrice acc*/
		for(int i=0; i<2; i++) {
			matriceFinale[i+43]= matrice_acc[i];
		}
		
	}
	
	
	/*ecrire la matrice finale dans un fichier de sortie .resf qui sera cree dans le sous dossier nomDossier*/
	public void ecrireFichierProteineFinal(String nomDossier) throws IOException{
		String nomFic = nomDossier+"/"+nomProteine+".resf";
		construireMatrice();
		PrintWriter p = new PrintWriter(new File(nomFic));
		
		for(int i=0; i<45; i++){
			for(int j=0; j< longueurProteine; j++){
				p.print(matriceFinale[i][j]);
				p.print(" ");
			}
			p.println();
		}
		
		p.flush();
		p.close();
	}
	

}
