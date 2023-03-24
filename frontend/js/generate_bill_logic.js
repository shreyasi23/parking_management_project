// const style = document.createElement('style')
// style.textContent=`
// .bill_format{
//    /* margin-left:20px */
// }
// `;
// document.head.appendChild(style);
const serverURL = 'http://localhost:8080/park_vision/'
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
// document.getElementById('currentDate').textContent = currentDate;

let hrs = date.getHours()
hrs = hrs.toString();
let mins = date.getMinutes();
mins = mins.toString();
let seconds = date.getSeconds();
seconds = seconds.toString();
if(hrs.length == 1){
    hrs = "0"+hrs;
    console.log(hrs);
}
if(mins.length == 1){
    mins = "0"+mins;
    console.log(mins);
}
if(seconds.length == 1){
    seconds = "0"+seconds;
    console.log(seconds);
}
let current_time = `${hrs}:${mins}:${seconds}`;
console.log("time", typeof (current_time));
document.getElementById('exitTime').setAttribute('value',current_time)

let vehicleNo, exit_time, entry_time, park_id, slot, vehicle_Type, enter_h, enter_m, exit_m, exit_h, bill_amt, base_price, per_hr_charge, driver_name, driver_ph, for2_2, perhr_charge2, for2_4, perhr_charge4, entry_date, payment_method;

