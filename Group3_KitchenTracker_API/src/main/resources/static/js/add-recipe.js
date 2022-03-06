/**
 * 
 */

(function(){
	const userId = sessionStorage.getItem("userId"),
		userIdInput = document.querySelector("#userId"),
		formAddRecipe = document.querySelector('#addRecipeForm'),
		btnAddIngredient = document.querySelector('.btn-add-ingredient'),
		ingredientInputs = document.querySelector('#ingredients'),
		btnRemoveIngredient = document.querySelector('.btn-remove-ingredient');
	
	
	if (userId===null){
		location.href='/login';
	}
	
	userIdInput.value = userId;
	
	formAddRecipe.addEventListener('submit', (e)=>{
		e.preventDefault()
		const formData = new FormData(formAddRecipe);
		 const json = JSON.stringify(Object.fromEntries(formData.entries()));
		console.log(json);
		fetch('/create/recipe', {
			method:'POST',
			body: formData
		}).then(response =>{
			const contentType = response.headers.get("content-type");
			if (contentType && contentType.indexOf("application/json") !== -1) {
			   return response.json();
			 } else {
			   throw "Something went wrong";
			 }
		}).then(data =>{
			if (data !==null){
				location.href = '/recipes';
			}
		})
	})
	
	
	btnAddIngredient.addEventListener('click',()=>{
		const divIngrInput = document.createElement('div');
		
		divIngrInput.innerHTML = `
			<input placeholder="Enter an ingredient" name="ingredients" type="text" required>
			<button type="button" class='btn-remove-ingredient'>X</button>
		`;
		
		const btnDelete = divIngrInput.querySelector('.btn-remove-ingredient');
		btnDelete.addEventListener('click',()=>{
			divIngrInput.remove();
		})
		ingredientInputs.append(divIngrInput);
	})
	
	btnRemoveIngredient.addEventListener('click',(e)=>{
		e.target.parentNode.remove();
	})
	
	
})()

