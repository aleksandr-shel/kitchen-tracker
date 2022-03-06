/**
 * 
 */
const formRegister=document.querySelector('#formRegister')


formRegister.addEventListener('submit',(e)=>{
	e.preventDefault();
	const formData = new FormData(formRegister);
	fetch("http://localhost:8084/register/user",{
		method:"POST",
		body:formData
	}).then(data => console.log(data.json())).catch(error => console.log(error))
})

