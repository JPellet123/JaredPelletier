<?php

class Car implements JsonSerializable
{
    private $carID;
    private $carColor;
    private $make;
    private $price;
    private $model;

    public function __construct($carID, $carColor, $make, $model, $price)
    {
        $this->carID = $carID;
        $this->carColor = $carColor;
        $this->make = $make;
        $this->price = $price;
        $this->model = $model;
    }

    public function getCarID()
    {
        return $this->carID;
    }

    public function getCarColor()
    {
        return $this->carColor;
    }

    public function getCarMake()
    {
        return $this->make;
    }

    public function getPrice()
    {
        return $this->price;
    }

    public function getCarModel()
    {
        return $this->model;
    }

    public function jsonSerialize()
    {
        return get_object_vars($this);
    }
}
// end class Car