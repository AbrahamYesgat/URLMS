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
	/*
	 * Save model to file
	 * @param $urlms URLMS model
	 */
	public static function saveModel($urlms) {
		$str = serialize ( $urlms );
		// Private folder not accessible through HTTP
		Storage::put ( PersistenceController::$persistanceFilename, $str, 'private' );
	}
	/**
	 * Load model from file
	 * 
	 * @return URLMS
	 */
	public static function loadModel() {
		if (Storage::exists ( PersistenceController::$persistanceFilename )) {
			$str = Storage::get ( PersistenceController::$persistanceFilename );
			$rm = unserialize ( $str );
		} else {
			$rm = URLMS::getInstance ();
		}
		
		return $rm;
	}
	/**
	 * Clear model and save to file
	 */
	public static function clearModel() {
		URLMS::getInstance ()->delete ();
		PersistenceController::saveModel ( URLMS::getInstance () );
	}
	/**
	 * Set persistence filename
	 * 
	 * @param $filename filename
	 */
	public static function setFilename($filename) {
    		PersistenceController::$persistanceFilename = $filename;	
    }
}