# Sessions Exercise

## Intro

Session reconstruction, also known as sessionization, is a technique used in web analytics to divide user events into sessions, with the intent of understanding user behaviour and calculating metrics such as session length or bounce rate. Various methodologies exist, the most popular is the time-oriented approach, which use user inactivity as a signal to end a session and begin a new one.

Time-oriented approaches to session reconstruction look for a period of inactivity, or "inactivity threshold": a span of time between requests by a user. Once this period of inactivity is reached, the user is assumed to have left the site or stopped using the browser entirely, and the session is ended: further requests from the same user are considered a second session. A common value for the inactivity threshold is 30 minutes.

## Goal

Your job is to write a program that reads [this dataset](http://ita.ee.lbl.gov/html/contrib/EPA-HTTP.html) and calculates the sessions for each visitor using an inactivity threshold of __30 minutes__.

The computed visit sessions must be written to a file named `sessions.csv`, each line in the file must have the following format:

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

### Extra extra goal

You get super extra bonus points if you build your solution on top of a distributed computing framework like Hadoop or Spark. 

## Submission instructions

### Programming language

You can choose any programming language you desire to implement the solution (if you know many languages, we prefer functional languages).

### Implementation

Try to stick to a [functional](http://en.wikipedia.org/wiki/Functional_programming) approach when implementing the solution, with a strong preference towards immutability and composability.

For instance prefer `map`, `reduce` and `partition` constructs against flow constrol construct like `for` and `while`,  and use immutable data structures as much as possible (i.e. create new collections instead of modifying existing ones).

If your language of choice is Java, a good immutable collection library is [Guava's Immutable Collections](https://code.google.com/p/guava-libraries/wiki/ImmutableCollectionsExplained).

### Submission guidelines

To submit your solution, fork this repository, commit your code and create a pull request.

## Some info about the provided solution

### Implementation 

The solution was implemented in Java and was build and tested on top of a virtual Hadoop framework v. 2.3.0

### Project structure

* src folder contains the source files 
* build folder contains the jar used to test the application
* output_files folder contains the two cvs files obtained by executing the provided program

### Ececution
 Session reconstruction:
 hadoop jar sessionization.jar sessionization sessionization_input_path sessionization_output_path
 After executing the execution `sessions.csv` will be available at sessionization_output_path/sessions
 
 hadoop jar sessionization.jar hourly_stats sessionization_output_path/sessions/sessions.csv hourly_stats_output_path
 After executing the execution `stats.csv` will be available at hourly_stats_output_path/stats/stats.csv
 
 
 
