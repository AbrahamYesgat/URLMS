<?php

namespace Tests\Unit;

use Tests\TestCase;
use Illuminate\Foundation\Testing\RefreshDatabase;
use App\Http\Controllers\URLMS\PersistenceController;

class PersistenceTest extends TestCase
{
    /**
     * A basic test example.
     *
     * @return void
     */
    public function testExample()
    {
    		$dirEmail = 'test@email.com';
    		$dirPassword = 'test';
    		$dirName = 'dir';
    	
    		//Change persistence file to not mess with current data
    		PersistenceController::setFilename('test');
    		PersistenceController::clearModel();
    		
    		$urlms = PersistenceController::loadModel();
    		
    		//Asserts empty URLMS
        $this->assertTrue(count($urlms->getDirectors()) == 0);
        $this->assertTrue(count($urlms->getLaboratories()) == 0);
        
        //Add object to test
        $urlms->addDirectorVia($dirEmail, $dirPassword, $dirName);
        
        PersistenceController::saveModel($urlms);
        
        //Delete model in memory
        $urlms->delete();
        
        //Restore from persistence
        $urlms = PersistenceController::loadModel();
        
        //Assert object is there
        $this->assertTrue($urlms->getDirector_index(0)->getName() == $dirName);
        $this->assertTrue($urlms->getDirector_index(0)->getPassword() == $dirPassword);
        $this->assertTrue($urlms->getDirector_index(0)->getEmail() == $dirEmail);
    }
}
