/**
 * 
 */


(function(){
	const recipeId = document.querySelector('#recipeId')?.dataset.value,
		recipeForm = document.querySelector('#RecipeForm'),
		editMessage = document.querySelector('#editMessage');
	const userId = sessionStorage.getItem("userId");
		
	fetch(`/get/recipe/${recipeId}`).then(response =>{
		const contentType = response.headers.get("content-type");
		  if (contentType && contentType.indexOf("application/json") !== -1) {
		    return response.json();
		  } else {
		    throw "Something went wrong";
		  }
	}).then(recipe => {
		recipeForm.innerHTML = `
			<input type="hidden" id="userId" value="${userId}" name="author_id">
			<div>
				<label for="recipename">Recipe Name: </label>
				<input placeholder="Recipe Name" value="${recipe.name}" name="name" id="recipename" type="text" required>
			</div>
			<label for="description">Description: </label>
			<div>
				<textarea id="description" placeholder="Description" name="description" required>${recipe.description}</textarea>
			</div>
			<div>
				<label for="imageurl">Image URL: </label>
				<input id="imageurl" value="${recipe.imageUrl}" placeholder="Image URL" name="imageUrl" type="text">
			</div>
			<label for="ingredients">Ingredients: </label> <button type="button" class="btn-add-ingredient">Add Ingredient</button>
			<div id="ingredients" class="ingredients-list">
					${recipe.ingredients.map(i => {return `
					<div>
						<input placeholder="Enter an ingredient" value="${i}" name="ingredients" type="text">
						<button type="button" class='btn-remove-ingredient'>X</button>
					</div>`}).join('')}
			</div>
			<div>
				<input type="submit" value="Edit">
				<input type="reset" value="Reset">
			</div>
			<div>
				<button type="button" onclick="location.href='/recipes'">Cancel</button>
			</div>
		`
		const btnAddIngredient = recipeForm.querySelector('.btn-add-ingredient'),
			ingredientInputs = document.querySelector('.ingredients-list'),
			deleteBtns = document.querySelectorAll('.btn-remove-ingredient');
		deleteBtns.forEach(btn =>{
			btn.addEventListener('click',(e)=>{
				e.target.parentNode.remove();
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
	})
	
	recipeForm.addEventListener('submit',(e)=>{
		e.preventDefault();
		editMessage.textContent = '';
		const formData = new FormData(recipeForm);
		fetch(`/update/recipe/${recipeId}`, {
			method: 'POST',
			body: formData
		}).then(response =>{
			const contentType = response.headers.get("content-type");
			 if (contentType && contentType.indexOf("application/json") !== -1) {
			    return response.json();
			  } else {
			    throw "Something went wrong";
			  }
		}).then(recipe =>{
			if (recipe !== null){
				editMessage.style.display="block";
				editMessage.style.color = 'green';
				editMessage.textContent ='Edited';
				location.href='/recipes';
			}
		}).catch(error =>{
			editMessage.style.display="block";
			editMessage.style.color = 'red';
			editMessage.textContent =error;
		})
	})
	
	
})()