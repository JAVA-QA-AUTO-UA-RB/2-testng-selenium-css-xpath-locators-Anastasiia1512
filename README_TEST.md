# Instructions for running tests ## How to run tests To run tests in your project, follow these steps: 1. Go to the Maven installation on your machine. To do this, enter the command: ```bash mvn -v ``` If Maven is installed, you will get information about the Maven version.

2. Go to the root directory of your project. This is the directory in which the `pom.xml` file is located.

```bash cd /way/to/your/project ``` 3. Run tests using the following command: ```bash mvn test ``` Maven will automatically run all the tests assigned to your project.

## Example of the console output when the tests are successfully completed. If the tests are successfully completed, you are required to provide a similar output from the console:
Results:
[INFO]
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  42.937 s
[INFO] Finished at: 2024-09-06T14:45:59+02:00
[INFO] ------------------------------------------------------------------------
