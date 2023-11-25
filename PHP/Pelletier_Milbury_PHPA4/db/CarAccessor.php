<?php
require_once dirname(__DIR__, 1) . '/entity/Car.php';

class CarAccessor
{
    private $getAllStatementString = "select * from cars";
    private $getByIDStatementString = "select * from cars where carID = :carID";
    private $deleteStatementString = "delete from cars where carID = :carID";
    private $insertStatementString = "insert into cars values (:carID, :carColor, :make, :model, :price)";
    private $updateStatementString = "update cars set carColor = :carColor, make = :make, model = :model, price = :price WHERE carID = :carID";

    private $getAllStatement = null;
    private $getByIDStatement = null;
    private $deleteStatement = null;
    private $insertStatement = null;
    private $updateStatement = null;

    /**
     * Creates a new instance of the accessor with the supplied database connection.
     * 
     * @param PDO $conn - a database connection
     */
    public function __construct($conn)
    {
        if (is_null($conn)) {
            throw new Exception("no connection");
        }

        $this->getAllStatement = $conn->prepare($this->getAllStatementString);
        if (is_null($this->getAllStatement)) {
            throw new Exception("bad statement: '" . $this->getAllStatementString . "'");
        }

        $this->getByIDStatement = $conn->prepare($this->getByIDStatementString);
        if (is_null($this->getByIDStatement)) {
            throw new Exception("bad statement: '" . $this->getByIDStatementString . "'");
        }

        $this->deleteStatement = $conn->prepare($this->deleteStatementString);
        if (is_null($this->deleteStatement)) {
            throw new Exception("bad statement: '" . $this->deleteStatementString . "'");
        }

        $this->insertStatement = $conn->prepare($this->insertStatementString);
        if (is_null($this->insertStatement)) {
            throw new Exception("bad statement: '" . $this->getAllStatementString . "'");
        }

        $this->updateStatement = $conn->prepare($this->updateStatementString);
        if (is_null($this->updateStatement)) {
            throw new Exception("bad statement: '" . $this->updateStatementString . "'");
        }
    }

    /**
     * Gets all of the cars.
     * 
     * @return Cars[] array of Car objects
     */
    public function getAllcars()
    {
        $results = [];

        try {
            $this->getAllStatement->execute();
            $dbresults = $this->getAllStatement->fetchAll(PDO::FETCH_ASSOC);

            foreach ($dbresults as $r) {
                $carID = $r['carID'];
                $carColor = $r['carColor'];
                $make = $r['make'];
                $model = $r['model'];
                $price = $r['price'];
                $obj = new Car($carID, $carColor, $make, $model, $price);
                array_push($results, $obj);
            }
        } catch (Exception $e) {
            $results = [];
        } finally {
            if (!is_null($this->getAllStatement)) {
                $this->getAllStatement->closeCursor();
            }
        }

        return $results;
    }

    /**
     * Gets the car with the specified ID.
     * 
     * @param Integer $id the ID of the car to retrieve 
     * @return Car car object with the specified ID, or NULL if not found
     */
    private function getCarByID($id)
    {
        $result = null;

        try {
            $this->getByIDStatement->bindParam(":carID", $id);
            $this->getByIDStatement->execute();
            $dbresults = $this->getByIDStatement->fetch(PDO::FETCH_ASSOC); // not fetchAll

            if ($dbresults) {
                $carID = $dbresults['carID'];
                $carColor = $dbresults['carColor'];
                $make = $dbresults['make'];
                $model = $dbresults['model'];
                $price = $dbresults['price'];
                $result = new Car($carID, $carColor, $make, $model, $price);
            }
        } catch (Exception $e) {
            $result = null;
        } finally {
            if (!is_null($this->getByIDStatement)) {
                $this->getByIDStatement->closeCursor();
            }
        }

        return $result;
    }

    /**
     * Does an car exist (with the same ID)?
     * 
     * @param Car $car the car to check
     * @return boolean true if the car exists; false if not
     */
    public function carExists($car)
    {
        return $this->getCarByID($car->getCarID()) !== null;
    }

    /**
     * Deletes a car.
     * 
     * @param Car $car an object whose ID is EQUAL TO the ID of the car to delete
     * @return boolean indicates whether the car was deleted
     */
    public function deletecar($car)
    {
        if (!$this->carExists($car)) {
            return false;
        }

        $success = false;
        $carID = $car->getCarID(); // only the ID is needed

        try {
            $this->deleteStatement->bindParam(":carID", $carID);
            $success = $this->deleteStatement->execute(); // this doesn't mean what you think it means
            $success = $success && $this->deleteStatement->rowCount() === 1;
        } catch (PDOException $e) {
            var_dump($e);
            $success = false;
        } finally {
            if (!is_null($this->deleteStatement)) {
                $this->deleteStatement->closeCursor();
            }
        }
        return $success;
    }

    /**
     * Inserts a car into the database.
     * 
     * @param Car $car an object of type car
     * @return boolean indicates if the car was inserted
     */
    public function insertCar($car)
    {
        if ($this->carExists($car)) {
            return false;
        }

        $success = false;

        $carID = $car->getCarID();
        //var_dump($carID);
        $carColor = $car->getCarColor();
        $make = $car->getCarMake();
        $model = $car->getCarModel();
        $price = $car->getPrice();
        // var_dump($carColor);
        // var_dump($make);
        // var_dump($model);
        // var_dump($price);
        try {
            $this->insertStatement->bindParam(":carID", $carID);
            $this->insertStatement->bindParam(":carColor", $carColor);
            $this->insertStatement->bindParam(":make", $make);
            $this->insertStatement->bindParam(":model", $model);
            $this->insertStatement->bindParam(":price", $price);
            $success = $this->insertStatement->execute(); // this doesn't mean what you think it means
            $success = $this->insertStatement->rowCount() === 1;
        } catch (PDOException $e) {
            var_dump($e);
            $success = false;
        } finally {
            if (!is_null($this->insertStatement)) {
                $this->insertStatement->closeCursor();
            }
        }
        return $success;
    }

    /**
     * Updates a car in the database.
     * 
     * @param Car $car an object of type Car, the new values to replace the database's current values
     * @return boolean indicates if the car was updated
     */
    public function updateCar($car)
    {
        if (!$this->carExists($car)) {
            return false;
        }

        $success = false;

        $carID = $car->getCarID();
        $carColor = $car->getCarColor();
        $make = $car->getCarMake();
        $model = $car->getCarModel();
        $price = $car->getPrice();

        try {
            $this->updateStatement->bindParam(":carID", $carID);
            $this->updateStatement->bindParam(":carColor", $carColor);
            $this->updateStatement->bindParam(":make", $make);
            $this->updateStatement->bindParam(":model", $model);
            $this->updateStatement->bindParam(":price", $price);
            $success = $this->updateStatement->execute(); // this doesn't mean what you think it means
            $success = $this->updateStatement->rowCount() === 1;
        } catch (PDOException $e) {
            $success = false;
        } finally {
            if (!is_null($this->updateStatement)) {
                $this->updateStatement->closeCursor();
            }
        }
        return $success;
    }
}
// end class CarAccessor
