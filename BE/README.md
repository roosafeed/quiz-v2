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

## Install

To get started,

    1. Download or clone the repository.

    2. Run `npm install` 

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

