UtiliSocial API Java Client
===========================

This is a sample standalone Java client that uses the UtiliSocial REST API.  When started, it grabs all existing posts, writes them to
a file (in the same directory that the jar is run), then connects to the real-time streaming API and writes to the same file for every post
received.



Prerequisites
-----------

  - Java 7



How-To
-----------

Download target/utilisocial-api-java-client-0.9.0.2.jar

```sh
cd <jar-location>
java -jar utilisocial-api-java-client-0.9.0.2.jar
```


Output
-----------

A file named utilisocial-data.txt will be generated in the directory in which you ran the jar.  The contents will
look similar to this.

```json
{
  "userName": "KaneFan57",
  "message": "RT @TheBatmanDark: Investigation confirms not even Bane\u0027s power outage attempt can save the 49ers.",
  "latitude": 38.030823,
  "longitude": -122.245132,
  "type": "TWITTER",
  "imageUrl": "",
  "date": "Wed Jun 04 22:30:54 UTC 2014",
  "userProfileImageUrl": "",
  "id": 115156,
  "tweetId": "474317397948522498",
  "locationType": "USER_HOME",
  "state": "NEW"
}
{
  "userName": "tinagunnarsson",
  "message": "#MountainView #poweroutage I\u0027m very disappointed, Twitter -- surely I\u0027m not the only person waiting for my phone to run out of batteries?",
  "latitude": 37.189396,
  "longitude": -121.70533,
  "type": "TWITTER",
  "imageUrl": "",
  "date": "Thu Jun 05 04:50:37 UTC 2014",
  "userProfileImageUrl": "",
  "id": 115429,
  "tweetId": "474412956596203520",
  "locationType": "USER_HOME",
  "state": "NEW"
}
```