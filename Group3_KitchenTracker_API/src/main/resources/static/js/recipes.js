/**
 * 
 */

(function(){
	const recipesListTable = document.querySelector("#recipes-list tbody"),
		formSearchRecipe = document.querySelector("#formSearchRecipe"),
		btnAddRecipe = document.querySelector('.btn-add-recipe'),
		btnMyRecipes = document.querySelector('.btn-my-recipes');
	const userId = sessionStorage.getItem("userId");
	if(userId !== null){
		btnAddRecipe.style.display = 'inline-block';
		btnMyRecipes.style.display = 'inline-block';
	}
	
	
	//processing search for recipes by recipe name
	formSearchRecipe.addEventListener('submit',(e)=>{
		e.preventDefault();
		const formData = new FormData(formSearchRecipe);
		searchForRecipeProcess(formData);
	})
	
	
	btnMyRecipes.addEventListener('click',()=>{
		fetch(`/get/recipes/${userId}`).then(response=>{
			const contentType = response.headers.get("content-type");
			  if (contentType && contentType.indexOf("application/json") !== -1) {
			    return response.json();
			  } else {
			    throw "Something went wrong";
			  }
		}).then(data =>{
			data.forEach(item =>{
				recipesListTable.innerHTML = '';
				AddRecipeToTable(recipesListTable, item.name, item.description, item.imageUrl, userId, item.author_id, item.id);
			})
		}).catch(error =>{
			console.log(error);
		})
	})
	
	
	// getting all recipes and show them in a table format
	fetch("/get/recipes").then(response =>{
			  const contentType = response.headers.get("content-type");
			  if (contentType && contentType.indexOf("application/json") !== -1) {
			    return response.json();
			  } else {
			    throw "Something went wrong";
			  }
	}).then(
		data =>{
			data.forEach(item =>{
				AddRecipeToTable(recipesListTable, item.name, item.description, item.imageUrl, userId, item.author_id, item.id);
			})
		}
	)
	
	//add recipe into the table
	function AddRecipeToTable(table, recipeName, recipeDescription, recipeImage, userId, authorId, itemId){
		const tr = document.createElement('tr');
				tr.classList.add('recipe-list-item');
				tr.innerHTML = `
					<td>${recipeName}</td>
					<td>${recipeDescription}</td>
					<td><img src="${recipeImage}" alt="image ${recipeName}" width="200" height="100"></td>
					<td>${showIfLoggedIn(userId, authorId, itemId)}</td>
				`;
		AddRemoveRecipeProcessToBtn(tr, itemId);
		table.append(tr);
	}
	
	//function that checking if user is logged in so recipes page can show edit/delete buttons for user's recipes
	function showIfLoggedIn(userId, authorId, itemId){
		if (userId === null){
			return `
			<div class="btns">
				<a href="/recipes/${itemId}" class="btn-details-recipe">Details</a>
			</div>`;
		} else if (userId === authorId) {
			return `
			<div class="btns">
				<a href="/recipes/${itemId}" class="btn-details-recipe">Details</a>
				<a href="/recipes/edit-page/${itemId}" class="btn-edit-recipe">Edit</a>
				<button class="btn-delete-recipe">Delete</button>
			</div>
			`
		} else {
			return `
			<div class="btns">
				<a href="/recipes/${itemId}" class="btn-details-recipe">Details</a>
			</div>`;
		}
	}
	
	
	function AddRemoveRecipeProcessToBtn(node, itemId){
		const btn = node.querySelector('.btn-delete-recipe');
		btn?.addEventListener('click',()=>{
			fetch(`/delete/recipe/${itemId}`, {
				method:'POST'
			}).then(response =>{
				const contentType = response.headers.get("content-type");
				if (contentType && contentType.indexOf("application/json") !== -1) {
				   console.log("something wrong");
				} else {
				   node.remove();
				}
			})
		})
	}
	
	//somewhat working search by recipe name and ingredient name
	function searchForRecipeProcess(formData){
		let results = [];
		fetch("/search/recipes",{
				method:"POST",
				body:formData
			}).then(response =>{
			    const contentType = response.headers.get("content-type");
				  if (contentType && contentType.indexOf("application/json") !== -1) {
				    return response.json();
				  } else {
				    throw "Something went wrong";
				  }
			}).then(data =>{
				recipesListTable.innerHTML = '';
				data.forEach(item =>{
					results.push(item.id);
					AddRecipeToTable(recipesListTable, item.name, item.description, item.imageUrl, userId, item.author_id, item.id);
				})
			})
			
		fetch("/advanced-search/recipes",{
				method:"POST",
				body:formData
			}).then(response =>{
			    const contentType = response.headers.get("content-type");
				  if (contentType && contentType.indexOf("application/json") !== -1) {
				    return response.json();
				  } else {
				    throw "Something went wrong";
				  }
			}).then(data =>{
				data.forEach(item =>{
					if (results.includes(item.id) === false){
						AddRecipeToTable(recipesListTable, item.name, item.description, item.imageUrl, userId, item.author_id, item.id);
					}
				})
			})
	}
})()
