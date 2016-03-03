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
