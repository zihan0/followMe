 # Follow Me (Task 2)

## Getting Started

1. Use an IDE to open the project and run the main class `~/FollowMe/src/com/follow/me/main/FollowMeApp.java`
2. From terminal, with java installed, use command to run the .jar file `~/FollowMe/out/artifacts/FollowMe_jar/FollowMe.jar``java -jar FollowMe.jar`

### Prerequisites

IntelliJ 2019.3 is used for developing this application.
Java 11 and JUnit4 is used.

## Running the tests

1. Input should follow the required format as stated in the task.

`clip <name> <ticks to play> <follow chance1> <follow chance2> <action1> <action2>`

`ticks <ticks to play> <optional list for chance for clips to play>`
2. Application collects all `clip` command and trigger a printout by `ticks` command.
3. Application only cache `clips` data for the current `ticks`, meaning it keeps clips input prior to the `ticks`, and clear data after the printout.
4. Application will stop with `exit` input.
4. Application use a random float number as an odd when deciding which actions to take for each `clip`.
5. Unit tests for `FollowMeService` under the package `~/FollowMe/src/com/follow/me/test`, main class is tested with manual input.
## Authors

* **Zihan Huang** (github.com/zihan0)
