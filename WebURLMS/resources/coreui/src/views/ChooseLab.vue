<template>
<div>
  <div class="app flex-row align-items-center">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-8">
          <div class="card-group mb-0">
            <div class="card p-4">
              <div class="card-body text-center">
                <div class="row">
	                	<div class="col col-12">
	                		<button type="button" class="close" @click="closeClick">×</button>
	                	</div>
                </div>
                <h1>Laboratories</h1>
                <div class="row mb-3">
	        			<div class="col">
	        				<b-button v-if="director" type="button" variant="success" @click="showAddLabModal">Add Lab</b-button>
	        				<b-button v-if="director" type="button" variant="danger" @click="showClearLabsModal">Clear Labs</b-button>
	        			</div>
	        		</div>
	        		<div v-if="topError != ''" class="row alert alert-danger">{{ topError }}</div>
                <div class="row" style="min-height: 350px;">
	                <table class="table table-striped table-hover">
	                		<thead>
	                			<tr>
	                				<th>#</th>
	                				<th>Name</th>
	                				<th>Field Of Study</th>
	                				<th>Start Date</th>
	                				<th>Active</th>
	                				<th></th>
	                				<th v-if="director"></th>
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
		        					<td><a v-if="director" v-on:click="deleteClick(index)" v-bind:id="index" href="#">Delete</a></td>
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
        <div class="input-group">
         <span class="input-group-addon"><i class="icon-chemistry"></i></span>
      	<b-form-input id="name" name="name" type="text" v-model="form.name"  placeholder="Enter name"></b-form-input>
      	</div>
      	<span class="text-danger" v-if="errors.has('name')">Please enter a valid name</span>
      </b-form-group>
      <b-form-group id="fieldGroup" label="Field">
      <div class="input-group">
         <span class="input-group-addon"><i class="icon-globe"></i></span>
      	<b-form-input id="field" type="text" name="field" v-model="form.field" placeholder="Enter field"></b-form-input>
      	</div>
      	<span class="text-danger" v-if="errors.has('field')">Please enter a valid field</span>
      </b-form-group>
      <b-form-group id="dateGroup" label="Start date">
      <div class="input-group">
         <span class="input-group-addon"><i class="icon-calendar"></i></span>
      	<date-picker v-model="form.date" :config="dateConfig"></date-picker>
      	</div>
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
		  topError: '',
		  staff: false,
		  director: false,
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
  mounted : function(){
		this.populateLabs();
		this.updateLabStatus();
  },
  components: {
	  datePicker
  },
  methods: {
	  updateLabStatus() {
		  axios.get('/user/info')
			.then(response => {
				if(response.data['status']) {
					if(response.data['director']) {
						this.director = true;
						this.staff = false;
					} else {
						this.director = false;
						this.staff = true;
					}
				}
			});
	  },
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
		  if(this.activeOptions.indexOf(this.form.active) >= 0 && !this.errors.any()) {
			  axios.post('/labs/add', {
				  name: this.form.name,
				  field: this.form.field,
				  date: this.form.date,
				  active: this.form.active
			  })
	  			.then(response => {
	  				if(response.data['status']) {
	  					this.populateLabs();
	  				} 
	  			});
			  this.closeAddLabModal();
		  }
	  },
	  closeAddLabModal() {
		  this.resetAddLabModal();
		  this.addLabModal = false;
	  },
	  clearLabs() {
		  axios.get('/labs/clear')
  			.then(response => {
  				if(response.data['status']) {
  					this.populateLabs();
  					this.clearLabsModal = false;
  				} 
  			});
	  },
	  deleteLab() {
		  axios.post('/labs/delete', {
			  name: this.labToBeDeleted.name
		  })
  			.then(response => {
  				if(response.data['status']) {
  					this.populateLabs();
  					this.deleteLabModal = false;
  				} 
  			});
	  },
	  enterClick(index) {
		  if(this.labs[index].active != 'Active') {
			  if(this.director) {
				axios.post('/labs/enter', {
					  name: this.labs[index].name
				  })
		  			.then(response => {
		  				if(response.data['status']) {
		  					this.$router.push('/');
		  				} 
		  			});
				} else {
					this.topError = 'Can\'t enter unactive lab'
				}
		  } else {
			  axios.post('/labs/enter', {
				  name: this.labs[index].name
			  })
	  			.then(response => {
	  				if(response.data['status']) {
	  					this.$router.push('/');
	  				} 
	  			});
		  }
	  },
	  closeClick() {
		  this.$router.push('/login');
	  },
	  populateLabs() {
		  axios.get('/labs/get')
  			.then(response => {
  				if(response.data['status']) {
  					this.labs = response.data['labs'];
  				} 
  			});
	  },
	  deleteClick(index) {
		  this.labToBeDeleted.index = index;
		  this.labToBeDeleted.name = this.labs[index].name;
		  this.deleteLabModal = true;
	  }
  }
}
</script>
