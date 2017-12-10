<template>
<div class="wrapper">
  <div class="animated fadeIn">
    <div class="row">
      <div class="col">
 		<div class="card">
        		<div class="card-header"><a href="/lab/staff">Staff members</a></div>
        		<div class="card-body">
	        		<div v-if="editable" class="row">
	        			<div class="col">
	        				<b-button type="button" variant="success" @click="showAddStaffModal">Add Staff</b-button>
	        				<b-button type="button" variant="danger" @click="showClearStaffModal">Clear Staff</b-button>
	        			</div>
	        		</div>
        		<div class="row top5">
        		    <span class="text-danger"> {{ listError }} </span>
        			<div class="col">
		        		<table class="table table-striped table-hover" v-if="staff.length > 0">
		        			<thead>
		        				<tr>
		        					<td>#</td>
		        					<td>Name</td>
		        					<td>Email</td>
		        					<td>Role</td>
		        					<td>Last login</td>
		        					<td></td>
		        					<td></td>
		        				</tr>
		        			</thead>
		        			<tbody>
		        				<tr v-for="(member, index) in staff">
		        					<td>{{ index+1 }}</td>
		        					<td>{{ member.name }}</td>
		        					<td>{{ member.email }}</td>
		        					<td>{{ member.role }}</td>
		        					<td>{{ member.lastLogIn }}</td>
		        					<td><a v-if="editable" v-on:click="modifyClick(index)" v-bind:id="index" href="#">Modify</a></td>
		        					<td><a v-if="editable" v-on:click="removeClick(index)" v-bind:id="index" href="#">Remove</a></td>
		        				</tr>
		        			</tbody>
		        		</table>
		        		<div class="alert alert-primary text-center" v-else> None :( </div>
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
      <div class="input-group">
         <span class="input-group-addon"><i class="icon-user"></i></span>
      	<b-form-input id="name" name="name" type="text" v-model="form.name" v-validate="'required|alpha_spaces'" :class="{'input': true, 'is-danger': errors.has('name') }" placeholder="Enter name"></b-form-input>
      	</div>
      	<span class="text-danger" v-if="errors.has('name')">Please enter a valid name</span>
      </b-form-group>
      <b-form-group id="roleGroup" label="Role">
        <div class="input-group">
         <span class="input-group-addon"><i class="icon-mustache"></i></span>
      	<b-form-select id="role" @change.native="onChangeRole" name="role" :options="roles" v-model="form.role" ></b-form-select>
      	</div>
      	<span class="text-danger" v-if="roleError" >Please enter a valid role</span>
      </b-form-group>
      <b-form-group id="emailGroup" label="Email">
      <div class="input-group">
         <span class="input-group-addon"><i class="icon-envelope"></i></span>
      	<b-form-input id="email" type="email" name="email" v-model="form.email" v-validate="'required|email'" :class="{'input': true, 'is-danger': errors.has('email') }" placeholder="Enter email"></b-form-input>
      	</div>
      	<span class="text-danger" v-if="errors.has('email')">Please enter a valid email</span>
      </b-form-group>
      <b-form-group id="passwordGroup" label="Password">
        <div class="input-group">
         <span class="input-group-addon"><i class="icon-lock"></i></span>
      	<b-form-input id="password" name="password" type="password" v-model="form.password" v-validate="'required|alpha_num'" :class="{'input': true, 'is-danger': errors.has('password') }" placeholder="Enter password"></b-form-input>
      	</div>
      	<span class="text-danger" v-if="errors.has('password')">Password can only contain letters and numbers</span>
      </b-form-group>
      <div v-if="addModalError != ''" class="row alert alert-danger">{{ addModalError }}</div>
     <b-button type="button" variant="primary" @click="addStaff">Save changes</b-button>
     <b-button type="button" variant="secondary" @click="closeAddStaff">Close</b-button>
	</b-form>
  </b-modal>
  
  <b-modal v-model="modifyStaffModal" hide-footer title="Modify Staff">
      <b-form>
      <b-form-group id="nameGroup" label="Name" description="Full name">
      	<div class="input-group">
         <span class="input-group-addon"><i class="icon-user"></i></span>
      	<b-form-input id="name_2" name="name_2" type="text" v-model="modify.name" v-validate="'required|alpha_spaces'" :class="{'input': true, 'is-danger': errors.has('name_m') }" placeholder="Enter name"></b-form-input>
      	</div>
      	<span class="text-danger" v-if="errors.has('name_m')">Please enter a valid name</span>
      </b-form-group>
      <b-form-group id="roleGroup" label="Role">
      	<div class="input-group">
         <span class="input-group-addon"><i class="icon-mustache"></i></span>
      	<b-form-select id="role_2" @change.native="onChangeRole" name="role_2" :options="roles" v-model="modify.role" ></b-form-select>
      	</div>
      	<span class="text-danger" v-if="roleError" >Please enter a valid role</span>
      </b-form-group>
      <b-form-group id="emailGroup" label="Email">
      	<div class="input-group">
         <span class="input-group-addon"><i class="icon-envelope"></i></span>
      	<b-form-input id="email_2" type="email" name="email"_2 v-model="modify.email" v-validate="'required|email'" :class="{'input': true, 'is-danger': errors.has('email_m') }" placeholder="Enter email"></b-form-input>
      	</div>
      	<span class="text-danger" v-if="errors.has('email_m')">Please enter a valid email</span>
      </b-form-group>
      <b-form-group id="passwordGroup" label="Password" description="Enter nothing to leave as is">
      	<div class="input-group">
         <span class="input-group-addon"><i class="icon-lock"></i></span>
      	<b-form-input id="password_2" name="password_2" type="password" v-model="modify.password" v-validate="'alpha_num'" :class="{'input': true, 'is-danger': errors.has('password_m') }" placeholder="Enter password"></b-form-input>
         </div>
      	<span class="text-danger" v-if="errors.has('password_m')">Password can only contain letters and numbers</span>
      </b-form-group>
      <div v-if="modifyModalError != ''" class="row alert alert-danger">{{ modifyModalError }}</div>
     <b-button type="button" variant="primary" @click="modifyStaff">Save changes</b-button>
     <b-button type="button" variant="secondary" @click="modifyStaffModal = false">Close</b-button>
	</b-form>
  </b-modal>
  
  <b-modal v-model="clearStaffModal" hide-footer title="Clear Staff">
  <b-form>
      <b-form-group id="clearStaffGroup">
      	Are you sure you want to clear all staff?
      </b-form-group>
      <div v-if="clearModalError != ''" class="row alert alert-danger">{{ clearModalError }}</div>
     <b-button type="button" variant="success" @click="clearStaff">Yes</b-button>
     <b-button type="button" variant="danger" @click="clearStaffModal = false">No</b-button>
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
	      modifyStaffModal: false,
	      clearModalError: '',
	      roleError: false,
	      listError: '',
	      addModalError: '',
	      modifyModalError: '',
	      form: {
	    	  	name: '',
	    	  	email: '',
	    	  	role: null,
	    	  	password: ''
	      },
	      modify: {
	    	    prevEmail: '',
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
		  this.errors.clear();
		  this.form.name = '';
		  this.form.role = null;
		  this.form.email = '';
		  this.form.password = '';
		  this.addModalError = '';
	  },
	  closeAddStaff() {
		  this.addStaffModal = false;
		  this.resetAddStaffModal();
	  },
	  modifyClick(index) {
		  this.modify.prevEmail = this.staff[index].email;
		  this.modify.name = this.staff[index].name;
		  this.modify.role = this.staff[index].role;
		  this.modify.email = this.staff[index].email
		  this.errors.clear();
		  this.modifyModalError = '';
		  this.modifyStaffModal = true;
	  },
	  modifyStaff() {
		  if(this.modify.role == null) {
			  this.roleError = true;
		  }
		  
		  if (!this.errors.any() && !this.roleError) {
			  axios.post('/staff/modify', {
				  name: this.modify.name,
				  prevEmail: this.modify.prevEmail,
			    	  email: this.modify.email,
			    	  role: (this.modify.role == 'Research Associate') ? 0 : 1,
			    	  password: this.modify.password
			  })
				.then(response => {
					if(response.data['status']) {
						  this.populateStaff();
						  this.roleError = false;
						  this.modifyStaffModal = false;
					} else {
						this.modifyModalError = response.data['message'];
					}
				});
		  }
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
