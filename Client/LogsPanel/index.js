document.addEventListener('DOMContentLoaded', () => {
    fetchLogs().then(logs =>{
        logs.reverse();
        logs.forEach(log => {
            const logCard = document.createElement('div');
            logCard.className = 'logCard';
        
            // Create the logDate div and set its content
            const logDateDiv = document.createElement('div');
            logDateDiv.className = 'logDate';
            logDateDiv.textContent = formatDateTime(log.date) + ':';
            logCard.appendChild(logDateDiv);
        
            // Create the logData div and set its content
            const logDataDiv = document.createElement('div');
            logDataDiv.className = 'logData';
            logDataDiv.textContent = log.message;
            logCard.appendChild(logDataDiv);
        
            // Append the logCard to the container with the class cardContainer
            const cardContainer = document.querySelector('.cardContainer');
            cardContainer.appendChild(logCard);                    
        });
    })        
})

function formatDateTime(arr) {
    // Destructure the array into individual components
    let [year, month, day, hour, minute, second] = arr;

    // Add leading zero if month, day, hour, minute, or second is a single digit
    month = month.toString().padStart(2, '0');
    day = day.toString().padStart(2, '0');
    hour = hour.toString().padStart(2, '0');
    minute = minute.toString().padStart(2, '0');
    second = second.toString().padStart(2, '0');

    // Construct the formatted string
    let formattedDateTime = `${year}-${month}-${day}, ${hour}:${minute}:${second}`;
    return formattedDateTime;
}

async function fetchLogs(){
    const response = await fetch('http://localhost:8080/api/logs', {
        method: 'GET',
        cache: 'no-cache',
        credentials: 'same-origin',   
    });

    return await response.json();
}