<template>
<div class="wrapper">
  <div class="animated fadeIn">
    <div class="row">
      <div class="col">
 		<div class="card">
        		<div class="card-header"> Staff members </div>
        		<div class="card-body">
	        		<div v-if="editable" class="row">
	        			<div class="col">
	        				<b-button type="button" variant="success" @click="showAddStaffModal">Add Staff</b-button>
	        				<b-button type="button" variant="danger" @click="showClearStaffModal">Clear Staff</b-button>
	        			</div>
	        		</div>
        		<div class="row">
        		    <span class="text-danger"> {{ listError }} </span>
        			<div class="col">
		        		<table class="table table-striped table-hover">
		        			<thead>
		        				<tr>
		        					<td>#</td>
		        					<td>Name</td>
		        					<td>Email</td>
		        					<td>Role</td>
		        					<td></td>
		        				</tr>
		        			</thead>
		        			<tbody>
		        				<tr v-for="(member, index) in staff">
		        					<td>{{ index+1 }}</td>
		        					<td>{{ member.name }}</td>
		        					<td>{{ member.email }}</td>
		        					<td>{{ member.role }}</td>
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
 <b-modal v-model="addStaffModal" hide-footer title="Add Staff">
      <b-form @reset="resetAddStaffModal">
      <b-form-group id="nameGroup" label="Name" description="Full name">
      	<b-form-input id="name" name="name" type="text" v-model="form.name" v-validate="'required|alpha_spaces'" :class="{'input': true, 'is-danger': errors.has('name') }" placeholder="Enter name"></b-form-input>
      	<span class="text-danger" v-if="errors.has('name')">Please enter a valid name</span>
      </b-form-group>
      <b-form-group id="roleGroup" label="Role">
      	<b-form-select id="role" @change.native="onChangeRole" name="role" :options="roles" v-model="form.role" ></b-form-select>
      	<span class="text-danger" v-if="roleError" >Please enter a valid role</span>
      </b-form-group>
      <b-form-group id="emailGroup" label="Email">
      	<b-form-input id="email" type="email" name="email" v-model="form.email" v-validate="'required|email'" :class="{'input': true, 'is-danger': errors.has('email') }" placeholder="Enter email"></b-form-input>
      	<span class="text-danger" v-if="errors.has('email')">Please enter a valid email</span>
      </b-form-group>
      <b-form-group id="passwordGroup" label="Password">
      	<b-form-input id="password" name="password" type="password" v-model="form.password" v-validate="'required|alpha_num'" :class="{'input': true, 'is-danger': errors.has('password') }" placeholder="Enter password"></b-form-input>
      	<span class="text-danger" v-if="errors.has('password')">Password can only contain letters and numbers</span>
      </b-form-group>
     <b-button type="button" variant="primary" @click="addStaff">Save changes</b-button>
     <b-button type="button" variant="secondary" @click="closeAddStaff">Close</b-button>
     <span class="text-danger">{{ addModalError }}</span>
	</b-form>
  </b-modal>
  <b-modal v-model="clearStaffModal" hide-footer title="Clear Staff">
  <b-form>
      <b-form-group id="clearStaffGroup">
      	Are you sure you want to clear all staff?
      </b-form-group>
     <b-button type="button" variant="success" @click="clearStaff">Yes</b-button>
     <b-button type="button" variant="danger" @click="clearStaffModal = false">No</b-button>
     <span class="text-danger">{{ clearModalError }}</span>
	</b-form>	
  </b-modal>
	<!--   -->
</div>
</template>

<script>
export default {
  name: 'staff',
  data () {
	    return {
	      addStaffModal: false,
	      clearStaffModal: false,
	      clearModalError: '',
	      roleError: false,
	      listError: '',
	      addModalError: '',
	      form: {
	    	  	name: '',
	    	  	email: '',
	    	  	role: null,
	    	  	password: ''
	      },
	      roles: [
	    	     { text: 'Select One', value: null, disabled: true }, 'Research Assistant', 'Research Associate'
	      ],
	      staff: [
	      ]
	    }
	  },
  props: {
	  editable: {
		type: Boolean,
		default: true
	  }
  },
  mounted : function(){
		this.populateStaff();
	},
  methods: {
	  showAddStaffModal() {
		  this.resetAddStaffModal();
		  this.errors.clear();
		  this.addStaffModal = true;
	  },
	  showClearStaffModal() {
		  this.clearModalError = '';
		  this.clearStaffModal = true;
	  },
	  resetAddStaffModal() {
		  this.form.name = '';
		  this.form.role = null;
		  this.form.email = '';
		  this.form.password = '';
		  this.errors.clear();
	  },
	  closeAddStaff() {
		  this.addStaffModal = false;
		  this.resetAddStaffModal();
	  },
	  removeClick(index) {
		  axios.post('/staff/delete', {
			  email: this.staff[index].email
		  })
			.then(response => {
				if(response.data['status']) {
					  this.populateStaff();
				} else {
					this.listError = response.data['message'];
				}
			});
	  },
	  populateStaff() {
		  this.listError = '';
		  axios.get('/staff/get')
			.then(response => {
				if(response.data['status']) {
					this.staff = response.data['staff'];
				} 
			});
	  },
	  onChangeRole(evt) {
		  if(evt.target.value == '') {
			  this.roleError = true;
		  } else {
			  this.roleError = false;
		  }
	  },
	  addStaff() {
		  this.$validator.validateAll();
		  
		  if(this.form.role == null) {
			  this.roleError = true;
		  }
		  
		  if (!this.errors.any() && !this.roleError) {
			  axios.post('/staff/add', {
				  name: this.form.name,
			    	  email: this.form.email,
			    	  role: (this.form.role == 'Research Associate') ? 0 : 1,
			    	  password: this.form.password
			  })
				.then(response => {
					if(response.data['status']) {
						  this.populateStaff();
						  this.addStaffModal = false;
						  this.resetAddStaffModal();
					} else {
						this.addModalError = response.data['message'];
					}
				});
		  }
	  },
	  clearStaff() {
		  axios.get('/staff/clear')
			.then(response => {
				if(response.data['status']) {
					this.populateStaff();
					this.clearStaffModal = false;
				} else {
					this.clearModalError = response.data['message'];
				}
			});
	  }
  }
}
</script>
