#Cette fonction prend en entree un fichier contenant une liste de proteines et leur famille
# et renvoie en sortie un fichier contenant cette liste de proteine mais avec leur famille
#sous representation entiere ( a chaque famille sera associe un entier)
def labels_to_ints(fic_liste):

    #on ouvre le fichier contenant la liste
    f = open(fic_liste, "r")
    
    #on cree le fichier de sortie
    out = open(fic_liste+".integers", "w")
    
    #On initialise un dictionnaire qui contiendra les familles et leur representation associe (famille, repr)
    d = dict()
    val = 0
    
    #On parcours le fichier
    for line in f:
        if(line == "\n"):
            break

        #On decoupe la ligne
        tab = line.split('\t')
        if(len(tab) == 1):
            tab = line.split(' ')

        #Si la famille de cette proteine n'a pas encore ete traite, on lui associe comme representation
        # la valeur de la variable "var" courante   
        if(tab[1] not in d.keys()):
            label = val
            d[tab[1]] = label
            #On incremente val pour que la prochaine famille non traite aura comme representation
            #la prochaine valeur de val
            val = val + 1

        # on ecrite la ligne dans le fichier de sortie :

        #nom de la proteine
        out.write(tab[0] + "\t")

        #label binaire de la proteine
        out.write(str(d[tab[1]])+"\t")

        out.write("\n")

    out.close()
    f.close()
    return val

print(labels_to_ints("PDB_SCOP95_seq_scop1.75.list"))
    
