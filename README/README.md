travis and other //todo

Voting system for deciding where to have lunch (REST only)
===============================================================

AdminController

 POST /admin/restaurant/create 
 Метод создает ресторан в БД. На вход принимает объект RestaurantRequest, где обязательный параметр String name, 
 необязательный String address, и обязательный int adminId. Возвращает созданный объект Restaurant.
 
`curl -v -X POST -H "Content-Type: application/json" --data '{"name": "Uchkuduk", "address" : "Chita", "adminId": 2} ' "http://localhost:8080/admin/restaurant/create"`

POST /restaurant/addMeal
Метод добавляет еду в существующий ресторан. //todo в ресторане должен быть список, перегрузить метод, чтобы он принимал список 

GET /admin/get-restaurants
Метод возвращает список всех ресторанов с подробной информацией. 

`curl 'http://localhost:8080/admin/get-restaurants'`

GET /admin/{id}/get-restaurants
Метод возвращает список всех ресторанов, принадлежащих админу, с подробной информацией. На вход принимает Integer adminId.

`curl 'http://localhost:8080/admin/2/get-restaurants'`

UserController

GET /get-restaurants
Метод возвращает список RestaurantMeal c id, именем, рейтингом и едой ресторана.  
Если ресторан не обновлялся со вчерашнего для, или еда в нем отсутствует, то такой ресторан не попадает в список.

`curl 'http://localhost:8080/get-restaurants'`

GET /{id}/vote
Метод принимает на вход Integer userId и Integer restaurantId. Вовзвращает строку с сообщением об успешном голосовании.
Метод голосования. Если юзер голосует впервые за день, то рейтинг ресторана увеличивается на 1, если юзер уже голосовал сегодня,
и хочет переголосовать, то до 11-00 он может это сделать, тогда рейтинг ресторана, за который он проголосовал ранее уменьшится на один, 
а рейтинг ресторана, id которого передан в метод, будет увеличен на 1. Если юзер хочет переголосовать после 11-00, то будет выброшено 
исключение.

`curl 'http://localhost:8080/1/vote?restaurantId=7'`