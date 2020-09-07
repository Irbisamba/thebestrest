### create User
curl "http://localhost:8080/save?name=User2&email=user2@gmail.com&password=qwerty"

SELECT name, m.meal_title, m.price FROM restaurants LEFT JOIN meals m on restaurants.id = m.restaurant_id