# Transactions

### Built With
* Groovy / Java 8
* Spring Boot 2.4.5
* H2
* Docker
____ 

### How to Run


#### - Using Docker:
```
docker-compose up
```


#### - Locally:
```
./gradlew bootRun
```
Execute only tests:
```
./gradlew test
```

Application will be running on **localhost:8080**

H2 console can be acessed on [localhost:8080/h2-console](http://localhost:8080/h2-console), using `jdbc:h2:mem:transactions` as JDBC URL.

____ 

### Postman Collection

Import [this collection](https://github.com/jadehelena/transactions/blob/main/Transactions.postman_collection.json) on Postman.

____ 

### Account
| Field | Type |
| :------------ |:---------------:|
| id      | Long |
| documentNumber      | String(11) |

#### 1. Create an Account
/POST `localhost:8080/accounts` \
Request body:
```
{
	"documentNumber": "01223456312"
}
```


Response body:
```
{
	"id": 1
	"documentNumber": "01223456312"
}
```
Status code: 201


#### 2. Get Account
/GET `localhost:8080/accounts/{id}` \
Response body:
```
{
	"id": 1,
	"documentNumber": "01223456312"
}
```
Status code: 200

____ 

### Transaction

| Field | Type | |
| :------------ |:---------------:| :---------------:|
| id      | Long | |
| operationType      | int | |
| accountId      | int | |
| amount      | Double | Only **positive** values| \




Valid **OperationTypes**:

| Value | OperationType |
| :------------ |:---------------|
| 1      | Cash purchase |
| 2      | With installments purchase |
| 3      | Withdraw |
| 4      | Payment |


#### 3. Create a Transaction
/POST `localhost:8080/transactions` \
Request body:
```
{
	"operationType": 1,
	"amount": 10.0,
	"accountId": 1
}
```

Response body:
```
{
	"id": 1,
	"operationType": 1,
	"amount": -10.0,
	"accountId": 1
}
```

Status code: 201
