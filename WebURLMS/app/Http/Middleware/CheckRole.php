<?php

namespace App\Http\Middleware;

use Closure;

class CheckRole
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @param  $role Role of logged-in user
     * @return mixed
     */
    public function handle($request, Closure $next, $role)
    {
    		//Director-only page
    		if($role == 'Director') {
    			//Redirect homepage if staff
    			if(!$request->session ()->get('director')) {
    				return redirect('/');
    			}
    		}
    		
    		//Staff-only page
    		else if($role == 'Staff') {
    			//Redirect homepage if director
    			if($request->session ()->get('director')) {
    				return redirect('/');
    			}
    		}
    	
        return $next($request);
    }
}
