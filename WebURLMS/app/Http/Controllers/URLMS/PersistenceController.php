<?php

namespace App\Http\Controllers\URLMS;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Storage;
use App\Http\Controllers\URLMS\Model\URLMS as URLMS;

/**
 * Class for managing model persistence
 */
class PersistenceController extends Controller {
	private static $persistanceFilename = 'data';
	public static function saveModel($urlms) {
		$str = serialize ( $urlms );
		//Private folder not accessible through HTTP
		Storage::put ( PersistenceController::$persistanceFilename, $str, 'private' );
	}
	public static function loadModel() {
		if (Storage::exists ( PersistenceController::$persistanceFilename )) {
			$str = Storage::get ( PersistenceController::$persistanceFilename );
			$rm = unserialize ( $str );
		} else {
			$rm = URLMS::getInstance ();
		}
		
		return $rm;
	}
	public static function clearModel() {
        URLMS::delete();
        PersistenceController::saveModel(URLMS::getInstance());
    }
}