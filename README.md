Energy Times
============

Show the price for the current time from the Portuguese electric suppliers

Compile
-------

    # Optional
    ANDROID_HOME=/home/.../android/sdk; export ANDROID_HOME
    ./gradlew assemble


Install on device
-----------------

    # Make sure emulator is running or connected to real device
    ./gradlew installDebug

Activity tests
--------------

    # Installs and runs the tests for Build 'debug' on connected devices.
    ./gradlew connectedAndroidTest

    # Runs all the instrumentation test variations on all the connected devices
    ./gradlew spoon        # results in app/build/spoon/debug/index.html

Code quality
------------

    # Runs lint on all variants
    ./gradlew lint         # results in _app/build/lint-results.html_

    # Run tests and generate Cobertura coverage reports
    ./gradlew cobertura    # results in _domain/build/reports/cobertura/index.html_

    # Checks if the code is accordings with the code style
    ./gradlew domain:check # results in _domain/build/reports/checkstyle/main.xml_

Unit tests
----------

    # Run the unit tests of the domain subproject
   ./gradlew :domain:test # Check the results in _domain/build/reports/tests/index.html_

Class diagram
=============

        +––––––––––––+
        |  Company   | --- (edp or endesa)
        +–––––+––––––+
              | 1
              |
              | *
        +–––––+––––––+
        |    Plan    | --- (ciclo semanal or ciclo diario)
        +––––––––––––+
              | 1
              |
              | *
   +––––––––––+––––––––––+
   |       Period        |  --- (from day X to Y, from hour A to B the price is Z)
   +–––––––––––––––––––––+
   |   MonthDayInterval  | 1       * +–––––+––––––+
   |   LocalTimeInterval |–––––––––––|   TypeDay  | --- (Weekend, weekday)
   |   Price             |           +–––––+––––––+
   +–––––––––––––––––––––+

