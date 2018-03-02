# cette fonction utilise le fichier construit de la fonction labels_to_ints
#afin d'associer les bons labels entiers aux proteines de train et de test
def construire_donnees_labels(fic_liste_proteines, fic_listes_labels_proteines):
    labels_fic = open(fic_listes_labels_proteines, "r")

    d = dict()

    #on recupere la liste de tous les labels
    for line in labels_fic:
        if(line == "\n"):
            break
        
        tab = line.split("\t")

        d[tab[0]] = tab[1]

    labels_fic.close()
    print(len(d))

    #on ouvre le fichier du dataset et le fichier de sortie
    f = open(fic_liste_proteines, "r")
    f2 = open(fic_liste_proteines+".labels_entiers", "w")

    for line in f:
        if(line == "\n"):
            break

        tab = line.split('\t')
        if(len(tab) == 1):
            tab = line.split(' ')

        if(tab[0] not in d.keys()):
            tab[0] = tab[0].replace("_",".")

        label = d[tab[0]]

        #on ecrit dans le fichier de sortie le nom de la proteine et son label entier
        f2.write(tab[0] + "\t" + tab[1] + "\t")

        f2.write(str(label) + "\n")

    f.close()
    f2.close()


construire_donnees_labels("test_dataset.list_family", "PDB_SCOP95_seq_scop1.75.list.integers")
construire_donnees_labels("test_dataset.list_superfamily", "PDB_SCOP95_seq_scop1.75.list.integers")
construire_donnees_labels("train_dataset.list", "PDB_SCOP95_seq_scop1.75.list.integers")
        
