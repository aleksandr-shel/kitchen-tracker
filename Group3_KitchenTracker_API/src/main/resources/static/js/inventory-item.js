/**
 * 
 */

(function(){
	
	const form = document.querySelector('.update-item-form'),
	userId = sessionStorage.getItem("userId"),
	itemId = document.querySelector("#itemId")?.dataset.value,
	inputs = form.elements;

	fetch(`/user/${userId}/inventory/get/${itemId}`).then(response =>{
		const contentType = response.headers.get("content-type");
	  	if (contentType && contentType.indexOf("application/json") !== -1) {
	    	return response.json();
	  	} else {
	    	throw "Something went wrong";
	  	}
	}).then(item =>{
		inputs["itemName"].value = item.itemName;
		inputs["dateExpiration"].value = item.dateExpiration;
		
	}).catch(error=>{
		console.log(error);
	})

	form.addEventListener('submit',(e)=>{
		e.preventDefault();
		
		const formData = new FormData(form);
		if (formData.get("dateExpiration") !== ''){
			const date = new Date(formData.get("dateExpiration"));
			const dateString = `${date.getUTCFullYear()}/${date.getUTCMonth() + 1}/${date.getUTCDate()}`;
			formData.set("dateExpiration",dateString);
		} else {
			formData.delete("dateExpiration");
		}
		fetch(`/user/${userId}/inventory/update/${itemId}`,{
			method:'POST',
			body: formData
		}).then(response =>{
			const contentType = response.headers.get("content-type");
			  if (contentType && contentType.indexOf("application/json") !== -1) {
			    return response.json();
			  } else {
			    throw "Something went wrong";
			  }
		}).then(item =>{
			console.log(item);
			location.href="/inventory";
		}).catch(error =>{
			console.log(error);
		})
	})
	
	
})()
