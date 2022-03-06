/**
 * 
 */

(function(){
	const recipeId = document.querySelector('#recipeId')?.dataset.value,
		recipeDiv = document.querySelector('#RecipeDiv');
		
	fetch(`/get/recipe/${recipeId}`).then(response =>{
		const contentType = response.headers.get("content-type");
		  if (contentType && contentType.indexOf("application/json") !== -1) {
		    return response.json();
		  } else {
		    throw "Something went wrong";
		  }
	}).then(recipe => {
		recipeDiv.innerHTML = `
			<h1>${recipe.name}</h1>
			<img class="image-details" src="${recipe.imageUrl}">
			<div><h3 style="font-weight: bold; text-align:center">Description: </h3> <p>${recipe.description}</p></div>
			<div><span style="font-weight: bold;">Ingredients: </span> <ol>${recipe.ingredients.map(item=>{return `<li>${item}</li>`}).join('')}</ol></div>
		`
	})
	
})()

	

