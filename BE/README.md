# Quiz Backend (Java Spring Boot)


## APIs

  ### Admins
   1. POST `/api/admin/question/add`

        `{
            "question":"Fathometer is used to measure",
            "choices": [
                {
                    "name": "Earthquakes",
                    "isAnswer": "n"
                },
                {
                    "name": "Rainfall",
                    "isAnswer": "n"
                },
                {
                    "name": "Ocean depth",
                    "isAnswer": "y"
                },
                {
                    "name": "Sound intensity",
                    "isAnswer": "n"
                }
            ]
        }`

   2. GET `/api/admin/question/all`


  ### Logged in Users
   1. GET `/api/user/quiz/{qzid}/question/get/{id}`        

   2. GET `/api/user/quiz/{id}/question/get/all`

   3. POST `/api/user/quiz/new`

        `{
            "startDate":"Thu Nov 18 2021 13:54:45 GMT+0530 (India Standard Time)"
        }`

   4. POST `/api/user/quiz/{id}/answer/add`

        `{
            "qid": 7,
            "cid": 5
        }`

   5. GET `/api/user/quiz/result/all`

   6. GET `/api/user/quiz/result/{id}`

