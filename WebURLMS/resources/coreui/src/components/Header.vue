<template>
  <div>
  <header class="app-header navbar">
    <button class="navbar-toggler mobile-sidebar-toggler d-lg-none" type="button" @click="mobileSidebarToggle">&#9776;</button>
    <b-link class="navbar-brand" to="#"></b-link>
    <button class="navbar-toggler sidebar-toggler d-md-down-none" type="button" @click="sidebarMinimize">&#9776;</button>
    <b-nav is-nav-bar class="ml-auto top-right-name">
      <b-nav-item-dropdown right>
        <template slot="button-content">
          <span class="d-md-down-none">{{ profile.name }}</span>
        </template>
        <b-dropdown-item @click="seeLabs"><i class="fa fa-flask"></i> See labs</b-dropdown-item> 
        <b-dropdown-divider></b-dropdown-divider> 
        <b-dropdown-item @click="showUpdateProfileModal"><i class="fa fa-user"></i> Profile</b-dropdown-item>
        <b-dropdown-item><i class="fa fa-lock"></i> Logout</b-dropdown-item>
      </b-nav-item-dropdown>
    </b-nav>
  </header>
  <b-modal v-model="updateProfileModal" hide-footer title="Update Profile">
      <b-form @reset="populateProfile">
      <b-form-group id="nameGroup" label="Name" description="Full name">
      	<b-form-input id="name" name="name" type="text" v-model="profile.name" v-validate="'required|alpha_spaces'" :class="{'input': true, 'is-danger': errors.has('name') }" placeholder="Enter name"></b-form-input>
      	<span class="text-danger" v-if="errors.has('name')">Please enter a valid name</span>
      </b-form-group>
      <b-form-group id="roleGroup" label="Role" v-if="profile.role != null">
      	<b-form-input id="role" name="role" :options="roles" v-model="profile.role" readonly></b-form-input>
      </b-form-group>
      <b-form-group id="emailGroup" label="Email">
      	<b-form-input id="email" type="email" name="email" v-model="profile.email" v-validate="'required|email'" :class="{'input': true, 'is-danger': errors.has('email') }" placeholder="Enter email"></b-form-input>
      	<span class="text-danger" v-if="errors.has('email')">Please enter a valid email</span>
      </b-form-group>
      <b-form-group id="passwordGroup" label="Password">
      	<b-form-input id="password" name="password" type="password" v-model="profile.password" v-validate="'required|alpha_num'" :class="{'input': true, 'is-danger': errors.has('password') }" placeholder="Enter password"></b-form-input>
      	<span class="text-danger" v-if="errors.has('password')">Password can only contain letters and numbers</span>
      </b-form-group>
     <b-button type="button" variant="primary" @click="updateProfile">Save Changes</b-button>
     <b-button type="button" variant="secondary" @click="updateProfileModal = false">Close</b-button>
	</b-form>	
  </b-modal>
  </div>
</template>
<script>
export default {
  name: 'header',
  data() {
	  return {
		  updateProfileModal: false,
		  profile: {
			  name: 'Andre',
			  role: null,
			  email: 'andre@urlms.ca',
			  password: ''
		  }
	  }
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
    seeLabs (e) {
      this.$router.push('/choose_lab')
    },
    populateProfile() {
    		//Load elements from php
    },
    showUpdateProfileModal() {
    		this.populateProfile();
    		this.updateProfileModal = true;
    },
    isValidInput() {
   	 	this.$validator.validateAll();
   		
   	 	if(profile.name == '') {
   	 		errors.add('name');
   	 		return false;
   	 	}
   	 	if(profile.email == '') {
   	 		errors.add('email');
   	 		return false;
   	 	}
   	 	if(profile.password == '') {
   	 		errors.add('password');
   	 		return false;
   	 	}
    },
    updateProfile() {
    		if (isValidInput() && !this.errors.any()) {
  			  //Update profile
  			  this.updateProfileModal = false;
  		 }
    }
  }
}
</script>
