<?php
require_once dirname(__DIR__, 1) . '/db/CarAccessor.php';
require_once dirname(__DIR__, 1) . '/db/ConnectionManager.php';
require_once dirname(__DIR__, 1) . '/entity/Car.php';
require_once dirname(__DIR__, 1) . '/utils/Constants.php';

// passed as URL parameter
$id = intval($_GET['carID']);
// create a dummy car object - only ID matters
$carObj = new Car($id, "fakeYellow", "honda", "corolla", 0);
// delete from DB
try {
    $cm = new ConnectionManager(Constants::$MYSQL_CONNECTION_STRING, Constants::$MYSQL_USERNAME, Constants::$MYSQL_PASSWORD);
    $ca = new CarAccessor($cm->getConnection());
    $success = $ca->deleteCar($carObj);
    echo $success ? 1 : 0;
} catch (Exception $e) {
    echo "ERROR " . $e->getMessage();
}
