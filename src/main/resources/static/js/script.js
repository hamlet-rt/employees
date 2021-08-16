let countrySelect = document.getElementById('country-select');
let citySelect = document.getElementById('city-select');

countrySelect.onchange = () => {
    let xhr = new XMLHttpRequest();
    let countryId = countrySelect.value;
    let path = '/countries/' + countryId + '/cities';
    xhr.open('get', path);
    xhr.send();
    xhr.onload = () => {
        if (xhr.status !== 200){
            return;
        }
        while (citySelect.childElementCount > 1){
            citySelect.removeChild(citySelect.lastChild);
        }
        let cities = JSON.parse(xhr.response);
        for (let city of cities){
            let option = document.createElement('option');
            option.value = city.id;
            option.innerText = city.name;
            citySelect.appendChild(option);
        }
    };
};