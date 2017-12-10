<template>
<div>
	<header class="app-header navbar">
		<button class="navbar-toggler mobile-sidebar-toggler d-lg-none"
			type="button" @click="mobileSidebarToggle">&#9776;</button>
		<b-link class="navbar-brand" to="/"></b-link>
		<button class="navbar-toggler sidebar-toggler d-md-down-none"
			type="button" @click="sidebarMinimize">&#9776;</button>
		<b-nav is-nav-bar class="ml-auto top-right-name"> <b-nav-item-dropdown
			right> <template slot="button-content">
		<span class="d-md-down-none">{{ displayName }}</span> </template> <b-dropdown-item
			@click="seeLabs">
		<i class="fa fa-flask"></i> See labs</b-dropdown-item> <b-dropdown-divider></b-dropdown-divider>
		<b-dropdown-item @click="showUpdateProfileModal">
		<i class="fa fa-user"></i> Profile</b-dropdown-item> <b-dropdown-item
			@click="logoutClicked">
		<i class="fa fa-lock"></i> Logout</b-dropdown-item> </b-nav-item-dropdown> </b-nav>
	</header>
	<b-modal v-model="updateProfileModal" hide-footer
		title="Update Profile">
	<div class="alert alert-success text-center"
		v-if="successMessage != ''">{{ successMessage }}</div>
	<b-form @reset="populateProfile"> <b-form-group id="nameGroup"
		label="Name" description="Full name">
	<div class="input-group">
		<span class="input-group-addon"><i class="icon-user"></i></span>
		<b-form-input id="name" name="name" type="text" v-model="profile.name"
			v-validate="'required|alpha_spaces'"
			:class="{'input': true, 'is-danger': errors.has('name') }"
			placeholder="Enter name"></b-form-input>
	</div>
	<span class="text-danger" v-if="errors.has('name')">Please enter
		a valid name</span> </b-form-group> <b-form-group id="roleGroup" label="Role"
		v-if="profile.role != null">
	<div class="input-group">
		<span class="input-group-addon"><i class="icon-mustache"></i></span>
		<b-form-input id="role" name="role" v-model="profile.role" readonly></b-form-input>
	</div>
	</b-form-group> <b-form-group id="emailGroup" label="Email">
	<div class="input-group">
		<span class="input-group-addon"><i class="icon-envelope"></i></span>
		<b-form-input id="email" type="email" name="email"
			v-model="profile.email" v-validate="'required|email'"
			:class="{'input': true, 'is-danger': errors.has('email') }"
			placeholder="Enter email"></b-form-input>
	</div>
	<span class="text-danger" v-if="errors.has('email')">Please
		enter a valid email</span> </b-form-group> <b-form-group id="passwordGroup" label="Password">
	<div class="input-group mb-3">
		<span class="input-group-addon"><i class="icon-lock"></i></span>
		<b-form-input id="password" name="password" type="password"
			v-model="profile.previousPassword"
			placeholder="Enter previous password"></b-form-input>
	</div>
	<div class="input-group">
		<span class="input-group-addon"><i class="icon-lock"></i></span>
		<b-form-input id="password2" name="password2" type="password"
			v-model="profile.password" placeholder="Enter new password"></b-form-input>
	</div>
	<div v-if="updateError != ''" class="row alert alert-danger">{{
		updateError }}</div>
	</b-form-group> <b-button type="button" variant="primary" @click="updateProfile">Save
	Changes</b-button> <b-button type="button" variant="secondary"
		@click="updateProfileModal = false">Close</b-button> </b-form> </b-modal>
</div>
</template>
<script>
export default {
  name: 'header',
  data() {
	  return {
		  updateProfileModal: false,
		  updateError: '',
		  displayName: '',
		  successMessage: '',
		  profile: {
			  name: '',
			  role: null,
			  email: '',
			  previousPassword: '',
			  password: ''
		  }
	  }
  },
  created : function(){
		this.populateProfile();
	},
  methods: {
    sidebarToggle (e) {
      e.preventDefault()
      document.body.classList.toggle('sidebar-hidden')
    },
    sidebarMinimize (e) {
      e.preventDefault()
      document.body.classList.toggle('sidebar-minimized')
    },
    mobileSidebarToggle (e) {
      e.preventDefault()
      document.body.classList.toggle('sidebar-mobile-show')
    },
    logoutClicked() {
	    axios.get('/logout')
			.then(response => {
				this.$router.push('/login');
			});
    },
    seeLabs() {
      this.$router.push('/choose_lab');
    },
    populateProfile() {
    	     axios.get('/user/info')
			.then(response => {
				if(response.data['status']) {
					this.displayName = response.data['name'];
					this.profile.name = response.data['name'];
					this.profile.role = response.data['role'];
					this.profile.email = response.data['email'];
					this.profile.previousPassword = '';
					this.profile.password = '';
				} else {
					this.$router.push('/login');
				}
			});
    },
    showUpdateProfileModal() {
      	this.updateError = '';
    		this.updateProfileModal = true;
    },
    updateProfile() {
    		if (!this.errors.any()) {
    				//Update profile
      			axios.post('/user/updateProfile', {
    		  				    name: this.profile.name,
    		  			 	    role: (this.profile.role == 'Research Associate') ? 0 : 1,
    		  			  		email: this.profile.email
    				})
    				.then(response => {
    					if(response.data['status']) {
    						//Update password
    			  			  if(this.profile.password != '' && this.profile.previousPassword != '') {
    			  				axios.post('/user/updatePassword', {
    			  					previousPassword: this.profile.previousPassword,
    			  					newPassword: this.profile.password
    			  				})
    			  				.then(response => {
    			  					if(response.data['status']) {
    			  						this.successMessage = response.data['message'];
    			  						var self = this;
    			  						setTimeout(function() {
    			  							self.updateProfileModal = false;
    	    			  						self.populateProfile();
    	    			  						self.successMessage = '';
    			  						}, 2000);
    			  						
    			  					} else {
    			  						this.updateError = response.data['message'];
    			  						this.populateProfile();
    			  						var self = this;
    			  						setTimeout(function() {
    			  							self.updateError = '';
    			  						}, 2000);
    			  					}
    			  				});
    			  			  } else {
    			  				this.successMessage = response.data['message'];
    			  				  var self = this;
    			  				setTimeout(function() {
		  							self.updateProfileModal = false;
    			  						self.populateProfile();
    			  						self.successMessage = '';
		  						}, 2000);
    			  			  }
    					} else {
    						this.updateError = response.data['message'];
	  						var self = this;
	  						setTimeout(function() {
	  							self.updateError = '';
	  						}, 2000);
    					}
    				});
    			  
  		 } else {
  			this.updateError = 'Error';
				var self = this;
				setTimeout(function() {
					self.updateError = '';
				}, 2000);
  		 }
    }
  }
}
</script>
