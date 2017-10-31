<?php
foreach(glob(dirname(__FILE__) . '/model/*.php') as $file){
    require_once $file;
}

require_once dirname(__FILE__) . '/controller/Controller.php';
require_once dirname(__FILE__) . '/persistence/PersistenceManager.php';

$urlms = $_SESSION['urlms'];
$controller = $_SESSION['controller'];

if (isset($_GET['email']) && isset($_GET['password'])) {
    if ($controller->login($_GET['email'], $_GET['password'])) {
        $_SESSION['logged_in'] = true;
        $_SESSION['activeUser'] = $controller->getActiveUser();
    }
}
?>