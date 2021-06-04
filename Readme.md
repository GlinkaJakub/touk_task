**Touk task**

Run application:
> mvn spring-boot:run 

Test application:
>curl "http://localhost:8282/films?date=2020-01-01&time=12:12:00"

>curl "http://localhost:8282/films/time?startDate=2020-01-01&endDate=2020-01-02&startTime=12:00:00&endTime=13:00:00"

>curl "http://localhost:8282/films/6"

>curl --request POST --data '{"filmId": 6,"seatId": 3,"name": "Adam","surname": "Nowak","type": "ADULT"}' "http://localhost:8282/ticket"