document.addEventListener('DOMContentLoaded', () => {
    fetchCities().then(cities => {
        cities.forEach(city => {
            const cityCard = document.createElement('div');
            cityCard.className = 'cityCard';
        
            const cityNameDiv = document.createElement('div');
            cityNameDiv.className = 'cityName';
            cityNameDiv.textContent = city.name;
            cityCard.appendChild(cityNameDiv);

            const hr = document.createElement('hr');
            cityCard.appendChild(hr);

            const stationsWrapper = document.createElement('div');
            stationsWrapper.className = 'stationsWraper';
        
            const ul = document.createElement('ul');
            
            fetchTrainStationsByCityName(city.name).then(stations =>{
                console.log(stations);
                stations.forEach(station => {
                    const li = document.createElement('li');
                    li.textContent = station.name;
                    ul.appendChild(li);
                });
            })
        
            stationsWrapper.appendChild(ul);
        
            cityCard.appendChild(stationsWrapper);
        
            const cardContainer = document.querySelector('.cardContainer');
            cardContainer.appendChild(cityCard);            
        });
    })
})

async function fetchTrainStationsByCityName(cityName){
    const response = await fetch('http://localhost:8080/api/trainStation/cityName/' + cityName, {
        method: 'GET',
        cache: 'no-cache',
        credentials: 'same-origin',   
    });

    return await response.json();
}

async function fetchCities(){
    const response = await fetch('http://localhost:8080/api/city', {
        method: 'GET',
        cache: 'no-cache',
        credentials: 'same-origin',   
    });

    return await response.json();
}