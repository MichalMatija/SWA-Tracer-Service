# Tracer Service

Tracer Service monitors current car offers and sends e-mails in case of a match.  
Uses mysql database.  
Running on:
* localhost
* port: 10222


## API

#### Tracer
Tracer controller has 4 methods:
* add - for adding tracers
* edit - for editing tracers
* remove - for removing tracers
* getAll - for getting all tracers by user

Method getAll is needed for editing and removing particular tracer. Because we don't know tracerId before adding its. Therefore it is necessary call **getAll** after added new tracer. 

Examples:  
**Add**  
Url: **localhost:3306/tracer/add**

```json
{
  "userId": 1,
  "priceFrom": 500,
  "priceTo": 600,
  "model": "Kia ceed"
}
```

**Edit**  
Url: **localhost:3306/tracer/edit**

```json
{
  "userId": 1,
  "tracerId": 4,
  "priceFrom": 500,
  "priceTo": 600,
  "model": "Citroen C1"
}
```

**Remove**  
Url: **localhost:3306/tracer/remove**

```json
{
  "userId": 1,
  "tracerId": 3
}
```

**GetAll**  
Url: **localhost:3306/tracer/{1}**, where 1 is userId

#### Offer

Offer has 2 methods:
* check - for adding and editing car offer
* remove - for removing car offer

Examples:

**Chceck**  
Url: **localhost:3306/offer/check**

```json
{
  "userId": 1,
  "carId": 233,
  "price": 1000,
  "model": "Kia rio"
}
```

**Remove**
Url: **localhost:3306/offer/{233}**, where 1 is cardId
