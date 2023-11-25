let addOrUpdate; // to track whether we're doing an add or an update

window.onload = function () {
  // add event handlers for buttons
  document.querySelector("#AddButton").addEventListener("click", addItem);
  document.querySelector("#DeleteButton").addEventListener("click", deleteItem);
  document.querySelector("#UpdateButton").addEventListener("click", updateItem);
  document.querySelector("#DoneButton").addEventListener("click", processForm);
  document
    .querySelector("#CancelButton")
    .addEventListener("click", hideUpdatePanel);

  // add event handler for selections on the table
  document.querySelector("#CarTable").addEventListener("click", handleRowClick);

  document.querySelector("#LoadButton").addEventListener("click", getAllItems);

  hideUpdatePanel();
};

// make AJAX call to get JSON data
function getAllItems() {
  let url = "api/getAllItems.php"; // file name or server-side process name
  let xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function () {
    if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) {
        let resp = xhr.responseText;
        console.log(resp);
        if (resp.search("ERROR") >= 0) {
          alert("oh no, something is wrong with the GET ...");
        } else {
          buildTable(xhr.responseText);
          setDeleteUpdateButtonState(false);
        }
      } else {
        alert("received status code " + xhr.status);
      }
    }
  };
  xhr.open("GET", url, true);
  xhr.send();
}

// text is a JSON string containing an array
function buildTable(text) {
  let arr = JSON.parse(text); // get JS Objects
  let html =
    "<table><tr><th>ID</th><th>Color</th><th>Make</th><th>Model</th><th>Price</th></tr>";
  for (let i = 0; i < arr.length; i++) {
    let row = arr[i];
    html += "<tr>";
    html += "<td>" + row.carID + "</td>";
    html += "<td>" + row.carColor + "</td>";
    html += "<td>" + row.make + "</td>";
    html += "<td>" + row.model + "</td>";
    html += "<td>" + row.price + "</td>";
    html += "</tr>";
  }
  html += "</table>";
  let theTable = document.querySelector("#CarTable");
  theTable.innerHTML = html;
}

function addItem() {
  addOrUpdate = "add";
  clearUpdatePanel();
  showUpdatePanel();
}

function updateItem() {
  addOrUpdate = "update";
  populateUpdatePanel();
  showUpdatePanel();
}

function deleteItem() {
  let row = document.querySelector(".selected"); // we know there's only one
  let id = Number(row.querySelectorAll("td")[0].innerHTML);

  // AJAX

  let url = "api/deleteItem.php/?carID=" + id; // "?param=value"
  let xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      let resp = xhr.responseText;
      if (resp === "1") {
        alert("Item deleted.");
      } else if (resp === "0") {
        alert("Item was not deleted.");
      } else {
        alert("Server Error!");
        //console.log(xhr.responseText);
      }
      getAllItems();
    }
  };
  xhr.open("GET", url, true); // will improve in REST version
  xhr.send();
}

// Called when "Done" button is pressed for either Add or Update
function processForm() {
  console.log("OK");
  // Get data from the form and build an object.
  let id = Number(document.querySelector("#carID").value);
  let color = document.querySelector("#carColor").value;
  let make = document.querySelector("#carMake").value;
  let model = document.querySelector("#carModel").value;
  let price = Number(document.querySelector("#price").value);

  let obj = {
    carID: id,
    carColor: color,
    carMake: make,
    carModel: model,
    carPrice: price,
  };

  // Make AJAX call to add or update the record in the database.
  let url = addOrUpdate === "add" ? "api/addItem.php" : "api/updateItem.php";
  let xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      let resp = xhr.responseText;
      console.log(resp, resp.length);
      if (resp === "1") {
        alert("Item " + (addOrUpdate === "add" ? "added." : "updated."));
      } else if (resp === "0") {
        alert("Item NOT " + (addOrUpdate === "add" ? "added." : "updated."));
      } else {
        alert("Server Error!");
      }
      hideUpdatePanel();
      getAllItems();
    }
  };
  xhr.open("POST", url, true); // must be POST because we need to send data
  xhr.send(JSON.stringify(obj));
}

function setIDFieldState(val) {
  let idInput = document.querySelector("#itemIDInput");
  if (val) {
    idInput.removeAttribute("disabled");
  } else {
    idInput.setAttribute("disabled", "disabled");
  }
}

function hideUpdatePanel() {
  document.querySelector("#AddUpdatePanel").classList.add("hidden");
}

function showUpdatePanel() {
  document.querySelector("#AddUpdatePanel").classList.remove("hidden");
}

function clearUpdatePanel() {
  document.querySelector("#carID").value = "";
  document.querySelector("#carColor").value = "";
  document.querySelector("#carMake").value = "";
  document.querySelector("#carModel").value = "";
  document.querySelector("#price").value = "";
}

function populateUpdatePanel() {
  let selectedItem = document.querySelector(".selected");
  let carID = Number(selectedItem.querySelector("td:nth-child(1)").innerHTML);
  let carColor = selectedItem.querySelector("td:nth-child(2)").innerHTML;
  let carMake = selectedItem.querySelector("td:nth-child(3)").innerHTML;
  let carModel = selectedItem.querySelector("td:nth-child(4)").innerHTML;
  let price = Number(selectedItem.querySelector("td:nth-child(5)").innerHTML);

  document.querySelector("#carID").value = carID;
  document.querySelector("#carColor").value = carColor;
  document.querySelector("#carMake").value = carMake;
  document.querySelector("#price").value = price;
  document.querySelector("#carModel").value = carModel;
}

function setDeleteUpdateButtonState(state) {
  if (state) {
    document.querySelector("#DeleteButton").removeAttribute("disabled");
    document.querySelector("#UpdateButton").removeAttribute("disabled");
  } else {
    document
      .querySelector("#DeleteButton")
      .setAttribute("disabled", "disabled");
    document
      .querySelector("#UpdateButton")
      .setAttribute("disabled", "disabled");
  }
}

function handleRowClick(evt) {
  clearSelections();
  evt.target.parentElement.classList.add("selected");

  setDeleteUpdateButtonState(true);
}

function clearSelections() {
  let trs = document.querySelectorAll("tr");
  for (let i = 0; i < trs.length; i++) {
    trs[i].classList.remove("selected");
  }
}
