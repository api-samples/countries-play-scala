REST JSON API: Countries
=========================

Simple interface for querying country information by CCA2 two-character country code,
per assignment https://bitbucket.org/mikaelhg/interview-countries

# Find the beef
* Test: [test/controllers/CountriesSpec.scala](test/controllers/CountriesSpec.scala)
* Routes: [conf/routes](conf/routes)
* Controller: [app/controllers/Countries.scala](app/controllers/Countries.scala)

# Install prerequisites
1. Java
2. [sbt](http://www.scala-sbt.org/download.html)

# Test it
`sbt clean test`

# Run it locally
`sbt run`

Check out the details of Finland at http://localhost:9000/countries/FI

# Deploy on Heroku
Prerequisites:

1. A [Heroku](https://heroku.com/) account, obviously
2. [Heroku toolbelt](https://toolbelt.heroku.com/) installed and ready to go

Set up a new app on Heroku and deploy:
```
heroku create --stack cedar
git push heroku master
```

See [Play documentation: Deploying to Heroku](https://www.playframework.com/documentation/2.3.x/ProductionHeroku) for more details.
