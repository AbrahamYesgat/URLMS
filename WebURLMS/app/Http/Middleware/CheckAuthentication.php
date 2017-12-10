<?php

namespace App\Http\Middleware;

use Closure;

class CheckAuthentication
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle($request, Closure $next, $param)
    {
    		$routeName = $request->getRequestUri();
    	
    		if($routeName != null) {
    			if($param == 'api') {
		    		if(!$request->session ()->get ( 'logged-in' )) {
		    			return redirect('/login');
		    		}
    			}
    			
    			else if($param == 'coreui') {
    				$prefixDir = explode('/', $routeName);
    				
    				if($prefixDir[1] != 'js' && $prefixDir[1] != 'css') {
    					if($prefixDir[1] != 'login' && $prefixDir[1] != 'register') {
	    					if(!$request->session ()->get ( 'logged-in' )) {
	    						return redirect('/login');
	    					}
	    				}
    				}
    			}
    		}
    		
        return $next($request);
    }
}
