Energy Times
============

[![Build Status](https://travis-ci.org/joninvski/EnergyTimes.svg?branch=master)](https://travis-ci.org/joninvski/EnergyTimes)

Show the price (according to the current hour) of the electricity by portuguese electric suppliers (EDP and Endesa).
Respects daylight savings changes to the plans.

<a href="https://play.google.com/store/apps/details?id=com.pifactorial.energytimes"><img src="http://developer.android.com/images/brand/en_app_rgb_wo_45.png" alt="Play Store Horario Energia" width="180px"></a>

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
    ./gradlew lint         # results in app/build/lint-results.html

    # Run tests and generate Cobertura coverage reports
    ./gradlew cobertura    # results in domain/build/reports/cobertura/index.html

    # Checks if the code is accordings with the code style
    ./gradlew domain:check app:checkstyle # results in [domain|app]/build/reports/checkstyle/main.xml

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


Libraries Used
--------------

- [Robotium](http://code.google.com/p/robotium/)
- Square's [Spoon](http://square.github.io/spoon/)
- Jake Wharton's [Butterknife](http://jakewharton.github.io/butterknife/)
- Google's [Android Support Library v4](http://developer.android.com/reference/android/support/v4/app/package-summary.html)
- [Joda Time](http://www.joda.org/joda-time/)
- Machinarius' [PreferenceFragment](https://github.com/Machinarius/PreferenceFragment-Compat)


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
