Hello,

Thank you for looking at my test. 

The relevant code and configurations are in the "app" and "conf" directory.

For convenience, I have included the built project. It was built with Java 8 build 1.8.0_31-b13
I have also included the Mocha test results in the sample-test/testResults directory.
If you do not wish to run the application you can also run tests again disastorm.com:8888

To run the application on a Unix-based system, you should do the following:
export JAVA_OPTS=-Dhttp.port=8888
(From sample-test directory): ./target/universal/stage/bin/sample-test

To run it on a Windows system, you should do the following:
(From sample-test directory): ./target/universal/stage/bin/sample-test.bat
This is a custom modified bat file to force the port number in there. A backup copy of it is also located at sample-test/windows/sample-test.bat

The sample-test/target directory was created when building this Java/Scala Play Framework project using the Typesafe Activator tool. There are more details about Play Framework further down.

Before making comments on the API spec itself, I would like to comment on the database tables. I noticed a few things here.

-The attends table has the user_id and event_id columns as Integers while the actual users and events tables store them as BigIntegers.
Although this issue is relatively minor, and would only affect a system with a very large amount of users, these fields should still be kept in sync with each other.

-Another, more important issue, is that the email column is not unique in the database on the users table. This can be a problem since the email is used to identify a user
and log them in. This field should definitely be made unique, as we wouldn't want duplicate rows in there due to a bug in the application.

-Additionally, this is not an actual issue, but it may be worth considering if foreign keys should be kept on these tables between the attends, users, and events tables.
This should usually be done if we plan to have these tables always on the same database and similar to the previous issue, it should prevent missing rows due to a bug in the application.


As for the api spec, there were a few things that were not specified that I decided to add in.

-The framework I decided to use was the Java/Scala Play Framework. I used this because I am experienced with it and Java is my primary programming language. Additionally, The Play Framework is a relatively lightweight framework that contains many integrated features such as the Netty webserver,the Java EBean ORM, and a logging system. This is much easier than something like a Spring + Hibernate + Log4j Enterprise setup. From what I've read, the Play Framework is based off of the concepts of other light weight frameworks from other programming languages like Ruby. Although I did not take advantage of this in my test project, the Play Framework also supports fully asynchronous programming by using callbacks.

-For the apis that allow an optional offset and limit, I added a default for both of these values, if they are not supplied. Offset, as you can expect, defaults to 0, and limit
 defaults to 1000. The reason we need a default limit is because MYSQL requires a LIMIT when an OFFSET is given. Therefore, I chose an arbitrarily high value for the default limit.

-There was no spec for the authorization token, so below is how I implemented it and the way it works.
In order to maintain a stateless server, the authorization token is a serialized version of the User data along with an expiry date time. The User data is converted to Json,
encrypted via AES using a random salt and a predefined constant server-side salt. The random salt is appended to the beginning of the encrypted token bytes. The reason why
the random salt is useful, is because it will mean that 2 tokens containing the same data will almost never contain the same bytes, which, from what I read, is important for security.
As I mentioned, I added an expiry date to the token, as well, which I arbitrarily set to 30 days. After this time, apis will return a 401 code and a message telling the caller
that the token has expired.

As for modifications to the spec, these are some things to think about:

-All of the dates in the spec are of the format: YYYY-MM-DD HH:mm:ss
I think you will notice that there is no time zone data there. This is definitely an issue, since time zone is basically a requirement to know a time. I suppose you could say that all the times in the system should be of a particular time zone, but there will probably still be users and companies who will make mistakes which can cause alot of confusion. I think the time zone
should be added and all APIs should output either UTC time or the Local Time Zone.

-It may be worth considering the different response codes of the APIs. While the application has its own custom api codes such as 500 for invalid authentication, I believe standard REST APIs
have standardized response codes. For example, with the current spec, invalid authentication results in a 200 Response Code with a 500 Api Code. It may be worth standardizing the response code in this case to 401 Unauthorized.

Those are my comments on the test. I hope you will find my implementation to be well done.
Thanks.
