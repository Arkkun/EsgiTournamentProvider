# EsgiTournamentProvider
#Utilisateur :
#champs :
#pseudo
#mdp
#mail
#isAdmin
#fonctions :
#connexion
#log off
#changer mot de passe
#gestion information (peut être )
#inscription
#lister equipe 

#Equipe 
#champs :
#nom
#tag
#membres
#fonctions :
#créer team
#supprimer team
##demande de d’adhesion
#lister demandes d'adhésion (createur only)
#accepter une demande (createur only)
#liste des prochains matchs

#Tournoi
#champs :
#nom
#membre par equipe minimum
#nombre max equipe
#equipes inscrites
#type
#fonctions :
#creation de tournoi (admin only)
#inscrire une de ses equipes au tournoi
#lancer tournoi/cloturer inscriptions (admin only)

#Partie
#champs : 
#resultat
#equipe1
#equipe2
#preuves
#fonctions :
#Donner résultat
#chargement preuve

#Lorsqu’un resultat de match est donné :
#arbre : calcul du prochain match
#ladder : calcul des changements de points
