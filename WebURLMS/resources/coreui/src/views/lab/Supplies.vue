<template>
<div class="wrapper">
  <div class="animated fadeIn">
    <div class="row">
      <div class="col">
 		<div class="card">
        		<div class="card-header"> Supplies </div>
        		<div class="card-body">
	        		<div v-if="editable" class="row">
	        			<div class="col">
	        				<b-button type="button" variant="success" @click="openAddSuppliesModal">Add Supplies</b-button>
	        				<b-button type="button" variant="danger" @click="openClearSuppliesModal">Clear Supplies</b-button>
	        			</div>
	        		</div>
        		<div class="row">
        			<div class="col">
		        		<table class="table table-striped table-hover">
		        			<thead>
		        				<tr>
		        					<td>#</td>
		        					<td>Name</td>
		        					<td>Quantity</td>
		        					<td></td>
		        					<td></td>
		        				</tr>
		        			</thead>
		        			<tbody>
		        				<tr v-for="(item, index) in supplies">
		        					<td>{{ index+1 }}</td>
		        					<td>{{ item.name }}</td>
		        					<td>{{ item.qty }}</td>
		        					<td><a v-if="editable" v-on:click="modifyClick(index)" v-bind:id="index" href="#">Modify</a></td>
		        					<td><a v-if="editable" v-on:click="removeClick(index)" v-bind:id="index" href="#">Remove</a></td>
		        				</tr>
		        			</tbody>
		        		</table>
		        	</div>
	        		</div>
	        	</div>
        </div>
      </div><!--/.col-->
    </div><!--/.row-->
  </div>
 <!--  Modals -->
 <b-modal v-model="addSuppliesModal" hide-footer title="Add Supplies">
      <b-form @reset="resetAddSuppliesModal">
      <b-form-group id="nameGroup" label="Name">
        <b-form-select id="nameSelectable" @change.native="onChangeNameSelectable" name="nameSelectable" :options="names" v-model="form.selectableName"></b-form-select>
      	<b-form-input id="name" name="name" type="text" v-model="form.name" v-validate="'required'" :disabled="!activeOtherBox" :class="{'input': true, 'is-danger': errors.has('name') }" :placeholder="addSuppliesPlaceHolder"></b-form-input>
      	<span class="text-danger" v-if="errors.has('name') || nameError">Please enter an supplies name</span>
      </b-form-group>
      <b-form-group id="qtyGroup" label="Quantity">
      	<b-form-input id="qty" type="number" name="qty" v-validate="'required|numeric'" :class="{'input': true, 'is-danger': errors.has('qty') }" v-model="form.qty" ></b-form-input>
      	<span class="text-danger" v-if="errors.has('qty')">Please enter a valid quantity</span>
      </b-form-group>
      <span class="text-danger">{{ addError }}</span>
     <b-button type="button" variant="primary" @click="addSupplies">Save changes</b-button>
     <b-button type="button" variant="secondary" @click="closeAddSupplies">Close</b-button>
	</b-form>
  </b-modal>
  
  <b-modal v-model="modifySuppliesModal" hide-footer title="Modify Supplies">
      <b-form @reset="resetModifySuppliesModal">
      <b-form-group id="nameGroup_2" label="Name">
      	<b-form-input id="name_2" name="name" type="text" v-model="modify.name" readOnly></b-form-input>
      </b-form-group>
      <b-form-group id="qtyGroup_2" label="Quantity">
      	<b-form-input id="qty_2" type="number" name="qty" v-validate="'required|numeric'" :class="{'input': true, 'is-danger': errors.has('qty') }" v-model="modify.qty" ></b-form-input>
        <span class="text-danger" v-if="errors.has('qty')">Please enter a valid quantity</span>
      </b-form-group>
      <span class="text-danger">{{ modifyUnknownError }}</span>
     <b-button type="button" variant="primary" @click="modifySupplies">Save changes</b-button>
     <b-button type="button" variant="secondary" @click="closeModifySupplies">Close</b-button>
	</b-form>
  </b-modal>
  
  <b-modal v-model="clearSuppliesModal" hide-footer title="Clear Supplies">
  <b-form>
      <b-form-group id="clearSuppliesGroup">
      	Are you sure you want to clear all supplies?
      </b-form-group>
     <b-button type="button" variant="success" @click="clearSupplies">Yes</b-button>
     <b-button type="button" variant="danger" @click="clearSuppliesModal = false">No</b-button>
	</b-form>	
  </b-modal>
	<!--   -->
</div>
</template>

