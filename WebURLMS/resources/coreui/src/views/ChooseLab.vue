<template>
<div>
  <div class="app flex-row align-items-center">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-8">
          <div class="card-group mb-0">
            <div class="card p-4">
              <div class="card-body text-center">
                <h1>Laboratories</h1>
                <div class="row">
	        			<div class="col">
	        				<b-button type="button" variant="success" @click="showAddLabModal">Add Lab</b-button>
	        				<b-button type="button" variant="danger" @click="showClearLabsModal">Clear Labs</b-button>
	        			</div>
	        		</div>
                <div style="min-height: 350px;">
	                <table class="table table-striped table-hover">
	                		<thead>
	                			<tr>
	                				<th>#</th>
	                				<th>Name</th>
	                				<th>Field Of Study</th>
	                				<th>Start Date</th>
	                				<th>Active</th>
	                				<th></th>
	                				<th></th>
	                			</tr>
	                		</thead>
	                		<tbody>
		        				<tr v-for="(lab, index) in labs">
		        					<td>{{ index+1 }}</td>
		        					<td>{{ lab.name }}</td>
		        					<td>{{ lab.field }}</td>
		        					<td>{{ lab.date }}</td>
		        					<td>
		        					<i v-if="lab.active == 'Active'" class="fa fa-check"></i>
		        					<i v-else class="fa fa-close"></i>
		        					</td>
		        					<td><a v-on:click="enterClick(index)" v-bind:id="index" href="#">Enter</a></td>
		        					<td><a v-on:click="deleteClick(index)" v-bind:id="index" href="#">Delete</a></td>
		        				</tr>
		        			</tbody>
	                </table>
	             </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  <b-modal v-model="addLabModal" hide-footer title="Add Lab">
      <b-form @reset="resetAddLabModal">
      <b-form-group id="nameGroup" label="Name">
      	<b-form-input id="name" name="name" type="text" v-model="form.name"  placeholder="Enter name"></b-form-input>
      	<span class="text-danger" v-if="errors.has('name')">Please enter a valid name</span>
      </b-form-group>
      <b-form-group id="fieldGroup" label="Field">
      	<b-form-input id="field" type="text" name="field" v-model="form.field" placeholder="Enter field"></b-form-input>
      	<span class="text-danger" v-if="errors.has('field')">Please enter a valid field</span>
      </b-form-group>
      <b-form-group id="dateGroup" label="Date">
      	<date-picker v-model="form.date" :config="dateConfig"></date-picker>
      </b-form-group>
      <b-form-radio-group id="activeGroup" v-model="form.active" :options="activeOptions" name="activeGroup"></b-form-radio-group>	
    <b-button type="button" variant="primary" @click="addLab">Save Changes</b-button>
     <b-button type="button" variant="secondary" @click="closeAddLabModal">Close</b-button>
    </b-form>
  </b-modal>
  
  <b-modal v-model="clearLabsModal" hide-footer title="Clear Labs">
  <b-form>
      <b-form-group id="clearLabsGroup">
      	Are you sure you want to clear all labs?
      </b-form-group>
     <b-button type="button" variant="success" @click="clearLabs">Yes</b-button>
     <b-button type="button" variant="danger" @click="clearLabsModal = false">No</b-button>
	</b-form>	
  </b-modal>
  
  <b-modal v-model="deleteLabModal" hide-footer title="Delete Lab">
  <b-form>
      <b-form-group id="deleteLabGroup">
      	Are you sure you want to delete lab {{ labToBeDeleted.name }}?
      </b-form-group>
     <b-button type="button" variant="success" @click="deleteLab">Yes</b-button>
     <b-button type="button" variant="danger" @click="deleteLabModal = false">No</b-button>
	</b-form>	
  </b-modal>
  
</div>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css';
import datePicker from 'vue-bootstrap-datetimepicker';
import 'eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.css';

export default {
  name: 'choose_lab',
  data() {
	  return {
		  addLabModal: false,
		  deleteLabModal: false,
		  clearLabsModal: false,
		  form: {
			  name: '',
			  field: '',
			  date: null,
			  active: 'Active'
		  },
		  labToBeDeleted: {
			  index: 0,
			  name: ''
		  },
		  labs: [
			{
				  name: 'Lab1',
				  field: 'Physics',
				  date: Date.now(),
				  active: 'Active'
			},
			{
				name: 'Lab2',
				field: 'Chemistry',
				date: Date.now(),
				active: 'Unactive'
			}
		  ],
		  activeOptions: [
			'Active', 'Unactive'  
		  ],
		  dateConfig: {
			  format: 'MM/DD/YYYY',
	          useCurrent: true
		  }
	  }
  },
  components: {
	  datePicker
  },
  methods: {
	  resetAddLabModal() {
		  this.form.name = '';
		  this.form.field = '';
		  this.form.date = null;
		  this.form.active = 'Active';
		  this.errors.clear();
	  },
	  showAddLabModal() {
		  this.errors.clear();
		  this.addLabModal = true;
	  },
	  showClearLabsModal() {
		  this.clearLabsModal = true;
	  },
	  addLab() {
		  if(this.verifyAddLabInput() && !this.errors.any()) {
			  this.labs.push({name: this.form.name, field: this.form.field, date: this.form.date, active: this.form.active});
			  this.closeAddLabModal();
		  }
	  },
	  verifyAddLabInput() {
		  if(this.form.name == '') {
			  this.errors.add('name');
			  return false;
		  }
		  
		  if(this.form.field == '') {
			  this.errors.add('field');
			  return false;
		  }
		  
		  if(this.form.date == null) {
			  this.errors.add('date');
			  return false;
		  }
		  
		  if(this.activeOptions.indexOf(this.form.active) <= -1)
			  return false;
		  
		  return true;
	  },
	  closeAddLabModal() {
		  this.resetAddLabModal();
		  this.addLabModal = false;
	  },
	  clearLabs() {
		  this.labs = [];
		  this.clearLabsModal = false;
	  },
	  deleteLab() {
		  this.labs.splice(this.labToBeDeleted.index, 1);
		  this.deleteLabModal = false;
	  },
	  enterClick(index) {
		  //enter lab with name this.labs[index].name
	  },
	  deleteClick(index) {
		  this.labToBeDeleted.index = index;
		  this.labToBeDeleted.name = this.labs[index].name;
		  this.deleteLabModal = true;
	  }
  }
}
</script>
