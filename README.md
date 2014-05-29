Energy Times
============

[![Build Status](https://travis-ci.org/joninvski/EnergyTimes.svg?branch=master)](https://travis-ci.org/joninvski/EnergyTimes)

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


License
-------

    /*
     * EnergyTimes
     * Copyright (C) 2014 Joao Trindade
     *
     * This program is free software: you can redistribute it and/or modify
     * it under the terms of the GNU General Public License as published by
     * the Free Software Foundation, either version 3 of the License, or
     * (at your option) any later version.
     *
     * This program is distributed in the hope that it will be useful,
     * but WITHOUT ANY WARRANTY; without even the implied warranty of
     * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
     * GNU General Public License for more details.
     *
     * You should have received a copy of the GNU General Public License
     * along with this program.  If not, see <http://www.gnu.org/licenses/>.
     */
