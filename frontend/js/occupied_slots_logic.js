
// const date = new Date();
// let day = date.getDate();
// let month = date.getMonth() + 1;
// let year = date.getFullYear();
// let currentDate = `${day}-${month}-${year}`;
// document.getElementById('currentDate').textContent = currentDate;
// const serverURL = 'http://localhost:8080/park_vision/'
const serverURL = 'http://localhost:8080/park_genie/'
let table = document.querySelector('.table');
console.log(table);

let getOccupancyData = async () =>{
    console.log("getOccupancyData");
    const tableBody = table.querySelector('tbody');

    try {
        const resp = await fetch(`${serverURL}parking-history/occupied`);
        const data = await resp.json();
        console.log(data);
        console.log("data: ",typeof(data));

        //populate the rows
        for(const rows of data){
            console.log("rows: ",typeof(rows));
            console.log("inside rows: ",rows);
            const rowArray = Object.values(rows);
            const rowElem = document.createElement('tr');
            for(const colText of rowArray){
                console.log("colText: ",typeof(colText));
                const colElem = document.createElement('td');
                colElem.textContent = colText;
                rowElem.appendChild(colElem);
            }
            tableBody.appendChild(rowElem);
        }
    }catch(error){
        console.log(error);
    }
}

// funtion for search table
const showRecord = ()=> {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("occupied");
    tr = table.getElementsByTagName("tr");
  
    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
      td = tr[i].getElementsByTagName("td")[0];
      if (td) {
        txtValue = td.textContent || td.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
          tr[i].style.display = "";
        } 
        else {
          tr[i].style.display = "none";
        }
      }
    }
  }

