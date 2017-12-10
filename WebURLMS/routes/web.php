<?php
/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

/*
Route::get('/', function () {
    return view('welcome');
});
*/

/*
 * Getting started using Vue's Vue-router for single page apps
 * Matt Stauffer
 * https://mattstauffer.co/blog/getting-started-using-vues-vue-router-for-single-page-apps/
 *
 * If you're using this app within a Laravel app, instead of configuring nginx or Apache
 * to handle hashbang-less push state, you could configure your Laravel app to handle it;
 * just set up a capture route that grabs all valid URLs and passes them the view that's
 * outputting your Vue code.
 *
 */

//Autentification
Route::post('/login', 'URLMS\AuthenticationController@login');
Route::get('/logout', 'URLMS\AuthenticationController@logout');
Route::post('/register', 'URLMS\AuthenticationController@register');

Route::group(['middleware' => 'auth:api'], function() {
	//Director only pages
	Route::group(['middleware' => 'role:Director'], function() {
		//Labs
		Route::post('/labs/add', 'URLMS\MainController@addLab');
		Route::get('/labs/clear', 'URLMS\MainController@clearLabs');
		Route::post('/labs/delete', 'URLMS\MainController@removeLab');
		Route::post('/labs/updateLab', 'URLMS\MainController@updateLab');
		
		//Staff
		Route::get('/staff/get', 'URLMS\MainController@getStaff');
		Route::post('/staff/add', 'URLMS\MainController@addStaff');
		Route::get('/staff/clear', 'URLMS\MainController@clearStaff');
		Route::post('/staff/delete', 'URLMS\MainController@removeStaff');
		Route::post('/staff/modify', 'URLMS\MainController@modifyStaff');
		
		//Expenses
		Route::get('/expenses/get', 'URLMS\MainController@getExpenses');
		
		//Funding accounts
		Route::get('/fundings/get', 'URLMS\MainController@getFundingAccounts');
		Route::post('/fundings/add', 'URLMS\MainController@addFundingAccount');
		Route::post('/fundings/modify', 'URLMS\MainController@modifyFundingAccount');
		Route::post('/fundings/delete', 'URLMS\MainController@removeFundingAccount');
	});
	
	//Staff only pages
	Route::group(['middleware' => 'role:Staff'], function() {
		//Weekly progress
		Route::post('/progress/add', 'URLMS\MainController@addWeeklyProgress');
	});
	
	//Labs
	Route::get('/labs/get', 'URLMS\MainController@getLabs');
	Route::post('/labs/enter', 'URLMS\MainController@enterLab');
	Route::get('/labs/info', 'URLMS\ProfileInfoController@getCurrentLab');

	//User info
	Route::get('/user/info', 'URLMS\ProfileInfoController@getCurrentUser');
	Route::post('/user/updatePassword', 'URLMS\ProfileInfoController@updatePassword');
	Route::post('/user/updateProfile', 'URLMS\ProfileInfoController@updateProfile');
	
	//Expenses
	Route::post('/expenses/add', 'URLMS\MainController@addExpenses');
	
	//Equipment
	Route::get('/equipment/get', 'URLMS\MainController@getEquipment');
	Route::get('/equipment/clear', 'URLMS\MainController@clearEquipment');
	Route::post('/equipment/add', 'URLMS\MainController@addEquipment');
	Route::post('/equipment/modify', 'URLMS\MainController@modifyEquipment');
	Route::post('/equipment/delete', 'URLMS\MainController@removeEquipment');
	
	//Supplies
	Route::get('/supplies/get', 'URLMS\MainController@getSupplies');
	Route::get('/supplies/clear', 'URLMS\MainController@clearSupplies');
	Route::post('/supplies/add', 'URLMS\MainController@addSupplies');
	Route::post('/supplies/modify', 'URLMS\MainController@modifySupplies');
	Route::post('/supplies/delete', 'URLMS\MainController@removeSupplies');
	
	//Weekly progress
	Route::get('/progress/get', 'URLMS\MainController@getWeeklyProgress');
});

Route::group(['middleware' => 'auth:coreui'], function() {
	Route::get('/{vue_capture?}', function () {
	    return view('coreui');
	})->where('vue_capture', '[\/\w\.-]*');
});
