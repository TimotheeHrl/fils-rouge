dump mysql sur docker
docker exec -ti 74ba1abde386 mysqldump -u root -p root --databases filrouge
dum mysql sans docker
mysqldump -u root -p root --databases filrouge
https://community.jaguar-network.com/sauvegarde-automatique-dune-db-mysql-avec-mysqldump/

lancer avec docker
docker-compose up

Searx
query html exemple
localhost:8082/search?q=coucou&language=fr-FR&time_range=None&safesearch=0&categories=general







docker exec -ti b5b8e81e334f mysqldump -u luteceuser -p gru-plugin-appointment --databases >> ./dump.sql
