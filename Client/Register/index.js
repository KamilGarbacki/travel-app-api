const submitBtn = document.getElementById("btn");
submitBtn.addEventListener('click',async ()=>{
    const inputFname = document.getElementById("fname").value;
    const inputLname = document.getElementById("lname").value;
    const inputEmail = document.getElementById("email").value;
    const inputPhone = document.getElementById("phone").value;
    
    if(inputEmail.length == 0 || inputFname.length == 0 || inputLname.length == 0 || inputPhone.length ==0){
        alert("All fields are required!");
        return;
    }
    if(!inputEmail.includes("@") || !inputEmail.includes(".com")){
        alert("Email is not valid!");
        return;
    }
    if(inputPhone.length != 9|| isNaN(inputPhone)){
        alert("Phone number is not valid!");
        return;
    }

    const isTaken = await fetchPassengerByEmail(inputEmail);
    console.log(isTaken);
    if(isTaken.status != 500){
        alert("Email is taken!");
        return;
    }
    postPassenger(inputFname, inputLname, inputEmail, inputPhone).then(()=>{
        alert("User registered!");
    })
    document.getElementById("fname").value = "";
    document.getElementById("lname").value = "";
    document.getElementById("email").value = "";
    document.getElementById("phone").value = "";
    
})

async function postPassenger(fname, lname, email, phone){
    await fetch('http://localhost:8080/api/passenger', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "email": email,
            "phone": phone,
            "lname": lname,
            "fname": fname
        })   
    });
}

async function fetchPassengerByEmail(email){
    const response = await fetch('http://localhost:8080/api/passenger/email/' + email, {
        method: 'GET',
        cache: 'no-cache',
        credentials: 'same-origin',   
    });

    return await response.json();
}