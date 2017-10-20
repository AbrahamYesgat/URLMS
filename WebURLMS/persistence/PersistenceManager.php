<?php
class PersistenceManager {
    private $filename;
    
    function __construct($filename = 'data.txt') {
        $this->filename = $filename;
    }
    
    function loadDataFromStore() {
        if (file_exists($this->filename)) {
            $str = file_get_contents($this->filename);
            $rm = unserialize($str);
        } else {
            $rm = URLMS::getInstance();
        }
        
        return $rm;
    }
    
    function writeDataToStore($urlms) {
        $str = serialize($urlms);
        file_put_contents($this->filename, $str);
    }
}
?>