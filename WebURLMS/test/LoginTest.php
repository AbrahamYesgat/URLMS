<?php
$curr_dir = dirname(__FILE__);

foreach(glob($curr_dir . '/../model/*.php') as $file){
    require_once $file;
}

class LoginTest extends PHPUnit_Framework_TestCase
{
    protected $pm;
    protected $urlms;
    
    // Set values for test cases
    private $testEmail ="director@urlms.ca";
    private $testPassword ="password";
    private $testName ="Director";
    
    private $testStaffEmail ="staff@urlms.ca";
    private $testStaffPassword ="password1";
    private $testStaffName ="Member";
    
    protected function setUp()
    {
        $this->urlms = URLMS::getInstance();
    }
    
    protected function tearDown()
    {
        $this->urlms->delete();
    }
    
    public function testLogin()
    {
        $dr = new Director($this->testEmail, $this->testPassword, $this->testName, $this->urlms);
        $sysC = new Controller($this->urlms);
        // Tests if the director login works
        $this->assertEquals(true, $sysC->login(testEmail, testPassword));
        $this->assertEquals(false, $sysC->login("random", "random"));
        
        $lab = new Laboratory("name", "study", date("m.d.y", strtotime("2017-10-10")), true, $this->urlms, $this->dr);
        $member = new Staff($this->testStaffEmail,$this->testStaffPassword,$this->testStaffName);
        $lab->addStaff($member);
        // Tests if the director and a staff member can login
        $this->assertEquals(true, $sysC->login($this->testEmail, $this->testPassword));
        $this->assertEquals(true, $sysC->login($this->testStaffEmail, $this->testStaffPassword));
        $this->assertEquals(false, $sysC->login("not", "right"));
    }
    
}
?>