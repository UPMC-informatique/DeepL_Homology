Le fichier Test.java contient le main à exécuter du programme. Dans ce fichier vous trouverez déjà un exemple d'exécution.
Le programme doit etre utilisé de la facon suivante:
Vous créérez dans le main une instance de ListeProteines(String nomFicProteines, String nomDossierResultat), où le paramètre 
nomFicProteines est le fichier contenant les noms des protéines suivies de leur taille, et nomDossierResultat est le nom 
du dossier dans lequel vous souhaitez avoir les fichiers de sortie .resf, et ensuite vous lancez
sa méthode ecrireDonneesProteines().
Les données .pssm.ascii , .fasta, .ss et .acc20 doivent obligatoirement etre stoquées dans les sous dossiers pssmData, fastas et
ssAcc respectivement afin que le programme puisse lire les fichiers puisqu'on spécifie le chemin durant l'ouverture du fichier dans
les classes correspondantes et on laisse pas le choix à l'utilisateur.

Exemple d'exécution:

ListeProteines lp1 = new ListeProteines("test_dataset.list_family","./test_dataset.list_family_resf");
lp1.ecrireDonneesProteines();

La méthode ecrireDonneesProteines s'occupe de tout le travail à faire.
