/**
 * 
 */



(function(){
	const inventoryListTable = document.querySelector("#inventory-list tbody"),
	addItemBtn = document.querySelector('#addItemBtn'),
	userId = sessionStorage.getItem("userId"),
	addItemForm = document.querySelector('#addItemForm'),
	cancelAddItemForm = document.querySelector('#cancelAddItemForm');
	
	
	if(userId === null){
		location.href="/login";
	}
	
	//getting inventory and put it inside table
	fetch(`/user/${userId}/inventory`).then(response =>{
		  const contentType = response.headers.get("content-type");
		  if (contentType && contentType.indexOf("application/json") !== -1) {
		    return response.json();
		  } else {
		    throw "Something went wrong";
		  }
	}).then(
		data =>{
			data.forEach(item =>{
				AddItemToTable(inventoryListTable, item.itemName, item.dateExpiration, item.itemId);
			})
		}
	)
	
	//function for adding item into table tbody
	function AddItemToTable(table, itemName, dateExpiration, itemId){
		const tr = document.createElement('tr');
		tr.classList.add('inventory-list-item');
		tr.innerHTML = `
			<td>${itemName}</td>
			<td>${evaluateDateExpiration(dateExpiration)}</td>
			<td><button class="btn-edit"><a href="/inventory/${itemId}">Edit</a></button><button class="btn-delete">Delete</button></td>
		`;
		addDeleteProcessToBtn(tr, itemId);
		table.append(tr);
	}
	
	
	function evaluateDateExpiration(dateExpiration){
		const d1 = new Date(dateExpiration).getTime();
		const dnow = Date.now();
		
		if ((d1 - dnow) / 172800000 < 1){
			return `<span class="notification">${dateExpiration}</span>`;
		} else {
			return `<span>${dateExpiration}</span>`;
		}
	}
	
	//show/hide add item form
	addItemBtn.addEventListener('click',()=>{
		addItemForm.style.display='block';
		addItemBtn.style.display='none';
	})
	cancelAddItemForm.addEventListener('click', ()=>{
		addItemForm.style.display='none';
		addItemBtn.style.display='block';
	})
	
	//adding item handling
	addItemForm.addEventListener('submit',(e)=>{
		e.preventDefault();
		const formData = new FormData(addItemForm);
		if (formData.get("dateExpiration") !== ''){
			const date = new Date(formData.get("dateExpiration"));
			const dateString = `${date.getUTCFullYear()}/${date.getUTCMonth() + 1}/${date.getUTCDate()}`;
			formData.set("dateExpiration",dateString);
		} else {
			formData.delete("dateExpiration");
		}
		
		fetch(`/user/${userId}/inventory/add`,{
			method:"POST",
			body:formData
		}).then(response =>{
			 const contentType = response.headers.get("content-type");
			 if (contentType && contentType.indexOf("application/json") !== -1) {
			   return response.json();
			 } else {
			   throw "Something went wrong";
			 }
		}).then(item =>{
			AddItemToTable(inventoryListTable, item.itemName, item.dateExpiration, item.itemId);
			addItemForm.reset();
		}).catch(error =>{
			console.log(error);
		})
	})
	
	//function for putting deleting process on item
	function addDeleteProcessToBtn(node, itemId){
		const btnDelete = node.querySelector('.btn-delete');
		btnDelete.addEventListener('click', (e)=>{
			fetch(`/user/${userId}/inventory/delete/${itemId}`,{
				method:'POST'
			}).then(response =>{
				return response.json();
			}).then(data=>{
				if (data == true){
					node.remove();
				}
			})
		})
	}
	
	//function for putting update process on item
	/*
	function addUpdateFormToBtn(node, itemId, itemName, dateExpiration){
		const btnUpdate = node.querySelector('.btn-edit');
		btnUpdate.addEventListener('click', ()=>{
			node.innerHTML = `
					<form class="update-item-form">
						<input placeholder="Item Name" name="itemName" type="text" value="${itemName}" required>
						<input name="dateExpiration" type="date" value="${dateExpiration}">
						<input type="submit" value="Update">
					</form>
			`;
			const form = node.querySelector('.update-item-form');
			console.log(form);
			form.addEventListener('submit', (e)=>{
				e.preventDefault();
				const formDataUpdate = new FormData(form);
				fetch(`/user/${userId}/inventory/update/${itemId}`,{
					method:'POST',
					body: formDataUpdate
				}).then(response =>{
					 const contentType = response.headers.get("content-type");
					 if (contentType && contentType.indexOf("application/json") !== -1) {
					   return response.json();
					 } else {
					   throw "Something went wrong";
					 }
				}).then(item =>{
					node.innerHTML = `
						<td>${item.itemName}</td>
						<td>${item.dateExpiration}</td>
						<td class="buttons-td"><button class="btn-edit">Edit</button><button class="btn-delete">Delete</button></td>
					`;
					addUpdateFormToBtn(node, itemId, itemName, dateExpiration);
					addDeleteProcessToBtn(node, itemId);
				}).catch(error =>{
					console.log(error);
				})
			})
		})
	}
	*/
	/*
	function addUpdateProcessToItem(node, itemName, dateExpiration, itemId){
		const btnUpdate = node.querySelector('.btn-edit');
		
		btnUpdate.addEventListener('click',(e)=>{
			const form = document.createElement('form');
			form.classList.add("update-item-form");
			form.innerHTML = `
				<input placeholder="Item Name" name="itemName" type="text" value="${itemName}" required>
				<input name="dateExpiration" type="date" value="${dateExpiration}">
				<input type="submit" value="Update">
			`;
			inventoryListDiv.append(form);
		})
	}*/
})()


