<?php
use PHPUnit\Framework\TestCase;

foreach(glob(dirname(__FILE__) . '/../model/*.php') as $file){
    require_once $file;
}

require_once dirname(__FILE__) . '/../controller/Controller.php';

class LoginTest extends TestCase
{
    private $pm;
    private $urlms;
    private $controller;
    
    // Set values for test cases
    private $testEmail ="director@urlms.ca";
    private $testPassword ="password";
    private $testName ="Director";
    
    private $testStaffEmail ="staff@urlms.ca";
    private $testStaffPassword ="password1";
    private $testStaffName ="Member";
    
    public function setUp()
    {
        $this->urlms = URLMS::getInstance();
        $this->controller = new Controller($this->urlms);
    }
    
    public function tearDown()
    {
        $this->urlms->delete();
    }
    
    public function testLogin()
    {
        //Create director
        $this->controller->createDirector($this->testEmail, $this->testPassword, $this->testName);
        
        // Tests if the director login works
        $this->assertEquals(true, $this->controller->login($this->testEmail, $this->testPassword));
        $this->assertEquals(false, $this->controller->login("random", "random"));
        
        $this->controller->addLaboratory("name", "study", new Datetime("now"));
        $this->controller->addStaff($this->testStaffName, $this->testStaffEmail, $this->testStaffPassword, StaffRole::ResearchAssistant);
        
        // Tests if the director and a staff member can login
        $this->assertEquals(true, $this->controller->login($this->testEmail, $this->testPassword));
        $this->assertEquals(true, $this->controller->login($this->testStaffEmail, $this->testStaffPassword));
        $this->assertEquals(false, $this->controller->login("not", "right"));
    }
}
?>