## Kotlin Scooter Maps

This application was built as an example on how to use several libraries and SDKs together.
Currently, the application utilizes the following technologies and libraries

- `Kotlin 1.1.4-2`
- `Retrofit 2.3.0`
- `Dagger2 2.11`
- `RxJava2 2.1.1`
- `Timber 4.5.1`

The application is built following the `MVP` architecture.

## How to

In order to use this application, a `config.properties` file has to be created in 
the `app` module. The file has to define two variables:

- `GOOGLE_MAPS_API_KEY=<YOUR_API_KEY>`
- `SCOOTER_API_URL=<YOUR_API_URI>`

These properties are being read during source compilation. The `SCOOTER_API_URL` is
created as a field of `BuildConfig`, whereas the `GOOGLE_MAPS_API_KEY` is created
as a `String` within the resources.

## What does it do?

This application displays a `SupportMapFragment` and displays the current position
of items returned by the API's endpoint. Each item has a state indicator (battery level)
which is used to determine the marker's color. 
  
Clicking the `refresh` button will re-query the data, and add the new results.
 
## Todo

- Data consistency: Delete deprecated items from the map (items that don't show up in the)
response anymore.
- Write unit tests for the `Presenter` classes
- Add caching for the received list
  
## License

This software is released under the [Apache License v2](https://www.apache.org/licenses/LICENSE-2.0). 
 
## Copyright

Copyright 2017 Damian Burke