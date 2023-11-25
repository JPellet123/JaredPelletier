<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Assignment 4</title>

    <link rel="stylesheet" href="mainStyleSheet.css">
    <script src="main.js"></script>
</head>

<body>
    <div id="content" class="container">
        <h1>Assignment 4</h1>

        <button id="LoadButton" class="btn-primary">Load Items</button>
        <div id="ButtonPanel">
            <button id="AddButton" class="btn-primary">Add</button>
            <button id="DeleteButton" class="btn-primary" disabled>
                Delete
            </button>
            <button id="UpdateButton" class="btn-primary" disabled>
                Update
            </button>
        </div>

        <div id="AddUpdatePanel" class="hidden">
            <div class="inputContainer">
                <div class="inputLabel">Car ID:</div>
                <div class="inputField">
                    <input id="carID" name="carID" type="number" min="1" max="100" />
                </div>
            </div>

            <div class="inputContainer">
                <div class="inputLabel">Car Color:</div>
                <div class="inputField">
                    <input id="carColor" name="carColor" />
                </div>
            </div>
            <div class="inputContainer">
                <div class="inputLabel">Car Make:</div>
                <div class="inputField">
                    <input id="carMake" name="carMake" />
                </div>
            </div>
            <div class="inputContainer">
                <div class="inputLabel">Car Model:</div>
                <div class="inputField">
                    <input id="carModel" name="carModel" />
                </div>
            </div>
            <div class="inputContainer">
                <div class="inputLabel">Price:</div>
                <div class="inputField">
                    <input id="price" type="number" name="price" min="0" />
                </div>
            </div>

            <div class="inputContainer">
                <div class="inputLabel">&nbsp;</div>
                <div class="inputField">
                    <button id="DoneButton" class="btn-primary">
                        Done
                    </button>
                    <button id="CancelButton" class="btn-primary">
                        Cancel
                    </button>
                </div>
            </div>
        </div>

        <div id="CarTable">
            <!-- to be filled in by JS -->
        </div>
    </div>
</body>

</html>