const getVehicleNo = document.querySelector('.form');
getVehicleNo.addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData(getVehicleNo);
    vehicleNo = formData.get('vehilcleNo');
    exit_time = formData.get('exitTime');
    payment_method = formData.get('paymentMethod');
    console.log("exit time", exit_time)

    // get parking area price information
    if (vehicleNo && exit_time) {
        try {
            const resp4 = await fetch("http://localhost:8080/park_vision/parking-area-info");
            const parkAreaInfo = await resp4.json();
            console.log(parkAreaInfo);
            for2_2 = parkAreaInfo[0].basePrice2;
            perhr_charge2 = parkAreaInfo[0].perHrCharge2;
            for2_4 = parkAreaInfo[0].basePrice4;
            perhr_charge4 = parkAreaInfo[0].perHrCharge4;
            if (!resp4.ok) {
                console.log(parkAreaInfo.description);
            }
        } catch (error) {
            console.log(error);
        }

        // get parking details for a particular vehicle
        try {
            const resp1 = await fetch(`http://localhost:8080/park_vision/parking-history/${vehicleNo}`);
            const parkingInfo = await resp1.json();
            console.log(parkingInfo);
            console.log(typeof (parkingInfo));
            console.log(parkingInfo[0].phID);
            park_id = parkingInfo[0].phID;
            entry_date = parkingInfo[0].entryDate;
            driver_name = parkingInfo[0].driverName;
            driver_ph = parkingInfo[0].driverPhNo
            slot = parkingInfo[0].slotNo;
            vehicle_Type = parkingInfo[0].vehicleType;
            entry_time = parkingInfo[0].entryTime;
            // const billElem = document.getElementById('bill_body');
            // billElem.innerHTML = `<h4>ABC Parking</h4><p>==========================</p><p><strong>Date: ${currentDate}</strong></p><p>Vehicle number: ${vehicleNo}</p><p>Base price: Rs. ${base_price}</p><p>Per hour charges: Rs. ${per_hr_charge}</p><p>Entry time: ${entry_time}</p><p>Exit time: ${entry_time}</p><h4>Total: Rs. ${bill_amt}</h4><p>Thank you</p>`;
            if(vehicle_Type == '2'){
                base_price = for2_2;
                per_hr_charge = perhr_charge2;
            }
            else if(vehicle_Type == '4'){
                base_price = for2_4;
                per_hr_charge = perhr_charge4;
            }
            calculateBill();
            if (!resp1.ok) {
                console.log(parkingInfo.description);
            }
        } catch (error) {
            console.log(error);
        }

        // update parking details for a particular vehicle
        const addExitTime = {
            phID: park_id,
            entryDate:entry_date,
            driverName: driver_name,
            driverPhNo: driver_ph,
            vehicleType: vehicle_Type,
            licensePlateNo: vehicleNo,
            slotNo: slot,
            entryTime: entry_time,
            exitTime: exit_time
        }
        try{
            const resp5 = await fetch("http://localhost:8080/park_vision/parking-history/exit-time",{
                method: 'PUT',
                headers:{
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(addExitTime)
            })
            const newRec = await resp5.json();
            console.log(newRec,"\nRecord updated");
            if(!resp5.ok){
                console.log(newRec.description);
            }
        }
        catch(error){
            console.log(error);
        }
    }
    else {
        showMessage("Please fill in all the fields");
     }
    


    try {
        const resp1 = await fetch(`http://localhost:8080/park_vision/parking-history/${vehicleNo}`);
        const parkingInfo = await resp1.json();
        console.log(parkingInfo);
        // console.log(typeof(parkingInfo));
        // console.log(parkingInfo[0].phID);
        park_id = parkingInfo[0].phID;
        slot = parkingInfo[0].slotNo;
        vehicle_Type = parkingInfo[0].vehicleType;
        if (!resp1.ok) {
            console.log(parkingInfo.description);
        }
    }
    catch (error) {
        console.log(error);
    }

    // update parking slot status as empty
    const updateSLot = {
        slotNo: slot,
        vehicleType: vehicle_Type,
        is_empty: true
    }
    try {
        const resp3 = await fetch(`http://localhost:8080/park_vision/parking-slots/${slot}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updateSLot)
        });
        const updateRes = await resp3.json();
        if (!resp3.ok) {
            console.log(updateRes.description);
        }
    } catch (error) {
        console.log(error);
    }
 });

// print bill
const printBill = () => {
    // const areaToPrint = document.getElementById('bill_body');
    const areaToPrint = document.getElementById('bill_box');
    const originalContent = document.body.innerHTML;
    document.body.innerHTML = areaToPrint.innerHTML;
    window.print();
    document.body.innerHTML = originalContent;
    location.reload();
}

// calculate and display bill
const calculateBill = async () => {
    let diff, extra;
    enter_h = parseInt(entry_time.substring(0, 2));
    console.log("entry hr: ",enter_h);
    exit_h = parseInt(exit_time.substring(0, 2));
    console.log("exit hr: ",exit_h);
    if (enter_h == exit_h) {
        bill_amt = base_price;
    }

    else if(enter_h > exit_h){
        diff = 0 - exit_h;
        diff = 12 + Math.abs(diff) + (12 - enter_h);
        extra = diff - 2;
        bill_amt = (per_hr_charge * extra) + base_price;
    }
   
    else{
        extra = 0;
        diff = exit_h - enter_h;
        console.log("difference: ",diff);
        diff = Math.abs(diff);
        if (diff < 2) {
            bill_amt = base_price;
        }
        else {
            extra = diff - 2;
            bill_amt = (per_hr_charge * extra) + base_price;
            // console.log("bill: Rs. ", bill_amt);
        }
    }
    const billElem = document.getElementById('bill_body');
    // billElem.innerHTML = `<h4>ABC Parking</h4><p>==========================</p><p><strong>Date: ${currentDate}</strong></p><p>Vehicle number: ${vehicleNo}</p><p>Base price: Rs. ${base_price}</p><p>Per hour charges: Rs. ${per_hr_charge}</p><p>Entry time: ${entry_time}</p><p>Exit time: ${exit_time}</p><h4><strong>Total: Rs. ${bill_amt}</strong></h4><p>Thank you</p>`;

    // billElem.innerHTML = `<h4>ABC Parking</h4><p>==========================</p><p><span class="bold">Date: ${currentDate}</span></p><p>Base price: <span class ="bill_format">Rs. ${base_price}<span></p><p>Add: <span class ="bill_format">Rs. ${per_hr_charge * extra}</span></p><p>Entry time: <span class ="bill_format">${entry_time}</span></p><p>Exit time: <span class ="bill_format">${exit_time}</span></p><h4><strong>Total: <span class ="bill_format">Rs. ${bill_amt}</span></strong></h4><p>Thank you</p>`;
//     billElem.innerHTML = `<div class="row">
//     <h5>ABC Parking</h5>
//     <p>===================</p> 
//     <h4>Date: ${currentDate}</h4>   
// </div>
// <div class="container-fluid">
//     <div class = "row">
//         <div class= "col-sm-6">
//             <p>Base Price:</p>
//             <p>Add:</p>
//             <p>Entry time:</p>
//             <p>Exit time:</p>
//             <h4><strong>Total: </strong></h4>
//         </div>
//         <div class= "col-sm-6">
//             <p>Rs. ${base_price}</p>
//             <p>Rs. ${per_hr_charge * extra}</p>
//             <p> ${entry_time}</p>
//             <p> ${exit_time}</p>
//             <h4><strong>Rs. ${bill_amt}</strong></h4>
//         </div>
//     </div>
// </div>
// <div class="row">
//     <p>Thank You!</p>
// </div>`

    billElem.innerHTML = `<div>
        <p>ABC Parking</p>
        <p>===========================</p> 
        <h4>Date: ${currentDate}</h4>   
    </div>
        <div id="box">
            <div>
                <p>Base Price:</p>
                <p>Add:</p>
                <p>Entry time:</p>
                <p>Exit time:</p>
                <h4><strong>Total: </strong></h4>
            </div>
            <div>
                <p>Rs. ${base_price}</p>
                <p>Rs. ${per_hr_charge * extra}</p>
                <p> ${entry_time}</p>
                <p> ${exit_time}</p>
                <h4><strong>Rs. ${bill_amt}</strong></h4>
            </div>
        </div>
    </div>
    <div class="row">
        <p>Thank You!</p>
    </div>`
    
    // create invoice of parking rent 
    const record_invoice = {
        invDate: entry_date,
        parkingID: park_id,
        amountPaid: bill_amt,
        paymentMethod: payment_method
    }
    try {
        const resp2 = await fetch('http://localhost:8080/park_vision//invoice/create-invoice', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(record_invoice)
        });
        const postedData = await resp2.json();
        if (!resp2.ok) {
            console.log(postedData.description);
        }
    } catch (error) {
        console.log(error);
    }
}

// show message function
let modal = document.getElementById("myModal");
let span = document.getElementsByClassName("close")[0];
const showMessage = (msg)=>{
    document.getElementById('message').textContent = msg;
    modal.style.display = "block";
}
span.onclick = ()=>{
    modal.style.display = "none";
}


