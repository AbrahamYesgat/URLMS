<?php
class PersistenceManager {
    private static $filename = 'data.txt';
    
    public static function setFilename($filename) {
        PersistenceManager::$filename = $filename;
    }
    
    public static function loadDataFromStore() {
        if (file_exists('/' . PersistenceManager::$filename)) {
            $str = file_get_contents('/' . PersistenceManager::$filename);
            $rm = unserialize($str);
        } else {
            $rm = URLMS::getInstance();
        }
        
        return $rm;
    }
    
    public static function writeDataToStore($urlms) {
        $str = serialize($urlms);
        file_put_contents('/' . PersistenceManager::$filename, $str);
    }
}
?>