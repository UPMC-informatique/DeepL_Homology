Dans ce projet, il y a 3 dossiers:
1- labels_formatting qui contient un fichier python contenant une fonction labels_to_int, qui prend en paramètre un fichier contenant des lignes du format (Nomproteine, taille proteine, famille proteine),
et renvoie un fichier de sortie .integers contenant des lignes (nom proteine, famille proteine sous forme entier)

2- construction_liste_labels, qui contient un fichier python contenant une fonction construire_donnees_labels, qui prend en paramètre le nom d'un fichier contenant la liste des proteines (comme test_dataset.list_family) contenant des lignes du format (nom proteine, taille proteine, famille proteine),
et prend en parametre aussi le fichier construit a partir de la fonction labels_to_int, et renvoie un fichier de sortie .labels_entiers contenant des lignes
(nom proteine, taille proteine, famille proteine sous forme entier) (comme test_dataset.list_family.labels_entiers)

3- le dossier 2DCNN contenant le fichier test2D qui contient l'architecture du réseau de neurones, ainsi qu'un fichier datasets.py qui contient
une fonction dataset_construction qui prend en parametre le nom du dossier contenant les fichiers .resf contenant les matrices des proteines
(comme train_dataset.list_resf), aisni que le nom du fichier contenant la lsite des proteines avec les labels entiers (comme test_dataset.list_family.labels_entiers)
construit dans la section 2, et donne en sortie le couple (dataset, labels_dataset) sous forme de numpy arrays, afin de les utiliser dans 
le 2D CNN

Remarque: Dans le fichier datasets.py, il y aune fonction save_data() qui charge les donnees a l'aide de la fonction dataset_construction et les 
stocke dans des fichiers binaires numpy .npy afin de les charger plus rapidement plus tard pendant l'entrainement du reseau
