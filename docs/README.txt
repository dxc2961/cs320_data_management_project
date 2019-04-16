BUILDING THE PROJECT

For first time users:
    1)  Run "PackageMain" in the PackageDatabase directory in order to create
        and populate the tables in the database.

        - To do this, execute the following commands from your jdk: (note: "path\" refers to wherever
          you have the project stored on your computer)

            javac path\cs320_data_management_project\src\PackageDatabase\*.java
            java path\cs320_data_management_project\src\PackageDatabase.PackageMain

    2)  Once the database is populated, run "RunApplication" in the Views directory.
        This will launch the command line interface to interact with the database.

        - To do this, execute the following commands from your jdk: (note: "path\" refers to wherever
          you have the project stored on your computer)

            javac path\cs320_data_management_project\src\Views\*.java
            java path\cs320_data_management_project\src\Views.RunApplication

For returning users:
    1)  The database should already be created and populated so, unless you need to
        recreate/repopulate the database for some reason, you only need to run
        "RunApplication" in the Views directory to interact with the already populated database.

        -To do this, execute the same commands for step 2) above for first time users.