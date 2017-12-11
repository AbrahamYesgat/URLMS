# Group 1 - University Research Lab Management System

Status:
[![Build Status](https://travis-ci.com/ECSE321-Fall2017/P1.svg?token=t5kUxHm2St2Da9kDdoZM&branch=master)](https://travis-ci.com/ECSE321-Fall2017/P1)

----------------------------------
HOW TO RUN WEB APPLICATION
----------------------------------

NOTE TO GRADER: If you can't manage to run the application, please send an email to:
	Andre	andre.vallieres@mail.mcgill.ca

Note: This is not an exhaustive tutorial in any sense to show how to run the php application, it is there to help the grader have an overview how to run it.

* Step 1: 
Make sure you have installed: npm, composer, php
These tools have to be available in command line for the next steps to succeed

* Step 2: 
In the WebURLMS folder, run
```$ composer install```.
This will install any dependencies

* Step 3: 
Again, in the same folder, run
```$ npm install```.
This will install node modules

* Step 4:
Now, everything should be install, but we need to generate an API key
``` 
$ cp .env.example .env
$ php artisan key:generate 
```
 
* Step 5:
Finally, build the application with
```$ npm run dev```
and launch the server with
```$ php artisan serve```.

The web application will be available at http://127.0.0.1:8000/
