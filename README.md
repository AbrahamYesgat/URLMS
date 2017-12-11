# Group 1 - University Research Lab Management System

Status:
[![Build Status](https://travis-ci.com/ECSE321-Fall2017/P1.svg?token=t5kUxHm2St2Da9kDdoZM&branch=master)](https://travis-ci.com/ECSE321-Fall2017/P1)

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

4. Open command line and enter the following command ```$ ./adb install "URLMS.apk" ``` on MAC or ```$ adb install "URLMS.apk"``` on Windows. (success message should appear on completion)

5. The app should appear the application menu of the emulator. 
