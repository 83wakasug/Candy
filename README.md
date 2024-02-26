Candy API 🍬

The Candy API allows you to manage delightful candy information effortlessly.
🌐 Endpoints
1. Get Candy Information
   Request

   Method: GET
   URL: /candy/{id}

Response

    200 OK: Successfully retrieved candy information.
    404 Not Found: Candy with the given ID not found.
    500 Internal Server Error: An unexpected error occurred.


2. Add Candy 🍭
   Request

   Method: POST
   URL: /candy/
   Body: Candy object in JSON format

Response

    200 OK: Successfully added candy.
    400 Bad Request: Data integrity violation or invalid request.
    500 Internal Server Error: An unexpected error occurred.

```json
{
  "id": null,
  "name": "Sample Candy",
  "manufacturingCompany": "Sample company",
  "price": 10
}
```

3. Get All Candies 🍫
   Request

   Method: GET
   URL: /candy/all

Response

    200 OK: Successfully retrieved the list of all candies.
    404 Not Found: No candies found.
    500 Internal Server Error: An unexpected error occurred.

4. Update Candy 🍬
   Request

   Method: PUT
   URL: /candy/{id}
   Body: Updated candy object in JSON format

Response

    200 OK: Successfully updated candy.
    404 Not Found: Candy with the given ID not found.
    400 Bad Request: Data integrity violation or invalid request.
    500 Internal Server Error: An unexpected error occurred.

5. Delete Candy 🗑️
   Request

   Method: DELETE
   URL: /candy/{id}

Response

    200 OK: Successfully deleted candy.
    404 Not Found: Candy with the given ID not found.
    400 Bad Request: Data integrity violation or invalid request.
    500 Internal Server Error: An unexpected error occurred.