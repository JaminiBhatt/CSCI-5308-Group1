
function showAddRole() {
  var x = document.getElementById("AddRole");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}

function showViewRole() {
  var x = document.getElementById("ViewRole");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}

window.onload = function() {
  document.getElementById('ViewRole').style.display = 'none';
    document.getElementById('AddRole').style.display = 'none';

};
