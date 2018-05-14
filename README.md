# wahlblock-backend

## 1. API

### 1.1 Get info about a election

Returns for a election id the election information with title, description and election period. (Atm only one election in dummy data, so Id is 1)

| **URL** |
|:---|
| `/api/v1/election/:id` |

| **Method** |
|:---|
| `GET` |

| **URL Params** |
|:---|
| *Required:* |
| `id`: id of election |

| **Example URL** |
|:---|
| `/api/v1/election/1` |

| **Example success response with body** |
|:---|
| **Code:** 200 OK |
```json
{
    "id": 1,
    "title": "Lorem ipsum dolor sit amet",
    "description": "Cat ipsum dolor sit amet ... ",
    "beginDate": "2018-08-01 10:00:00",
    "endDate": "2019-08-20 23:59:59",
    "selectionOptions": [
        {
            "position": 1,
            "option": "Yes"
        },
        {
            "position": 2,
            "option": "No"
        }
    ]
}
```

### 1.2 Log voter in

Logs the voter in once and returns a hash value if voter not logged in before.

| **URL** |
|:---|
| `/api/v1/voter/login` |

| **Method** |
|:---|
| `POST` |

| **Headers** |
|:---|
| `Content-Type: application/json` |

Body:
```json
{
    "idCardNumber": "ABC123456",
    "voterKey": "12345678",
    "publicKey": "XXX"
}
```

| **Example URL** |
|:---|
| `/api/v1/voter/login` |

| **Example success response with body** |
|:---|
| **Code:** 200 OK |
```json
{
    "hash": "6485f6d071b2a88a0c6f3dc997280fa8bb21dbb0d252280db7fcac3eecddbc52"
}
```

| **Example failure response with body** |
|:---|
| **Code:** 404 Forbidden |
| **Possible messages:** "Voter logged in before", "Voting not allowed" |
```json
{
    "status": "FORBIDDEN",
    "message": "Voter logged in before",
    "detail": "uri=/api/v1/voter/login",
    "timestamp": "2018-03-24 12:58:47"
}
```

### 1.3 Validate hash values

Validates a list of hash values with hash values from logged in users in DB and returns all non valid hash values as a list.

| **URL** |
|:---|
| `/api/v1/validation` |

| **Method** |
|:---|
| `POST` |

| **Headers** |
|:---|
| `Content-Type: application/json` |

Body:
//data object is there because consumer is using grahql ;)
```json
{
	"data": [
		"6485f6d071b2a88a0c6f3dc997280fa8bb21dbb0d252280db7fcac3eecddbc52",
		"not a valid hash value",
		"also not a valid hash value"
		]
}
```

| **Example URL** |
|:---|
| `/api/v1/validation` |

| **Example success response with body** |
|:---|
| **Code:** 200 OK |
```json
[
    "not a valid hash value",
    "also not a valid hash value"
]
```

## 2. Usage

Use the included gradle wrapper to run the project

Run the application
```bash
./gradlew bootRun
```

## 3. Info

DB is filled with data from the import.sql file on application start and DB data is lost on application exit.
