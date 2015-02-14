# Sessions Exercise

## Intro

Session reconstruction, also known as sessionization, is a technique used in web analytics to divide user events into sessions, with the intent of understanding user behaviour and calculating metrics such as session length or bounce rate. Various methodologies exist, the most popular is the time-oriented approach, which use user inactivity as a signal to end a session and begin a new one.

Time-oriented approaches to session reconstruction look for a period of inactivity, or "inactivity threshold": a span of time between requests by a user. Once this period of inactivity is reached, the user is assumed to have left the site or stopped using the browser entirely, and the session is ended: further requests from the same user are considered a second session. A common value for the inactivity threshold is 30 minutes.

## Goal

You job is to write a program that reads [this dataset](http://ita.ee.lbl.gov/html/contrib/EPA-HTTP.html) and calculates the sessions for each visitor using an inactivity threshold of 30 minutes.

The sessions must be written to a file named `sessions.csv`, each line in the file must have the following format:

```
IP_ADDRESS,SESSION_START,SESSION_END
```

where:

* `IP_ADDRESS` is the IP address of the client
* `SESSION_START` is the timestamp of the start of the visit session
* `SESSION_END` is the timestamp of the end of the visit session

### Extra goal

If you're very good and you finish early, you can also implement basic hourly statistics by creating a file `stats.csv` with the following format:

```
DAY,HOUR,VISITORS
```

where:

* `DAY` is either `29` or `30`
* `HOUR` is the hour of the day (`00` to `23`)
* `VISITORS` is the count of active sessions during that hour of the day

A session is considered active during a certain our of the day if any of the following is true:

* `SESSION_START` <= `HOUR_START` && `SESSION_END` >= `HOUR_END`
* `SESSION_START` >= `HOUR_START` && `SESSION_START` <= `HOUR_END`
* `SESSION_END` >= `HOUR_START` && `SESSION_END` <= `HOUR_END`

where for a certain hour of the day (say 2pm):

* `HOUR_START` is 14:00
* `HOUR_END` is 14:59

## Submission instructions

### Programming language

You can choose any programming language you desire to implement the solution (if you know many languages, we prefer functional languages).

### Implementation

Try to use a functional approach to the solution, with a strong preference towards immutability and composability.

For instance, use `map`, `reduce` and `partition` cosntructs and prefer immutable data structures.

If your language of choice is Java, a good choice is [Guava's Immutable Collections](https://code.google.com/p/guava-libraries/wiki/ImmutableCollectionsExplained).

### Submission guidelines

To submit your solution, fork this repository, commit your code and create a pull request.
