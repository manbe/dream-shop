 
# The Dream Shop

Given an online shop. Let's call the currency coins. So each customer of the shop has some credit which can be used to make purchases.


## Input

- the list of items identified by SKU (stock keeping unit, e.g. 10001-1290-S). Item consists of name, price (in coins), the amount of items in stock (which can be zero).

- the list of customers identified by ID (some long integer value, e.g. 100001). User consists of user-name, email and available credit (in coins, which also can be zero).

- the list of customer's shipping addresses identified by ID (also long integer value) to which it is possible to ship purchased items after checkout. Shipping address consists of postal code, street, house and flat.

## Output

- the final result of checkout is user's order. The order consists of coins total and the list of items ordered.

## Assumptions

- Assuming that all customers are living in the same town. 

- Assuming that logic for creating deleting and updating listed entities is already present in the system. So you do not need to implement it. But you still need some data in you database. So it is required that you design the schema (as simple as possible) and fill the database with some data of your choice.

## REST resources to implement

- items in stock (read-only)

- customers (read-only)

- customer's shipping addresses (read-only)

- orders (read/write)


# SWAGGER URL#
 #
http://localhost:8080/swagger-ui.html

You can find all curls there

## POST ORDER
 ##
curl -X POST --header "Content-Type: application/json" --header "Accept: */*" -d "  {
    \"totalPrice\": 10,
    \"orderItemViews\": [
      {
        \"stockKeepingUnitId\": 1,
        \"actualPrice\": 10,
        \"amount\": 2
      }
    ]
  }" "http://localhost:8080/shop/api/customer/1/order"

