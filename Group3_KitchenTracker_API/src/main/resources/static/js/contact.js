/**
 * 

// contact process
*/


const formContact=document.querySelector('#formContact')

formContact.addEventListener('submit',(e)=>{
	e.preventDefault();
	const formData = new FormData(formContact);

	fetch('/contact/post',{
		method:'POST',
		body:formData
	}).then(response => {
		const contentType = response.headers.get("content-type");
		if (contentType && contentType.indexOf("application/json") !== -1) {
		   return response.json();
		 } else {
		   throw "Contact form was not sent";
		 }
	}).then(data =>{
		if (data !== null){
			console.log("Contact form was sent");
			formContact.reset();
			location.href='/';
		}
	}).catch(error =>{
		console.log(error);
	})

 })