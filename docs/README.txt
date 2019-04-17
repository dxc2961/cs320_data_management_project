BUILDING THE PROJECT

*Run using IntelliJ*

For first time users:
    1)  You will first need to download h2 from their website (http://www.h2database.com/html/download.html). Once you
        do this, you will need to add it to your project structure in IntelliJ by going to File->Project Structure
        and then navigating to the Modules tab. In the Modules tab, click he green '+' symbol, and then choose the option
        to add a JAR. Then specify the location of the h2 jar file that you downloaded.

    2)  Run 'PackageMain' in the 'PackageDatabase' directory in order to create
        and populate the tables in the database.


    3)  Once the database is populated, run 'RunApplication' in the 'Views' directory.
        This will launch the command line interface to interact with the database (for information regarding
        each of the user interfaces, see 'README2.txt' in the 'docs' folder).


For returning users:
    1)  The database should already be created and populated so, unless you need to
        recreate/repopulate the database for some reason, you only need to run
        "RunApplication" in the Views directory to interact with the already populated database.