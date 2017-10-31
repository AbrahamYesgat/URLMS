<?php
foreach(glob(dirname(__FILE__) . '/model/*.php') as $file){
    require_once $file;
}

require_once dirname(__FILE__) . '/controller/Controller.php';
require_once dirname(__FILE__) . '/persistence/PersistenceManager.php';

$urlms = PersistenceManager::loadDataFromStore();
$controller = new Controller($urlms);

if ($_SESSION['activeUser'] != null) {
    if ($controller->logout()) {
        session_destroy();
    }
}
?>