<script>
export default {
  name: 'supplies',
  data () {
	    return {
	      addSuppliesModal: false,
	      modifySuppliesModal: false,
	      clearSuppliesModal: false,
	      activeOtherBox: false,
	      addSuppliesPlaceHolder: '',
	      nameError: false,
	      modifyUnknownError: '',
	      clearModalError: '',
	      addError: '',
	      form: {
	    	  	name: '',
	    	  	selectableName: null,
	    	  	qty: 0,
	    	  	role: ''
	      },
	      modify: {
	    	  	name: '',
	    	  	qty: 0,
	    	  	index: 0
	      },
	      supplies: [
	      ]
	    }
	  },
  computed: {
		  names() {
			  let list = [];
			  list.push({ text: 'Select One', value: null, disabled: true });
			  
			  this.supplies.map(function(eq) {
				  list.push(eq.name);
			  });
			  
			  list.push('Other');
			  return list;
		  }
	  },
  props: {
	  editable: {
		type: Boolean,
		default: true
	  }
  },
  mounted : function(){
		this.populateSupplies();
	},
  methods: {
	  openAddSuppliesModal() {
		 this.errors.clear();
		 this.nameError = false;
		 this.addError = '';
		 this.addSuppliesModal = true;
	  },
	  openClearSuppliesModal() {
		 this.errors.clear();
		 this.clearModalError = '';
		 this.clearSuppliesModal = true;
	  },
	  resetAddSuppliesModal() {
		  this.form.name = '';
		  this.form.selectableName = null;
		  this.form.qty = 0;
		  this.errors.clear();
		  this.activeOtherBox = false;
	  },
	  resetModifySuppliesModal() {
		  this.modify.name = '';
		  this.modifyUnknownError = '';
		  this.nameError = false;
		  this.modify.qty = 0;
		  this.errors.clear();
	  },
	  closeAddSupplies() {
		  this.addSuppliesModal = false;
		  this.resetAddSuppliesModal();
	  },
	  closeModifySupplies() {
		  this.modifySuppliesModal = false;
		  this.resetModifySuppliesModal();
	  },
	  removeClick(index) {
		  axios.post('/supplies/delete', {
			  name: this.supplies[index].name
		  }).then(response => {
			 if(response.data['status']) {
				 this.populateSupplies();
			 }
		  });
	  },
	  modifyClick(index) {
		  this.modify.name = this.supplies[index].name;
		  this.modify.qty = this.supplies[index].qty;
		  this.modify.index = index;
		  this.modifySuppliesModal = true;
	  },
	  onChangeNameSelectable(evt) {
		  if(evt.target.value == "Other") {
			  this.activeOtherBox = true;
			  this.form.name = '';
			  this.addSuppliesPlaceHolder = 'Enter Name';
		  } else{
			  this.activeOtherBox = false;
			  this.addSuppliesPlaceHolder = '';
		  }
	  },
	  addSupplies() {
		  if((this.activeOtherBox && this.form.name == '') || (!this.activeOtherBox && (this.form.selectableName == '' || this.form.selectableName == null))) {
			  this.nameError = true;
		  }
		  
		  if (!this.errors.any() && !this.nameError) {
			  if(!this.activeOtherBox) {
				  this.form.name = this.form.selectableName;
			  }
			  
			  axios.post('/supplies/add', {
				  name: this.form.name,
				  qty: this.form.qty
			  }).then(response => {
				 if(response.data['status']) {
					 this.populateSupplies();
					 this.closeAddSupplies();
				 } else {
					 this.addError = response.data['message'];
				 }
			  });
		  }
	  },
	  populateSupplies() {
		  this.listError = '';
		  axios.get('/supplies/get')
			.then(response => {
				if(response.data['status']) {
					this.supplies = response.data['supplies'];
				} 
			});
	  },
	  modifySupplies() {
		  if(!this.errors.any() && this.modify.qty >= 0) {
			  axios.post('/supplies/modify', {
				  name: this.modify.name,
				  qty: this.modify.qty
			  }).then(response => {
				  if(response.data['status']) {
					  this.closeModifySupplies();
					  this.populateSupplies();
				  } else {
					  this.modifyUnknownError = response.data['message'];
				  }
			  });
		  } else {
			  this.modifyUnknownError = 'Bad input';
		  }
	  },
	  clearSupplies() {
		  axios.get('/supplies/clear')
			.then(response => {
				if(response.data['status']) {
					this.populateSupplies();
					this.clearSuppliesModal = false
				} else {
					this.clearModalError = response.data['message'];
				}
			});
	  }
  }
}
</script>

