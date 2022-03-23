### Run emlak burada microservices
    docker-compose up

    # incase of errors, try forcing a image rebuild
    docker-compose up --build --force-recreate

### Check the status of all the microservices
    docker-compose ps

### To execute the API's through the gateway
    1) localhost:8080/login 
    2) localhost:8080/advert-api/** -> emlak-burada
    3) localhost:8080/banner-api/** -> emlak-burada-banner 
    4) localhost:8080/product-api/** -> emlak-burada-product

### Basic use cases
#### Signup a new user
    curl --request POST \
    --url http://localhost:8080/advert-api/signup \
    --header 'Content-Type: application/json' \
    --data '{
    "firstName": "ali",
    "lastName": "bal",
    "passwordHash":"123",
    "email": "emlakburada@gmail.com",
    "mobileNo": "0555555555",
    "userType" : "INDIVIDUAL"
    }'
#### Login with the created user to get authentication token
    curl --request POST \
    --url http://localhost:8080/login \
    --header 'Content-Type: application/json' \
    --data '{
    "email":"emlakburada@gmail.com",
    "passwordHash":"123"
    }'
#### Create an address
    curl --request POST \
    --url http://localhost:8080/advert-api/address \
    --header 'Authorization: eyJraWQiOiJidXNyYSIsInR5cCI6IkpXVCIsImFsZyI6IkVTMzg0In0.eyJpc3MiOiJBRE1JTiIsImF1ZCI6IkFQSV9VU0VSIiwic3ViIjoiNCIsIlVTRVJOQU1FIjoiYWxpIGJhbCIsImV4cCI6MTY0ODA2NTQzNX0.JdLLAc17pCtcA_29BoTc0CHqCWpaG6LvIE77r6NwHs6kjzssRWRBXU5dvloSk5UUGJJXJTBpXtdGnRLhHTmlMG4DwKFYRE1BsEutirhLtpd96FyOMtROwCZoFJEfQSpX' \
    --header 'Content-Type: application/json' \
    --data '{
    "city":"Istanbul",
    "district":"Kadıkoy",
    "addressDesc":"apartment"
    }'
#### Create a property
    curl --request POST \
    --url http://localhost:8080/advert-api/property \
    --header 'Authorization: eyJraWQiOiJidXNyYSIsInR5cCI6IkpXVCIsImFsZyI6IkVTMzg0In0.eyJpc3MiOiJBRE1JTiIsImF1ZCI6IkFQSV9VU0VSIiwic3ViIjoiNCIsIlVTRVJOQU1FIjoiYWxpIGJhbCIsImV4cCI6MTY0ODA2NTQzNX0.JdLLAc17pCtcA_29BoTc0CHqCWpaG6LvIE77r6NwHs6kjzssRWRBXU5dvloSk5UUGJJXJTBpXtdGnRLhHTmlMG4DwKFYRE1BsEutirhLtpd96FyOMtROwCZoFJEfQSpX' \
    --header 'Content-Type: application/json' \
    --data '{
    "description":"perfect land",
    "addressId":5,
    "grossSquareMeter":200,
    "isForSale":true,
    "propertyType":"LAND",
    "landPropertyDetails": {
    "parcelNo":24,
    "zoning":""
    }
    }'
#### Create an advert
    curl --request POST \
    --url http://localhost:8080/advert-api/advert \
    --header 'Authorization: eyJraWQiOiJidXNyYSIsInR5cCI6IkpXVCIsImFsZyI6IkVTMzg0In0.eyJpc3MiOiJBRE1JTiIsImF1ZCI6IkFQSV9VU0VSIiwic3ViIjoiNCIsIlVTRVJOQU1FIjoiYWxpIGJhbCIsImV4cCI6MTY0ODA2NTQzNX0.JdLLAc17pCtcA_29BoTc0CHqCWpaG6LvIE77r6NwHs6kjzssRWRBXU5dvloSk5UUGJJXJTBpXtdGnRLhHTmlMG4DwKFYRE1BsEutirhLtpd96FyOMtROwCZoFJEfQSpX' \
    --header 'Content-Type: application/json' \
    --data '{
    "title":"big land",
    "description": "near sea in uskudar",
    "propertyId":7,
    "images":["https://hecdn01.hemlak.com/mncropresize/640/480/ds01/9/5/5/3/2/2/8/3/1641564767-950223-38223559.jpeg"],
    "cost":1234,
    "duration":3,
    "shouldHighlighted":false,
    "isReviewed":false,
    "isActive":true,
    "phoneNumber":"05552626955"
    }'
#### Buy a product to post adverts
    curl --request POST \
    --url http://localhost:8080/product-api/start_transaction \
    --header 'Authorization: eyJraWQiOiJidXNyYSIsInR5cCI6IkpXVCIsImFsZyI6IkVTMzg0In0.eyJpc3MiOiJBRE1JTiIsImF1ZCI6IkFQSV9VU0VSIiwic3ViIjoiNCIsIlVTRVJOQU1FIjoiYWxpIGJhbCIsImV4cCI6MTY0ODA2NTQzNX0.JdLLAc17pCtcA_29BoTc0CHqCWpaG6LvIE77r6NwHs6kjzssRWRBXU5dvloSk5UUGJJXJTBpXtdGnRLhHTmlMG4DwKFYRE1BsEutirhLtpd96FyOMtROwCZoFJEfQSpX' \
    --header 'Content-Type: application/json' \
    --data '{
    "accountNumber":"123456789",
    "productId":1
    }'
#### Complete payment
    curl --request PUT \
    --url http://localhost:8080/product-api/complete_transaction \
    --header 'Authorization: eyJraWQiOiJidXNyYSIsInR5cCI6IkpXVCIsImFsZyI6IkVTMzg0In0.eyJpc3MiOiJBRE1JTiIsImF1ZCI6IkFQSV9VU0VSIiwic3ViIjoiNCIsIlVTRVJOQU1FIjoiYWxpIGJhbCIsImV4cCI6MTY0ODA2NTQzNX0.JdLLAc17pCtcA_29BoTc0CHqCWpaG6LvIE77r6NwHs6kjzssRWRBXU5dvloSk5UUGJJXJTBpXtdGnRLhHTmlMG4DwKFYRE1BsEutirhLtpd96FyOMtROwCZoFJEfQSpX' \
    --header 'Content-Type: application/json' \
    --data '{
    "transactionId":9,
    "transactionStatus":"COMPLETE"
    }'
#### Post an advert
    curl --request POST \
    --url http://localhost:8080/advert-api/advert \
    --header 'Authorization: eyJraWQiOiJidXNyYSIsInR5cCI6IkpXVCIsImFsZyI6IkVTMzg0In0.eyJpc3MiOiJBRE1JTiIsImF1ZCI6IkFQSV9VU0VSIiwic3ViIjoiNCIsIlVTRVJOQU1FIjoiYWxpIGJhbCIsImV4cCI6MTY0ODA2NTQzNX0.JdLLAc17pCtcA_29BoTc0CHqCWpaG6LvIE77r6NwHs6kjzssRWRBXU5dvloSk5UUGJJXJTBpXtdGnRLhHTmlMG4DwKFYRE1BsEutirhLtpd96FyOMtROwCZoFJEfQSpX' \
    --header 'Content-Type: application/json' \
    --data '{
    "title":"big land",
    "description": "near sea in uskudar",
    "propertyId":7,
    "images":["https://hecdn01.hemlak.com/mncropresize/640/480/ds01/9/5/5/3/2/2/8/3/1641564767-950223-38223559.jpeg"],
    "cost":1234,
    "duration":3,
    "shouldHighlighted":false,
    "isReviewed":false,
    "isActive":true,
    "phoneNumber":"05552626955"
    }'

### To access swagger documentation visit the below urls
    emlak-burada - http://localhost:8081/swagger-ui/index.html
    emlak-burada-banner - http://localhost:8082/swagger-ui/index.html
    emlak-burada-product - http://localhost:8084/swagger-ui/index.html

### Arcitechture diagram
![](documentation/emlakburada.png)

### ER diagram
![](documentation/er-diagram.png)

###FAQ
    make sure port 5432,5672,15672,8080,8081,8082,8083,8084 are not in use
