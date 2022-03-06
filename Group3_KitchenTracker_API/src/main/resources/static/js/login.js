/**
 * 
 */


(function(){
	const formLogin= document.querySelector("#loginForm"),
		loading = document.querySelector("#loading");
	const loginMessage = formLogin.querySelector("#loginMessage");
	
	if (sessionStorage.getItem("userId")!==null){
		formLogin.innerHTML = `<h1>You are logged in already</h1>`;
	}
		
	formLogin?.addEventListener('submit',(e)=>{
		e.preventDefault();
		loading.style.display='block';
		
		loginMessage.textContent = '';
		const formData = new FormData(formLogin);
		fetch("/login/user",{
			method:"POST",
			body:formData
		}).then(response =>{
			  const contentType = response.headers.get("content-type");
				//checking if content type is json parsable: if it is then move on;
				//if not then throw error
			  if (contentType && contentType.indexOf("application/json") !== -1) {
			    return response.json();
			  } else {
			    throw "Incorrect username or password";
			  }
			})
			.then(data => {
				loading.style.display='none';
				loginMessage.style.color = 'green';
				loginMessage.textContent = "Logged In";
				sessionStorage.setItem("userId",data.id);
				location.href = '/inventory';
			})
			.catch(error => {
				loading.style.display='none';
				loginMessage.style.color = 'red';
				loginMessage.textContent = error;
				console.log(error);
			})
	})
})()