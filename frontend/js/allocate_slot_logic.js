
let entry_date, d_name, dPhNo, vehicle_Type, vehicle_number, slot, entry_Time, exit_time, countEmpty2, countEmpty4, twoWheeler, fourWheeler;
// const serverURL = 'http://localhost:8080/park_vision/'
const serverURL = 'http://localhost:8080/park_genie/'
// get current time
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
// document.getElementById('currentDate').textContent = currentDate;
// console.log("date", typeof (currentDate));
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
document.getElementById('entryTime').setAttribute('value', current_time);

// var timepicker = new TimePicker('.entryTime', {
//     theme: 'dark',
//     lang: 'pt',
// });
//   timepicker.on('change', function(evt) {
//     var value = (evt.hour || '00') + ':' + (evt.minute || '00');
//     evt.element.value = value;
// });

// get free parking slot count
let availability = async () => {
    try {
        const resp1 = await fetch(`${serverURL}parking-slots/2`);
        const availTwo = await resp1.json();
        countEmpty2 = availTwo.length;
        console.log("countEmpty2: " + countEmpty2);
        // twoWheeler = document.getElementById('empty_slots_2');
        // twoWheeler.textContent = countEmpty2;
        if (!resp1.ok) {
            console.log(availTwo.description);
        }
    }
    catch (error) {
        console.log(error);
    }
    try {
        const resp2 = await fetch(`${serverURL}parking-slots/4`);
        const availFour = await resp2.json();
        countEmpty4 = availFour.length;
        console.log("countEmpty4: " + countEmpty4);
        // fourWheeler = document.getElementById('empty_slots_4');
        // fourWheeler.textContent = countEmpty4;
        if (!resp2.ok) {
            console.log(availFour.description);
        }
    }
    catch (error) {
        console.log(error);
    }
}

const parking_details = document.querySelector('.form');
parking_details.addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData(parking_details);
    entry_date = formData.append("entryDate",currentDate);
    // exit_time = formData.append("exitTime", null);
    d_name = formData.get('driverName');
    dPhNo = formData.get('driverPhNo');
    vehicle_number = formData.get('licensePlateNo');
    vehicle_Type = formData.get('vehicleType');
    // console.log("vehicle type: ",vehicle_Type);
    entry_Time = formData.get('entryTime');
    // console.log("vehicle_type: ",vehicle_Type,"data type",typeof(vehicle_Type));
    if (d_name && dPhNo && vehicle_number && vehicle_Type && entry_Time) {
        if (dPhNo.length == 10 && (/^[0-9]+$/).test(dPhNo)) {
            if ((/^[A-Z]{1,3}[0-9]{1,3}[A-Z]{1,3}[0-9]{1,5}$/).test(vehicle_number) && vehicle_number.length == 10) {
                console.log("got data!")
                // get free parking slot
                try {
                    // const resp3 = await fetch(`${serverURL}parking-slots/${parseInt(vehicle_Type)}`);
                    const resp3 = await fetch(`${serverURL}parking-slots/${vehicle_Type}`);
                    const parkingSlotsList = await resp3.json();
                    console.log(parkingSlotsList);
                    slot = parkingSlotsList[0];
                    if(slot == null){
                        showMessage("No more slots available for "+vehicle_Type+" wheeler");
                        return;
                    }
                    formData.append("slotNo", slot);
                    // if(vehicle_Type == "2"){
                    //     formData.set('vehicleType','2 wheeler');
                    // }
                    // else{
                    //     formData.set('vehicleType','4 wheeler');
                    // }
                    const data = Object.fromEntries(formData);
                    let jsonData = JSON.stringify(data);
                    // console.log(data);
                    console.log(jsonData);

                    // record parking details 
                    try {
                        const resp4 = await fetch(`${serverURL}parking-history/record-parking`, {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: jsonData
                        });
                        const parkingRec = await resp4.json();
                        if (!(resp4).ok) {
                            console.log(parkingRec.description);
                        }
                    } catch (error) {
                        console.log(error);
                    }
                    if (!resp3.ok) {
                        console.log(parkingSlotsList.description);
                    }
                }
                catch (error) {
                    console.log(error);
                }
                const updateSlotStatus = {
                    slotNo: slot,
                    vehicleType: vehicle_Type,
                    is_empty: false
                };
                //console.log(updateSlotStatus);

                // update parking slot status as occupied
                try {
                    if(slot != null){
                        const resp5 = await fetch(`${serverURL}parking-slots/${slot}`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(updateSlotStatus)
                    });
                    const updateRes = await resp5.json();
                    if (!resp5.ok) {
                        console.log(updateRes.description);
                    }
                    showSlot("Slot allocated: " + slot);
                    availability();
                    console.log("availability was invoked");
                    document.querySelector('.form').reset();
                    console.log(updateRes);
                    // setTimeout(redirect(), 300000);
                    }
                    else{
                        showMessage("No parking slots available for ",vehicle_Type," wheeler");
                    }
                    
                } catch (error) {
                    console.log(error);
                }
            }
            else {
                showMessage("Invalid license plate number!\nInput example: TS09ET2639");
            }
        }
        else {
            showMessage("Invalid phone number");
        }
    }
    else {
        // alert("Please fill all the form fields");
        showMessage("Please fill all the form fields");
    }
});

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

const showSlot = (slot_msg)=>{
    document.getElementById('message').textContent = slot_msg;
    modal.style.display = "block";
    span.onclick = ()=>{
        modal.style.display = "none";
        window.location.href = '../html/index.html';
    }
}

// page redirect function
const redirect = ()=>{
    window.location.href = '../html/index.html';
}