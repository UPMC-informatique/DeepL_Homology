# Imports
import numpy as np

#Longueur max des proteines
MAX = 200

#cette fonction construit le dataset et ses labels sous form de numpy arrays, a partir des fichiers contenant les donnees
#dataDossier contient les fichiers .resf des proteines contenant les matrices
#labelsFic est le fichier contenant la liste des noms des proteines suivies de leur taille et leur labels entier
def dataset_construction(dataDossier, labelsFic):
    total = 0

    labels = open(labelsFic, "r")

    dataset = []
    labelset = []

    #on parcours le fichier contenant la liste des proteines
    for line in labels:
        tab = line.split("\t")

        if((int(tab[1]))>MAX):
            continue
        
        #On ouvre le fichier contenant la matrice
        try:
            data = open(dataDossier+"/"+tab[0]+".resf", "r")
        except IOError:
            try:
                tab[0] = tab[0].replace("_",".")
                data = open(dataDossier+"/"+tab[0]+".resf", "r")
            except IOError:
                print(tab[0] + " non retrouve")
                continue

        #On initialise la matrice
        tmp = []

        l = int(tab[1])
        ajout = ((MAX - l)*1.0)/2
        indice = 0

        #On rajoute la moitie du "padding" au debut de la matrice
        while(indice < ajout):
            for i in range(45):
                tmp.append(0.0)
            indice = indice + 1

        #On rajoute les donnees de la matrice   
        for ligne in data:
            mat_lig = ligne.split(' ')
            
            for j in range (l):
                tmp.append(float(mat_lig[j]))

        indice = indice + l

        #On rajoute l'autre moitie du "padding" a la fin de la matrice
        while(indice < MAX):
            for i in range(45):
                tmp.append(0.0)
            indice = indice + 1


        dataset.append(tmp)
        labelset.append(int(tab[2]))


    return np.asarray(dataset, dtype=float),np.asarray(labelset, dtype=int)
