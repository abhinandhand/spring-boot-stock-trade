## Environment:
- Java version: 1.21
- Maven version: 3.*
- Spring Boot version: 3.3.1

## Read-Only Files:
- src/test/*

## Data:
Example of a trade data JSON object:
```json
{
    "id":1,
    "type": "buy",
    "userId": 23,
    "symbol": "ABX",
    "shares": 30,
    "price": 134,
    "timestamp": 1531522701000
}
```

## Requirements:
The task is to implement a model for the trade object and the REST service that exposes the `/trades` endpoint, which allows for managing the collection of trade records in the following way:

**POST** request to `/trades`:

- creates a new trade
- expects a JSON trade object without an id property as a body payload. If the shares value is out of accepted range [1, 100], or the type value is invalid (i.e. not 'buy' or 'sell'), the API must return error code 400. Besides those cases, you can assume that the given payload is always valid.
- adds the given trade object to the collection of trades and assigns a unique integer id to it. The first created trade must have id 1, the second one 2, and so on.
- the response code is 201, and the response body is the created trade object

**GET** request to `/trades`:

- return a collection of all trades
- the response code is 200, and the response body is an array of all trades objects ordered by their ids in increasing order
- optionally accepts query parameters type and userId, for example `/trades/?type=buy&&userId=122`. All these parameters are optional. In case they are present, only objects matching the parameters must be returned.

**GET** request to `/trades/<id>`:

- returns a trade with the given id
- if the matching trade exists, the response code is 200 and the response body is the matching trade object
- if there is no trade with the given id in the collection, the response code is 404

**DELETE**, **PUT**, **PATCH** request to `/trades/<id>`:

- the response code is 405 because the API does not allow deleting or modifying trades for any id value

## Commands
- run: 
```bash
mvn clean package
```
- install: 
```bash
mvn clean install
```
- test: 
```bash
mvn clean test
```

## Implementation notes
- you may use-well known libraries to reduce boilerplate.
- you may utilize TDD and implement your own tests.
- do not change existing tests and do not extend existing test classes.
