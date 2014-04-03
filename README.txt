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
        |  Company   |
        +–––––+––––––+
              | 1
              |
              | *
        +–––––+––––––+
        |    Plan    |
        +––––––––––––+
              | 1
              |
              | *
   +––––––––––+––––––––––+
   |       Period        |
   +–––––––––––––––––––––+
   |   MonthDayInterval  | 1       * +–––––+––––––+
   |   LocalTimeInterval |–––––––––––|   TypeDay  |
   |   Price             |           +–––––+––––––+
   +–––––––––––––––––––––+

