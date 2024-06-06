document.addEventListener('DOMContentLoaded', () => {
  
    const findConnectionBtn = document.getElementById('findConnection');
    findConnectionBtn.addEventListener('click', async function(){
        let departureId;
        let destinationId;

        if(document.getElementById("departureCity").value.length == 0){
            departureId = 0
        }
        else{
            const departureCityName =   document.getElementById("departureCity").value;
            const departureCity = await fetchCityByName(departureCityName);
            departureId = departureCity.id;
        }

        if(document.getElementById("arrivalCity").value.length == 0){
            destinationId = 0
        }
        else{
            const destinationCityName =   document.getElementById("arrivalCity").value;
            console.log(destinationCityName);
            const destinationCity = await fetchCityByName(destinationCityName);
            console.log(destinationCity);
            destinationId = destinationCity.id;
        }

        fetchConnectionsByCities(departureId, destinationId).then(connections =>{
            console.log(connections);
            
            const cardContainer = document.querySelector('.cardContainer');

            while(cardContainer.firstChild){
                cardContainer.removeChild(cardContainer.lastChild);
            }

            connections.forEach(connection => {
                const connectionCard = document.createElement('div');
                connectionCard.className = 'connectionCard';

                const opperatorContainer = document.createElement('div');
                opperatorContainer.className = 'opperatorContainer';

                const logo = document.createElement('img');
                logo.className = 'logo';
                logo.src = connection.operator.logo;
                logo.alt = '';

                opperatorContainer.appendChild(logo);

                const cardData = document.createElement('div');
                cardData.className = 'cardData';

                const timeAndPrice = document.createElement('div');
                timeAndPrice.className = 'timeAndPrice';

                const time = document.createElement('div');
                time.className = 'time';

                const departureTime = document.createElement('p');
                departureTime.className = 'departureTime';
                departureTime.textContent = connection.departureStation.name + ' ' + formatTime(connection.departureTime);

                const travelTime = document.createElement('div');
                travelTime.className = 'travelTime';
                travelTime.textContent = timeDifference(connection.arrivalTime, connection.departureTime);

                const arrivalTime = document.createElement('p');
                arrivalTime.className = 'arrivalTime';
                arrivalTime.textContent =  connection.destinationStation.name + ' ' + formatTime(connection.arrivalTime);

                time.appendChild(departureTime);
                time.appendChild(document.createTextNode('-'));
                time.appendChild(travelTime);
                time.appendChild(document.createTextNode('->'));
                time.appendChild(arrivalTime);

                const price = document.createElement('div');
                price.className = 'price';
                price.textContent = connection.price +"zł";

                timeAndPrice.appendChild(time);
                timeAndPrice.appendChild(price);

                const inputAndBtn = document.createElement('div');
                inputAndBtn.className = 'inputAndBtn';

                const form = document.createElement('form');
                form.className = 'formClass';
                const emailInput = document.createElement('input');
                emailInput.type = 'text';
                emailInput.className = 'emailInput';
                emailInput.id = "Input" + connection.id;
                emailInput.placeholder = 'Enter email';

                form.appendChild(emailInput);

                const bookBtn = document.createElement('button');
                bookBtn.className = 'bookBtn';
                bookBtn.id = connection.id;
                bookBtn.textContent = 'Book';

                inputAndBtn.appendChild(form);
                inputAndBtn.appendChild(bookBtn);

                cardData.appendChild(timeAndPrice);
                cardData.appendChild(inputAndBtn);

                connectionCard.appendChild(opperatorContainer);
                connectionCard.appendChild(cardData);

                cardContainer.appendChild(connectionCard);

                               
            });
            const bookingButtons = document.querySelectorAll('.bookBtn');
            bookingButtons.forEach(function(bookingButton){
                bookingButton.addEventListener('click', function(){
                    const connectionId = bookingButton.id
                    const inputEmail = document.getElementById("Input" + connectionId);
                    const email = inputEmail.value;

                    if(email.length == 0){
                        alert("Email Field is required!")
                        return;
                    }
                    if(!email.includes("@") || !email.includes(".com")){
                        alert("Email is not valid!");
                        return;
                    }
                
                    fetchPassengerByEmail(email).then(passenger =>{
                        
                        if(passenger.status == 500){
                            alert("Passenger with this email doesnt exist.")
                            return
                        }

                        fetchConnectionById(connectionId).then(connection =>{
                        const date = new Date();
                        const bookingDate = formatDate(date);
                        postBooking(passenger.id, connection.id, bookingDate).then(()=>{return});
                        alert("Train booked successfully")
                        })
                    })                        
                }) 
            }) 
        })

    })

    
});

function formatDate(date) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');

    return `${year}-${month}-${day}`;
}

function formatTime(timeArray) {
    let hours = timeArray[0];
    let minutes = timeArray[1];

    let formattedHours = hours.toString().padStart(2, '0');
    let formattedMinutes = minutes.toString().padStart(2, '0');

    return `${formattedHours}:${formattedMinutes}`;
}

function timeDifference(time1, time2) {
    let hours1 = time1[0];
    let minutes1 = time1[1];
    let hours2 = time2[0];
    let minutes2 = time2[1];

    let totalMinutes1 = hours1 * 60 + minutes1;
    let totalMinutes2 = hours2 * 60 + minutes2;

    let differenceInMinutes = Math.abs(totalMinutes1 - totalMinutes2);

    let diffHours = Math.floor(differenceInMinutes / 60);
    let diffMinutes = differenceInMinutes % 60;

    let formattedHours = diffHours.toString().padStart(2, '0');
    let formattedMinutes = diffMinutes.toString().padStart(2, '0');

    return `${formattedHours}:${formattedMinutes}`;
}

async function postBooking(passengerId, connectionId, bookingDate){
    await fetch('http://localhost:8080/api/booking', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        // cache: 'no-cache',
        // credentials: 'same-origin',
        body: JSON.stringify({
            "passengerId": passengerId,
            "connectionId": connectionId,
            "bookingDate": bookingDate
        })   
    });
}

async function fetchCityByName(cityName){
    const response = await fetch('http://localhost:8080/api/city/name/'+cityName, {
        method: 'GET',
        cache: 'no-cache',
        credentials: 'same-origin',   
    });

    return await response.json();
}

async function fetchPassengerByEmail(email){
    const response = await fetch('http://localhost:8080/api/passenger/email/' + email, {
        method: 'GET',
        cache: 'no-cache',
        credentials: 'same-origin',   
    });

    return await response.json();
}

async function fetchConnectionsByCities(departureCityId, destinationStation){
    const response = await fetch('http://localhost:8080/api/connection/cities/' + departureCityId + '/' + destinationStation, {
        method: 'GET',
        cache: 'no-cache',
        credentials: 'same-origin',   
    });

    return await response.json();
}

async function fetchConnectionById(connectionId){
    const response = await fetch('http://localhost:8080/api/connection/id/' + connectionId, {
        method: 'GET',
        cache: 'no-cache',
        credentials: 'same-origin',   
    });

    return await response.json();
}



    