•	L’énoncé du problème : 
On souhaite créer une application web et mobile qui permet de gérer des cinémas.
Chaque cinéma se trouvant dans une ville est Mini par son code, son nom et sa position géographique.
Le cinéma contient un ensemble de salles.
Chaque salle qui est définie par son numéro, son nom contient un ensemble de places.
Chaque place à un numéro et positionnée géographiquement.
Quotidiennement, on programme plusieurs projections de films dans des salles.
Chaque Projection se déroule dans une séance, concerne un Film et se déroule dans une Salle a une date de projection et un prix fixe.
Chaque séance est définie par son numéro et l'heure de début e la séance.
Chaque projection on prévoie un ensemble de Tickets.
Chaque Ticket concerne une Place et défini par le nom du client, le prix du ticket et le code payement.
L'application se compose de 2 Parties : La partie backend et la partie Frontend.

•	Les exigences fonctionnelles de l’application sont : 
Gestion des Cinémas (Consultations, Saisie, Ajout, Edition, Mise à jour et suppression) 
Gestion des Salles et des Places 
Gestion Films 
Gestion Projection
Gestion des ventes des Tickets 
La partie backend est basée sur Spring et se compose des couches DAO, Service et Web.
La couche DAO est basée sur Spring Data, JPA, Hibernate 
La couche Métier est définie par une interface et une implémentation quelques spécifications fonctionnelles qui nécessite des calculs ou des traitements particuliers 
La couche Web est basée sur des API Restful basee sur Spring Data Rest ou un RestController 
La partie Frontend est basée sur le Framework Angular. 
La sécurité est basée sur Spring Security et Json Web Token 

