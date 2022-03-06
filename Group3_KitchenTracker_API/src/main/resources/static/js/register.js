/**
 * 

// register process
*/


const formRegister=document.querySelector('#formRegister')
const registerMessage = formRegister.querySelector("#registerMessage");
const loading = document.querySelector("#loading");

formRegister.addEventListener('submit',(e)=>{
	e.preventDefault();
	loading.style.display='block';
	const formData = new FormData(formRegister);
	const json = Object.fromEntries(formData.entries());
	const formDataForUsername = new FormData();
	formDataForUsername.append("username", json.username);
	registerMessage.textContent = '';
	fetch('/get/user/username',{
		method:'POST',
		body: formDataForUsername
	}).then(response => {
		const contentType = response.headers.get("content-type");
		if (contentType && contentType.indexOf("application/json") !== -1) {
			registerMessage.textContent = 'Username already exists';
			loading.style.display='none';
		    return response.json();
		  } else {
			fetch("/register/user",{
				method:"POST",
				body:formData
			})
			.then(() =>{
				loading.style.display='none';
				location.href="/login";
			})
		  }
	}) 

 })