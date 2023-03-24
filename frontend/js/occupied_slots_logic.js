
// const date = new Date();
// let day = date.getDate();
// let month = date.getMonth() + 1;
// let year = date.getFullYear();
// let currentDate = `${day}-${month}-${year}`;
// document.getElementById('currentDate').textContent = currentDate;
let table = document.querySelector('.table');
console.log(table);

let getOccupancyData = async () =>{
    console.log("getOccupancyData");
    const tableBody = table.querySelector('tbody');

    try {
        const resp = await fetch('http://localhost:8080/park_vision/parking-history/occupied');
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

