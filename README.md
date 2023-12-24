-- This readme file is going to be updated but for now I just want to explain 2 lambda functions I created.

I created 2 lambda functions for my project. Both are deployed and running on AWS Lambda. I made calls to them in the functions package in my project.

1. Lambda:
    - Used when a user registers and I call it from AuthService. I's purpose is to email the newly registered user with his/hers confirmation code (clickable link, made with UUID)
    - Link of it: https://f75k5m2ygpvw6bjzeqfi33nicm0vpqve.lambda-url.eu-central-1.on.aws/
    - Example JSON body for testing purposes (POST Request):
    - {
        "firstName": "Benjamin",
        "email": "benjamin.peljto@stu.ibu.edu.ba",
        "token": "ffffffffffffffffffffff"
      }

2. Lambda:
     - Used when user buys tickets for an event. It's purpose is to generate QR codes for the tickets, pack them as email attachment and email them to the user. Also can be tested through postman or similar.
     - Link of it: https://hf32xmiz5ibbvgeqokihzajvoy0dvini.lambda-url.eu-central-1.on.aws/
     - Example JSON body for testing purposes (POST Request)
     - {
         "buyerID": "benjobenjo",
         "buyerName": "Benjamin",
         "buyerEmail": "benjamin.peljto@stu.ibu.edu.ba",
         "tickets": [
            {
                "ticketID": "3245345",
                "buyerID": "benjobenjo",
                "buyerName": "Benjamin",
                "eventName": "Billiard cup - Skenderija",
                "ticketType": "VIP"
            },
            {   
                "ticketID": "5555555555",
                "buyerID": "benjobenjo",
                "buyerName": "Benjamin",
                "eventName": "Billiard cup - Skenderija",
                "ticketType": "VIP"
            }
         ]
      }
