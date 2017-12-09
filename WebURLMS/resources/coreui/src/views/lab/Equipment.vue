<template>
<div class="wrapper">
  <div class="animated fadeIn">
    <div class="row">
      <div class="col">
 		<div class="card">
        		<div class="card-header"> Equipment </div>
        		<div class="card-body">
	        		<div v-if="editable" class="row">
	        			<div class="col">
	        				<b-button type="button" variant="success" @click="openAddEquipmentModal">Add Equipment</b-button>
	        				<b-button type="button" variant="danger" @click="openClearEquipmentModal">Clear Equipment</b-button>
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
		        				<tr v-for="(item, index) in equipment">
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
 <b-modal v-model="addEquipmentModal" hide-footer title="Add Equipment">
      <b-form @reset="resetAddEquipmentModal">
      <b-form-group id="nameGroup" label="Name">
        <b-form-select id="nameSelectable" @change.native="onChangeNameSelectable" name="nameSelectable" :options="names" v-model="form.selectableName"></b-form-select>
      	<b-form-input id="name" name="name" type="text" v-model="form.name" v-validate="'required'" :disabled="!activeOtherBox" :class="{'input': true, 'is-danger': errors.has('name') }" :placeholder="addEquipmentPlaceHolder"></b-form-input>
      	<span class="text-danger" v-if="errors.has('name') || nameError">Please enter an equipment name</span>
      </b-form-group>
      <b-form-group id="qtyGroup" label="Quantity">
      	<b-form-input id="qty" type="number" name="qty" v-validate="'required|numeric'" :class="{'input': true, 'is-danger': errors.has('qty') }" v-model="form.qty" ></b-form-input>
      	<span class="text-danger" v-if="errors.has('qty')">Please enter a valid quantity</span>
      </b-form-group>
      <span class="text-danger">{{ addError }}</span>
     <b-button type="button" variant="primary" @click="addEquipment">Save changes</b-button>
     <b-button type="button" variant="secondary" @click="closeAddEquipment">Close</b-button>
	</b-form>
  </b-modal>
  
  <b-modal v-model="modifyEquipmentModal" hide-footer title="Modify Equipment">
      <b-form @reset="resetModifyEquipmentModal">
      <b-form-group id="nameGroup_2" label="Name">
      	<b-form-input id="name_2" name="name" type="text" v-model="modify.name" readOnly></b-form-input>
      </b-form-group>
      <b-form-group id="qtyGroup_2" label="Quantity">
      	<b-form-input id="qty_2" type="number" name="qty" v-validate="'required|numeric'" :class="{'input': true, 'is-danger': errors.has('qty') }" v-model="modify.qty" ></b-form-input>
        <span class="text-danger" v-if="errors.has('qty')">Please enter a valid quantity</span>
      </b-form-group>
      <span class="text-danger">{{ modifyUnknownError }}</span>
     <b-button type="button" variant="primary" @click="modifyEquipment">Save changes</b-button>
     <b-button type="button" variant="secondary" @click="closeModifyEquipment">Close</b-button>
	</b-form>
  </b-modal>
  
  <b-modal v-model="clearEquipmentModal" hide-footer title="Clear Equipment">
  <b-form>
      <b-form-group id="clearEquipmentGroup">
      	Are you sure you want to clear all equipment?
      </b-form-group>
     <b-button type="button" variant="success" @click="clearEquipment">Yes</b-button>
     <b-button type="button" variant="danger" @click="clearEquipmentModal = false">No</b-button>
	</b-form>	
  </b-modal>
	<!--   -->
</div>
</template>

<script>
export default {
  name: 'equipment',
  data () {
	    return {
	      addEquipmentModal: false,
	      modifyEquipmentModal: false,
	      clearEquipmentModal: false,
	      activeOtherBox: false,
	      addEquipmentPlaceHolder: '',
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
	      equipment: [
	      ]
	    }
	  },
  computed: {
		  names() {
			  let list = [];
			  list.push({ text: 'Select One', value: null, disabled: true });
			  
			  this.equipment.map(function(eq) {
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
		this.populateEquipment();
	},
  methods: {
	  openAddEquipmentModal() {
		 this.errors.clear();
		 this.nameError = false;
		 this.addError = '';
		 this.addEquipmentModal = true;
	  },
	  openClearEquipmentModal() {
		 this.errors.clear();
		 this.clearModalError = '';
		 this.clearEquipmentModal = true;
	  },
	  resetAddEquipmentModal() {
		  this.form.name = '';
		  this.form.selectableName = null;
		  this.form.qty = 0;
		  this.errors.clear();
		  this.activeOtherBox = false;
	  },
	  resetModifyEquipmentModal() {
		  this.modify.name = '';
		  this.modifyUnknownError = '';
		  this.nameError = false;
		  this.modify.qty = 0;
		  this.errors.clear();
	  },
	  closeAddEquipment() {
		  this.addEquipmentModal = false;
		  this.resetAddEquipmentModal();
	  },
	  closeModifyEquipment() {
		  this.modifyEquipmentModal = false;
		  this.resetModifyEquipmentModal();
	  },
	  removeClick(index) {
		  axios.post('/equipment/delete', {
			  name: this.equipment[index].name
		  }).then(response => {
			 if(response.data['status']) {
				 this.populateEquipment();
			 }
		  });
	  },
	  modifyClick(index) {
		  this.modify.name = this.equipment[index].name;
		  this.modify.qty = this.equipment[index].qty;
		  this.modify.index = index;
		  this.modifyEquipmentModal = true;
	  },
	  onChangeNameSelectable(evt) {
		  if(evt.target.value == "Other") {
			  this.activeOtherBox = true;
			  this.form.name = '';
			  this.addEquipmentPlaceHolder = 'Enter Name';
		  } else{
			  this.activeOtherBox = false;
			  this.addEquipmentPlaceHolder = '';
		  }
	  },
	  addEquipment() {
		  if((this.activeOtherBox && this.form.name == '') || (!this.activeOtherBox && (this.form.selectableName == '' || this.form.selectableName == null))) {
			  this.nameError = true;
		  }
		  
		  if (!this.errors.any() && !this.nameError) {
			  if(!this.activeOtherBox) {
				  this.form.name = this.form.selectableName;
			  }
			  
			  axios.post('/equipment/add', {
				  name: this.form.name,
				  qty: this.form.qty
			  }).then(response => {
				 if(response.data['status']) {
					 this.populateEquipment();
					 this.closeAddEquipment();
				 } else {
					 this.addError = response.data['message'];
				 }
			  });
		  }
	  },
	  populateEquipment() {
		  this.listError = '';
		  axios.get('/equipment/get')
			.then(response => {
				if(response.data['status']) {
					this.equipment = response.data['equipment'];
				} 
			});
	  },
	  modifyEquipment() {
		  if(!this.errors.any() && this.modify.qty >= 0) {
			  axios.post('/equipment/modify', {
				  name: this.modify.name,
				  qty: this.modify.qty
			  }).then(response => {
				  if(response.data['status']) {
					  this.closeModifyEquipment();
					  this.populateEquipment();
				  } else {
					  this.modifyUnknownError = response.data['message'];
				  }
			  });
		  } else {
			  this.modifyUnknownError = 'Bad input';
		  }
	  },
	  clearEquipment() {
		  axios.get('/equipment/clear')
			.then(response => {
				if(response.data['status']) {
					this.populateEquipment();
					this.clearEquipmentModal = false
				} else {
					this.clearModalError = response.data['message'];
				}
			});
	  }
  }
}
</script>

