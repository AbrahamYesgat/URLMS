# Group 1 - University Research Lab Management System

Status:
[![Build Status](https://travis-ci.com/ECSE321-Fall2017/P1.svg?token=t5kUxHm2St2Da9kDdoZM&branch=master)](https://travis-ci.com/ECSE321-Fall2017/P1) </br>

Note: Each application is seperated in the corresponding folder. 
AppURLMS contains the android app, WebURLMS contains the web app and DesktopURLMS contains the desktop app. 

----------------------------------
HOW TO RUN WEB APPLICATION
----------------------------------

NOTE TO GRADER: If you can't manage to run the application, please send an email to:
	Andre	andre.vallieres@mail.mcgill.ca

Note: This is not an exhaustive tutorial in any sense to show how to run the php application, it is there to help the grader have an overview how to run it.

1. Make sure you have installed: npm, composer, php.
These tools have to be available in command line for the next steps to succeed.

2. In the WebURLMS folder, run
```$ composer install```.
This will install any dependencies.

3. Again, in the same folder, run
```$ npm install```.
This will install node modules.

4. Now, everything should be install, but we need to generate an API key
``` 
$ cp .env.example .env
$ php artisan key:generate 
```
 
5. Finally, build the application with
```$ npm run dev```
and launch the server with
```$ php artisan serve```.

The web application will be available at http://127.0.0.1:8000/

----------------------------------
HOW TO RUN ANDROID APPLICATION
----------------------------------

* Use build-tool version 26.0.2 or later
* Use android SDK version 25 or later
* Have java version 1.8 or later

### Alternate method to run the application (using the apk)

1. Run the emulator and wait until it has fully started. 

2. Locate the URLMS.apk in the /appURLMS folder

3. Place the apk file in the skd/platform-tools sub-folder of where the emulator was installed (ex: ~/Library/Android/sdk/platform-tools/ was where mine was located).

4. Open command line and enter the following command ```$ ./adb install "URLMS.apk" ``` on Mac or ```$ adb install "URLMS.apk"``` on Windows. (success message should appear on completion)

The app should appear the application menu of the emulator. 

----------------------------------
HOW TO RUN DESKTOP APPLICATION
----------------------------------

* Have java version 1.8 or later

1. Create a directory called "output" within the directory "~/P1/DesktoURLMS" (this is where xml files will be saved for persistence).

2. Ensure the "DesktopAppURLMS.jar" file is in the same directory as this "output" folder for it to be runnable.
(By default, we have placed the jar file in correct directory. So long as output folder added to correct path, jar shall be execteable. Thus, please leave it as is if possible)

3. Simply double click on the executable jar file and application should pop up.
