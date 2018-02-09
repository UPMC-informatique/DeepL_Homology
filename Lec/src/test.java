

/*UTILISATION DU PROGRAMME: Il faut creer un objet ListeProteines en lui passant en parametres le nom du fichier contenant la liste des noms
 * des proteines, ainsi que le dossier sortie dans lequel les fichiers de sortie doivent etre stoques.
 * Les fichiers .pssm.ascii, .fasta, .ss et .acc20 doivent etre obligatoirement organises dans des sous dossiers "pssm", "fasta" et "ssAcc"
 */


public class test {

	public static void main(String[] args) {
		
		try{
			
			System.out.println("LECTURE DE test_dataset.list_family");
			System.out.println("=============");
			ListeProteines lp1 = new ListeProteines("test_dataset.list_family","./test_dataset.list_family_resf");
			lp1.ecrireDonneesProteines();
			System.out.println("=============");
			System.out.println("FIN LECTURE DE test_dataset.list_family\n\n");
			
			
			System.out.println("LECTURE DE test_dataset.list_superfamily");
			System.out.println("=============");
			ListeProteines lp2 = new ListeProteines("test_dataset.list_superfamily","./test_dataset.list_superfamily_resf");
			lp2.ecrireDonneesProteines();
			System.out.println("=============");
			System.out.println("FIN LECTURE DE test_dataset.list_superfamily\n\n");
			
			
			System.out.println("LECTURE DE train_dataset.list");
			System.out.println("=============");
			ListeProteines lp3 = new ListeProteines("train_dataset.list","./train_dataset.list_resf");
			lp3.ecrireDonneesProteines();
			System.out.println("=============");
			System.out.println("FIN LECTURE DE train_dataset.list\n\n");

		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}

	}

}
