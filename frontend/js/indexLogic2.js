
// current date
const date = new Date();
let day = date.getDate();
day = day.toString();
if(date.length == 1){
    day = '0'+day;
}
let month = date.getMonth() + 1;
month = month.toString();
if(month.length == 1){
    month = '0'+month;
}
let year = date.getFullYear();
let currentDate = `${day}-${month}-${year}`;
console.log(currentDate);

// get free parking slot count
let getDBData = async () => {

    // getting number of available parking slots
    try {
        const resp1 = await fetch('http://localhost:8080/park_vision/parking-slots/2');
        const availTwo = await resp1.json();
        countEmpty2 = availTwo.length;
        console.log("countEmpty2: " + countEmpty2);
        twoWheeler = document.getElementById('empty_slots_2');
        twoWheeler.textContent = countEmpty2;
        if (!resp1.ok) {
            console.log(availTwo.description);
        }
    }
    catch (error) {
        console.log(error);
    }
    try {
        const resp2 = await fetch('http://localhost:8080/park_vision/parking-slots/4');
        const availFour = await resp2.json();
        countEmpty4 = availFour.length;
        console.log("countEmpty4: " + countEmpty4);
        fourWheeler = document.getElementById('empty_slots_4');
        fourWheeler.textContent = countEmpty4;
        if (!resp2.ok) {
            console.log(availFour.description);
        }
    }
    catch (error) {
        console.log(error);
    }

    // getting occupied slots
    let table = document.querySelector('.table');
    console.log(table);
    console.log("getOccupancyData");
    const tableBody = table.querySelector('tbody');

    try {
        const resp = await fetch('http://localhost:8080/park_vision/parking-history/occupied-5');
        const data = await resp.json();
        console.log(data);
        console.log("data: ", typeof (data));

        //populate the rows
        for (const rows of data) {
            console.log("rows: ", typeof (rows));
            console.log("inside rows: ", rows);
            const rowArray = Object.values(rows);
            const rowElem = document.createElement('tr');
            for (const colText of rowArray) {
                console.log("colText: ", typeof (colText));
                const colElem = document.createElement('td');
                colElem.textContent = colText;
                rowElem.appendChild(colElem);
            }
            tableBody.appendChild(rowElem);
        }
    } catch (error) {
        console.log(error);
    }

    // get total revenue
    let total_amt = document.getElementById('sum_amt');
    console.log("getRevenue");
    try{
        const resp4 = await fetch(`http://localhost:8080/park_vision/invoice/${currentDate}`);
        const amount = await resp4.json();
        let amount2 = amount;
        // console.log("amount: ",amount[0]);
        if(amount2 == ""){
            amount2 = 0;
        }
        total_amt.textContent = amount2;
        if(!resp4.ok){
            console.log(amount.description);
        }
    }
    catch(error){
        console.log(error)
    }
}