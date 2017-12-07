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
      	<span class="text-danger" v-if="errors.has('name')">Please enter an supplies name</span>
      </b-form-group>
      <b-form-group id="qtyGroup" label="Quantity">
      	<b-form-input id="qty" type="number" name="qty" v-validate="'required|numeric'" :class="{'input': true, 'is-danger': errors.has('qty') }" v-model="form.qty" ></b-form-input>
      	<span class="text-danger" v-if="errors.has('qty')">Please enter a valid quantity</span>
      </b-form-group>
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
	    	   {
	    		   name: 'Pen',
	    		   qty: 10
	    	   }
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
  methods: {
	  openAddSuppliesModal() {
		 this.errors.clear();
		 this.addSuppliesModal = true;
	  },
	  openClearSuppliesModal() {
		 this.errors.clear();
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
		  this.supplies.splice(index, 1);
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
		  this.$validator.validateAll();
		  
		  if((this.activeOtherBox && this.form.name == '') || (!this.activeOtherBox && (this.form.selectableName == '' || this.form.selectableName == null))) {
			  this.errors.add('name');
		  }
		  
		  if (!this.errors.any()) {
			  if(!this.activeOtherBox) {
				  this.form.name = this.form.selectableName;
			  }
			  
			  var elHasBeenModified = false;
			  var formName = this.form.name;
			  var formQty = this.form.qty;
			  
			  this.supplies.map(function(eq) {
				  if(eq.name == formName) {
					  eq.qty = +eq.qty + +formQty;
					  elHasBeenModified = true;
				  }
			  });
	
			  if(!elHasBeenModified) {
			  	this.supplies.push({name: this.form.name, qty: this.form.qty});
		  	  }
			  
			  this.closeAddSupplies();
		  }
	  },
	  modifySupplies() {
		  this.$validator.validateAll();
		  
		  if(this.errors.has('name')) {
			  this.errors.remove('name');
		  }
		  
		  if(!this.errors.any() && this.modify.qty >= 0) {
			  this.supplies[this.modify.index].qty = this.modify.qty;
			  this.closeModifySupplies();
		  }
	  },
	  clearSupplies() {
		  this.supplies = [];
		  this.clearSuppliesModal = false
	  }
  }
}
</script>

