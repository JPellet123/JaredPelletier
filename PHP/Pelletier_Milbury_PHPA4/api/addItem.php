<?php
require_once dirname(__DIR__, 1) . '/db/CarAccessor.php';
require_once dirname(__DIR__, 1) . '/db/ConnectionManager.php';
require_once dirname(__DIR__, 1) . '/entity/Car.php';
require_once dirname(__DIR__, 1) . '/utils/Constants.php';
// reading the HTTP request body
$body = file_get_contents('php://input');
$contents = json_decode($body, true);

$carID = $contents['carID'];
$carColor = $contents['carColor'];
$make = $contents['carMake'];
$model = $contents['carModel'];
$price = $contents['carPrice'];

// create a car object
$CarObj = new Car($carID, $carColor, $make, $model, $price);
//var_dump($CarObj);
// add the object to DB
try {
    $cm = new ConnectionManager(Constants::$MYSQL_CONNECTION_STRING, Constants::$MYSQL_USERNAME, Constants::$MYSQL_PASSWORD);
    $ca = new CarAccessor($cm->getConnection());
    $success = $ca->insertCar($CarObj);
    echo $success ? 1 : 0;
} catch (Exception $e) {
    echo "ERROR " . $e->getMessage();
}
