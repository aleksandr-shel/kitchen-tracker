

(function(){
	const userId = sessionStorage.getItem("userId"),
		loginBtn = document.querySelector("#loginButton"),
		logoutBtn = document.querySelector("#logoutButton"),
		inventoryBtn = document.querySelector("#inventoryButton");


	logoutBtn.addEventListener('click',()=>{
		sessionStorage.removeItem('userId');
		loginBtn.style.display = 'block';
		logoutBtn.style.display = 'none';
		inventoryBtn.style.display = 'none';
		location.href='/';
	})
	
	if (userId){
		loginBtn.style.display = 'none';
		logoutBtn.style.display = 'block';
		inventoryBtn.style.display = 'block';
	} else {
		loginBtn.style.display = 'block';
		logoutBtn.style.display = 'none';
		inventoryBtn.style.display = 'none';
	}
})()

