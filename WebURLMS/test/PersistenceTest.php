<?php
foreach(glob(dirname(__FILE__) . '/../model/*.php') as $file){
    require_once $file;
}

require_once dirname(__FILE__) . '/../controller/Controller.php';

class PersistenceTest extends PHPUnit\Framework\TestCase
{
    private $pm;
    private $urlms;
    
    private $testEmail ="director@urlms.ca";
    private $testStaffEmail ="staff@urlms.ca";
    private $testPassword ="password";
    private $testDirName ="Director";
    private $testStaffName ="Staff";
    
    public function setUp()
    {
        $this->pm = new PersistenceManager();
        
        $this->urlms = URLMS::getInstance();
        
        // Create participants
        $dr = new Director($this->testEmail, $this->testPassword, $this->testDirName, $this->urlms);
        $lab = $this->urlms->addLaboratoryVia("LabOne", "Test", new DateTime("now"), true, $dr);
        $staffMember = new Staff($this->testStaffEmail, $this->testPassword, $this->testStaffName, StaffRole::ResearchAssistant, [$lab]);
        // Create data file
        $this->pm->writeDataToStore($this->urlms);
    }
    
    public function tearDown()
    {
        $this->urlms->delete();
    }
    
    public function testPersistence()
    {
        //Delete current model
        $this->urlms->delete();

        //Assure that nothing is there
        $this->assertEquals(false, $this->urlms->hasDirectors());
        $this->assertEquals(false, $this->urlms->hasLaboratories());
        
        // Load model
        $this->urlms = $this->pm->loadDataFromStore();
            
        // Check participants (Staff and director)
        $this->assertEquals(true, $this->urlms->hasDirectors());
        $this->assertEquals(true, $this->urlms->hasLaboratories());
        $this->assertEquals(true, $this->urlms->getLaboratory_index(0)->hasStaffs());
        $this->assertEquals($this->testEmail, $this->urlms->getDirector_index(0)->getEmail());
        $this->assertEquals($this->testPassword, $this->urlms->getDirector_index(0)->getPassword());
        $this->assertEquals($this->testDirName, $this->urlms->getDirector_index(0)->getName());
        $this->assertEquals($this->testStaffEmail, $this->urlms->getLaboratory_index(0)->getStaff_index(0)->getEmail());
        $this->assertEquals($this->testPassword, $this->urlms->getLaboratory_index(0)->getStaff_index(0)->getPassword());
        $this->assertEquals($this->testStaffName, $this->urlms->getLaboratory_index(0)->getStaff_index(0)->getName());
    }
    
}
?>