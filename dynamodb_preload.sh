#!/bin/bash
# Populating User table
aws dynamodb put-item \
--endpoint-url http://localhost:8000 \
--table-name 'Users' \
--item '{
  "userId": {"S": "281724217c33dcdf2"},
  "name": {"S": "Britney Spears"},
  "email": {"S": "britney@gmail.com"},
  "username": {"S": "thebigspear1"},
  "birthday": {"S": "12/02/1981"}
}'
aws dynamodb put-item \
--endpoint-url http://localhost:8000 \
--table-name 'Users' \
--item '{
  "userId": {"S": "0084d4dc6e1d57a7193"},
  "name": {"S": "Mario Lopez"},
  "email": {"S": "mariolopez@gmail.com"},
  "username": {"S": "bigcatch56"},
  "birthday": {"S": "04/23/1999"}
}'
aws dynamodb put-item \
--endpoint-url http://localhost:8000 \
--table-name 'Users' \
--item '{
  "userId": {"S": "98d0900e72005055f216"},
  "name": {"S": "Zelda Lotus"},
  "email": {"S": "zelda23@hotmail.com"},
  "username": {"S": "pinkzelda12"},
  "birthday": {"S": "02/07/2001"}
}'
# Populating VideoGame table
aws dynamodb put-item \
--endpoint-url http://localhost:8000 \
--table-name 'GameCatalog' \
--item ' {
"id": {"S": "D216314B"},
"title": {"S": "Age of Mythology: The Titans"},
"developer": {"S": "Ensemble Studios"},
"genre": {"S": "Real-time strategy"},
"year": {"N": "2003"},
"description": {"S": "Age of Mythology: The Titans is an expansion pack to the real-time strategy video game of Age of Mythology. The Titans adds a fourth culture to the game, the Atlantean'\''s, and three new major gods, plus new units, buildings and god powers. It also includes many new features, such as auto-queueing (allows indefinite training of units as long as you have sufficient resources), and the ability to summon a Titan, a gargantuan, godlike being that forms the game'\''s focal point."},
"country": {"S": "U.S.A."},
"platforms": {"S": "PC"} ,
"tags": {"S": "Single-Player, Multiplayer"},
"image": {"S" : "https://upload.wikimedia.org/wikipedia/en/2/21/Age_of_Mythology_-_The_Titans_Liner.jpg"}
}'

aws dynamodb put-item \
--endpoint-url http://localhost:8000 \
--table-name 'GameCatalog' \
--item '{
  "id": {"S": "2A67C3E71"},
  "title": {"S": "Assassin'\''s Creed IV: Black Flag"},
  "developer": {"S": "Ubisoft Montreal"},
  "genre": {"S": "Action-adventure, stealth"},
  "year": {"N": "2013"},
  "description": {"S": "Assassin'\''s Creed IV: Black Flag is an action-adventure, stealth game set in an open world environment and played from a third-person perspective. The game features three main cities: Havana, Kingston, and Nassau, which reside under Spanish, British, and pirate influence, respectively."},
  "country": {"S": "France"},
 "platforms": {"S": "Playstation 3, Playstation 4, Xbox, Wii U, Nintendo, PC, Stadia"},
"tags": {"S": "Single-Player, Multiplayer"},
"image": {"S" : "https://cdn.cloudflare.steamstatic.com/steam/apps/242050/capsule_616x353.jpg?t=1670596397"}
}'


