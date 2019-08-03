#TechTest : 
ce projet était fait par Maher GLENZA et proposé par la société Happn comme étant un test technique 

Compiler
--------

Pour compiler, vous avez besoin de Maven.

Lancer le Projet
--------
Pour Lancer le projet, idéalement vous avez besoin d'IDE STS 3 pour compiler le projet et ajouter le JAR comme étant une application Spring boot au serveur avec le main : com.happn.techTest.TechTestApplication

Contenu du Projet
--------
c'est une application (ou un microservice) spring boot qui procure deux web-service REST l'un est pour calculer le nombre de POIs d'une zone et l'autre est pour trouver les n zones les plus denses d'un fichier Tsv mis en entrée pour l'application contenant plusieurs points POI.

Ajout de fichier TSV
--------
il faut avant tout acceder au fichier application.properties ans src/main/resources et vous changer le path file.path:\\E:\\inputFile.tsv vers votre fichier tsv qui doit sembler à ce modèle:
<table  cellspacing="0" cellpadding="6" >
<thead>
<tr>
<th scope="col" >id</th>
<th scope="col" >lat</th>
<th scope="col" >lon</th>
</tr>
 <tr>
<th scope="col" >id1</th>
<th scope="col" >6.5</th>
<th scope="col" >-7</th>
</tr>
</thead>
</table>

Execution des webservices
--------
vous lancer le serveur spring boot et vous acceder en localhost à cette adresse "http://localhost:8181/techtest/calculatepoi" pour lancer le premier webservice en ajoutant comme queryparam une latitude et une longitude avec des valeurs pour que l'uri devient par exemple: 
"http://localhost:8181/techtest/calculatepoi?min_lat=6.5&min_lon=-7" et de même façon vous faites pour le deuxieme webservice mais avec le l'adresse : "http://localhost:8181/techtest/searchdensity" et le queryparam n qui reflète le nombre des zones les plus dense à chercher et l'uri devient : "http://localhost:8181/techtest/searchdensity?n=2"

Testes Unitaire 
--------

vous trouver aussi une liste de testes unitaires qui se font en ordre avec le lancement de la compilation dans le package : src/test/java


Merci et bonne éxecution
--------
