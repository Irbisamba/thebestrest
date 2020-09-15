# thebestrest
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/14148ccfae014a3898c5d36c6973d642)](https://app.codacy.com/manual/Irbisamba/thebestrest?utm_source=github.com&utm_medium=referral&utm_content=Irbisamba/thebestrest&utm_campaign=Badge_Grade_Dashboard)
[![Build Status](https://travis-ci.org/Irbisamba/thebestrest.svg?branch=master)](https://travis-ci.org/Irbisamba/thebestrest)


Voting system for deciding where to have lunch (REST only)
===============================================================

##AdminController

###POST /admin/restaurant/create   
Метод создает ресторан в БД. На вход принимает объект RestaurantRequest, где обязательный параметр String name, 
необязательный String address, и обязательный int adminId. Возвращает созданный объект Restaurant.
 
`curl -v -X POST -H "Content-Type: application/json" --data '{"name": "Uchkuduk", "address" : "Chita", "adminId": 2} ' "http://localhost:8080/admin/restaurant/create"`

###POST /restaurant/newMealList  
Метод добавляет список еды для ресторана и сохраняет еду и историю. На вход принимает объект MealListRequest, где  
два обязательных параметра - Integer restaurantId и список еды (название и цена). Возвращает список сохраненной еды.

`curl -v -X POST -H "Content-Type: application/json" --data '{"restaurantId" : 7, "meals" : [ {"mealTitle" : "Buuza","price" : 50 },{"mealTitle" : "Salmon","price" : 500}]}' "http://localhost:8080/admin/restaurant/newMealList"`

###POST /restaurant/addMeal  
Метод добавляет еду в список ресторана. Если список == null, то создает новый список. Если  
в списке ресторана есть еда, но список обновлен не сегодня, то считаем информацию устаревшей и заменяем этот список на новый.

`curl -v -X POST -H "Content-Type: application/json" --data '{"restaurantId" : 7,"mealTitle" : "Cheesecake","price" : 150}' "http://localhost:8080/admin/restaurant/addMeal"`

###GET /admin/get-restaurants  
Метод возвращает список всех ресторанов с подробной информацией. 

`curl 'http://localhost:8080/admin/get-restaurants'`

###GET /admin/{id}/get-restaurants  
Метод возвращает список всех ресторанов, принадлежащих админу, с подробной информацией. На вход принимает Integer adminId.

`curl 'http://localhost:8080/admin/3/get-restaurants'`

###GET /admin/restaurant/delete  
Метод удаляет ресторан по id.

`curl 'http://localhost:8080/admin/restaurant/delete?restaurantId=7'`

###GET /admin/restaurant/clearMeals
Метод заменяет список еды ресторана пустым списком

`curl 'http://localhost:8080/admin/restaurant/clearMeals?restaurantId=7'`

##UserController

###GET /get-restaurants  
Метод возвращает список RestaurantMeal c id, именем, рейтингом и едой ресторана.  
Если ресторан не обновлялся со вчерашнего для, или еда в нем отсутствует, то такой ресторан не попадает в список.

`curl 'http://localhost:8080/get-restaurants'`

###GET /{id}/vote   
Метод принимает на вход Integer userId и Integer restaurantId. Вовзвращает строку с сообщением об успешном голосовании.
Метод голосования. Если юзер голосует впервые за день, то рейтинг ресторана увеличивается на 1, если юзер уже голосовал сегодня,
и хочет переголосовать, то до 11-00 он может это сделать, тогда рейтинг ресторана, за который он проголосовал ранее, уменьшится на 1, 
а рейтинг ресторана, id которого передан в метод, будет увеличен на 1. Если юзер хочет переголосовать после 11-00, то будет выброшено 
исключение.

`curl 'http://localhost:8080/1/vote?restaurantId=7'`