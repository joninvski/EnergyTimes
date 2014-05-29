Build Instructions
-------------------


To see a list of all available commands, run "gradlew tasks".

Energy Times
============

Show the current price category for the Portuguese electric suppliers

Compile
-------

    # Optional
    ANDROID_HOME=/home/.../android/sdk; export ANDROID_HOME
    ./gradlew assemble


Install on device
-----------------

    # Make sure emulator is running or connected to real device
    ./gradlew installDebug

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

